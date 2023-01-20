package br.com.locatecar.grupoii.veiculos.service;

import br.com.locatecar.grupoii.veiculos.model.Alugel;
import br.com.locatecar.grupoii.veiculos.model.Cliente;
import br.com.locatecar.grupoii.veiculos.model.Veiculos;
import br.com.locatecar.grupoii.veiculos.util.Alugavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class AlugarService implements Alugavel {
    static Path path = Paths.get("D:\\Ada\\SantanderCoder\\ModuloIII\\Projetos\\LocateCar\\locatecar\\src\\main\\java\\arquivos\\caminhao.txt");


    @Override
    public void alugarVeiculo(Cliente cliente, Veiculos veiculos) {

        Alugel alugel = new Alugel();
        alugel.setVeiculo(veiculos);
        alugel.setCliente(cliente);
        alugel.setDataAlugel(LocalDate.of(2023,  1,  1));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String saidaVeiculosJson = gson.toJson(alugel);

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            Files.writeString(path, saidaVeiculosJson);
        }catch (Exception e) {
            System.out.println("Não foi possível alugar o veículo :(");
            System.out.println(e.getMessage());
        }

    }
}
