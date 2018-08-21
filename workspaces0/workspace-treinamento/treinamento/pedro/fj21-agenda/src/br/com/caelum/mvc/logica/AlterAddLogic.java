package br.com.caelum.mvc.logica;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class AlterAddLogic implements Logica{
	
	public String executa(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
		
		int id = Integer.parseInt(req.getParameter("id"));

        String nome = req.getParameter("nome");
        String endereco = req.getParameter("endereco");
        String email = req.getParameter("email");
        String dataEmTexto = req
                .getParameter("dataNascimento");
        Calendar dataNascimento = null;

        // fazendo a conversão da data
        try {
            Date date =
                    new SimpleDateFormat("dd/MM/yyyy")
                    .parse(dataEmTexto);
            dataNascimento = Calendar.getInstance();
            dataNascimento.setTime(date);
        } catch (ParseException e) {
            return dataEmTexto; //para a execução do método
        }

        // monta um objeto contato
        Contato contato = new Contato();
        
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        contato.setDataNascimento(dataNascimento);
        
        ContatoDao dao = new ContatoDao();
        
        if(id > 0) {
        	contato.setId(Long.parseLong(req.getParameter("id")));
            dao.altera(contato);
        } else {
            dao.adiciona(contato);
        }

	return "mvc?logica=ListaContatosLogic";

	}
}