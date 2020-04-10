package compressorAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Contem a especificacao que um compressor deve ter.
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
public interface CompressorIF {

    /**
     * A extensao do arquivo que o compressor utiliza.
     * 
     * @return a extensao do arquivo
     */
    public String getExtension();

    /**
     * Comprime o o array de bytes e retorna um array de bytes comprimidos
     *
     * @param sourceFile o arquivo fonte a ser comprimido
     * @param destinyFile onde o arquivo comprimido deve ser gravado
     */
    public void compress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException;

    /**
     * Descomprime os dados passados e retorna os dados descomprimidos.
     *
     * @param sourceFile o arquivo fonte a ser descomprimido
     * @param destinyFile onde o arquivo descomprimido deve ser gravado
     */
    public void uncompress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException;

    /**
     * Retorna o nome do compressor.
     *
     * @return o nome
     */
    public String getName();
}
