package br.com.mv.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;

public class ExecucaoScripts {
	
	public static void main(String args[]) throws IOException {
		
 		String sql = ExecucaoScripts.lerArquivo("/br/com/mv/sql/RESET_PRODUTOS.sql");
		
		Connection connection = new Conexao().getConexao();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.executeQuery();
			stmt.close();
			connection.close();
			
			System.out.println("Reset de produtos feito com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String lerArquivo(String nomeArquivo) throws IOException {
		StringBuffer plSql = new StringBuffer();
		
		try {
			InputStream input = ExecucaoScripts.class.getResourceAsStream(nomeArquivo);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(input));

			String line;
			while ((line = br.readLine()) != null) {
				plSql.append(line).append("\n");
			}
			
			br.close();
		
			//System.out.println(plSql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plSql.toString();
	}
}