package LogAuditoria2;

public class Telefone {
	
	public Telefone(String celular, String fixo) {
		this.celular = celular;
		this.fixo = fixo;
	}
	
	public Telefone() {
		// TODO Auto-generated constructor stub
	}
	
	private String celular;
	
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
}
