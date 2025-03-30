package validador;

import java.util.function.Supplier;

public class RegraValidacao {
	private final String campo;
	private final Supplier<Boolean> condicao;
	private final String mensagem;
	
	public RegraValidacao(String campo, Supplier<Boolean> condicao, String mensagem) {
		this.campo = campo;
		this.condicao = condicao;
		this.mensagem = mensagem;
	}
	
	public boolean validar() {
		try {
			return condicao.get();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getCampo() {
		return campo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
}
