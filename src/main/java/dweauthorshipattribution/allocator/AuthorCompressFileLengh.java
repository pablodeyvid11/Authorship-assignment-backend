package dweauthorshipattribution.allocator;

import dweauthorshipattribution.interfaces.AuthorCompressFileLenghIF;
import dweauthorshipattribution.interfaces.AuthorIF;

/**
 * Representa um autor e o tamanho do arquivo apos o ppm.
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
public class AuthorCompressFileLengh implements AuthorCompressFileLenghIF{

    private AuthorIF author;
    private long fileLenght;

    /**
     * Construtor da classe.
     *
     * @param author o autor
     * @param fileLenght o tamanho do arquivo
     */
    public AuthorCompressFileLengh(AuthorIF author, long fileLenght) {
        this.author = author;
        this.fileLenght = fileLenght;
    }

    public AuthorIF getAuthor() {
        return author;
    }

    public long getFileLenght() {
        return fileLenght;
    }

}
