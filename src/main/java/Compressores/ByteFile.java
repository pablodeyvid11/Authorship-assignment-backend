package Compressores;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
		if (file == null || !file.isFile())
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
	public byte readByte() throws IOException {
		return dataInputStream.readByte();
	}

	/**
	 * O numero de bytes que ainda podem ser lidos
	 *
	 * @return o numero de bytes
	 */
	public int avaliable() throws IOException {
		return dataInputStream.available();
	}
}
