package br.com.mv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.thucydides.core.pages.PageObject;

public class PopupConsultaNotaFiscalTransferenciaPage extends PageObject {

	@FindBy(id = "txtNumeroNf")
	private WebElement numeroNf;
	
	@FindBy(id = "txtLojaDestino")
	private WebElement lojaDestino;
	
	@FindBy(css = "#toolbarvazia input[id='toolbarvazia:btnSearch']")
	private WebElement btnSearchNota;
	
	@FindBy(id = "btnSelecionar")
	private WebElement btnSelecionar;
	
	
	public PopupConsultaNotaFiscalTransferenciaPage() {
	}
	
	public PopupConsultaNotaFiscalTransferenciaPage setNumeroNf(String numeroNf) {
		this.numeroNf.clear();
		this.numeroNf.sendKeys(numeroNf);
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferenciaPage limparLojaDestino() {
		this.lojaDestino.clear();
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferenciaPage clickBtnSearchNota() {
		this.btnSearchNota.click();
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferenciaPage clickBtnSelecionar() {
		this.btnSelecionar.click();
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferenciaPage selecionarNFe() throws InterruptedException {
		$("//table[@id='table:tContent']/tbody/tr[1]/td[1]/input").click();
		return this;
	}
}
