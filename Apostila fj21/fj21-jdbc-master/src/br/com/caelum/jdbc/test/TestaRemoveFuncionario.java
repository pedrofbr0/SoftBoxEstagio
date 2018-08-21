package br.com.caelum.jdbc.test;

import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaRemoveFuncionario {

	public static void main(String[] args) {
		FuncionarioDao dao = new FuncionarioDao();
		Funcionario funcionario = dao.getFuncionarioById(2);
		
		dao.remove(funcionario);
		
		System.out.println("Funcionario removido");

	}

}
