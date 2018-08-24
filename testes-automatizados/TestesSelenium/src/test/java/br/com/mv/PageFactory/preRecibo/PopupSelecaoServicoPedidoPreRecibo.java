package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopupSelecaoServicoPedidoPreRecibo extends PopupPreRecibo{
	
	public PopupSelecaoServicoPedidoPreRecibo(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css = "input[name='tableServicosNaoSelecionados:tHeader:radio']")
	private WebElement chkSelecionarTodosServicos;
	
	public PopupSelecaoServicoPedidoPreRecibo clickSelecionarTodosServicos() {
		chkSelecionarTodosServicos.click();
		return this;
	}
	
	public PopupSelecaoServicoPedidoPreRecibo selecionarServicos(int[] produtos) {
		for (int i=0; i<produtos.length; i++) {
			driver.findElement(By.xpath("//table[@id='tableServicosNaoSelecionados:tContent']/tbody/tr/td[text()='" + produtos[i] + "']/parent::tr/td[1]")).click();
		}
		return this;
	}
	

}
