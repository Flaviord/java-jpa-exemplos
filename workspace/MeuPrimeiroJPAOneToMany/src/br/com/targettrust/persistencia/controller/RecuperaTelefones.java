package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Endereco;
import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RecuperaTelefones {

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
	
		String idConsulta = JOptionPane.showInputDialog("Informe o Id de consulta.");
		Long idConsultaConv = Long.parseLong(idConsulta); 
		user = em.find(Usuario.class, idConsultaConv);	//Find sempre procura pela pk		
		
		

		
		System.out.println(user.getNome());
		System.out.println(user.getCpf());
		System.out.println(user.getRg());
		System.out.println(user.getDataNasc());
		System.out.println(user.getSexo());		
		System.out.println(user.getPerfil());
		
		for (Endereco endereco : user.getEnderecos()) {
			
			System.out.println("\t"+endereco.getLogradouro());
			System.out.println("\t"+endereco.getNumeral());
			System.out.println("\t"+endereco.getBairro());
			System.out.println("\t"+endereco.getCep());
			System.out.println("\t"+endereco.getCidade());
			System.out.println("\t"+endereco.getUf());
			System.out.println("------------------------------");
			
		}
		//Quando utilizar LAZY fechar as conexões somene pos os métodos.
		em.close();
		emf.close();
	}

}
