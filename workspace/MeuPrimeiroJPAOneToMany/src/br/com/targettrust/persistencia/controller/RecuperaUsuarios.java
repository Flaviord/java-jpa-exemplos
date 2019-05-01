package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Endereco;
import br.com.targettrust.persistencia.model.Telefone;
import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RecuperaUsuarios {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap

		Endereco ender = null;
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
	
		String idConsulta = JOptionPane.showInputDialog("Informe o Id de ENDERENCO para Consulta os telefones.");
		Long idConsultaConv = Long.parseLong(idConsulta); 
		
		ender = em.find(Endereco.class, idConsultaConv);	//Find sempre procura pela pk		
		
		

		
		System.out.println(ender.getLogradouro());
		System.out.println(ender.getBairro());
		System.out.println(ender.getCidade());
		System.out.println(ender.getUf());
		System.out.println(ender.getNumeral());
		
		Usuario dono = ender.getUsuario();
		
		System.out.println(dono.getNome());
		System.out.println(dono.getRg());
		System.out.println(dono.getCpf());
		System.out.println(dono.getSalario());
		System.out.println(dono.getPerfil());
		System.out.println(dono.getSexo());
		
		for (Telefone telco : dono.getTelefones() ){
			System.out.println(telco.getNumeral());
			System.out.println(telco.getRamal());
			System.out.println(telco.getDdd());
			System.out.println(telco.getUsuario());
			System.out.println("-----------------------");
		}
		
		//Quando utilizar LAZY fechar as conexões somene pos os métodos.
		em.close();
		emf.close();
	}

}
