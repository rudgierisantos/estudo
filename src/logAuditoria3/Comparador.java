package logAuditoria3;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Comparador {

    public static List<Map<String, Object>> comparar(Object antigoObj, Object novoObj) {
        Map<String, Object> antigo = JsonUtil.toMapAnotado(antigoObj);
        Map<String, Object> novo = JsonUtil.toMapAnotado(novoObj);

        List<Map<String, Object>> resultado = new ArrayList<>();
        compararRecursivo("", antigo, novo, resultado);
        return resultado;
    }

    private static void compararRecursivo(String campo, Object antigo, Object novo, List<Map<String, Object>> resultado) {
        if (Objects.equals(antigo, novo)) return;

        if (antigo instanceof Map && novo instanceof Map) {
            Map<String, Object> mapAntigo = (Map<String, Object>) antigo;
            Map<String, Object> mapNovo = (Map<String, Object>) novo;
            for (String key : mapAntigo.keySet()) {
                compararRecursivo(campo.isEmpty() ? key : campo + "." + key,
                        mapAntigo.get(key), mapNovo.get(key), resultado);
            }

        } else if (antigo instanceof List && novo instanceof List) {
            List<?> listaAntiga = (List<?>) antigo;
            List<?> listaNova = (List<?>) novo;
            if (!listaAntiga.equals(listaNova)) {
                Map<String, Object> alteracao = new LinkedHashMap<>();
                alteracao.put("campo", campo);
                alteracao.put("valorAnterior", listaAntiga);
                alteracao.put("valorAtual", listaNova);
                resultado.add(alteracao);
            }

        } else {
        	Map<String, Object> alteracao = new LinkedHashMap<>();
            alteracao.put("campo", campo);
            alteracao.put("valorAnterior", antigo);
            alteracao.put("valorAtual", novo);
            resultado.add(alteracao);
        }
    }
}