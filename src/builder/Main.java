package builder;

public class Main {

	public static void main(String[] args) {
		Objeto obj = Objeto.builder()
				.comNome("Rudgieri")
				.comCpf("154.587.589-85")
				.comEndereco("Rua teste 123")
				.build();
		
		System.out.println(obj);
	}

}
