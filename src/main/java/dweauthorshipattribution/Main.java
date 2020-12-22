package dweauthorshipattribution;

import dweauthorshipattribution.gui.MainFrame;
import dweauthorshipattribution.interfaces.DoTrainingIF;
import dweauthorshipattribution.lexicon.WordClassifier;

/**
 * Classe main do projeto de atribuicao de autoria.
 *
 * <br>
 * <br>
 * Esse projeto faz parte do projeto integrador dos alunos:
 * Clara Yasmin Cunha Fernandes dos Santos;
 * Pablo Deyvid de Paiva.
 * 
 * Orientados pelo professor mestre Elenilson Vieira.
 * 
 *
 * @since 2020
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Pablo Deyvid - claryasmin.cf[at]gmail.com
 * @author Clara Yasmin - wolgrandcardoso[at]gmail.com
 * @version 2.0
 */
public class Main {
	public static void main(String[] args) {
		new MainFrame(DoTrainingIF.EXTENSION, new WordClassifier()).setVisible(true);
	}
}
