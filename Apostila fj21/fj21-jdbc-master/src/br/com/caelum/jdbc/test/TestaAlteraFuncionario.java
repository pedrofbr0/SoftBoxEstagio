package br.com.caelum.jdbc.test;

import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaAlteraFuncionario {

	public static void main(String[] args) {
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.getFuncionarioById(1);
		
		funcionario.setSenha("123456");
		
		dao.altera(funcionario);
		
		System.out.println("Funcionario alterado!");

	}

}
