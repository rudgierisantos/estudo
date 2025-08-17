package LogAuditoria;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<String, String> mapP1 = new HashMap<>();
		mapP1.put("cor", "preto");
		mapP1.put("peso", "2kg");

		Map<String, String> mapP2 = new HashMap<>();
		mapP1.put("cor", "vermelho");
		mapP1.put("peso", "2kg");
		
		Produto p1 = new Produto("Notebook", new BigDecimal("2500.00"), Arrays.asList("eletronico", "portatil"), mapP1);

		Produto p2 = new Produto("Notebook Gamer", new BigDecimal("2500.0"), Arrays.asList("portatil", "eletronico", "gamer"), mapP2);
		
		
		List<Produto> produtos = Arrays.asList(p1, p1);
		
		List<Produto> produtos2 = Arrays.asList(p2, p2);


		LogService audit = new LogService();
		List<Log> diferencas = audit.comparar(produtos, produtos2, "produtos");

		diferencas.forEach(System.out::println);
	}

}
