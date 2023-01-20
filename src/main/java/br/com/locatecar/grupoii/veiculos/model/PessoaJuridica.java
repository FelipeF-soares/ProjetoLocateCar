package br.com.locatecar.grupoii.veiculos.model;

public class PessoaJuridica extends Cliente {
    private String cnpj;

    public PessoaJuridica() {
        super.setPessoa(TipoPessoa.JURIDICA);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    @Override
    public String getIdentificadorUnico() {
        return getCnpj();
    }
}
