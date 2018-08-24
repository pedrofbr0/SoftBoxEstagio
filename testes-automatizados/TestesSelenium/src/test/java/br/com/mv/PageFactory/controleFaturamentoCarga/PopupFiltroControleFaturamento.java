package br.com.mv.PageFactory.controleFaturamentoCarga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupFiltroControleFaturamento {

	WebDriver driver;
	
	@FindBy(id = "txtPedido")
	private WebElement nroPedido;
	
	@FindBy(css = "#toolbarvazia  input[value='Consultar']")
	private WebElement btnConsultar;
	
	public PopupFiltroControleFaturamento(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupFiltroControleFaturamento setNroPedido(String pedido) {
		this.nroPedido.clear();
		this.nroPedido.sendKeys(pedido);
		
		return this;
	}
	
	public WebElement getConsultar() {
		return btnConsultar;
	}
	
	public PopupFiltroControleFaturamento clickConsultar() {
		btnConsultar.click();
		return this;
	}
}
