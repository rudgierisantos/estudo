package LogAuditoria;

public class Log {

	private String campo;
	private Object valorAnterior;
	private Object novoValor;

	public Log(String campo, Object valorAnterior, Object novoValor) {
		this.campo = campo;
		this.valorAnterior = valorAnterior;
		this.novoValor = novoValor;
	}

	@Override
	public String toString() {
		return String.format("Campo: %s | De: %s | Para: %s", campo, valorAnterior, novoValor);
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Object getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(Object valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public Object getNovoValor() {
		return novoValor;
	}

	public void setNovoValor(Object novoValor) {
		this.novoValor = novoValor;
	}
}
