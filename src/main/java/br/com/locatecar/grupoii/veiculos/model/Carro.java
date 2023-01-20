package br.com.locatecar.grupoii.veiculos.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Carro extends Veiculo {
	
	public Carro() {
		super.setTipoVeiculo(TipoVeiculo.CARRO);
		super.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
	}

	@Override
	public BigDecimal getValorDiaria() {
		return BigDecimal.valueOf(150.00).setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
