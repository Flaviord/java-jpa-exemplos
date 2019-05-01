package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class GravaUsuarios {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap ou seja em Memória volatil.
		Usuario u1 = new Usuario();
		
		u1.getUsuarioPK().setNome("Flavio");
		u1.setCpf("999");
		u1.getUsuarioPK().setRg("123");
		u1.setDataNasc(new GregorianCalendar(2013,06,18).getTime());
		//u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal
		
		u1.setSalario(new BigDecimal(2550.78));// forma um
		u1.setSalario(BigDecimal.valueOf(2550.78));// forma dois

		Usuario u2 = new Usuario();
		
		u2.getUsuarioPK().setNome("Rodrigues");
		u2.setCpf("888");
		u2.getUsuarioPK().setRg("100");
		u2.setDataNasc(new GregorianCalendar(2013,06,28).getTime());
		//u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal
		
		u2.setSalario(new BigDecimal(1550.78));// forma um
		u2.setSalario(BigDecimal.valueOf(1550.78));// forma dois		
		
		// Colocando os objetos no em estado PERSISTENT
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		
		// Obtendo um "Gerenciador de entidades" do JPA - HIBERNATE 3.6.10
		EntityManager em = emf.createEntityManager();
		
		// controla a transação do usuario (commit,rollback)
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		em.persist(u2); //INSERT INTO
		em.persist(u1); //INSERT INTO
		
		tx.commit();
		
		em.close();
		emf.close();
		
		System.out.println("Objetos persistidos no Banco Dados.");
	}

}
