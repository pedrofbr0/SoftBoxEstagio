package br.com.mv.dao.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mv.jdbc.Conexao;

/**
 * ProdutoDAO
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class ProdutoDAO {
	
	private Connection connection;
	
	public List<Produto> getProduto(int nroLoja, int nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT "
				+ " prod.nro_produto ,prod.desc_produto ,ipp.nro_item_pedido_produto "
				+ " FROM "
				+ " mv_pedido ped "
				+ " INNER JOIN mv_item_pedido_produto ipp ON ped.nro_pedido = ipp.nro_pedido and ped.nro_loja = ipp.nro_loja "
				+ " INNER JOIN mv_produto prod ON ipp.nro_produto = prod.nro_produto "
				+ " WHERE "
				+ " ped.nro_loja = " + nroLoja
				+ " AND ped.nro_pedido = " + nroPedido;
		
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setNroItemPedidoProduto(rs.getInt("nro_item_pedido_produto"));
				produto.setNroProduto(rs.getInt("nro_produto"));
				produto.setDescProduto(rs.getString("desc_produto"));
				produtos.add(produto);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return produtos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}

	public List<Produto> getProdutoReverso(int nroLoja, int nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql =  "SELECT PR.NRO_PRODUTO, "
					+ "		  PR.DESC_PRODUTO "
					+ "FROM   MV_PRODUTO_REVERSO PR "
					+ "		  INNER JOIN MV_PEDIDO_REVERSO MPR ON MPR.NRO_PEDIDO_REVERSO = PR.NRO_PEDIDO_REVERSO "
					+ "WHERE  MPR.NRO_PEDIDO = " + nroPedido
					+ "		  AND MPR.FILIAL_PEDIDO = " + nroLoja;
		
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setNroProduto(rs.getInt("nro_produto"));
				produto.setDescProduto(rs.getString("desc_produto"));
				produtos.add(produto);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return produtos;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<Produto> getPreReciboProdutoReverso(int nroPedido, int nroLojaPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql =  "SELECT   MPRPR.ID_PRODUTO_REVERSO, PR.NRO_PRODUTO "
					+ "FROM     MV_PRE_RECIBO_PRODUTO_REVERSO MPRPR "
					+ "		    INNER JOIN MV_PEDIDO_REVERSO MPR ON MPR.NRO_PEDIDO_REVERSO = MPRPR.NRO_PEDIDO_REVERSO "
					+ "			INNER JOIN MV_PRODUTO_REVERSO PR ON PR.ID_PRODUTO_REVERSO = MPRPR.ID_PRODUTO_REVERSO AND PR.NRO_PEDIDO_REVERSO = MPR.NRO_PEDIDO_REVERSO "
					+ "WHERE    MPR.NRO_PEDIDO = " + nroPedido
					+ "		    AND MPR.FILIAL_PEDIDO = " + nroLojaPedido;

		try {
			List<Produto> produtos = new ArrayList<Produto>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProdutoReverso(rs.getInt("id_produto_reverso"));
				produto.setNroProduto(rs.getInt("nro_produto"));
				produtos.add(produto);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return produtos;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public int getNroProduto(int codNroProduto, int codCorProduto, String codTipoVoltagem) {
		connection = new Conexao().getConexao();
		
		String sql =  "select nro_produto from mv_produto where cod_nro_produto = " + codNroProduto + " and cod_cor_produto = " + codCorProduto + " and cod_tipo_voltagem = '" + codTipoVoltagem + "'" ;
		
		
		int nroProduto = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				nroProduto = rs.getInt("nro_produto");
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return nroProduto;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
}