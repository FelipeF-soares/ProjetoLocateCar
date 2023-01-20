package br.com.locatecar.grupoii.veiculos.dto;

import br.com.locatecar.grupoii.agencia.model.Agencia;

public abstract class VeiculoDto<T> {
	private String placa;
	private String marca;
	private String modelo;
	private Integer anoDeFabricao;
	private Agencia agencia;
	
	public T adicionar() {
		return null;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getAnoDeFabricao() {
		return anoDeFabricao;
	}
	public void setAnoDeFabricao(Integer anoDeFabricao) {
		this.anoDeFabricao = anoDeFabricao;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
}
