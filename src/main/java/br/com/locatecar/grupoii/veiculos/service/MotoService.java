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

import br.com.locatecar.grupoii.veiculos.model.Moto;
import br.com.locatecar.grupoii.veiculos.util.CrudVeiculos;

public class MotoService implements CrudVeiculos<Moto> {
	static Path path = Paths.get("D:\\Ada\\SantanderCoder\\ModuloIII\\Projetos\\LocateCar\\locatecar\\src\\main\\java\\arquivos\\motos.txt");
	@Override
	public void adicionar(List<Moto> veiculos) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String saidaVeiculosJson = gson.toJson(veiculos);
		
		try{
            if(!Files.exists(path)){
                Files.createFile(path);
                Files.writeString(path, "[]");
            }
            Files.writeString(path, saidaVeiculosJson);
		}catch (Exception e) {
			System.out.println("Não foi possível cadastrar o veículo!");
		}    
		
	}
	
	@Override
	public List<Moto> listar() {
		List<Moto> motos = new ArrayList<Moto>();
		Gson gson = new Gson();
		String linha = "";
		try {
			if(!Files.exists(path)) {
				Files.createFile(path);
				Files.writeString(path, "[]");
			}
			linha = Files.readString(path);
		}catch(Exception e) {
			System.out.println("Lista Não Encontrada");
			return motos;
		}
		
		Type tipoDelinha = new TypeToken<ArrayList<Moto>>(){}.getType();
		motos = gson.fromJson(linha, tipoDelinha);
		return motos;
		
	}

	@Override
	public List<Moto> localizar(String placa) {
		List<Moto> veiculos = this.listar();
		List<Moto> veiculosLocalizados = new ArrayList<Moto>();
		for(int i = 0; i< veiculos.size(); i++) {
			if(veiculos.get(i).getPlaca().equals(placa)){
				Moto moto = veiculos.get(i);
					veiculosLocalizados.add(moto);
				}
		}	
		return veiculosLocalizados;
	}

	@Override
	public void remover(String placa) {
		List<Moto> veiculosLocalizados = this.localizar(placa);
		List<Moto> veiculos = this.listar();
		
		if(veiculosLocalizados.isEmpty()) {
			System.out.println("Não Foi Localizado Nenhum Veiculo Com essa placa para Exclusão");
		}else{
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
	public void editar(String placa, Moto veiculo) {
		List<Moto> listar = this.listar();
		for(int i =0; i<listar.size(); i++) {
			if(listar.get(i).getPlaca().equals(placa)) {
				listar.set(i, veiculo);
			}
		}
		this.adicionar(listar);
	}

	@Override
	public List<Moto> localizarPorParteDoNome(String modelo) {
		List<Moto> listaDeCorrespondentes = new ArrayList<Moto>();
		List<Moto> motos = this.listar();
		for(Moto moto : motos) {
			if(moto.getModelo().contains(modelo)) {
				listaDeCorrespondentes.add(moto);
			}
		}
		
		return listaDeCorrespondentes;
		
	}

	
}
