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
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		//String cpfConsulta = JOptionPane.showInputDialog("Informe o CPF para dobrar o salário.");
		//user = em.find(Usuario.class, cpfConsulta);	//Find sempre procura pela pk	
		
		String idConsulta = JOptionPane.showInputDialog("Informe o Id de consulta.");
		Long idConsultaConv = Long.parseLong(idConsulta); 
		user = em.find(Usuario.class, idConsultaConv);
		
		user.setSalario(user.getSalario().multiply(BigDecimal.valueOf(2)));		
		em.merge(user);		
		
		if(JOptionPane.showConfirmDialog(null, "Deseja dobrar o salario ?") == 0 ){
			tx.commit();
		}else{
			em.refresh(user);// primeiro deve atualizar o objeto
			tx.rollback();//depois rollback
		}
		em.close();
		emf.close();
		
		System.out.println(user);
	}

}
