import java.io.*;

public class Q2 {

    public static void main(String[] args) throws IOException {

        long tempoI = System.currentTimeMillis();
        String arquivo1 = args[0];
        String arquivo2 = args[1];

        try {

            InputStream origem = new FileInputStream(arquivo1);
            InputStreamReader isr = new InputStreamReader(origem, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            OutputStream destino = new FileOutputStream(arquivo2, true);
            OutputStreamWriter osw = new OutputStreamWriter(destino, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            byte[] tamanho = new byte[8192];
            int lerByte;

            while ((lerByte = origem.read(tamanho)) != -1) {
                destino.write(tamanho, 0, lerByte);

                lerByte = origem.read(tamanho, 0, lerByte);

            }

            origem.close();
            destino.close();

        } catch (IOException e) {
            System.err.println("Erro ao tentar gravar no arquivo");
            e.printStackTrace();
        }
        long tempoF = System.currentTimeMillis();
        long tempoT = tempoF - tempoI;

        System.out.println("Tempo de execucao: " + tempoT + " milissegundos");

    }
}
