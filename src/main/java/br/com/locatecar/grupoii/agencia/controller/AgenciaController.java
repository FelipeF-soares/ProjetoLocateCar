package br.com.locatecar.grupoii.agencia.controller;

import java.util.List;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.agencia.service.AgenciaService;

public class AgenciaController {
	AgenciaService agenciaService = new AgenciaService();
	
	public void atualizaAgencia(){
		List<Agencia> listaDeAgencia = agenciaService.listar();
		for(int i = 0; i < listaDeAgencia.size(); i++) {
			Agencia agenciaAtualizada = agenciaService.adicionaListaVeiculo(listaDeAgencia.get(i));
			listaDeAgencia.set(i, agenciaAtualizada);
			agenciaService.adicionar(listaDeAgencia);
		}
		
	}

	public void salvar(Agencia agencia) {
		List<Agencia> listaDeAgencia = agenciaService.listar();
		agencia = agenciaService.adicionaListaVeiculo(agencia);
		listaDeAgencia.add(agencia);
		agenciaService.adicionar(listaDeAgencia);
	}
	
	public void editarAgencia(Integer id, Agencia agencia) {
		List<Agencia> listaDeAgencia = agenciaService.listar();
		agencia.setId(id);
		listaDeAgencia.set(id, agencia);
		agenciaService.adicionar(listaDeAgencia);
	}
}
