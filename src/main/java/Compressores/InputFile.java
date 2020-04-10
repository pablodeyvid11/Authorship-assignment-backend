package Compressores;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Classe para manipulacao de arquivo de dados. Com essa classe e possivel ler
 * alguns tipos do arquivo ate o seu final.
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
public class InputFile {

    private String path;
    private File file;
    private DataInputStream dataInputStream;
    private FileInputStream fileInputStream;

    /**
     * Construtor da classe.
     *
     * @param path o caminho do arquivo
     */
    public InputFile(String path) throws FileNotFoundException {
        this(new File(path));
    }

    /**
     * Construtor.
     *
     * @param file o arquivo
     */
    public InputFile(File file) throws FileNotFoundException {
        if(file == null || file.isDirectory())
            throw new IllegalArgumentException("File can't be null or directory!");

        this.file = file;
        this.path = file.getPath();
        this.fileInputStream = new FileInputStream(file);
        this.dataInputStream = new DataInputStream(fileInputStream);
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
     * Ler um long do arquivo na posicao em que o apontador se encontra
     *
     * @return o long lido ou uma EOFException
     */
    public long readLong() throws IOException{
        return dataInputStream.readLong();
    }

    /**
     * Ler um int do arquivo na posicao em que o apontador se encontra
     *
     * @return o int lido ou uma EOFException
     */
    public int readInt() throws IOException{
        return dataInputStream.readInt();
    }

    /**
     * Ler um double do arquivo na posicao em que o apontador se encontra
     *
     * @return o double lido ou uma EOFException
     */
    public double readDouble() throws IOException{
        return dataInputStream.readDouble();
    }

    /**
     * Ler um BigInteger do arquivo na posicao em que o apontador se encontra
     *
     * @return o BigInteger lido ou uma EOFException
     */
    public BigInteger readBigInteger() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line = bufferedReader.readLine();
        return new BigInteger(line);
    }

    /**
     * Ler um BigDecimal do arquivo na posicao em que o apontador se encontra
     *
     * @return o BigDecimal lido ou uma EOFException
     */
    public BigDecimal readBigDecimal() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line = bufferedReader.readLine();
        return new BigDecimal(line);
    }

    /**
     * O numero de bytes que ainda podem ser lidos
     *
     * @return o numero de bytes
     */
    public int avaliable() throws IOException{
        return dataInputStream.available();
    }

    public void close() throws IOException{
        dataInputStream.close();
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }
}
