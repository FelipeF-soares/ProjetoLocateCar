package br.com.locatecar.grupoii.veiculos.model;

public class PessoaFisica extends Cliente {


    private String cpf;
    public PessoaFisica() {
        super.setPessoa(TipoPessoa.FISICA);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    @Override
    public String getIdentificadorUnico() {
        return getCpf();
    }
}
