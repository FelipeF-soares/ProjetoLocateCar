package br.com.locatecar.grupoii.veiculos.util;

import br.com.locatecar.grupoii.veiculos.model.Cliente;
import br.com.locatecar.grupoii.veiculos.model.Veiculos;

public interface Alugavel {
    public void alugarVeiculo (Cliente cliente, Veiculos veiculos);
}
