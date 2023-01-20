package br.com.locatecar.grupoii.veiculos.model;

import java.time.LocalDate;

public class Aluguel {
    private Cliente cliente;
    private Veiculo veiculos;

    private LocalDate dataAlugel;



    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculos() {
        return veiculos;
    }

    public void setVeiculo(Veiculo veiculos) {
        this.veiculos = veiculos;
    }

    public LocalDate getDataAlugel() {
        return dataAlugel;
    }

    public void setDataAlugel(LocalDate dataAlugel) {
        this.dataAlugel = dataAlugel;
    }


}
