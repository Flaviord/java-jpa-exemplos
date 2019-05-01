package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class GravaUsusarios {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap
		Usuario u1 = new Usuario();
		
		u1.setNome("Flavio Rodrigues");
		u1.setCpf("99988866677");
		u1.setRg("1232143");
		u1.setDataNasc(new GregorianCalendar(2013,06,18).getTime());
		//u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal
		
		u1.setSalario(new BigDecimal(2550.78));// forma um
		u1.setSalario(BigDecimal.valueOf(2550.78));// forma dois

		Usuario u2 = new Usuario();
		
		u2.setNome("Fla Rigues");
		u2.setCpf("00888888877");
		u2.setRg("100000003");
		u2.setDataNasc(new GregorianCalendar(2013,06,28).getTime());
		//u1.setSalario(2550.78);// erro pois esse literal não é BigDecimal
		
		u2.setSalario(new BigDecimal(1550.78));// forma um
		u2.setSalario(BigDecimal.valueOf(1550.78));// forma dois
		
		
		// Colocando os objetos no em estado PERSISTENT
		//carrega o driver
		DriverManager.registerDriver(new org.postgresql.Driver());
		
		String jdbcURL = "jdbc:postgresql://localhost:5432/CadastroJPA";
		String user	="postgres";
		String password = "postgres";
		Connection conn = DriverManager.getConnection(jdbcURL, user, password);
		
		//Envio dos comandos e retornando resultado.
		String query = "insert into usuarios (nome,rg,cpf,datanasc,salario) "+
						"values (?,?,?,?,?)";
		PreparedStatement pStmt = conn.prepareStatement(query);		
		
		//efetuando MOR via JDBC NAKED
		pStmt.setString(1, u1.getNome());
		pStmt.setString(2, u1.getRg());
		pStmt.setString(3, u1.getCpf());
		pStmt.setTimestamp(4,new java.sql.Timestamp( u1.getDataNasc().getTime()));
		pStmt.setBigDecimal(5, u1.getSalario());
		pStmt.executeUpdate();//utilizado para INSERT,UPDATE,DELETE

		//efetuando MOR via JDBC NAKED
		pStmt.setString(1, u2.getNome());
		pStmt.setString(2, u2.getRg());
		pStmt.setString(3, u2.getCpf());
		pStmt.setTimestamp(4,new java.sql.Timestamp( u2.getDataNasc().getTime()));
		pStmt.setBigDecimal(5, u2.getSalario());
		pStmt.executeUpdate();
		
		//fechas as conexões 
		pStmt.close();
		conn.close();
		
		System.out.println("Objetos persistidos no Banco Dados.");
	}

}
