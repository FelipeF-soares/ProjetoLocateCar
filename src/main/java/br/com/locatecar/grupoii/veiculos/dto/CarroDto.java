package br.com.locatecar.grupoii.veiculos.dto;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.veiculos.model.Carro;
import br.com.locatecar.grupoii.veiculos.util.VeiculoDto;

public class CarroDto implements VeiculoDto<Carro> {
	
	private String placa;
	private String marca;
	private String modelo;
	private Integer anoDeFabricao;
	private Agencia agencia;
	
	public Carro adicionar() {
		Carro carro = new Carro();
		carro.setPlaca(placa);
		carro.setMarca(marca);
		carro.setModelo(modelo);
		carro.setAnoDeFabricao(anoDeFabricao);
		carro.setIdAgencia(agencia);
		
		return carro;
	}
	
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa){
			this.placa = placa.toUpperCase().trim();
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca.toUpperCase().trim();
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo.toUpperCase().trim();
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
