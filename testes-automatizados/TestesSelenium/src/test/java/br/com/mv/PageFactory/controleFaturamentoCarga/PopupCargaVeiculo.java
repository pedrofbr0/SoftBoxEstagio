package br.com.mv.PageFactory.controleFaturamentoCarga;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.utils.ControleJanela;

public class PopupCargaVeiculo {

	WebDriver driver;
	Toolbar toolbar;
	ControleJanela controleJanela;
	
	@FindBy(id = "txtCodigo")
	private WebElement numCarga;
	
	public PopupCargaVeiculo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		this.controleJanela = new ControleJanela(driver);
		this.toolbar = new Toolbar(driver);
	}
	
	public PopupCargaVeiculo setNumCarga(String numCarga) {
		this.numCarga.clear();
		this.numCarga.sendKeys(numCarga);
		
		return this;
	}
	
	public PopupCargaVeiculo clickPesquisar() {
		toolbar.clickPesquisar();
		return this;
	}
	
	public PopupCargaVeiculo selecionarCarga(String numCarga) throws InterruptedException {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + numCarga + "']/parent::tr/td[2]")).click();
		//controleJanela.voltarJanelaPrincipal();
		
		return this;
	}
}
