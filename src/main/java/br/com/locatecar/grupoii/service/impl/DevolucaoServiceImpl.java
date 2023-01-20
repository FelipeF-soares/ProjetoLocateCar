package br.com.locatecar.grupoii.service.impl;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.agencia.service.AgenciaService;
import br.com.locatecar.grupoii.dto.SolicitacaoDevolucaoDTO;
import br.com.locatecar.grupoii.model.Devolucao;
import br.com.locatecar.grupoii.service.DevolucaoService;
import br.com.locatecar.grupoii.util.UtilArquivos;
import br.com.locatecar.grupoii.veiculos.model.*;
import br.com.locatecar.grupoii.veiculos.service.*;
import br.com.locatecar.grupoii.veiculos.util.VeiculosController;

import java.util.List;

public class DevolucaoServiceImpl implements DevolucaoService {

    private static final String CAMINHO_ARQUIVO_DEVOLUCOES = "src\\main\\java\\arquivos\\devolucoes.txt";
    private static final int PRIMEIRO_ITEM = 0;

    private AgenciaService agenciaService = new AgenciaService();
    private CarroService carroService = new CarroService();
    private MotoService motoService = new MotoService();
    private CaminhaoService caminhaoService = new CaminhaoService();
    private PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
    private PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService();
    private AlugarService alugarService = new AlugarService();

    @Override
    public Devolucao registrarDevolucao(SolicitacaoDevolucaoDTO solicitacaoDevolucaoDTO) {

        Devolucao devolucao = new Devolucao();

        try {
            devolucao.setAgencia(getAgencia(solicitacaoDevolucaoDTO));
            devolucao.setCliente(getCliente(solicitacaoDevolucaoDTO));
            devolucao.setAluguel(getAluguel(solicitacaoDevolucaoDTO));
            devolucao.setDataHoraDevolucao(solicitacaoDevolucaoDTO.getDataHoraDevolucao());
            Veiculo veiculo = devolucao.getAluguel().getVeiculos();
            validaVeiculoStatusAlugado(veiculo);
            devolucao.setVeiculo(veiculo);

            atualizarDisponibilidadeVeiculo(veiculo);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }

        List<Devolucao> devolucoes = UtilArquivos.lerArquivo(CAMINHO_ARQUIVO_DEVOLUCOES, Devolucao.class);
        devolucoes.add(devolucao);
        UtilArquivos.salvarArquivo(CAMINHO_ARQUIVO_DEVOLUCOES, devolucoes);

        return devolucao;
    }

    private void atualizarDisponibilidadeVeiculo(Veiculo veiculo) {

        veiculo.setStatusVeiculo(StatusVeiculo.DISPONIVEL);

        if (TipoVeiculo.CARRO.equals(veiculo.getTipoVeiculo())) {
            carroService.editar(veiculo.getPlaca(), (Carro) veiculo);
        } else if (TipoVeiculo.MOTO.equals(veiculo.getTipoVeiculo())) {
            motoService.editar(veiculo.getPlaca(), (Moto) veiculo);
        } else if (TipoVeiculo.CAMINHAO.equals(veiculo.getTipoVeiculo())) {
            caminhaoService.editar(veiculo.getPlaca(), (Caminhao) veiculo);
        }
    }

    private static void validaVeiculoStatusAlugado(Veiculo veiculo) {
        if (!StatusVeiculo.ALUGADO.equals(veiculo.getStatusVeiculo())) {
            throw new RuntimeException("Veiculo não está atualmente alugado!");
        }
    }

    private Aluguel getAluguel(SolicitacaoDevolucaoDTO solicitacaoDevolucaoDTO) {

        Aluguel aluguelEncontrado = alugarService.consultarAluguel(solicitacaoDevolucaoDTO.getCpfCnpjCliente(),
                solicitacaoDevolucaoDTO.getPlacaVeiculo(), solicitacaoDevolucaoDTO.getDataAluguel());

        if (aluguelEncontrado == null) {
            throw new RuntimeException("Aluguel não encontrado!");
        }

        return aluguelEncontrado;
    }

    private Cliente getCliente(SolicitacaoDevolucaoDTO solicitacaoDevolucaoDTO) {

        Cliente cliente = null;

        List<PessoaFisica> pessoasFisicasClientes = pessoaFisicaService.localizarCliente(solicitacaoDevolucaoDTO.getCpfCnpjCliente());
        List<PessoaJuridica> pessoasJuridicasClientes = pessoaJuridicaService.localizarCliente(solicitacaoDevolucaoDTO.getCpfCnpjCliente());

        if(pessoasFisicasClientes.isEmpty() && pessoasJuridicasClientes.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado!");
        } else if(!pessoasFisicasClientes.isEmpty()) {
            cliente = pessoasFisicasClientes.get(PRIMEIRO_ITEM);
        } else if(!pessoasJuridicasClientes.isEmpty()) {
            cliente = pessoasJuridicasClientes.get(PRIMEIRO_ITEM);
        }

        return cliente;
    }

    private Agencia getAgencia(SolicitacaoDevolucaoDTO solicitacaoDevolucaoDTO) {

        Agencia agencia = null;

        List<Agencia> agencias = agenciaService.buscarAgenciaPorNomeLogradouro(
                solicitacaoDevolucaoDTO.getLogradouroAgencia());

        if(agencias.isEmpty()) {
            throw new RuntimeException("Agência não encontrada!");
        }

        agencia = agencias.get(PRIMEIRO_ITEM);

        return agencia;
    }

}
