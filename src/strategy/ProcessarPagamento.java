package strategy;

import java.math.BigDecimal;

public class ProcessarPagamento {
	
	Pagamento pagamento;
	
	public ProcessarPagamento(Pagamento pagamento) {
		// apenas se tiver lógica além do retorno do cálculo de cada tipo de pagamento
		this.pagamento = pagamento;
	}
	
	public BigDecimal obterValorTotalComTaxa() {
		return pagamento.valorApagar();
	}
}
