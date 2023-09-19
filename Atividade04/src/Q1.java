
/*
 * 1. Crie uma aplicação em Java que recebe via linha de comando 
 * (1) o nome de um arquivo compactado a ser criado e (2) uma pasta. 
 * Compactar todos os arquivos e subpastas em um arquivo compactado
 *  com extensão zip.
 */

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Q1 {

    public static void main(String[] args) throws IOException {
        
        String nomeArquivoZip = args[0];
        String pastaOrigem = args[1];

        compactarDiretorio(pastaOrigem, nomeArquivoZip);
        System.out.println("Compactação concluída com sucesso!");
    }

    public static void compactarDiretorio(String pastaOrigem, String nomeArquivoZip) throws IOException {
        File pastaOrigemFile = new File(pastaOrigem);
        File arquivoZip = new File(nomeArquivoZip);

        try (FileOutputStream fos = new FileOutputStream(arquivoZip);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            ziparPasta(pastaOrigemFile, pastaOrigemFile.getName(), zos);
        }
    }

    private static void ziparPasta(File pasta, String pastaPai, ZipOutputStream zos) throws IOException {
        for (File arquivo : pasta.listFiles()) {
            if (arquivo.isDirectory()) {
                ziparPasta(arquivo, pastaPai + "/" + arquivo.getName(), zos);
                continue;
            }

            try (FileInputStream fis = new FileInputStream(arquivo)) {
                String nomeEntrada = pastaPai + "/" + arquivo.getName();
                ZipEntry zipEntry = new ZipEntry(nomeEntrada);
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
            }
        }
    }
}