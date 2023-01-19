package br.com.locatecar.grupoii.veiculos.util;

public interface VeiculosController<E>{

	void salvarVeiculo(E novoVeiculo);
	
	void alterarVeiculo(E veiculoAlterado, String placa);


}
