package br.com.caelum.jdbc.test;

import java.text.SimpleDateFormat;

import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaSelecionaFuncionarioPorId {

	public static void main(String[] args) {
		FuncionarioDao dao = new FuncionarioDao();

		Funcionario funcionario = dao.getFuncionarioById(2);
		
		System.out.println("Nome: " + funcionario.getNome());
		System.out.println("Usuario: " + funcionario.getUsuario());
		System.out.println("Senha: " + funcionario.getSenha());

	}

}
