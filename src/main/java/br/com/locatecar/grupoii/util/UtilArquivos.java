package br.com.locatecar.grupoii.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UtilArquivos {

    private static final String OBJETO_VAZIO = "[]";

    public static <T> void salvarArquivo(String caminho, List<T> listaObjetosSalvar) {

        Path path = Paths.get(caminho);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String saidaObjetoJson = gson.toJson(listaObjetosSalvar);

        try {
            if(!Files.exists(path)){
                criarArquivoVazio(path);
            }
            Files.writeString(path, saidaObjetoJson);

        } catch (Exception e) {
            System.err.println("ERRO AO SALVAR ARQUIVO!");
            System.err.println(e.getMessage());
        }
    }

    public static <T> List<T> lerArquivo(String caminho, Class<T> tipoObjeto) {

        List<T> objetosLidos = new ArrayList<>();
        Path path = Paths.get(caminho);
        Gson gson = new Gson();
        String conteudoArquivo = "";

        try {
            if(!Files.exists(path)) {
                criarArquivoVazio(path);
            }
            conteudoArquivo = Files.readString(path);
            objetosLidos = gson.fromJson(conteudoArquivo, new ArrayList<T>().getClass());
        } catch (Exception e) {
            System.err.println("ERRO AO LER ARQUIVO!");
            System.err.println(e.getMessage());
        }

        return objetosLidos;
    }

    private static void criarArquivoVazio(Path path) throws IOException {
        Files.createFile(path);
        Files.writeString(path, OBJETO_VAZIO);
    }

}
