package br.com.locatecar.grupoii.veiculos.dto;

import br.com.locatecar.grupoii.veiculos.model.Carro;

public class CarroDto extends VeiculoDto<Carro> {
	
	public Carro adicionar() {
		Carro carro = new Carro();
		carro.setPlaca(super.getPlaca());
		carro.setMarca(super.getMarca());
		carro.setModelo(super.getModelo());
		carro.setAnoDeFabricao(super.getAnoDeFabricao());
		carro.setIdAgencia(super.getAgencia());
		
		return carro;
	}
	
}
