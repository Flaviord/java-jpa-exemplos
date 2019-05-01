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

		Endereco end1 = new Endereco();
		Telefone t1	  = new Telefone();
		
		Usuario u1 = new Usuario();

		end1.setLogradouro("Rua Sao Francisco da California");
		end1.setNumeral(23);
		end1.setBairro("Higienopolis");
		end1.setCep("90590130");
		end1.setCidade("Porto Alegre");
		end1.setUf("RS");		
	
		t1.setDdd(51);
		t1.setNumeral("99998888");
		t1.setRamal(null);
		
		u1.setNome("Flavio Rodrigues");
		u1.setCpf("999");
		u1.setRg("123");
		u1.setDataNasc(new GregorianCalendar(2013, 06, 18).getTime());
		u1.setSexo(Sexo.M);
		u1.setPerfil(Perfil.V);
		// u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal		
		u1.setSalario(new BigDecimal(2550.78));// forma um
		u1.setSalario(BigDecimal.valueOf(2550.78));// forma dois
		u1.setEndereco(end1);//Estabelecendo o relacionamento ASSOCIATIVO BIDIRECIONAL
		u1.setTelefone(t1);

		Endereco end2 = new Endereco();
		Telefone t2 = new Telefone();
		
		end2.setLogradouro("Avenida Cristóvão Colombo");
		end2.setNumeral(450);
		end2.setBairro("Floresta");
		end2.setCep("90080110");
		end2.setCidade("Canoas");
		end2.setUf("RS");

		t2.setDdd(11);
		t2.setNumeral("88889999");
		t2.setRamal("5150");
		
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
		u2.setEndereco(end2);
		u2.setTelefone(t2);
				
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
