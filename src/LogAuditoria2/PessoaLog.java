package LogAuditoria2;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaLog extends Pessoa {
	
    private final Pessoa pessoa; // objeto original

    public PessoaLog(Pessoa p) {
    	super();
    	this.pessoa = p;
    }

    @LogField(label = "Nome", section = "Dados Pessoais")
    @Override
    public String getNome() {
        return pessoa.getNome();
    }

    @LogField(label = "Idade", section = "Dados Pessoais")
    @Override
    public int getIdade() {
        return pessoa.getIdade();
    }

    @LogField(label = "Cidade", section = "Endereço")
    @Override
    public String getCidade() {
        return pessoa.getCidade();
    }

    @LogField(label = "Telefones", section = "Contatos")
    @Override
    public List<String> getTelefones() {
        return pessoa.getTelefones();
    }

    @LogField(label = "Telefones Objetos", section = "Contatos")
    @Override
    public List<Telefone> getTelefonesObj() {
        // Retorna a lista convertida “on the fly” sem precisar mapear antes
        return pessoa.getTelefonesObj()
                    .stream()
                    .map(TelefoneLog::new)
                    .collect(Collectors.toList());
    }
}