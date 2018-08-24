package br.com.mv.dao;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import br.com.mv.jdbc.Conexao;
import br.com.mv.model.ItemPedidoProduto;
import br.com.mv.model.ItemPedidoServico;
import br.com.mv.model.Pedido;
import br.com.mv.model.PlanoPagamento;
import br.com.mv.utils.Utils;

public class PedidoDAO {

	Connection connection;
	
	private static final String s = System.getProperty("file.separator");
	
	private static final String PATH_ARQUIVO = System.getProperty("user.dir") + s + "src" + s + "test" + s + "resources" + s + "sql" + s; //O caminho para o sql nesse caso está baseado em Windows
	
//	private static final String PATH_ARQUIVO = System.getProperty("user.dir") + "\\src\\test\\resources\\sql\\"; //O caminho para o sql nesse caso está baseado em Windows
	
	public void executarScriptReset(String nomScript) throws IOException {
		
		connection = new Conexao().getConexao();
		
		String sql = new Utils().lerArquivo(PATH_ARQUIVO + nomScript);
		
		System.out.println(sql);
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.executeQuery();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public double buscarValorBasePlusJuros(String codNroLoja, String nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "select decode( p.nro_tipo_Venda,76, ((i.vr_unitario_produto * i.fator_prestacao) * p.qtde_parcelas) - i.vr_unitario_produto, i.vr_faturamento_produto - i.vr_unitario_produto) base_plus_juros"
				+ " from mv_item_pedido_produto i, mv_pedido p"
				+ " where p.nro_loja=i.nro_loja"
				+ " and p.nro_pedido=i.nro_pedido"
				+ " and p.nro_loja= (select nro_loja from mv_loja where cod_nro_loja = " + codNroLoja + ")"
				+ " and p.nro_pedido= " + nroPedido;
						
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			double basePlusJuros = 0;
			
			
			while (rs.next()) {
				basePlusJuros = rs.getDouble("base_plus_juros");
				System.out.println("basePlusJuros: " + basePlusJuros);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return basePlusJuros;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}			
	}
	
	public double buscarPercentualPlusVendFinan(String codNroLoja, String nroVendedor) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT percentual_plus_vend_financ FROM  MV_PERCENTUAL_PLUS_VENDEDOR where NRO_VENDEDOR = " + nroVendedor + " and nro_loja= (select nro_loja from mv_loja where cod_nro_loja = " + codNroLoja + ")";
				
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			double percPlusVendFinan = 0;
			
			while (rs.next()) {
				percPlusVendFinan = rs.getDouble("percentual_plus_vend_financ");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return percPlusVendFinan;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	public void validarValoresCalculoReverso(String nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "select pp.vr_pedido_plano_reverso, " + 
				"       (pp.vr_pedido_plano_normal*pp.FATOR_PLANO_CALCULADO)/pp.FATOR_PLANO_REV_CALCULADO VR_REGRA_VALIDACAO " + 
				"  from MV_PEDIDO_PLANO_PAGTO pp where nro_pedido = " + nroPedido;
				
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			float vrPedidoPlanoReverso = 0;
			float vrRegraValidacao = 0;
			
			while (rs.next()) {
				vrPedidoPlanoReverso = Float.parseFloat(rs.getString("vr_pedido_plano_reverso").replace(",", "."));
				vrRegraValidacao = Float.parseFloat(rs.getString("vr_regra_validacao").replace(",", "."));
			}
			
			float vrDiferenca = vrRegraValidacao - vrPedidoPlanoReverso;

			System.out.println(vrRegraValidacao + " - " +  vrPedidoPlanoReverso + " = " + vrDiferenca);
			
			Assert.assertTrue("Calculo reverso estah incorreto.",  vrDiferenca < 0.01);
			
			stmt.close();
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	public double buscarFatorPlanoReversoCalculado(String nroLoja, String nroPedido) {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT FATOR_PLANO_REV_CALCULADO FROM MV_PEDIDO_PLANO_PAGTO WHERE NRO_LOJA = " + nroLoja + " AND nro_pedido = " + nroPedido;
				
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			double fatorPlanoRevCalculado = 0;
			
			while (rs.next()) {
				fatorPlanoRevCalculado = rs.getDouble("FATOR_PLANO_REV_CALCULADO");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return fatorPlanoRevCalculado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	public ItemPedidoProduto buscarValoresPrestacaoEntradaItemPedidoProduto(String nroLoja, String nroPedido, String codProduto) {
		connection = new Conexao().getConexao();
		
		String[] codigo = codProduto.split("[.]");
		
		System.out.println(codigo);
		
		String codNroProduto = codigo[0];
		String codCorProduto = codigo[1];
		String codTipoVoltagem = codigo[2];
				
		String sql = "SELECT  P.COD_NRO_PRODUTO || '.' || P.COD_COR_PRODUTO || '.' || P.COD_TIPO_VOLTAGEM AS COD_PRODUTO, IPP.NRO_PRODUTO, IPP.VR_PRESTACAO_PRODUTO, IPP.VR_ENTRADA_PRODUTO, IPP.VR_UNITARIO_PRODUTO " + 
					 "FROM    MV_ITEM_PEDIDO_PRODUTO IPP " + 
					 "        INNER JOIN MV_PRODUTO P ON P.NRO_PRODUTO = IPP.NRO_PRODUTO " + 
					 "WHERE   IPP.NRO_LOJA = " + nroLoja + " AND IPP.NRO_PEDIDO = " + nroPedido + " AND P.COD_NRO_PRODUTO = " + codNroProduto + " "
							+ "AND P.COD_COR_PRODUTO = " + codCorProduto + " AND P.COD_TIPO_VOLTAGEM = " + codTipoVoltagem;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			//List<ItemPedidoProduto> itensPedidoProduto = new ArrayList<ItemPedidoProduto>();
			
			ItemPedidoProduto itemPedidoProduto = new ItemPedidoProduto();
			
			while (rs.next()) {
				
				itemPedidoProduto.setVrUnitarioProduto(rs.getDouble("VR_UNITARIO_PRODUTO"));
				itemPedidoProduto.setCodProduto(rs.getString("COD_PRODUTO"));
				itemPedidoProduto.setNroProduto(rs.getInt("NRO_PRODUTO"));
				itemPedidoProduto.setVrPrestacaoProduto(rs.getDouble("VR_PRESTACAO_PRODUTO"));
				itemPedidoProduto.setVrEntradaProduto(rs.getDouble("VR_ENTRADA_PRODUTO"));
				
				//itensPedidoProduto.add(itemPedidoProduto);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return itemPedidoProduto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public ItemPedidoServico buscarValoresPrestacaoItemPedidoServico(String nroLoja, String nroPedido, String codGarantia) {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT NRO_SERVICO,VR_PRESTACAO_SERVICO,VR_UNITARIO_SERVICO FROM MV_ITEM_PEDIDO_SERVICO WHERE NRO_LOJA = " + nroLoja + " AND nro_pedido = " + nroPedido + " AND NRO_SERVICO = " + codGarantia;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			//List<ItemPedidoServico> itensPedidoServico = new ArrayList<ItemPedidoServico>();
			
			ItemPedidoServico itemPedidoServico = new ItemPedidoServico();
			
			while (rs.next()) {
				itemPedidoServico.setNroServico(rs.getInt("NRO_SERVICO"));
				itemPedidoServico.setVrPrestacaoServico(rs.getDouble("VR_PRESTACAO_SERVICO"));
				itemPedidoServico.setVrUnitarioServico(rs.getDouble("VR_UNITARIO_SERVICO"));
				
				//itensPedidoServico.add(itemPedidoServico);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return itemPedidoServico;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public ItemPedidoServico buscarValoresPrestacaoPedidoServico(String nroLoja, String nroPedido, String nroServico) {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT NRO_SERVICO,VR_UNITARIO_SERVICO,VR_PRESTACAO_SERVICO FROM MV_PEDIDO_SERVICO WHERE NRO_LOJA = " + nroLoja + " AND nro_pedido = " + nroPedido + " AND NRO_SERVICO = " + nroServico;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			//List<ItemPedidoServico> pedidoServicos = new ArrayList<ItemPedidoServico>();
			ItemPedidoServico pedidoServico = new ItemPedidoServico();
			
			while (rs.next()) {
				pedidoServico.setNroServico(rs.getInt("NRO_SERVICO"));
				pedidoServico.setVrPrestacaoServico(rs.getDouble("VR_PRESTACAO_SERVICO"));
				pedidoServico.setVrUnitarioServico(rs.getDouble("VR_UNITARIO_SERVICO"));
				
				//pedidoServicos.add(pedidoServico);
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return pedidoServico;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public double buscarTaxaPremioCobertura() {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT SUM (TAXA_PREMIO_COBERTURA) AS TAXA_PREMIO_COBERTURA FROM MV_COBERTURA_LOSANGO WHERE PLANO_MES_REF = 7";
				
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			double taxaPremioCobertura = 0;
			
			while (rs.next()) {
				taxaPremioCobertura = rs.getDouble("TAXA_PREMIO_COBERTURA");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return taxaPremioCobertura;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	public PlanoPagamento buscarFatoresPlanoPagamento(String nroLoja, String nroTipoVenda, String codTipoTabela, String plano) {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT FATOR_PLANO,R_COD_TIPO_TABELA FROM MV_PLANO_PAGAMENTO WHERE NRO_LOJA = " + nroLoja + " AND NRO_TIPO_VENDA = " + nroTipoVenda + " AND COD_TIPO_TABELA = '"+ codTipoTabela +"' AND PLANO = " + plano;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			PlanoPagamento planoPagamento = new PlanoPagamento();
			
			while (rs.next()) {
				planoPagamento.setFatorPlano(rs.getDouble("FATOR_PLANO"));
				planoPagamento.setRCodTipoTabela(rs.getString("R_COD_TIPO_TABELA"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return planoPagamento;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Pedido buscarPedidoPorLoja(String nroLoja, String nroPedido) {
		connection = new Conexao().getConexao();
		
		String sql = "select VR_ENTRADA, QTDE_PARCELAS, VR_PRESTACAO from mv_pedido where nro_pedido = " + nroPedido + " and nro_loja = " + nroLoja;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			Pedido pedido = new Pedido();
			
			while (rs.next()) {
				pedido.setVrEntrada(rs.getDouble("VR_ENTRADA"));
				pedido.setQtdeParcelas(rs.getInt("QTDE_PARCELAS"));
				pedido.setVrPrestacao(rs.getDouble("VR_PRESTACAO"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return pedido;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public double buscarFatorCDC(int qtdParcelas) {
		connection = new Conexao().getConexao();
		
		String sql = "SELECT SUM (TAXA_PREMIO_COBERTURA) as FATOR_CDC FROM MV_COBERTURA_LOSANGO WHERE PLANO_MES_REF = " + (qtdParcelas + 1);
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			double vrFatorCDC = 0;
			
			while (rs.next()) {
				vrFatorCDC = rs.getDouble("FATOR_CDC");
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return vrFatorCDC;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public void alterarDataFechamentoPedidoRegraDevolucaoParcial(int nroLoja, int nroPedido) {
		
		connection = new Conexao().getConexao();
		
		String sql = "update mv_pedido set dt_fechamento_pedido = sysdate-1 where nro_loja = ? and nro_pedido = ?";
		
		System.out.println(sql);
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, nroLoja);
			stmt.setInt(2, nroPedido);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
}