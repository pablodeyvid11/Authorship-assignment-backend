package PPM.interfaces;

import java.io.Serializable;

/**
 * Interface que representa um no da arvore PPM.
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
public interface SymbolIF extends Serializable{

    /**
     * Compara dois nos. Essa comparacao deve ser feita utilizando apenas
     * o campo simbolo do no. Deve-se levar em consideracao que o escape tem
     * esse campo como null.
     *
     * @param obj o no a ser comparado
     *
     * @return verdadeiro se os simbolos forem iguais e falso caso contrario
     */
    @Override
    boolean equals(Object obj);

    /**
     * Retorna o numero de ocorrencias do simbolo
     *
     * @return as ocorrencias
     */
    int getOccurrences();

    /**
     * Retorna o simbolo.
     *
     * @return o simbolo ou null se for o escape
     */
    Byte getSymbol();

    /**
     * Incrementa as ocorrencias do simbolo.
     *
     * @return retorna o numero de ocorrencias antes do incremento
     */
    int increment();

}
