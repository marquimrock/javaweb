package br.com.chamados.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.chamados.dao.ChamadoDAO;
import br.com.chamados.dao.ClienteDAO;
import br.com.chamados.dao.UsuarioDAO;
import br.com.chamados.domain.Chamado;
import br.com.chamados.domain.Cliente;
import br.com.chamados.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setDescricao("teste2");
		usuario.setSenha("kj");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<Usuario> lista = usuarioDAO.listar();

		for (Usuario u : lista) {
			System.out.println("Descricao : " + u.getDescricao());

			System.out.println("-----------");
		}
	}

	@Test
	@Ignore
	public void listarCliente() throws SQLException {
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<Cliente> lista = clienteDAO.listar();

		for (Cliente c : lista) {
			System.out.println("Cliente: " + c.getRazao_social());
			System.out.println("==========================");
		}

	}

	@Test
	@Ignore
	public void salvarCliente() throws SQLException {

		Cliente cliente = new Cliente();
		cliente.setCnpj("99009900990090");
		cliente.setIe("1110022");
		cliente.setRazao_social("cliente teste");
		cliente.setFantasia("teste");
		cliente.setCidade("Luziania");
		cliente.setTelefone("99887788");
		cliente.setEmail("teste@mail.com");
		cliente.setResponsavel("marcos");

		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);

	}

	@Test
	@Ignore
	public void salvarChamado() throws SQLException {

		Chamado chamado = new Chamado();
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		chamado.setCliente(cliente);
		chamado.setUsuario(usuario);
		chamado.setData_abertura("01-01-2015");
		chamado.setSolicitante("solicitenate");
		chamado.setMotivo("motivo teste");
		chamado.setTec_abertura("marcos");
		chamado.setErro_econtrado("nao liga");
		chamado.setSolucao("troaca de pim pad");
		chamado.setTec_encerramento("marquim");
		chamado.setData_encerramento("02-01-2015");
		chamado.setStatus("1");
		
		ChamadoDAO dao = new ChamadoDAO();
		dao.salvar(chamado);
		
	}
	
	@Test
	@Ignore
	public void listarChamado() throws SQLException {
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		ArrayList<Chamado> lista = chamadoDAO.listar();

		int i = 0;
		for (Chamado c : lista) {
			System.out.println("Motivo: " + c.getMotivo());
			System.out.println("Usuario: " +c.getUsuario().getDescricao());
			System.out.println("Razao: " +c.getCliente().getRazao_social());
			System.out.println("==========================");
			i++;
		}
		System.out.println(i +" 0000000000");
	}

	@Test
	@Ignore
	public void excluir() throws SQLException{
		
		Chamado chamado = new Chamado();
		chamado.setId(1L);

		
		ChamadoDAO chamadoDAO = new ChamadoDAO();
		chamadoDAO.excluir(chamado);
		
	}
	
	@Test
	public void testaCalendar(){
	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());  
		String date = new SimpleDateFormat("dd/MM/yyyy" + " H:mm").format(timestamp.getTime());  
		System.out.println(date);

	
	}
	
}
