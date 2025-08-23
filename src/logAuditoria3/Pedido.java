package logAuditoria3;

public class Pedido {
	@LogComparacao(nome = "C�digo do pedido")
	private int codigo;

	@LogComparacao(nome = "Descri��o do pedido")
	private String descricao;

	public Pedido() {
	}

	public Pedido(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	// Getters e setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
