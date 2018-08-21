package br.com.mv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;
import br.com.mv.model.Bordero;

public class ConfirmacaoDepositoDAO {

	Connection connection;
	
	public Bordero getBordero(String nroBordero, String codNroLoja) {
		
		connection = new Conexao().getConexao();
		
		String sql = "select nro_bordero, to_char(dt_geracao_bordero, 'dd/mm/yyyy') as dt_geracao_bordero from MV_BORDERO where nro_bordero = " + nroBordero + " and nro_loja = (select nro_loja from mv_loja where cod_nro_loja = " + codNroLoja + ")"; // + nroLoja;
		
		System.out.println(sql);
		try {
			Bordero bordero = new Bordero();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				bordero.setDtGeracaoBordero(rs.getString("dt_geracao_bordero"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return bordero;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public Boolean validarCodBanco(String nroBordero, String codNroLoja, String codBanco) {
		
		connection = new Conexao().getConexao();
		
		String sql = "select cod_banco from MV_CONFIRMACAO_DEPOSITO where nro_bordero = " + nroBordero + " and nro_loja = (select nro_loja from mv_loja where cod_nro_loja = " + codNroLoja + ") and cod_banco = " + codBanco;
		
		System.out.println(sql);
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			int codigoBanco = 0;
			while (rs.next()) {
				codigoBanco = rs.getInt("cod_banco");
			}
			
			Boolean bancoValidado = false;
			if (codigoBanco == Integer.parseInt(codBanco)) {
				bancoValidado = true;
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return bancoValidado;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
