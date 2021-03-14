package main;

import java.util.Random;

public class AlgoritmoGenetico {
	
//	int nPopulacao = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de individuos da população inicial?"));
	int nPopulacao = 5;
	Populacao populacao = new Populacao(nPopulacao);
	Random roleta = new Random();
	int penalidade = -50;
	
	public void primeiraPopulacao(int nItens) {
		
		for(int i=0; i<nPopulacao; i++) { //Criando os Individuos
			Individuo individuo = new Individuo(nItens);
			for(int j=0; j<nItens; j++) { //Definindo os cromossomos
				individuo.cromossomos[j] = roleta.nextInt(2);
			}
			
			populacao.individuos[i] = individuo; //Adicionando o individuo a populacao
			
		}
		
	}
	
	public void fitness(Item itens[], int capMochila, int nItens){
		
		for(int i=0; i<nPopulacao;i++) {
			for(int j=0; j<nItens; j++) { 
				if(populacao.individuos[i].cromossomos[j] == 1) {
					populacao.individuos[i].beneficio += itens[j].valorItem;
					populacao.individuos[i].peso += itens[j].pesoItem;
				}
			}
			
			if(populacao.individuos[i].peso > capMochila) {
				populacao.individuos[i].beneficio += penalidade;
			}
		}
		
	}
	
	public void mostraResultado(Item itens[], int nItens) {
		
		String Cromossomo = "";
		
		for(int i=0; i<nItens;i++) {
			System.out.println("Item " + (i+1) + " - Peso: "+ itens[i].pesoItem + "  Valor: " + itens[i].valorItem); 
		}
		
		System.out.println("\n"); 
	
		
		for(int i=0; i<nPopulacao; i++) { 
			
			System.out.println("Individuo " + (i+1)); 
			Cromossomo = "";
			
			for(int j=0; j<nItens; j++) { 
				Cromossomo += populacao.individuos[i].cromossomos[j] + " ";
			
			}
			
			System.out.println("Cromossomos: " + Cromossomo);
			System.out.println("Beneficio: " + populacao.individuos[i].beneficio); 
			System.out.println("Peso: " + populacao.individuos[i].peso + "\n"); 
			System.out.println("\n");
			
		}
		
	}
	

}
