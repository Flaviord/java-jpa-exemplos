package br.com.targettrust.persistencia.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import br.com.targettrust.persistencia.model.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class RecuperaUsuarios {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Usuario u1 ;
		Usuario u2 ;
	
		// Recuperando os objetos no em estado PERSISTENT
		FileReader file = new FileReader("C:\\TrabalhoJPA\\usuarios.xml");
		
		XStream xstream =  new XStream(new DomDriver("ISO-8869-1"));
		xstream.alias("usuario", Usuario.class);
		
		ObjectInputStream ois = xstream.createObjectInputStream(file);
		
		u2 = (Usuario) ois.readObject();
		u1 = (Usuario) ois.readObject();
		
		ois.close();
		
		System.out.println("U1 = "+u1.toString());
		System.out.println("U2 = "+u2.toString());
	}


}
