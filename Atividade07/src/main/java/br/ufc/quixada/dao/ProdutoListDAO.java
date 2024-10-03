package br.ufc.quixada.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Produto;

@Repository
// @Primary
public class ProdutoListDAO implements ProdutoDAO {

	private List<Produto> clientes;
	
	private static int idProximo = 1;
	
	
	public ProdutoListDAO() {
        this.produto = new ArrayList<Produto>();
    }

	
	public void save(Produto entity) {
        // Insere um produto se o ID do objeto for 0.
        if (entity.getId() == 0) {
            entity.setId(idProximo++); 
            produto.add(entity); 
        } else {
            // Atualiza um produto se o ID não for 0.
            int posicaoNaLista = findIndex(entity.getId()); // Encontra a posição do produto na lista pelo ID.
            produto.set(posicaoNaLista, entity); // Substitui o produto existente na posição encontrada com o novo produto.
        }
    }

	public void delete(int id) {
        // Remove um produto com o código especificado.
        produto.remove(find(id));
    }

    public Produto find(int id) {
        // Busca um produto pelo ID.
        for (Produto cl : this.produto) {
            if (cl.getId() == id) {
                return cl; // Retorna o produto se encontrado.
            }
        }
        return null; // Retorna null se o produto não for encontrado.
    }


    private int findIndex(int id) {
    
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                return i; // Retorna o índice do produto se encontrado.
            }
        }
        return -1; /
    }


    public List<Produto> find() {
        // Retorna uma lista de todos os produtos.
        return this.produtos;
    }

    public Produto findByCodigo(int codigo) {
        // Retorna o produto com o código especificado.
        for (Produto cl : this.produtos) {
            if (cl.getCodigo() == codigo) {
                return cl;
            }
        }
        return null; // Retorna null se o produto não for encontrado com o código especificado.
    }

    public List<Produto> findByPreco(Float preco) {
        // Retorna uma lista de produtos com preço menor ou igual ao máximo especificado.
        List<Produto> resultado = new ArrayList<>();
        for (Produto produto : produto) {
          
            if (produto.getPreco().compareTo(precoMaximo) <= 0) {
                resultado.add(produto);
            }
        }
        return resultado;
    }

    public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {

        List<Produto> produtosPorData = new ArrayList<>();

        for (Produto produto : produtos) {
            // Converte a data de última entrada do produto em um objeto LocalDate.
            LocalDate dataUltimaEntrada = LocalDate.parse(produto.getUltimaEntrada());
            // Verifica se a data de última entrada não é antes da data inicial e não é depois da data final.
            if (!dataUltimaEntrada.isBefore(dataInicial) && !dataUltimaEntrada.isAfter(dataFinal)) {
                // Se a data de última entrada estiver dentro do intervalo, adiciona o produto à lista de produtosPorData.
                produtosPorData.add(produto);
            }
        }
        // Retorna a lista de produtos cuja data de última entrada está dentro do intervalo especificado.
        return produtosPorData;
    }
}