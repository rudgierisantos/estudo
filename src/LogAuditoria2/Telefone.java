package LogAuditoria2;

public class Telefone {
	
	@LogField(label = "Celular", section = "Endereço")
	private String celular;
	
	@LogField(label = "Fixo", section = "Endereço")
	private String fixo;

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	public Telefone(String celular, String fixo) {
		this.celular = celular;
		this.fixo = fixo;
	}
}
