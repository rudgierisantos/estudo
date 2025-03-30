package strategy;

import java.math.BigDecimal;

public class Main {

	public static void main(String[] args) {
		
		BigDecimal valor = BigDecimal.valueOf(100);
		
		ProcessarPagamento credito = new ProcessarPagamento(new CartaoCredito(valor));
		System.out.println(credito.obterValorTotalComTaxa());
		
		ProcessarPagamento debito = new ProcessarPagamento(new CartaoDebito(valor));
		System.out.println(debito.obterValorTotalComTaxa());
		
		for (TipoPagamentoEnum tipoPagamento : TipoPagamentoEnum.values()) {
			Pagamento pagamento = tipoPagamento.criar(valor);
			System.out.println("------- " + pagamento.valorApagar() + " ---------");
		}
		
		Pagamento pagamento = TipoPagamentoEnum.CREDITO.criar(valor);
		System.out.println(pagamento.valorApagar());

	}

}
