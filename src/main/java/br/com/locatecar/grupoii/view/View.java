package br.com.locatecar.grupoii.view;

import java.util.List;
import java.util.Scanner;

import br.com.locatecar.grupoii.agencia.controller.AgenciaController;
import br.com.locatecar.grupoii.agencia.dto.AgenciaDto;
import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.agencia.service.AgenciaService;
import br.com.locatecar.grupoii.util.SiglasEstados;
import br.com.locatecar.grupoii.util.ValidaEntrada;
import br.com.locatecar.grupoii.veiculos.controller.CaminhaoController;
import br.com.locatecar.grupoii.veiculos.controller.CarroController;
import br.com.locatecar.grupoii.veiculos.controller.MotoController;
import br.com.locatecar.grupoii.veiculos.dto.CaminhaoDto;
import br.com.locatecar.grupoii.veiculos.dto.CarroDto;
import br.com.locatecar.grupoii.veiculos.dto.MotoDto;
import br.com.locatecar.grupoii.veiculos.model.Caminhao;
import br.com.locatecar.grupoii.veiculos.model.Carro;
import br.com.locatecar.grupoii.veiculos.model.PessoaFisica;
import br.com.locatecar.grupoii.veiculos.model.PessoaJuridica;
import br.com.locatecar.grupoii.veiculos.service.PessoaFisicaService;
import br.com.locatecar.grupoii.veiculos.service.PessoaJuridicaService;
import br.com.locatecar.grupoii.veiculos.model.Moto;
import br.com.locatecar.grupoii.veiculos.service.CaminhaoService;
import br.com.locatecar.grupoii.veiculos.service.CarroService;
import br.com.locatecar.grupoii.veiculos.service.MotoService;
import br.com.locatecar.grupoii.veiculos.util.ValidaVeiculos;

public class View {
	static Scanner scanner = new Scanner(System.in);
	static Boolean retornoMenu = true;
	
	
	// MENU PRINCIPAL
	public void menu() {
		System.out.println(".:: BOAS VINDAS À LOCATECAR! ::.");
		System.out.println(".::       BY GRUPO II        ::.");
		System.out.println();
		
		do {
			System.out.println("Menu Principal");
			System.out.println("1 - Veículos");
			System.out.println("2 - Agencias");
			System.out.println("3 - Clientes");
			System.out.println("4 - Registrar Aluguel");
			System.out.println("0 - Sair");
			
			switch(scanner.nextLine()) {
				case "1" -> this.menuVeiculos();
				case "2" -> this.menuAgencia();
				case "3" -> this.menuClientes();
				case "4" -> this.menuRegistrarAluguel();
				case "0" -> {System.out.println("Obrigado por utilizar nosso sistema :)");
							retornoMenu = false;}
				default -> System.out.println("Ops, opção inválida!");
			}
		}while(retornoMenu);
		
	}
	
	 // INÍCIO DOS METODOS DO VEÍCULO
	public void menuVeiculos() {
		Boolean validaVeiculo = true;
		System.out.println("....: Menu De Veículos :....");
		do {
			System.out.println("1 - Cadastrar um novo veículos.");
			System.out.println("2 - Alterar um veículo já cadastrado.");
			System.out.println("3 - Buscar um veículo por parte do nome.");
			System.out.println("4 - Buscar um veículo pelo emplacamento.");
			System.out.println("5 - Remover Veículo.");
			System.out.println("0 - Voltar ao Menu Principal.");
			switch(scanner.nextLine()) {
				case "1" -> cadastrarVeiculo();
				case "2" -> alterarVeiculo();
				case "3" -> buscarPorNome();
				case "4" -> buscarPorEmplacamento();
				case "5" -> removerVeículo();
				case "0" -> validaVeiculo = false;
				default -> System.out.println("Ops, opção inválida!");
			}
		}while(validaVeiculo);
		
	}
	
