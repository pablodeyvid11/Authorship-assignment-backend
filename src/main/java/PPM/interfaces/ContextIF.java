package PPM.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.TreeMap;

import PPM.comparator.ByteComparator;

/**
 * Interface que representa um contexto do PPM.
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
public interface ContextIF extends Serializable{

    /**
     * O comparator a ser utilizado entre os bytes.
     */
    public static final Comparator<Byte> BYTE_COMPARATOR = new ByteComparator();

    /**
     * Retorna o contexto. Esse valor deve ser construido da concatenacao
     * dos bytes em um String.
     * 
     * byte a, b;
     * String context = ab -> new String(new byte[]{a, b})
     * 
     * @return o contexto
     */
    public String getContext();

    /**
     * Retorna os simbolos do contexto.
     *
     * Deve ser atribuido a esse mapa um Comparator que deve seguir as seguintes regras:
     * -> Se o primeiro byte for null e o segundo byte tambem for null, retorne 0, caso contrario retorne -1
     * -> Senao se o segundo byte for null, retorna 1, caso contrario retorne primeiro - segundo
     *
     * @return os simbolos
     */
    public TreeMap<Byte, SymbolIF> getSymbols();

    /**
     * Retorna o numero de ocorrencias de um simbolo.
     *
     * @param symbol o simbolo a ser buscado
     *
     * @return o numero de ocorrencias do simbolo ou -1 se ele nao existir no contexto
     */
    public int getSymbolOccurreces(Byte symbol);

    /**
     * Retorna o numero de ocorrencias do escape.
     *
     * @return o numero de ocorrencias do escape ou -1 se ele foi eliminado do contexto
     */
    public int getEscapeOccurrences();

    /**
     * Calcula e retorna o low, high total de um determinado simbolo.
     *
     * @param symbol o simbolo
     *
     * @return o low, high e total do simbolo ou null se o simbolo nao estiver presente
     */
    public LHTIF getLowHighTotal(Byte symbol);

    /**
     * Diz em qual faixa de simbolo o valor passado como argumento esta.
     *
     * @param arithmeticValue o valor
     *
     * @return o simbolo que o valor pertece a faixa
     */
    public SymbolIF getSymbol(BigDecimal arithmeticValue);

    /**
     * Pega o simbolo do contexto.
     *
     * @param symbol o byte
     *
     * @return o simbolo ou null caso ele nao exista
     */
    public SymbolIF getSymbol(Byte symbol);
    
    /**
     * Compara dois contextos. Essa comparacao deve ser feita utilizando apenas
     * o campo contexto.
     *
     * @param obj o no a ser comparado
     *
     * @return verdadeiro se os simbolos forem iguais e falso caso contrario
     */
    @Override
    boolean equals(Object obj);

    /**
     * Retorna o numero total de ocorrencias de simbolos no contexto.
     *
     * @return o numero de ocorrencias
     */
    public int getTotalOfOccurrences();

    /**
     * Incrementa um determinado simbolo e o total do contexto. Se o simbolo nao
     * existir, ele e' adicionado na lista e o escape e' incrementado.
     *
     * @param symbol o simbolo a ser incrementado (null para escape)
     */
    public void incrementSymbol(Byte symbol);

    /**
     * Retorna o simbolo referente a posicao.
     *
     * @param position a posicao do simbolo
     *
     * @return o simbolo ou null se a posicao passada estiver fora do intervalo
     */
    public Byte getSymbol(int indice);
}
