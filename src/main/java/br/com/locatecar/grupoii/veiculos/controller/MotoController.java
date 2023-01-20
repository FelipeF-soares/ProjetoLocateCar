package br.com.locatecar.grupoii.veiculos.controller;

import java.util.List;

import br.com.locatecar.grupoii.veiculos.model.Moto;
import br.com.locatecar.grupoii.veiculos.service.MotoService;
import br.com.locatecar.grupoii.veiculos.util.VeiculosController;

public class MotoController implements VeiculosController<Moto> {
	
	
	@Override
	public void salvarVeiculo(Moto novoVeiculo) {
		MotoService motoService = new MotoService();
		List<Moto> listaDeMotos = motoService.listar();
		listaDeMotos.add(novoVeiculo);
		motoService.adicionar(listaDeMotos);
		
	}

	@Override
	public void alterarVeiculo(Moto veiculoAlterado, String placa) {
		MotoService motoService = new MotoService();
		motoService.editar(placa, veiculoAlterado);
	}

}
