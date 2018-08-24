package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Caixa {

	WebDriver driver;
	
	@FindBy(css = "#txtDescricao")
	private WebElement txtDescricao;
	
	@FindBy(xpath = ".//*[@id='searchLoja']/tbody/tr/td[1]/input")
	private WebElement codLoja;
	
	@FindBy(xpath = ".//*[@id='searchOperacaoContaCredito']/tbody/tr/td[2]/input")
	private WebElement pesquisarOperacaoContaCredito;
	
	@FindBy(xpath = ".//*[@id='searchOperacaoCaixaDebito']/tbody/tr/td[2]/input")
	private WebElement pesquisarOperacaoCaixaCredito;
	
	@FindBy(xpath = "//*[@id='searchLoja']/tbody/tr/td[3]/input")
	private WebElement searchLoja;
	
	public Caixa(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Caixa setDescricao(String descricao) {
		this.txtDescricao.clear();
		this.txtDescricao.sendKeys(descricao);
		return this;
	}
	
	public Caixa setCodLoja(String codLoja) {
		this.codLoja.clear();
		this.codLoja.sendKeys(codLoja);
		return this;
	}
	
	public void clickSearchLoja() {
		this.searchLoja.click();
	}
	
	public WebElement getBtnPesquisarOperacaoContaCredito() {
		return this.pesquisarOperacaoContaCredito;
	}
	
	public WebElement getBtnPesquisarOperacaoCaixaCredito() {
		return this.pesquisarOperacaoCaixaCredito;
	}
	
	public Caixa selecionarCaixa(String descricao, String descLoja) {
		driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr/td[contains(text(), '" + descricao + "')]/parent::tr/td[contains(text(), '" + descLoja + "')]/parent::tr/td/input")).click();
		return this;
	}
}