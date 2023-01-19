package br.com.locatecar.grupoii.util;

public class Telefones {
	private String ddd;
	private String numeroTelefone;
	private TipoTelefone tipoTelefone;
	private TipoLinha tipoLinha;
	private String nomeDoContato;
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}
	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	public TipoLinha getTipoLinha() {
		return tipoLinha;
	}
	public void setTipoLinha(TipoLinha tipoLinha) {
		this.tipoLinha = tipoLinha;
	}
	public String getNomeDoContato() {
		return nomeDoContato;
	}
	public void setNomeDoContato(String nomeDoContato) {
		this.nomeDoContato = nomeDoContato;
	}
	
	
}
