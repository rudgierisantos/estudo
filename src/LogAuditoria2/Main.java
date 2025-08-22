package LogAuditoria2;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {
    	
    	List<Telefone> antigaTel = Arrays.asList(new Telefone("981315105", "35965795"));
    	List<Telefone> novoTel = Arrays.asList(new Telefone("981315103", "35965797"));

    	
        Pessoa antiga = new Pessoa("Jo�o", 30, "S�o Paulo", Arrays.asList("1111-1111", "2222-2222"), antigaTel);
        Pessoa nova   = new Pessoa("Jo�o Silva", 31, "Rio de Janeiro", Arrays.asList("1111-1111", "3333-3333"), novoTel);

        List<ChangeLog> logs = LogComparator.compare(antiga, nova);
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(logs);

        System.out.println(json);
    }
}
