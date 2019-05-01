package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Endereco;

// Controller (Java SE)

public class RecuperaEnderecos {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap
		Endereco ender = null ;
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
	
		String idConsulta = JOptionPane.showInputDialog("Informe o Id de consulta.");
		Long idConsultaConv = Long.parseLong(idConsulta); 
		ender = em.find(Endereco.class, idConsultaConv);	//Find sempre procura pela pk		
		
		
		em.close();
		emf.close();
		
		System.out.println(ender.getLogradouro());
		System.out.println(ender.getBairro());
		System.out.println(ender.getNumeral());
		System.out.println(ender.getCep());
		System.out.println(ender.getCidade());
		System.out.println(ender.getUsuario().getNome());
		System.out.println(ender.getUsuario().getRg());
		System.out.println(ender.getUsuario().getCpf());
		
	}

}
