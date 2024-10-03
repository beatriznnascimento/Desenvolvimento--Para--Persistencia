package br.ufc.quixada.dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.ufc.quixada.entity.Produto;

@Repository
// @Primary
public class ProdutoJDBCDAO implements ProdutoDAO {

	@Autowired
	private DataSource ds;

	public ProdutoJDBCDAO() { }
	
	public void save(Produto entity) {
		Connection con = null;
		try {
			con = ds.getConnection();
			String insert_sql = "insert into produto (codigo, preco, qtdEstoque, dt_ult_entrada) values (?, ?, ?, ?)";
			String update_sql = "update produto set codigo = ?, preco = ?, qtdEstoque = ?, dt_ult_entrada = ? where id = ?";
			PreparedStatement pst;
			if (entity.getId() == 0) {
				pst = con.prepareStatement(insert_sql);
			} else {
				pst = con.prepareStatement(update_sql);
				pst.setInt(5, entity.getId());
			}
			pst.setString(1, entity.getCodigo());
			pst.setFloat(2, entity.getPreco());
			pst.setInt(3, entity.getQtdEstoque());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
	}

	public void delete(int id) {
		Connection con = null;
		try {
			con = ds.getConnection();
			String sql = "delete from produto where id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
	}

	public Produto find(int id) {
		Connection con = null;
		Produto cl = null;
		try {
			con = ds.getConnection();
			String sql = "select id, codigo, preco, qtdEstoque, dt_ult_entrada where id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cl = map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return cl;
	}

	private Produto map(ResultSet rs) throws SQLException {
		Produto cl = new Produto();
		cl.setId(rs.getInt("id"));
		cl.setPreco(rs.getFloat("preco"));
		cl.setCodigo(rs.getString("codigo"));
		return cl;
	}

	public List<Produto> find() {
		Connection con = null;
		List<Produto> result = new ArrayList<>();
		try {
			con = ds.getConnection();
			PreparedStatement pst;
			String sql = "select * from produto";
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Produto cl = map(rs);
				result.add(cl);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return result;
	}

	public Produto findByCodigo(String codigo) {
		Connection con = null;
		Produto cl = null;
		try {
			con = ds.getConnection();
			PreparedStatement pst;
			String sql = "select id, codigo, preco, qtdEstoque where codigo = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, codigo);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cl = map(rs);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return cl;
	}

	public List<Produto> findByPreco(Float preco) {
		Connection con = null;
		List<Produto> result = null;
		try {
			con = ds.getConnection();
			PreparedStatement pst;
			String sql = "select id, codigo, preco, qtdEstoque where codigo = ?";
			pst = con.prepareStatement(sql);
			pst.setFloat(1, preco);
			ResultSet rs = pst.executeQuery();
			result = new ArrayList<Produto>();
			while (rs.next()) {
				Produto cl = map(rs);
				result.add(cl);
			}
		} catch (SQLException e) {
			throw new DAOException("Operação não realizada com sucesso.", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível fechar a conexão.",e);
			}
		}
		return result;
	}
	public List<Produto> findByDataUltimaEntrada(LocalDate dataInicial, LocalDate dataFinal) {
        Connection con = null;
		List<Produto> result = null;
        try  {
			con = ds.getConnection();
            String sql = "SELECT id, codigo, preco, qtdEstoque, dt_ult_entrada FROM produtos WHERE dt_ult_entrada BETWEEN ? AND ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, java.sql.Date.valueOf(dataInicial));
            pst.setDate(2, java.sql.Date.valueOf(dataFinal));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto produto = map(rs);
                result.add(produto);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        }
        return result;
    }
	
}
