/* 
 * //todo Crie uma aplicação em Java que recebe via linha de comando: 
 * (1) o nome de um arquivo CSV; 
 * (2) o delimitador usado para separar os campos do arquivo; 
 * (3) uma lista de nomes das colunas do arquivo CSV que serão processados. 
 * 
 * Considere que o arquivo CSV (1) deva ter um cabeçalho com os nomes das 
 * colunas em sua primeira linha e que não tenha internamente colunas com 
 * Strings contendo o mesmo caractere usado como delimitador (2). 
 * A aplicação deve exibir a soma e a média das colunas selecionadas em (3), 
 * caso tenham dados numéricos. Se não tiverem dados numéricos,
 * somente exibir que aquela coluna não é numérica. 
*/

import java.io.*;

public class Q1 {
    
    public static void main(String[] args) throws IOException{
        
        String nomeArquivo = args[0];
        String delimitador = args[1];
        String NomeColuna = args[2];
        int coluna = Integer.parseInt(NomeColuna); // converte a string em inteiro
        
        try {
            FileReader nf = new FileReader(nomeArquivo); // lê o arquivo
            BufferedReader br = new BufferedReader(nf); // lê o arquivo linha a linha

            String s = br.readLine(); // lê a primeira linha do arquivo
            
            int soma = 0; 
            int media = 0;
            int cont = 0;

            while (s != null) {
                String[] str = s.split(delimitador); // cria um array com as colunas da linha
                
                if (coluna < str.length) {
                    String valueString = str[coluna]; 
                    
                    if (valueString.matches("[0-9]+")) { // verifica se é numérico
                        int numero = Integer.parseInt(valueString);
                        soma += numero; // soma os valores
                        cont++;
                    }
                }
                s = br.readLine();
            }
            
            if (cont > 0) { // verifica se existem valores numéricos
                media = soma / cont; // calcula a média
                System.out.println("Soma: " + soma + " Média: " + media); // exibe a soma e a média
            } else {
                System.out.println("Coluna não é numérica");
            }
            
            br.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}