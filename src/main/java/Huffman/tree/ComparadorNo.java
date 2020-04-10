package Huffman.tree;

import java.util.Comparator;

public class ComparadorNo implements Comparator<No>{

	public int compare(No arg0, No arg1) {
		
		return arg0.probabilidade - arg1.probabilidade;
	}

}
