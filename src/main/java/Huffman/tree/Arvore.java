package Huffman.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Huffman.interfaces.HuffmanTreeIF;


public class Arvore implements HuffmanTreeIF {
	
	public No raiz;
	public static List<No> folhas = new ArrayList<No>();
	public boolean[] situacaoSimbolo = new boolean[256];
	public No escape;
	
	public Arvore(){
		
	}
	
	public Arvore(int probabilidade){
		
		raiz = new No(probabilidade);
				
	}
	
	/*O array de probabilidades deve ter tamanho maior que 1!!*/
	public void ConstroiArvore(List<No> lista ){
		
		List<No> listaDeNos = copiaLista(lista);
		
		if(listaDeNos.size() <= 255){
		   escape = new No(listaDeNos.size());
		   listaDeNos.add(escape);
		   
		}
		
		Collections.sort(listaDeNos, new ComparadorNo());
		No no1;
		No no2;
		imprimeLista(listaDeNos);
		while(listaDeNos.size() > 1){
			No noAux = new No(listaDeNos.get(0).probabilidade + listaDeNos.get(1).probabilidade);
			no1 = listaDeNos.get(0);
			no2 = listaDeNos.get(1);
			no1.pai = noAux;
			no2.pai = noAux;
			noAux.setEsquerdo(no1);
			noAux.setDireito(no2);
			listaDeNos.remove(listaDeNos.get(1));
			listaDeNos.remove(listaDeNos.get(0));
			listaDeNos.add(noAux);
			Collections.sort(listaDeNos, new ComparadorNo());
			
		}
		this.raiz = listaDeNos.get(0);
		
	}
	
	public static List<No> copiaLista(List<No> lista){
		
		List<No> list = new ArrayList<No>();
		int tamanho = lista.size();
		
		for(int i=0;i<tamanho;i++){
			list.add(lista.get(i));
		}
		
		return list;
		
	}
	
	public static void imprimeLista(List<No> lista){
		
		for(int i = 0;i<lista.size();i++){
		//	System.out.print(" "+lista.get(i).probabilidade);
			
		}
	}
	
	public static void imprimeAlturas(List<No> lista){
		
		for(int i = 0;i<lista.size();i++){
			//System.out.print(" "+altura(lista.get(i)));
			
		}
	}
	
	public void InOrdem(No no){
		
		if(no == null)
			return;
		
		InOrdem(no.direito);
		InOrdem(no.esquerdo);
		//System.out.print(" "+no.probabilidade+" ");
			
	}
	
	public static int altura(No no){
		int altura=0;
		
		while(no != null){
			no = no.pai;
			altura++;
		}
		return altura;
		
	}
	
	public static void main(String[] args){
		 
//		Arvore arv = new Arvore();
//		
//		System.out.print("Lista de Probabilidades: ");
//		arv.addOcurrency((byte)1);
//		boolean[] res = arv.getSymbolCode((byte)1);
//		for(int i=0;i<res.length;i++){
//			System.out.print(res[i]+" ");
//		}
//		arv.addOcurrency((byte)1);
//		res = arv.getSymbolCode((byte)1);
//		for(int i=0;i<res.length;i++){
//			System.out.print(res[i]+" ");
//		}
//		arv.addOcurrency((byte)1);
//		res = arv.getSymbolCode((byte)1);
//		for(int i=0;i<res.length;i++){
//			System.out.print(res[i]+" ");
//		}
//		arv.addOcurrency((byte)1);
//		res = arv.getSymbolCode((byte)1);
//		for(int i=0;i<res.length;i++){
//			System.out.print(res[i]+" ");
//		}
//		arv.addOcurrency((byte)1);
//		res = arv.getSymbolCode((byte)1);
//		for(int i=0;i<res.length;i++){
//			System.out.print(res[i]+" ");
//		}
//		System.out.println(">>>"+arv.getOriginalSymbol(arv.getEscapeCode()));
		
//		    String s = new String("101");
//	        Byte b = Byte.valueOf(s,2);
//	        System.out.print("The byte is: " + b);
		
		        boolean[] ba = { false, true, false, true, true, false, true};  
		         String valores = "";  
		 
		         for(boolean b : ba) {  
		            valores += (b ? '1' : '0');  
		        }  
	  
		         byte b = Byte.valueOf("-"+valores, 2);  
		   
		      //  System.out.println(valores + " = " + b);

	}
	
	
	public boolean[] getNodeCode(No no){
		
		No pai;
		List<Boolean> codigo = new ArrayList<Boolean>();
		do{
			pai = no.pai;
			if(no.equals(pai.esquerdo))
				codigo.add(true);
			else
				codigo.add(false);
			no = no.pai;
		}while(no.pai != null);
		int tamanho = codigo.size()-1;
		boolean codigoFinal[]= new boolean[tamanho+1];
		for(int i=0; i<=tamanho ;i++){
			codigoFinal[i] = codigo.get(tamanho-i);
		}
		
		return codigoFinal;
		
	}
	
