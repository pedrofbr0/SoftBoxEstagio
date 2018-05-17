package br.com.caelum.produtos.controller;

//import javax.servlet.jsp.tagext.ValidationMessage;

import br.com.caelum.produtos.component.UsuarioLogado;
import br.com.caelum.produtos.dao.UsuarioDao;
import br.com.caelum.produtos.modelo.Usuario;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class LoginController {
	private UsuarioDao usuarioDao;
	private UsuarioLogado usuarioLogado;
	private Result result;

	public LoginController(UsuarioDao usuarioDao, UsuarioLogado usuarioLogado, Result result) {
		this.usuarioDao = usuarioDao;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
	}

	public void autentica(Usuario usuario) {
		Usuario autenticado = usuarioDao.buscaUsuarioPorLoginESenha(usuario);
		if (autenticado != null) {
			usuarioLogado.efetuaLogin(autenticado);
			result.redirectTo(ProdutoController.class).lista();
			return;
		}
//		validator.add(new ValidationMessage("Usuario ou senha incorretos", "Erro de Autenticação "));
//		validator.onErrorRedirectTo(ProdutoController.class).formulario();
		result.redirectTo(LoginController.class).formulario();
	}

	public void formulario() {
	}

	public void logout() {
		usuarioLogado.efetuaLogout();
		result.redirectTo(LoginController.class).formulario();
	}
}
