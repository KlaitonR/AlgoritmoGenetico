package main;

import java.util.Random;

public class AlgoritmoGenetico {
	
//	int nPopulacao = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de individuos da população inicial?"));
	int nPopulacao = 5;
	Populacao populacao = new Populacao(nPopulacao);
	Random roleta = new Random();
	int penalidade = -50;
	int selecionados = 0;
	
	public void primeiraPopulacao(int nItens) {
		
		for(int i=0; i<nPopulacao; i++) { //Criando os Individuos
			Individuo individuo = new Individuo(nItens);
			for(int j=0; j<nItens; j++) { //Definindo os cromossomos
				individuo.cromossomos[j] = roleta.nextInt(2);
			}
			populacao.individuos[i] = individuo; //Adicionando o individuo a populacao
		}
	}
	
	public void fitness(Item itens[], double capMochila, int nItens){
		
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
	
	public void selecao() {
		
		double menorBeneficio = 999999999;
		int fitnessTotal = 0;
		
		for(int i=0; i<nPopulacao; i++) { // Verificando o menor beneficio
			if(populacao.individuos[i].beneficio < menorBeneficio) {
				menorBeneficio = populacao.individuos[i].beneficio;
			}
		}
		
		for(int i=0; i<nPopulacao; i++) { //Eliminando valores negativos
			populacao.individuos[i].beneficio -= menorBeneficio;
		}
		
		for(int i=0; i<nPopulacao; i++) {// Calculando Fitness Total
			fitnessTotal += populacao.individuos[i].beneficio;
		}
		
		System.out.println("\nFitness total: " + fitnessTotal + "\n"); 
		
		for(int i=0; i<nPopulacao; i++) { //Calculando porcentagem
			populacao.individuos[i].propSelecao = (populacao.individuos[i].beneficio/fitnessTotal) * 100;
			
		}
		
		double somaDaRoleta = 0;
		
		for(int i=0; i<nPopulacao; i++) { //Definindo os espaços da Roleta para cada individuo
			somaDaRoleta += populacao.individuos[i].propSelecao;
			populacao.individuos[i].parcelaDaRoleta = somaDaRoleta;  
		}
		
		double nPop = nPopulacao;
		
		if((nPopulacao%2) != 0) {
			nPop -= 1;
		}
		
		for(int i=0; i<nPop/2; i++) {
			if(selecionados < nPop/2)
				rodarRoleta();
		}
	}
	
	public void rodarRoleta() {
		
	double sorteio = roleta.nextDouble()*100;
	boolean selecionado = false;
	int i = 0;
	double faixaAnterior = 0;
	double faixaAtual = 0;
	System.out.println("Número sorteado: " + sorteio + "\n");
		
		while(!selecionado){ 
			
			faixaAtual += populacao.individuos[i].parcelaDaRoleta;
			
			if(sorteio >= faixaAnterior && 
					sorteio < faixaAtual && !populacao.individuos[i].selecionado) {
				populacao.individuos[i].selecionado = true;
				selecionado = true;
				selecionados++;
			}
			
			faixaAnterior += populacao.individuos[i].parcelaDaRoleta;
			i++;
		}
		
	}
	
	public void mostraResultado(Item itens[], int nItens) {
		
		String Cromossomo = "";
		String selecionados = "Individuos Selecionados: ";
		
		for(int i=0; i<nItens;i++) {
			System.out.println("Item " + (i+1) + " - Peso: "+ itens[i].pesoItem + "  Valor: " + itens[i].valorItem); 
		}
		
		System.out.println("\n"); 
	
		
		for(int i=0; i<nPopulacao; i++) { 
			
			System.out.println("Individuo " + (i+1)); 
			Cromossomo = "";
			if(populacao.individuos[i].selecionado)
				selecionados += i + "  ";
			
			for(int j=0; j<nItens; j++) { 
				Cromossomo += populacao.individuos[i].cromossomos[j] + " ";
			
			}
			
			System.out.println("Cromossomos: " + Cromossomo);
			System.out.println("Beneficio: " + populacao.individuos[i].beneficio); 
			System.out.println("Peso: " + populacao.individuos[i].peso); 
			System.out.println("Probabilidade de seleção: " + populacao.individuos[i].propSelecao);
			System.out.println("\n");
			
		}
		
		System.out.println(selecionados);
		
	}
	

}
