package br.com.locatecar.grupoii.veiculos.util;

import java.util.List;

public interface CrudCliente<T> {


    void adicionarCliente(List<T> clients);

    public List<T> listarCliente();

    public List<T> localizarCliente(String cpf);

    public void removerCliente(String cpf);

    public void editarCliente(String cpf, T clientes);



}
