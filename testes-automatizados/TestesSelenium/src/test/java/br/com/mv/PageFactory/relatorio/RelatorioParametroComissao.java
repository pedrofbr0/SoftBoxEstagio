package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RelatorioParametroComissao {

	WebDriver driver;
	
	@FindBy(css = "#searchLoja .inputText")
	private WebElement codNroLoja;
	
	@FindBy(css = "#comboDepartamentos")
	private WebElement comboDepartamentos;
	
	@FindBy(xpath = "//*[@id='searchLoja']/tbody/tr/td[3]/input")
	private WebElement searchLoja;
	
	@FindBy(css = "#btnImprimir")
	private WebElement btnImprimir;
	
	public RelatorioParametroComissao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickIconeSearchLoja() {
		this.searchLoja.click();
	}
	
	public RelatorioParametroComissao setCodNroLoja(String codNroLoja) {
		this.codNroLoja.clear();
		this.codNroLoja.sendKeys(codNroLoja);
		return this;
	}
	
	public RelatorioParametroComissao setComboDepartamentos(String departamento) {
		new Select(this.comboDepartamentos).selectByVisibleText(departamento);
		return this;
	}
	
	public WebElement getBtnImprimir() {
		return btnImprimir;
	}
	
	public RelatorioParametroComissao clickBtnImprimir() {
		this.btnImprimir.click();
		return this;
	}
}