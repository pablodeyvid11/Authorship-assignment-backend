package dweauthorshipattribution.gui;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Compressores.TextFile;
import dweauthorshipattribution.allocator.AuthorshipAllocator;
import dweauthorshipattribution.interfaces.AuthorCompressFileLenghIF;
import dweauthorshipattribution.interfaces.AuthorIF;
import dweauthorshipattribution.interfaces.AuthorshipAllocatorIF;
import dweauthorshipattribution.interfaces.AuthorshipAllocatorResultIF;
import dweauthorshipattribution.interfaces.DoTrainingIF;
import dweauthorshipattribution.interfaces.WordClassifierIF;
import dweauthorshipattribution.lexicon.WordClassifier;
import dweauthorshipattribution.util.FileUtils;

/**
 * Tela principal do projeto.
 *
 * <br><br>
 * Esse projeto faz parte de uma das atividades da disciplina de Introdução
 * à Teoria da Informação do Departamento de Informática da Universidade Federal
 * da Paraíba (UFPB) do período 2010.1 ministrada pelo professor Leonardo Vidal e
 * consiste na atribuicao de autoria utilizando a frequencia de classificacao das
 * palavras utilizadas por um determinado autor.
 * Este e' o projeto final da disciplina.
 *
 * @since 26 de junho de 2010
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Daniel Pires - dpsmetal[at]gmail.com
 * @author Wolgrand Cardoso - wolgrandcardoso[at]gmail.com
 * @version 1.0
 * @see www.di.ufpb.br
 */
public class MainFrame extends javax.swing.JFrame {
    
    private DialogAbout dialogAbout = new DialogAbout(this);
    private DialogLoadedAuthors dialogLoadedAuthors = new DialogLoadedAuthors(this);
    private DialogComicSplash dialogComicSplash = new DialogComicSplash(this);
    private TextFile selectedTextFile;
    private String filesTrainingExtension;
    private WordClassifierIF wordClassifier;
    private DialogTrainingAuthor dialogTrainingAuthor;

    /**
     * Construtor da classe.
     *
     * @param filesTrainingExtension a extensao dos arquivos de treinamento
     */
    public MainFrame(String filesTrainingExtension, WordClassifierIF wordClassifier) {
        if(filesTrainingExtension == null || filesTrainingExtension.isEmpty())
            throw new IllegalArgumentException("Extension can't be null!");

        if(wordClassifier == null)
            throw new IllegalArgumentException("Word classifier can't be null!");

        initComponents();
        setLocationRelativeTo(null);
        this.filesTrainingExtension = filesTrainingExtension;
        this.wordClassifier = wordClassifier;
        this.dialogTrainingAuthor = new DialogTrainingAuthor(this, true, wordClassifier);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTextFile = new javax.swing.JTextArea();
        jLabelTextFilePath = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPossibleAuthor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRankAuthors = new javax.swing.JTable();
        jButtonExecute = new javax.swing.JButton();
        getFileCla = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuTraining = new javax.swing.JMenu();
        jMenuItemNewAuthor = new javax.swing.JMenuItem();
        jMenuItemLoadAuthors = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuAbout = new javax.swing.JMenu();
        jMenuItemAuthors = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DWE Authorship Attribution");

        jTextAreaTextFile.setEditable(false);
        jTextAreaTextFile.setColumns(20);
        jTextAreaTextFile.setRows(5);
        jScrollPane1.setViewportView(jTextAreaTextFile);

        jLabelTextFilePath.setText("...");
        jLabelTextFilePath.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Text File"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addComponent(jLabelTextFilePath, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTextFilePath)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Text File", jPanel1);

        jLabelPossibleAuthor.setText("...");
        jLabelPossibleAuthor.setBorder(javax.swing.BorderFactory.createTitledBorder("Possible Author"));

        jLabel1.setText("Rank:");

