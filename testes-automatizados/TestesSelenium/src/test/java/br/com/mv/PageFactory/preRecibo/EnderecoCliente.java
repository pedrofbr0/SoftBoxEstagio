package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class EnderecoCliente {
	
	WebDriver driver;
	
	@FindBy(id = "btn-confirmar")
	private WebElement btnConfirmar;
	
	@FindBy(css = "input[name='endereco'][value='1']")
	private WebElement endereco;
			
	public EnderecoCliente(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public EnderecoCliente clickConfirmar() {
		this.btnConfirmar.click();
		return this;
	}
}