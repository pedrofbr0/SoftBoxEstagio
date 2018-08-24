package br.com.mv.PageFactory.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValidacaoUsuario {

	WebDriver driver;
	
	@FindBy(css = "#fm-validacao-pedido #usuario")
	private WebElement usuario;
	
	@FindBy(css = "#fm-validacao-pedido #senha")
	private WebElement senha;
	
	@FindBy(css = "#modal-validacao-usuario .modal-validar-privilegio-ok")
	private WebElement btnOk;
	
	public ValidacaoUsuario(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ValidacaoUsuario setUsuario(String usuario) {
		this.usuario.clear();
		this.usuario.sendKeys(usuario);
		return this;
	}
	
	public ValidacaoUsuario setSenha(String senha) {
		this.senha.clear();
		this.senha.sendKeys(senha);
		return this;
	}
	
	public ValidacaoUsuario clickBtnOk() {
		this.btnOk.click();
		return this;
	}
	
}
