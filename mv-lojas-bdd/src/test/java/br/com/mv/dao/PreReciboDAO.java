package br.com.mv.dao;

import static br.com.mv.model.SessionVariables.COD_NRO_LOJA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.mv.jdbc.Conexao;
import br.com.mv.model.Carga;
import br.com.mv.model.NotaFiscal;
import br.com.mv.model.PreRecibo;
import br.com.mv.pages.HomePage;
import net.serenitybdd.core.Serenity;

public class PreReciboDAO {

Connection connection;
	
	public PreRecibo getPreReciboAprovado() {
		
		connection = new Conexao().getConexao();
		
		String sql = " SELECT  PR.NRO_PRE_RECIBO, PR.NRO_RECIBO, PR.NRO_PEDIDO, PR.NRO_LOJA_PRE_RECIBO, PR.NRO_TIPO_PRE_RECIBO_TROCDEV, L.COD_NRO_LOJA " + 
					"FROM    MV_PRE_RECIBO_TROCADEV PR " + 
					"        inner join MV_LOJA l on L.NRO_LOJA = PR.NRO_LOJA_PEDIDO and L.NRO_EMPRESA = 5 " + 
					"WHERE   PR.NRO_STATUS_PRE_RECIBO_TROCDEV = 7 " + 
					"        AND PR.NRO_TIPO_PRE_RECIBO_TROCDEV = 1 " + 
					"        AND NOT EXISTS (SELECT P.REC_NRO_PEDIDO FROM MV_PLANILHA_PAGTO_PEDIDO P WHERE P.REC_NRO_PEDIDO = PR.NRO_PEDIDO) " + 
					"        AND (select count(*) from MV_PRE_RECIBO_TROCADEV PR2 where PR2.NRO_PEDIDO = PR.NRO_PEDIDO) = 1 " + 
					"        AND PR.NRO_RECIBO IS NOT NULL " + 
					"        AND PR.VR_PRE_RECIBO < 1000 " + 
					"        AND ROWNUM < 2 ";
		
		try {
			PreRecibo preRecibo = new PreRecibo();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				preRecibo.setNroPedido(rs.getInt("NRO_PEDIDO"));
				preRecibo.setCodNroLoja(rs.getInt("COD_NRO_LOJA"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return preRecibo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}