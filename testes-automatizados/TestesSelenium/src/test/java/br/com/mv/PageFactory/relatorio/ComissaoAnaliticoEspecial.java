package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComissaoAnaliticoEspecial {
	
	WebDriver driver;
	
	@FindBy(xpath = ".//*[@id='sf-referencia']/div/button[1]")
	private WebElement btnPesquisarReferencia;
	
	@FindBy(css = "#btn-imprimir")
	private WebElement btnImprimir;
	
	@FindBy(xpath = ".//*[@id='sf-funcionario']/input[1]")
	private WebElement codFuncionario;
	
	public ComissaoAnaliticoEspecial(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ComissaoAnaliticoEspecial clickPesquisarReferencia() {
		this.btnPesquisarReferencia.click();
		return this;
	}
	
	public ComissaoAnaliticoEspecial setCodFuncionario(String codFuncionario) {
		this.codFuncionario.clear();
		this.codFuncionario.sendKeys(codFuncionario + Keys.TAB);
		return this;
	}
	
	public ComissaoAnaliticoEspecial clickBtnImprimir() {
		this.btnImprimir.click();
		return this;
	}
	
	public WebElement getBtnImprimir() {
		return btnImprimir;
	}
}