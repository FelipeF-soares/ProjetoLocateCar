package br.com.locatecar.grupoii.agencia.service;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.locatecar.grupoii.agencia.model.Agencia;

public class AgenciaService {
	
	static Path path = Paths.get("D:\\Ada\\SantanderCoder\\ModuloIII\\Projetos\\LocateCar\\locatecar\\src\\main\\java\\arquivos\\listaDeAgencias.txt");
	
	public void adicionar(List<Agencia> agencias) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String saidaAgenciaJson = gson.toJson(agencias);
		
		try {
			if(!Files.exists(path)) {
				Files.createFile(path);
                Files.writeString(path, "[]");
			}
			Files.writeString(path, saidaAgenciaJson);
		}catch(Exception e) {
			System.out.println("Não foi possível cadastrar a Agência!");
		}
		
	}

	public List<Agencia> listar(){
		List<Agencia> agencias = new ArrayList<Agencia>();
		Gson gson = new Gson();
		String linha = "";
		try {
			if(!Files.exists(path)) {
				Files.createFile(path);
				Files.writeString(path, "[]");
			}
			linha = Files.readString(path);
		}catch(Exception e) {
			System.out.println("Lista Não Encotrada");
			return agencias;
		}
		Type tipoDeLista = new TypeToken<ArrayList<Agencia>>(){}.getType();
		agencias = gson.fromJson(linha, tipoDeLista);
		
		return agencias;
		
	}
	
	public List<Agencia> buscarAgenciaPorNomeLogradouro(String pesquisa){
		List<Agencia> listaDeCorrespondentes = new ArrayList<Agencia>();
		List<Agencia> agencias = this.listar();
		for(Agencia agencia : agencias) {
			if(agencia.getEndereco().getLogradouro().contains(pesquisa) || agencia.getNomeAgencia().contains(pesquisa)) {
				listaDeCorrespondentes.add(agencia);
			}
		}
		
		return listaDeCorrespondentes;
	}
}


