package logAuditoria3;

import java.util.List;

public class Cliente {
	@LogComparacao(nome = "Nome do cliente")
	private String nome;

	@LogComparacao(nome = "Telefones")
	private List<String> telefones;

	@LogComparacao(nome = "Pedidos")
	private List<Pedido> pedidos;

	// Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
