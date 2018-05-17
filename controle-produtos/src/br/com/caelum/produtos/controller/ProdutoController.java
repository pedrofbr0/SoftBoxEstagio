package br.com.caelum.produtos.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.produtos.component.UsuarioLogado;
import br.com.caelum.produtos.dao.ProdutoDao;
import br.com.caelum.produtos.modelo.Produto;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {
	
    private Result result;
    private ProdutoDao produtoDao;
    private Validator validator;
    private UsuarioLogado usuarioLogado;
    private String permissao;

    public ProdutoController(Result result, ProdutoDao produtoDao, Validator validator, UsuarioLogado usuarioLogado) {
        this.result = result;
        this.produtoDao = produtoDao;
        this.validator = validator;
        this.usuarioLogado = usuarioLogado;
    }
    
    public List<Produto> lista() {
    	
    	permissao=usuarioLogado.getUsuario().getPermissao();
    	return produtoDao.lista(usuarioLogado);
//    	return produtoDao.lista();
    }
    
    
    //método para fazer a listagem

    public void adiciona(Produto produto) {
    	
    	permissao=usuarioLogado.getUsuario().getPermissao();
    	
//    	if (!permissao.equals(produto.getAcesso())) {
//    		validator.add(new ValidationMessage("Você não tem a permissão requisitada", "Permissão"));
//    	}
//    	
//    	if (produto.getNome() == null || produto.getNome().length() < 3) {
//    		validator.add(new ValidationMessage("Inválido: mínimo 3 letras",
//    		        "Nome"));
//    	}
//    	
//    	if (produto.getDescricao() == null || produto.getDescricao().length() < 3) {
//    		validator.add(new ValidationMessage("Inválida: mínimo 3 letras",
//    		        "Descricao"));
//    	}
//    	
//    	if (produto.getDataInicioVenda() == null) {
//    		validator.add(new ValidationMessage("Inválida",
//    		        "Data"));
//    	}
//    	
//    	String pattern = "^\\d+([.,]\\d{1,2})?$";
//    	Pattern p = Pattern.compile(pattern);
//    	Matcher m = p.matcher(produto.getPreco());
//    	
//    	if (!m.find() || Double.parseDouble(produto.getPreco()) < 0) {
//    		validator.add(new ValidationMessage("Inválido",
//    		        "Preço"));
//    	}
//    	
//    	validator.onErrorRedirectTo(ProdutoController.class).lista();
//    	validator.onErrorUsePageOf(ProdutoController.class).lista();

        produtoDao.adiciona(produto);
        result.redirectTo(ProdutoController.class).lista();
    }
    
    public void remove(Produto produto) {
    	
    	if (permissao != "g") {
    		validator.add(new ValidationMessage("Você não tem a permissão requisitada", ""));
    	}
    	
    	produtoDao.remove(produto);
//        result.redirectTo(ProdutoController.class).lista();
    }
    
    public void formulario() {
    }

}
