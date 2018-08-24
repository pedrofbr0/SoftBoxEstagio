package br.com.mv.dao.cargaVeiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;

public class CargaDAO {
	
	Connection connection;
	
	public Carga getCarga(int nroLoja, int nroPedido, int nroLojaPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT NRO_CARGA_VEICULO " +
					 "FROM 	 MV_CONTROLE_FATURAMENTO " + 
					 "WHERE  NRO_PEDIDO = " + nroPedido +
					 " 		 AND NRO_LOJA_PEDIDO = " + nroLojaPedido +
					 "		 AND NRO_LOJA = " + nroLoja;
		
		try {
			Carga cargaVeiculo = new Carga();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				cargaVeiculo.setNroCargaVeiculo(rs.getInt("NRO_CARGA_VEICULO"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return cargaVeiculo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public Carga getCargaVeiculo(int nroCargaVeiculo) {
		
		connection = new Conexao().getConexao();
		
		String sql = "select NRO_CARGA_VEICULO, DESC_CARGA_VEICULO, DT_CARGA_VEICULO, NRO_LOJA_CARGA, NRO_TRANSPORTADOR, " +
					 "		 PLACA_VEICULO, PLACA_VEICULO_UF, NOME_MOTORISTA, NRO_TIPO_CARGA_VEICULO " +
					 "from mv_carga_veiculo where nro_carga_veiculo = " + nroCargaVeiculo;
		
		try {
			Carga cargaVeiculo = new Carga();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				cargaVeiculo.setNroCargaVeiculo(rs.getInt("NRO_CARGA_VEICULO"));
				cargaVeiculo.setNroTransportador(rs.getInt("NRO_TRANSPORTADOR"));
				cargaVeiculo.setPlacaVeiculo(rs.getString("PLACA_VEICULO"));
				cargaVeiculo.setPlacaVeiculoUF(rs.getString("PLACA_VEICULO_UF"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return cargaVeiculo;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}