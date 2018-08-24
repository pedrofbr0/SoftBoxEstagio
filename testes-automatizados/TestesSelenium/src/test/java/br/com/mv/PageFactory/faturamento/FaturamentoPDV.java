package br.com.mv.PageFactory.faturamento;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FaturamentoPDV {

WebDriver driver;
	
	@FindBy(css = "#txtPedido")
	private WebElement txtPedido;
	
	@FindBy(css = "#btnPesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#btnFaturar")
	private WebElement btnFaturar;
	
	public FaturamentoPDV(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public FaturamentoPDV setNroPedido(String nroPedido) {
		this.txtPedido.clear();
		this.txtPedido.sendKeys(nroPedido);
		return this;
	}
	
	public FaturamentoPDV clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public FaturamentoPDV clickBtnFaturar() {
		this.btnFaturar.click();
		return this;
	}
	
	public FaturamentoPDV selecionarControleFaturamento(String nroPedido) {
		driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr/td[contains(text(), '" + nroPedido + "')]/parent::tr/td/input")).click();
		return this;
	}
}