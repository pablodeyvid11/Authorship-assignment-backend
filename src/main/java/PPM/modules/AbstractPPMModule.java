package PPM.modules;

import java.io.File;
import java.util.Arrays;
import java.util.TreeMap;

import PPM.interfaces.ContextIF;
import PPM.interfaces.PPMModuleIF;

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
public abstract class AbstractPPMModule implements PPMModuleIF{

    /**
     * Numero de contextos default.
     */
    public static int DEFAULT_NUMBER_OF_CONTEXTS = 3;

    /**
     * Essa variavel diz se e' para utilizar compressao continua chamando um metodo do
     * artimetico para o descompreessor saber quando deve parar ou escrever o numero
     * de bytes do arquivo original no arquivo de saida e fazer a descompressao
     * ate atingir esse numero.
     *
     * Valores:
     * true - usa o metodo do aritimetitco
     * false - insere o numero de bytes no arquivo de saida
     */
    public static final boolean DO_USE_CONTINOUS_COMPRESS = false;

    private final int numberOfContexts;
    private final TreeMap<String, ContextIF> contexts = new TreeMap<String, ContextIF>(STRING_COMPARATOR);
    private ContextKMinusOne contextKMinusOne = new ContextKMinusOne();
    private Byte[] lookBack;
    private transient File sourceFile;
    private transient File destinyFile;

    /**
     * Construtor da classe.
     *
     * @param numberOfContext o numero de contextos utilizado
     */
    public AbstractPPMModule(File sourceFile, File destinyFile, int numberOfContext) {
        if(numberOfContext < 0)
            throw new IllegalArgumentException("Only positive values!");

        if(sourceFile == null)
            throw new IllegalArgumentException("Source file can't be null!");

        if(destinyFile.isDirectory())
            throw new IllegalArgumentException("Destiny file can't be a directory!");

        if(destinyFile.exists())
            destinyFile.delete();

        this.numberOfContexts = numberOfContext;
        this.sourceFile = sourceFile;
        this.destinyFile = destinyFile;

        System.out.println(">> k = " + numberOfContexts);
    }

    /**
     * Construtor da classe que cria o modulo com numero de contextos default.
     */
    public AbstractPPMModule(File sourceFile, File destinyFile) {
        this(sourceFile, destinyFile, DEFAULT_NUMBER_OF_CONTEXTS);
    }

    /**
     * Construtor da classe que cria o modulo com numero de contextos default.
     */
    public AbstractPPMModule() {
        this.numberOfContexts = DEFAULT_NUMBER_OF_CONTEXTS;
    }

    public TreeMap<String, ContextIF> getContexts() {
        return contexts;
    }

    public Byte[] getLookBack() {
        return lookBack;
    }

    public void addContext(Byte[] context) {
        String stringContext = context != null ? toString(context) : null;

        if(!contexts.containsKey(stringContext)){
            ContextIF newContext = new Context(context);
            contexts.put(stringContext, newContext);
        }
    }

    public void addContext(Byte[] context, Byte firstSymbol) {
        String stringContext = context != null ? toString(context) : null;

        if(!contexts.containsKey(stringContext)){
            ContextIF newContext = new Context(context, firstSymbol);
            contexts.put(stringContext, newContext);
        }
    }

    public ContextIF getContext(Byte[] context) {
        String stringContext = toString(context);
        return contexts.get(stringContext);
    }

    public int getNumberOfContexts() {
        return numberOfContexts;
    }

    /**
     * Converte de bytes primitivos para String
     *
     * @param bytes os bytes
     * @return o String
     * @deprecated Nao converte adequadamente bytes para strings
     */
    @Deprecated
    public static String toString(byte[] bytes){
        return bytes != null ? new String(bytes) : null;
    }

    /**
     * Converte de bytes primitivos para String
     *
     * @param bytes os bytes
     * @return o String
     */
    public static String toString(Byte[] bytes){
        if(bytes == null)
            return null;

        char[] b = toChar(bytes);
        return new String(b);
    }

    /**
     * Converte Bytes para bytes.
     *
     * @param bytes bytes em objeto
     *
     * @return bytes primitivos ou null caso a entrada seja nula
     */
    public static char[] toChar(Byte[] bytes){
        if(bytes == null)
            return null;

        char[] b = new char[bytes.length];

        for(int i = 0; i < bytes.length; i++)
            b[i] = (char)(byte) bytes[i];

        return b;
    }

    /**
     * Converte bytes para Bytes.
     *
     * @param bytes bytes primitivos
     *
     * @return bytes objetos ou null caso a entrada seja nula
     */
    public static Byte[] toByte(byte[] bytes){
        if(bytes == null)
            return null;

        Byte[] b = new Byte[bytes.length];

        for(int i = 0; i < bytes.length; i++)
            b[i] = bytes[i];

        return b;
    }

    /**
     * Decrementa o lookBack em um, eliminando a primeira posicao.
     *
     * @param lookBack o lookBack atual
     *
     * @return o lookBack sem a primeira posicao
     */
    public static Byte[] decrementLookBack(Byte[] lookBack){
        if(lookBack == null || lookBack.length <= 1)
            return null;

        return Arrays.copyOfRange(lookBack, 1, lookBack.length);
    }

    public ContextKMinusOne getContextKMinusOne() {
        return contextKMinusOne;
    }

    public File getDestinyFile() {
        return destinyFile;
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void updateLookBack(Byte newSymbol){
        if(newSymbol == null)
            throw new IllegalArgumentException("newSymbol can't be null!");

        if(numberOfContexts > 0){
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
    }
    
    public void setDestinyFile(File destiny){
        this.destinyFile = destiny;
    }

    public void setSourceFile(File source){
        this.sourceFile = source;
    }

}
