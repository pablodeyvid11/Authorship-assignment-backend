package LZW;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author danielpires
 */
public class Coder {

    //private FileOutputStream arquivoCodificado;
    //private File arquivoAux;
    private String nomeArquivoSaida;
    BufferedWriter out;
    private FileWriter paperbackWriter;
    //private DataOutputStream streamInput;
    //private BufferedWriter bufferEscrita;
    private String principal = "";
    private String auxiliar = "";
    private String stringCodificada = "";
    private ArrayList<String> dicionario = new ArrayList<String>();
    private ArrayList<Integer> listaCodificada = new ArrayList<Integer>();
    private ArrayList<Byte> bytesCodificados = new ArrayList<Byte>();

    private boolean estaNoDicionario(String x) {
        //System.out.println("\\");
        for (int i = 0; i < dicionario.size(); i++) {
            //System.out.println("X > "+x+" String "+i+ dicionario.get(i));
            if (dicionario.get(i).equals(x)) {
                //System.out.println("ENTROU NO IF");
                return true;
            }
        }
        return false;
    }

    private void abrirArquivo() throws FileNotFoundException, IOException, NullPointerException {

        try {

            //arquivoCodificado  = new FileOutputStream("arquivo.txt");

            //streamInput = new DataOutputStream(arquivoCodificado);

            for(int i=0;i<256;i++){
         //       streamInput.writeChar(i);
            }


            int ch = 65;
            
            //streamInput.close();
            // Create file
            //paperbackWriter= new FileWriter("arquivoCodificado.txt");
            //bufferEscrita = new BufferedWriter(paperbackWriter);
            //paperbackWriter.write("Hello Java");

            //Close the output stream
            //out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    /***
     * retornaCodigo(): retorna o codigo correspondente a string pesquisada no dicionario
     * @param x
     * @return
     */
    private int retornaCodigo(String x) {
        char aux;
        //se a string for uma string de apenas um caractere procura o indice correspondente
        if (x.length() == 1) {
            aux = x.charAt(0);
            return (int) aux;
        } else {
            for (int i = 255; i < dicionario.size(); i++) {
                if (dicionario.get(i).equals(x)) {
                    return i;
                }
            }
        }
        return -1;//em caso de erro
    }

    @Deprecated
    public Coder(String arquivo) {
        //filee = arquivo;
        nomeArquivoSaida = arquivo;
        //nomeArquivoSaida = arquivo.getName();
        //System.out.println(nomeArquivoSaida);
        for (int i = 0; i < 256; i++) {
            dicionario.add("" + (char) i);

            //System.out.println(i + " : " + (char) i);
        }

    }

    public Coder() throws FileNotFoundException, IOException {
        for (int i = 0; i < 256; i++) {
            dicionario.add("" + (char) i);

            //System.out.println(i + " : " + (char) i);
        }
    //    FileInputStream bos = new FileInputStream("arquivo.txt");
   //     DataInputStream dos = new DataInputStream(bos);
     //   System.out.println("********************"+(int)dos.readChar());
        //bufferLeitura= new BufferedReader(new FileReader(arquivoCodificado));

    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }


    /*
     * 1. No início o dicionário contém todas as raízes possíveis e I é vazio;
    2. c <= próximo caractere da sequência de entrada;
    3. A string I+c existe no dicionário?
    a. se sim,
    i. I <= I+c;
    b. se não,
    i. coloque a palavra código correspondente a I na sequência codificada;
    ii. adicione a string I+c ao dicionário;
    iii. I <= c;
    4. Existem mais caracteres na sequência de entrada ?
    a. se sim,
    i. volte ao passo 2;
    b. se não,
    ii. coloque a palavra código correspondente a I na sequência codificada;
    iii. FIM.
     *
     */
    /***
     * codify(): codifica a string utilizando o metodo LZW
     *
     * @param stringOriginal
     * @return
     */
    public String codify(String stringOriginal) throws IOException {

        char aux;
        byte byteAux;
        int intAux;
        String str;
        principal = "";
        //abrirArquivo();
        

        for (int i = 0; i < stringOriginal.length(); i++) {
            aux = stringOriginal.charAt(i);
            if (estaNoDicionario(principal + aux)) {
                principal += aux;
            } else {

                str = "" + retornaCodigo(principal);
                intAux = Integer.parseInt(str);
                //byteAux = Byte.parseByte(str);
                if (intAux < 100) {
                    str = "0" + intAux;
                }
                //System.out.println("String >"+principal);
                stringCodificada += str;
                //bytesCodificados.add(byteAux);
                //streamInput.writeChar(intAux);//aqui deveria escrever o char no arquivo,oq nao esta acontecendo
                
                
                dicionario.add(principal + aux);


                //System.out.println("PRINCIPAL " + (dicionario.size() - 1) + " : " + principal + aux);
                principal = "" + aux;
            }

        }
        intAux = retornaCodigo(principal);
        //byteAux = (byte) retornaCodigo(principal);
        //bytesCodificados.add(byteAux);
        stringCodificada += intAux;
        //streamInput.writeChar(intAux);
        //streamInput.flush();
        //streamInput.close();


//        System.out.println(stringOriginal.charAt(43));
        return stringCodificada;
    }

    /***
     * codifyByte : recebe uma string de entrada e retorna um arrayList de bytes
     *
     * @param stringOriginal
     * @return
     * @throws IOException
     */
    public ArrayList<Byte> codifyByte(String stringOriginal) throws IOException {

        char aux;
        byte byteAux;
        int intAux;
        String str;
        principal = "";
        //abrirArquivo();


        for (int i = 0; i < stringOriginal.length(); i++) {
            aux = stringOriginal.charAt(i);
            if (estaNoDicionario(principal + aux)) {
                principal += aux;
            } else {

                str = "" + retornaCodigo(principal);
                intAux = Integer.parseInt(str);
                byteAux = Byte.parseByte(str);
                if (intAux < 100) {
                    str = "0" + intAux;
                }
                //System.out.println("String >"+principal);
                //stringCodificada += str;
                bytesCodificados.add(byteAux);
                //streamInput.writeChar(intAux);//aqui deveria escrever o char no arquivo,oq nao esta acontecendo


                dicionario.add(principal + aux);


                //System.out.println("PRINCIPAL " + (dicionario.size() - 1) + " : " + principal + aux);
                principal = "" + aux;
            }

        }
        intAux = retornaCodigo(principal);
        byteAux = (byte) retornaCodigo(principal);
        bytesCodificados.add(byteAux);
        //stringCodificada += intAux;
        //streamInput.writeChar(intAux);
        //streamInput.flush();
        //streamInput.close();


//        System.out.println(stringOriginal.charAt(43));
        return bytesCodificados;
    }




}
