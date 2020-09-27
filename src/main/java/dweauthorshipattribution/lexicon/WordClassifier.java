package dweauthorshipattribution.lexicon;

import dweauthorshipattribution.interfaces.WordClassifierIF;
import dweauthorshipattribution.interfaces.WordIF;
import dweauthorshipattribution.persistence.dao.WordDAO;

/**
 * Classificador de palavras.
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
public class WordClassifier implements WordClassifierIF{

    private DicionarioReader dicionarioReader = new DicionarioReader("");
    private WordDAO dao = new WordDAO();

    public Word searchClass(String param,String word) {
//            Word.WordClassification classification = null;
        Word wrd = null;
	if (param.contains("desconhecido")) {
            wrd = new Word(word, WordIF.WordClassification.UNKNOWN);

//            wrd.setWordClassification(WordIF.WordClassification.NOUN);
                    

//			result = TipoToken.DESCONHECIDO.toString();
	}
        else if(param.contains("art")||param.contains("pref.")){
            wrd = new Word(word, WordIF.WordClassification.ARTICLE);
        }
        else if (param.contains("s. f") || param.contains("s. m")) {
            wrd = new Word(word, WordIF.WordClassification.NOUN);
//                 wrd.setWordClassification(WordIF.WordClassification.NOUN);
//			result = TipoToken.SUBSTANTIVO.toString();
		}
        else if (param.contains("v. tr") || param.contains("gerúndio") || param.contains("part")
				|| param.contains("pess.") || param.contains("verbo") ) {
            wrd = new Word(word, WordIF.WordClassification.VERB);
//                        wrd.setWordClassification(WordIF.WordClassification.VERB);
//			result = TipoToken.VERBO.toString();
		}
        else if (param.contains("pron")) {
            wrd = new Word(word, WordIF.WordClassification.PRONOUN);
//                        wrd.setWordClassification(Word.WordClassification.PRONOUN);
//			result = TipoToken.PRONOME.toString();
		}
        else if (param.contains("conj")) {
            wrd = new Word(word,WordIF.WordClassification.CONJUNCTION);
//                        wrd.setWordClassification(Word.WordClassification.CONJUNCTION);
//			result = TipoToken.CONJUNCAO.toString();
		}
        else if (param.contains("adv")) {
            wrd = new Word(word, WordIF.WordClassification.ADVERB);
//                        wrd.setWordClassification(Word.WordClassification.ADVERB);
//			result = TipoToken.ADVERBIO.toString();
		}
        else if (param.contains("adj")) {
            wrd = new Word(word,WordIF.WordClassification.ADJECTIVE);
//                        wrd.setWordClassification(Word.WordClassification.ADJECTIVE);
//			result = TipoToken.ADJETIVO.toString();
	}
        else if (param.contains("num")) {
            wrd = new Word(word,WordIF.WordClassification.ADJECTIVE);
//                        wrd.setWordClassification(Word.WordClassification.NOUN);
//			result = TipoToken.NUMERAL.toString();
	}
        else if (param.contains("interj")) {
            wrd = new Word(word,WordIF.WordClassification.INTERJECTION);
//                        wrd.setWordClassification(Word.WordClassification.INTERJECTION);
//			result = TipoToken.INTERJEICAO.toString();
	}
        else if (param.contains("prep")||param.contains("contr")) {
            wrd = new Word(word, WordIF.WordClassification.PREPOSITION);
//                        wrd.setWordClassification(Word.WordClassification.PREPOSITION);
//			result = TipoToken.PREPOSICAO.toString();
	}
        else{
            wrd = new Word(word, WordIF.WordClassification.NOUN);
//            wrd.setWordClassification(Word.WordClassification.NOUN);
        }
                return wrd;
//		return classification;
	}

    public WordIF classificate(String word) {
//        if(word.equals(".")||word.equals("!")||word.equals("?")||word.equals("...")){
//            return new Word(word, WordIF.WordClassification.FINAL_PONCTUATION);
////            wrd.setWordClassification(WordIF.WordClassification.FINAL_PONCTUATION);
//
//            //dao.save(wrd);//salva no banco a pontuacao final
//        }
//        else if(word.equals(",")||word.equals(";")||word.equals("-")||word.equals(":")||word.equals("(")||word.equals(")")||word.equals("\"")||word.equals("\'")){
//            return new Word(word, WordIF.WordClassification.INTERMEDIATE_PONTUATION);
////            wrd.setWordClassification(WordIF.WordClassification.INTERMEDIATE_PONTUATION);
//            //dao.save(wrd);//salva no banco
//        }

        Word wrd = (Word) dao.getWord(word);
        if(wrd==null){
            String palavra = dicionarioReader.searchByGet(word);
//            wrd = new Word(palavra, WordIF.WordClassification.NOUN);
            wrd = searchClass(palavra,word);//faz o mapeamento das palavras e suas classificacoes
           // System.out.println("PALAVRA : "+wrd.getWord()+"  classificada como :"+wrd.getWordClassification().toString());
            //buscaNaNet
            //classifica a palavra
//            if(palavra.contains("adj")){
//                wrd.setWordClassification(WordIF.WordClassification.ADJECTIVE);
//            }
//            else if(palavra.contains("v.")||palavra.contains("verbo")){
//                wrd.setWordClassification(WordIF.WordClassification.VERB);
//            }
//            else if(palavra.contains("pron")){
//                wrd.setWordClassification(WordIF.WordClassification.PRONOUN);
//            }
//            else if(palavra.contains("adv")){
//                wrd.setWordClassification(WordIF.WordClassification.ADVERB);
//            }
//            else if(palavra.contains("art")){
//                wrd.setWordClassification(WordIF.WordClassification.ARTICLE);
//            }
//            else if(palavra.contains("s.")||palavra.contains("subs")){
//                wrd.setWordClassification(WordIF.WordClassification.NOUN);
//            }
//            else if(palavra.contains("prep")){
//                wrd.setWordClassification(WordIF.WordClassification.PREPOSITION);
//            }
//            else if(palavra.contains("int")){
//                wrd.setWordClassification(WordIF.WordClassification.INTERJECTION);
//            }
//            //falta pontuacoes!!!!

//            wrd.setWordClassification(WordIF.WordClassification.ARTICLE);//exemplo
            dao.save(wrd);//salva no banco
        }
        else{
//           System.out.println("Palavra : "+wrd.getWord()+ " já consta no banco de dados como :"+wrd.getWordClassification().toString());
        }

        return wrd;
        /***
         * 1. Vai no banco e verifica se a palavra ja se encontra (metodo getWord da classe WordDAO)
         * 2. Se contiver, ja retorna-a
         * 3. Se nao contiver a palavra, vai no dicionario online e busca
         * 4. Classifica a palavra e de acordo com o enum WordIF.WordClassification
         * 5. Insere a palavra no banco (metodo save do WordDAO)
         * 6. Retorna a palavra
         ***/
//        throw new UnsupportedOperationException("Culpa de Daniel e Wolgrand que ainda nao implementaram!");
    }

}
