package br.com.locatecar.grupoii.veiculos.service;

import br.com.locatecar.grupoii.util.UtilArquivos;
import br.com.locatecar.grupoii.veiculos.model.*;
import br.com.locatecar.grupoii.veiculos.util.Alugavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class AlugarService implements Alugavel {
    static Path path = Paths.get("C:\\Users\\agame\\IdeaProjects\\ProjetoLocateCar\\src\\main\\java\\arquivos\\registroAlugel.txt");

    private static final int PRIMEIRO_ITEM = 0;
    private PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
    private PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService();
    private CarroService carroService = new CarroService();
    private MotoService motoService = new MotoService();
    private CaminhaoService caminhaoService = new CaminhaoService();

    @Override
    public void alugarVeiculo(Cliente cliente, Veiculo veiculo) {

        Aluguel alugel = new Aluguel();
        alugel.setVeiculo(veiculo);
        alugel.setCliente(cliente);
        alugel.setDataAlugel(LocalDate.now());

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

    @Override
    public Aluguel consultarAluguel(String cpfCnpj, String placaVeiculo, LocalDate diaAluguel) {

        Cliente clienteConsulta = getCliente(cpfCnpj);
        Veiculo veiculo = getVeiculo(placaVeiculo);

        List<Aluguel> alugueis = UtilArquivos.lerArquivo("src\\main\\java\\arquivos\\alugueis.txt",
                Aluguel.class);

        Aluguel aluguelEncontrado = getAluguelEncontrado(diaAluguel, clienteConsulta, veiculo, alugueis);

        return aluguelEncontrado;
    }

    private static Aluguel getAluguelEncontrado(LocalDate diaAluguel, Cliente clienteConsulta, Veiculo veiculo, List<Aluguel> alugueis) {
        Aluguel aluguelEncontrado = null;

        for(Aluguel aluguel : alugueis) {
            if (aluguel.getCliente().equals(clienteConsulta) && aluguel.getVeiculos().equals(veiculo)
                    && aluguel.getDataAlugel().equals(diaAluguel)) {
                aluguelEncontrado = aluguel;
                break;
            }
        }
        return aluguelEncontrado;
    }

    private Veiculo getVeiculo(String placaVeiculo) {

        Veiculo veiculo = null;

        List<Carro> carros = carroService.localizar(placaVeiculo);
        List<Moto> motos = motoService.localizar(placaVeiculo);
        List<Caminhao> caminhoes = caminhaoService.localizar(placaVeiculo);

        if (carros.isEmpty() && motos.isEmpty() && caminhoes.isEmpty()) {
            throw new RuntimeException("Veiculo não encontrado!");
        } else if (!carros.isEmpty()) {
            veiculo = carros.get(PRIMEIRO_ITEM);
        } else if(!motos.isEmpty()) {
            veiculo = motos.get(PRIMEIRO_ITEM);
        } else if (!caminhoes.isEmpty()) {
            veiculo = caminhoes.get(PRIMEIRO_ITEM);
        }

        return veiculo;
    }

    private Cliente getCliente(String cpfCnpj) {

        Cliente cliente = null;

        List<PessoaFisica> pessoasFisicasClientes = pessoaFisicaService.localizarCliente(cpfCnpj);
        List<PessoaJuridica> pessoasJuridicasClientes = pessoaJuridicaService.localizarCliente(cpfCnpj);

        if(pessoasFisicasClientes.isEmpty() && pessoasJuridicasClientes.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado!");
        } else if(!pessoasFisicasClientes.isEmpty()) {
            cliente = pessoasFisicasClientes.get(PRIMEIRO_ITEM);
        } else if(!pessoasJuridicasClientes.isEmpty()) {
            cliente = pessoasJuridicasClientes.get(PRIMEIRO_ITEM);
        }

        return cliente;
    }

}
