package main;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import classes.Array;
import classes.Lista;
import classes.Produto;

public class MainMultithread {

	public static void main(String[] args) {
		Lista<Produto> produtos = new Array<>();
		
		ExecutorService executador = Executors.newFixedThreadPool(10);
		
		
		for (int i = 0; i < 100000; i++) {
		    final int index = i;
			executador.execute(() -> {
				produtos.adicionar(new Produto( String.valueOf(index), "Produto"+ index));
                System.out.println(Thread.currentThread().getName() + " processando: " + index);

			});
		}
		executador.shutdown();
		
		System.out.println(produtos + " " + produtos.size());
	}

}
