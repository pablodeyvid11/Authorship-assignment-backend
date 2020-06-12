/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LZW;

import java.util.ArrayList;

/**
 *
 * @author DANIMA
 */
public class Decoder {

    private String principal = "";
    private String auxiliar = "";
    private String stringDecodificada = "";
    private ArrayList<String> dicionario = new ArrayList<String>();
    private ArrayList<Byte> mensagemDecodificada = new ArrayList<Byte>();

    public Decoder() {
        //inicializa o dicionario com todos os simbolos possiveis
        for (int i = 0; i < 256; i++) {
            dicionario.add("" + (char) i);
            //System.out.println(i + " : " + (char) i);
        }

    }

    private boolean estaNoDicionario(String x) {
        for (int i = 0; i < dicionario.size(); i++) {
            //System.out.println("X > "+x+" String "+i+ dicionario.get(i));
            if (dicionario.get(i).equals(x)) {
                //System.out.println("ENTROU NO IF");
                return true;
            }
        }
        return false;
    }

    /****
     * 
     * @param x
     * @param indice
     * @return
     */
    private int leCodigo(String x, int indice) {
        int index = 0;
        String aux = null;

        //System.out.println("STRING "+x);
        try {
            aux = x.substring(indice, indice + 3);
            index = Integer.parseInt(aux);
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("STRING OUT OF BOUNDS");
            return -1;

        }

        //System.out.println("aux : " + index);
        return index;
        //OK!
    }

    private String intParaString(int numero) {
        String aux;
        aux = dicionario.get(numero);
        return aux;
    }

    private String byteParaString(byte numero){
        String aux;
        aux = dicionario.get(numero);
        return aux;
    }

    /****
     * 
     * @param str
     * @param indice
     * @return
     */
    /*
     * 1. No início o dicionário contém todas as raízes possíveis;
    2. cW <= primeira palavra código na sequência codificada (sempre é uma raiz);
    3. Coloque a string(cW) na sequência de saída;
    4. pW <= cW;
    5. cW <= próxima palavra código da sequência codificada;
    6. A string(cW) existe no dicionário ?
    a. se sim,
    i. coloque a string(cW) na sequência de saída;
    ii. P <= string(pW);
    iii. C <= primeiro caracter da string(cW);
    iv. adicione a string P+C ao dicionário;
    b. se não,
    i. P <= string(pW);
    ii. C <= primeiro caracter da string(pW);
    iii. coloque a string P+C na sequência de saída e adicione-a ao dicionário;
    7. Existem mais palavras código na sequência codificada ?
    a. se sim,
    i. volte ao passo 4;
    b. se não,
    i. FIM.

     */
    public String decode(String stringCodificada) {
        int contador = 0;
        int tamanhoDicionario;
        String aux = null;
        String aux2 = null;
        int counter = 0;
        //boolean maiorQueDicionario = false;
        int cW = 0;
        int pW = 0;
        String P = new String();
        char C;

        cW = leCodigo(stringCodificada, 0);
        stringDecodificada += intParaString(cW);



        for (int i = 1; i < (stringCodificada.length() / 3); i++) {
            pW = cW;
            cW = leCodigo(stringCodificada, 3 * i);
            tamanhoDicionario = dicionario.size();
//            System.out.println("cW -> "+cW);
//            System.out.println("TAMANHO DICIONARIO : "+dicionario.size());

            ////var bool , if se cW for maior ou igual ao tam do dicionario


            if (cW >= tamanhoDicionario) {
                P = intParaString(pW);
                C = P.charAt(0);
                stringDecodificada += (P + C);
                dicionario.add(P+C);
                //System.out.println("ADICIONANDO STRING : " + P + C);
            } else {
                aux = intParaString(cW);
                //System.out.println("CARACTERE > " + aux);
                if (estaNoDicionario(aux)) {
                    stringDecodificada += aux;
                    aux2 = intParaString(pW);
                    P = aux2;
                    C = aux.charAt(0);
                    dicionario.add(P + C);
                    //System.out.println("ADICIONANDO STRING " + aux);
                } else {
                    P = intParaString(pW);
                    C = P.charAt(0);
                    stringDecodificada += (P + C);
                    //System.out.println("ADICIONANDO STRING : " + P + C);
                }
            }



            //contador = i+3;
/*
            if (estaNoDicionario(aux)) {
            stringDecodificada += aux;
            aux2 = intParaString(pW);
            P = aux2;
            C = aux.charAt(0);
            dicionario.add(P + C);
            System.out.println("ADICIONANDO STRING " + aux);
            } else {
            P = intParaString(pW);
            C = P.charAt(0);
            stringDecodificada += (P + C);
            System.out.println("ADICIONANDO STRING : " + P + C);
            }
             */
        }

        // cW = retornaCodigo2();



        return stringDecodificada;
    }

    /*****
     * decode() : recebe um arrayList de bytes e retorna uma string, que sera
     *
     * @param mensagemCodificada
     * @return
     */
    public String decode(ArrayList<Byte> mensagemCodificada) {
        int contador = 0;
        String aux = null;
        String aux2 = null;
        int counter = 0;
        boolean maiorQueDicionario = false;
        byte cW = 0;
        byte pW = 0;
        String P = new String();
        char C;
        cW = mensagemCodificada.get(0);
        
        //cW = leCodigo(stringCodificada, 0);
        mensagemDecodificada.add((byte)cW);

        ///stringDecodificada += intParaString(cW);



        for (int i = 1; i < mensagemCodificada.size(); i++) {
            pW = cW;
            cW = mensagemCodificada.get(i);


            ////var bool , if se cW for maior ou igual ao tam do dicionario


            if (cW >= dicionario.size()) {
                P = byteParaString(pW);
                C = P.charAt(0);
                stringDecodificada += (P + C);
                //mensagemDecodificada.add
                //System.out.println("ADICIONANDO STRING : " + P + C);
            } else {
                aux = byteParaString(cW);
                //System.out.println("CARACTERE > " + aux);
                if (estaNoDicionario(aux)) {
                    stringDecodificada += aux;
                    aux2 = byteParaString(pW);
                    P = aux2;
                    C = aux.charAt(0);
                    dicionario.add(P + C);
                    //System.out.println("ADICIONANDO STRING " + aux);
                } else {
                    P = byteParaString(pW);
                    C = P.charAt(0);
                    stringDecodificada += (P + C);
                    //System.out.println("ADICIONANDO STRING : " + P + C);
                }
            }



            //contador = i+3;
/*
            if (estaNoDicionario(aux)) {
            stringDecodificada += aux;
            aux2 = intParaString(pW);
            P = aux2;
            C = aux.charAt(0);
            dicionario.add(P + C);
            System.out.println("ADICIONANDO STRING " + aux);
            } else {
            P = intParaString(pW);
            C = P.charAt(0);
            stringDecodificada += (P + C);
            System.out.println("ADICIONANDO STRING : " + P + C);
            }
             */
        }

        // cW = retornaCodigo2();



        return stringDecodificada;
    }


}
