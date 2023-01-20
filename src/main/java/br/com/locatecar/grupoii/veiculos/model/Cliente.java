package br.com.locatecar.grupoii.veiculos.model;

import java.util.List;

public abstract class Cliente {

    private String nomeCliente;
    private TipoPessoa pessoa;
    private List<Telefone> telefones;


    public TipoPessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(TipoPessoa pessoa) {
        this.pessoa = pessoa;
    }


    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }


    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public abstract String getIdentificadorUnico();


}
