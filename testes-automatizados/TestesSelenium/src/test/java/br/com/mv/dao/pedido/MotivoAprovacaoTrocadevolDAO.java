package br.com.mv.dao.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mv.jdbc.Conexao;


public class MotivoAprovacaoTrocadevolDAO {
	
	private Connection connection;
	
	public List<MotivoAprovacaoTrocadevol> getMotivos(int nroLojaLogada, int nroPedido, int nroLojaPedido, 
			int nroItemPedidoProduto, int nroTipoFluxoAprovacao) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT  MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO, "
							+ " TRIM(MOTIVO.DESC_MOTIVO_FLUXO_APROVACAO) AS DESC_MOTIVO_FLUXO_APROVACAO "
						+ " FROM MV_MOTIVO_FLUXO_APROVACAO MOTIVO, "
						+ " MV_CONFIG_APROVACAO_TROCADEVOL CONFIG, "
						+ " MV_FLUXO_APROVACAO_TROCADEVOL FLUXO, "
						+ " MV_ITEM_PEDIDO_PRODUTO IPP, "
						//+ " mv_item_nf inf, "
						+ " MV_PRODUTO PRD "
					+ " WHERE MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO = CONFIG.NRO_MOTIVO_FLUXO_APROVACAO "
						+ " AND CONFIG.NRO_LOJA = " + nroLojaLogada //LOJA LOGADA
						+ " and IPP.NRO_PRODUTO = PRD.NRO_PRODUTO "
						+ " and IPP.NRO_PEDIDO = " + nroPedido //PEDIDO PESQUISADO
						+ " and IPP.NRO_LOJA = " + nroLojaPedido //LOJA DO PEDIDO
						
						+ " AND IPP.NRO_ITEM_PEDIDO_PRODUTO = " + nroItemPedidoProduto //ITEM DO PEDIDO SELECIONADO
						
						//+ " and inf.nro_loja_item_pedido_produto = ipp.nro_loja "
					    //+ " and inf.nro_pedido = ipp.nro_pedido "
					    //+ " and inf.nro_item_pedido_produto = ipp.nro_item_pedido_produto "
					    //+ " and inf.nro_item_nf = " + nroItemPedidoProduto  //ITEM DO PEDIDO SELECIONADO      
						
						+ " AND NVL(CONFIG.NRO_DEPTO, PRD.NRO_DEPTO) = PRD.NRO_DEPTO "
						+ " AND NVL(CONFIG.NRO_GRUPO_PRODUTO, PRD.NRO_GRUPO_PRODUTO) = PRD.NRO_GRUPO_PRODUTO "
						+ " AND MOTIVO.IND_ATIVO = 1 "
						+ " AND NVL(CONFIG.NRO_SUBGRUPO_PRODUTO, PRD.NRO_SUBGRUPO_PRODUTO) = PRD.NRO_SUBGRUPO_PRODUTO "
						+ " AND FLUXO.NRO_FLUXO_APROVACAO_TROCADEVOL = CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL "
						+ " AND (FLUXO.NRO_TIPO_FLUXO_APROV_TROCADEV = " + nroTipoFluxoAprovacao //TIPO FLUXO SELECIONADO
						+ " OR FLUXO.NRO_TIPO_FLUXO_APROV_TROCADEV = 3) "
						+ " GROUP BY MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO, "
						+ " MOTIVO.DESC_MOTIVO_FLUXO_APROVACAO, "
						+ " MOTIVO.IND_ATIVO, "
						+ " MOTIVO.NRO_USUARIO, "
						+ " MOTIVO.NRO_EMPRESA, "
						+ " MOTIVO.COD_MOTIVO "
						+ " ORDER BY MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO";
		
		
		try {
			List<MotivoAprovacaoTrocadevol> motivos = new ArrayList<MotivoAprovacaoTrocadevol>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				MotivoAprovacaoTrocadevol motivo = new MotivoAprovacaoTrocadevol();
				motivo.setNroMotivoFluxoAprovacao(rs.getInt("NRO_MOTIVO_FLUXO_APROVACAO"));
				motivo.setDescMotivoFluxoAprovacao(rs.getString("DESC_MOTIVO_FLUXO_APROVACAO"));
				motivos.add(motivo);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return motivos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}
	
