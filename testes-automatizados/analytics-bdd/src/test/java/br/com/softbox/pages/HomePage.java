package br.com.softbox.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.thucydides.core.pages.PageObject;

public class HomePage extends PageObject {

	public void login(String campoUsuario, String usuario, String campoSenha, String senha, String botaoLogin) throws InterruptedException {
		Thread.sleep(2000);
		$(campoUsuario).sendKeys(usuario);
		$(campoSenha).sendKeys(senha);
		$(botaoLogin).click();
	}
	
	public void preecherCampo(String campo, String valor) throws InterruptedException {
		Thread.sleep(1000);
		$(campo).sendKeys(valor);
	}
	
	 public void clicarBotaoLogin(String botaoLogin) {
		 $(botaoLogin).click();
	 }
	
	public void verificarStatusAplicacao() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 20);
		
		wait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return document.readyState"));
		
		String status = (String)((JavascriptExecutor)this.getDriver()).executeScript("return document.readyState");
		
		System.out.println("Status: " + status);
		
		System.out.println("Título: " + this.getDriver().getTitle());
		
		Assert.assertFalse("ERRO 404", this.getDriver().getTitle().contains("404"));
	}
	
	public void verificarPresencaElemento(String elementoTelaInicial) {
		Assert.assertTrue("Elemento " + elementoTelaInicial+ " nao foi encontrado na tela inicial.", $(elementoTelaInicial).isVisible());
	}
}