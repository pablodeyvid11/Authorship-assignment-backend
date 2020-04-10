/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PPM.tomaz;

/**
 *
 * @author Usuário
 */
public class Contador {
    private int count;
    public int getCount() {
        return count;
    }

    /**
     * retorna o valor atual, e depois incrementa.
     * @return
     */
    public int increment() {
        return count++;
    }
}
