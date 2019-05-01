package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Endereco;
import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RemoveEndereco {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap
		Usuario user = null ;
		Endereco ender = null ;
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		
		String idConsulta = JOptionPane.showInputDialog("Informe o Id Usuario.");
		Long idConsultaConv = Long.parseLong(idConsulta); 

		String idEnderConsulta = JOptionPane.showInputDialog("Informe o Id Endereco.");
		Long idEnderConsultaConv = Long.parseLong(idEnderConsulta); 
		
		user = em.find(Usuario.class, idConsultaConv);	//SELECT 		
		ender = em.find(Endereco.class, idEnderConsultaConv);	//SELECT
		
		user.removeEndereco(ender);//porque estamos utilizando o orphanRemovel = TRUE
		//user = em.merge(user);//não precisa mais realizar essa operação
		tx.commit();
		
		em.close();
		emf.close();
	}

}
