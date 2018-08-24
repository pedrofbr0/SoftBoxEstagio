package br.com.mv.PageFactory.faturamento;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupConsultaCrePendente {

	WebDriver driver;
	
	@FindBy(css = "#cbxQtdeRegistros")
	private WebElement cbxQtdeRegistros;
	
	@FindBy(css = "#btnSelecionar")
	private WebElement btnSelecionar;
	
	@FindBy(xpath = ".//*[@id='table:tContent']/tbody/tr/td[1]/input")
	private WebElement radioCrePendente;
	
	public PopupConsultaCrePendente(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupConsultaCrePendente setQtdeRegistros(String qtdeRegistros) {
		new Select(this.cbxQtdeRegistros).selectByVisibleText(qtdeRegistros);
		return this;
	}
	
	public PopupConsultaCrePendente selecionarCrePendente() {
		this.radioCrePendente.click();
		return this;
	}
	
	public WebElement getBtnSelecionar() {
		return btnSelecionar;
	}
	
	public PopupConsultaCrePendente clickBtnSelecionar() {
		this.btnSelecionar.click();
		return this;
	}
}