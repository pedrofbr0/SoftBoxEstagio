package br.com.mv.PageFactory.pedido;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.utils.Utils;

public class ServicoPedido {

	WebDriver driver;
	
	@FindBy(css = "#tab-servicos")
	private WebElement abaServicos; 
	
	public ServicoPedido(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ServicoPedido clickAbaServicos() {
		this.abaServicos.click();
		return this;
	}
	
	public void removerTodosServicos() {
		clickAbaServicos();
		
		int qtdServicos = driver.findElements(By.cssSelector("button[id^=btn-excluir-servico-]")).size();
		
		while (qtdServicos > 0) {
			driver.findElement(By.cssSelector("button[id^=btn-excluir-servico-]")).click();
			
			qtdServicos--;
		}
	}
}