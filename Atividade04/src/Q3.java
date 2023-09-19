/*
 * 3. Crie uma aplicação em Java que recebe via linha de comando 
 * (1) o nome de um arquivo a ser decriptado e 
 * (2) o nome do arquivo resultante da decriptação e 
 * (3) a chave de decriptação.
 */
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

public class Q3 {

    public static void main(String[] args) {
       
        String arquivoCripto = args[0];
        String arquivoDecripto = args[1];
        String chaveDecrip = args[2];

        try {
            descriptografarArquivo(arquivoCripto, arquivoDecripto, chaveDecrip);
            System.out.println("Arquivo descriptografado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public static void descriptografarArquivo(String arquivoCripto, String arquivoDecripto, String chaveDecrip) throws Exception {
        SecretKeySpec chaveSpec = new SecretKeySpec(chaveDecrip.getBytes(), "AES");
        Cipher cifra = Cipher.getInstance("AES");
        cifra.init(Cipher.DECRYPT_MODE, chaveSpec);

        try (InputStream entrada = new FileInputStream(arquivoCripto);
             CipherInputStream cis = new CipherInputStream(entrada, cifra);
             OutputStream saida = new FileOutputStream(arquivoDecripto)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                saida.write(buffer, 0, bytesRead);
            }
        }
    }
}