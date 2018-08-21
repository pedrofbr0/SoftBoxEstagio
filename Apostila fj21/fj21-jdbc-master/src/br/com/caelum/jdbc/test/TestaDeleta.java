package br.com.caelum.jdbc.test;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaDeleta {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		
		Contato contato = dao.getContatoById(8);
		
		dao.remove(contato);
	}

}
