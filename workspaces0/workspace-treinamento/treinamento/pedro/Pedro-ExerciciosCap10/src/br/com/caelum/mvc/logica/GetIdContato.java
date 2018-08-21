package br.com.caelum.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class GetIdContato implements Logica {
	public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
		
		int id = Integer.parseInt(req.getParameter("id"));

        // Pesquisa o contato
        Contato contato = new ContatoDao().pesquisar(id);

        req.setAttribute("contato", contato);
        
        if(id > 0) {
        	return "/WEB-INF/jsp/altera-contato.jsp";
        } else {
        	return "/WEB-INF/jsp/adiciona-contato.jsp";
        }
        
	}
}