	public void cadastrarVeiculo() {
		Boolean validaCadastro = true;
		System.out.println("..:    Cadastro de Veículo   :..");
		do {
			System.out.println("Escolha o Tipo De Veículo");
			System.out.println("1 - Carro");
			System.out.println("2 - Moto");
			System.out.println("3 - Caminhão");
			System.out.println("0 - Sair");
			switch(scanner.nextLine()) {
				case "1" -> cadastraCarro();
				case "2" -> cadastraMoto();
				case "3" -> cadastraCaminhao();
				case "0" -> validaCadastro = false;
				default -> System.out.println("Ops, opção inválida!");
			}
			
		}while(validaCadastro);
		
	}
	public void cadastraCarro() {
		ValidaVeiculos validaVeiculos = new ValidaVeiculos();
		Boolean saida = true;
		CarroDto carroDto = new CarroDto();
		Carro novoCarro = new Carro();
		do {
			try {
				System.out.println("Digíte o emplacamento do veículo com o hífen:");
				String placaValidada = validaVeiculos.placaValidada(scanner.nextLine());
				carroDto.setPlaca(placaValidada);
				System.out.println("Digíte a Marca do Veículo");
				String marcaValidado = validaVeiculos.marcaValidado(scanner.nextLine());
				carroDto.setMarca(marcaValidado);
				System.out.println("Digíte o Modelo do Veículo");
				String modeloValidado = validaVeiculos.modeloValidado(scanner.nextLine());
				carroDto.setModelo(modeloValidado);
				System.out.println("Digíte o Ano do Veículo");
				Integer anoValidado = validaVeiculos.anoValidado(scanner.nextLine());
				carroDto.setAnoDeFabricao(anoValidado);
				System.out.println(new AgenciaService().listar());
				System.out.println("Digíte o ID da agência");
				Agencia AgenciaValidado = validaVeiculos.idAgenciaValidado(scanner.nextLine());
				carroDto.setAgencia(AgenciaValidado);
				novoCarro = carroDto.adicionar();
				System.out.println(novoCarro);
				System.out.println("\nConfirma as Informações acima?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = false;
					case "2" -> System.out.println("Cadastro Cancelado!!!");
					default -> System.out.println("Cadastro Cancelado!!!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
		new CarroController<Carro>().salvarVeiculo(novoCarro);
	}
	
	public void cadastraMoto() {
		ValidaVeiculos validaVeiculos = new ValidaVeiculos();
		Boolean saida = true;
		MotoDto motoDto = new MotoDto();
		Moto novoMoto = new Moto();
		do {
			try {
				System.out.println("Digíte o emplacamento do veículo com o hífen:");
				String placaValidada = validaVeiculos.placaValidada(scanner.nextLine());
				motoDto.setPlaca(placaValidada);
				System.out.println("Digíte a Marca do Veículo");
				String marcaValidado = validaVeiculos.marcaValidado(scanner.nextLine());
				motoDto.setMarca(marcaValidado);
				System.out.println("Digíte o Modelo do Veículo");
				String modeloValidado = validaVeiculos.modeloValidado(scanner.nextLine());
				motoDto.setModelo(modeloValidado);
				System.out.println("Digíte o Ano do Veículo");
				Integer anoValidado = validaVeiculos.anoValidado(scanner.nextLine());
				motoDto.setAnoDeFabricao(anoValidado);
				System.out.println(new AgenciaService().listar());
				System.out.println("Digíte o ID da agência");
				Agencia AgenciaValidado = validaVeiculos.idAgenciaValidado(scanner.nextLine());
				motoDto.setAgencia(AgenciaValidado);
				novoMoto = motoDto.adicionar();
				System.out.println(novoMoto);
				System.out.println("\nConfirma as Informações acima?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = false;
					case "2" -> System.out.println("Cadastro Cancelado!!!");
					default -> System.out.println("Cadastro Cancelado!!!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
		new MotoController().salvarVeiculo(novoMoto);
		
	}
	public void cadastraCaminhao() {
		ValidaVeiculos validaVeiculos = new ValidaVeiculos();
		Boolean saida = true;
		CaminhaoDto caminhaDto = new CaminhaoDto();
		Caminhao novoCaminhao = new Caminhao();
		do {
			try {
				System.out.println("Digíte o emplacamento do veículo com o hífen:");
				String placaValidada = validaVeiculos.placaValidada(scanner.nextLine());
				caminhaDto.setPlaca(placaValidada);
				System.out.println("Digíte a Marca do Veículo");
				String marcaValidado = validaVeiculos.marcaValidado(scanner.nextLine());
				caminhaDto.setMarca(marcaValidado);
				System.out.println("Digíte o Modelo do Veículo");
				String modeloValidado = validaVeiculos.modeloValidado(scanner.nextLine());
				caminhaDto.setModelo(modeloValidado);
				System.out.println("Digíte o Ano do Veículo");
				Integer anoValidado = validaVeiculos.anoValidado(scanner.nextLine());
				caminhaDto.setAnoDeFabricao(anoValidado);
				System.out.println(new AgenciaService().listar());
				System.out.println("Digíte o ID da agência");
				Agencia AgenciaValidado = validaVeiculos.idAgenciaValidado(scanner.nextLine());
				caminhaDto.setAgencia(AgenciaValidado);
				novoCaminhao = caminhaDto.adicionar();
				System.out.println(novoCaminhao);
				System.out.println("\nConfirma as Informações acima?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = false;
					case "2" -> System.out.println("Cadastro Cancelado!!!");
					default -> System.out.println("Cadastro Cancelado!!!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
		new CaminhaoController().salvarVeiculo(novoCaminhao);
		
	}
	
	public void alterarVeiculo() {
		Boolean validaCadastro = true;
		System.out.println("..:    Cadastro de Veículo   :..");
		do {
			System.out.println("Escolha o Tipo De Veículo");
			System.out.println("1 - Carro");
			System.out.println("2 - Moto");
			System.out.println("3 - Caminhão");
			System.out.println("0 - Sair");
			switch(scanner.nextLine()) {
				case "1" -> alterarCarro();
				case "2" -> alterarMoto();
				case "3" -> alterarCaminhao();
				case "0" -> validaCadastro = false;
				default -> System.out.println("Ops, opção inválida!");
			}
			
		}while(validaCadastro);
	}
	
	public void alterarCarro() {
		ValidaVeiculos validaVeiculos = new ValidaVeiculos();
		Boolean saida = true;
		CarroDto carroDto = new CarroDto();
		Carro novoCarro = new Carro();
		do {
			try {
				System.out.println("Digíte o emplacamento do veículo com o hífen:");
				String placaValidada = validaVeiculos.placaLocalizado(scanner.nextLine());
				carroDto.setPlaca(placaValidada);
				System.out.println("Digíte a Marca do Veículo");
				String marcaValidado = validaVeiculos.marcaValidado(scanner.nextLine());
				carroDto.setMarca(marcaValidado);
				System.out.println("Digíte o Modelo do Veículo");
				String modeloValidado = validaVeiculos.modeloValidado(scanner.nextLine());
				carroDto.setModelo(modeloValidado);
				System.out.println("Digíte o Ano do Veículo");
				Integer anoValidado = validaVeiculos.anoValidado(scanner.nextLine());
				carroDto.setAnoDeFabricao(anoValidado);
				System.out.println(new AgenciaService().listar());
				System.out.println("Digíte o ID da agência");
				Agencia AgenciaValidado = validaVeiculos.idAgenciaValidado(scanner.nextLine());
				carroDto.setAgencia(AgenciaValidado);
				novoCarro = carroDto.adicionar();
				System.out.println(novoCarro);
				System.out.println("\nConfirma as Informações acima?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = false;
					case "2" -> System.out.println("Cadastro Cancelado!!!");
					default -> System.out.println("Cadastro Cancelado!!!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		new CarroController<Carro>().alterarVeiculo(novoCarro, novoCarro.getPlaca());
	}
	
	public void alterarMoto() {
		ValidaVeiculos validaVeiculos = new ValidaVeiculos();
		Boolean saida = true;
		MotoDto motoDto = new MotoDto();
		Moto novoMoto = new Moto();
		do {
			try {
				System.out.println("Digíte o emplacamento do veículo com o hífen:");
				String placaValidada = validaVeiculos.placaLocalizado(scanner.nextLine());
				motoDto.setPlaca(placaValidada);
				System.out.println("Digíte a Marca do Veículo");
				String marcaValidado = validaVeiculos.marcaValidado(scanner.nextLine());
				motoDto.setMarca(marcaValidado);
				System.out.println("Digíte o Modelo do Veículo");
				String modeloValidado = validaVeiculos.modeloValidado(scanner.nextLine());
				motoDto.setModelo(modeloValidado);
				System.out.println("Digíte o Ano do Veículo");
				Integer anoValidado = validaVeiculos.anoValidado(scanner.nextLine());
				motoDto.setAnoDeFabricao(anoValidado);
				System.out.println(new AgenciaService().listar());
				System.out.println("Digíte o ID da agência");
				Agencia AgenciaValidado = validaVeiculos.idAgenciaValidado(scanner.nextLine());
				motoDto.setAgencia(AgenciaValidado);
				novoMoto = motoDto.adicionar();
				System.out.println(novoMoto);
				System.out.println("\nConfirma as Informações acima?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = false;
					case "2" -> System.out.println("Cadastro Cancelado!!!");
					default -> System.out.println("Cadastro Cancelado!!!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
		new MotoController().alterarVeiculo(novoMoto, novoMoto.getPlaca());
		
	}
	
	public void alterarCaminhao() {
		ValidaVeiculos validaVeiculos = new ValidaVeiculos();
		Boolean saida = true;
		CaminhaoDto caminhaoDto = new CaminhaoDto();
		Caminhao caminhaoNovo = new Caminhao();
		do {
			try {
				System.out.println("Digíte o emplacamento do veículo com o hífen:");
				String placaValidada = validaVeiculos.placaLocalizado(scanner.nextLine());
				caminhaoDto.setPlaca(placaValidada);
				System.out.println("Digíte a Marca do Veículo");
				String marcaValidado = validaVeiculos.marcaValidado(scanner.nextLine());
				caminhaoDto.setMarca(marcaValidado);
				System.out.println("Digíte o Modelo do Veículo");
				String modeloValidado = validaVeiculos.modeloValidado(scanner.nextLine());
				caminhaoDto.setModelo(modeloValidado);
				System.out.println("Digíte o Ano do Veículo");
				Integer anoValidado = validaVeiculos.anoValidado(scanner.nextLine());
				caminhaoDto.setAnoDeFabricao(anoValidado);
				System.out.println(new AgenciaService().listar());
				System.out.println("Digíte o ID da agência");
				Agencia AgenciaValidado = validaVeiculos.idAgenciaValidado(scanner.nextLine());
				caminhaoDto.setAgencia(AgenciaValidado);
				caminhaoNovo = caminhaoDto.adicionar();
				System.out.println(caminhaoNovo);
				System.out.println("\nConfirma as Informações acima?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = false;
					case "2" -> System.out.println("Cadastro Cancelado!!!");
					default -> System.out.println("Cadastro Cancelado!!!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
		new CaminhaoController().alterarVeiculo(caminhaoNovo, caminhaoNovo.getPlaca());
		
	}
	
	public void buscarPorNome() {
		Boolean saida = true;
		String parteVeiculo;
		CarroService carroService = new CarroService();
		MotoService motoService = new MotoService();
		CaminhaoService caminhaoService = new CaminhaoService();
	
		do {
			System.out.println("Digíte o modelo do veículo ou parte dele:");
			try {
				 parteVeiculo = new ValidaVeiculos().localizaParteVeiculo(scanner.nextLine());
					List<Carro> carros = carroService.localizarPorParteDoNome(parteVeiculo);
					List<Moto> motos = motoService.localizarPorParteDoNome(parteVeiculo);
					List<Caminhao> caminhoes = caminhaoService.localizarPorParteDoNome(parteVeiculo);
					if(carros.isEmpty() && motos.isEmpty() && caminhoes.isEmpty()) System.out.println("Nenhuma referência encontrada com esses dados\n");
					if(!carros.isEmpty()) System.out.println(carros+"\n");
					if(!motos.isEmpty()) System.out.println(motos+"\n");
					if(!caminhoes.isEmpty()) System.out.println(caminhoes+"\n");
					System.out.println("\nDeseja Fazer uma nova Pesquisa?");
					System.out.println("1-Sim\n2-Não");
					switch(scanner.nextLine()) {
						case "1" -> saida = true;
						case "2" -> saida = false;
						default -> System.out.println("Ops, opção inválida!");
					}
					
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
		
	}
	
	public void buscarPorEmplacamento() {
		Boolean saida = true;
		String placa;
		CarroService carroService = new CarroService();
		MotoService motoService = new MotoService();
		CaminhaoService caminhaoService = new CaminhaoService();
	
		do {
			System.out.println("Digíte o emplacamento do veículo com o hífen:");
			try {
				placa = new ValidaVeiculos().placaLocalizado(scanner.nextLine());
					List<Carro> carros = carroService.localizar(placa);
					List<Moto> motos = motoService.localizar(placa);
					List<Caminhao> caminhoes = caminhaoService.localizar(placa);
					if(!carros.isEmpty()) System.out.println(carros+"\n");
					if(!motos.isEmpty()) System.out.println(motos+"\n");
					if(!caminhoes.isEmpty()) System.out.println(caminhoes+"\n");
					System.out.println("\nDeseja Fazer uma nova Pesquisa?");
					System.out.println("1-Sim\n2-Não");
					switch(scanner.nextLine()) {
						case "1" -> saida = true;
						case "2" -> saida = false;
						default -> System.out.println("Ops, opção inválida!");
					}
					
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
	}
	public void removerVeículo() {
		Boolean saida = true;
		String placa;
		CarroService carroService = new CarroService();
		MotoService motoService = new MotoService();
		CaminhaoService caminhaoService = new CaminhaoService();
	
		do {
			System.out.println("Digíte o emplacamento do veículo com o hífen:");
			try {
				placa = new ValidaVeiculos().placaLocalizado(scanner.nextLine());
					carroService.remover(placa);
					motoService.remover(placa);
					caminhaoService.remover(placa);
					System.out.println("\nDeseja Remover um novo Veículo?");
					System.out.println("1-Sim\n2-Não");
					switch(scanner.nextLine()) {
						case "1" -> saida = true;
						case "2" -> saida = false;
						default -> System.out.println("Ops, opção inválida!");
					}
					
			} catch (Exception e) {
				System.out.println(e.getMessage()+"\n");
			} 
			
		}while(saida);
		
	}
	// FIM DOS METODOS DO MENU VEICULO
	
	// INICIO DOS METODOS MENU AGENCIA
    public void menuAgencia() {
    	new AgenciaController().atualizaAgencia();
		Boolean continuarMenu = true;
		System.out.println("..:    Menu Agencia   :..");
		do {
			System.out.println("Escolha uma opção para realizar abaixo: ");
			System.out.println("1 - Cadastrar a agência");
			System.out.println("2 - Alterar a agência");
			System.out.println("3 - Buscar agência por filtro de pesquisa");
			System.out.println("0 - Sair");
			switch(scanner.nextLine()) {
			    case "1" -> cadastrarAgencia();
			    case "2" -> alterarAgencia();
				case "3" -> buscarAgenciaPorFiltro();
				case "0" -> continuarMenu = false;
				default -> System.out.println("Ops, opção inválida!");
			}

		}while(continuarMenu);
	}
    
    public void cadastrarAgencia() {
    	Boolean saida = true;
    	AgenciaDto agenciaDto = new AgenciaDto();
    	ValidaEntrada entradaValidada = new ValidaEntrada();
    	do {
    		try {
    			System.out.println("..:    Cadastro de Agencia   :..");
        		System.out.println("Digíte o Nome da Agência: ");
        		String nomeAgenciaValidado = entradaValidada.validaEntrada(scanner.nextLine());
        		agenciaDto.setNomeAgencia(nomeAgenciaValidado);
        		System.out.println("Digíte o Logradouro da Agência:");
        		String logradouroValidado = entradaValidada.validaEntrada(scanner.nextLine());
        		agenciaDto.setLogradouro(logradouroValidado);
        		System.out.println("Digíte o Numero do Logradouro da Agência:");
        		String numeroAgenciaValidado = entradaValidada.validaEntrada(scanner.nextLine());
        		agenciaDto.setNumero(numeroAgenciaValidado);
        		System.out.println("Digíte a Sigla do Estado Federativo da Agência:");
        		SiglasEstados validaEstadoFederativo = entradaValidada.validaEstadoFederativo(scanner.nextLine());
        		agenciaDto.setSiglasEstados(validaEstadoFederativo);
        		Agencia agencia = agenciaDto.adicionar();
        		new AgenciaController().salvar(agencia);
        		System.out.println("\nDeseja Cadastar uma nova Agência?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = true;
					case "2" -> saida = false;
					default -> System.out.println("Ops, opção inválida!");
				}
        		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
    	}while(saida);
    }
    
    public void alterarAgencia() {
    	Boolean saida = true;
    	AgenciaDto agenciaDto = new AgenciaDto();
    	ValidaEntrada entradaValidada = new ValidaEntrada();
    	do {
    		try {
    			System.out.println("..:    Atualização de Agencia   :..");
    			System.out.println("Digíte o Id da Agência: ");
    			Integer idAgencia = entradaValidada.validaIdAgencia(scanner.nextLine());
        		System.out.println("Digíte o Nome da Agência: ");
        		String nomeAgenciaValidado = entradaValidada.validaEntrada(scanner.nextLine());
        		agenciaDto.setNomeAgencia(nomeAgenciaValidado);
        		System.out.println("Digíte o Logradouro da Agência:");
        		String logradouroValidado = entradaValidada.validaEntrada(scanner.nextLine());
        		agenciaDto.setLogradouro(logradouroValidado);
        		System.out.println("Digíte o Numero do Logradouro da Agência:");
        		String numeroAgenciaValidado = entradaValidada.validaEntrada(scanner.nextLine());
        		agenciaDto.setNumero(numeroAgenciaValidado);
        		System.out.println("Digíte a Sigla do Estado Federativo da Agência:");
        		SiglasEstados validaEstadoFederativo = entradaValidada.validaEstadoFederativo(scanner.nextLine());
        		agenciaDto.setSiglasEstados(validaEstadoFederativo);
        		Agencia agencia = agenciaDto.editar();
        		new AgenciaController().editarAgencia(idAgencia, agencia);
        		System.out.println("\nDeseja Atualizar uma nova Agência?");
				System.out.println("1-Sim\n2-Não");
				switch(scanner.nextLine()) {
					case "1" -> saida = true;
					case "2" -> saida = false;
					default -> System.out.println("Ops, opção inválida!");
				}
        		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
    	}while(saida);
    	
    }

	public void buscarAgenciaPorFiltro() {

		System.out.println("..:    Busca de Agencia   :..");
		try{
			System.out.println("Digite o termo para pesquisar: ");
			String termoPesquisa = scanner.nextLine();
			List<Agencia> agenciasEncontradas = new AgenciaService().buscarAgenciaPorNomeLogradouro(termoPesquisa);
			System.out.println("Total de agencias encontradas: " + agenciasEncontradas.size());
			if(agenciasEncontradas.isEmpty()) {
				System.out.println("Não foram encontradas agencias por esse termo.");
			} else {
				System.out.println("Abaixo as agencias encontradas: ");
				for (Agencia agencia : agenciasEncontradas) {
					System.out.println(agencia);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n");
		}
	}
	
	// FIM DOS METODOS DO MENU AGÊNCIA
	
    public void menuClientes() {
		do {
			;
			System.out.println("1 - Cadastrar Pessoa Física");
			System.out.println("2 - Cadastrar Pessoa Jurídica");
			System.out.println("3 - Alterar Pessoa Física");
			System.out.println("4 - Alterar Pessoa Jurídica");
			System.out.println("0 - Sair");

			switch(scanner.nextLine()) {
				case "1" -> this.adicionarPessoaFisica();
				case "2" -> this.adicionarPessoaJuridica();
				case "3" -> this.alterarPessoaFisica();
				case "4" -> this.menuRegistrarAluguel();
				case "0" -> {System.out.println("Obrigado por utilizar nosso sistema :)");
					retornoMenu = false;}
				default -> System.out.println("Ops, opção inválida!");
			}
		}while(retornoMenu);
		
	}
	public void adicionarPessoaFisica(){
		boolean sair = true;

		do {
			PessoaFisica pessoaFisica = new PessoaFisica();

			System.out.println("Digite seu nome completo");
			pessoaFisica.setNomeCliente(scanner.nextLine());

			System.out.println("Digite seu CPF");
			pessoaFisica.setCpf(scanner.nextLine());

			List<PessoaFisica> listaPessoaFisicas = new PessoaFisicaService().listarCliente();
			listaPessoaFisicas.add(pessoaFisica);
			new PessoaFisicaService().adicionarCliente(listaPessoaFisicas);

			System.out.println("Deseja realizar outra cadastro?");
			System.out.println("1 - sim!");
			System.out.println("2 - não!");

			switch (scanner.nextLine()){
				case "1" -> System.out.println("Digite outro Cliente!");
				default -> sair = false;
			}
		}while (sair);
	}

	public void adicionarPessoaJuridica(){
		boolean sair = true;

		do {
			PessoaJuridica pessoaJuridica = new PessoaJuridica();

			System.out.println("Digite seu nome completo");
			pessoaJuridica.setNomeCliente(scanner.nextLine());

			System.out.println("Digite seu CNPJ");
			pessoaJuridica.setCnpj(scanner.nextLine());

			List<PessoaJuridica> listaPessoaJuridica = new PessoaJuridicaService().listarCliente();
			listaPessoaJuridica.add(pessoaJuridica);
			new PessoaJuridicaService().adicionarCliente(listaPessoaJuridica);


			System.out.println("Deseja realizar outra cadastro?");
			System.out.println("1 - sim!");
			System.out.println("2 - não!");

			switch (scanner.nextLine()){
				case "1" -> System.out.println("Digite outro Cliente!");
				default -> sair = false;
			}
		}while (sair);
	}

	public void alterarPessoaFisica(){
		boolean sair = true;
		PessoaFisica pessoaFisica = new PessoaFisica();

		do {
			System.out.println("Digite o CPF!");
			String cpf = scanner.nextLine();
			PessoaFisicaService pessoaService = new PessoaFisicaService();
			List<PessoaFisica> listaPessoaFisicas = pessoaService.localizarCliente(cpf);


			if (listaPessoaFisicas.isEmpty() ) {
				System.out.println("CPF não encontrado!");
			}else {
				System.out.println("Digite o nome do cliente!");
				pessoaFisica.setNomeCliente(scanner.nextLine());
				pessoaFisica.setCpf(cpf);
				List<PessoaFisica> pessoaFisicas = pessoaService.listarCliente();
				System.out.println(pessoaFisicas);

				for (int i = 0; i < pessoaFisicas.size(); i++){
					if (pessoaFisicas.get(i).getCpf().equals(cpf)){
						pessoaFisicas.set(i, pessoaFisica);
					}
				}
				pessoaService.adicionarCliente(pessoaFisicas);
			}

			new PessoaFisicaService().adicionarCliente(listaPessoaFisicas);

			System.out.println("Deseja realizar outra cadastro?");
			System.out.println("1 - sim!");
			System.out.println("2 - não!");

			switch (scanner.nextLine()){
				case "1" -> System.out.println("Digite outro Cliente!");
				default -> sair = false;
			}
		}while (sair);
	}
    public void menuRegistrarAluguel() {
		do {
			System.out.println("Menu Principal");
			System.out.println("1 - Veículos");
			System.out.println("2 - Agencias");
			System.out.println("3 - Clientes");
			System.out.println("4 - Registrar Aluguel");
			System.out.println("0 - Sair");

			switch(scanner.nextLine()) {
				case "1" -> this.menuVeiculos();
				case "2" -> this.menuAgencia();
				case "3" -> this.menuClientes();
				case "4" -> this.menuRegistrarAluguel();
				case "0" -> {System.out.println("Obrigado por utilizar nosso sistema :)");
					retornoMenu = false;}
				default -> System.out.println("Ops, opção inválida!");
			}
		}while(retornoMenu);
	}

}
