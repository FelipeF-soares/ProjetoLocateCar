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
import br.com.locatecar.grupoii.veiculos.model.Caminhao;
import br.com.locatecar.grupoii.veiculos.model.Carro;
import br.com.locatecar.grupoii.veiculos.model.Moto;
import br.com.locatecar.grupoii.veiculos.service.CaminhaoService;
import br.com.locatecar.grupoii.veiculos.service.CarroService;
import br.com.locatecar.grupoii.veiculos.service.MotoService;

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
	
	public Agencia adicionaListaVeiculo( Agencia agencia) {
		List<Carro> listaCarro = new CarroService().listar();
		List<Moto> listaMoto = new MotoService().listar();
		List<Caminhao> listaCaminhao = new CaminhaoService().listar();
		
		List<Carro> listaDeCarrosEncontrados = new ArrayList<Carro>();
		List<Moto> listaDeMotosEncontrados = new ArrayList<Moto>();
		List<Caminhao> listaDeCaminhaoEncontrado = new ArrayList<Caminhao>();
		
		for(int i = 0; i <listaCarro.size(); i++) {
			if(listaCarro.get(i).getIdAgencia().equals(agencia.getId())){
				listaDeCarrosEncontrados.add(listaCarro.get(i));
			}
		}
		
		for(int i = 0; i < listaMoto.size(); i++) {
			if(listaMoto.get(i).getIdAgencia().equals(agencia.getId())) {
				listaDeMotosEncontrados.add(listaMoto.get(i));
			}
		}
		
		for(int i = 0; i < listaCaminhao.size();i++) {
			if(listaCaminhao.get(i).getIdAgencia().equals(agencia.getId())) {
				listaDeCaminhaoEncontrado.add(listaCaminhao.get(i));
			}
		}
		
		 agencia.setListaDeCarros(listaDeCarrosEncontrados);
		 agencia.setListaDeMotos(listaDeMotosEncontrados);
		 agencia.setListaCaminhao(listaDeCaminhaoEncontrado);
		 return agencia;
		
		
	}
	
}


