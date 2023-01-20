package br.com.locatecar.grupoii.veiculos.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Caminhao extends Veiculo {
	
	public Caminhao() {
		super.setTipoVeiculo(TipoVeiculo.CAMINHAO);
		super.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
	}

	@Override
	public BigDecimal getValorDiaria() {
		return BigDecimal.valueOf(200.00).setScale(2, RoundingMode.HALF_UP);
	}

}
