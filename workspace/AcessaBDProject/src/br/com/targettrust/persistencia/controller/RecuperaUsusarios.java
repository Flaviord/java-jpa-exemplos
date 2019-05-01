package br.com.targettrust.persistencia.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.targettrust.persistencia.model.Usuario;

// Controller (Java SE)

public class RecuperaUsusarios {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
        // Criando objetos TRANSIENT na heap
		Usuario usuario = new Usuario();
		
		// Colocando os objetos no em estado PERSISTENT
		//carrega o driver
		DriverManager.registerDriver(new org.postgresql.Driver());
		
		String jdbcURL = "jdbc:postgresql://localhost:5432/CadastroJPA";
		String user	="postgres";
		String password = "postgres";
		Connection conn = DriverManager.getConnection(jdbcURL, user, password);
		
		//Envio dos comandos e retornando resultado.
		String query = "select * from usuarios where cpf= ?";
		PreparedStatement pStmt = conn.prepareStatement(query);		
		
		//efetuando MOR via JDBC NAKED
		String cpfConsulta = JOptionPane.showInputDialog("Informe o cpf para pesquisar.");
		
		pStmt.setString(1,cpfConsulta);		
		ResultSet rs = pStmt.executeQuery();
		
		rs.next();//posiciona o cursor para 1º linha do resultado
		
		usuario.setNome(rs.getString(1));		
		usuario.setRg(rs.getString(2));
		usuario.setCpf(rs.getString(3));
		usuario.setDataNasc(rs.getTimestamp(4));
		usuario.setSalario(rs.getBigDecimal(5));
		
		System.out.println(usuario);
		
		//fechas as conexões 
		pStmt.close();
		conn.close();
		
		System.out.println("Objetos persistidos no Banco Dados.");
	}

}
