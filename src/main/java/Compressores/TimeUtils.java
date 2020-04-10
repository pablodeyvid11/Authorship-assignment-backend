package Compressores;

/**
 * Classe que contem metodos para manipulacao de tempo.
 * 
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
public class TimeUtils {

    /**
     * Construtor e' privado.
     */
    private TimeUtils() {
    }

    /**
     * Converte o intervalo para String.
     *
     * @param timeBefore tempo antes
     * @param timeAfter tempo depois
     *
     * @return o intervalo formatado para String
     */
    public static String convertInterval(long timeBefore, long timeAfter){
        long interval = timeAfter - timeBefore;
        int hours = 0;
        int min = 0;
        long secs = 0;

        while(interval > 3600000){
            hours++;
            interval -= 3600000;
        }

        while(interval > 60000){
            min++;
            interval -= 60000;
        }

        while(interval > 1000){
            secs++;
            interval -= 1000;
        }

        StringBuilder result = new StringBuilder();

        if(hours > 0)
            result.append(hours + " hours");

        if(min > 0){
            if(hours > 0)
                result.append(" and ");

            result.append(min + " minutes");
        }

        if(secs > 0){
            if(hours > 0 || min > 0)
                result.append(" and ");

            result.append(secs + " seconds");
        }

        if(interval > 0){
            if(hours > 0 || min > 0 || secs > 0)
                result.append(" and ");

            result.append(interval + " milliseconds");
        }

        return result.toString();
    }

}
