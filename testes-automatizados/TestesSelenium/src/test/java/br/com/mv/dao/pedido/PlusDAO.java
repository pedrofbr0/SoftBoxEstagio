package br.com.mv.dao.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.mv.jdbc.Conexao;

public class PlusDAO {

	Connection connection;
	
	/**
	 * --- VALOR PLUS VENDEDOR ---
	 * 
	 * @return
	 */
	public float getValorPlusVendedor(String nroVendedor, String nroEmpresa) {
	
		String sql = "SELECT "
					  + "  (SELECT LISTAGG(EC.VALOR_CONFIGURACAO, ',') "
					  + "  WITHIN GROUP (ORDER BY VALOR_CONFIGURACAO) "
					  + "  FROM MV_EMPRESA_X_PARAMETRO_CONFIG EC "
					  + "    INNER JOIN MV_PARAMETRO_CONFIG PC "
					  + "            ON PC.NRO_PARAMETRO             = EC.NRO_PARAMETRO "
					  + "    WHERE PC.CHAVE_PARAMETRO_CONFIG = 'TABELA_EXCLUSAO_COMISSAO_PLUS' "
					  + "      AND EC.NRO_EMPRESA              =  " + nroEmpresa
					  + " ) AS TABELAS_EXCLUSAO, "
					  + " (SELECT VALOR_PARAMETRO_CONFIG "
					  + "  FROM MV_PARAMETRO_CONFIG "
					  + "  WHERE CHAVE_PARAMETRO_CONFIG = 'VALOR_PADRAO_COMISSAO_PLUS') AS VALOR_DEFAULT_PLUS, "
					  + " (SELECT PERCENTUAL_PLUS_VENDEDOR "
					  + "  FROM MV_PERCENTUAL_PLUS_VENDEDOR "
					  + "  WHERE NRO_VENDEDOR = " + nroVendedor + ") AS PERCENTUAL_PLUS_VENDEDOR "
					+ " FROM DUAL";
	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			float percentualPlusVendedor = 0;
			
			while (rs.next()) {
				percentualPlusVendedor = rs.getFloat("PERCENTUAL_PLUS_VENDEDOR");
			}
			
			stmt.close();
			rs.close();
			
			return percentualPlusVendedor;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	/**
	 * --- CALCULO PLUS PRODUTO E JUROS NA TELA DE PEDIDO ---
	 * 
	 * @param nroLoja
	 * @param nroPedido
	 * @throws SQLException 
	 */
	public Plus getCalculoPlusProdEJuros(String nroLoja, String nroPedido, String nroEmpresa, String nroVendedor) throws SQLException {
		
		connection = new Conexao().getConexao();
		
		float percentualPlusVendedor = getValorPlusVendedor(nroVendedor, nroEmpresa) / 100;
		
		String sql = "SELECT NRO_PEDIDO, "
						+ "  I.PRECO_PARTIDA, "
						+ "  I.PRECO_MINIMO, "
						  + "  I.VR_UNITARIO_PRODUTO, "
						  + "  I.VR_FATURAMENTO_PRODUTO, "
						  + "  I.NRO_PROMOCAO, "
						  + "  I.QTDE_PEDIDA_PRODUTO, "
						  + "  ROUND((I.VR_UNITARIO_PRODUTO-I.PRECO_MINIMO) * " + percentualPlusVendedor + ", 2) AS PLUS_PRODUTO_SEM_PROMO, "
						  + "  ROUND((I.VR_FATURAMENTO_PRODUTO-I.VR_UNITARIO_PRODUTO) * " + percentualPlusVendedor + ", 2) AS PLUS_JUROS_SEM_PROMO, "
						  + "  ROUND((I.VR_UNITARIO_PRODUTO-I.PRECO_PARTIDA) * " + percentualPlusVendedor + ", 2) AS PLUS_PRODUTO_COM_PROMO, "
						  + "  ROUND((I.VR_FATURAMENTO_PRODUTO-I.VR_UNITARIO_PRODUTO) * " + percentualPlusVendedor + ", 2) AS PLUS_JUROS_COM_PROMO "
					+ "  FROM MV_ITEM_PEDIDO_PRODUTO I "
					+ "  WHERE NRO_LOJA = " + nroLoja
					+ "  AND NRO_PEDIDO = " + nroPedido;
	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Plus plus = new Plus();
			
			while (rs.next()) {
				plus.setPlusProdSemPromocao(rs.getFloat("PLUS_PRODUTO_SEM_PROMO"));
				plus.setPlusJurosSemPromocao(rs.getFloat("PLUS_JUROS_SEM_PROMO"));
				plus.setPlusProdComPromocao(rs.getFloat("PLUS_PRODUTO_COM_PROMO"));
				plus.setPlusJurosComPromocao(rs.getFloat("PLUS_JUROS_COM_PROMO"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return plus;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e.getMessage());
		}
	}
	
	public void atualizarDataMetaVendedor() throws SQLException {
		connection = new Conexao().getConexao();
		
		Calendar cal = Calendar.getInstance();
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
		
        // System.out.println("mes: " + mes + " ano: " + ano);
        
        String sql = "update fp_meta_loja set mes_meta_loja = " + mes + ", ano_meta_loja = " + ano + " where nro_meta_loja = 14734"; // meta loja original: 11-2016
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			stmt.close();
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public boolean existeTabelaVigente() throws SQLException {
		connection = new Conexao().getConexao();
		
		String sql = "select * from mv_vigencia_parametro where nom_tabela = 'MV_PERCENTUAL_PLUS' and DT_INICIO_VIGENCIA = '01/12/2016' and DT_FIM_VIGENCIA = '03/12/2016'";
		
		Boolean existe = false;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				existe = true;
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return existe;
	}
}