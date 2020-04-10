package Compressores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Contem um pool de threads.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal e
 * consiste na atribuicao de autoria utilizando a frequencia de classificacao das
 * palavras utilizadas por um determinado autor.
 * Este e' o projeto final da disciplina.
 *
 * @since 7 de julho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class Pool {

    public static final int NUMBER_OF_THREADS = 64;

    private static ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private Pool() {
    }

    /**
     * Executa um runnable.
     * 
     * @param r o runnable a ser executado
     */
    public static void execute(Runnable r){
        executorService.execute(r);
    }

}
