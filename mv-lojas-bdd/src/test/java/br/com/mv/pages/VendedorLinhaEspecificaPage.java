package br.com.mv.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.Utils;
import net.serenitybdd.core.pages.PageObject;

public class VendedorLinhaEspecificaPage extends PageObject {

	
	/*
	 * Tela Departamentos Associáveis
	 */

	@FindBy(xpath = "/html/body/div[2]/div/div[3]/div/p[1]/input")
	private WebElement inputDepartamento;
	
	
	
	public void informarDepartamento(String dep) {
		
		this.inputDepartamento.clear();
		this.inputDepartamento.sendKeys(dep);
		
	}
	
	public void validarMensagemDeXDoTipoY(String msg, String tipo) throws InterruptedException {
		Thread.sleep(2000);
		
		if(tipo.equals("popup")) {
			
			List<WebElement> modais = this.getDriver().findElements(By.cssSelector("div[id^='mdl'].modal.in .modal-body"));
			System.out.println( modais.get(1).getText().trim());
		}

		
		if(tipo.equals("alert")) {
			Assert.assertTrue("Erro ao salvar", $(".alert").containsText(msg));
		}
	}
	
	public void numSei() {
		System.out.println("Num Sei");
	}
	
	
	
	
	
	
	
	
	
	

}
