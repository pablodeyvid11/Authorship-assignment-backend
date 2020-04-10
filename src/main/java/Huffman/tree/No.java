package Huffman.tree;

public class No {
	
	public No esquerdo;
	public No direito;
	public No pai;
	public int probabilidade;
	public byte simbolo;
	
	public No( int probabilidade){
		
		this.probabilidade = probabilidade;
		this.pai = null;
		this.esquerdo = null;
		this.direito = null;
		
	}
	
	public No( byte simbol){
		
		this.pai = null;
		this.simbolo = simbol;
		this.esquerdo = null;
		this.direito = null;
		
	}
	
	
	
	public No(No esquerdo, No direito, No pai, int probabilidade, byte simbolo) {
		super();
		this.esquerdo = esquerdo;
		this.direito = direito;
		this.pai = pai;
		this.probabilidade = probabilidade;
		this.simbolo = simbolo;
	}

	public No( int probabilidade, byte simbolo){
		
		this.probabilidade = probabilidade;
		this.simbolo = simbolo;
		this.pai = null;
		this.esquerdo = null;
		this.direito = null;
		
	}
	
	
	public No( int probabilidade, No pai){
		
		this.probabilidade = probabilidade;
		this.pai = pai;
		this.esquerdo = null;
		this.direito = null;
	}
	
	public No( int probabilidade,byte simbolo, No pai){
		
		this.probabilidade = probabilidade;
		this.simbolo = simbolo;
		this.pai = pai;
		this.esquerdo = null;
		this.direito = null;
	}
	
	public No getEsquerdo() {
		return esquerdo;
	}
	
	public void setEsquerdo(No esquerdo) {
		this.esquerdo = esquerdo;
	}
	
	public No getDireito() {
		return direito;
	}
	
	public void setDireito(No direito) {
		this.direito = direito;
	}
	
	public No getPai() {
		return pai;
	}
	
	public byte getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(byte simbolo) {
		this.simbolo = simbolo;
	}

	public void setPai(No pai) {
		this.pai = pai;
	}
	
	public int getProbabilidade() {
		return probabilidade;
	}
	
	public void setProbabilidade(int probabilidade) {
		this.probabilidade = probabilidade;
	}

        public boolean isFolha(){
            return esquerdo == null && direito == null;
        }
	
	
}
