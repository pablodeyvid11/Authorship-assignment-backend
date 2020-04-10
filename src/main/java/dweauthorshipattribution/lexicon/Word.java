package dweauthorshipattribution.lexicon;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import dweauthorshipattribution.interfaces.WordIF;

/**
 * Represesenta uma palavra com sua classificacao.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal e
 * consiste na atribuicao de autoria utilizando a frequencia de classificacao das
 * palavras utilizadas por um determinado autor.
 * Este e' o projeto final da disciplina.
 *
 * @since 26 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
@Entity
public class Word implements WordIF, Serializable {

    private String word;
    private long id;
    private WordClassification wordClassification;

    /**
     * Construtor default
     */
    public Word() {
    }

    /**
     * Construtor da classe.
     *
     * @param word a palavra
     * @param wordClassification a classificacao da palavra
     */
    public Word(String word, WordClassification wordClassification) {
        this.word = word;
        this.wordClassification = wordClassification;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordClassification getWordClassification() {
        return wordClassification;
    }

    public void setWordClassification(WordClassification wordClassification) {
        this.wordClassification = wordClassification;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if ((this.word == null) ? (other.word != null) : !this.word.equals(other.word)) {
            return false;
        }
        if (this.wordClassification != other.wordClassification && (this.wordClassification == null || !this.wordClassification.equals(other.wordClassification))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.word != null ? this.word.hashCode() : 0);
        hash = 53 * hash + (this.wordClassification != null ? this.wordClassification.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("id = %d, word = %s, class = %s", id, word, wordClassification);
    }

}
