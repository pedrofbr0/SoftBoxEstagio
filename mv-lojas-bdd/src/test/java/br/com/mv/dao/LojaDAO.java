package br.com.mv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;
import br.com.mv.model.Loja;
import br.com.mv.model.Pedido;

public class LojaDAO {

	Connection connection;
	
	public Loja buscarLoja(int codNroLoja, int nroEmpresa) {
		connection = new Conexao().getConexao();
		
		String sql = "select nro_loja, desc_loja, nro_empresa, cod_nro_loja from mv_loja where cod_nro_loja = " + codNroLoja + " and nro_empresa = " + nroEmpresa;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			Loja loja = new Loja();
			
			while (rs.next()) {
				loja.setNroLoja(rs.getInt("nro_loja"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return loja;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
}