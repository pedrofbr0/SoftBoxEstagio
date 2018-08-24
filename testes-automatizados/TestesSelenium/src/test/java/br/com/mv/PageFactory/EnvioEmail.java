package br.com.mv.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnvioEmail {

	WebDriver driver;
	
	@FindBy(css = "#observacaoHistorico")
	private WebElement observacaoHistorico;
	
	@FindBy(css = "input[value='Ok']")
	private WebElement btnOk;
	
	public EnvioEmail(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public EnvioEmail setObsHistorico(String obsHistorico) {
		this.observacaoHistorico.clear();
		this.observacaoHistorico.sendKeys(obsHistorico);
		return this;
	}
	
	public EnvioEmail clickBtnOk() {
		this.btnOk.click();
		return this;
	}
}