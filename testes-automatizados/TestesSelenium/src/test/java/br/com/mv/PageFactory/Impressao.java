package br.com.mv.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Impressao {

	WebDriver driver;
	
	@FindBy(css = "#btnImprimir")
	private WebElement btnImprimir;
	
	@FindBy(css = "#btnFecharImpressao")
	private WebElement btnFecharImpressao;
	
	@FindBy(css = "form input[value='Fechar']")
	private WebElement btnFecharRelatorio;
	
	public Impressao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Impressao clickBtnImprimir() {
		this.btnImprimir.click();
		return this;
	}
	
	public WebElement getBtnImprimir() {
		return btnImprimir;
	}
	
	public Impressao clickBtnFecharImpressao() {
		this.btnFecharImpressao.click();
		return this;
	}
	
	public WebElement getBtnFecharImpressao() {
		return this.btnFecharImpressao;
	}
	
	public Impressao clickBtnFecharRelatorio() {
		this.btnFecharRelatorio.click();
		return this;
	}
	
	public WebElement getBtnFecharRelatorio() {
		return this.btnFecharRelatorio;
	}
}