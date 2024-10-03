package br.ufc.quixada.dao;

import java.time.LocalDate;
import java.util.List;

import br.ufc.quixada.entity.Produto;

public interface ProdutoDAO {
	
	public void save(Produto entity);
	
	public void delete(int id);
	
	public Produto find(int id);
	
	public List<Produto> find();
	
	public Produto findByCodigo(String codigo);
	
	public List<Produto> findByDescricao(String descricao);

	public List<Produto> findByPreco(double preco);

        public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal);
}
