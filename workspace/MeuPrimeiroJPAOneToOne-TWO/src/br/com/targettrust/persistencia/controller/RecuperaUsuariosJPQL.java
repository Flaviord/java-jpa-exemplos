package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RecuperaUsuariosJPQL {

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
	
		String cpfConsulta = JOptionPane.showInputDialog("Informe o CPF de consulta.");
		
		String jpqlStr = "select object(u) from Usuario u where u.cpf = :cpfInformado";
		TypedQuery<Usuario> query = em.createQuery(jpqlStr, Usuario.class);
		query.setParameter("cpfInformado", cpfConsulta);
		
		user = query.getSingleResult();
		
		
		System.out.println(user);
		System.out.println(user.getEndereco().toString());
		
		em.close();
		emf.close();
		
	}

}
