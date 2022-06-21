package DWECompressor.gui;

/**
 * Splash screen do processamento.
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
public class DialogSplash extends javax.swing.JDialog {

    private PaintTimeRunnable paintTimeRunnable;
    private Thread threadPainTimeRunnable;

    /**
     * Construtor
     *
     * @param parent o form pai
     */
    public DialogSplash(java.awt.Frame parent) {
        super(parent, false);
        initComponents();
        setLocationRelativeTo(null);
    }

    @Override
    public void setVisible(boolean b){
        if(b == true){
            paintTimeRunnable = new PaintTimeRunnable();
            threadPainTimeRunnable = new Thread(paintTimeRunnable);
            threadPainTimeRunnable.start();
        }else if(paintTimeRunnable != null){
            paintTimeRunnable.stop();
            threadPainTimeRunnable.stop();
        }

        super.setVisible(b);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jButtonCancel = new javax.swing.JButton();
        jLabelTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Working");
        setResizable(false);

        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setString("Working");
        jProgressBar1.setStringPainted(true);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jLabelTime.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                        .addComponent(jButtonCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jLabelTime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    /**
     * Thead para mostrar o tempo.
     */
    private class PaintTimeRunnable implements Runnable{

        private boolean stop;

        public void run() {
            int sec = 0;
            int min = 0;
            int hour = 0;

            while(!stop){
                if(sec == 59){
                    sec = 0;
                    min++;
                }
                
                if(min == 60){
                    min = 0;
                    sec = 0;
                    hour++;
                }

                paint(sec, min, hour);

                try{
                    Thread.sleep(1000);
                    sec++;
                }catch(Exception ex){
                    stop();
                }
            }
        }
        
        /**
         * Pinta o tempo na tela.
         */
        private void paint(int sec, int min, int hour){
            String secString = sec < 9 ? String.format("0%d", sec) : String.format("%d", sec);
            String minString = min < 9 ? String.format("0%d", min) : String.format("%d", min);
            String hourString = hour < 9 ? String.format("0%d", hour) : String.format("%d", hour);

            jLabelTime.setText(String.format("Elapsed time: %s:%s:%s", hourString, minString, secString));
        }

        /**
         * Para a thread.
         */
        public void stop(){
            stop = true;
        }
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogSplash dialog = new DialogSplash(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

}