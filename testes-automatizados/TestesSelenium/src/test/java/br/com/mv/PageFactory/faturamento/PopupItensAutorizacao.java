package br.com.mv.PageFactory.faturamento;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupItensAutorizacao {

	WebDriver driver;
	
	@FindBy(css = "input[id^='txtQtdSolicitada'")
	public WebElement txtQtdSolicitada;
	
	@FindBy(css = "input[id^='txtQtdAutorizada'")
	public WebElement txtQtdAutorizada;
	
	@FindBy(css = "#btnSair")
	private WebElement btnSair;
	
	@FindBy(css = "#btnInserir")
	private WebElement btnInserir;
	
	@FindBy(css = "#btnSalvar")
	private WebElement btnSalvar;
	
	public PopupItensAutorizacao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public WebElement getBtnInserir() {
		return btnInserir;
	}
	
	public PopupItensAutorizacao clickBtnInserir() {
		this.btnInserir.click();
		return this;
	}
	
	public WebElement getBtnSair() {
		return btnSair;
	}
	
	public PopupItensAutorizacao clickBtnSair() {
		this.btnSair.click();
		return this;
	}
	
	public WebElement getBtnSalvar() {
		return btnSalvar;
	}
	
	public PopupItensAutorizacao clickBtnSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public PopupItensAutorizacao setQtdSolicitada(String qtdSolicitada) {
		this.txtQtdSolicitada.clear();
		this.txtQtdSolicitada.sendKeys(qtdSolicitada);
		return this;
	}
	
	public PopupItensAutorizacao setQtdAutorizada(String qtdAutorizada) {
		this.txtQtdAutorizada.clear();
		this.txtQtdAutorizada.sendKeys(qtdAutorizada);
		return this;
	}
}