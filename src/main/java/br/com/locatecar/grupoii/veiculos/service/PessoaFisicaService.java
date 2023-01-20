package br.com.locatecar.grupoii.veiculos.service;

import br.com.locatecar.grupoii.erros.ErroPlacaNaoLocalizada;
import br.com.locatecar.grupoii.veiculos.model.Cliente;
import br.com.locatecar.grupoii.veiculos.model.PessoaFisica;
import br.com.locatecar.grupoii.veiculos.model.PessoaJuridica;
import br.com.locatecar.grupoii.veiculos.util.CrudCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaService implements CrudCliente<PessoaFisica> {
    static Path path = Paths.get("C:\\Users\\agame\\IdeaProjects\\ProjetoLocateCar\\src\\main\\java\\arquivos\\pessoaFisica.txt");


    @Override
    public void adicionarCliente(List<PessoaFisica> clients) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String saidaVeiculosJson = gson.toJson(clients);

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            Files.writeString(path, saidaVeiculosJson);
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar o cliente!");
        }
    }

    @Override
    public List<PessoaFisica> listarCliente() {
        List<PessoaFisica> pessoaFisica = new ArrayList<PessoaFisica>();
        Gson gson = new Gson();
        String linha = "";
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            linha = Files.readString(path);
        } catch (Exception e) {
            System.out.println("Lista Não Encontrada");
            return pessoaFisica;
        }

        Type tipoDeLista = new TypeToken<ArrayList<PessoaFisica>>() {
        }.getType();
        pessoaFisica = gson.fromJson(linha, tipoDeLista);

        return pessoaFisica;
    }


    @Override
    public List<PessoaFisica> localizarCliente(String cpf) {

        List<PessoaFisica> pessoaFisica = this.listarCliente();
        List<PessoaFisica> clienteLocalizado = new ArrayList<PessoaFisica>();

        for (int i = 0; i < pessoaFisica.size(); i++) {
            if (pessoaFisica.get(i).getCpf().equals(cpf)) {
                clienteLocalizado.add(pessoaFisica.get(i));
            }

        }
        return clienteLocalizado;
    }


    @Override
    public void removerCliente(String cpf) {
        List<PessoaFisica> clienteLocalizado = this.localizarCliente(cpf);
        List<PessoaFisica> clientes = this.listarCliente();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            if (clienteLocalizado.isEmpty()) {
                throw new ErroPlacaNaoLocalizada();
            } else {
                for (int i = 0; i < clientes.size(); i++) {
                    for (int j = 0; j < clienteLocalizado.size(); j++) {
                        Cliente cliente = clientes.get(i);
                        if (cliente instanceof PessoaFisica) {
                            ((PessoaFisica) cliente).getCpf().equals(cpf);
                            {
                                clientes.remove(i);
                            }
                        }

                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editarCliente(String cpf, PessoaFisica clientes) {

    }

}
