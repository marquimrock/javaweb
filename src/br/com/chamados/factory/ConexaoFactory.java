package br.com.chamados.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/hostcall";
	
	public static Connection conectar() throws SQLException {
		//se ocorrer erro de registro de driver, descomentar a linha abaixo
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conexao OK");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Nao Conectado!!!");
		}
		
	}
	
}
