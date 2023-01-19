package br.com.locatecar.grupoii.erros;

public class ErroDeAgenciaNaoLocalizada extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "!!!ERRO!!! \nA Agencia Não Encontra-se Cadastrado em nosso Banco de Dados";
	}
}
