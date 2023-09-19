/*3. Escreva uma aplicação Java para ler um arquivo texto com codificação ISO-8859-1 e convertê-lo para UTF-8.

    Os nomes dos arquivos (origem e destino) devem ser definidos via argumentos de linha de comando (Dica: usar o String args[] do método main).
*/
import java.io.*;

public class Q3 {

  public static void main(String[] args) throws IOException {

    String arquivo1 = args[0];
    String arquivo2 = args[1];


    try {
      InputStream is = new FileInputStream(arquivo1);
      InputStreamReader isr = new InputStreamReader(is, "ISO-8859-1");
      BufferedReader br = new BufferedReader(isr);

      OutputStream os = new FileOutputStream(arquivo2);
      OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
      BufferedWriter bw = new BufferedWriter(osw);

      String s = br.readLine();
      while (s != null) {

        bw.write(s);
        bw.newLine();
        bw.flush();
        s = br.readLine();

      }

      br.close();
    } catch (IOException e) {
      System.err.println("Erro ao tentar gravar no arquivo");
      e.printStackTrace();
    }
  }
}