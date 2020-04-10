package PPM.comparator;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator de bytes.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal.
 *
 * @since 7 de julho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class ByteComparator implements Comparator<Byte>, Serializable{
    
    public int compare(Byte o1, Byte o2) {
        if (o1 == null)
            return o2 == null ? 0 : -1;
        else
            return o2 == null ? 1 : o1-o2;
    }
}
