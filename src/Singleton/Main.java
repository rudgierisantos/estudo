package Singleton;

import Factory.Pessoa;

public class Main {

	public static void main(String[] args) {
		
		Pessoa pessoa = Singleton.getPessoaSingleton();
		
		Pessoa pessoa2 = Singleton.getPessoaSingleton();
		
		System.out.println(pessoa + " " + pessoa.hashCode());
		System.out.println(pessoa2 + " " + pessoa.hashCode());
	}

}
