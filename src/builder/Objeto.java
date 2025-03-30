package builder;

public class Objeto {
	private String nome;
	private String cpf;
	private String endereco;
	
	public Objeto(ObjetoBuilder objetoBuilder) {
		this.nome = objetoBuilder.getNome();
		this.cpf = objetoBuilder.getCpf();
		this.endereco = objetoBuilder.getEndereco();
	}

	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	@Override
	public String toString() {
		return "Objeto [nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + "]";
	}
	public static ObjetoBuilder builder() {
		return new ObjetoBuilder();
	}
}
