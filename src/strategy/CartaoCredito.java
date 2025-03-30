package strategy;

import java.math.BigDecimal;

public class CartaoCredito implements Pagamento {
	
	private BigDecimal valor;
	
	public CartaoCredito(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public BigDecimal taxa() {
		return BigDecimal.valueOf(0.03);
	}
	
	@Override
	public BigDecimal valorApagar() {
	    return valor.multiply(BigDecimal.ONE.add(taxa()));
	}

}
