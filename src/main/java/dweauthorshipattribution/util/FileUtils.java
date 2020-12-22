package dweauthorshipattribution.util;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import dweauthorshipattribution.allocator.Author;
import dweauthorshipattribution.allocator.PPMAuthor;
import dweauthorshipattribution.interfaces.AuthorIF;

/**
 * Classe main do projeto de atribuicao de autoria.
 *
 * <br>
 * <br>
 * Esse projeto faz parte do projeto integrador dos alunos:
 * Clara Yasmin Cunha Fernandes dos Santos;
 * Pablo Deyvid de Paiva.
 * 
 * Orientados pelo professor mestre Elenilson Vieira.
 * 
 *
 * @since 2020
 * @author Elenilson Vieira - elenilson[at]elenilsonvieira.com
 * @author Pablo Deyvid - claryasmin.cf[at]gmail.com
 * @author Clara Yasmin - wolgrandcardoso[at]gmail.com
 * @version 2.0
 */
public class FileUtils {

    /**
     * Construtor privado.
     */
    private FileUtils() {
    }

    /**
     * Seleciona um arquivo de acordo com as extensoes
     *
     * @param parent a tela pai
     * @param extensionsDescription descricao das extensoes dos arquivos que podem ser selcionados
     * @param extensions as extensoes permitidas
     *
     * @return o arquivo selecionado ou null se nada foi selecionado
     */
    public static File selectFile(Component parent, String extensionsDescription, String... extensions) {
        FileNameExtensionFilter fileFilter = null;
        JFileChooser jFileChooser = new JFileChooser();

        if(extensions != null && extensionsDescription != null){
            fileFilter = new FileNameExtensionFilter(extensionsDescription, extensions);
            jFileChooser.addChoosableFileFilter(fileFilter);
        }

        jFileChooser.setDialogTitle("Select a file!");
        jFileChooser.setApproveButtonText("Select");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setFileFilter(null);
        jFileChooser.setMultiSelectionEnabled(false);
        int result = jFileChooser.showOpenDialog(parent);

        return result != JFileChooser.CANCEL_OPTION ? jFileChooser.getSelectedFile() : null;
    }

    /**
     * Seleciona um ou varios arquivos de acordo com as extensoes
     *
     * @param parent a tela pai
     * @param multipleChoice verdadeiro para selecionar varios e falso caso contrario
     * @param extensionsDescription descricao das extensoes dos arquivos que podem ser selcionados
     * @param extensions as extensoes permitidas
     *
     * @return o arquivo selecionado ou null se nada foi selecionado
     */
    public static File[] selectFiles(Component parent, String extensionsDescription, String... extensions) {
        FileNameExtensionFilter fileFilter = null;
        JFileChooser jFileChooser = new JFileChooser();

        if(extensions != null && extensionsDescription != null){
            fileFilter = new FileNameExtensionFilter(extensionsDescription, extensions);
            jFileChooser.addChoosableFileFilter(fileFilter);
        }

        jFileChooser.setDialogTitle("Select a file!");
        jFileChooser.setApproveButtonText("Select");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setFileFilter(null);
        jFileChooser.setMultiSelectionEnabled(true);
        int result = jFileChooser.showOpenDialog(parent);

        return result != JFileChooser.CANCEL_OPTION ? jFileChooser.getSelectedFiles() : null;
    }
    
    /**
     * Ler os arquivos dos autores e retorna um set com eles.
     *
     * @param selectedFiles os arquivos a serem lidos
     *
     * @return o set de autores
     */
    public static Set<AuthorIF> loadAuthorFiles(File[] selectedFiles) throws IOException, ClassNotFoundException {
        Set<AuthorIF> authors = new HashSet<AuthorIF>();

        for (File file : selectedFiles) {
            PPMAuthor ppmAuthor = (PPMAuthor) readObject(file);
            authors.add(new Author(ppmAuthor.getAuthorName(), file));
        }

        return authors;
    }

    /**
     * Salva um objeto no arquivo
     *
     * @param object o objeto a ser salvo
     * @param file o nome do arquivo
     *
     * @return o arquivo criado
     */
    public static File writeObject(Object object, String fileName) throws FileNotFoundException, IOException{
        File file = new File(fileName);

        if(file.exists())
            file.delete();

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(object);
        out.close();

        return file;
    }

    /**
     * Ler um objeto do arquivo.
     *
     * @param file o nome do arquivo a ser lido
     *
     * @return o objeto lido
     */
    public static Object readObject(String fileName) throws IOException, ClassNotFoundException{
        return readObject(new File(fileName));
    }

    /**
     * Ler um objeto do arquivo.
     *
     * @param file o nome do arquivo a ser lido
     *
     * @return o objeto lido
     */
    public static Object readObject(File file) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        return in.readObject();
    }
    
    /**
     * Formata o caminho do arquivo.
     *
     * @param fileName o nome do arquivo
     * @param directoryTo o diretorio
     *
     * @return o caminho formatado
     */
    public static String formatPath(String fileName, File directoryTo) {
        String directoryPath = directoryTo.getAbsolutePath();
        return directoryPath.length() < 1 ? fileName : String.format("%s/%s", directoryPath, fileName);
    }
    /**
     * Formata o caminho do arquivo.
     *
     * @param fileName o nome do arquivo
     * @param directoryTo o diretorio
     *
     * @return o caminho formatado
     */
    public static String formatPath(String fileName, String directoryTo) {
        return directoryTo.length() < 1 ? fileName : String.format("%s/%s", directoryTo, fileName);
    }
}
