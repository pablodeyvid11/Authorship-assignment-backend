package LZW;

import LZW.gui.TelaPrincipal;

/**
 * Classe main do compressor LZW.
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) ministrada pelo professor Leonardo Vidal.
 *
 * @since 17 de abril de 2010
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class Main {

    /**
     * Metodo inicial do programa.
     * 
     * @param args argumentso da linha de comando
     */
    public static void main(String args[]){
        new TelaPrincipal().setVisible(true);
    }

}
