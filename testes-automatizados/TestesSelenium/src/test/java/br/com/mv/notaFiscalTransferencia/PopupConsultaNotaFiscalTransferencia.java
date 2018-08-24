package br.com.mv.notaFiscalTransferencia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.PageFactory.cargaVeiculo.CargaVeiculo;
import br.com.mv.PageFactory.cargaVeiculo.PopupVeiculo;

public class PopupConsultaNotaFiscalTransferencia {

private WebDriver driver;
	
	@FindBy(id = "txtNumeroNf")
	private WebElement numeroNf;
	
	@FindBy(id = "txtLojaDestino")
	private WebElement lojaDestino;
	
	@FindBy(css = "#toolbarvazia input[id='toolbarvazia:btnSearch']")
	private WebElement btnSearchNota;
	
	@FindBy(id = "btnSelecionar")
	private WebElement btnSelecionar;
	
	
	public PopupConsultaNotaFiscalTransferencia(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupConsultaNotaFiscalTransferencia setNumeroNf(String numeroNf) {
		this.numeroNf.clear();
		this.numeroNf.sendKeys(numeroNf);
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferencia limparLojaDestino() {
		this.lojaDestino.clear();
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferencia clickBtnSearchNota() {
		this.btnSearchNota.click();
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferencia clickBtnSelecionar() {
		this.btnSelecionar.click();
		return this;
	}
	
	public PopupConsultaNotaFiscalTransferencia selecionarNFe() throws InterruptedException {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr[1]/td[1]/input")).click();
		return this;
	}
}
