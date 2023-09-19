import java.io.*;

public class Q1 {
    public static void main(String[] args) throws IOException {

        long tempoI = System.currentTimeMillis();

        try {
            String arquivo1 = args[0];
            String arquivo2 = args[1];

            InputStream is = new FileInputStream(arquivo1); // origem
            OutputStream os = new FileOutputStream(arquivo2); // destino

            int s = is.read();

            while (s != -1) {
                os.write(s);
                s = is.read();
                
            }
            is.close();
            os.close();
        } catch (IOException e) {
            System.err.println("Falha ao tentar gravar no arquivo");
            e.printStackTrace();
        }
        long tempoF = System.currentTimeMillis();

        long tempoT = tempoF - tempoI;
        System.out.println("Tempo de execucao: " + tempoT + " milissegundos");

    }
}
