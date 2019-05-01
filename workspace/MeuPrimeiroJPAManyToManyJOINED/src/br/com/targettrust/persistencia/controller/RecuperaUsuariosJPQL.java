package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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

		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM
		// HEAP).
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		String cpfConsulta = JOptionPane
				.showInputDialog("Informe o cpf para consulta");

		// String jpqlStr = "select object(u) from Usuario u where u.cpf = ?";
		// //este SQL é executado no Objeto e não na entidade do DBMS
		String jpqlStr = "select object(u) from Usuario u where u.cpf = :cpf";
		TypedQuery<Usuario> query = em.createQuery(jpqlStr, Usuario.class);
		
		// query.setParameter(1,"888");
		// List<Usuario> usuarios = query.getResultList();//lista de informações

		query.setParameter("cpf", cpfConsulta);
		Usuario user = query.getSingleResult();// busca uma informação;
		System.out.println(user+"\n");

		user.setSalario(user.getSalario().multiply(BigDecimal.valueOf(2)));
		user = em.merge(user);
		System.out.println(user);

		// System.out.println("\n");
		// for (Usuario user : usuarios) {
		// System.out.println(user.getNome());
		// System.out.println(user.getRg());
		// System.out.println(user.getCpf());
		// System.out.println(user.getSalario());
		// System.out.println(user.getPerfil());
		// System.out.println(user.getSexo());
		// System.out.println("/*******************************/");
		//
		// for (Endereco ender : user.getEnderecos()) {
		//
		// System.out.println(ender.getLogradouro());
		// System.out.println(ender.getBairro());
		// System.out.println(ender.getCidade());
		// System.out.println(ender.getUf());
		// System.out.println(ender.getNumeral());
		// System.out.println("/*******************************/");
		//
		// }
		// }

		tx.commit();
		// Quando utilizar LAZY fechar as conexões somene pos os métodos.
		em.close();
		emf.close();
	}

}
