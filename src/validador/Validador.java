package validador;

import java.util.ArrayList;
import java.util.List;

public class Validador {
	private final List<RegraValidacao> regras;
	
	public Validador(List<RegraValidacao> regras) {
		this.regras = regras;
	}
	
	public List<String> validar(){
		List<String> erros = new ArrayList<>();
		for (RegraValidacao regra : regras) {
			if(!regra.validar()) {
				erros.add(regra.getCampo() + ": " + regra.getMensagem());
			}
		}
		return erros;
	}
	
	public void validarComExcecao() throws ValidacaoCampoException {
		for (RegraValidacao regra : regras) {
			if(!regra.validar()) {
				throw new ValidacaoCampoException(regra.getCampo() + ": " + regra.getMensagem());
			}
		}
	}
}
