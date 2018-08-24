package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupConferenciaMovimentacaoProduto {

	WebDriver driver;
	
	@FindBy(css = "#searchLoja .inputText")
	private WebElement codNroLoja;
	
	@FindBy(css = "#btnImprimir")
	private WebElement btnImprimir;
	
	@FindBy(xpath = "//*[@id='searchLoja']/tbody/tr/td[1]/input")
	private WebElement searchLoja;
	
	public PopupConferenciaMovimentacaoProduto(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickIconeSearchLoja() {
		this.searchLoja.click();
	}
	
	public PopupConferenciaMovimentacaoProduto setCodNroLoja(String codNroLoja) {
		this.codNroLoja.clear();
		this.codNroLoja.sendKeys(codNroLoja);
		return this;
	}
	
	public WebElement getBtnImprimir() {
		return btnImprimir;
	}
	
	public PopupConferenciaMovimentacaoProduto clickBtnImprimir() {
		this.btnImprimir.click();
		return this;
	}
}