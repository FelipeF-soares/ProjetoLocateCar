package br.com.locatecar.grupoii.util;

import java.util.List;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.agencia.service.AgenciaService;
import br.com.locatecar.grupoii.erros.ErroDeValorInvalidoDaLista;
import br.com.locatecar.grupoii.erros.ValorVazioOuNulo;

public class ValidaEntrada {
	
	public String validaEntrada(String entrada) throws ValorVazioOuNulo {
		if(entrada.isBlank()||entrada.isBlank()) throw new ValorVazioOuNulo();
		
		return entrada.toUpperCase().trim();
	}
	
	public SiglasEstados validaEstadoFederativo(String entrada) throws ValorVazioOuNulo, ErroDeValorInvalidoDaLista {
		if(entrada.isBlank()||entrada.isBlank()) throw new ValorVazioOuNulo();
		entrada = entrada.toUpperCase().trim();
		SiglasEstados[] siglas = SiglasEstados.values();
		for(int i=0; i<siglas.length; i++) {
			if(entrada.equals(siglas[i].name())) {
				return siglas[i];
			}
		}
		throw new ErroDeValorInvalidoDaLista();
	}
	
	public Integer validaIdAgencia(String entrada) throws ValorVazioOuNulo, ErroDeValorInvalidoDaLista{
		if(entrada.isBlank()||entrada.isBlank()) throw new ValorVazioOuNulo();
		Integer entradaInteger = Integer.valueOf(entrada);
		List<Agencia> listaDeAgencia = new AgenciaService().listar();
		if(entradaInteger > listaDeAgencia.size() || entradaInteger < 0 ) throw new ErroDeValorInvalidoDaLista();
		return entradaInteger;
	}
}
