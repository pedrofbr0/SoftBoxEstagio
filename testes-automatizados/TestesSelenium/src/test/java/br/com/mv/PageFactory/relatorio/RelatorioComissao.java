package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RelatorioComissao {

	WebDriver driver;
	
	@FindBy(css = "#searchComissaoControle .buttonSearchField")
	private WebElement btnSearchReferencia;
	
	@FindBy(css = "#searchLoja .inputText")
	private WebElement codNroLoja;
	
	@FindBy(xpath = "//*[@id='searchLoja']/tbody/tr/td[3]/input")
	private WebElement searchLoja;
	
	@FindBy(css = "#btnImprimir")
	private WebElement btnImprimir;
	
	public RelatorioComissao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickIconeSearchLoja() {
		this.searchLoja.click();
	}
	
	public WebElement getBtnSearchReferencia() {
		return btnSearchReferencia;
	}
	
	public RelatorioComissao setCodNroLoja(String codNroLoja) {
		this.codNroLoja.clear();
		this.codNroLoja.sendKeys(codNroLoja);
		return this;
	}
	
	public RelatorioComissao clickBtnImprimir() {
		this.btnImprimir.click();
		return this;
	}
	
	public WebElement getBtnImprimir() {
		return btnImprimir;
	}
}