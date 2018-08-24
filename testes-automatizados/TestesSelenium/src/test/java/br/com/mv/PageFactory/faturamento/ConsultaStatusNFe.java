package br.com.mv.PageFactory.faturamento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ConsultaStatusNFe {

	WebDriver driver;
	
	@FindBy(css = "input[name='radioOpcao'][value='1']")
	private WebElement filtroPendentes;
	
	@FindBy(css = "input[name='radioOpcao'][value='2']")
	private WebElement filtroNaoPendentes;
	
	@FindBy(css = "#txtNumeroNf")
	private WebElement txtNumeroNf;
	
	@FindBy(css = "#comboAcao")
	private WebElement comboAcao;
	
	@FindBy(css = "#btnPesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#btnAcao")
	private WebElement btnAcao;
	
	public ConsultaStatusNFe(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ConsultaStatusNFe setFiltroPendentes(Boolean pendentes) {
		
		if (pendentes) {
			this.filtroPendentes.click();
		} else {
			this.filtroNaoPendentes.click();
		}
		
		return this;
	}
	
	public ConsultaStatusNFe setNumeroNf(String numeroNf) {
		this.txtNumeroNf.clear();
		this.txtNumeroNf.sendKeys(numeroNf);
		return this;
	}
	
	public ConsultaStatusNFe setComboAcao(String acao) {
		new Select(this.comboAcao).selectByVisibleText(acao);
		return this;
	}
	
	public ConsultaStatusNFe selecionaNotaFiscal() {
		driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]/input")).click();
		return this;
	}
	
	public ConsultaStatusNFe clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public ConsultaStatusNFe clickBtnAcao() {
		this.btnAcao.click();
		return this;
	}
}