package dweauthorshipattribution;

import dweauthorshipattribution.interfaces.WordIF.WordClassification;
import dweauthorshipattribution.lexicon.Word;
import dweauthorshipattribution.persistence.dao.WordDAO;

public class AdicionarOsPontos {
	public static void main(String[] args) {
		WordDAO wd = new WordDAO();
		wd.save(new Word(".", WordClassification.FINAL_PONCTUATION));
		wd.save(new Word(",", WordClassification.VIRGULA));
		wd.save(new Word("!", WordClassification.EXCLAMACAO));
		wd.save(new Word("?", WordClassification.INTERROGACAO));
		wd.save(new Word("...", WordClassification.RETICENCIAS));
		
		wd.save(new Word("*", WordClassification.ASTERISCO));
		wd.save(new Word("(", WordClassification.ABRE_PARENTESES));
		wd.save(new Word(")", WordClassification.FECHA_PARENTESES));
		wd.save(new Word("{", WordClassification.ABRE_CHAVES));
		wd.save(new Word("}", WordClassification.FECHA_CHAVES));
		
		wd.save(new Word("[", WordClassification.ABRE_COLCHETES));
		wd.save(new Word("]", WordClassification.FECHA_COLCHETES));
		
		wd.save(new Word("ª", WordClassification.A_ORDINAL));
		wd.save(new Word("º", WordClassification.O_ORDINAL));
		
		wd.save(new Word(";", WordClassification.PONTO_VIRGULA));
		wd.save(new Word(":", WordClassification.DOIS_PONTOS));
		
		wd.save(new Word("'", WordClassification.APOSTROFE));
		wd.save(new Word("\"", WordClassification.ASPAS));
	}
}
