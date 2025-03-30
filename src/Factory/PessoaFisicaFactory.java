package Factory;

public class PessoaFisicaFactory implements PessoaFactory {

	@Override
	public Pessoa criarPessoa() {
		Fisica fisica = new Fisica();
		fisica.setNome("rudgieri");
		fisica.setSobrenome(getNomeBatata());
		fisica.setCpf("454.548.256-75");
		return fisica;
	}
}
