/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DWECompressor.test;

import DWECompressor.gui.DialogSplash;

/**
 *
 * @author elenilson
 */
public class TestSplash {

    public static void main(String[] args) throws InterruptedException {
        DialogSplash ds = new DialogSplash(null);
        ds.setVisible(true);
        Thread.sleep(10000);
        ds.setVisible(false);
    }

}
