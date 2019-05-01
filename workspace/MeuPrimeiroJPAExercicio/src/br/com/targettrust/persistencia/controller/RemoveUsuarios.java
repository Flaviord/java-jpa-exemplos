package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RemoveUsuarios {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap
		Usuario user = null ;
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		String cpfConsulta = JOptionPane.showInputDialog("Informe o CPF que será removido.");
		user = em.find(Usuario.class, cpfConsulta);	//Find sempre procura pela pk	
		
		if (em.contains(user)){
			//verifica se o objeto esta persistent ER->OOP (plugado ER com OOP)
			System.out.println("SIM.");
		}
		
		em.remove(user);			
		tx.commit();
		
		if (em.contains(user)){
			System.out.println("SIM.");
		}
		
		//persiste novamente o objeto
		//tx.begin();
		//em.persist(user);
		//tx.commit();
		
		//OBS.: Apos remover o objeto continua na HEAP.
		//Retira o objeto da HEAP
		//Se for removido e não for persistido utilize
		user = null;
		
		em.close();
		emf.close();
		
		System.out.println(user);
		
	}

}
