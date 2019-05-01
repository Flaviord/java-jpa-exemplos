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

public class DesconectaConectaUsuarios {

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

		System.out.println("user hash :="+user.hashCode());
		
		if (em.contains(user)){
			//verifica se o objeto esta persistent ER->OOP (plugado ER com OOP)
			System.out.println("SIM.");
		}

		em.detach(user);// COLOCA SOMENTE O OBJETO QUE SE QUER EM MODO TRANSIENT (SEM REMOVER DA BASE)		
		em.clear();// COLOCA TODAS AS ENTIDADES EM ESTADO TRANSIENT (SEM REMOVER DA BASE)		
		
		
		
		if(em.contains(user)){
			System.out.println("SIM.");
		}else{
			System.out.println("NÃO");
		}
			
		
		user.setSalario(user.getSalario().multiply(BigDecimal.valueOf(1.5)));
		user = em.merge(user);//recoloca no modo PERSISTENT e faz o retorno do objeto para evitar duplicidade de objeto
		
		tx.commit();
		
		em.close();
		emf.close();
		
		System.out.println(user.toString());
		System.out.println("user hash :="+user.hashCode());
		
	}

}
