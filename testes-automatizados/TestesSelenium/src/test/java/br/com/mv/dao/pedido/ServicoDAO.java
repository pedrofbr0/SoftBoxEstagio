package br.com.mv.dao.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mv.jdbc.Conexao;

/**
 * ServicoDAO
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class ServicoDAO {
	
	private Connection connection = new Conexao().getConexao();
	
	public List<Servico> getServico(int nroLoja, int nroPedido, int nroPreRecibo) {
		
		String sql = "";
		
		if (nroPreRecibo == 0) {
			
			sql = "SELECT" 
					  + " s.nro_tipo_servico"
					  + " ,s.nro_servico"
					  + " ,s.desc_servico"
					  + " ,ts.flag_devolucao"
					  + " FROM"
					  + " mv_pedido ped"
					  + " INNER JOIN mv_item_pedido_servico ips ON ped.nro_pedido = ips.nro_pedido and ped.nro_loja = ips.nro_loja"
					  + " INNER JOIN mv_servico s on ips.nro_servico = s.nro_servico"
					  + " INNER JOIN mv_tipo_servico ts ON s.nro_tipo_servico = ts.nro_tipo_servico"
					  + " WHERE"
					  + " ped.nro_loja = " + nroLoja
					  + " AND ped.nro_pedido = " + nroPedido  
					  + " union"
					  + " SELECT"
					  + " s.nro_tipo_servico"
					  + " ,s.nro_servico"
					  + " ,s.desc_servico"
					  + " ,ts.flag_devolucao"
					  + " FROM"
					  + " mv_pedido ped"
					  + " INNER JOIN mv_pedido_servico ps ON ped.nro_pedido = ps.nro_pedido and ped.nro_loja = ps.nro_loja"
					  + " INNER JOIN mv_servico s ON ps.nro_servico = s.nro_servico"
					  + " INNER JOIN mv_tipo_servico ts ON s.nro_tipo_servico = ts.nro_tipo_servico"
					  + " WHERE" 
					  + " ped.nro_loja = " + nroLoja
					  + " AND ped.nro_pedido = " + nroPedido
					  + " AND s.nro_servico != 104141"; // Regra: Frete não aparece mais
			
		} else {
			
			sql = "SELECT" 
					  + " s.nro_tipo_servico"
					  + " ,s.nro_servico"
					  + " ,s.desc_servico"
					  + " ,ts.flag_devolucao"
					  + " FROM"
					  + " mv_pedido ped"
					  + " INNER JOIN mv_item_pedido_servico ips ON ped.nro_pedido = ips.nro_pedido and ped.nro_loja = ips.nro_loja"
					  + " INNER JOIN mv_servico s on ips.nro_servico = s.nro_servico"
					  + " INNER JOIN mv_tipo_servico ts ON s.nro_tipo_servico = ts.nro_tipo_servico"
					  + " WHERE"
					  + " ped.nro_loja = " + nroLoja
					  + " AND ped.nro_pedido = " + nroPedido  
					  + " union"
					  + " SELECT"
					  + " s.nro_tipo_servico"
					  + " ,s.nro_servico"
					  + " ,s.desc_servico"
					  + " ,ts.flag_devolucao"
					  + " FROM"
					  + " mv_pedido ped"
					  + " INNER JOIN mv_pedido_servico ps ON ped.nro_pedido = ps.nro_pedido and ped.nro_loja = ps.nro_loja"
					  + " INNER JOIN mv_servico s ON ps.nro_servico = s.nro_servico"
					  + " INNER JOIN mv_tipo_servico ts ON s.nro_tipo_servico = ts.nro_tipo_servico"
					  + " INNER JOIN MV_SERVICO_PRE_RECIBO_TROCDEV sprt on sprt.nro_servico_ps = s.nro_servico"
					  + " WHERE" 
					  + " ped.nro_loja = " + nroLoja
					  + " AND ped.nro_pedido = " + nroPedido
					  + " AND sprt.nro_pre_recibo = " + nroPreRecibo + " and sprt.NRO_STATUS_APROVACAO_TROCADEV = 3";
		}
		
		try {
			List<Servico> servicos = new ArrayList<Servico>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Servico servico = new Servico();
				servico.setNroServico(rs.getInt("nro_servico"));
				servico.setNroTipoServico(rs.getInt("nro_tipo_servico"));
				servico.setDescServico(rs.getString("desc_servico"));
				servico.setFlagDevolucao(rs.getString("flag_devolucao"));
				servicos.add(servico);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return servicos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}

	public List<Servico> getServicoReverso(int nroLoja, int nroPedido) {
		
		String sql =  "SELECT MSR.NRO_SERVICO, "
			        + "		  MSR.DESC_SERVICO, "
			        + "		  MTS.NRO_TIPO_SERVICO, "
			        + "		  MTS.FLAG_DEVOLUCAO "
					+ "FROM   MV_SERVICO_REVERSO MSR "
					+ "		  INNER JOIN MV_PEDIDO_REVERSO MPR ON MPR.NRO_PEDIDO_REVERSO = MSR.NRO_PEDIDO_REVERSO "
					+ "		  INNER JOIN MV_SERVICO MS ON MS.NRO_SERVICO = MSR.NRO_SERVICO "
					+ "		  INNER JOIN MV_TIPO_SERVICO MTS ON MTS.NRO_TIPO_SERVICO = MS.NRO_TIPO_SERVICO "
					+ "WHERE  MPR.NRO_PEDIDO = " + nroPedido
					+ "		  AND MPR.FILIAL_PEDIDO = " + nroLoja;
		
		try {
			
			List<Servico> servicos = new ArrayList<Servico>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Servico servico = new Servico();
				servico.setNroServico(rs.getInt("nro_servico"));
				servico.setNroTipoServico(rs.getInt("nro_tipo_servico"));
				servico.setDescServico(rs.getString("desc_servico"));
				servico.setFlagDevolucao(rs.getString("flag_devolucao"));
				servicos.add(servico);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return servicos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public List<Servico> getPreReciboServicoReverso(int nroPedido, int nroLojaPedido) {
		
		String sql =  "SELECT   MPRS.ID_SERVICO_REVERSO, MTS.FLAG_DEVOLUCAO "
					+ "FROM     MV_PRE_RECIBO_SERVICO_REVERSO MPRS "
			        + "		    INNER JOIN MV_PEDIDO_REVERSO MPR ON MPR.NRO_PEDIDO_REVERSO = MPRS.NRO_PEDIDO_REVERSO "
			        + "			INNER JOIN MV_SERVICO_REVERSO MSR ON MSR.ID_SERVICO_REVERSO = MPRS.ID_SERVICO_REVERSO AND MSR.NRO_PEDIDO_REVERSO = MPR.NRO_PEDIDO_REVERSO "
			        + "			INNER JOIN MV_SERVICO MS ON MS.NRO_SERVICO = MSR.NRO_SERVICO "
			        + "			INNER JOIN MV_TIPO_SERVICO MTS ON MTS.NRO_TIPO_SERVICO = MS.NRO_TIPO_SERVICO "
					+ "WHERE    MPR.NRO_PEDIDO = " + nroPedido
					+"		    AND MPR.FILIAL_PEDIDO = " + nroLojaPedido;
		
		try {
			
			List<Servico> servicos = new ArrayList<Servico>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Servico servico = new Servico();
				servico.setIdServicoReverso(rs.getInt("id_servico_reverso"));
				servico.setFlagDevolucao(rs.getString("flag_devolucao"));
				servicos.add(servico);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return servicos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
