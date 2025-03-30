package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validador {

    public static void main(String[] args) {
        String nome = "q";
        Integer idade = 16;
        List<String> emails = Arrays.asList("", "emailinvalido", "usuario@dominio.com.br");

        ValidadorBuilder validador = new ValidadorBuilder()
            .comCampo("nome", nome).naoNulo().tamanhoMinimo(3)
            .comCampo("idade", idade).naoNulo().maiorQue(17)
            .comLista("emails", emails).naoNula().naoVazia().cadaItemValido();

        List<String> erros = validador.validar();

        for (String erro : erros) {
            System.out.println(erro);
        }
    }
}

// ---------------- CLASSES SIMPLES ----------------

class ValidadorBuilder {
    private final List<CampoValidacao> campos = new ArrayList<>();
    private final List<ListaValidacao> listas = new ArrayList<>();
    private CampoValidacao campoAtual;
    private ListaValidacao listaAtual;

    public ValidadorBuilder comCampo(String nome, Object valor) {
        campoAtual = new CampoValidacao(nome, valor);
        campos.add(campoAtual);
        listaAtual = null;
        return this;
    }

    public ValidadorBuilder comLista(String nome, List<?> lista) {
        listaAtual = new ListaValidacao(nome, lista);
        listas.add(listaAtual);
        campoAtual = null;
        return this;
    }

    public ValidadorBuilder naoNulo() {
        if (campoAtual != null && campoAtual.getValor() == null) {
            campoAtual.adicionarErro("não pode ser nulo");
        }
        return this;
    }

    public ValidadorBuilder tamanhoMinimo(int min) {
        if (campoAtual != null && campoAtual.getValor() instanceof String) {
            String texto = (String) campoAtual.getValor();
            if (texto.length() < min) {
                campoAtual.adicionarErro("tamanho mínimo é " + min);
            }
        }
        return this;
    }

    public ValidadorBuilder maiorQue(int limite) {
        if (campoAtual != null && campoAtual.getValor() instanceof Integer) {
            Integer numero = (Integer) campoAtual.getValor();
            if (numero <= limite) {
                campoAtual.adicionarErro("deve ser maior que " + limite);
            }
        }
        return this;
    }

    public ValidadorBuilder naoNula() {
        if (listaAtual != null && listaAtual.getValores() == null) {
            listaAtual.adicionarErro("lista não pode ser nula");
        }
        return this;
    }

    public ValidadorBuilder naoVazia() {
        if (listaAtual != null && (listaAtual.getValores() == null || listaAtual.getValores().isEmpty())) {
            listaAtual.adicionarErro("lista não pode ser vazia");
        }
        return this;
    }

    public ValidadorBuilder cadaItemValido() {
        if (listaAtual != null && listaAtual.getValores() != null) {
            int i = 0;
            for (Object item : listaAtual.getValores()) {
                if (!(item instanceof String)) continue;
                String valor = (String) item;
                if (valor == null || valor.trim().isEmpty()) {
                    listaAtual.adicionarErro("emails[" + i + "]: não pode ser vazio");
                } else if (!valor.matches(".+@.+\\..+")) {
                    listaAtual.adicionarErro("emails[" + i + "]: formato inválido");
                } else if (valor.length() > 50) {
                    listaAtual.adicionarErro("emails[" + i + "]: tamanho máximo é 50");
                }
                i++;
            }
        }
        return this;
    }

    public List<String> validar() {
        List<String> erros = new ArrayList<>();
        for (CampoValidacao campo : campos) {
            for (String erro : campo.getErros()) {
                erros.add(campo.getNome() + ": " + erro);
            }
        }
        for (ListaValidacao lista : listas) {
            erros.addAll(lista.getErros());
        }
        return erros;
    }
}

class CampoValidacao {
    private final String nome;
    private final Object valor;
    private final List<String> erros = new ArrayList<>();

    public CampoValidacao(String nome, Object valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public Object getValor() {
        return valor;
    }

    public void adicionarErro(String mensagem) {
        erros.add(mensagem);
    }

    public List<String> getErros() {
        return erros;
    }
}

class ListaValidacao {
    private final String nome;
    private final List<?> valores;
    private final List<String> erros = new ArrayList<>();

    public ListaValidacao(String nome, List<?> valores) {
        this.nome = nome;
        this.valores = valores;
    }

    public String getNome() {
        return nome;
    }

    public List<?> getValores() {
        return valores;
    }

    public void adicionarErro(String mensagem) {
        erros.add(mensagem);
    }

    public List<String> getErros() {
        return erros;
    }
}
