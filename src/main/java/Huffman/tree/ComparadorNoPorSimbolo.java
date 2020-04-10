package Huffman.tree;

import java.util.Comparator;

public class ComparadorNoPorSimbolo implements Comparator<No>{
	
	public int compare(No arg0, No arg1) {
		
		return arg0.simbolo - arg1.simbolo;
	}

}
