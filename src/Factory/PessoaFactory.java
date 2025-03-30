package Factory;

public interface PessoaFactory {
    public abstract Pessoa criarPessoa();
    
    public default String getNomeBatata() {
    	return "batata";
    }
}
