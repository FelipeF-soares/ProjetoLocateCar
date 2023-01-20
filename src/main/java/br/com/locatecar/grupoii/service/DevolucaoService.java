package br.com.locatecar.grupoii.service;

import br.com.locatecar.grupoii.dto.SolicitacaoDevolucaoDTO;
import br.com.locatecar.grupoii.model.Devolucao;

public interface DevolucaoService {

    public Devolucao registrarDevolucao(SolicitacaoDevolucaoDTO solicitacaoDevolucaoDTO);

}
