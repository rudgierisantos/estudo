package classes;

import java.util.ArrayList;
import java.util.List;

public class Array<E> implements Lista<E> {
	
	private List<Object> lista = new ArrayList<>();

	@Override
	public boolean adicionar(Object e) {
		return this.lista.add(e);
	}

	@Override
	public boolean remover(Object e) {
		return this.lista.remove(e);
	}

	@Override
	public String toString() {
		return "Array [lista=" + lista + "]";
	}

	@Override
	public int size() {
		return lista.size();
	}

}
