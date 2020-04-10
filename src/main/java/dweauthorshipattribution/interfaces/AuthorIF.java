package dweauthorshipattribution.interfaces;

import java.io.File;

/**
 * Representa um autor com o seu arquivo de treinamento.
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
public interface AuthorIF {

    /**
     * Retorna o nome do autor.
     * 
     * @return o nome do autor
     */
    public String getAuthorName();

    /**
     * Retorna o arquivo de treinamento do autor.
     *
     * @return o arquivo de trinamento
     */
    public File getAuthorFile();

}
