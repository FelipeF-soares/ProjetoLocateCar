package br.com.locatecar.grupoii.veiculos.dto;

import br.com.locatecar.grupoii.veiculos.model.Caminhao;

public class CaminhaoDto extends VeiculoDto<Caminhao> {
	
	@Override
	public Caminhao adicionar() {
		Caminhao caminhao = new Caminhao();
		caminhao.setPlaca(getPlaca());
		caminhao.setMarca(getMarca());
		caminhao.setModelo(getModelo());
		caminhao.setAnoDeFabricao(getAnoDeFabricao());
		caminhao.setIdAgencia(getAgencia());
		
		return caminhao;
	}

}
