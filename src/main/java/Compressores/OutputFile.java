package Compressores;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Classe para manipulacao de arquivo de dados. Com essa classe e possivel
 * escrever alguns tipos do arquivo ate o seu final.
 *
 * <br>
 * <br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução à
 * Teoria da Informação do Departamento de Informática da Universidade
 * Federal da Paraíba (UFPB) do período 2010.1 ministrada pelo professor
 * Leonardo Vidal.
 *
 * @since 13 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class OutputFile {

	private String path;
	private File file;
	private DataOutputStream dataOutputStream;
	private FileOutputStream fileOutputStream;

	public OutputFile(File file) throws FileNotFoundException {
		if (file == null)
			throw new IllegalArgumentException("File can't be null or directory!");

		this.file = file;
		this.path = file.getPath();

		if (file.exists())
			file.delete();

		if (!file.exists()) {
			if (file.getParentFile() != null) {
				if (!file.getParentFile().exists()) {
					file.mkdirs();
				}
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.fileOutputStream = new FileOutputStream(file);
		this.dataOutputStream = new DataOutputStream(fileOutputStream);
	}

	public File getFile() {
		return file;
	}

	public String getPath() {
		return path;
	}

	public void write(byte b) throws IOException {
		dataOutputStream.writeByte(b);
	}

	public void write(byte[] b) throws IOException {
		dataOutputStream.write(b);
	}

	public void write(long l) throws IOException {
		dataOutputStream.writeLong(l);
	}

	public void write(int i) throws IOException {
		dataOutputStream.writeInt(i);
	}

	public void close() throws IOException {
		dataOutputStream.close();
	}

	public void flush() throws IOException {
		dataOutputStream.flush();
	}

	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}
}
