package br.com.mv.PageFactory.cargaVeiculo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Transporte {
	
	WebDriver driver;
	
	@FindBy(id = "txtNomeMotorista")
	private WebElement nomeMotorista;
	
	@FindBy(id = "txtTelefoneMotorista")
	private WebElement telefoneMotorista;
	
	@FindBy(css = ".buttonSearchField")
	private WebElement searchVeiculo;
	
	@FindBy(id = "btnOK")
	private WebElement btnOk;
	
	public Transporte(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Transporte setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista.clear();
		this.nomeMotorista.sendKeys(nomeMotorista);
		return this;
	}
	
	public Transporte setTelefoneMotorista(String telefoneMotorista) {
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
	
	public Transporte clickOk() {
		this.btnOk.click();
		return this;
	}
}