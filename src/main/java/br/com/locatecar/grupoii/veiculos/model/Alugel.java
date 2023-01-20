package br.com.locatecar.grupoii.veiculos.model;

import java.time.LocalDate;

public class Alugel {
    private Cliente cliente;
    private Veiculos veiculos;

    private LocalDate dataAlugel;



    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculos getVeiculos() {
        return veiculos;
    }

    public void setVeiculo(Veiculos veiculos) {
        this.veiculos = veiculos;
    }

    public LocalDate getDataAlugel() {
        return dataAlugel;
    }

    public void setDataAlugel(LocalDate dataAlugel) {
        this.dataAlugel = dataAlugel;
    }


}
