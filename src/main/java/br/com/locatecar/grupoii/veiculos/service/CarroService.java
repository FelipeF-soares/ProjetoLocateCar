package br.com.locatecar.grupoii.veiculos.service;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.locatecar.grupoii.erros.ErroPlacaNaoLocalizada;
import br.com.locatecar.grupoii.veiculos.model.Carro;
import br.com.locatecar.grupoii.veiculos.util.CrudVeiculos;

public class CarroService implements CrudVeiculos<Carro> {
	
	static Path path = Paths.get("D:\\Ada\\SantanderCoder\\ModuloIII\\Projetos\\LocateCar\\locatecar\\src\\main\\java\\arquivos\\carros.txt");

	@Override
	public void adicionar(List<Carro> veiculos) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String saidaCarroJson = gson.toJson(veiculos);
		
		try{
            if(!Files.exists(path)){
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            Files.writeString(path, saidaCarroJson);
		}catch (Exception e) {
			System.out.println("Não foi possível cadastrar o veículo!");
		}    
		
	}
	
	@Override
	public List<Carro> listar() {
		List<Carro> carros = new ArrayList<Carro>();
		Gson gson = new Gson();
		String linha = "";
		try {
			if(!Files.exists(path)) {
				Files.createFile(path);
				Files.writeString(path, "[]");
			}
			linha = Files.readString(path);
		}catch (Exception e) {
			System.out.println("Lista Não Encontrada");
			return carros;
		}
		
		Type tipoDeLista = new TypeToken<ArrayList<Carro>>(){}.getType();
		carros = gson.fromJson(linha, tipoDeLista);
		
		return carros;
	}
	
	@Override
	public List<Carro> localizar(String placa) {
		List<Carro> veiculos = this.listar();
		List<Carro> veiculosLocalizados = new ArrayList<Carro>();
		for(int i = 0; i< veiculos.size(); i++) {
			if(veiculos.get(i).getPlaca().equals(placa)){
				Carro carro = veiculos.get(i);
				veiculosLocalizados.add(carro);
				}
		}	
		return veiculosLocalizados;
	}

	@Override
	public void remover(String placa) {
		List<Carro> veiculosLocalizados = this.localizar(placa);
		List<Carro> veiculos = this.listar();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			if(veiculosLocalizados.isEmpty()) {
				throw new ErroPlacaNaoLocalizada();
			}else{
				for(int i = 0; i < veiculos.size(); i++ ) {
					for(int j = 0; j < veiculosLocalizados.size(); j++) {
						if(veiculos.get(i).getPlaca().equals(veiculosLocalizados.get(j).getPlaca())) {
							veiculos.remove(i);
						}
					}
				}
				
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String saidaVeiculoJson = gson.toJson(veiculos);
			try{
	            if(!Files.exists(path)){
	                Files.createFile(path);
	                Files.writeString(path, "[]");
	            }
	            Files.writeString(path, saidaVeiculoJson);
			}catch (Exception e) {
				System.out.println("Não é possível excluir o veiculo");
			}    
			
		}

	@Override
	public void editar(String placa, Carro carro) {
		List<Carro> listaDeLocalizar = this.localizar(placa);
		List<Carro> listar = this.listar();
			for(int i = 0; i<listar.size();i++) {
				if(listar.get(i).getPlaca().equals(placa)) {
				 listar.add(i, carro);
				}
			}
		
		this.adicionar(listaDeLocalizar);
	}

	@Override
	public List<Carro> localizarPorParteDoNome(String modelo) {
		List<Carro> listaDeCorrepondente = new ArrayList<Carro>();
		List<Carro> carros = this.listar();
		for(Carro carro : carros) {
			if(carro.getModelo().contains(modelo)) {
				listaDeCorrepondente.add(carro);
			}
		}
		return listaDeCorrepondente;
	}


}
