package br.com.mv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.thucydides.core.pages.PageObject;

public class TransportePage extends PageObject {

	@FindBy(id = "txtNomeMotorista")
	private WebElement nomeMotorista;
	
	@FindBy(id = "txtTelefoneMotorista")
	private WebElement telefoneMotorista;
	
	@FindBy(css = "#searchVeiculo .buttonSearchField")
	private WebElement searchVeiculo;
	
	@FindBy(id = "btnOK")
	private WebElement btnOk;
	
	public TransportePage() {
	}
	
	public TransportePage setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista.clear();
		this.nomeMotorista.sendKeys(nomeMotorista);
		return this;
	}
	
	public TransportePage setTelefoneMotorista(String telefoneMotorista) {
		this.telefoneMotorista.clear();
		this.telefoneMotorista.sendKeys(telefoneMotorista);
		return this;
	}
	
	public WebElement getBtnPesquisarVeiculo() {
		return this.searchVeiculo;
	}
	
	public WebElement getBtnOk() {
		return this.btnOk;
	}
	
	public TransportePage clickOk() {
		this.btnOk.click();
		return this;
	}
}
