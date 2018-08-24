package br.com.mv.PageFactory.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Login
 * 
 * @author weverton <weverton@softbox.com.br>
 *
 */
public class Logoff {

	WebDriver driver;
	
	@FindBy(id="btnSair")
	private WebElement btnSair;
	
	public Logoff(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Logoff clickSair() {
		btnSair.click();
		return this;
	}
	
}