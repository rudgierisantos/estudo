package Factory;

public class Main {

	public static void main(String[] args) {
		Pessoa pessoaF = new PessoaFisicaFactory().criarPessoa();
		
		Pessoa pessoaJ = new PessoaJuridicaFactory().criarPessoa();

		System.out.println(pessoaF);
		
		System.out.println(pessoaJ);
	}

}
