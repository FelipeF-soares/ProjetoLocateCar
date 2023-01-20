package br.com.locatecar.grupoii.veiculos.util;

public interface ClienteController<E> {

    void salvarCliente(E novoCliente);

    void alterarCliente(E clienteAlterado, String cpf);
}
