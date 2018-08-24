package br.com.mv.PageFactory.pedido;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConsultaPedido {

	WebDriver driver;
	
	@FindBy(css = "#txt-pedido")
	private WebElement txtPedido;
	
	@FindBy(css = "#btn-consultar-pedido")
	private WebElement btnConsultarPedido;
	
	public ConsultaPedido(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ConsultaPedido setNroPedido(String nroPedido) {
		this.txtPedido.sendKeys(nroPedido);
		return this;
	}
	
	public ConsultaPedido selecionarPedido(String nroPedido) {
		driver.findElement(By.xpath(".//*[@id='grid-pedido']/tbody/tr/td[contains(@aria-describedby, 'grid-pedido_nro_pedido') and contains(@title, '" + nroPedido + "')]")).click();
		return this;
	}
	
	public ConsultaPedido clickBtnConsultarPedido() {
		this.btnConsultarPedido.click();
		return this;
	}
}