package Huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Huffman.interfaces.HuffmanTreeIF;
import Huffman.tree.Arvore;
import compressorAPI.CompressorIF;

/**
 * Classe do compressor Huffman.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 28 de abril de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class Huffman implements CompressorIF{

    public static final String NAME = "HUFFMAN";
    public static final String EXTENSION = "huffman";
    private String name = NAME;
    private String extension = EXTENSION;

    public String getExtension() {
        return extension;
    }

    public String getName() {
        return name;
    }

    public void compress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException {
        HuffmanTreeIF treeHuffman = new Arvore();
        new HuffmanCompress(sourceFile, destinyFile, treeHuffman).compress();
    }

    public void uncompress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException {
        HuffmanTreeIF treeHuffman = new Arvore();
        new HuffmanUncompress(sourceFile, destinyFile, treeHuffman).uncompress();
    }

}
