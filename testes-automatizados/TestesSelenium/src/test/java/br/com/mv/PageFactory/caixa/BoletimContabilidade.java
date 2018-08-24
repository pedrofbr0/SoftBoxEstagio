package br.com.mv.PageFactory.caixa;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.PageFactory.Toolbar;

public class BoletimContabilidade {

	WebDriver driver;
	
	@FindBy(css = "#txtNumeroControle")
	private WebElement txtNumeroControle;
	
	@FindBy(css = "#txtDataAbertura")
	private WebElement txtDataAbertura;
	
	@FindBy(css = "#txtDataFechamento")
	private WebElement txtDataFechamento;

	@FindBy(css = "#btnRelatorioBoletim")
	private WebElement btnRelatorioBoletim;
	
	public BoletimContabilidade(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public BoletimContabilidade setNumeroControle(String numeroControle) {
		this.txtNumeroControle.sendKeys(numeroControle);
		return this;
	}
	
	public BoletimContabilidade setDataAbertura(String dataAbertura) {
		this.txtDataAbertura.sendKeys(dataAbertura);
		return this;
	}
	
	public BoletimContabilidade setDataFechamento(String dataFechamento) {
		this.txtDataFechamento.sendKeys(dataFechamento);
		return this;
	}
	
	public BoletimContabilidade selecionarControleCaixa(String numeroControle) {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + numeroControle + "']")).click();
		return this;
	}
	
	public BoletimContabilidade clickEmitirRelatorio() {
		this.btnRelatorioBoletim.click();
		return this;
	}
	
	public WebElement getBtnEmitirRelatorio() {
		return this.btnRelatorioBoletim;
	}
}