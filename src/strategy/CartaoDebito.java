package strategy;

import java.math.BigDecimal;

public class CartaoDebito implements Pagamento {
	
	private final BigDecimal valor;
	
	public CartaoDebito(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public BigDecimal taxa() {
		return BigDecimal.valueOf(0.01);
	}
	
	@Override
	public BigDecimal valorApagar() {
	    return valor.multiply(BigDecimal.ONE.add(taxa()));
	}
}
