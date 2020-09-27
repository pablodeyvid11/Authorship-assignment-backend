package dweauthorshipattribution.interfaces;

import java.io.Serializable;

/**
 * Representa uma palavra.
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
public interface WordIF extends Serializable {

	/**
	 * Contem a classificacao das palavras.
	 */
	public enum WordClassification {

		/**
		 * Substantivos
		 */
		NOUN, ARTICLE, PRONOUN, VERB, ADJECTIVE, CONJUNCTION, INTERJECTION, PREPOSITION, ADVERB,

		/**
		 * ".", "!", "?"
		 */
		FINAL_PONCTUATION, VIRGULA, EXCLAMACAO, INTERROGACAO, RETICENCIAS, ASTERISCO, ABRE_PARENTESES, FECHA_PARENTESES,
		ABRE_CHAVES, FECHA_CHAVES, ABRE_COLCHETES, FECHA_COLCHETES, A_ORDINAL, O_ORDINAL, PONTO_VIRGULA, DOIS_PONTOS,
		APOSTROFE, ASPAS,

		/**
		 * ";", ",", ":", "-", "_", etc
		 */
		INTERMEDIATE_PONTUATION,

		/**
		 * Tudo aquilo que nao for nenhum dos outros
		 */
		UNKNOWN;

		/**
		 * Retorna o codigo da classificacao.
		 *
		 * @return o codigo em byte
		 */
		public byte getCode() {
			return (byte) this.ordinal();
		}

	}

	/**
	 * Retorna a palavra
	 *
	 * @return a palavra
	 */
	public String getWord();

	/**
	 * Retorna a classificacao da palavra
	 *
	 * @return a classificacao da palavra
	 */
	public WordClassification getWordClassification();

	/**
	 * Seta a palavra
	 *
	 * @param word a palavra
	 */
	public void setWord(String word);

	/**
	 * Seta a classificacao da palavra
	 *
	 * @param wordClassification a classificacao da palavra
	 */
	public void setWordClassification(WordClassification wordClassification);

	/**
	 * Retorna o id
	 * 
	 * @return o id
	 */
	public long getId();

	/**
	 * Seta o id
	 *
	 * @param id o id
	 */
	public void setId(long id);

}
