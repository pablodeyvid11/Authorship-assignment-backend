package compressorAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	 * @param sourceFile  o arquivo fonte a ser comprimido
	 * @param destinyFile onde o arquivo comprimido deve ser gravado
	 */
	public void compress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException;

	/**
	 * Descomprime os dados passados e retorna os dados descomprimidos.
	 *
	 * @param sourceFile  o arquivo fonte a ser descomprimido
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
