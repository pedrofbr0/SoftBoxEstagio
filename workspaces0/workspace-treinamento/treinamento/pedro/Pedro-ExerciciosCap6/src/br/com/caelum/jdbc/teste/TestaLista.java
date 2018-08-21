package br.com.caelum.jdbc.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.modelo.Funcionario;

public class TestaLista {

	public static void main(String[] args) {
		
		ContatoDao dao = new ContatoDao();
		List<Contato> contatos = dao.getLista();
		
		for (Contato contato : contatos) {
	          System.out.println("Nome: " + contato.getNome());
	          System.out.println("Email: " + contato.getEmail());
	          System.out.println("Endereço: " + contato.getEndereco());
	          System.out.println("Data de Nascimento: " + 
	                  contato.getDataNascimento().getTime() + "\n");
	          String date = new SimpleDateFormat("dd/MM/yyyy").
	        		  format(contato.getDataNascimento().getTime());
	          System.out.println(date);
	    }
		
		Contato alo = dao.pesquisar(1);
		
		System.out.println("Id: " + alo.getId());
		System.out.println("Nome: " + alo.getNome());
        System.out.println("Email: " + alo.getEmail());
        System.out.println("Endereço: " + alo.getEndereco());
        System.out.println("Data de Nascimento: " + 
                alo.getDataNascimento().getTime());
        String date = new SimpleDateFormat("dd/MM/yyyy").format(alo.getDataNascimento().getTime());
        System.out.println("Data de Nascimento: " + date);
        
        dao.altera(dao.getLista().get(0));
        dao.remove(dao.getLista().get(0));
        	
	}
}
