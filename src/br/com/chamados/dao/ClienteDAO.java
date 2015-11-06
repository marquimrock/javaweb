package br.com.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.chamados.domain.Cliente;
import br.com.chamados.factory.ConexaoFactory;

public class ClienteDAO {

	public void salvar(Cliente cliente) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cliente ");
		sql.append("(cnpj, ie, razao_social, fantasia, cidade, telefone, email, responsavel) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, cliente.getCnpj());
		comando.setString(2, cliente.getIe());
		comando.setString(3, cliente.getRazao_social());
		comando.setString(4, cliente.getFantasia());
		comando.setString(5, cliente.getCidade());
		comando.setString(6, cliente.getTelefone());
		comando.setString(7, cliente.getEmail());
		comando.setString(8, cliente.getResponsavel());

		comando.executeUpdate();

	}
	
	public ArrayList<Cliente> listar() throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, cnpj, ie, razao_social, fantasia, cidade, telefone, email, responsavel ");
		sql.append("FROM cliente  ");
		sql.append("ORDER BY razao_social ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		while (resultado.next()) {

			Cliente cliente = new Cliente();
			cliente.setId(resultado.getLong("id"));
			cliente.setCnpj(resultado.getString("cnpj"));
			cliente.setIe(resultado.getString("ie"));
			cliente.setRazao_social(resultado.getString("razao_social"));
			cliente.setFantasia(resultado.getString("fantasia"));
			cliente.setCidade(resultado.getString("cidade"));
			cliente.setTelefone(resultado.getString("telefone"));
			cliente.setEmail(resultado.getString("email"));
			cliente.setResponsavel(resultado.getString("responsavel"));
			
			lista.add(cliente);			
			
		}
		return lista;
	}

	public void editar(Cliente cliente) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cliente ");
		sql.append("SET cnpj = ?, ");
		sql.append("ie = ?, ");
		sql.append("razao_social = ?, ");
		sql.append("fantasia = ?, ");
		sql.append("cidade = ?, ");
		sql.append("telefone = ?, ");
		sql.append("email = ?, ");
		sql.append("responsavel = ? ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, cliente.getCnpj());
		comando.setString(2, cliente.getIe());
		comando.setString(3, cliente.getRazao_social());
		comando.setString(4, cliente.getFantasia());
		comando.setString(5, cliente.getCidade());	
		comando.setString(6, cliente.getTelefone());
		comando.setString(7, cliente.getEmail());
		comando.setString(8, cliente.getResponsavel());
		comando.setLong(9, cliente.getId());
		
		comando.executeUpdate();
	}
}
