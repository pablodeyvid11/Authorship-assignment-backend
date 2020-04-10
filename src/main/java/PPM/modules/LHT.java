package PPM.modules;

import PPM.interfaces.LHTIF;

/**
 * Classe que representa o low, high e total de um simbolo em um determinado
 * contexto.
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
public class LHT implements LHTIF{

    private int low;
    private int high;
    private int total;

    /**
     * Construtor
     *
     * @param low o low
     * @param high o high
     * @param total o total
     */
    public LHT(int low, int high, int total) {
        this.low = low;
        this.high = high;
        this.total = total;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public int getTotal() {
        return total;
    }

}
