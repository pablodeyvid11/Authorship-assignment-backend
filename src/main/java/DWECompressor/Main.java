package DWECompressor;

import java.util.HashMap;
import java.util.Map;

import DWECompressor.gui.MainFrame;
import Huffman.Huffman;
import LZW.LZW;
import PPM.PPM;
import compressorAPI.CompressorIF;

/**
 * Classe main da interface grafica do compressor implementado
 * pelo autores do projeto
 * .
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 28 de abril de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class Main {

    /**
     * Metodo inicial da aplicacao.
     *
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        Map<String, CompressorIF> compressors = new HashMap<String, CompressorIF>();

        /*** Adicionar os compressores ***/
        compressors.put(PPM.EXTENSION, new PPM());
        compressors.put(Huffman.EXTENSION, new Huffman());
        compressors.put(LZW.EXTENSION, new LZW());

        new MainFrame(compressors).setVisible(true);
    }

}
