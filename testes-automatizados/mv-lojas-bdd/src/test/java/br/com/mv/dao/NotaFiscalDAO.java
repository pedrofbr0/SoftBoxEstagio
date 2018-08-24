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
import br.com.mv.pages.HomePage;
import net.serenitybdd.core.Serenity;

public class NotaFiscalDAO {

Connection connection;
	
	/**
	 * Pega as notas fiscais necessárias na hora de efetuar a troca ou devolução de um produto/serviço/pedido
	 * @param nroLoja, int com o número da loja
	 * @param nroPedido, int com o número do pedido
	 * @param nroTiposNf, String com todos os tipos de nf separados por ','. Ex.: ("1,10")
	 * @return
	 */
	public NotaFiscal getNotaFiscal(String nroLoja, String nroPedido, String tiposNf) {
		
		connection = new Conexao().getConexao();
		
		String sql = " SELECT NF.NRO_TIPO_NF, "
					       + " NF.NRO_CUPOM, "
					       + " NF.COD_TIPO_DOCTO, "
					       + " NF.NRO_NF, "
					       + " NF.SERIE_NF, "
					       + " NF.NRO_LOJA, "
					       + " L.COD_NRO_LOJA, "
					       + " NF.NRO_LOJA_DESTINO " 
					  + " FROM MV_PEDIDO PED "
					       + " INNER JOIN MV_NF NF ON NF.NRO_LOJA_PEDIDO = PED.NRO_LOJA "
					              + " AND NF.NRO_PEDIDO = PED.NRO_PEDIDO "
					       + " INNER JOIN MV_LOJA L ON NF.NRO_LOJA = L.NRO_LOJA "
					 + " WHERE PED.NRO_PEDIDO = " + nroPedido
					   + " AND L.COD_NRO_LOJA = " + nroLoja
					   + " AND NF.NRO_TIPO_NF IN (" + tiposNf + ")";
		
		//System.out.println(sql);
		
		try {
			NotaFiscal notaFiscal = new NotaFiscal();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				notaFiscal.setNroTipoNf(rs.getInt("NRO_TIPO_NF"));
				notaFiscal.setNroCupom(rs.getInt("NRO_CUPOM"));
				notaFiscal.setCodTipoDocto(rs.getString("COD_TIPO_DOCTO"));
				notaFiscal.setNroNf(rs.getInt("NRO_NF"));
				notaFiscal.setSerieNf(rs.getString("SERIE_NF"));
				notaFiscal.setNroLoja(rs.getInt("NRO_LOJA"));
				notaFiscal.setCodNroLoja(rs.getInt("COD_NRO_LOJA"));
				notaFiscal.setNroLojaDestino(rs.getInt("NRO_LOJA_DESTINO"));
				
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return notaFiscal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}
	/*
	public List<ProdutoNf> getItensNf(int nroNf, int nroLoja) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT " 
						  + " nf.nro_nf, "
						  + " nf.nro_loja, "
						  + " inf.nro_produto, "
						  + " p.cod_nro_produto, "
						  + " p.desc_produto "
						+ " FROM " 
						  + " mv_nf nf "
						  + " INNER JOIN mv_item_nf inf ON nf.nro_seq_nf = inf.nro_seq_nf "
						    + " AND nf.nro_loja = inf.nro_loja "
						  + " INNER JOIN mv_produto p ON inf.nro_produto = p.nro_produto "
						+ " WHERE " 
						  + " nf.nro_nf = " + nroNf + " and nf.nro_loja = " + nroLoja;
		
		try {
			List<ProdutoNf> itensNf = new ArrayList<ProdutoNf>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ProdutoNf prodNf = new ProdutoNf();
				prodNf.setNroNf(rs.getInt("nro_nf"));
				prodNf.setNroLoja(rs.getInt("nro_loja"));
				prodNf.setNroProduto(rs.getInt("nro_produto"));
				prodNf.setCodNroProduto(rs.getInt("cod_nro_produto"));
				prodNf.setDescProduto(rs.getString("desc_produto"));
				itensNf.add(prodNf);
			}
			
			return itensNf;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}*/
	
	public void aprovarNota(int nroLoja, int nroPedido, int nroTipoNf) {
		
		connection = new Conexao().getConexao();
		
		String sql = "UPDATE mv_nf set flag_situacao_docto_nf = 'A' WHERE nro_loja = ? AND nro_pedido = ? and nro_tipo_nf = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, nroLoja);
			stmt.setInt(2, nroPedido);
			stmt.setInt(3, nroTipoNf);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
	
	public void aprovarNotasPedido(int codNroLoja, int nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "UPDATE mv_nf set flag_situacao_docto_nf = 'A' WHERE nro_loja = (select l.nro_loja from mv_loja l where l.cod_nro_loja = ?) AND nro_pedido = ?";
		
		System.out.println(sql);
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, codNroLoja);
			stmt.setInt(2, nroPedido);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
	
	public void aprovarNFe(int nroLoja, int nroNF) {
		
		connection = new Conexao().getConexao();
		
		String sql = "UPDATE MV_NFE SET STATUS_RE = 'A', MOTIVO = 'Autorizado o uso da NF-e' " + 
					 "WHERE NRO_SEQ_NF = (SELECT NRO_SEQ_NF FROM MV_NF WHERE NRO_NF = ? AND NRO_LOJA = ?)";
		
		String sql2 = "UPDATE MV_NF SET FLAG_SITUACAO_DOCTO_NF = 'A' " +
					  "WHERE NRO_NF = ? AND NRO_LOJA = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			
			stmt.setInt(1, nroNF);
			stmt.setInt(2, nroLoja);
			stmt.executeQuery();
			stmt.close();
			
			stmt2.setInt(1, nroNF);
			stmt2.setInt(2, nroLoja);
			stmt2.executeQuery();
			stmt2.close();
			
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public NotaFiscal getNotaFiscalTransferencia(int nroLoja, int nroLojaPedido, int nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT NF.NRO_NF " +
					 "FROM   MV_CONTROLE_FATURAMENTO CF " +
					 "		 INNER JOIN MV_NF NF ON NF.NRO_SEQ_NF = CF.NRO_SEQ_NF " +
					 "WHERE  CF.NRO_LOJA = " + nroLoja + " AND CF.NRO_PEDIDO = " + nroPedido + " AND CF.NRO_LOJA_PEDIDO = " + nroLojaPedido + " and flag_processamento_nf = 0";
		
		System.out.println(sql);
		
		try {
			NotaFiscal notaFiscal = new NotaFiscal();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				notaFiscal.setNroNf(rs.getInt("NRO_NF"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return notaFiscal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public List<NotaFiscal> getNotaFiscalPedidoReverso(int nroPreReciboReverso) {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT  NF.NRO_NF " +
					 "FROM    MV_CONTROLE_FATURAMENTO CF " +
					 "        INNER JOIN MV_APROVACAO_REVERSO AR on AR.SEQ_CONTROLE_FATURAMENTO = CF.SEQ_CONTROLE_FATURAMENTO " +
					 "        INNER JOIN MV_NF NF on NF.NRO_SEQ_NF = CF.NRO_SEQ_NF " +
					 "WHERE   AR.NRO_PRE_RECIBO_REVERSO = " + nroPreReciboReverso;
		
		try {
			List<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				NotaFiscal nfs = new NotaFiscal();
				nfs.setNroNf(rs.getInt("NRO_NF"));
				notasFiscais.add(nfs);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return notasFiscais;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public void associarCargaControleFaturamento(int nroPedido, int nroLojaPedido, int nroCargaVeiculo, int nroLoja) {
		connection = new Conexao().getConexao();
				
		String sql = "select seq_controle_faturamento from mv_controle_faturamento where nro_pedido = " + nroPedido + " and nro_loja_pedido = " + nroLojaPedido + " and nro_loja = " + nroLoja + " and flag_processamento = 0";
		
		System.out.println(sql);
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			int seqControleFaturamento = 0;
			
			CargaDAO cargaVeiculoDAO = new CargaDAO();
			
			while (rs.next()) {
				seqControleFaturamento = rs.getInt("seq_controle_faturamento");
				
				Carga cargaVeiculo = cargaVeiculoDAO.getCargaVeiculo(nroCargaVeiculo);
				
				String sql1 = "update mv_controle_faturamento "
							+ "set 	NRO_FORNECEDOR_TRANSPORTADORA = " + cargaVeiculo.getNroTransportador() + ", "
							+ "		NRO_CARGA_VEICULO = " + cargaVeiculo.getNroCargaVeiculo() + ", "
							+ "		PLACA_VEICULO = '" + cargaVeiculo.getPlacaVeiculo() + "', "
							+ "		PLACA_VEICULO_UF = '" + cargaVeiculo.getPlacaVeiculoUF() + "' " +
						 	  "where SEQ_CONTROLE_FATURAMENTO = " + seqControleFaturamento;
				
				System.out.println(sql1);
				
				PreparedStatement stmt1 = connection.prepareStatement(sql1);
				stmt1.executeQuery();
				stmt1.close();
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public int getCargaVeiculoPorPedidoLoja(int nroLoja, int nroLojaPedido, int nroPedido) {
		connection = new Conexao().getConexao();
		
		String sql = "select nro_carga_veiculo from mv_controle_faturamento where nro_pedido = " + nroPedido + " and nro_loja_pedido = " + nroLojaPedido + " and nro_loja = " + nroLoja;
		
		System.out.println(sql);
		
		int nroCargaVeiculo = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				nroCargaVeiculo = rs.getInt("nro_carga_veiculo");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nroCargaVeiculo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public int getCargaVeiculoPorPedidoLojaETipoControleFaturamento(int nroLoja, int nroLojaPedido, int nroPedido, String tipoControleFaturamento) {
		connection = new Conexao().getConexao();
		
		String sql = "select nro_carga_veiculo from mv_controle_faturamento where nro_pedido = " + nroPedido + " and nro_loja_pedido = " + nroLojaPedido + " and nro_loja = " + nroLoja + " and tipo_controle_faturamento = '" + tipoControleFaturamento + "' and flag_processamento = 0";
		
		System.out.println(sql);
		
		int nroCargaVeiculo = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				nroCargaVeiculo = rs.getInt("nro_carga_veiculo");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nroCargaVeiculo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public int getNotaFiscalPorTipoControleFaturamento(int nroLoja, int nroLojaPedido, int nroPedido, String tipoControleFaturamento) {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT NF.nro_nf " +
					 "FROM   MV_CONTROLE_FATURAMENTO CF " + 
					 "		 INNER JOIN MV_NF NF ON NF.NRO_SEQ_NF = CF.NRO_SEQ_NF " + 
					 "WHERE  CF.NRO_LOJA = " + nroLoja + " AND CF.NRO_PEDIDO = " + nroPedido + " AND CF.NRO_LOJA_PEDIDO = " + nroLojaPedido + " and CF.TIPO_CONTROLE_FATURAMENTO = '" + tipoControleFaturamento + "'";
		
		System.out.println(sql);
		
		int nroNF = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				nroNF = rs.getInt("nro_nf");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nroNF;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public NotaFiscal getNotaFiscalPedidoMais90Dias() {
		connection = new Conexao().getConexao();
		
		String sql = "select NF.NRO_SEQ_NF, (select cod_nro_loja from mv_loja ml where nro_loja = nf.nro_loja) as cod_nro_loja, NF.NRO_LOJA, NF.NRO_NF, NF.SERIE_NF , NF.NRO_CUPOM " + 
					"from mv_nf nf " + 
					"where nf.nro_tipo_nf in (1,5,10) " + 
					" and not EXISTS (select 1 " + 
					"                 from mv_nf nf2 " + 
					"                 where nf2.nro_seq_nf_referencia = nf.nro_seq_nf " + 
					"                   and nf2.nro_pedido = nf.nro_pedido " + 
					"                   and nf2.nro_loja_pedido = nf.nro_loja_pedido " + 
					"                   and nf2.nro_tipo_nf in (22,23) " + 
					"                   ) " + 
					"and nf.nro_loja = 1101 " + 
					"and nf.dt_emissao_nf >= '01/01/2016' " + 
					"and nf.VR_ICMS_NF = 0 " + 
					"and rownum < 3";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			NotaFiscal nfs = new NotaFiscal();
			
			while (rs.next()) {
				nfs.setNroCupom(rs.getInt("NRO_CUPOM"));
				nfs.setSerieNf(rs.getString("SERIE_NF"));
				nfs.setCodNroLoja(rs.getInt("cod_nro_loja"));
				
				break;
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nfs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public NotaFiscal getNotaFiscalPedidoFaturado() {
		connection = new Conexao().getConexao();
		
		String sql = "select NF.NRO_SEQ_NF, (select cod_nro_loja from mv_loja ml where nro_loja = nf.nro_loja) as cod_nro_loja, NF.NRO_LOJA, NF.NRO_NF, NF.SERIE_NF , NF.NRO_CUPOM " + 
					"from mv_nf nf " + 
					"where nf.nro_tipo_nf in (1) " + 
					" and not EXISTS (select 1 " + 
					"                 from mv_nf nf2 " + 
					"                 where nf2.nro_seq_nf_referencia = nf.nro_seq_nf " + 
					"                   and nf2.nro_pedido = nf.nro_pedido " + 
					"                   and nf2.nro_loja_pedido = nf.nro_loja_pedido " + 
					"                   and nf2.nro_tipo_nf in (22,23,30) " + 
					"                   ) " + 
					"and nf.nro_loja = 1101 " +  
					"and nf.VR_ICMS_NF = 0 " +
					"and NF.NRO_CLIENTE != 1100000001 " +
					"and rownum < 3";
		
		//System.out.println(sql);
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			NotaFiscal nfs = new NotaFiscal();
			
			while (rs.next()) {
				nfs.setNroCupom(rs.getInt("NRO_CUPOM"));
				nfs.setSerieNf(rs.getString("SERIE_NF"));
				nfs.setCodNroLoja(rs.getInt("cod_nro_loja"));
				
				break;
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nfs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public NotaFiscal getNotaFiscalNFCEClinteConsumidor() {
		connection = new Conexao().getConexao();
		
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
		
		String sql = "select NF.NRO_SEQ_NF, (select cod_nro_loja from mv_loja ml where nro_loja = nf.nro_loja) as cod_nro_loja, NF.NRO_LOJA, NF.NRO_NF, NF.SERIE_NF , NF.NRO_CUPOM " + 
				"from mv_nf nf " + 
				"where nf.nro_tipo_nf = 5 " + 
				" and not EXISTS (select 1 " + 
				"                 from mv_nf nf2 " + 
				"                 where nf2.nro_seq_nf_referencia = nf.nro_seq_nf " + 
				"                   and nf2.nro_pedido = nf.nro_pedido " + 
				"                   and nf2.nro_loja_pedido = nf.nro_loja_pedido " + 
				"                   and nf2.nro_tipo_nf in (22,23,30,29) " + 
				"                    " + 
				"                   ) " + 
				"and nf.nro_loja = (select nro_loja from mv_loja where cod_nro_loja = " + codNroLoja + ") " + 
				"and nf.VR_ICMS_NF = 0 " + 
				"and NF.NRO_CLIENTE = (select nro_cliente from mv_cliente where nome_cliente like '%CONSUMIDOR%" + codNroLoja + "%') " + 
				"and rownum < 5";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			NotaFiscal nfs = new NotaFiscal();
			
			while (rs.next()) {
				nfs.setNroCupom(rs.getInt("NRO_CUPOM"));
				nfs.setSerieNf(rs.getString("SERIE_NF"));
				nfs.setCodNroLoja(rs.getInt("cod_nro_loja"));
				
				break;
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nfs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public NotaFiscal getNotaFiscalNFCEClinteComCPF() {
		connection = new Conexao().getConexao();
		
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
		
		String sql = "select NF.NRO_SEQ_NF, (select cod_nro_loja from mv_loja ml where nro_loja = nf.nro_loja) as cod_nro_loja, NF.NRO_LOJA, NF.NRO_NF, NF.SERIE_NF , NF.NRO_CUPOM " + 
				"from mv_nf nf " + 
				"where nf.nro_tipo_nf = 5 " + 
				" and not EXISTS (select 1 " + 
				"                 from mv_nf nf2 " + 
				"                 where nf2.nro_seq_nf_referencia = nf.nro_seq_nf " + 
				"                   and nf2.nro_pedido = nf.nro_pedido " + 
				"                   and nf2.nro_loja_pedido = nf.nro_loja_pedido " + 
				"                   and nf2.nro_tipo_nf in (22,23,30,29) " + 
				"                    " + 
				"                   ) " + 
				"and nf.nro_loja = (select nro_loja from mv_loja where cod_nro_loja = " + codNroLoja + ") " + 
				"and nf.VR_ICMS_NF = 0 " + 
				"and NF.NRO_CLIENTE != (select nro_cliente from mv_cliente where nome_cliente like '%CONSUMIDOR%" + codNroLoja + "%') " + 
				"and rownum < 5";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			NotaFiscal nfs = new NotaFiscal();
			
			while (rs.next()) {
				nfs.setNroCupom(rs.getInt("NRO_CUPOM"));
				nfs.setSerieNf(rs.getString("SERIE_NF"));
				nfs.setCodNroLoja(rs.getInt("cod_nro_loja"));
				
				break;
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nfs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public NotaFiscal getNotaFiscalSemPedido(String codNroLoja, String tipoNF, String nroNF) {
		
		connection = new Conexao().getConexao();
		
		String sql = "select  * " + 
					"from    mv_nf nf " + 
					"        inner join mv_loja ml on ml.nro_loja = nf.nro_loja " + 
					"where   nf.nro_tipo_nf = " + tipoNF + 
							"and not EXISTS (select 1 " +
										"from mv_nf nf2 " +
										"where nf2.nro_seq_nf_referencia = nf.nro_seq_nf  " +
										"and nf2.nro_pedido = nf.nro_pedido " +
										"and nf2.nro_loja_pedido = nf.nro_loja_pedido " +
										"and nf2.nro_tipo_nf in (32, 612) " +
										") " +
					"        and nf.NRO_NF = " + nroNF +
					"        and ml.cod_nro_loja = " + codNroLoja;
		
		System.out.println(sql);
		
		try {
			NotaFiscal notaFiscal = new NotaFiscal();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				notaFiscal.setNroTipoNf(rs.getInt("NRO_TIPO_NF"));
				notaFiscal.setNroCupom(rs.getInt("NRO_CUPOM"));
				notaFiscal.setCodTipoDocto(rs.getString("COD_TIPO_DOCTO"));
				notaFiscal.setNroNf(rs.getInt("NRO_NF"));
				notaFiscal.setSerieNf(rs.getString("SERIE_NF"));
				notaFiscal.setNroLoja(rs.getInt("NRO_LOJA"));
				notaFiscal.setCodNroLoja(rs.getInt("COD_NRO_LOJA"));
				notaFiscal.setNroLojaDestino(rs.getInt("NRO_LOJA_DESTINO"));
				
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return notaFiscal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}
	
	public NotaFiscal getNotaFiscalViaIntegracao(String tipoNF) {
		connection = new Conexao().getConexao();
		
		String sql = "";
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
		
		NotaFiscal nfs = new NotaFiscal();
		
		if (tipoNF.equals("614")) {
			
			sql = "SELECT NF.NRO_NF,  lj.cod_nro_loja, LJ.DESC_LOJA, SERIE_NF  FROM MV_NF NF " + 
					"   INNER JOIN MV_LOJA LJ ON NF.NRO_LOJA = LJ.NRO_LOJA " + 
					"  WHERE NF.NRO_TIPO_NF = " + tipoNF +
					"	and lj.cod_nro_loja = " + codNroLoja + 
					"  AND NF.NRO_SEQ_NF_REFERENCIA IS NULL " + 
					"  ORDER BY NF.DT_INCLUSAO DESC";
			
			
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					nfs.setNroNf(rs.getInt("NRO_NF"));
					nfs.setSerieNf(rs.getString("SERIE_NF"));
					nfs.setCodNroLoja(rs.getInt("cod_nro_loja"));
					
					break;
				}
				
				stmt.close();
				rs.close();
				connection.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
			
			
			
		} else if (tipoNF.equals("124") || tipoNF.equals("243") || tipoNF.equals("244")) {
			
			sql = "select  nf.NRO_NF, nf.nro_loja, nf.SERIE_NF, ml.cod_nro_loja, nf.nro_seq_nf,ij.nro_produto, mf.nro_fornecedor || ' - ' || mf.nome_fornecedor as nro_fornecedor " + 
					"from    mv_nf nf " + 
					"        inner join mv_item_nf ij on ij.nro_seq_nf = nf.nro_seq_nf and ij.nro_loja = nf.nro_loja " + 
					"        inner join mv_loja ml on ml.nro_loja = nf.nro_loja " +
					"		 inner join mv_fornecedor mf on mf.nro_fornecedor = nf.nro_fornecedor " +
					"where   nf.nro_tipo_nf = " + tipoNF +  
					"        and not exists (select 1 " + 
					"                        from mv_nf " + 
					"                        where NRO_SEQ_NF_REFERENCIA = nf.nro_seq_nf) " + 
					"        and ml.cod_nro_loja = " + codNroLoja + 
					"		 and nf.flag_situacao_docto_nf != 'C'";
			
			Map<String, String> metadata = HomePage.getTags();
			
			String base = "RELOH";
			
			if (!metadata.get("base").isEmpty()) {
				base = metadata.get("base");
			}
			
			if (base.equals("RELOH")) {
				sql += " and ml.nro_empresa = 5";
			}
			
			System.out.println("sql" + sql);
			
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				
				while (rs.next()) {
					nfs.setNroNf(rs.getInt("NRO_NF"));
					nfs.setSerieNf(rs.getString("SERIE_NF"));
					nfs.setCodNroLoja(rs.getInt("cod_nro_loja"));
					nfs.setNroProduto(rs.getInt("nro_produto"));
					nfs.setNroFornecedor(rs.getString("nro_fornecedor"));
					
					break;
				}
				
				stmt.close();
				rs.close();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
			
		}
		
		return nfs;
	}
	
	public NotaFiscal getNotaFiscalPorNumeroELoja(String nroNF, String codNroLoja) {
		connection = new Conexao().getConexao();
		
		
		String sql = "select  NRO_NF, SERIE_NF, cod_nro_loja " + 
					"from    mv_nf nf " + 
					"        inner join mv_loja ml on ml.nro_loja = nf.nro_loja " + 
					"where   nf.nro_tipo_nf = " + nroNF + 
					" and not EXISTS (select 1 " + 
					"                 from mv_nf nf2" + 
					"                 where nf2.nro_seq_nf_referencia = nf.nro_seq_nf " + 
					"                   and nf2.nro_pedido = nf.nro_pedido " + 
					"                   and nf2.nro_loja_pedido = nf.nro_loja_pedido " + 
					"                   and nf2.nro_tipo_nf in (32) " + 
					"                   ) " + 
					"        and ml.cod_nro_loja = " + codNroLoja + 
					"		 and nf.flag_situacao_docto_nf != 'C'" +
					"		 and nf.peso_bruto != 0" + 
					"        and nf.peso_liquido != 0";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			NotaFiscal nfs = new NotaFiscal();
			
			while (rs.next()) {
				nfs.setNroNf(rs.getInt("NRO_NF"));
				nfs.setSerieNf(rs.getString("SERIE_NF"));
				nfs.setCodNroLoja(rs.getInt("cod_nro_loja"));
				
				break;
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return nfs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}