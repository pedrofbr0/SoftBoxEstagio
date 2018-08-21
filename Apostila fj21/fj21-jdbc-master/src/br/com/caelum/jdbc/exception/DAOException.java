package br.com.caelum.jdbc.exception;

import java.sql.SQLException;

public class DAOException extends RuntimeException{
	static final long serialVersionUID = -2345678190745766939L;
	
	public DAOException(SQLException e){
		super(e);
	}
}
