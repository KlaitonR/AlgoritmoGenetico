package main;

public class Main {

	public static void main(String[] args) {
		
		int nItens;
		Item itens[];
		int capMochila;
		
		nItens = 5;
//		nItens = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos itens existem?"));
		itens = new Item[nItens];
		
//		for(int i=0; i<nItens; i++) {
//			
//			Item item = new Item(Integer.parseInt(JOptionPane.showInputDialog(null, "PESO do "+ (i+1) +"º Item")),
//								Integer.parseInt(JOptionPane.showInputDialog(null, "VALOR do "+ (i+1) +"º Item")));
//			
//			itens[i] = item;
//			
//		}
		
		Item item1 = new Item(4, 3);
		Item item2 = new Item(3, 2);
		Item item3 = new Item(8, 7);
		Item item4 = new Item(1, 4);
		Item item5 = new Item(5, 10);
		
		itens[0] = item1;
		itens[1] = item2;
		itens[2] = item3;
		itens[3] = item4;
		itens[4] = item5;
		
//		capMochila = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual a capacidade da mochila"));
		
		capMochila = 15;
		
		AlgoritmoGenetico ag = new AlgoritmoGenetico();
		
		ag.primeiraPopulacao(nItens);
		
		ag.fitness(itens, capMochila, nItens);
		
		ag.mostraResultado(itens, nItens);

	}

}
