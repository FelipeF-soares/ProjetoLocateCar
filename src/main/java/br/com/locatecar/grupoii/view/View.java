package br.com.locatecar.grupoii.view;

import java.util.List;
import java.util.Scanner;

import br.com.locatecar.grupoii.agencia.model.Agencia;
import br.com.locatecar.grupoii.agencia.service.AgenciaService;
import br.com.locatecar.grupoii.erros.ErroDeValorInvalidoDaLista;
import br.com.locatecar.grupoii.erros.ErroPlacaNaoLocalizada;
import br.com.locatecar.grupoii.erros.ErroVeiculoJaCadastrado;
import br.com.locatecar.grupoii.erros.ValorNumericoInvalido;
import br.com.locatecar.grupoii.erros.ValorVazioOuNulo;
import br.com.locatecar.grupoii.veiculos.controller.CarroController;
import br.com.locatecar.grupoii.veiculos.dto.CarroDto;
import br.com.locatecar.grupoii.veiculos.model.Carro;
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
			} catch (ValorVazioOuNulo e) {
				System.out.println(e.getMessage()+"\n");
			} catch (ErroVeiculoJaCadastrado e) {
				System.out.println(e.getMessage()+"\n");
			} catch (ValorNumericoInvalido e) {
				System.out.println(e.getMessage()+"\n");
			} catch(ErroDeValorInvalidoDaLista e) {
				System.out.println(e.getMessage()+"\n");
			}
			
		}while(saida);
		
		new CarroController<Carro>().salvarVeiculo(novoCarro);
	}
	
	public void cadastraMoto() {
		
	}
	public void cadastraCaminhao() {
		
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
			} catch (ValorVazioOuNulo e) {
				System.out.println(e.getMessage()+"\n");
			} catch (ErroPlacaNaoLocalizada e) {
				System.out.println(e.getMessage()+"\n");
			} catch (ValorNumericoInvalido e) {
				System.out.println(e.getMessage()+"\n");
			} catch(ErroDeValorInvalidoDaLista e) {
				System.out.println(e.getMessage()+"\n");
			}
			
		}while(saida);
		new CarroController<Carro>().alterarVeiculo(novoCarro, novoCarro.getPlaca());
	}
	
	public void alterarMoto() {
		
	}
	
	public void alterarCaminhao() {
		
	}
	
	public void buscarPorNome() {
		
	}
	
	public void buscarPorEmplacamento() {
		
	}
	public void removerVeículo() {
		
	}
	// FIM DOS METODOS DO MENU VEICULO
	
	// INICIO DOS METODOS MENU AGENCIA
    public void menuAgencia() {
		Boolean continuarMenu = true;
		System.out.println("..:    Menu Agencia   :..");
		do {
			System.out.println("Escolha uma opção para realizar abaixo: ");
			System.out.println("1 - Buscar agência por filtro de pesquisa");
			System.out.println("0 - Sair");
			switch(scanner.nextLine()) {
				case "1" -> buscarAgenciaPorFiltro();
				case "0" -> continuarMenu = false;
				default -> System.out.println("Ops, opção inválida!");
			}

		}while(continuarMenu);
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

    public void menuClientes() {
		
	}
    public void menuRegistrarAluguel() {
		
	}

}
