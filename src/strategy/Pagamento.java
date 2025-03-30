package strategy;

import java.math.BigDecimal;

public interface Pagamento {
	BigDecimal taxa();
	BigDecimal valorApagar();
}
