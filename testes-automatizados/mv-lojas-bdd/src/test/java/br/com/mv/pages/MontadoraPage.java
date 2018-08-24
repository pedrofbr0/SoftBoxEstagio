package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;

public class MontadoraPage extends PageObject{

	@FindBy(xpath = ".//*[@id='searchMontadora']/tbody/tr/td[1]/input")
	private WebElement searchMontadora;
	
	@FindBy(css = "#observacao")
	private WebElement observacao;
	
	@FindBy(css = "#btnConfirm")
	private WebElement btnConfirm;
	
	public MontadoraPage() {
	}
	
	public void informarMontadora() {
		this.searchMontadora.clear();
		this.searchMontadora.sendKeys("1");
	}
	
	public void infomarObservacao() {
		this.observacao.clear();
		this.observacao.sendKeys("Obs automação");
	}
	
	public void clicarBtnOk() throws InterruptedException {
		this.btnConfirm.click();
	}
}