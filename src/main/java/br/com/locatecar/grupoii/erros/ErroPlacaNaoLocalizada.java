package br.com.locatecar.grupoii.erros;

public class ErroPlacaNaoLocalizada extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "!!!ERRO!!! \nO Veículo não foi localizado!";
	}
}
