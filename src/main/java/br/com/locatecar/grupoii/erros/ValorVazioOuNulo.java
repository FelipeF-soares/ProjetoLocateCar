package br.com.locatecar.grupoii.erros;

public class ValorVazioOuNulo extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "!!!ERRO!!! \nO valor não pode ser nulo ou vazio para esse parâmetro!";
	}
}
