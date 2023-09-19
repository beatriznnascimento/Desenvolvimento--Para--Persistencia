
/*  Escreva uma aplicação Java para obter via teclado uma sequência de linhas de
 texto até que uma linha contendo somente a String "FIM" seja digitada. 
 Depois disso, solicitar o nome do arquivo via teclado e salvar todas as linhas 
 de texto digitadas no arquivo solicitado. A linha contendo a String "FIM" não 
 deve ser salva no arquivo.  */
import java.util.Scanner;
import java.io.*;

public class Q4 {
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);
        String linha;
        String nomeArquivo;
    
        nomeArquivo = sc.nextLine();
        FileWriter fw = new FileWriter(nomeArquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        linha = sc.nextLine();
        while (!linha.equals("FIM")) {
            bw.write(linha);
            bw.newLine();
            linha = sc.nextLine();
       
        }
        bw.close();
        sc.close();
    }
}