package br.com.mv.dao.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;

public class ConsultaPreSimplificadaDAO {

	Connection connection;
	
	public void limparRetornoConsultaSimplificada(String cpf) {
		
		connection = new Conexao().getConexao();
		
		String sql = "delete from MV_RETORNO_CONS_PRE_SIMPLIF where CPF = ?";
		String sql1 = "delete from MV_FILA_CONSULTA_SIMPLIFICADA where CPF = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, cpf);
			stmt.execute();
			stmt.close();
			
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			
			stmt1.setString(1, cpf);
			stmt1.execute();
			stmt1.close();
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}