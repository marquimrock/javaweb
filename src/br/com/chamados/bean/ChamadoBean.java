package br.com.chamados.bean;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.chamados.dao.ChamadoDAO;
import br.com.chamados.dao.ClienteDAO;
import br.com.chamados.dao.UsuarioDAO;
import br.com.chamados.domain.Chamado;
import br.com.chamados.domain.Cliente;
import br.com.chamados.domain.Usuario;
import br.com.chamados.util.JSFUtil;

@ManagedBean(name = "MBChamado")
@ViewScoped
public class ChamadoBean {

	private Chamado chamado;
	private ArrayList<Chamado> chamados;
	private ArrayList<Chamado> chamadosFiltrados;
	private ArrayList<Cliente> comboClientes;
	private ArrayList<Usuario> comboUsuarios;

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public ArrayList<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(ArrayList<Chamado> chamados) {
		this.chamados = chamados;
	}

	public ArrayList<Chamado> getChamadosFiltrados() {
		return chamadosFiltrados;
	}

	public void setChamadosFiltrados(ArrayList<Chamado> chamadosFiltrados) {
		this.chamadosFiltrados = chamadosFiltrados;
	}

	public ArrayList<Cliente> getComboClientes() {
		return comboClientes;
	}

	public void setComboClientes(ArrayList<Cliente> comboClientes) {
		this.comboClientes = comboClientes;
	}

	public ArrayList<Usuario> getComboUsuarios() {
		return comboUsuarios;
	}

	public void setComboUsuarios(ArrayList<Usuario> comboUsuarios) {
		this.comboUsuarios = comboUsuarios;
	}

	public void carregarListagem() {
		try {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			chamados = chamadoDAO.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());

		}
	}

	public void carregarListagemEncerrados() {
		try {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			chamados = chamadoDAO.listarEncerrados();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());

		}
	}

	public void prepararNovo() {
		try {
			chamado = new Chamado();

			ClienteDAO clienteDAO = new ClienteDAO();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			comboClientes = clienteDAO.listar();
			comboUsuarios = usuarioDAO.listar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	public void novo() {	
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String date = new SimpleDateFormat("dd/MM/yyyy" + " H:mm")
					.format(timestamp.getTime());

			chamado.setData_abertura(date);
			
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			
			chamadoDAO.salvar(chamado);

			chamados = chamadoDAO.listar();

			JSFUtil.adicionarMensagemSucesso("Chamado Salvo com Sucesso!!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void excluir() {
		try {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			chamadoDAO.excluir(chamado);
			chamados = chamadoDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Chamado Excluido com Sucesso!!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararEditar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			comboClientes = clienteDAO.listar();
			comboUsuarios = usuarioDAO.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void editar() {
		try {
			ChamadoDAO chamadoDAO = new ChamadoDAO();

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String date = new SimpleDateFormat("dd/MM/yyyy" + " H:mm")
					.format(timestamp.getTime());

			chamado.setData_encerramento(date);

			chamadoDAO.editar(chamado);
			chamados = chamadoDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Chamado Encerrado com sucesso!!!");
		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
