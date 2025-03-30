package validador;

import java.util.List;

public class CampoBuilder<T> {
	private final ValidadorBuilder validador;
	private final String nome;
	private final Object valor;
	
	public CampoBuilder(ValidadorBuilder validador, String nome, Object valor) {
		this.validador = validador;
		this.nome = nome;
		this.valor = valor;
	}
	
	public CampoBuilder<T> naoNuloVazio() {
		naoNuloVazio("não pode ser nulo");
		return this;
	}

	public CampoBuilder<T> naoNuloVazio(String mensagem) {
		validador.adicionarRegra(new RegraValidacao(nome, () -> valor != null && valor != "", mensagem));
		return this;
	}
	
    public <U> ListaBuilder<U> lista(String nome, List<U> list) {
        return validador.lista(nome, list);
    }
	
	public Validador build() {
		return validador.build();
	}
}
