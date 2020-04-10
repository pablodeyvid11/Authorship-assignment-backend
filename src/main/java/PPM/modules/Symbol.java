package PPM.modules;

import java.io.Serializable;

import PPM.interfaces.SymbolIF;

/**
 * Representa um no da arvore PPM.
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
public class Symbol implements SymbolIF, Serializable {

    private final Byte symbol;
    private int occurrences;

    /**
     * Construtor da classe.
     *
     * @param symbol o simbolo
     * @param occurrences o numero de vezes que ele apareceu
     */
    public Symbol(Byte symbol, int occurrences) {
        this.symbol = symbol;
        this.occurrences = occurrences;
    }

    /**
     * Construtor que cria o symbolo com zero ocorrencias.
     * 
     * @param symbol o simbolo
     */
    public Symbol(Byte symbol) {
        this.symbol = symbol;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public Byte getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Symbol other = (Symbol) obj;
        if (this.symbol != other.symbol) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.symbol != null ? this.symbol : 0);
        return hash;
    }

    public int increment() {
        return occurrences++;
    }

    @Override
    public String toString() {
        return String.format("[Symbol: %s, occurrences: %d]", symbol + "", occurrences);
    }


}
