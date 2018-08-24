package br.com.mv.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EfetuaLogin {
	
	private WebDriver driver;
	
	@FindBy(how = How.ID, using = "j_loja")
	private WebElement loja;

	@FindBy(how = How.ID, using = "j_username_fake")
	private WebElement usuario;
	
	@FindBy(how = How.ID, using = "j_password")
	private WebElement senha;
	
	@FindBy(how = How.ID, using = "btnEntrar")
	private WebElement entrar;
	
	public EfetuaLogin(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void inserirLoja(String vLoja) {
		loja.clear();
		loja.sendKeys(vLoja);
	}
	
	public void inserirUsuario(String vUsuario) {
		usuario.clear();
		usuario.sendKeys(vUsuario);
	}
	
	public void inserirSenha(String vSenha) {
		senha.clear();
		senha.sendKeys(vSenha);
	}
	
	public void clicarEntrar() {
		entrar.click();
	}
	
}