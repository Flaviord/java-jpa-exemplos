package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RecuperaUsuariosPorEnderecoJPQL {

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

		String cidadeConsulta = JOptionPane.showInputDialog("Informe o CIDADE para consultar USUARIOS.");

		String jpqlStr = "select object(u) from Usuario u "
						+ "where UPPER(u.endereco.cidade)  LIKE UPPER(:cidade)";//não pode colocar diretamente [where u.cidade]
		TypedQuery<Usuario> query = em.createQuery(jpqlStr, Usuario.class);
		query.setParameter("cidade", "%"+cidadeConsulta+"%");

		usuarios = query.getResultList();

		for (Usuario usuario : usuarios) {
			
			System.out.println(usuario.getNome());
			System.out.println(usuario.getRg());
			System.out.println(usuario.getDataNasc());
			System.out.println(usuario.getEndereco());
			System.out.println("--------------------------");
		}

		em.close();
		emf.close();

	}

}
