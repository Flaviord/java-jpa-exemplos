package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.targettrust.persistencia.model.Endereco;
import br.com.targettrust.persistencia.model.Perfil;
import br.com.targettrust.persistencia.model.Sexo;
import br.com.targettrust.persistencia.model.Telefone;
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

		Endereco u1end1,u1end2,u1end3;
		
		Telefone u1t1,u1t2	,u2t2,u2t1;
		
		Usuario u1 = new Usuario();
		
		u1end1 = new Endereco();
		u1end1.setLogradouro("Rua Sao Francisco da California");
		u1end1.setNumeral(231);
		u1end1.setBairro("Higienopolis");
		u1end1.setCep("90590130");
		u1end1.setCidade("Porto Alegre");
		u1end1.setUf("RS");		
	
		u1end2 = new Endereco();
		u1end2.setLogradouro("Rua Sao Francisco da California 2");
		u1end2.setNumeral(232);
		u1end2.setBairro("Higie2");
		u1end2.setCep("90590132");
		u1end2.setCidade("Porto Alegre 2");
		u1end2.setUf("RS");
		
		u1end3 = new Endereco();
		u1end3.setLogradouro("Rua Sao Francisco da California 3");
		u1end3.setNumeral(233);
		u1end3.setBairro("Higie3");
		u1end3.setCep("90590133");
		u1end3.setCidade("Porto Alegre 3");
		u1end3.setUf("RS");		
		
		 u1t1	  = new Telefone();
			u1t1.setDdd(51);
			u1t1.setNumeral("99998888");
			u1t1.setRamal(null);

		u1t2	  = new Telefone();
			u1t2.setDdd(51);
			u1t2.setNumeral("99998888");
			u1t2.setRamal(null);
		
		u1.setNome("Flavio Rodrigues");
		u1.setCpf("999");
		u1.setRg("123");
		u1.setDataNasc(new GregorianCalendar(2013, 06, 18).getTime());
		u1.setSexo(Sexo.M);
		u1.setPerfil(Perfil.V);
		// u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal		
		u1.setSalario(new BigDecimal(2550.78));// forma um
		u1.setSalario(BigDecimal.valueOf(2550.78));// forma dois
		//u1.setEnderecos(Arrays.asList(u1end1,u1end2,u1end3));
		
		u1.addEndereco(u1end1);
		u1.addEndereco(u1end2);
		u1.addEndereco(u1end3);
		
//		u1end1.setUsuario(u1);
//		u1end2.setUsuario(u1);
//		u1end3.setUsuario(u1);
		
		u1.addTelefone(u1t1);
		u1.addTelefone(u1t2);
		

		Endereco u2end1,u2end2;			
		
		u2end1 = new Endereco();		
		u2end1.setLogradouro("Avenida Farrapos");
		u2end1.setNumeral(450);
		u2end1.setBairro("Floresta");
		u2end1.setCep("90080112");
		u2end1.setCidade("Brusque");
		u2end1.setUf("SC");

		u2end2 = new Endereco();		
		u2end2.setLogradouro("Avenida C. Colombo");
		u2end2.setNumeral(40);
		u2end2.setBairro("Umbu");
		u2end2.setCep("90080333");
		u2end2.setCidade("Cach");
		u2end2.setUf("SC");

		 u2t1	  = new Telefone();
			u2t1.setDdd(51);
			u2t1.setNumeral("77774444");
			u2t1.setRamal(null);

		u2t2	  = new Telefone();
			u2t2.setDdd(51);
			u2t2.setNumeral("88887777");
			u2t2.setRamal(null);
		
		Usuario u2 = new Usuario();

		u2.setNome("Fla Rigues");
		u2.setCpf("888");
		u2.setRg("103");
		u2.setDataNasc(new GregorianCalendar(2013, 06, 28).getTime());
		u2.setSexo(Sexo.F);
		u2.setPerfil(Perfil.E);
		// u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal

		u2.setSalario(new BigDecimal(1550.78));// forma um
		u2.setSalario(BigDecimal.valueOf(1550.78));// forma dois
		//u2.setEnderecos(u2end2);
		u2.addEndereco(u2end1);
		u2.addEndereco(u2end2);
		
		u2.addTelefone(u2t1);
		u2.addTelefone(u2t2);
		
				
		// Colocando os objetos no em estado PERSISTENT

		// Carregando o framework JPA - hibernate 3.6.10 - para a memória (JVM
		// HEAP).
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("targetPU");

		// Obtendo um "Gerenciador de entidades" do JPA - HIBERNATE 3.6.10
		EntityManager em = emf.createEntityManager();

		// controla a transação do usuario (commit,rollback)
		EntityTransaction tx = em.getTransaction();

		tx.begin();

			em.persist(u2); // INSERT INTO NAS TABELAS USUARIOS E ENDERENCOS
			em.persist(u1); // INSERT INTO NAS TABELAS USUARIOS E ENDERENCOS

		tx.commit();

		em.close();
		emf.close();

		System.out.println("Objetos persistidos no Banco Dados.");
	}

}
