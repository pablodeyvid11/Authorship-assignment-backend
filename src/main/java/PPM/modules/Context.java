package PPM.modules;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import PPM.interfaces.ContextIF;
import PPM.interfaces.LHTIF;
import PPM.interfaces.SymbolIF;

/**
 * Representa um contexto do PPM.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 10 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class Context implements ContextIF, Serializable{

    private final String context;
    private final TreeMap<Byte, SymbolIF> symbols = new TreeMap<Byte, SymbolIF>(BYTE_COMPARATOR);
    private final SymbolIF escape = new Symbol(null);
    private int totalNumberOfOccurrences;

    /**
     * Construtor da classe.
     *
     * @param context o contexto
     */
    public Context(String context) {
        this.context = context;
    }

    /**
     * Construtor da classe que ja adiciona o escape e o simbolo
     * passado como argumento no contexto.
     *
     * @param context o contexto
     * @param firstSymbol o primeiro simbolo do contextot
     */
    public Context(String context, Byte firstSymbol) {
        this.context = context;
        incrementSymbol(firstSymbol);
    }

    /**
     * Construtor da classe.
     *
     * @param context o contexto
     */
    public Context(byte[] context) {
        this.context = context != null ? new String(context) : null;
    }

    /**
     * Construtor da classe que ja adiciona o escape e o simbolo
     * passado como argumento no contexto.
     *
     * @param context o contexto
     * @param firstSymbol o primeiro simbolo do contextot
     */
    public Context(byte[] context, Byte firstSymbol) {
        this.context = context != null ? new String(context) : null;
        incrementSymbol(firstSymbol);
    }

    /**
     * Construtor da classe.
     *
     * @param context o contexto
     */
    public Context(Byte[] context) {
        byte[] b = null;

        if(context != null){
            b = new byte[context.length];

            for(int i = 0; i < context.length; i++)
                b[i] = context[i];
        }

        this.context = b != null ? new String(b) : null;
    }

    /**
     * Construtor da classe que ja adiciona o escape e o simbolo
     * passado como argumento no contexto.
     *
     * @param context o contexto
     * @param firstSymbol o primeiro simbolo do contextot
     */
    public Context(Byte[] context, Byte firstSymbol) {
        byte[] b = null;

        if(context != null){
            b = new byte[context.length];

            for(int i = 0; i < context.length; i++)
                b[i] = context[i];
        }
        
        this.context = b != null ? new String(b) : null;

        incrementSymbol(firstSymbol);
    }

    public String getContext() {
        return context;
    }

    public TreeMap<Byte, SymbolIF> getSymbols() {
        return symbols;
    }

    public int getSymbolOccurreces(Byte symbol) {
        SymbolIF symbolIF = symbols.get(symbol);

        return symbolIF != null ? symbols.get(symbol).getOccurrences() : 0;
    }

    public int getEscapeOccurrences() {
        return escape.getOccurrences();
    }

    public LHTIF getLowHighTotal(Byte symbol) {
//        if(!symbols.containsKey(symbol))
//            return null;

        SortedMap<Byte, SymbolIF> tail = symbols.tailMap(symbol, false);
        SortedMap<Byte, SymbolIF> head = symbols.headMap(symbol);
        SortedMap<Byte, SymbolIF> workedList = null;
        boolean useHead = false;

        if(head.size() <= tail.size()){
            useHead = true;
            workedList = head;
        }else
            workedList = tail;

        Collection<SymbolIF> values = workedList.values();
        SymbolIF foundSymbol = getSymbol(symbol);
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

        //System.out.println("("+low+", "+high+", "+totalNumberOfOccurrences+")");
        return new LHT(low, high, totalNumberOfOccurrences);
    }

    @Deprecated
    public SymbolIF getSymbol(BigDecimal arithmeticValue) {
        Collection<SymbolIF> list = symbols.values();

        for (SymbolIF symbolIF : list) {
                arithmeticValue = arithmeticValue.subtract(BigDecimal.valueOf(symbolIF.getOccurrences()));
                if(arithmeticValue.signum()<0){
                    return symbolIF;
                }
        }
        
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Context other = (Context) obj;
        if ((this.context == null) ? (other.context != null) : !this.context.equals(other.context)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.context != null ? this.context.hashCode() : 0);
        return hash;
    }

    public int getTotalOfOccurrences() {

        //System.out.println("Total="+totalNumberOfOccurrences);
        return totalNumberOfOccurrences;
    }

    public void incrementSymbol(Byte symbol) {
        if(symbol == null)
            escape.increment();
        else if(symbols.size() == 0){
                SymbolIF symbolIF = new Symbol(symbol, 1);
                escape.increment();

                symbols.put(escape.getSymbol(), escape);
                symbols.put(symbolIF.getSymbol(), symbolIF);

                //Em relacao ao escape
                totalNumberOfOccurrences++;
        }else if(!symbols.containsKey(symbol)){
            SymbolIF symbolIF = new Symbol(symbol, 1);
            escape.increment();

            symbols.put(symbolIF.getSymbol(), symbolIF);
            totalNumberOfOccurrences++;
        }else
            symbols.get(symbol).increment();

        if(symbols.size() > 256){
            symbols.remove(escape.getSymbol());
            totalNumberOfOccurrences -= escape.getOccurrences();
        }
        
        totalNumberOfOccurrences++;
    }

    public SymbolIF getSymbol(Byte symbol) {
        return symbols.get(symbol);
    }

    public Byte getSymbol(int value) {
        Collection<SymbolIF> list = symbols.values();

        for (SymbolIF symbolIF : list) {
                value -= symbolIF.getOccurrences();
                if(value<0){
                    return symbolIF.getSymbol();
                }
    }
        return null;
    }

    @Override
    public String toString() {
        return String.format("Id: %s, total of occurrences: %d, symbols: %s", context, totalNumberOfOccurrences, symbols);
    }

}
