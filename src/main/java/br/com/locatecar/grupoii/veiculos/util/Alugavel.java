package br.com.locatecar.grupoii.veiculos.util;

import br.com.locatecar.grupoii.veiculos.model.Aluguel;
import br.com.locatecar.grupoii.veiculos.model.Cliente;
import br.com.locatecar.grupoii.veiculos.model.Veiculo;

import java.time.LocalDate;

public interface Alugavel {
    public void alugarVeiculo (Cliente cliente, Veiculo veiculo);

    public Aluguel consultarAluguel(String cpfCnpj, String placaVeiculo, LocalDate diaAluguel);
}
