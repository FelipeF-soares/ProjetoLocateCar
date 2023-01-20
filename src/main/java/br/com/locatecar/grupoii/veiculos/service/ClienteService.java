package br.com.locatecar.grupoii.veiculos.service;

import br.com.locatecar.grupoii.erros.ErroPlacaNaoLocalizada;
import br.com.locatecar.grupoii.veiculos.model.Carro;
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

public class ClienteService implements CrudCliente<Cliente> {
    static Path path = Paths.get("D:\\Ada\\SantanderCoder\\ModuloIII\\Projetos\\LocateCar\\locatecar\\src\\main\\java\\arquivos\\caminhao.txt");



    @Override
    public void adicionarCliente(List<Cliente> clients) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String saidaVeiculosJson = gson.toJson(clients);

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            Files.writeString(path, saidaVeiculosJson);
        }catch (Exception e) {
            System.out.println("Não foi possível cadastrar o cliente!");
        }
    }

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        Gson gson = new Gson();
        String linha = "";
        try {
            if(!Files.exists(path)) {
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            linha = Files.readString(path);
        }catch (Exception e) {
            System.out.println("Lista Não Encontrada");
            return clientes;
        }

        Type tipoDeLista = new TypeToken<ArrayList<Cliente>>(){}.getType();
        clientes = gson.fromJson(linha, tipoDeLista);

        return clientes;
    }


    @Override
    public List<Cliente> localizarCliente(String cpfCnpj) {

        List<Cliente> clientes = this.listarCliente();
        List<Cliente> clienteLocalizado = new ArrayList<Cliente>();

        for(int i = 0; i< clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente instanceof PessoaFisica){
                PessoaFisica pessoaFisica = (PessoaFisica) cliente;
                if (pessoaFisica.getCpf().equals(cpfCnpj)) {
                    clienteLocalizado.add(pessoaFisica);
                }

            }else if (cliente instanceof PessoaJuridica){
                PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
                if (pessoaJuridica.getCnpj().equals(cpfCnpj)) {
                    clienteLocalizado.add(pessoaJuridica);
                }

            }

        }
        return clienteLocalizado;
    }


    @Override
    public void removerCliente(String cpfCnpj) {
        List<Cliente> clienteLocalizado = this.localizarCliente(cpfCnpj);
        List<Cliente> clientes = this.listarCliente();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            if(clienteLocalizado.isEmpty()) {
                throw new ErroPlacaNaoLocalizada();
            }else{
                for(int i = 0; i < clientes.size(); i++ ) {
                    for(int j = 0; j < clienteLocalizado.size(); j++) {
                        Cliente cliente = clientes.get(i);
                        if (cliente instanceof PessoaFisica){
                            if (((PessoaFisica) cliente).getCpf().equals(cpfCnpj)) {
                                clientes.remove(i);
                            }
                        } else if (cliente instanceof PessoaJuridica) {
                            if (((PessoaJuridica) cliente).getCnpj().equals(cpfCnpj)) {
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
    public void editarCliente(String cpfCnjp, Cliente clientes) {
        List<Cliente> listaCliente = this.localizarCliente(cpfCnjp);
        List<Cliente> listar = this.listarCliente();
        for(int i = 0; i<listar.size();i++) {

            Cliente cliente = listar.get(i);

            if (cliente instanceof PessoaFisica){
                PessoaFisica pessoaFisica = (PessoaFisica) cliente;
                if (pessoaFisica.getCpf().equals(cpfCnjp)){
                    listar.add(i, pessoaFisica);
                }
            } else if (cliente instanceof PessoaJuridica) {
                PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
                if (pessoaJuridica.getCnpj().equals(cpfCnjp)){
                    listar.add(i, pessoaJuridica);
                }

            }

        }
        this.adicionarCliente(listaCliente);
    }



}
