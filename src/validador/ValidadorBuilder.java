package validador;

import java.util.ArrayList;
import java.util.List;

public class ValidadorBuilder {
	private final List<RegraValidacao> regras = new ArrayList<>();
	
	public <T> CampoBuilder<T> campo(String nome, Object valor){
		return new CampoBuilder<>(this, nome, valor);
	}
	
	public <T> ListaBuilder<T> lista(String nome, List<T> list) {
		return new ListaBuilder<>(this, nome, list);
	}
	
	public ValidadorBuilder adicionarRegra(RegraValidacao regra) {
		regras.add(regra);
		return this;
	}
	
	public Validador build() {
		return new Validador(regras);
	}
}
