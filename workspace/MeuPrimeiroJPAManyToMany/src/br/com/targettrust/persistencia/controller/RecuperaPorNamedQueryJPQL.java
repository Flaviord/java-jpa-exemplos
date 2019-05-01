package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Endereco;
import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RecuperaPorNamedQueryJPQL {

	/**
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

		// Criando objetos TRANSIENT na heap
		List<Usuario> usuarios = null;

		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM
		// HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();

		String cpfConsulta = JOptionPane.showInputDialog("Informe o CPF para consultar USUARIOS.");		
		
		TypedQuery<Usuario> query = em.createNamedQuery("UsuarioPorCpf", Usuario.class);
		
		query.setParameter("cpf", cpfConsulta);
		
		usuarios = query.getResultList();

		for (Usuario usuario : usuarios) {
			
			System.out.println(usuario.getNome());
			System.out.println(usuario.getRg());
			System.out.println(usuario.getDataNasc());
			System.out.println("--------------------------");
			
			for (Endereco endereco : usuario.getEnderecos()) {
				System.out.println("\t"+endereco.getBairro());
				System.out.println("\t"+endereco.getCidade());
				System.out.println("\t"+endereco.getUf());
				
			}
		}

		em.close();
		emf.close();

	}

}
