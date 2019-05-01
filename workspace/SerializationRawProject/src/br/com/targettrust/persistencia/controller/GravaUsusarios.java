package br.com.targettrust.persistencia.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class GravaUsusarios {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
        // Criando objetos TRANSIENT na heap
		Usuario u1 = new Usuario();
		
		u1.setNome("Flavio Rodrigues");
		u1.setCpf("99988866677");
		u1.setRg("1232143");
		u1.setDataNasc(new GregorianCalendar(2013,06,18).getTime());
		//u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal
		
		u1.setSalario(new BigDecimal(2550.78));// forma um
		u1.setSalario(BigDecimal.valueOf(2550.78));// forma dois

		Usuario u2 = new Usuario();
		
		u2.setNome("Fla Rigues");
		u2.setCpf("00888888877");
		u2.setRg("100000003");
		u2.setDataNasc(new GregorianCalendar(2013,06,28).getTime());
		//u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal
		
		u2.setSalario(new BigDecimal(1550.78));// forma um
		u2.setSalario(BigDecimal.valueOf(1550.78));// forma dois
		
		
		// Colocando os objetos no em estado PERSISTENT
		File file = new File("C:\\TrabalhoJPA\\usuarios.ser");
		FileOutputStream fos = new FileOutputStream(file);
		
		//ObjectOutputStream = utilizado para inserir o objeto inteiro no arquivo.
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(u2);
		oos.writeObject(u1);
		oos.flush();
		oos.close();
		fos.close();
		
		System.out.println("U1 = "+u1.toString());
		System.out.println("U2 = "+u2.toString());
		System.out.println("Persistiu (Gravou) os Objetos!!!");
	}

}
