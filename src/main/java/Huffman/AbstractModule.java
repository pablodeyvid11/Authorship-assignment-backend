package Huffman;

import java.io.File;

import Huffman.interfaces.HuffmanTreeIF;

/**
 * Classe do modulo (compressao/descompressao) do compressor Huffman.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 11 de maio de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class AbstractModule {

    private File source;
    private File destiny;
    private HuffmanTreeIF treeHuffman;

    /**
     * O tamanho do arquivo em bytes
     */
    public final static long MAX_FILE_LENGHT = (long) Math.pow(2, 255 * 8);

    /**
     * Construtor
     *
     * @param source a fonte
     * @param destiny onde o arquivo deve ser gravado
     * @param treeHuffman a arvore de huffman
     */
    public AbstractModule(File source, File destiny, HuffmanTreeIF treeHuffman) {
        if(source == null || destiny == null)
            throw new IllegalArgumentException("Files can't be null!");

        if(treeHuffman == null)
            throw new IllegalArgumentException("Tree can't be null!");

        if(source.length() > MAX_FILE_LENGHT)
            throw new IllegalArgumentException("File is to large!");

        this.source = source;
        this.destiny = destiny;
        this.treeHuffman = treeHuffman;
    }

    /**
     * Retorna a arvore
     * 
     * @return a arvore
     */
    public HuffmanTreeIF getTreeHuffman() {
        return treeHuffman;
    }

    /**
     * O arquivo onde deve ser gravado
     *
     * @return o arquivo
     */
    public File getDestiny() {
        return destiny;
    }

    /**
     * O arquivo fonte
     *
     * @return o arquivo
     */
    public File getSource() {
        return source;
    }
}
