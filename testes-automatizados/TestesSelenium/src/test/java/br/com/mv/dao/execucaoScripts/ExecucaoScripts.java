package br.com.mv.dao.execucaoScripts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;

public class ExecucaoScripts {
	
	Connection connection;
	
	/*public static void main(String args[]) throws IOException {
		
		Connection connection = new Conexao().getConexao();
		
		String workspace = System.getenv("PATH_WORKSPACE");
		
		if (workspace == null) { // Para executar local via IDE
			workspace = "C:\\Users\\weverton\\testes-automatizados";
			//workspace = "/var/lib/jenkins/jobs/mv-troca-devolucao-TESTES/workspace/";
		}
		
		String sql = ""; //new Utils().lerArquivo(workspace + "/scripts_sql_reset_base_testes/RESET_PRODUTOS.sql");
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//stmt.executeQuery();
			stmt.close();
			connection.close();
			
			System.out.println("as");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
	}*/
	
	
	public static void main(String args[]) throws IOException {
		
		Conexao conexao = new Conexao();
		Connection connection = conexao.getConexao();
		
		String workspace = System.getenv("PATH_WORKSPACE");
		
		if (workspace == null) { // Para executar local via IDE
			workspace = "C:\\Users\\weverton\\testes-automatizados";
			//workspace = "/var/lib/jenkins/jobs/mv-troca-devolucao-TESTES/workspace/";
		}
		
		String sql = ExecucaoScripts.lerArquivo(workspace + "/scripts_sql_reset_base_testes/RESET_PRODUTOS.sql");
		
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
	
	public static String lerArquivo(String nomeArquivo) throws IOException {
		FileReader arq;
		StringBuffer plSql = new StringBuffer();
		
		try {
			arq = new FileReader(nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			
			String linha = null;
			
			while ((linha = lerArq.readLine()) != null) {
				plSql.append(linha);
				plSql.append(" ");
			}

			arq.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return plSql.toString();
	}
}