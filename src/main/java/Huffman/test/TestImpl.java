package Huffman.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import Compressores.NumberUtils;

/**
 * Classe de testes do compressor Huffman.
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
public class TestImpl {

    /**
     * Metodo inicial dos testes.
     *
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        //parteInteira(log2(numero) + 1)

        long x = 255;
//        System.out.println(">> " + Math.round(log2(x) + 1));

//        System.out.println(">> " + x + " - " + NumberUtils.numberOfBytes(x));

        boolean[] b = new boolean[]{true, false, false, false, false, false, false, true};
        byte bb = NumberUtils.toByte(b);
        System.out.println(">> " + bb);

        print(NumberUtils.toBits(bb));
    }

    public static void print(boolean[] b){
        for (boolean c : b) {
            System.out.print(c);
        }

        System.out.println("");
    }
    
    public static double log2(double x){
        return Math.log10(x)/Math.log10(2);
    }

    public static long n(long x){
        int count = 0; //ou começa com 0 ou com 1, nao lembro
        
        while(x != 0){
            x = x >> 1;
            count++;
        }

        return count;
    }
}
