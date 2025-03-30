package classes;

import interfaces.Colecao;

public interface Lista<E> extends Colecao<E> {

	boolean adicionar(E e);

	boolean remover(Object o);
	
	int size();

}
