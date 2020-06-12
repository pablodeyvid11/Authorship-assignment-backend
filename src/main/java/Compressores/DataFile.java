package Compressores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataFile {

	private final String path;
	private DataInputStream in;
	private DataOutputStream out;
	private File file;
	private BitInput bitInput;
	private BitOutput bitOutput;
	private boolean append;
	private boolean alreadyReadBit = true;
	private boolean alreadyWriteBit = true;

	/**
	 * Construtor da classe.
	 *
	 * @param path o nome do arquivo a ser aberto
	 */
	public DataFile(String path) {
		this(new File(path));
	}

	/**
	 * Construtor da classe
	 *
	 * @param file o arquivo a ser aberto
	 */
	public DataFile(File file) {
		this.file = file;
		this.path = file.toString();
	}

	/**
	 * Construtor da classe.
	 *
	 * @param path   o nome do arquivo a ser aberto
	 * @param append verdadeiro para manter o conteudo do arquivo e falso caso
	 *               contrario
	 */
	public DataFile(String path, boolean append) {
		this(new File(path));
	}

	/**
	 * Construtor da classe
	 *
	 * @param file   o arquivo a ser aberto
	 * @param append verdadeiro para manter o conteudo do arquivo e falso caso
	 *               contrario
	 */
	public DataFile(File file, boolean append) {
		this.file = file;
		this.path = file.toString();
	}

	/**
	 * Abre o fluxo de leitura do arquivo
	 */
	public void openInputFlow() throws FileNotFoundException, IOException {
		if (in == null) {
			in = new DataInputStream(new FileInputStream(file));
			// bitInput = new BitInput(in);
		}
	}

	/**
	 * Abre o fluxo de escrita no arquivo
	 */
	public void openOutputFlow() throws FileNotFoundException {
		if (out == null) {
			out = new DataOutputStream(new FileOutputStream(file, append));
			bitOutput = new BitOutput(out);
		}
	}

	/**
	 * Fecha o fluxo de leitura do arquivo
	 */
	public void closeInputFlow() throws IOException {
		if (in != null) {
			in.close();

			if (bitInput != null)
				bitInput.close();

			bitInput = null;
			in = null;
		}
	}

	/**
	 * Fecha o fluxo de escrita no arquivo
	 */
	public void closeOutputFlow() throws IOException {
		if (out != null) {
			flush();

			if (bitOutput != null)
				bitOutput.close();
			out.close();

			bitOutput = null;
			out = null;
		}
	}

	/**
	 * Retorna o stream para leitura do arquivo
	 *
	 * @return o stream de leitura
	 */
	public DataInputStream getDataInput() throws FileNotFoundException, IOException {
		openInputFlow();
		return in;
	}

	/**
	 * Retorna o stream de escrita no arquivo
	 *
	 * @return o stream de escrita
	 */
	public DataOutputStream getDataOutput() throws FileNotFoundException {
		openOutputFlow();
		return out;
	}

	/**
	 * Ler um byte do arquivo na posicao em que o apontador se encontra
	 *
	 * @return o byte lido ou uma EOFException
	 */
	public byte readByte() throws FileNotFoundException, IOException {
		if (alreadyReadBit)
			return readByteThroughBits();

		openInputFlow();
		byte b = in.readByte();
		//System.out.println("\nByte lido " + b);
		return b;
	}

	/**
	 * Ler um byte do arquivo na posicao em que o apontador se encontra atraves de
	 * varias leituras de bits
	 *
	 * @return o byte lido ou uma EOFException
	 */
	public byte readByteThroughBits() throws FileNotFoundException, IOException {
		openInputFlow();
		boolean[] bits = new boolean[8];

		if (bitInput == null)
			bitInput = new BitInput(in);

		for (int i = 0; i < 8; i++)
			bits[i] = bitInput.readBit();

		byte b = NumberUtils.toByte(bits);
		//System.out.println("\nByte lido atraves de bits " + b);
		return b;
	}

	/**
	 * Ler uma quantidade de bytes do arquivo na posicao em que o apontador se
	 * encontra
	 *
	 * @param b o array a ser preenchido
	 *
	 * @return o numero de bytes lidos
	 */
	public int read(byte b[]) throws FileNotFoundException, IOException {
		openInputFlow();

		//System.out.println("Tamanho de b " + b.length);
		int i = 0;

		if (alreadyReadBit) {
			for (i = 0; i < b.length; i++) {
				b[i] = readByteThroughBits();
			}
		} else
			i = in.read(b);

		System.out.print("b = ");
		for (byte c : b) {
			System.out.print(c);
		}

		return i;
	}

	/**
	 * Escreve um byte no final do arquivo
	 * 
	 * @param b o byte a ser escrito
	 */
	public void write(byte b) throws FileNotFoundException, IOException {
		openOutputFlow();
		//System.out.println("\nEscrevendo byte " + b);

		if (alreadyWriteBit)
			writeThroughBits(b);
		else
			out.writeByte(b);
	}

	/**
	 * Escreve um long no final do arquivo
	 *
	 * @param l o long a ser escrito
	 */
//    public void write(long l) throws FileNotFoundException, IOException{
//        openOutputFlow();
//        System.out.println("Escrevendo Long " + l);
//        out.writeLong(l);
//    }

	/**
	 * Escreve um long no final do arquivo
	 *
	 * @param l o long a ser escrito
	 */
	public void writeThroughBits(byte b) throws FileNotFoundException, IOException {
		openOutputFlow();
		//System.out.println("Escrevendo byte atraves de bits" + b);

		boolean[] bits = NumberUtils.toBits(b);
		writeBit(bits);
	}

	/**
	 * Escreve um array de bytes no final do arquivo
	 *
	 * @param bs os bytes a ser escrito
	 */
	public void write(byte[] bs) throws FileNotFoundException, IOException {
		openOutputFlow();

		if (alreadyWriteBit)
			for (byte b : bs)
				writeThroughBits(b);
		else
			out.write(bs);
	}

	/**
	 * Ler um char do arquivo na posicao em que o apontador se encontra
	 *
	 * @return o char lido
	 */
//    public char readChar() throws FileNotFoundException, IOException{
//        openInputFlow();
//        return in.readChar();
//    }

	/**
	 * Escreve um char no final do arquivo
	 *
	 * @param c o char a ser escrito
	 */
//    public void write(char c) throws FileNotFoundException, IOException{
//        openOutputFlow();
//        out.writeChar(c);
//    }

	/**
	 * Escreve um array de char no final do arquivo
	 *
	 * @param cs os chars a seres escritos
	 */
//    public void write(char[] cs) throws FileNotFoundException, IOException{
//        for(char c : cs)
//            write(c);
//    }

	/**
	 * Retorna o path do arquivo
	 *
	 * @return o path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Retorna a extensao do arquivo
	 *
	 * @return a extensao do arquivo
	 */
	public String getExtension() {
		return path.substring(path.lastIndexOf(".") + 1, path.length());
	}

	/**
	 * Retorna o tamanho do arquivo em bytes
	 *
	 * @return o tamanho
	 */
	public long getSize() {
		return file.length();
	}

	/**
	 * Retorna todo o conteudo do arquivo
	 *
	 * @return um array de bytes com o conteudo do arquivo
	 */
//    public byte[] getAllBytes() throws FileNotFoundException, IOException{
//        openInputFlow();
//        in.reset();
//
//        List<Byte> bigBytes = new ArrayList<Byte>();
//
//        while(true){
//            try{
//                bigBytes.add(readByte());
//            }catch(EOFException ex){
//                break;
//            }
//        }
//
//        byte[] litleBytes = new byte[bigBytes.size()];
//
//        for(int i = 0; i < bigBytes.size(); i++)
//            litleBytes[i] = bigBytes.get(i);
//
//        closeInputFlow();
//        return litleBytes;
//    }

	/**
	 * Retorna todo o conteudo do arquivo
	 *
	 * @return um array de chars com o conteudo do arquivo
	 */
//    public char[] getAllChars() throws FileNotFoundException, IOException{
//        openInputFlow();
//        in.reset();
//
//        List<Character> bigChars = new ArrayList<Character>();
//
//        while(true){
//            try{
//                bigChars.add(readChar());
//            }catch(EOFException ex){
//                break;
//            }
//        }
//
//        char[] litleChars = new char[bigChars.size()];
//
//        for(int i = 0; i < bigChars.size(); i++)
//            litleChars[i] = bigChars.get(i);
//
//        closeInputFlow();
//        return litleChars;
//    }

	/**
	 * Retorna o arquivo
	 *
	 * @return uma instancia de file
	 */
	public File getFile() {
		return file;
	}

	public String getName() {
		if (!path.contains(File.separator))
			return path;

		String a = path.substring(path.lastIndexOf(File.separator) + 1);
		return path.substring(path.lastIndexOf(File.separator) + 1);
	}

	/**
	 * Retorna o stream para leitura de bits
	 *
	 * @return o stream
	 */
	public BitInput getBitInput() {
		return bitInput;
	}

	/**
	 * Retorna o stream para escrita de bits
	 *
	 * @return o stream
	 */
	public BitOutput getBitOutput() {
		return bitOutput;
	}

	/**
	 * Escreve um bit no arquivo
	 *
	 * @param bits verdadeiro para 1 e falso para 0
	 */
	public void writeBit(boolean bit) throws FileNotFoundException, IOException {
		openOutputFlow();
		//System.out.println("\nEscrevendo bit " + bit);
		bitOutput.writeBit(bit);
	}

	/**
	 * Escreve um bit no arquivo
	 *
	 * @param bits verdadeiro para 1 e falso para 0
	 */
	public void writeBit(boolean[] bits) throws FileNotFoundException, IOException {
		openOutputFlow();
		//System.out.print("\nEscrevendo bitS ");

		for (boolean b : bits) {
			System.out.print(b + "");
		}

		//System.out.println("");

		for (boolean bit : bits)
			bitOutput.writeBit(bit);
	}

	/**
	 * Ler um bit do arquivo
	 *
	 * @return verdadeiro para 1 e falso para 0
	 */
	public boolean readBit() throws FileNotFoundException, IOException {
		openInputFlow();

		if (bitInput == null)
			bitInput = new BitInput(in);

		alreadyReadBit = true;
		boolean bit = bitInput.readBit();
		//System.out.println("\nBit lido " + bit);

		return bit;
	}

	/**
	 * Retorna a quantidade de bits que ainda faltam para serem lidos
	 * 
	 * @return a quantidade de bits
	 */
	public long avaliableBits() throws FileNotFoundException, IOException {
		openInputFlow();

		if (bitInput == null)
			bitInput = new BitInput(in);

		return bitInput.available();
	}

	/**
	 * Faz um flush no stream de saida
	 * 
	 * @throws IOException
	 */
	public void flush() throws IOException {
		if (bitOutput != null)
			bitOutput.flush();

		if (out != null)
			out.flush();
	}

	/**
	 * Apaga o conteudo.
	 */
	public void eraseContent() throws FileNotFoundException, IOException {
		new FileOutputStream(file, false).close();
	}
}
