package Huffman;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import Compressores.DataFile;
import Huffman.interfaces.HuffmanTreeIF;

/**
 * Classe da parte de descompressao do compressor Huffman.
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
public class HuffmanUncompress extends AbstractModule{
    
    /**
     * Construtor
     *
     * @param source a fonte
     * @param destiny onde o arquivo deve ser gravado
     * @param treeHuffman a arvore de huffman
     */
    public HuffmanUncompress(File source, File destiny, HuffmanTreeIF treeHuffman) {
        super(source, destiny, treeHuffman);
    }

    /**
     * Faz a descompressao
     */
    public void uncompress() throws FileNotFoundException, IOException{
        /***
         * Sequencia:
         *
         * -> byte1 - numero de bytes "b" que diz quantos bytes foi necessario para guardar o tamanho original do arquivo (em bytes)
         * -> proximos "b" bytes - numero de bytes do arquivo original
         * -> restante - arquivo comprimido
         *
         ***/

        /*** Abre os arquivos ***/
        DataFile source = new DataFile(getSource());
        DataFile destiny = new DataFile(getDestiny());
        HuffmanTreeIF treeHuffman = getTreeHuffman();

        /*** ler o numero de bytes "b" que foi necessario para gravar o tamanho do arquivo original ***/
        byte numberOfBytesToOriginalFileLenght = source.readByte();
        byte[] originalFileLenghtArray = new byte[numberOfBytesToOriginalFileLenght];

        /*** Ler os "b" bytes para formar o tamanho do arquivo original em bytes ***/
        source.read(originalFileLenghtArray);

        /*** Esse e' o numero de bytes do arquivo original ***/
        long originalFileLengh = new BigInteger(originalFileLenghtArray).longValue();

        /*** O primeiro byte e' sempre o primeiro simbolo ***/
        byte readByte = source.readByte();
        destiny.write(readByte);
        treeHuffman.addOcurrency(readByte);

        try{
            /*** Ler o arquivo ate atingir o numero de simbolos original (despresa o restante do arquivo comprimido porque foram enchimento de byte) ***/
            for(long decodedSymbols = 1; decodedSymbols < originalFileLengh; decodedSymbols++){
                boolean[] atualEscapeCode = treeHuffman.getEscapeCode();
                boolean readBit = false;
                Byte symbol = null;
                boolean isEscape = false;
                boolean[] symbolCode = null;
int i = 0;
                do{
                    readBit = source.readBit();
                    symbolCode = addBitToArray(symbolCode, readBit);
                    isEscape = Arrays.equals(symbolCode, atualEscapeCode);
                    symbol = treeHuffman.getOriginalSymbol(symbolCode);
                    System.out.println("i = " + i++);
                    System.out.println("Symbol = " + symbol);
                    System.out.println("isEscape " + isEscape);
                    /*** Se o simbolo retorna pela arvore for nulo (nao existe) e nao for escape, repito ***/
                }while(symbol == null && !isEscape);

                /*** se for escape, ler os proximos 8 bits que significarao um simbolo ***/
                if(isEscape)
                    symbol = source.readByte();

                /*** escreve o simbolo ***/
                destiny.write(symbol);
                System.out.println("Adicionando ocorrencia do simbolo " + symbol);
                /*** adiciona uma ocorrencia do simbolo na arvore ***/
                treeHuffman.addOcurrency(symbol);
            }
        }catch(EOFException ex){
            throw new EOFException("More symbols was expected!");
        }
    }

    /**
     * Adiciona um novo bit no array
     * 
     * @param symbolCode o array onde deve ser adicionado o bit
     * @param bit o bit
     * 
     * @return o novo array
     */
    private boolean[] addBitToArray(boolean[] symbolCode, boolean bit) {
        boolean[] newArray = null;

        if(symbolCode != null){
            newArray = new boolean[symbolCode.length + 1];

            int i = 0;
            for (i = 0; i < symbolCode.length; i++)
                newArray[i] = symbolCode[i];

            newArray[i] = bit;
        }
        else
            newArray = new boolean[]{bit};
        
        return newArray;
    }
}
