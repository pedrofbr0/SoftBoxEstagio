package br.com.mv.PageFactory.faturamento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Autorizacao {

	WebDriver driver;
	
	@FindBy(css = "#txtCodigo")
	private WebElement txtCodigo;
	
	@FindBy(css = "#cbxTipoAutorizacao")
	private WebElement cbxTipoAutorizacao;
	
	@FindBy(xpath = ".//*[@id='sfLojaDestino']/tbody/tr/td[1]/input")
	private WebElement codNroLojaDestino;
	
	@FindBy(css = "#txtObsUsrSolicitante")
	private WebElement txtObsUsrSolicitante;
	
	@FindBy(css = "#cbxTipoNotaFiscal")
	private WebElement cbxTipoNotaFiscal;
	
	@FindBy(css = "#cbxStatusAutorizacao")
	private WebElement cbxStatusAutorizacao;
	
	@FindBy(css = "#btnItens")
	private WebElement btnItens;
	
	@FindBy(css = "#btnProcessa")
	private WebElement btnProcessa;
	
	@FindBy(css = "#btnSair")
	private WebElement btnSair;
	
	@FindBy(css = "#btnInserir")
	private WebElement btnInserir;
	
	@FindBy(css = "#btnSalvar")
	private WebElement btnSalvar;
	
	public Autorizacao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Autorizacao setCodigo(String codigo) {
		this.txtCodigo.sendKeys(codigo);
		return this;
	}
	
	public Autorizacao setTipoAutorizacao(String tipoAutorizacao) {
		new Select(this.cbxTipoAutorizacao).selectByVisibleText(tipoAutorizacao);
		return this;
	}
	
	public Autorizacao setObservacao(String observacao) {
		this.txtObsUsrSolicitante.sendKeys(observacao);
		return this;
	}
	
	public Autorizacao setLojaDestino(String codNroLojaDestino) {
		this.codNroLojaDestino.clear();
		this.codNroLojaDestino.sendKeys(codNroLojaDestino);
		return this;
	}
	
	public Autorizacao setTipoNotaFiscal(String tipoNotaFiscal) {
		new Select(this.cbxTipoNotaFiscal).selectByVisibleText(tipoNotaFiscal);
		return this;
	}
	
	public Autorizacao setStatusAutorizacao(String statusAutorizacao) {
		new Select(this.cbxStatusAutorizacao).selectByVisibleText(statusAutorizacao);
		return this;
	}
	
	public WebElement getBtnItens() {
		return this.btnItens;
	}
	
	public Autorizacao clickBtnItens() {
		this.btnItens.click();
		return this;
	}
	
	public WebElement getBtnProcessar() {
		return btnProcessa;
	}
	
	public Autorizacao clickBtnProcessar() {
		this.btnProcessa.click();
		return this;
	}
	
	public String getCodigo() {
		//String codigo = this.txtCodigo.getText();
		
		String codigo = driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr/td[2]")).getText();
		return codigo;
	}
	
	public Autorizacao selecionarAutorizacao(String codAutorizacao) {
		driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr/td[contains(text(), '" + codAutorizacao + "')]")).click();
		return this;
	}
}