package br.com.locatecar.grupoii.veiculos.util;

import java.util.List;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.agencia.service.AgenciaService;
import br.com.locatecar.grupoii.erros.ErroDeValorInvalidoDaLista;
import br.com.locatecar.grupoii.erros.ErroPlacaNaoLocalizada;
import br.com.locatecar.grupoii.erros.ErroVeiculoJaCadastrado;
import br.com.locatecar.grupoii.erros.ValorNumericoInválido;
import br.com.locatecar.grupoii.erros.ValorVazioOuNulo;
import br.com.locatecar.grupoii.veiculos.service.CaminhaoService;
import br.com.locatecar.grupoii.veiculos.service.CarroService;
import br.com.locatecar.grupoii.veiculos.service.MotoService;

public class ValidaVeiculos {
	
	CarroService carroService = new CarroService();
	MotoService motoService = new MotoService();
	CaminhaoService caminhaoService = new CaminhaoService();
	
	public String placaValidada(String placa) throws ValorVazioOuNulo, ErroVeiculoJaCadastrado {
		placa = placa.toUpperCase().trim();
		if(placa.isBlank()||placa.isEmpty()) {
			throw new ValorVazioOuNulo();
		}else{
			if(carroService.localizar(placa).isEmpty() 
					&& motoService.localizar(placa).isEmpty() 
					&& caminhaoService.localizar(placa).isEmpty()) 
			{
				return placa;
			}else {
				throw new ErroVeiculoJaCadastrado();
			}
		}
	}
	
	public String marcaValidado(String marca) throws ValorVazioOuNulo {
		marca = marca.toUpperCase().trim();
		if(marca.isBlank()||marca.isEmpty()) {
			throw new ValorVazioOuNulo();
		}else {
			return marca;
		}
	}
	
	public String modeloValidado(String modelo) throws ValorVazioOuNulo {
		modelo = modelo.toUpperCase().trim();
		if(modelo.isBlank()||modelo.isEmpty()) {
			throw new ValorVazioOuNulo();
		}else {
			return modelo;
		}
	}
	
	public Integer anoValidado(String ano) throws ValorVazioOuNulo {
		ano = ano.toUpperCase().trim();
		if(ano.isBlank()||ano.isEmpty()) {
			throw new ValorVazioOuNulo();
		}else {
			Integer anoDeFabricacao;
			try {
				anoDeFabricacao = Integer.valueOf(ano);
				return anoDeFabricacao;
			} catch (NumberFormatException e) {
				throw new ValorNumericoInválido();
			}
		}
	}
	
	public Agencia idAgenciaValidado(String id) throws ValorVazioOuNulo,  ErroDeValorInvalidoDaLista {
		id = id.toUpperCase().trim();
		if(id.isBlank()||id.isEmpty()) {
			throw new ValorVazioOuNulo();
		}else {
			Integer idInteger;
			try {
				idInteger = Integer.valueOf(id);
				AgenciaService agenciaService = new AgenciaService();
				List<Agencia> listaDeAgencia = agenciaService.listar();
				if(idInteger>listaDeAgencia.size()||idInteger<0) {
					throw new ErroDeValorInvalidoDaLista();
				}
				for(int i = 0; i < listaDeAgencia.size();i++) {
					if(listaDeAgencia.get(i).getId().equals(idInteger)) {
						return listaDeAgencia.get(i);
					}
				}
				throw new ErroDeValorInvalidoDaLista();
			} catch (NumberFormatException e) {
				throw new ValorNumericoInválido();
			}
		}
	}
	
	public String placaLocalizado(String placa) throws ValorVazioOuNulo, ErroPlacaNaoLocalizada {
		placa = placa.toUpperCase().trim();
		if(placa.isBlank()||placa.isEmpty()) {
			throw new ValorVazioOuNulo();
		}else{
			
			if(!carroService.localizar(placa).isEmpty() 
					|| !motoService.localizar(placa).isEmpty() 
					|| !caminhaoService.localizar(placa).isEmpty()) 
			{
				return placa;
			}else {
				throw new ErroPlacaNaoLocalizada();
			}
		}
	}
	
	public String localizaParteVeiculo(String parteVeiculo) throws ValorVazioOuNulo {
		parteVeiculo = parteVeiculo.toUpperCase().trim();
		if(parteVeiculo.isBlank()||parteVeiculo.isEmpty()) {
			throw new ValorVazioOuNulo();
		}
		return parteVeiculo;
	}

}
