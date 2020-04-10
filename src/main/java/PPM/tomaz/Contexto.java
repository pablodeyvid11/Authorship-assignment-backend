/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PPM.tomaz;

import java.util.Comparator;
import java.util.TreeMap;

/**
 *
 * @author Usuário
 */
public class Contexto  {
    private static final Comparator<Byte> ORDEM = new Comparator<Byte>(){

            public int compare(Byte o1, Byte o2) {
                if (o1 == null)
                    return o2 == null ? 0 : -1;
                else
                    return o2 == null ? 1 : o1-o2;
            }

    };
    private final TreeMap<Byte,Contador> simbolos = new TreeMap<Byte, Contador>(ORDEM);
    private static final SimbolAux simbolAux = new SimbolAux();
    private final String value;
    private final Contador escape = new Contador();
    private int sum;

    public Contexto(String value) {
        this.value = value;
        simbolos.put(null, escape);
    }

    public String getKey() {
        return value;
    }

    public Contexto getValue() {
        return this;
    }

    public Contexto setValue(Contexto value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void operaSimbol(byte simb) {
        setSimbolAux(simb);
        if (!simbolAux.wasFound()) {
            put(simb);
            incScape();
        }
        increment();
    }

    private void setSimbolAux(Byte simb) {
        simbolAux.setValores(simb, simbolos.get(simb));
    }

    private void increment() {
        simbolAux.increment();
        sum++;
    }

    private void put(byte simb) {
        simbolAux.setValores(simb, new Contador());
        simbolos.put(simb, simbolAux.getContador());
    }

    private void incScape() {
        escape.increment();
    }
}
