package LZW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Compressores.TextFile;
import compressorAPI.CompressorIF;

/**
 * Classe do compressor LZW.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 30 de abril de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class LZW implements CompressorIF{

    public static final String NAME = "LZW";
    public static final String EXTENSION = "lzw";
    private String name = NAME;
    private String extension = EXTENSION;

    public String getExtension() {
        return extension;
    }

    public String getName() {
        return name;
    }

    public void compress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException {
        TextFile sourceTextFile = new TextFile(sourceFile);
        TextFile destinyTextFile = new TextFile(destinyFile);

        String compressText = new Coder().codify(sourceTextFile.getContent());

        try {
            destinyTextFile.setContent(compressText);
        } catch (IllegalAccessException ex) {
        }
    }

    public void uncompress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException {
        TextFile sourceTextFile = new TextFile(sourceFile);

        String uncompressedText = new Decoder().decode(sourceTextFile.getContent());
        TextFile destinyTextFile = new TextFile(destinyFile);

        try {
            destinyTextFile.setContent(uncompressedText);
        } catch (IllegalAccessException ex) {
        }
    }

}