        jTableRankAuthors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Author", "File lenght"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableRankAuthors);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addComponent(jLabelPossibleAuthor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPossibleAuthor)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Result", jPanel2);

        jButtonExecute.setText("Execute");
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });

        getFileCla.setText("Get file");
        getFileCla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFileClaActionPerformed(evt);
            }
        });

        jMenuFile.setText("File");

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dweauthorshipattribution/images/toggle16x16.png"))); // NOI18N
        jMenuItemOpen.setText("Open");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dweauthorshipattribution/images/del16x16.png"))); // NOI18N
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuTraining.setText("Traning");

        jMenuItemNewAuthor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemNewAuthor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dweauthorshipattribution/images/compilerSucess16x16.png"))); // NOI18N
        jMenuItemNewAuthor.setText("Author");
        jMenuItemNewAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewAuthorActionPerformed(evt);
            }
        });
        jMenuTraining.add(jMenuItemNewAuthor);

        jMenuItemLoadAuthors.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemLoadAuthors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dweauthorshipattribution/images/help16x16.png"))); // NOI18N
        jMenuItemLoadAuthors.setText("Load Authors");
        jMenuItemLoadAuthors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoadAuthorsActionPerformed(evt);
            }
        });
        jMenuTraining.add(jMenuItemLoadAuthors);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dweauthorshipattribution/images/compilerError16x16.png"))); // NOI18N
        jMenuItem1.setText("Loaded Authors");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuTraining.add(jMenuItem1);

        jMenuBar1.add(jMenuTraining);

        jMenuAbout.setText("About");

        jMenuItemAuthors.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemAuthors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dweauthorshipattribution/images/question16x16.gif"))); // NOI18N
        jMenuItemAuthors.setText("Authors");
        jMenuItemAuthors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAuthorsActionPerformed(evt);
            }
        });
        jMenuAbout.add(jMenuItemAuthors);

        jMenuBar1.add(jMenuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(getFileCla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExecute)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExecute)
                    .addComponent(getFileCla))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAuthorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAuthorsActionPerformed
        // TODO add your handling code here:
        dialogAbout.setVisible(true);
    }//GEN-LAST:event_jMenuItemAuthorsActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dialogLoadedAuthors.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Retorna o local onde a aplicacao esta rodando.
     *
     * @return o local
     */
    public static String getAppPath(){
        return System.getProperty("user.dir");
    }

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        // TODO add your handling code here
        File selectedFile =  FileUtils.selectFile(this, "Text files!", "txt");
        
        if(selectedFile != null){
            try{
                selectedTextFile = new TextFile(selectedFile);
                jTextAreaTextFile.setText(selectedTextFile.getContent());
                jLabelTextFilePath.setText(selectedTextFile.getPath());
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error opening text file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemLoadAuthorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoadAuthorsActionPerformed
        // TODO add your handling code here:
        File[] selectedFiles = FileUtils.selectFiles(this, "Files training", filesTrainingExtension);

        if(selectedFiles != null && selectedFiles.length > 0){
            try{
                Set<AuthorIF> authors = FileUtils.loadAuthorFiles(selectedFiles);
                dialogLoadedAuthors.addAuthor(authors);

                JOptionPane.showMessageDialog(this, "Load with sucess!", "Sucess", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading some files!", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItemLoadAuthorsActionPerformed

    private void jMenuItemNewAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewAuthorActionPerformed
        // TODO add your handling code here:
        dialogTrainingAuthor.setVisible(true);
    }//GEN-LAST:event_jMenuItemNewAuthorActionPerformed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecuteActionPerformed
        // TODO add your handling code here:
        if(dialogLoadedAuthors.getAuthors().size() == 0){
            JOptionPane.showMessageDialog(this, "No authors loaded!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(selectedTextFile == null){
            JOptionPane.showMessageDialog(this, "Select a text file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        /*** Cria um novo atribuidor de autoria com os autores ***/
        final AuthorshipAllocatorIF authorshipAllocator =
            new AuthorshipAllocator(dialogLoadedAuthors.getAuthors(), wordClassifier);

        final MainFrame mainFrame = this;
        
        System.out.println(selectedTextFile);
        
        Thread t = new Thread(){
        
            public void run(){
                new Thread(){
                    public void run(){
                        dialogComicSplash.setVisible(true);
                    }
                }.start();
                
                System.out.println(selectedTextFile.getExist());
                
                try{
                    /*** Executa a atribuicao de autoria ***/
                    AuthorshipAllocatorResultIF result = authorshipAllocator.execute(selectedTextFile);
                    setResult(result);
                }catch(Exception ex){
                	ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, "Error assigning authorship!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                dialogComicSplash.setVisible(false);
            }
        };

        t.start();
    }//GEN-LAST:event_jButtonExecuteActionPerformed

    private void getFileClaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getFileClaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getFileClaActionPerformed

    public void setResult(AuthorshipAllocatorResultIF result){
        Arrays.sort(result.getTestedAuthors(), AuthorCompressFileLenghIF.COMPARATOR);

        /*** Seta o possivel autor ***/
        jLabelPossibleAuthor.setText(result.getPossibleAuthor().getAuthor().getAuthorName());

        /*** Seta os autores testados ***/

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Author");
        defaultTableModel.addColumn("File Lenght");
        
        AuthorCompressFileLenghIF[] authorsResult = result.getTestedAuthors();

        for (AuthorCompressFileLenghIF authorCompressFileLenghIF : authorsResult) {
            Vector<String> v = new Vector<String>();

            v.add(authorCompressFileLenghIF.getAuthor().getAuthorName());
            v.add(authorCompressFileLenghIF.getFileLenght() + "");

            defaultTableModel.addRow(v);
        }

        jTableRankAuthors.setModel(defaultTableModel);
    }

    /**
     * Limpa todos os campos.
     */
    private void erase(){
        jTextAreaTextFile.setText("");
        jLabelPossibleAuthor.setText("...");

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Author");
        defaultTableModel.addColumn("File Lenght");

        jTableRankAuthors.setModel(defaultTableModel);
    }

    @Override
    public void dispose(){
        int option = JOptionPane.showConfirmDialog(this,
                "Are you sure want to exit?", "Information", JOptionPane.YES_NO_OPTION);

        if(option != JOptionPane.NO_OPTION)
            System.exit(1);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(DoTrainingIF.EXTENSION, new WordClassifier()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton getFileCla;
    private javax.swing.JButton jButtonExecute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPossibleAuthor;
    private javax.swing.JLabel jLabelTextFilePath;
    private javax.swing.JMenu jMenuAbout;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAuthors;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemLoadAuthors;
    private javax.swing.JMenuItem jMenuItemNewAuthor;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenu jMenuTraining;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableRankAuthors;
    private javax.swing.JTextArea jTextAreaTextFile;
    // End of variables declaration//GEN-END:variables

}
