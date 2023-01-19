package br.com.locatecar.test.agencia;

import java.util.List;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.agencia.service.AgenciaService;
import br.com.locatecar.grupoii.util.Endereco;
import br.com.locatecar.grupoii.util.SiglasEstados;

public class TesteCriarAgencia {
	
	public static void main(String[] args) {
		AgenciaService agenciaService = new AgenciaService();
		Agencia agencia1 = new Agencia();
		Agencia agencia2 = new Agencia();
		Endereco endereco1 = new Endereco();
		Endereco endereco2 = new Endereco();
		endereco1.setLogradouro("Avenida Amador Bueno da Veiga");
		endereco1.setNumero("2774");
		endereco1.setSiglasEstados(SiglasEstados.SP);
		
		endereco2.setLogradouro("Avenida Brasil");
		endereco2.setNumero("1001");
		endereco2.setSiglasEstados(SiglasEstados.RJ);
		
		agencia1.setId();
		agencia1.setEndereco(endereco1);
		agencia1.setNomeAgencia("Unidade São Paulo");
	
		List<Agencia> agencias = agenciaService.listar();
		agencias.add(agencia1);
		agenciaService.adicionar(agencias);
		agencia2.setId();
		agencia2.setEndereco(endereco2);
		agencia2.setNomeAgencia("Unidade Rio de Janeiro");
		agencias.add(agencia2);
		agenciaService.adicionar(agencias);
	}

}
