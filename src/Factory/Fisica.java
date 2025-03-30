package Factory;

public class Fisica extends Pessoa  {
	
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Fisica [cpf=" + cpf + ", nome=" + getNome() + ", sobrenome=" + getSobrenome() + "]";
	}
}
