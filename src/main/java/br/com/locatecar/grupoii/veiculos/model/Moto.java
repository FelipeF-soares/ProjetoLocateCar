package br.com.locatecar.grupoii.veiculos.model;

public class Moto extends Veiculos{
	
	public Moto() {
		super.setTipoVeiculo(TipoVeiculo.MOTO);
		super.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
	}
}
