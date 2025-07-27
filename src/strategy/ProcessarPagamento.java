package strategy;

import java.math.BigDecimal;

public class ProcessarPagamento {
	
	Pagamento pagamento;
	
	public ProcessarPagamento(Pagamento pagamento) {
		// apenas se tiver l�gica al�m do retorno do c�lculo de cada tipo de pagamento
		this.pagamento = pagamento;
	}
	
	public BigDecimal obterValorTotalComTaxa() {
		return pagamento.valorApagar();
	}
}
