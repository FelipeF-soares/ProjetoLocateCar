package br.com.locatecar.grupoii.veiculos.model;

import java.math.BigDecimal;
import java.util.List;

import br.com.locatecar.grupoii.agencia.model.Agencia;

public abstract class Veiculo {
	
	private String placa;
	private TipoVeiculo tipoVeiculo;
	private StatusVeiculo statusVeiculo;
	private List<HistoricoAluguel> historicoAluguel;
	private String marca;
	private String modelo;
	private Integer anoDeFabricao;
	private Integer IdAgencia = null;
	
	public Integer getIdAgencia() {
		return this.IdAgencia;
	}
	
	public void setIdAgencia(Agencia agencia) {
		this.IdAgencia = agencia.getId();
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnoDeFabricao() {
		return anoDeFabricao;
	}

	public void setAnoDeFabricao(Integer anoDeFabricao) {
		this.anoDeFabricao = anoDeFabricao;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public StatusVeiculo getStatusVeiculo() {
		return statusVeiculo;
	}

	public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
		this.statusVeiculo = statusVeiculo;
	}

	public List<HistoricoAluguel> getHistoricoAluguel() {
		return historicoAluguel;
	}

	public void setHistoricoAluguel(List<HistoricoAluguel> historicoAluguel) {
		this.historicoAluguel = historicoAluguel;
	}

	public abstract BigDecimal getValorDiaria();
	
	@Override
	public String toString() {
		return "placa: "+ this.placa+" Tipo Veiculo: "+ this.tipoVeiculo+" Status Veiculo: "+ this.statusVeiculo+
				" Marca: "+this.marca+" Modelo: "+this.modelo+" Ano Fabricacao: "+this.anoDeFabricao+" Id da Agência: "+this.IdAgencia;
	}
	
	

}
