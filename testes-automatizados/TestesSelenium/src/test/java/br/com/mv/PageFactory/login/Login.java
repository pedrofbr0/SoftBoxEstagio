package br.com.mv.PageFactory.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Login
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class Login {

	WebDriver driver;
	
	@FindBy(id="j_loja")
	private WebElement lojaLogin;
	
	@FindBy(id="j_username_fake")
	private WebElement usuarioLogin;
	
	@FindBy(id="j_password")
	private WebElement senhaLogin;
	
	@FindBy(id="btnEntrar")
	private WebElement bntLogar;
	
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Login loja(String strLoja) {
		lojaLogin.clear();
		lojaLogin.sendKeys(strLoja);
		return this;
	}

	public Login usuario(String strUsuario) {
		usuarioLogin.clear();
		usuarioLogin.sendKeys(strUsuario);
		return this;
	}

	public Login senha(String strSenha) {
		senhaLogin.clear();
		senhaLogin.sendKeys(strSenha);
		return this;
	}
	
	public Login clickLogin() {
		bntLogar.click();
		return this;
	}
	
}