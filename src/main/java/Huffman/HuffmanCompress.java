package Huffman;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Compressores.DataFile;
import Compressores.NumberUtils;
import Huffman.interfaces.HuffmanTreeIF;

/**
 * Classe da parte de compressao do compressor Huffman.
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
public class HuffmanCompress extends AbstractModule{

    /**
     * Construtor
     *
     * @param source a fonte
     * @param destiny onde o arquivo deve ser gravado
     * @param treeHuffman a arvore de huffman
     */
    public HuffmanCompress(File source, File destiny, HuffmanTreeIF treeHuffman) {
        super(source, destiny, treeHuffman);
    }

    /**
     * Faz a compressao
     */
    public void compress() throws FileNotFoundException, IOException {
        /***
         * Sequencia:
         *
         * -> byte1 - numero de bytes "b" que diz quantos bytes e' necessario para guardar o tamanho original do arquivo (em bytes)
         * -> proximos "b" bytes - numero de bytes do arquivo original
         * -> restante - arquivo comprimido
         *
         ***/

        /*** Abre os arquivos ***/
        DataFile sourceFile = new DataFile(getSource());
        DataFile destinyFile = new DataFile(getDestiny(), true);
        HuffmanTreeIF treeHuffman = getTreeHuffman();

        /*** Tamanho do arquivo original em bytes ***/
        long sourceFileLenghBytes = sourceFile.getFile().length();

        /*** Numero de bytes necessarios para representar o tamanho do arquivo ***/
        byte numberOfByteToSourceFileLenghtRepresentation = NumberUtils.numberOfBytes(sourceFileLenghBytes);

        /*** Escreve o numero de bytes que é necessario para armazenar o tamanho do arquivo original ***/
        destinyFile.write(numberOfByteToSourceFileLenghtRepresentation);

        /***
         * Escreve o tamanho do arquivo em bytes
         * 
         * O que vem abaixo e' quase uma gambiarra
         ***/
//        if(sourceFileLenghBytes < Byte.MAX_VALUE)
            destinyFile.write((byte) sourceFileLenghBytes);
//        else if(sourceFileLenghBytes < Short.MAX_VALUE)
//            destinyFile.write((short) sourceFileLenghBytes);
//        else if(sourceFileLenghBytes < Integer.MAX_VALUE)
//            destinyFile.write((int) sourceFileLenghBytes);
//        else
//            destinyFile.write(sourceFileLenghBytes);

        /*** Manda o primeiro byte direto pra saida ***/
        byte readByte = sourceFile.readByte();
        destinyFile.write(readByte);
        treeHuffman.addOcurrency(readByte);
        
        /*** Codifica o arquivo original ***/
        try{
            while(sourceFile.avaliableBits() > 0){
                readByte = sourceFile.readByte();

                boolean[] symbolCode = treeHuffman.getSymbolCode(readByte);

                if(symbolCode == null){
                    /*** Codifica o escape e o simbolo ***/
                    boolean[] escapeCode = treeHuffman.getEscapeCode();
                    destinyFile.writeBit(escapeCode);
                    destinyFile.write(readByte);
                }else{
                    /*** Codifica o codigo do simbolo ***/
                    destinyFile.writeBit(symbolCode);
                }

                /*** adiciona uma ocorrencia do simbolo na arvore ***/
                treeHuffman.addOcurrency(readByte);
            }
        }catch(EOFException eOFException){
            destinyFile.closeOutputFlow();
            sourceFile.closeInputFlow();
        }
    }

}
