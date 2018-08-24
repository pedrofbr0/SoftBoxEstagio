package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.ControleJanela;

public class CalculoComissao {

	WebDriver driver;
	
	@FindBy(css = "#searchComissaoControle .buttonSearchField")
	private WebElement btnSearchReferencia;
	
	@FindBy(css = "#btCalcular")
	private WebElement btCalcular;
	
	@FindBy(css = "#btnStatusProcessos")
	private WebElement btnStatusProcessos;
	
	@FindBy(css = "#btPesquisar")
	private WebElement btPesquisar;
	
	private ControleJanela controleJanela;
	
	public CalculoComissao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.controleJanela = new ControleJanela(driver);
	}
	
	public WebElement getBtnSearchReferencia() {
		return btnSearchReferencia;
	}
	
	public CalculoComissao selecionarMes() throws InterruptedException {
		controleJanela.fecharJanela(By.xpath("//*[@id='table:tContent']/tbody/tr[1]/td[2]"));
		return this;
	}
	
	public CalculoComissao clickBtnCalcular() {
		this.btCalcular.click();
		return this;
	}
	
	public CalculoComissao clickBtnStatusProcessos() {
		this.btnStatusProcessos.click();
		return this;
	}
	
	public WebElement getBtnStatusProcessos() {
		return btnStatusProcessos;
	}
	
	public CalculoComissao clickBtnPesquisar() {
		this.btPesquisar.click();
		return this;
	}
}