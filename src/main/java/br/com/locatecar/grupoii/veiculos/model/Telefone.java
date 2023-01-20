package br.com.locatecar.grupoii.veiculos.model;

import br.com.locatecar.grupoii.util.TipoTelefone;

public class Telefone {
    private String ddd;
    private String numero;

    private TipoTelefone tipoTelefone;

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
