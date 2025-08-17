package LogAuditoria;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Produto {

	@GravaLog
	private String nome;

	@GravaLog
	private BigDecimal preco;

	@GravaLog
	private List<String> tags;

	@GravaLog
	private Map<String, String> atributos;

	public Produto(String nome, BigDecimal preco, List<String> tags, Map<String, String> atributos) {
		this.nome = nome;
		this.preco = preco;
		this.tags = tags;
		this.atributos = atributos;
	}
}
