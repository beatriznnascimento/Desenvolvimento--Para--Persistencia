/**
 * 2. Crie uma aplicação em Java que recebe via linha de comando 
 * (1) o nome de um arquivo a ser encriptado, 
 * (2) o nome do arquivo encriptado a ser criado e 
 * (3) a chave de encriptação.
 */
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

public class Q2 {

    public static void main(String[] args) throws Exception {
       
        String arquivo = args[0];
        String arquivoCripto = args[1];
        String chaveEncrip = args[2];

        try {
            criptografarArquivo(arquivo, arquivoCripto, chaveEncrip);
            System.out.println("Arquivo criptografado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public static void criptografarArquivo(String arquivo, String arquivoCripto, String chaveEncrip) throws Exception {
        SecretKeySpec chaveSpec = new SecretKeySpec(chaveEncrip.getBytes(), "AES");
        Cipher cifra = Cipher.getInstance("AES");
        cifra.init(Cipher.ENCRYPT_MODE, chaveSpec);

        try (InputStream entrada = new FileInputStream(arquivo);
             OutputStream saida = new FileOutputStream(arquivoCripto);
             CipherOutputStream cos = new CipherOutputStream(saida, cifra)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = entrada.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }
}