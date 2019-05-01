package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Endereco;
import br.com.targettrust.persistencia.model.Telefone;
import br.com.targettrust.persistencia.model.Turma;
import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RecuperaTurma {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		 // Criando objetos TRANSIENT na heap
		Turma turma = null ;
		
		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM HEAP).
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("targetPU");
		EntityManager em = emf.createEntityManager();
	
		String idConsulta = JOptionPane.showInputDialog("Informe o Id TURMA de consulta e Enderenco.");
		Long idConsultaConv = Long.parseLong(idConsulta); 
		turma = em.find(Turma.class, idConsultaConv);	//Find sempre procura pela pk		
		
		

		// Imprime dados da Turma
		System.out.println(turma.getDescricao());
		System.out.println(turma.getSala());
		

			for (Usuario user: turma.getUsuarios()){
				System.out.println(user.toString());

				
				System.out.println(user.getNome());
				System.out.println(user.getCpf());
				System.out.println(user.getRg());
				System.out.println(user.getDataNasc());
				System.out.println(user.getSexo());		
				System.out.println(user.getPerfil());
				System.out.println("--------------------------");
				
				for (Telefone telco : user.getTelefones() ){
					System.out.println(telco.getNumeral());
					System.out.println(telco.getRamal());
					System.out.println(telco.getDdd());
					System.out.println(telco.getUsuario());
					System.out.println("-----------------------");
				}
				for (Endereco endereco : user.getEnderecos()) {
					System.out.println("\t"+endereco.getLogradouro());
					System.out.println("\t"+endereco.getNumeral());
					System.out.println("\t"+endereco.getBairro());
					System.out.println("\t"+endereco.getCep());
					System.out.println("\t"+endereco.getCidade());
					System.out.println("\t"+endereco.getUf());
					System.out.println("------------------------------");					
				}

			
		}
		//Quando utilizar LAZY fechar as conexões somene pos os métodos.
		em.close();
		emf.close();
	}       

}
