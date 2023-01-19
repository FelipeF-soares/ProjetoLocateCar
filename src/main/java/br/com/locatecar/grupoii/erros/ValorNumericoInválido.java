package br.com.locatecar.grupoii.erros;

public class ValorNumericoInválido extends NumberFormatException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "!!!ERRO!!! \nO valor passado para esse parâmetro é inválido!";
	}
}
