package br.com.locatecar.grupoii.erros;

public class ErroVeiculoJaCadastrado extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "!!!ERRO!!! \nO Veículo Já Encontra-se Cadastardo em nosso Banco de Dados";
	}
}
