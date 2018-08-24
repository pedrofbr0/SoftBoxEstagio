package br.com.mv.dao.caixa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;

public class ControleCaixaDAO {
	
	Connection connection;
		
	/**
	 * Pega as notas fiscais necessárias na hora de efetuar a troca ou devolução de um produto/serviço/pedido
	 * @param nroLoja, int com o número da loja
	 * @param nroPedido, int com o número do pedido
	 * @param nroTiposNf, String com todos os tipos de nf separados por ','. Ex.: ("1,10")
	 * @return
	 */
	public ControleCaixa getControleCaixa(int nroLoja) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT * "
					 + "FROM (SELECT 	MC.NRO_CONTROLE_CAIXA, TO_CHAR(MC.DT_ABERTURA_CAIXA, 'DD/MM/YYYY') AS DT_ABERTURA_CAIXA, "
					 + "				TO_CHAR(MC.DT_FECHAMENTO_CAIXA, 'DD/MM/YYYY') AS DT_FECHAMENTO_CAIXA "
					 + "      FROM 		MV_CONTROLE_CAIXA MC "
					 + "				INNER JOIN MV_LOJA ML ON ML.NRO_LOJA = MC.NRO_LOJA "
					 + "      WHERE 	ML.COD_NRO_LOJA = " + nroLoja
					 + "           		AND MC.DT_FECHAMENTO_CAIXA IS NOT NULL "
					 + "      ORDER BY MC.DT_FECHAMENTO_CAIXA DESC) "
					 + " WHERE ROWNUM = 1";
		
		try {
			ControleCaixa controleCaixa = new ControleCaixa();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				controleCaixa.setNumeroControle(rs.getInt("NRO_CONTROLE_CAIXA"));
				controleCaixa.setDataAberturaCaixa(rs.getString("DT_ABERTURA_CAIXA"));
				controleCaixa.setDataFechamentoCaixa(rs.getString("DT_FECHAMENTO_CAIXA"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return controleCaixa;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}