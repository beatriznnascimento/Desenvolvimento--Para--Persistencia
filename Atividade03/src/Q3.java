/*
 * 3. Escreva um arquivo de propriedades Java via editor de textos. Esse arquivo deve ter os dados de chave e valor. Exemplo:

    arquivo config.properties
        arquivo = meu_arquivo.txt
        linha_inicial = 1
        linha_final = 3
    Depois, escreva uma classe Java que exibe da linha_inicial até a linha_final do arquivo, conforme definidos no arquivo de propriedades config.properties.

 */
import java.io.*;
import java.util.Properties;

public class Q3 {
    
    public static void main(String[] args) throws IOException{
   
        Properties properties =  new Properties();

        try{
            FileReader fr = new FileReader("config.properties");
            BufferedReader br = new BufferedReader(fr);
            properties.load(fr);

            String arq = properties.getProperty("arquivo");
            int li = Integer.parseInt(properties.getProperty("linha_inicial"));
            int lf = Integer.parseInt(properties.getProperty("linha_final"));

            String s;// = br.readLine();
            int qtdLinhas = 1;

            while((s = br.readLine()) != null){
                if(qtdLinhas >= li && qtdLinhas <= lf){
                System.out.println(s);
                }
                qtdLinhas++;
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage());

        }

        
    }
}
