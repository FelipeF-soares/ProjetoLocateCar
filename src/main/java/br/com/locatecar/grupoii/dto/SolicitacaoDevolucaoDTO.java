package br.com.locatecar.grupoii.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SolicitacaoDevolucaoDTO {

    private String cpfCnpjCliente;
    private String placaVeiculo;
    private String logradouroAgencia;
    private LocalDate dataAluguel;
    private LocalDateTime dataHoraDevolucao;

    public String getCpfCnpjCliente() {
        return cpfCnpjCliente;
    }

    public void setCpfCnpjCliente(String cpfCnpjCliente) {
        this.cpfCnpjCliente = cpfCnpjCliente;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getLogradouroAgencia() {
        return logradouroAgencia;
    }

    public void setLogradouroAgencia(String logradouroAgencia) {
        this.logradouroAgencia = logradouroAgencia;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDateTime getDataHoraDevolucao() {
        return dataHoraDevolucao;
    }

    public void setDataHoraDevolucao(LocalDateTime dataHoraDevolucao) {
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

}
