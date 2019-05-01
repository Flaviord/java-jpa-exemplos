package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class VerificaVersao {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
			
		// Colocando os objetos no em estado PERSISTENT
		//carrega o driver
		DriverManager.registerDriver(new org.postgresql.Driver());
		
		String jdbcURL = "jdbc:postgresql://localhost:5432/CadastroJPA";
		String user	="postgres";
		String password = "";
		Connection conn = DriverManager.getConnection(jdbcURL, user, password);
		
		//Envio dos comandos e retornando resultado.
		String query = "select version() as versao";
		PreparedStatement pStmt = conn.prepareStatement(query);		
		ResultSet rs = pStmt.executeQuery();//SOMENTE PARA SELECT
		
		rs.next();
		String versao = rs.getString("versao");
		
		//		
		System.out.println("Conteudo := "+versao);
		//fechas as conexões 
		rs.close();
		pStmt.close();
		conn.close();
	}

}
