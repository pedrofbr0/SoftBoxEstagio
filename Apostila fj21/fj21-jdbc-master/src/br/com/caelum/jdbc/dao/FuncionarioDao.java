package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.exception.DAOException;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDao {
	private Connection connection;
	
	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Funcionario funcionario) {
		String sql = "insert into funcionarios "+
				"(nome, usuario, senha) "+
				"values(?,?,?)";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public void altera(Funcionario funcionario) {
		String sql = "update funcionarios "+
				"set nome=?, usuario=?, senha=? "+
				"where id=?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.setLong(4, funcionario.getId());
			
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public void remove(Funcionario funcionario) {
		String sql = "delete from funcionarios where id=? ";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setLong(1, funcionario.getId());
			
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public Funcionario getFuncionarioById(long id) {
		String sql = "select * from funcionarios where id=?";
				
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setLong(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setId(rs.getLong("id"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setUsuario(rs.getString("usuario"));
			funcionario.setSenha(rs.getString("senha"));
			
			rs.close();
			stmt.close();
			
			return funcionario;
			
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
}
