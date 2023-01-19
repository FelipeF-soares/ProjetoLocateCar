package br.com.locatecar.grupoii.erros;

public class ErroDeValorInvalidoDaLista extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "!!!ERRO!!! \nA O valor Informado não é valido!";
	}
}
