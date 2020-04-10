package PPM.interfaces;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.TreeMap;

import PPM.comparator.StringComparator;
import PPM.modules.ContextKMinusOne;

/**
 * Representa um modulo do PPM.
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
public interface PPMModuleIF extends Serializable{

    /**
     * O comparator a ser utilizado entre os contextos.
     */
    public static final Comparator<String> STRING_COMPARATOR = new StringComparator();
    
    /**
     * Retorna os contextos do modulo.
     *
     * Deve ser atribuido a esse mapa um Comparator que deve seguir as seguintes regras:
     * -> Se o primeiro String for null e o segundo String tambem for null, retorne 0, caso contrario retorne -1
     * -> Senao se o segundo Sring for null, retorna 1, caso contrario retorne primeiro - segundo
     *
     * @return os contextos
     */
    public TreeMap<String, ContextIF> getContexts();

    /**
     * Adiciona um novo contexto.
     *
     * @param context a identificacao do contexto
     */
    public void addContext(Byte[] context);

    /**
     * Adiciona um novo contexto e ja adiciona o primeiro simbolo no contexto
     *
     * @param context a identificacao do contexto
     * @param firstSymbol o primeiro simbolo do contexto
     */
    public void addContext(Byte[] context, Byte firstSymbol);

    /**
     * Retorna o contexto.
     *
     * @param context a identificacao do contexto
     *
     * @return o contexto ou null se nao existir
     */
    public ContextIF getContext(Byte[] context);

    /**
     * Retorna os ultimos bytes lidos
     *
     * K - numero de contextos
     *
     * @return os K ultimos bytes lidos
     */
    public Byte[] getLookBack();

    /**
     * Retorna o numero de contextos utilizados.
     *
     * @return o numero de contextos
     */
    public int getNumberOfContexts();

    /**
     * Retorna o contexto k - 1.
     *
     * @return o contexto k - 1.
     */
    public ContextKMinusOne getContextKMinusOne();

    /**
     * Atualiza o lookBack com o novo simbolo.
     *
     * Antes: ABC e recebeu D
     * Fica: BCD
     *
     * @param newSymbol o simbolo
     */
    public void updateLookBack(Byte newSymbol);

    /**
     * Retorna o arquivo destino.
     *
     * @return o arquivo destino
     */
    public File getDestinyFile();

    /**
     * Retorna o arquivo fonte.
     *
     * @return o arquivo fonte
     */
    public File getSourceFile();

    /**
     * Seta o arquivo destino.
     *
     * @param destiny o arquivo destino
     */
    public void setDestinyFile(File destiny);

    /**
     * Seta o arquivo fonte.
     *
     * @param source o arquivo fonte
     */
    public void setSourceFile(File source);
}
