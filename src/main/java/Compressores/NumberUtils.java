package Compressores;

/**
 * Classe que contem metodos para manipulacao de numeros.
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
public class NumberUtils {

    /**
     * Construtor e' privado.
     */
    private NumberUtils() {
    }

    /**
     * Retorna o numero de bits que sao necessarios para representar o numero
     *
     * @param number o numero
     *
     * @return o numero de bits
     */
    public static long numberOfBits(long number){
        int count = 0;

        while(number != 0){
            number = number >> 1;
            count++;
        }

        return count;
    }

    /**
     * Retorna o numero de bytes que sao necessarios para representar o numero
     *
     * @param number o numero
     *
     * @return o numero de bytes
     */
    public static byte numberOfBytes(long number){
        long numberOfBits = numberOfBits(number);
        byte numberOfBytes = 0;

        while(numberOfBits > 0){
            numberOfBits -= 8;
            numberOfBytes++;
        }

        return numberOfBytes;
    }

    /**
     * Converte de bits pra byte
     *
     * @param bits 8 bits
     *
     * @return o byte correspondente ou 0 caso nao sejam 8 bits ou for nulo
     */
    public static byte toByte(boolean[] bits){
        if(bits == null || bits.length != 8)
            return 0;

        byte result = 0;

        for(int i = 7; i >= 0; i--)
            if(bits[i])
                result += Math.pow(2, 7 - i);

        return result;
    }

    /**
     * Converte um byte para array de bit
     *
     * @param b o byte a ser convertido
     *
     * @return um arra com 8 posicoes
     */
    public static boolean[] toBits(byte b){
        char array[] = Integer.toBinaryString(b).toCharArray();
        boolean[] bits = new boolean[8];
        
        for(int i = 7, j = array.length - 1; j >= 0 && i >=0 ; i--, j--){
            bits[i] = array[j] == '1' ? true : false;
        }

        return bits;
    }
}
