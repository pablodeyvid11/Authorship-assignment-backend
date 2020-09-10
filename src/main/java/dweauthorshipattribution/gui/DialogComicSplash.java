package dweauthorshipattribution.gui;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Tela com tirinhas comicas.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal e
 * consiste na atribuicao de autoria utilizando a frequencia de classificacao das
 * palavras utilizadas por um determinado autor.
 * Este e' o projeto final da disciplina.
 *
 * @since 27 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class DialogComicSplash extends javax.swing.JDialog {

    public static final long INTERVAL_BETWEEN_IMAGES = 15000;
    public static final int NUMBER_OF_COMIC_IMAGES = 20;
    public static final String COMIC_IMAGES_PATH = "/dweauthorshipattribution/images/comic/";
    public static final Random RANDOM = new Random();
    private String operacaoAtual = "";

   
    public String getOperacaoAtual(){
        return operacaoAtual;
    }
    public void setOperacaoAtual(String operacaoAtual, String vez){
        this.operacaoAtual = operacaoAtual;
        Vez.setText(vez);
        operacaoAtualLabel.setText(operacaoAtual);
    }
    
    /** Creates new form DialogComic */
    public DialogComicSplash(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);

        new ThreadImages().start();
    }

    private ImageIcon getImage(){
        int imageNumber = RANDOM.nextInt(NUMBER_OF_COMIC_IMAGES);

        String image = String.format("%scomic_%d.jpg", COMIC_IMAGES_PATH, imageNumber);
        try{
            BufferedImage im = ImageIO.read(DialogComicSplash.class.getResourceAsStream(image));
            return new ImageIcon(im);
        }catch(Exception ex){
            return null;
        }
    }

    private class ThreadImages extends Thread{
        public void run(){
            while(true){
                try{
                    Thread.sleep(INTERVAL_BETWEEN_IMAGES);
                }catch(Exception ex){}

                jLabelImages.setIcon(getImage());
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelImages = new javax.swing.JLabel();
        operacaoAtualLabel = new javax.swing.JLabel();
        Vez = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Comic");
        setResizable(false);

        jLabelImages.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comic_10.jpg"))); // NOI18N
        jLabelImages.setBorder(javax.swing.BorderFactory.createTitledBorder("While you wait..."));

        operacaoAtualLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        operacaoAtualLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Vez.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Vez.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Vez, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(operacaoAtualLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImages, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operacaoAtualLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Vez, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogComicSplash dialog = new DialogComicSplash(new javax.swing.JFrame());
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
    private javax.swing.JLabel Vez;
    private javax.swing.JLabel jLabelImages;
    private javax.swing.JLabel operacaoAtualLabel;
    // End of variables declaration//GEN-END:variables

}
