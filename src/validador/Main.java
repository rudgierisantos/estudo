package validador;

public class Main {

	public static void main(String[] args) throws ValidacaoCampoException {
		
		Validador validador = new ValidadorBuilder()
//				.campo("nome","")
//					.naoNuloVazio("campo obrigatório")
				.lista("email", null)
					.naoNula()
				.build();
		
		validador.validarComExcecao();
		
		System.out.println(validador.validar());

	}

}
