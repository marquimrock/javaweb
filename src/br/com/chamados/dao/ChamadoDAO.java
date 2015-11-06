package br.com.chamados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.chamados.domain.Chamado;
import br.com.chamados.domain.Cliente;
import br.com.chamados.domain.Usuario;
import br.com.chamados.factory.ConexaoFactory;

public class ChamadoDAO {

	public void salvar(Chamado chamado) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO chamado ");
		sql.append("(id_usuario, id_cliente, data_abertura, solicitante, motivo, tec_abertura, "
				+ "erro_encontrado, solucao, tec_encerramento, data_encerramento, status) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		Connection conexao = ConexaoFactory.conectar();
		
		//Long idUsuario = Long.parseLong(chamado.getTec_abertura());

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, 1L);
		comando.setLong(2, chamado.getCliente().getId());
		comando.setString(3, chamado.getData_abertura());
		comando.setString(4, chamado.getSolicitante());
		comando.setString(5, chamado.getMotivo());
		comando.setString(6, chamado.getTec_abertura());
		comando.setString(7, chamado.getErro_econtrado());
		comando.setString(8, "");
		comando.setString(9, chamado.getTec_encerramento());
		comando.setString(10, chamado.getData_encerramento());
		comando.setString(11, "1");

		comando.executeUpdate();

	}

	public ArrayList<Chamado> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ch.id, ch.data_abertura, ch.solicitante, ch.motivo, ch.tec_abertura, "
				+ "ch.erro_encontrado, ch.solucao, ch.tec_encerramento, ch.data_encerramento, "
				+ "ch.status, ch.id_cliente, cl.id, cl.razao_social, u.id, u.descricao ");
		sql.append("FROM chamado ch ");
		sql.append("INNER JOIN cliente cl ");
		sql.append("INNER JOIN usuario u ");
		sql.append("ON cl.id = ch.id_cliente AND u.id = ch.id_usuario where ch.solucao = '' ");
		sql.append("ORDER BY ch.id ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Chamado> chamados = new ArrayList<Chamado>();

		while (resultado.next()) {

			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("u.id"));
			usuario.setDescricao(resultado.getString("u.descricao"));

			Cliente cliente = new Cliente();
			cliente.setId(resultado.getLong("cl.id"));
			cliente.setRazao_social(resultado.getString("cl.razao_social"));

			Chamado chamado = new Chamado();
			chamado.setId(resultado.getLong("ch.id"));
			chamado.setData_abertura(resultado.getString("ch.data_abertura"));
			chamado.setSolicitante(resultado.getString("ch.solicitante"));
			chamado.setMotivo(resultado.getString("ch.motivo"));
			chamado.setTec_abertura(resultado.getString("ch.tec_abertura"));
			chamado.setErro_econtrado(resultado.getString("ch.erro_encontrado"));
			chamado.setSolucao(resultado.getString("ch.solucao"));
			chamado.setTec_encerramento(resultado
					.getString("ch.tec_encerramento"));
			chamado.setData_encerramento(resultado
					.getString("ch.data_encerramento"));
			chamado.setStatus(resultado.getString("ch.status"));
			chamado.setUsuario(usuario);
			chamado.setCliente(cliente);

			chamados.add(chamado);
		}

		return chamados;

	}
	
	public ArrayList<Chamado> listarEncerrados() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ch.id, ch.data_abertura, ch.solicitante, ch.motivo, ch.tec_abertura, "
				+ "ch.erro_encontrado, ch.solucao, ch.tec_encerramento, ch.data_encerramento, "
				+ "ch.status, ch.id_cliente, cl.id, cl.razao_social, u.id, u.descricao ");
		sql.append("FROM chamado ch ");
		sql.append("INNER JOIN cliente cl ");
		sql.append("INNER JOIN usuario u ");
		sql.append("ON cl.id = ch.id_cliente AND u.id = ch.id_usuario where ch.solucao <> '' ");
		sql.append("ORDER BY ch.id ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Chamado> chamados = new ArrayList<Chamado>();

		while (resultado.next()) {

			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("u.id"));
			usuario.setDescricao(resultado.getString("u.descricao"));

			Cliente cliente = new Cliente();
			cliente.setId(resultado.getLong("cl.id"));
			cliente.setRazao_social(resultado.getString("cl.razao_social"));

			Chamado chamado = new Chamado();
			chamado.setId(resultado.getLong("ch.id"));
			chamado.setData_abertura(resultado.getString("ch.data_abertura"));
			chamado.setSolicitante(resultado.getString("ch.solicitante"));
			chamado.setMotivo(resultado.getString("ch.motivo"));
			chamado.setTec_abertura(resultado.getString("ch.tec_abertura"));
			chamado.setErro_econtrado(resultado.getString("ch.erro_encontrado"));
			chamado.setSolucao(resultado.getString("ch.solucao"));
			chamado.setTec_encerramento(resultado
					.getString("ch.tec_encerramento"));
			chamado.setData_encerramento(resultado
					.getString("ch.data_encerramento"));
			chamado.setStatus(resultado.getString("ch.status"));
			chamado.setUsuario(usuario);
			chamado.setCliente(cliente);

			chamados.add(chamado);
		}

		return chamados;

	}

	public void excluir(Chamado chamado) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM chamado ");
		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, chamado.getId());

		comando.executeUpdate();
	}

	public void editar(Chamado chamado) throws SQLException {

		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE chamado ");
		sql.append("SET  erro_encontrado = ?, solucao = ?, tec_encerramento = ?, data_encerramento = ?, status = ? ");

		sql.append("WHERE id = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, chamado.getErro_econtrado());
		comando.setString(2, chamado.getSolucao());
		comando.setString(3, chamado.getTec_encerramento());
		comando.setString(4, chamado.getData_encerramento());
		comando.setString(5, chamado.getStatus());
		comando.setLong(6, chamado.getId());

		comando.executeUpdate();

	}
}
