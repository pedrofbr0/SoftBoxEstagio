package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupConsultaExecucaoCalculo {

	WebDriver driver;
	
	@FindBy(css = "#txtStatus")
	private WebElement txtStatus;
	
	@FindBy(css = "#txtLog")
	private WebElement txtLog;
	
	public PopupConsultaExecucaoCalculo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getStatus() {
		return txtStatus.getText();
	}
	
	public String getTxtLog() {
		return txtLog.getText();
	}
}