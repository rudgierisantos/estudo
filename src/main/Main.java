package main;

import classes.Array;
import classes.Lista;
import classes.Produto;

public class Main {

	public static void main(String[] args) {
	
		Lista<Produto> produtos = new Array<>();
		
		produtos.adicionar(new Produto("1", "computador"));
		produtos.adicionar(new Produto("2", "mouse"));
		
		System.out.println(produtos + " " + produtos.size());
		
	}

}
