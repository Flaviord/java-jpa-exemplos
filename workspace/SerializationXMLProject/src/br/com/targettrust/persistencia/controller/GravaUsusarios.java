package br.com.targettrust.persistencia.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import br.com.targettrust.persistencia.model.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
		// FileWriter utilizado para arquivos (.txt)
		FileWriter file = new FileWriter("C:\\TrabalhoJPA\\usuarios.xml");
		
		// XStream para gravar no arquivo xml
		XStream xstream = new XStream(new DomDriver("ISO-8859-1"));// ISO-8859-1 PARA MANTER A COD LATIM-1
		
		// cria um alias para a tag onde estava a pck
		xstream.alias("usuario", Usuario.class);
		
		//ObjectOutputStream = utilizado para inserir o objeto inteiro no arquivo.
		ObjectOutputStream oos = xstream.createObjectOutputStream(file,"usuarios");
		
		oos.writeObject(u2);
		oos.writeObject(u1);
		
		oos.close();
		
		System.out.println("Persistiu (Gravou) os Objetos!!!");
	}

}
