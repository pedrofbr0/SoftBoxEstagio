package br.com.caelum.jdbc.test;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaLista {
	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		
		List<Contato> contatos = dao.getList();
		
		for(Contato contato : contatos) {
			System.out.println("Nome: " + contato.getNome());
			System.out.println("Email: " + contato.getEmail());
			System.out.println("Endereço: " + contato.getEndereco());
			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Data de Nascimento: " +
					dataFormatada.format(contato.getDataNascimento().getTime())
					+ "\n");
		}
	}
}
