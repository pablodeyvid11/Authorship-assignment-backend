package Compressores;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe para manipulacao de arquivo de dados. Com essa classe e possivel ler
 * e bytes do arquivo ate o seu final.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 11 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class ByteFile {

    private String path;
    private File file;
    private DataInputStream dataInputStream;

    /**
     * Construtor da classe.
     *
     * @param path o caminho do arquivo
     */
    public ByteFile(String path) throws FileNotFoundException {
        this(new File(path));
    }

    /**
     * Construtor.
     *
     * @param file o arquivo
     */
    public ByteFile(File file) throws FileNotFoundException {
        if(file == null || !file.isFile())
            throw new IllegalArgumentException("File can't be null or not file!");

        this.file = file;
        this.path = file.getPath();
        this.dataInputStream = new DataInputStream(new FileInputStream(file));
    }

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    /**
     * Ler um byte do arquivo na posicao em que o apontador se encontra
     *
     * @return o byte lido ou uma EOFException
     */
    public byte readByte() throws IOException{
        return dataInputStream.readByte();
    }

    /**
     * O numero de bytes que ainda podem ser lidos
     *
     * @return o numero de bytes
     */
    public int avaliable() throws IOException{
        return dataInputStream.available();
    }
}
