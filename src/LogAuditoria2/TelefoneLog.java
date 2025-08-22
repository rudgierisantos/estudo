package LogAuditoria2;

public class TelefoneLog extends Telefone {
	
	private final Telefone telefone;

    public TelefoneLog(Telefone t) {
        super();
        this.telefone = t;
    }

    @LogField(label = "Celular", section = "Contato")
    @Override
    public String getCelular() {
        return telefone.getCelular();
    }

    @LogField(label = "Fixo", section = "Contato")
    @Override
    public String getFixo() {
        return telefone.getFixo();
    }
}