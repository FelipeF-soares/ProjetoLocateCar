package br.com.locatecar.grupoii.agencia.dto;

import java.util.List;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.util.Endereco;
import br.com.locatecar.grupoii.util.SiglasEstados;
import br.com.locatecar.grupoii.veiculos.model.Caminhao;
import br.com.locatecar.grupoii.veiculos.model.Carro;
import br.com.locatecar.grupoii.veiculos.model.Moto;

public class AgenciaDto {
	
	private String nomeAgencia;
	private Endereco endereco;
	private String logradouro;
	private String numero;
	private SiglasEstados siglasEstados;
	private List<Carro> listaDeCarros;
	private List<Moto> listaDeMotos;
	private List<Caminhao> listaCaminhao;
	
	
	public Agencia adicionar() {
		Agencia agencia = new Agencia();
		Endereco enderecoDto = new Endereco();
		agencia.setId();
		agencia.setNomeAgencia(nomeAgencia);
		enderecoDto.setLogradouro(logradouro);
		enderecoDto.setNumero(numero);
		enderecoDto.setSiglasEstados(siglasEstados);
		agencia.setEndereco(enderecoDto);
		return agencia;
	}
	
	public Agencia editar() {
		Agencia agencia = new Agencia();
		Endereco enderecoDto = new Endereco();
		agencia.setNomeAgencia(nomeAgencia);
		enderecoDto.setLogradouro(logradouro);
		enderecoDto.setNumero(numero);
		enderecoDto.setSiglasEstados(siglasEstados);
		agencia.setEndereco(enderecoDto);
		return agencia;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public SiglasEstados getSiglasEstados() {
		return siglasEstados;
	}

	public void setSiglasEstados(SiglasEstados siglasEstados) {
		this.siglasEstados = siglasEstados;
	}
	
}
