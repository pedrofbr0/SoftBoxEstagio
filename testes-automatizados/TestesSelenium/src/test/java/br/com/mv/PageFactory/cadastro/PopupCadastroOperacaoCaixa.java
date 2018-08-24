package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PopupCadastroOperacaoCaixa {
	
	WebDriver driver;
	
	public PopupCadastroOperacaoCaixa(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement selecionarOperacaoCaixa() {
		WebElement elemento = driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]/input"));
		return elemento;
	}
}