package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class AlteraUsuarios {

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
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		String cpfConsulta = JOptionPane.showInputDialog("Informe o CPF para dobrar o sal�rio.");
		user = em.find(Usuario.class, cpfConsulta);	//Find sempre procura pela pk	
		
		user.setSalario(user.getSalario().multiply(BigDecimal.valueOf(2)));		
		em.merge(user);		
		
		tx.commit();
		
		em.close();
		emf.close();
		
		System.out.println(user);
	}

}
