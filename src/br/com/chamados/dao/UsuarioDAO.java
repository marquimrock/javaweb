package br.com.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.chamados.domain.Usuario;
import br.com.chamados.factory.ConexaoFactory;

public class UsuarioDAO {

	public void salvar(Usuario usuario) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO usuario ");
		sql.append("(descricao, senha) ");
		sql.append("VALUES(?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, usuario.getDescricao());
		comando.setString(2, usuario.getSenha());

		comando.executeUpdate();
	}

	public void excluir(Usuario usuario) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, usuario.getId());

		comando.executeUpdate();
	}

	public void editar(Usuario usuario) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE usuario ");
		sql.append("SET descricao = ?, senha = ? ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, usuario.getDescricao());
		comando.setString(2, usuario.getSenha());
		comando.setLong(3, usuario.getId());

		comando.executeUpdate();
	}

	public ArrayList<Usuario> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, descricao, senha ");
		sql.append("FROM usuario ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("id"));
			usuario.setDescricao(resultado.getString("descricao"));
			usuario.setSenha(resultado.getString("senha"));
			lista.add(usuario);
		}

		return lista;

	}

}
