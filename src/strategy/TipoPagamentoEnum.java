package strategy;

import java.math.BigDecimal;

public enum TipoPagamentoEnum {

	CREDITO {
		@Override
		public Pagamento criar(BigDecimal valor) {
			return new CartaoCredito(valor);
		}
	},
	DEBITO {
		@Override
		public Pagamento criar(BigDecimal valor) {
			return new CartaoDebito(valor);
		}
	};
	
    public abstract Pagamento criar(BigDecimal valor);
}
