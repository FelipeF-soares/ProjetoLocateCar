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

public class PessoaJuridicaService implements CrudCliente<PessoaJuridica> {
    static Path path = Paths.get("C:\\Users\\agame\\IdeaProjects\\ProjetoLocateCar\\src\\main\\java\\arquivos\\pessoaJuridica.txt");


    @Override
    public void adicionarCliente(List<PessoaJuridica> clients) {
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
    public List<PessoaJuridica> listarCliente() {
        List<PessoaJuridica> pessoaJuridica = new ArrayList<PessoaJuridica>();
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
            return pessoaJuridica;
        }

        Type tipoDeLista = new TypeToken<ArrayList<PessoaFisica>>() {
        }.getType();
        pessoaJuridica = gson.fromJson(linha, tipoDeLista);

        return pessoaJuridica;
    }


    @Override
    public List<PessoaJuridica> localizarCliente(String cnpj) {

        List<PessoaJuridica> pessoaJuridica = this.listarCliente();
        List<PessoaJuridica> clienteLocalizado = new ArrayList<PessoaJuridica>();

        for (int i = 0; i < pessoaJuridica.size(); i++) {
            if (pessoaJuridica.get(i).getCnpj().equals(cnpj)) {
                clienteLocalizado.add(pessoaJuridica.get(i));
            }

        }
        return clienteLocalizado;
    }


    @Override
    public void removerCliente(String cnpj) {
        List<PessoaJuridica> clienteLocalizado = this.localizarCliente(cnpj);
        List<PessoaJuridica> clientes = this.listarCliente();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            if (clienteLocalizado.isEmpty()) {
                throw new ErroPlacaNaoLocalizada();
            } else {
                for (int i = 0; i < clientes.size(); i++) {
                    for (int j = 0; j < clienteLocalizado.size(); j++) {
                        Cliente cliente = clientes.get(i);
                        if (cliente instanceof PessoaJuridica) {
                            ((PessoaJuridica) cliente).getCnpj().equals(cnpj);
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
    public void editarCliente(String cnpj, PessoaJuridica clientes) {

    }

}
