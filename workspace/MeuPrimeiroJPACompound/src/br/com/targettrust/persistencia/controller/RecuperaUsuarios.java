package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Usuario;
import br.com.targettrust.persistencia.model.UsuarioPK;

// Controller (Java SE)

public class RecuperaUsuarios {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap
		Usuario user = null ;
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a mem�ria (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
	
		String rgConsulta = JOptionPane.showInputDialog("Informe o RG.");
		String nomeConsulta = JOptionPane.showInputDialog("Informe o Nome.");
		user = em.find(Usuario.class,new UsuarioPK(nomeConsulta, rgConsulta) );	//Find sempre procura pela pk	
		
		
		
		em.close();
		emf.close();
		
		System.out.println(user);
	}

}