	public List<MotivoAprovacaoTrocadevol> getMotivosProdutos(int nroLojaLogada, int nroPedido, int nroLojaPedido, 
			int nroProduto, int nroTipoFluxoAprovacao) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT  NRO_MOTIVO_FLUXO_APROVACAO "
				+ ",DESC_MOTIVO "
				+ ",NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ ",DESC_FLUXO "
				+ "FROM ( "
				+ "SELECT "
				+ "CONFIG.NRO_MOTIVO_FLUXO_APROVACAO NRO_MOTIVO_FLUXO_APROVACAO "
				+ ",CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ ",MOTIVO.DESC_MOTIVO_FLUXO_APROVACAO DESC_MOTIVO "
				+ ",FLUXO.DESC_FLUXO_APROVACAO DESC_FLUXO "
				+ ", CONFIG.NRO_SUBGRUPO_PRODUTO "
				+ ", CONFIG.NRO_GRUPO_PRODUTO "
				+ ", CONFIG.NRO_DEPTO "
				+ "FROM "
				+ "MV_CONFIG_APROVACAO_TROCADEVOL CONFIG, "
				+ "MV_FLUXO_APROVACAO_TROCADEVOL FLUXO, "
				+ "MV_MOTIVO_FLUXO_APROVACAO MOTIVO, "
				+ "MV_PRODUTO PROD, "
				+ "MV_LOJA LOJA "
				+ "WHERE PROD.NRO_PRODUTO                 = " + nroProduto 
				+ "AND CONFIG.NRO_LOJA                  = " + nroLojaPedido 
				+ "AND CONFIG.NRO_DEPTO                 = PROD.NRO_DEPTO "
				+ "AND CONFIG.NRO_GRUPO_PRODUTO         = PROD.NRO_GRUPO_PRODUTO "
				+ "AND CONFIG.NRO_SUBGRUPO_PRODUTO      = PROD.NRO_SUBGRUPO_PRODUTO "
				+ "AND FLUXO.NRO_FLUXO_APROVACAO_TROCADEVOL = CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ "AND FLUXO.NRO_TIPO_FLUXO_APROV_TROCADEV IN (" + nroTipoFluxoAprovacao + " , 3) "
				+ "AND FLUXO.IND_ATIVO = 1 "
				+ "AND MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO = CONFIG.NRO_MOTIVO_FLUXO_APROVACAO "
				+ "AND LOJA.NRO_LOJA = CONFIG.NRO_LOJA "
				+ "AND MOTIVO.NRO_EMPRESA = LOJA.NRO_EMPRESA "
				+ "AND NVL(MOTIVO.IND_ATIVO, 0) = 1 "
				+ "AND NVL(MOTIVO.NRO_EMPRESA, LOJA.NRO_EMPRESA) = LOJA.NRO_EMPRESA "
				+ "UNION "
				+ "SELECT "
				+ "CONFIG.NRO_MOTIVO_FLUXO_APROVACAO NRO_MOTIVO_FLUXO_APROVACAO "
				+ ",CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ ",MOTIVO.DESC_MOTIVO_FLUXO_APROVACAO DESC_MOTIVO "
				+ ",FLUXO.DESC_FLUXO_APROVACAO DESC_FLUXO "
				+ ", CONFIG.NRO_SUBGRUPO_PRODUTO "
				+ ", CONFIG.NRO_GRUPO_PRODUTO "
				+ ", CONFIG.NRO_DEPTO "
				+ "FROM "
				+ "MV_CONFIG_APROVACAO_TROCADEVOL CONFIG, "
				+ "MV_FLUXO_APROVACAO_TROCADEVOL FLUXO, "
				+ "MV_MOTIVO_FLUXO_APROVACAO MOTIVO, "
				+ "MV_PRODUTO PROD, "
				+ "MV_LOJA LOJA "
				+ "WHERE PROD.NRO_PRODUTO             = " + nroProduto 
				+ "AND CONFIG.NRO_LOJA                  = " + nroLojaPedido 
				+ "AND CONFIG.NRO_DEPTO                 = PROD.NRO_DEPTO "
				+ "AND CONFIG.NRO_GRUPO_PRODUTO         = PROD.NRO_GRUPO_PRODUTO "
				+ "AND CONFIG.NRO_SUBGRUPO_PRODUTO      IS NULL "
				+ "AND FLUXO.NRO_FLUXO_APROVACAO_TROCADEVOL = CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ "AND FLUXO.NRO_TIPO_FLUXO_APROV_TROCADEV IN (" + nroTipoFluxoAprovacao + " , 3) "
				+ "AND FLUXO.IND_ATIVO = 1 "
				+ "AND MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO = CONFIG.NRO_MOTIVO_FLUXO_APROVACAO "
				+ "AND LOJA.NRO_LOJA = CONFIG.NRO_LOJA "
				+ "AND MOTIVO.NRO_EMPRESA = LOJA.NRO_EMPRESA "
				+ "AND NVL(MOTIVO.IND_ATIVO, 0) = 1 "
				+ "AND NVL(MOTIVO.NRO_EMPRESA, LOJA.NRO_EMPRESA) = LOJA.NRO_EMPRESA "
				+ "UNION "
				+ "SELECT "
				+ "CONFIG.NRO_MOTIVO_FLUXO_APROVACAO NRO_MOTIVO_FLUXO_APROVACAO "
				+ ",CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ ",MOTIVO.DESC_MOTIVO_FLUXO_APROVACAO DESC_MOTIVO "
				+ ",FLUXO.DESC_FLUXO_APROVACAO DESC_FLUXO "
				+ ", CONFIG.NRO_SUBGRUPO_PRODUTO "
				+ ", CONFIG.NRO_GRUPO_PRODUTO "
				+ ", CONFIG.NRO_DEPTO "
				+ "FROM "
				+ "MV_CONFIG_APROVACAO_TROCADEVOL CONFIG, "
				+ "MV_FLUXO_APROVACAO_TROCADEVOL FLUXO, "
				+ "MV_MOTIVO_FLUXO_APROVACAO MOTIVO, "
				+ "MV_PRODUTO PROD, "
				+ "MV_LOJA LOJA "
				+ "WHERE "
				+ "PROD.NRO_PRODUTO             = " + nroProduto 
				+ "AND CONFIG.NRO_LOJA                  = " + nroLojaPedido 
				+ "AND CONFIG.NRO_DEPTO                 = PROD.NRO_DEPTO "
				+ "AND CONFIG.NRO_GRUPO_PRODUTO         IS NULL "
				+ "AND CONFIG.NRO_SUBGRUPO_PRODUTO      IS NULL "
				+ "AND FLUXO.NRO_FLUXO_APROVACAO_TROCADEVOL = CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ "AND FLUXO.NRO_TIPO_FLUXO_APROV_TROCADEV IN (" + nroTipoFluxoAprovacao + " , 3) "
				+ "AND FLUXO.IND_ATIVO = 1 "
				+ "AND MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO = CONFIG.NRO_MOTIVO_FLUXO_APROVACAO "
				+ "AND LOJA.NRO_LOJA = CONFIG.NRO_LOJA "
				+ "AND MOTIVO.NRO_EMPRESA = LOJA.NRO_EMPRESA "
				+ "AND NVL(MOTIVO.IND_ATIVO, 0) = 1 "
				+ "AND NVL(MOTIVO.NRO_EMPRESA, LOJA.NRO_EMPRESA) = LOJA.NRO_EMPRESA "
				+ "UNION "
				+ "SELECT "
				+ "CONFIG.NRO_MOTIVO_FLUXO_APROVACAO NRO_MOTIVO_FLUXO_APROVACAO "
				+ ",CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ ",MOTIVO.DESC_MOTIVO_FLUXO_APROVACAO DESC_MOTIVO "
				+ ",FLUXO.DESC_FLUXO_APROVACAO DESC_FLUXO "
				+ ", CONFIG.NRO_SUBGRUPO_PRODUTO "
				+ ", CONFIG.NRO_GRUPO_PRODUTO "
				+ ", CONFIG.NRO_DEPTO "
				+ "FROM "
				+ "MV_CONFIG_APROVACAO_TROCADEVOL CONFIG, "
				+ "MV_FLUXO_APROVACAO_TROCADEVOL FLUXO, "
				+ "MV_MOTIVO_FLUXO_APROVACAO MOTIVO, "
				+ "MV_PRODUTO PROD, "
				+ "MV_LOJA LOJA "
				+ "WHERE "
				+ "PROD.NRO_PRODUTO             = " + nroProduto 
				+ "AND CONFIG.NRO_LOJA                  = " + nroLojaPedido 
				+ "AND CONFIG.NRO_DEPTO                 IS NULL "
				+ "AND CONFIG.NRO_GRUPO_PRODUTO         IS NULL "
				+ "AND CONFIG.NRO_SUBGRUPO_PRODUTO      IS NULL "
				+ "AND FLUXO.NRO_FLUXO_APROVACAO_TROCADEVOL = CONFIG.NRO_FLUXO_APROVACAO_TROCADEVOL "
				+ "AND FLUXO.NRO_TIPO_FLUXO_APROV_TROCADEV IN (" + nroTipoFluxoAprovacao + " , 3) "
				+ "AND FLUXO.IND_ATIVO = 1 "
				+ "AND MOTIVO.NRO_MOTIVO_FLUXO_APROVACAO = CONFIG.NRO_MOTIVO_FLUXO_APROVACAO "
				+ "AND LOJA.NRO_LOJA = CONFIG.NRO_LOJA "
				+ "AND MOTIVO.NRO_EMPRESA = LOJA.NRO_EMPRESA "
				+ "AND NVL(MOTIVO.IND_ATIVO, 0) = 1 "
				+ "AND NVL(MOTIVO.NRO_EMPRESA, LOJA.NRO_EMPRESA) = LOJA.NRO_EMPRESA "
				+ ") "
				+ "ORDER BY "
				+ "NRO_SUBGRUPO_PRODUTO DESC "
				+ ",NRO_GRUPO_PRODUTO DESC "
				+ ",NRO_DEPTO DESC "
				+ ",NRO_MOTIVO_FLUXO_APROVACAO ASC";
		
		
		try {
			List<MotivoAprovacaoTrocadevol> motivos = new ArrayList<MotivoAprovacaoTrocadevol>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				MotivoAprovacaoTrocadevol motivo = new MotivoAprovacaoTrocadevol();
				motivo.setNroMotivoFluxoAprovacao(rs.getInt("NRO_MOTIVO_FLUXO_APROVACAO"));
				motivo.setDescMotivoFluxoAprovacao(rs.getString("DESC_MOTIVO"));
				motivos.add(motivo);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return motivos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}
}
