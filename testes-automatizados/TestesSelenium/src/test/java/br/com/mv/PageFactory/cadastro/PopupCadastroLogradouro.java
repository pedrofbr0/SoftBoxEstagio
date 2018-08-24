package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupCadastroLogradouro {

	WebDriver driver;
	
	@FindBy(css = "#txtCidade")
	private WebElement cidade;
	
	@FindBy(css = "#txtLogradouro")
	private WebElement logradouro;
	
	public PopupCadastroLogradouro(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupCadastroLogradouro setCidade(String cidade) {
		this.cidade.clear();
		this.cidade.sendKeys(cidade);
		return this;
	}
	
	public PopupCadastroLogradouro setLogradouro(String logradouro) {
		this.logradouro.clear();
		this.logradouro.sendKeys(logradouro);
		return this;
	}
	
	public WebElement selecionarLogradouro() {
		WebElement logradouro = driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]/input"));
		return logradouro;
	}
}