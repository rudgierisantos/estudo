package validador;

import java.util.ArrayList;
import java.util.List;

public class ListaBuilder<T> {
	final ValidadorBuilder validador;
	private final String nome;
	private final List<T> list;
	private final List<RegraValidacao> regras = new ArrayList<>();
	
    public ListaBuilder(ValidadorBuilder validador, String nome, List<T> list) {
        this.validador = validador;
        this.nome = nome;
        this.list = list;
    }
    
    public ListaBuilder<T> naoNula() {
    	naoNula("não deve ser nulo");
        return this;
    }
    
    public ListaBuilder<T> naoNula(String mensagem) {
        regras.add(new RegraValidacao(nome, () -> list != null, mensagem));
        return this;
    }
    
    public Validador build() {
    	regras.forEach(validador::adicionarRegra);
		return validador.build();
	}
}
