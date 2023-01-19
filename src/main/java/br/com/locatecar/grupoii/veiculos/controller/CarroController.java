package br.com.locatecar.grupoii.veiculos.controller;

import java.util.List;

import br.com.locatecar.grupoii.veiculos.model.Carro;
import br.com.locatecar.grupoii.veiculos.service.CarroService;
import br.com.locatecar.grupoii.veiculos.util.VeiculosController;

public class CarroController<E> implements VeiculosController<Carro> {


	@Override
	public void salvarVeiculo(Carro novoCarro) {
		CarroService carroService = new CarroService();
		List<Carro> listaDeCarros = carroService.listar();
		listaDeCarros.add(novoCarro);
		carroService.adicionar(listaDeCarros);
		
	}

	@Override
	public void alterarVeiculo(Carro veiculoAlterado, String placa) {
		CarroService carroService = new CarroService();
		carroService.editar(placa, veiculoAlterado);
		
	}


}
