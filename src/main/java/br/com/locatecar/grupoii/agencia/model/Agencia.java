package br.com.locatecar.grupoii.agencia.model;

import java.util.List;

import br.com.locatecar.grupoii.agencia.service.AgenciaService;
import br.com.locatecar.grupoii.util.Endereco;
import br.com.locatecar.grupoii.veiculos.model.Caminhao;
import br.com.locatecar.grupoii.veiculos.model.Carro;
import br.com.locatecar.grupoii.veiculos.model.Moto;

public class Agencia {
	
	private Integer id;
	private String nomeAgencia;
	private Endereco endereco;
	private List<Carro> listaDeCarros;
	private List<Moto> listaDeMotos;
	private List<Caminhao> listaCaminhao;
	
	public Agencia() {
		
	}
	public void setId () {
		AgenciaService agenciaService = new AgenciaService();
		List<Agencia> listar = agenciaService.listar();
		this.id = listar.size();
	}
	
	public Integer getId() {
		return id;
	}
	public String getNomeAgencia() {
		return nomeAgencia;
	}
	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Carro> getListaDeCarros() {
		return listaDeCarros;
	}
	public void setListaDeCarros(List<Carro> listaDeCarros) {
		this.listaDeCarros = listaDeCarros;
	}
	public List<Moto> getListaDeMotos() {
		return listaDeMotos;
	}
	public void setListaDeMotos(List<Moto> listaDeMotos) {
		this.listaDeMotos = listaDeMotos;
	}
	public List<Caminhao> getListaCaminhao() {
		return listaCaminhao;
	}
	public void setListaCaminhao(List<Caminhao> listaCaminhao) {
		this.listaCaminhao = listaCaminhao;
	}
	
	@Override
	public String toString() {
		return "\nid: "+this.getId()+" Nome Da Agencia: "+this.getNomeAgencia()+"\n".trim();
	}
	
}
