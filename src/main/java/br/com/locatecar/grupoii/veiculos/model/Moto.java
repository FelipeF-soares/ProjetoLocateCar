package br.com.locatecar.grupoii.veiculos.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Moto extends Veiculo {
	
	public Moto() {
		super.setTipoVeiculo(TipoVeiculo.MOTO);
		super.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
	}

	@Override
	public BigDecimal getValorDiaria() {
		return BigDecimal.valueOf(100.00).setScale(2, RoundingMode.HALF_UP);
	}

}
