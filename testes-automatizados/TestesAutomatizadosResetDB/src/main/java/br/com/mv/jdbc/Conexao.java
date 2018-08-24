package br.com.mv.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection getConexao() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@exa01.maquinadevendas.corp:1521/RELOH", "mv", "mv");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
