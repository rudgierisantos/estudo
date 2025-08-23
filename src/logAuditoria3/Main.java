package logAuditoria3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {
        Cliente antigo = new Cliente();
        antigo.setNome("Rudgieri");
        antigo.setTelefones(Arrays.asList("1111", "2222"));
        antigo.setPedidos(Arrays.asList(new Pedido(1, "Pedido A"), new Pedido(2, "Pedido B")));

        Cliente atual = new Cliente();
        atual.setNome("Rudgieri Santos");
        atual.setTelefones(Arrays.asList("1111", "3333"));
        atual.setPedidos(Arrays.asList(new Pedido(1, "Pedido A alterado"), new Pedido(3, "Pedido C")));

        List<Map<String, Object>> alteracoes = Comparador.comparar(antigo, atual);

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(alteracoes));
    }
}

