package dweauthorshipattribution.allocator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import Compressores.OutputFile;
import Compressores.TextFile;
import dweauthorshipattribution.interfaces.AuthorCompressFileLenghIF;
import dweauthorshipattribution.interfaces.AuthorIF;
import dweauthorshipattribution.interfaces.AuthorshipAllocatorIF;
import dweauthorshipattribution.interfaces.AuthorshipAllocatorResultIF;
import dweauthorshipattribution.interfaces.PPMAuthorIF;
import dweauthorshipattribution.interfaces.WordClassifierIF;
import dweauthorshipattribution.interfaces.WordIF;
import dweauthorshipattribution.util.FileUtils;

/**
 * Faz a atribuicao de autoria a partir de varios arquivos autores.
 *
 * <br>
 * <br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução à
 * Teoria da Informação do Departamento de Informática da Universidade
 * Federal da Paraíba (UFPB) do período 2010.1 ministrada pelo professor
 * Leonardo Vidal e consiste na atribuicao de autoria utilizando a frequencia de
 * classificacao das palavras utilizadas por um determinado autor. Este e' o
 * projeto final da disciplina.
 *
 * @since 26 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class AuthorshipAllocator implements AuthorshipAllocatorIF {

	public static final String CLASSIFIED_FILE_NAME = "classified-file.bytes";

	private AuthorIF[] authors;
	private String locationToWrite;
	private WordClassifierIF wordClassifier;

	/**
	 * Construtor da classe.
	 * 
	 * @param authors         os autores a serem tentados.
	 * @param locationToWrite o local de gravacao dos arquivos necessarios
	 * @param wordClassifier  o classificador de palavras
	 */
	public AuthorshipAllocator(AuthorIF[] authors, String locationToWrite, WordClassifierIF wordClassifier) {
		if (authors == null || locationToWrite == null)
			throw new IllegalArgumentException("Authors and location can't be null!");

		this.authors = authors;
		this.locationToWrite = locationToWrite;
		this.wordClassifier = wordClassifier;
	}

	/**
	 * Construtor da classe.
	 *
	 * @param authors         os autores a serem tentados.
	 * @param locationToWrite o local de gravacao dos arquivos necessarios
	 * @param wordClassifier  o classificador de palavras
	 */
	public AuthorshipAllocator(Set<AuthorIF> authors, String locationToWrite, WordClassifierIF wordClassifier) {
		if (authors == null || locationToWrite == null)
			throw new IllegalArgumentException("Authors and location can't be null!");

		this.authors = authors.toArray(new AuthorIF[1]);
		this.locationToWrite = locationToWrite;
		this.wordClassifier = wordClassifier;
	}

	/**
	 * Construtor da classe.
	 *
	 * @param authors         os autores a serem tentados.
	 * @param locationToWrite o local de gravacao dos arquivos necessarios
	 * @param wordClassifier  o classificador de palavras
	 */
	public AuthorshipAllocator(Set<AuthorIF> authors, WordClassifierIF wordClassifier) {
		this(authors, DEFAULT_LOCALTION_TO_WRITE, wordClassifier);
	}

	public AuthorshipAllocator(AuthorIF[] authors, WordClassifierIF wordClassifier) {
		this(authors, DEFAULT_LOCALTION_TO_WRITE, wordClassifier);
	}

	public AuthorIF[] getAuthors() {
		return authors;
	}

	public String getLocationToWrite() {
		return locationToWrite;
	}

	public AuthorshipAllocatorResultIF execute(TextFile sourceTextFile)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		/*** Cria o local de gravacao ***/
		File toDirectory = new File(locationToWrite);

		/*** Cria o arquivo classificado ***/
		String tokens[] = sourceTextFile.tokenizer();
		System.out.println(locationToWrite);
		File classifiedFile = new File(FileUtils.formatPath(CLASSIFIED_FILE_NAME, locationToWrite));

		System.out.println(classifiedFile.getPath());
		OutputFile classifiedFileByte = new OutputFile(classifiedFile);

		for (String token : tokens) {
			token = token.trim();

			if (!token.isEmpty()) {
				WordIF word = wordClassifier.classificate(token);
				classifiedFileByte.write(word.getWordClassification().getCode());
			}
		}

		classifiedFileByte.flush();
		classifiedFileByte.close();

		/*** Manda para todos os ppms dos autores ***/
		AuthorCompressFileLenghIF[] authorCompressFileLenghIFs = new AuthorCompressFileLenghIF[authors.length];

		for (int i = 0; i < authors.length; i++) {
			PPMAuthorIF pPMAuthorIF = (PPMAuthorIF) FileUtils.readObject(authors[i].getAuthorFile());
			File resultFile = pPMAuthorIF.execute(classifiedFile, toDirectory, wordClassifier);

			authorCompressFileLenghIFs[i] = new AuthorCompressFileLengh(authors[i], resultFile.length());
		}

		return new AuthorshipAllocatorResult(authorCompressFileLenghIFs);
	}

	public WordClassifierIF getWordClassifier() {
		return wordClassifier;
	}

}
