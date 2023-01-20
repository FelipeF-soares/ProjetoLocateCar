package br.com.locatecar.grupoii.veiculos.dto;

import br.com.locatecar.grupoii.veiculos.model.Moto;

public class MotoDto extends VeiculoDto<Moto> {
	
	@Override
	public Moto adicionar() {
		Moto moto = new Moto();
		moto.setPlaca(getPlaca());
		moto.setMarca(getMarca());
		moto.setModelo(getModelo());
		moto.setAnoDeFabricao(getAnoDeFabricao());
		moto.setIdAgencia(getAgencia());
		
		return moto;
	}

}
