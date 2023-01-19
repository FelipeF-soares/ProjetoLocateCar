package br.com.locatecar.grupoii.veiculos.model;

public class Caminhao extends Veiculos {
	
	public Caminhao() {
		super.setTipoVeiculo(TipoVeiculo.CAMINHAO);
		super.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
	}
}