    /**
     * Retorna o codigo atual do escape
     *
     * @return o codigo atual do escape na arvore ou null caso ele nao seja mais utilizado
     */
	public boolean[] getEscapeCode() {
		// TODO Auto-generated method stub
		
		return getNodeCode(escape);

	}
	
	 /**
     * Retorna o simbolo dado o codigo em bytes dele na arvore de huffman
     *
     * @param code o codigo do simbolo na arvore de huffman
     *
     * @return o simbolo que o codigo representa ou null caso o codigo nao existe
     */
	public Byte getOriginalSymbol(boolean[] code) {
		// TODO Auto-generated method stub
		
		boolean[] escape = getEscapeCode();
		
		if(Arrays.equals(code, escape))
			return null;
		
		No raiz = new No(this.raiz.esquerdo,this.raiz.direito,this.raiz.pai,this.raiz.probabilidade,this.raiz.simbolo);
		try{
		for(int i=0;i<code.length;i++){
			if(code[i]){
                           // System.out.println("if");
			   raiz = raiz.esquerdo;
                        }
			else{
                           // System.out.println("else");
				raiz = raiz.direito;
                        }
		}
			        //         System.out.println("Simbolo " + raiz.simbolo);
		return raiz.isFolha() ? raiz.simbolo : null;
		}catch(NullPointerException e){
			return null;
		}
	}
	
	
    /**
     * Adiciona uma ocorrencia do simbolo passado como argumento
     
     * @param symbol o simbolo a ser adicionado a ocorrencia
     * 
     * @return o numero de ocorrencias apos adiciona a nova ocorrencia
     */
	public int addOcurrency(byte symbol) {
		int indice;
		
		if(!situacaoSimbolo[symbol+128]){ //simbolo novo
			folhas.add(new No(1,symbol));
			ConstroiArvore(folhas);
			situacaoSimbolo[symbol+128] = true;
			return 1;
		}else{
			Collections.sort(folhas, new ComparadorNoPorSimbolo());
			indice = Collections.binarySearch(folhas, new No(symbol), new ComparadorNoPorSimbolo());
			folhas.get(indice).probabilidade++;
			ConstroiArvore(folhas);
			return folhas.get(indice).probabilidade;
		}
		
	}
	
	public boolean estaOrdenadaPorSimbolo(List<No> listaDeNos){
		
		int tamanho = listaDeNos.size()-1; 
		for(int i=1;i<tamanho;i++){
			if((listaDeNos.get(i).simbolo < listaDeNos.get(i-1).simbolo) && (listaDeNos.get(i).simbolo > listaDeNos.get(i+1).simbolo))
				return false;
		}
		
		return true;
	}
	
	public boolean estaOrdenada(List<No> listaDeNos){
		
		int tamanho = listaDeNos.size()-1; 
		for(int i=1;i<tamanho;i++){
			if((listaDeNos.get(i).probabilidade < listaDeNos.get(i-1).probabilidade) && (listaDeNos.get(i).probabilidade > listaDeNos.get(i+1).probabilidade))
				return false;
		}
		
		return true;
	}
	
	/**
     * Retorna o codigo de um simbolo
     *
     * @param symbol o simbolo
     *
     * @return o codigo do simbolo se ele existir na arvore ou null caso contrario
     */
	public boolean[] getSymbolCode(byte symbol) {
		// TODO Auto-generated method stub
		
		List<No> folhas = new ArrayList<No>();
		folhas = copiaLista(this.folhas);
		Collections.sort(folhas, new ComparadorNoPorSimbolo());
		int posic = Collections.binarySearch(folhas, new No(symbol), new ComparadorNoPorSimbolo());
		
		if(posic < 0)
		  return null; 
		
		return getNodeCode(folhas.get(posic));
		
	}

}
