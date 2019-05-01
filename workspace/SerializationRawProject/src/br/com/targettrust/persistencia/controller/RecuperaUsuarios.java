package br.com.targettrust.persistencia.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import br.com.targettrust.persistencia.model.Usuario;

public class RecuperaUsuarios {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Usuario u1 ;
		Usuario u2 ;
		
		
		
		// Recuperando os objetos no em estado PERSISTENT
		File file = new File("C:\\TrabalhoJPA\\usuarios.ser");
		FileInputStream fis = new FileInputStream(file);
		
		//ObjectInputStream = utilizado para recuperar o objeto inteiro do arquivo.
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		u2 = (Usuario) ois.readObject();
		u1 = (Usuario) ois.readObject();
		
		ois.close();
		fis.close();
		
		System.out.println("U1 = "+u1.toString());
		System.out.println("U2 = "+u2.toString());
	}


}
