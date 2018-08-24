package br.com.mv.dao.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mv.jdbc.Conexao;

public class ItemFluxoAprovacaoDAO {
	
	Connection connection;
	
	public List<ItemFluxoAprovacao> getItensAprovacao(int nroLoja, int nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT IPED.NRO_PRODUTO, "
						 + " ITROC.NRO_ITEM_APROVACAO_TROCADEVOL, " 
						 + " ITROC.NRO_SEQ_APROVACAO, "    
						 + " ITROC.DESC_FASE_APROVACAO, "
						 + " ITROC.NRO_PEDIDO, "
						 + " ITROC.NRO_LOJA, "
						 + " ITROC.NRO_STATUS_APROV_TROCADEV_ITEM "
				  + " FROM   MV_ITEM_APROVACAO_TROCADEVOL ITROC, MV_ITEM_PEDIDO_PRODUTO IPED, MV_PRODUTO P "
				  + " WHERE  ITROC.NRO_PEDIDO = " + nroPedido
				         + " AND ITROC.NRO_LOJA = " + nroLoja
				         + " AND IPED.NRO_PEDIDO = " + nroPedido
				         + " AND IPED.NRO_LOJA = " + nroLoja
				         + " AND IPED.NRO_ITEM_PEDIDO_PRODUTO = ITROC.NRO_ITEM_PEDIDO_PRODUTO "
				         + " AND P.NRO_PRODUTO = IPED.NRO_PRODUTO "
				         + " AND ITROC.NRO_STATUS_APROV_TROCADEV_ITEM = 3 ";
		
		try {
			List<ItemFluxoAprovacao> itensAprovacao = new ArrayList<ItemFluxoAprovacao>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ItemFluxoAprovacao itemAprovacao = new ItemFluxoAprovacao();
				itemAprovacao.setNroItemAprovacaoTrocadevol(rs.getInt("NRO_ITEM_APROVACAO_TROCADEVOL"));
				itemAprovacao.setNroProduto(rs.getInt("nro_produto"));
				itemAprovacao.setNroSeqAprovacao(rs.getInt("NRO_SEQ_APROVACAO"));
				itemAprovacao.setDescFaseAprovacao(rs.getString("DESC_FASE_APROVACAO"));
				itensAprovacao.add(itemAprovacao);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return itensAprovacao;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public List<ItemFluxoAprovacao> getItensAprovacaoReverso(int nroUsuario, int nroAprovacaoReverso) {
		
		connection = new Conexao().getConexao();
		
		String sql =  "SELECT 	ITEM.NRO_ITEM_APROVACAO_REVERSO, "
				    + "			ITEM.DESC_FASE_APROVACAO, "
				    + "			ITEM.COD_PRIVILEGIO, "
				    + "			NVL(STATUS.DESC_STATUS_APROVACAO, 'PENDENTE') AS DESC_STATUS_APROVACAO, "
				    + "			NVL(STATUS.NRO_STATUS_APROVACAO_TROCADEV, 3 ) AS NRO_STATUS_APROVACAO_TROCADEV, "
				    + "			ITEM.NRO_USUARIO_APROVADOR, "
				    + "			US.NOME_USUARIO, "
				    + "			ITEM.OBSERVACAO_APROVADOR, "
				    + "			DECODE(STATUS.NRO_STATUS_APROVACAO_TROCADEV, 3, NULL, TO_CHAR(ITEM.DT_INCLUSAO, 'DD/MM/YYYY')) AS DT_LIBERACAO, "
				    + "			CASE WHEN(PRI.NRO_USUARIO IS NULL) THEN 'N' ELSE 'S' END as POSSUI_ACESSO, "
				    + "			AP.ID_PRODUTO_REVERSO "
					+ "FROM 	MV_ITEM_APROVACAO_REVERSO ITEM "
					+ "			INNER JOIN MV_APROVACAO_REVERSO AP ON(AP.NRO_APROVACAO_REVERSO = ITEM.NRO_APROVACAO_REVERSO) "
					+ "			LEFT JOIN MV_STATUS_APROVACAO_TROCADEVOL STATUS ON(STATUS.NRO_STATUS_APROVACAO_TROCADEV = ITEM.NRO_STATUS_APROVACAO_TROCADEV) "
					+ "			LEFT JOIN MV_USUARIO US ON(US.NRO_USUARIO = ITEM.NRO_USUARIO_APROVADOR) "
					+ "			LEFT JOIN MV_USUARIO_X_PRIVILEGIO PRI ON(PRI.COD_PRIVILEGIO = ITEM.COD_PRIVILEGIO AND PRI.NRO_USUARIO = " + nroUsuario + ") "
					+ "WHERE	ITEM.NRO_APROVACAO_REVERSO = " + nroAprovacaoReverso;
		
		try {
			List<ItemFluxoAprovacao> itensAprovacao = new ArrayList<ItemFluxoAprovacao>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ItemFluxoAprovacao itemAprovacao = new ItemFluxoAprovacao();
				itemAprovacao.setNroItemAprovacaoReverso(rs.getInt("NRO_ITEM_APROVACAO_REVERSO"));
				itemAprovacao.setIdProdutoReverso(rs.getInt("ID_PRODUTO_REVERSO"));
				itemAprovacao.setDescFaseAprovacao(rs.getString("DESC_FASE_APROVACAO"));
				itensAprovacao.add(itemAprovacao);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return itensAprovacao;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public int getNroAprovacaoReverso(int nroPreReciboReverso, int idProdutoReverso) {
		
		connection = new Conexao().getConexao();
		
		String sql =  "SELECT 	NRO_APROVACAO_REVERSO "
					+ "FROM 	MV_APROVACAO_REVERSO "
					+ "WHERE 	NRO_PRE_RECIBO_REVERSO = " + nroPreReciboReverso
					+ "			AND ID_PRODUTO_REVERSO = " + idProdutoReverso;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int nroAprovacaoReverso = 0;
			
			while (rs.next()) {
				nroAprovacaoReverso = rs.getInt("NRO_APROVACAO_REVERSO");
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return nroAprovacaoReverso;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}