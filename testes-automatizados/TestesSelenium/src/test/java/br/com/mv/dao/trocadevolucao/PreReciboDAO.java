package br.com.mv.dao.trocadevolucao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;

/**
 * PreReciboDAO
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class PreReciboDAO {
	
	Connection connection = new Conexao().getConexao();
	
	public int getPreRecibo(int nroLoja, int nroPedido, int statusPreRecibo) {
		
		String sql = "SELECT nro_pre_recibo "
					+ "FROM mv_pre_recibo_trocadev "
					+ "WHERE nro_loja_pedido = " + nroLoja 
					+ " AND nro_pedido = " + nroPedido 
					+ " AND nro_status_pre_recibo_trocdev = " + statusPreRecibo;
		
		try {
			int nroPreRecibo = 0;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				nroPreRecibo = rs.getInt("nro_pre_recibo");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nroPreRecibo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public boolean existePreReciboPendenteEmissaoRecibo(int nroLoja, int nroPedido) {
		
		String sql = " SELECT  NRO_PRE_RECIBO, NRO_PEDIDO, NRO_STATUS_PRE_RECIBO_TROCDEV, NRO_RECIBO " +
					 " FROM    MV_PRE_RECIBO_TROCADEV " +
					 " WHERE   NRO_PEDIDO = " + nroPedido + 
			         "		   AND NRO_LOJA_PEDIDO = " + nroLoja +
			         " 		   AND NRO_RECIBO is null " +
			         " 		   AND NRO_STATUS_PRE_RECIBO_TROCDEV = 5 ";
		
		try {
			boolean existe = false;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				existe = true;
			}
			
			return existe;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public int getPreReciboReverso(int nroLoja, int nroPedido, int statusPreRecibo) {
		
		String sql =  "SELECT  MPRR.NRO_PRE_RECIBO_REVERSO "
					+ "FROM    MV_PRE_RECIBO_REVERSO MPRR "
					+ "		   INNER JOIN MV_PEDIDO_REVERSO MPR ON MPR.NRO_PEDIDO_REVERSO = MPRR.NRO_PEDIDO_REVERSO "
					+ "WHERE   MPR.NRO_PEDIDO = " + nroPedido
					+ "		   AND MPR.FILIAL_PEDIDO = " + nroLoja
					+ "		   AND MPRR.NRO_STATUS_PRE_RECIBO_TROCDEV = " + statusPreRecibo;

		try {
			int nroPreRecibo = 0;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				nroPreRecibo = rs.getInt("nro_pre_recibo_reverso");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nroPreRecibo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public int getNroLojaPreReciboReverso(int nroLoja, int nroPedido, int statusPreRecibo) {
		
		String sql =  "SELECT  MPRR.NRO_LOJA "
						+ "FROM    MV_PRE_RECIBO_REVERSO MPRR "
						+ "		   INNER JOIN MV_PEDIDO_REVERSO MPR ON MPR.NRO_PEDIDO_REVERSO = MPRR.NRO_PEDIDO_REVERSO "
						+ "WHERE   MPR.NRO_PEDIDO = " + nroPedido
						+ "		   AND MPR.FILIAL_PEDIDO = " + nroLoja
						+ "		   AND MPRR.NRO_STATUS_PRE_RECIBO_TROCDEV = " + statusPreRecibo;

		try {
			int nroLojaPreReciboReverso = 0;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				nroLojaPreReciboReverso = rs.getInt("nro_loja");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nroLojaPreReciboReverso;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public void resetPedidoReverso(String sql, int nroPedido, int nroLojaPedido) {
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, nroPedido);
			stmt.setInt(2, nroLojaPedido);
			
			stmt.executeQuery();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
}
