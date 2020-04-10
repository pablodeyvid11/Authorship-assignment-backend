package dweauthorshipattribution.allocator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import PPM.modules.PPMEncoder;
import dweauthorshipattribution.interfaces.PPMAuthorIF;
import dweauthorshipattribution.interfaces.WordClassifierIF;
import dweauthorshipattribution.util.FileUtils;

/**
 * Representa um autor com o seu encoder do PPM.
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
public class PPMAuthor implements PPMAuthorIF, Serializable{

    private PPMEncoder ppmEncoder;
    private String authorName;

    /**
     * Construtor da classe.
     *
     * @param ppmEncoder o encoder do PPM
     * @param authorName o nome do autor
     */
    public PPMAuthor(PPMEncoder ppmEncoder, String authorName) {
        if(ppmEncoder == null)
            throw new IllegalArgumentException("Encoder can't be null!");

        if(authorName == null || authorName.isEmpty())
            throw new IllegalArgumentException("Name can't be null or empty!");
        
        this.ppmEncoder = ppmEncoder;
        this.authorName = authorName;
    }

    /**
     * Construtor da classe.
     *
     * @param authorName o nome do autor
     */
    public PPMAuthor(String authorName) {
        if(ppmEncoder == null)
            throw new IllegalArgumentException("Encoder can't be null!");

        if(authorName == null || authorName.isEmpty())
            throw new IllegalArgumentException("Name can't be null or empty!");

        this.ppmEncoder = new PPMEncoder();
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public PPMEncoder getPPMEncoder() {
        return ppmEncoder;
    }

    public File execute(File classifiedSourceText, File locationToWrite, WordClassifierIF wordClassifier) throws FileNotFoundException, IOException {
        String author = authorName.replace(" ", "-");
        File toFile = new File(FileUtils.formatPath(String.format("%s.%s", author, "ppm"), locationToWrite));

        getPPMEncoder().setSourceFile(classifiedSourceText);
        getPPMEncoder().setDestinyFile(toFile);
        getPPMEncoder().execute();

        return toFile;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PPMAuthor other = (PPMAuthor) obj;
        if ((this.authorName == null) ? (other.authorName != null) : !this.authorName.equals(other.authorName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.authorName != null ? this.authorName.hashCode() : 0);
        return hash;
    }
}
