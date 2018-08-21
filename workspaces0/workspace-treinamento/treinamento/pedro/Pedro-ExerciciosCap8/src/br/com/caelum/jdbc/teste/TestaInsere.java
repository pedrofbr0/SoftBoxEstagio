package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaInsere {

	public static void main(String[] args) {
		// pronto para gravar
	       Contato contato = new Contato();
	       contato.setNome("Caelum");
	       contato.setEmail("contato@caelum.com.br");
	       contato.setEndereco("R. Vergueiro 3185 cj57");
	       contato.setDataNascimento(Calendar.getInstance());

	       // grave nessa conexão!!!
	       ContatoDao dao = new ContatoDao();

	       // método elegante
	       dao.adiciona(contato);
	       
	       Funcionario funcionario = new Funcionario();
	       funcionario.setNome("eu");
	       funcionario.setUsuario("eu@nois");
	       funcionario.setSenha("123");
	       
	       
	       
	       FuncionarioDao daoFun = new FuncionarioDao();
	       daoFun.adiciona(funcionario);

	       System.out.println("Gravado!");
	}
}

