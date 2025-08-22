package LogAuditoria2;

import java.util.List;

public class Pessoa {

	@LogField(label = "Nome", section = "Dados Pessoais")
	private String nome;

	@LogField(label = "Idade", section = "Dados Pessoais")
	private int idade;

	@LogField(label = "Cidade", section = "Endereço")
	private String cidade;

	@LogField(label = "Telefones", section = "Contatos")
	private List<String> telefones;
	
	@LogField(label = "Telefones", section = "Contatos")
	private List<Telefone> telefonesObj;

	public Pessoa(String nome, int idade, String cidade, List<String> telefones, List<Telefone> telefonesObj) {
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
		this.telefones = telefones;
		this.telefonesObj = telefonesObj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<Telefone> getTelefonesObj() {
		return telefonesObj;
	}

	public void setTelefonesObj(List<Telefone> telefonesObj) {
		this.telefonesObj = telefonesObj;
	}
}
