package br.com.mv.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexao
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class Conexao {
	
	public Connection getConexao() {
		try {
			//return DriverManager.getConnection("jdbc:oracle:thin:@192.168.100.18:1521:RELOH", "mv","mv");
			//return DriverManager.getConnection("jdbc:oracle:thin:@exa01.maquinadevendas.corp:1521/RELOH", "mv", "mv");
			return DriverManager.getConnection("jdbc:oracle:thin:@exa01.maquinadevendas.corp:1521/RELOH", "mv", "mv");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}