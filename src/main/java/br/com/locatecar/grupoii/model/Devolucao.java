package br.com.locatecar.grupoii.model;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.veiculos.model.Aluguel;
import br.com.locatecar.grupoii.veiculos.model.Cliente;
import br.com.locatecar.grupoii.veiculos.model.TipoPessoa;
import br.com.locatecar.grupoii.veiculos.model.Veiculo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Devolucao {

    private Cliente cliente;
    private Veiculo veiculo;
    private Agencia agencia;
    private Aluguel aluguel;
    private LocalDateTime dataHoraDevolucao;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public LocalDateTime getDataHoraDevolucao() {
        return dataHoraDevolucao;
    }

    public void setDataHoraDevolucao(LocalDateTime dataHoraDevolucao) {
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

    public int getTotalDiasAluguel() {
        return (dataHoraDevolucao != null && aluguel != null & aluguel.getDataAlugel() != null) ?
                dataHoraDevolucao.toLocalDate().compareTo(aluguel.getDataAlugel()) : 0;
    }

    public BigDecimal getPercentualDesconto() {

        BigDecimal percentualDesconto = BigDecimal.ZERO;

        if(TipoPessoa.FISICA.equals(cliente.getPessoa()) && getTotalDiasAluguel() > 5) {

            percentualDesconto = BigDecimal.valueOf(0.05).setScale(2, RoundingMode.HALF_UP);

        } else if(TipoPessoa.JURIDICA.equals(cliente.getPessoa()) && getTotalDiasAluguel() > 3) {

            percentualDesconto = BigDecimal.valueOf(0.1).setScale(2, RoundingMode.HALF_UP);
        }

        return percentualDesconto;
    }

    public BigDecimal getValorIntegral() {
        return veiculo.getValorDiaria().multiply(BigDecimal.valueOf(getTotalDiasAluguel()));
    }

    public BigDecimal getTotalAPagar() {

        BigDecimal valorIntegral = getValorIntegral();
        BigDecimal valorComDesconto = valorIntegral.subtract(valorIntegral.multiply(getPercentualDesconto()));

        return valorComDesconto;
    }

    @Override
    public String toString() {
        return "Devolucao :" +
                "cliente: " + cliente +
                ", veiculo: " + veiculo +
                ", agencia: " + agencia +
                ", aluguel: " + aluguel +
                ", dataHoraDevolucao: " + dataHoraDevolucao +
                '\n';
    }
}
