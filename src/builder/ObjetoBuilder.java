package builder;

public class ObjetoBuilder {
	private String nome;
	private String cpf;
	private String endereco;
	
	public ObjetoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public ObjetoBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public ObjetoBuilder comEndereco(String endereco) {
		this.endereco = endereco;
		return this;
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
	
	public Objeto build() {
		return new Objeto(this);
	}
}
