package PPM.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import PPM.PPM;
import PPM.interfaces.ContextIF;
import PPM.interfaces.SymbolIF;
import PPM.modules.AbstractPPMModule;
import PPM.modules.Symbol;

/**
 * Classe de testes.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 9 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class Main {

    /**
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        encoderDecoderTest();
    }

    private static void encoderDecoderTest() throws FileNotFoundException, IOException{
        String original = "teste.jpg";
        String ida = "ida.jpg.ppm";
        String volta = "volta.jpg";

        encoderTest(original, ida);
        decoderTest(ida, volta);
    }

    public static String toString(byte b){
        byte[] c = new byte[]{b};
        return new String(c);
    }

    public static byte toByte(char c){
        return (byte) c;
    }

    public static void printArray(Byte[] b){
        if(b == null)
            System.out.print("null");
        else
            for (Byte byte1 : b) {
                System.out.print(byte1);
            }

         System.out.println("");
    }

    private static void kMinusOneCreateTest(){
        System.out.println(">> MIN: " + Byte.MIN_VALUE);
        System.out.println(">> MAX: " + Byte.MAX_VALUE);
        java.util.LinkedList<Byte> symbols = new java.util.LinkedList<Byte>();

        for(byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++){
            symbols.add(b);
            System.out.println("Adicionado: " + b);
        }

        symbols.add(Byte.MAX_VALUE);

        System.out.println(">> " + symbols);
    }

    private static void encoderTest(String original, String ida) throws FileNotFoundException, IOException{
        PPM ppm = new PPM();
        File sourceFile = new File(original);
        File destinyFile = new File(ida);

        ppm.compress(sourceFile, destinyFile);
    }

    private static void decoderTest(String ida, String volta) throws FileNotFoundException, IOException{
        PPM ppm = new PPM();
        File sourceFile = new File(ida);
        File destinyFile = new File(volta);

        ppm.uncompress(sourceFile, destinyFile);
    }
    
    private static Byte[] lookBack = null;
    private static int numberOfContexts = 2;
    
    private static void updateLookBack(Byte newSymbol){
        if(newSymbol == null)
            throw new IllegalArgumentException("newSymbol can't be null!");

        /*** Se nao havia lookBack ***/
        if(lookBack == null){
            lookBack = new Byte[]{newSymbol};
            return;
        }

        /*** Se o lookBack nao estava totalmente preenchido com o numero de contextos ***/
        if(lookBack.length < numberOfContexts){
            Byte[] newLookBack = new Byte[lookBack.length + 1];

            for(int i = 0; i < lookBack.length; i++)
                newLookBack[i] = lookBack[i];

            newLookBack[newLookBack.length - 1] = newSymbol;
            lookBack = newLookBack;

            return;
        }

        /*** Se o lookBack tem o tamanho do numero de contextos (faz um shift) ***/
        Byte[] newLookBack = new Byte[numberOfContexts];

        for(int i = 0; i < numberOfContexts - 1; i++)
            newLookBack[i] = lookBack[i + 1];

        newLookBack[numberOfContexts - 1] = newSymbol;
        lookBack = newLookBack;
    }

    private static void decrementLoopBackTest(){
        byte[] b = "ABAC".getBytes();
        Byte[] B = AbstractPPMModule.toByte(b);

        System.out.print(">> original: ");
        printArray(B);

        B = AbstractPPMModule.decrementLookBack(B);
        System.out.print(">> decrementando: ");
        printArray(B);

        B = AbstractPPMModule.decrementLookBack(B);
        System.out.print(">> decrementando: ");
        printArray(B);

        B = AbstractPPMModule.decrementLookBack(B);
        System.out.print(">> decrementando: ");
        printArray(B);

        B = AbstractPPMModule.decrementLookBack(B);
        System.out.print(">> decrementando: ");
        printArray(B);

    }
    
    private static void getLowHighTotalTest() {
//        if(!symbols.containsKey(symbol))
//            return null;
        Byte symbol = null;
        int totalNumberOfOccurrences = 4;
        TreeMap<Byte, SymbolIF> symbols = new TreeMap<Byte, SymbolIF>(ContextIF.BYTE_COMPARATOR);

        symbols.put(null, new Symbol(null, 1));
        symbols.put((byte) 65, new Symbol((byte) 65, 3));

        SortedMap<Byte, SymbolIF> tail = symbols.tailMap(symbol, false);
        SortedMap<Byte, SymbolIF> head = symbols.headMap(symbol);
        SortedMap<Byte, SymbolIF> workedList = null;

        boolean useHead = false;

        if(head.size() >= tail.size()){
            useHead = true;
            workedList = head;
        }else
            workedList = tail;

        Collection<SymbolIF> values = workedList.values();
        SymbolIF foundSymbol = symbols.get(symbol);
        int low = 0;
        int high = 0;

        for (SymbolIF symbolIF : values) {
            if(useHead)
                low += symbolIF.getOccurrences();
            else
                high += symbolIF.getOccurrences();
        }

        if(useHead)
            high = low + foundSymbol.getOccurrences();
        else{
            high = totalNumberOfOccurrences - high;
            low = high - foundSymbol.getOccurrences();
        }

        System.out.println(String.format(">> %d %d %d", low, high, totalNumberOfOccurrences));
    }
}
