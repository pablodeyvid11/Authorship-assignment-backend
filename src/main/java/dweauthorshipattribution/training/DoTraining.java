package dweauthorshipattribution.training;

import java.io.File;

import Compressores.OutputFile;
import Compressores.TextFile;
import PPM.modules.PPMEncoder;
import dweauthorshipattribution.allocator.PPMAuthor;
import dweauthorshipattribution.interfaces.DoTrainingIF;
import dweauthorshipattribution.interfaces.WordClassifierIF;
import dweauthorshipattribution.interfaces.WordIF;
import dweauthorshipattribution.util.FileUtils;



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
public class DoTraining implements DoTrainingIF{

    public static final String INTERMEDIATE_CLASSIFIED_FILE_NAME = "intermediate.classified";
    public static final String INTERMEDIATE_PPM_FILE_NAME = "intermediate.ppm";

    private TextFile textSource;
    private String fileName;
    private File directoryTo;
    private WordClassifierIF wordClassifier;
    private String createdFilePath;
    private String authorName;

    /**
     * Construtor da classe.
     * 
     * @param textSource o arquivo fonte
     * @param fileName o nome do arquivo a ser criado
     * @param authorName o nome do autor a ser inserido
     * @param directoryTo o diretorio onde sera criado o arquivo
     * @param wordClassifier o classificador de palavras
     */
    public DoTraining(TextFile textSource, String fileName, String authorName, File directoryTo,
            WordClassifierIF wordClassifier) {
        if(textSource == null)
            throw new IllegalArgumentException("Source is null!");

        if(fileName == null || fileName.isEmpty())
            throw new IllegalArgumentException("File name can't be null or empty!");

        if(authorName == null || authorName.isEmpty())
            throw new IllegalArgumentException("Author name can't be null or empty!");

        if(directoryTo == null || directoryTo.isFile())
            throw new IllegalArgumentException("Location to write can't be a file!");

        if(wordClassifier == null)
            throw new IllegalArgumentException("Classifier can't be null!");

        this.textSource = textSource;
        this.fileName = fileName;
        this.directoryTo = directoryTo;
        this.wordClassifier = wordClassifier;
        this.authorName = authorName;

        this.createdFilePath = formatPath(fileName, directoryTo);
    }

    public TextFile getTextSource() {
        return textSource;
    }

    public File getDirectoryTo() {
        return directoryTo;
    }

    public String getFileName() {
        return fileName;
    }

    public WordClassifierIF getWordClassifier() {
        return wordClassifier;
    }

    public File execute() throws Exception {
    	
        //Arquivo classidicado
        File intermediateClassifiedFile = new File(formatPathWithoutExtension(INTERMEDIATE_CLASSIFIED_FILE_NAME, directoryTo));

        //Arquivo que o PPM vai gerar
        File intermediatePPMFile = new File(formatPathWithoutExtension(INTERMEDIATE_PPM_FILE_NAME, directoryTo));
        OutputFile outputFile = new OutputFile(intermediateClassifiedFile);

        /*** Tokenizar ***/
        String[] tokens = textSource.tokenizer();
        
        /*** Classifica as palavras ***/
        for (String token : tokens) {
            token = token.trim();

            if(!token.isEmpty()){
                WordIF word = wordClassifier.classificate(token);
                //System.out.println((word == null) ? "NULLL" : "Tá certo"); 
                outputFile.write(word.getWordClassification().getCode());
            }
        }

        /*** Fecha o arquivo ***/
        outputFile.flush();
        outputFile.close();

        /*** Passa o ppm ***/
        PPMEncoder pPMEncoder = new PPMEncoder(intermediateClassifiedFile, intermediatePPMFile);
        pPMEncoder.execute();

        PPMAuthor pPMAuthor = new PPMAuthor(pPMEncoder, authorName);

        /*** Deleta os arquivos intermediarios ***/
        intermediateClassifiedFile.delete();
        intermediatePPMFile.delete();

        return FileUtils.writeObject(pPMAuthor, formatPath(fileName, directoryTo));
    }

    /**
     * Forma o caminho do arquivo a ser criado com extensao.
     *
     * @param fileName o nome do arquivo
     * @param directoryTo o diretorio onde sera criado
     *
     * @return o caminho formatado
     */
    private String formatPath(String fileName, File directoryTo) {
        String directoryPath = directoryTo.getAbsolutePath();
        
        return directoryPath.length() < 1 ? String.format("%s.%s", fileName, EXTENSION) :
            String.format("%s/%s.%s", directoryPath, fileName, EXTENSION);
    }

    /**
     * Forma o caminho do arquivo a ser criado sem extensao.
     *
     * @param fileName o nome do arquivo
     * @param directoryTo o diretorio onde sera criado
     *
     * @return o caminho formatado
     */
    private String formatPathWithoutExtension(String fileName, File directoryTo) {
        String directoryPath = directoryTo.getAbsolutePath();

        return directoryPath.length() < 1 ? fileName :
            String.format("%s/%s", directoryPath, fileName);
    }

    public String getCreatedFilePath() {
        return createdFilePath;
    }

}
