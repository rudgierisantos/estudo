package Singleton;

import java.util.Objects;

import Factory.Pessoa;

public class Singleton {
	static Pessoa pessoa;
	
	public static synchronized Pessoa getPessoaSingleton() {
		if(Objects.isNull(pessoa)){
			pessoa = new Pessoa("Rudgieri", "Santos");
		}
		return pessoa;
	}
}
