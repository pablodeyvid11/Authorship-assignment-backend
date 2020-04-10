package PPM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import PPM.modules.PPMDecoder;
import PPM.modules.PPMEncoder;
import compressorAPI.CompressorIF;

/**
 * Classe do compressor PPM com modelo o aritmetico.
 *
 * <br><br> 
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 9 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class PPM implements CompressorIF{

    public static final String NAME = "PPM";
    public static final String EXTENSION = "ppm";
    private String name = NAME;
    private String extension = EXTENSION;

    public String getExtension() {
        return extension;
    }

    public void compress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException {
        new PPMEncoder(sourceFile, destinyFile).execute();
    }

    public void uncompress(File sourceFile, File destinyFile) throws FileNotFoundException, IOException {
        new PPMDecoder(sourceFile, destinyFile).execute();
    }

    public String getName() {
        return name;
    }

}
