package br.com.mv.PageFactory.baixaRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PesquisaBackofficeCancelamento {
	
	WebDriver driver;
	
	@FindBy(id = "cmbEmpresas")
	private WebElement cbxEmpresa;
	
	@FindBy(id = "fluxoAprovAdm")
	private WebElement pesquisar;
	
	public PesquisaBackofficeCancelamento(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PesquisaBackofficeCancelamento setCbxEmpresa(String txtEmpresa) {
		new Select(cbxEmpresa).selectByVisibleText(txtEmpresa);
		return this;
	}
	
	public WebElement getPesquisar() {
		return this.pesquisar;
	}
	
	public PesquisaBackofficeCancelamento clickPesquisar() {
		this.pesquisar.click();
		return this;
	}
	
	public boolean verificarPedidoComBaixaPrioritaria(int nroPedido) {
		boolean flagPedidoBaixa = true;
		
		try {
			driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + nroPedido + "']"));
		} catch (NoSuchElementException e) {
			flagPedidoBaixa = false;
		}
		
		return flagPedidoBaixa;
	}
}