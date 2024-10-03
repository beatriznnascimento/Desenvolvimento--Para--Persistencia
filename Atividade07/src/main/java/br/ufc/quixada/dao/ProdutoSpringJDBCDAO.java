package br.ufc.quixada.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Produto;
import lombok.extern.slf4j.Slf4j;

@Repository
@Primary
@Slf4j
public class ProdutoSpringJDBCDAO implements ProdutoDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public ProdutoSpringJDBCDAO() {
	}
	
	public void save(Produto entity) {
		String insert_sql = "insert into produto (codigo, preco, qtdEstoque, dt_ult_entrada) values (:codigo, :preco, :qtdEstoque, :dt_ult_entrada)";
		String update_sql = "update produto set codigo = :codigo, preco = :preco, qtdEstoque = :qtdEstoque, dt_ult_entrada = :dt_ult_entrada  where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("codigo", entity.getCodigo())
			.addValue("preco", entity.getPreco())
			.addValue("qtdEstoque", entity.getQtdEstoque())
			.addValue("dt_ult_entrada", entity.getDt_ult_entrada());
		if (entity.getId() == null) {
			jdbcTemplate.update(insert_sql, params);
		} else {
			params.addValue("id", entity.getId());
			jdbcTemplate.update(update_sql, params);
		}
	}

	public void delete(int id) {
		String sql = "delete from produto where id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("id", id);
		jdbcTemplate.update(sql, params);
	}

	public Produto find(int id) {
		Produto produto = null;
		try {
			String sql = "select id, codigo, preco, qtdEstoque, dt_ult_entrada from produto where id = :id";
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);
			produto = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> map(rs));
		} catch (EmptyResultDataAccessException e) {
			log.debug(e.getMessage());
		}
		return produto;
	}

	private Produto map(ResultSet rs) throws SQLException {
		Produto cl = new Produto();
		cl.setId(rs.getInt("id"));
		cl.setCodigo(rs.getString("cpf"));
		cl.setPreco(rs.getFloat("nome"));
		cl.setQtdEstoque(rs.getInt("fone"));
		cl.setDt_ult_entrada(rs.getString("renda"));
		return cl;
	}

	public List<Produto> find() {
			String sql = "select id, codigo, preco, qtdEstoque, dt_ult_entrada from produto";
			return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
	}

	public Produto findByCodigo(String codigo) {
		Produto produto = null;
		try {
			String sql = "select id, codigo, preco, qtdEstoque, dt_ult_entrada from produto where codigo = :codigo";
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("codigo", codigo);
			produto = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> map(rs));
		} catch (EmptyResultDataAccessException e) {
			log.debug(e.getMessage());
		}
		return produto;
}

	public List<Produto> findByPreco(Float preco) {
		String sql = "select id, codigo, preco, qtdEstoque, dt_ult_entrada from produto where upper(preco) like :preco";
		MapSqlParameterSource params = new MapSqlParameterSource()
			.addValue("preco", "%" + preco + "%");
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> map(rs));
	}
	
}
