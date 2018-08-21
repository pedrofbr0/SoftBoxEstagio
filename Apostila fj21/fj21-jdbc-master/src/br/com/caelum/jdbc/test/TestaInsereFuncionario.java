package br.com.caelum.jdbc.test;

import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaInsereFuncionario {

	public static void main(String[] args) {
		FuncionarioDao dao = new FuncionarioDao();
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Roberval Jacinto P.");
		funcionario.setUsuario("rjacindop");
		funcionario.setSenha("noRego");
		
		dao.adiciona(funcionario);
		
		System.out.println("Funcionario gravado");

	}

}
