package br.com.mv.dao.faturamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mv.jdbc.Conexao;

public class ControleRotativoEstoqueDAO {

	private Connection connection;
	
	public void resetCRE(String sql) {
		connection = new Conexao().getConexao();
		
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
	
	public int getQtdeEstoqueSistema(int nroCre, int nroLoja, int nroProduto) {
		connection = new Conexao().getConexao();
		
		String sql =  "select qtde_estoque_sistema from mv_item_cre where nro_cre = " + nroCre + " and nro_loja = " + nroLoja + " and nro_produto = " + nroProduto;
		int qtdeEstoqueSistema = 0;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				qtdeEstoqueSistema = rs.getInt("qtde_estoque_sistema");
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			return qtdeEstoqueSistema;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<String> getDeptosCre(int nroCre, int nroLoja) {
		connection = new Conexao().getConexao();
		
		String sql = "select  md.nro_depto || ' - ' || md.desc_depto as desc_depto " + 
					 "from    mv_item_cre mic " +
					 "       inner join mv_depto md on md.nro_depto = mic.nro_depto " +
					 "where   mic.nro_cre = " + nroCre +
					 "       and mic.nro_loja = " + nroLoja + 
					 "group by md.nro_depto, md.desc_depto " +
					 "order by md.nro_depto asc";
		
		try {
			List<String> deptos = new ArrayList<String>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				deptos.add(rs.getString("desc_depto"));
			}
			
			return deptos;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}