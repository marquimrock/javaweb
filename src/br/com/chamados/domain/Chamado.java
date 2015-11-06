package br.com.chamados.domain;

public class Chamado {

	private Long id;
	private String data_abertura;
	private String solicitante;
	private String motivo;
	private String tec_abertura;
	private String erro_econtrado;
	private String solucao;
	private String tec_encerramento;
	private String data_encerramento;
	private String status;
	private Usuario usuario = new Usuario();
	private Cliente cliente = new Cliente();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(String data_abertura) {
		this.data_abertura = data_abertura;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getTec_abertura() {
		return tec_abertura;
	}

	public void setTec_abertura(String tec_abertura) {
		this.tec_abertura = tec_abertura;
	}

	public String getErro_econtrado() {
		return erro_econtrado;
	}

	public void setErro_econtrado(String erro_econtrado) {
		this.erro_econtrado = erro_econtrado;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public String getTec_encerramento() {
		return tec_encerramento;
	}

	public void setTec_encerramento(String tec_encerramento) {
		this.tec_encerramento = tec_encerramento;
	}

	public String getData_encerramento() {
		return data_encerramento;
	}

	public void setData_encerramento(String data_encerramento) {
		this.data_encerramento = data_encerramento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		String retorno = motivo;
		return retorno;
	}

}
