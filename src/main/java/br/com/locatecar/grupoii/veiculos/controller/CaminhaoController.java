package br.com.locatecar.grupoii.veiculos.controller;

import java.util.List;

import br.com.locatecar.grupoii.veiculos.model.Caminhao;
import br.com.locatecar.grupoii.veiculos.service.CaminhaoService;
import br.com.locatecar.grupoii.veiculos.util.VeiculosController;

public class CaminhaoController implements VeiculosController<Caminhao> {
	CaminhaoService caminhaoService = new CaminhaoService();
	
	@Override
	public void salvarVeiculo(Caminhao novoVeiculo) {
		List<Caminhao> listaDeCaminhoes = caminhaoService.listar();
		listaDeCaminhoes.add(novoVeiculo);
		caminhaoService.adicionar(listaDeCaminhoes);
		
	}

	@Override
	public void alterarVeiculo(Caminhao veiculoAlterado, String placa) {
		caminhaoService.editar(placa, veiculoAlterado);
	}

}
