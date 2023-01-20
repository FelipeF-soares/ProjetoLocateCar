package br.com.locatecar.grupoii.veiculos.util;

import java.util.List;

public interface CrudVeiculos<T> {

	public void adicionar(List<T> veiculos);
	
	public List<T> listar();
	
	public List<T> localizar(String placa);

	public void remover(String placa);

	public void editar(String placa,T veiculo);
	
	public List<T> localizarPorParteDoNome(String modelo);
	

}
