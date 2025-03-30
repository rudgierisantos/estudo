package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Validador2 {
    public static void main(String[] args) {
        String nome = "Jo";
        Integer idade = 16;
        List<String> emails = Arrays.asList("usuario@email.com", "emailinvalido", null);

        Validador3 validador = new ValidadorBuilder2()
            .campo("nome", () -> nome)
                .naoNulo("Nome não pode ser nulo")
                .minimo(3, "Nome deve ter ao menos 3 caracteres")
            .lista("emails", () -> emails)
                .naoNula("Lista de emails não pode ser nula")
                .naoVazia("Lista de emails não pode ser vazia")
                .cadaItem()
                    .naoNulo("Email não pode ser nulo")
                    .regex(".+@.+\\..+", "Email inválido")
            .campo("idade", () -> idade)
                .naoNulo("Idade não pode ser nula")
                .maiorQue(17, "Idade deve ser maior que 17")
            .build();

        List<String> erros = validador.validar();
        erros.forEach(System.out::println);
    }
}

// ----------------------- BUILDER -----------------------

class ValidadorBuilder2 {
    private final List<RegraValidacao> regras = new ArrayList<>();

    public <T> CampoBuilder<T> campo(String nome, Supplier<T> getter) {
        return new CampoBuilder<>(this, nome, getter);
    }

    public <T> ListaBuilder<T> lista(String nome, Supplier<List<T>> getter) {
        return new ListaBuilder<>(this, nome, getter);
    }

    public ValidadorBuilder2 adicionarRegra(RegraValidacao regra) {
        regras.add(regra);
        return this;
    }

    public Validador3 build() {
        return new Validador3(regras);
    }
}

class CampoBuilder<T> {
    private final ValidadorBuilder2 validador;
    private final String nome;
    private final Supplier<T> getter;

    public CampoBuilder(ValidadorBuilder2 validador, String nome, Supplier<T> getter) {
        this.validador = validador;
        this.nome = nome;
        this.getter = getter;
    }

    public CampoBuilder<T> naoNulo(String mensagem) {
        validador.adicionarRegra(new RegraValidacao(nome, () -> getter.get() != null, mensagem));
        return this;
    }

    public CampoBuilder<T> minimo(int min, String mensagem) {
        validador.adicionarRegra(new RegraValidacao(nome, () -> {
            T val = getter.get();
            return val != null && val instanceof String && ((String) val).length() >= min;
        }, mensagem));
        return this;
    }

    public CampoBuilder<T> maiorQue(int limite, String mensagem) {
        validador.adicionarRegra(new RegraValidacao(nome, () -> {
            T val = getter.get();
            return val instanceof Integer && ((Integer) val) > limite;
        }, mensagem));
        return this;
    }

    public <U> CampoBuilder<U> campo(String nome, Supplier<U> getter) {
        return validador.campo(nome, getter);
    }

    public <U> ListaBuilder<U> lista(String nome, Supplier<List<U>> getter) {
        return validador.lista(nome, getter);
    }

    public Validador3 build() {
        return validador.build();
    }
}

class ListaBuilder<T> {
    final ValidadorBuilder2 validador;
    private final String nome;
    private final Supplier<List<T>> getter;
    private final List<RegraValidacao> regras = new ArrayList<>();

    public ListaBuilder(ValidadorBuilder2 validador, String nome, Supplier<List<T>> getter) {
        this.validador = validador;
        this.nome = nome;
        this.getter = getter;
    }

    public ListaBuilder<T> naoNula(String mensagem) {
        regras.add(new RegraValidacao(nome, () -> getter.get() != null, mensagem));
        return this;
    }

    public ListaBuilder<T> naoVazia(String mensagem) {
        regras.add(new RegraValidacao(nome, () -> {
            List<T> lista = getter.get();
            return lista != null && !lista.isEmpty();
        }, mensagem));
        return this;
    }

    public ItemBuilder<T> cadaItem() {
        return new ItemBuilder<>(this, nome, getter);
    }

    public <U> CampoBuilder<U> campo(String nome, Supplier<U> getter) {
        // Antes de sair, adiciona regras da lista ao validador principal
        regras.forEach(validador::adicionarRegra);
        return validador.campo(nome, getter);
    }

    public <U> ListaBuilder<U> lista(String nome, Supplier<List<U>> getter) {
        regras.forEach(validador::adicionarRegra);
        return validador.lista(nome, getter);
    }

    public Validador3 build() {
        regras.forEach(validador::adicionarRegra);
        return validador.build();
    }
}

class ItemBuilder<T> {
    private final ListaBuilder<T> listaBuilder;
    private final String nomeLista;
    private final Supplier<List<T>> listaGetter;

    public ItemBuilder(ListaBuilder<T> listaBuilder, String nomeLista, Supplier<List<T>> listaGetter) {
        this.listaBuilder = listaBuilder;
        this.nomeLista = nomeLista;
        this.listaGetter = listaGetter;
    }

    public ItemBuilder<T> naoNulo(String mensagem) {
        listaBuilder.validador.adicionarRegra(new RegraValidacao(nomeLista, () -> {
            List<T> lista = listaGetter.get();
            if (lista == null) return false;
            for (T item : lista) {
                if (item == null) return false;
            }
            return true;
        }, mensagem));
        return this;
    }

    public ItemBuilder<T> regex(String regex, String mensagem) {
        listaBuilder.validador.adicionarRegra(new RegraValidacao(nomeLista, () -> {
            List<T> lista = listaGetter.get();
            if (lista == null) return false;
            for (T item : lista) {
                if (item == null) return false;
                if (!(item instanceof String)) return false;
                if (!((String) item).matches(regex)) return false;
            }
            return true;
        }, mensagem));
        return this;
    }

    // Pode adicionar mais validações específicas para item aqui

    public ListaBuilder<T> lista() {
        return listaBuilder;
    }

    public <U> CampoBuilder<U> campo(String nome, Supplier<U> getter) {
        return listaBuilder.campo(nome, getter);
    }

    public <U> ListaBuilder<U> lista(String nome, Supplier<List<U>> getter) {
        return listaBuilder.lista(nome, getter);
    }

    public Validador3 build() {
        return listaBuilder.build();
    }
}

// Validador3 e RegraValidacao continuam iguais:
class Validador3 {
    private final List<RegraValidacao> regras;

    public Validador3(List<RegraValidacao> regras) {
        this.regras = regras;
    }

    public List<String> validar() {
        List<String> erros = new ArrayList<>();
        for (RegraValidacao regra : regras) {
            if (!regra.validar()) {
                erros.add(regra.getCampo() + ": " + regra.getMensagem());
            }
        }
        return erros;
    }
}

class RegraValidacao {
    private final String campo;
    private final Supplier<Boolean> condicao;
    private final String mensagem;

    public RegraValidacao(String campo, Supplier<Boolean> condicao, String mensagem) {
        this.campo = campo;
        this.condicao = condicao;
        this.mensagem = mensagem;
    }

    public boolean validar() {
        try {
            return condicao.get();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
