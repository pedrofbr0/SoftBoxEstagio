package br.com.mv.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;

public class UsuarioDAO {

	Connection connection = new Conexao().getConexao();
	
	public boolean existePrivilegioUsuario(int nroUsuario, int nroMotivo) {
		
		String sql = " SELECT   U.NRO_USUARIO " +
					 " FROM     MV_USUARIO U " +
					 "			INNER JOIN MV_USUARIO_X_PRIVILEGIO UP ON up.NRO_USUARIO = U.NRO_USUARIO " +
					 " 			INNER JOIN MV_FLUXO_APROVACAO_TROCADEVOL FAT ON FAT.COD_PRIVILEGIO_ADM_APROVADOR = UP.COD_PRIVILEGIO " +
					 " 			INNER JOIN MV_ITEM_FLUXO_APROVACAO IFA ON IFA.COD_PRIVILEGIO = FAT.COD_PRIVILEGIO_ADM_APROVADOR " +
					 " 			INNER JOIN MV_CONFIG_APROVACAO_TROCADEVOL CAT ON CAT.NRO_FLUXO_APROVACAO_TROCADEVOL = FAT.NRO_FLUXO_APROVACAO_TROCADEVOL " +
					 " WHERE    U.NRO_USUARIO = " + nroUsuario + 
					 " 			AND CAT.NRO_MOTIVO_FLUXO_APROVACAO = " + nroMotivo;
				
		
		try {
			boolean existe = false;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				existe = true;
			}
			
			return existe;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
