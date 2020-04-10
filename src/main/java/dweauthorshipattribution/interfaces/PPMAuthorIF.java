package dweauthorshipattribution.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import PPM.modules.PPMEncoder;

/**
 * Contem um PPMEncoder com as caracteristicas de um determinado autor.
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
public interface PPMAuthorIF extends Serializable{

    /**
     * Retorna o encoder que contem as caracteristicas do autor
     * 
     * @return o encoder
     */
    public PPMEncoder getPPMEncoder();

    /**
     * Retorna o nome do autor.
     *
     * @return o nome do autor
     */
    public String getAuthorName();

    /**
     * Executa o PPM no arquivo ja classificado.
     *
     * @param classifiedSourceText o texto fonte ja classificado
     * @param locationToWrite o local onde deve ser gravado
     * @param wordClassifier o classificador de palavras a ser utilizado
     *
     * @return o arquivo criado (locationToWrite/nome-do-autor.ppm)
     */
    public File execute(File classifiedSourceText, File locationToWrite, WordClassifierIF wordClassifier) throws FileNotFoundException, IOException;

}
