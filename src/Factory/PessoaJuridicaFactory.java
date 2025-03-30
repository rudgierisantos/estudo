package Factory;

public class PessoaJuridicaFactory implements PessoaFactory {

	@Override
	public Pessoa criarPessoa() {
		Juridica juridica = new Juridica();
		juridica.setNome("LTDA");
		juridica.setSobrenome(getNomeBatata());
		juridica.setCnpj("21.010.506.255-18");
		return juridica;
	}
	
	@Override
	public String getNomeBatata() {
		return "batata LTDA";
	}
}
