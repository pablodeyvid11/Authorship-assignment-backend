package Huffman.tree;

public class Elemento {
	
	public int probabilidade;
	public byte simbolo;
	
	public Elemento(){}
	
	public Elemento(int probabilidade, byte simbolo){
		
		this.probabilidade = probabilidade;
		this.simbolo = simbolo;
	}

	public int getProbabilidade() {
		return probabilidade;
	}

	public void setProbabilidade(int probabilidade) {
		this.probabilidade = probabilidade;
	}

	public byte getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(byte simbolo) {
		this.simbolo = simbolo;
	}
	
	

}
