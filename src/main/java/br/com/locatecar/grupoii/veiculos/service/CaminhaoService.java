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

import br.com.locatecar.grupoii.veiculos.model.Caminhao;
import br.com.locatecar.grupoii.veiculos.util.CrudVeiculos;

public class CaminhaoService implements CrudVeiculos<Caminhao> {
	static Path path = Paths.get("D:\\Ada\\SantanderCoder\\ModuloIII\\Projetos\\LocateCar\\locatecar\\src\\main\\java\\arquivos\\caminhao.txt");

	@Override
	public void adicionar(List<Caminhao> veiculos) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String saidaVeiculosJson = gson.toJson(veiculos);
		
		try{
            if(!Files.exists(path)){
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            Files.writeString(path, saidaVeiculosJson);
		}catch (Exception e) {
			System.out.println("Não foi possível cadastrar o veículos!");
		}    
		
	}
	
	@Override
	public List<Caminhao> listar() {
		List<Caminhao> caminhoes = new ArrayList<Caminhao>();
		Gson gson = new Gson();
		String linha = "";
		try {
			if(!Files.exists(path)) {
				Files.createFile(path);
				Files.writeString(path, "[]");
			}
			linha = Files.readString(path);
		}catch (Exception e) {
			System.out.println("Lista Não encontrada");
			return caminhoes;
		}
		Type tipoDelista = new TypeToken<ArrayList<Caminhao>>(){}.getType();
		caminhoes = gson.fromJson(linha, tipoDelista);
		
		return caminhoes;
	}

	@Override
	public List<Caminhao> localizar(String placa) {
		List<Caminhao> veiculos = this.listar();
		List<Caminhao> veiculosLocalizados = new ArrayList<Caminhao>();
		for(int i = 0; i< veiculos.size(); i++) {
			if(veiculos.get(i).getPlaca().equals(placa)){
				Caminhao caminhao = veiculos.get(i);
				veiculosLocalizados.add(caminhao);
				}
		}	
		return veiculosLocalizados;
	}

	@Override
	public void remover(String placa) {
		List<Caminhao> veiculosLocalizados = this.localizar(placa);
		List<Caminhao> veiculos = this.listar();
		
		if(veiculosLocalizados.isEmpty()) {
			System.out.println("Não Foi Localizado Nenhum Veiculo Com essa placa para Exclusão");
		}else {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			for(int i = 0; i < veiculos.size(); i++ ) {
				for(int j = 0; j < veiculosLocalizados.size(); j++) {
					if(veiculos.get(i).getPlaca().equals(veiculosLocalizados.get(j).getPlaca())) {
						veiculos.remove(i);
					}
				}
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
	}

	@Override
	public void editar(String placa, Caminhao veiculo) {
		List<Caminhao> listar = this.listar();
		for(int i = 0; i<listar.size();i++) {
			if(listar.get(i).getPlaca().equals(placa)) {
				listar.set(i, veiculo);
			}
		}
		this.adicionar(listar);
	}

	@Override
	public List<Caminhao> localizarPorParteDoNome(String modelo) {
		List<Caminhao> listaDeCorrespondentes = new ArrayList<Caminhao>();
		List<Caminhao> caminhoes = this.listar();
		for(Caminhao caminhao : caminhoes) {
			if(caminhao.getModelo().contains(modelo)) {
				listaDeCorrespondentes.add(caminhao);
			}
		}
		
		return listaDeCorrespondentes;
		
	}

}
