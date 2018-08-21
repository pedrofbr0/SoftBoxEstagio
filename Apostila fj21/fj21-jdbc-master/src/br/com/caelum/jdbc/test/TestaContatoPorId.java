package br.com.caelum.jdbc.test;

import java.text.SimpleDateFormat;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaContatoPorId {
	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();

		Contato contato = dao.getContatoById(4);
		
		System.out.println("Nome: " + contato.getNome());
		System.out.println("Email: " + contato.getEmail());
		System.out.println("Endereço: " + contato.getEndereco());
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Data de Nascimento: " +
				dataFormatada.format(contato.getDataNascimento().getTime())
				+ "\n");				
	}
}