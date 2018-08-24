package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupTextarea {
	
	WebDriver driver;
	
	@FindBy(id = "textAreaPopup")
	private WebElement textArea;
	
	@FindBy(css = "input[type='button'][value='Ok']")
	private WebElement btnOk;
	
	@FindBy(css = "input[type='button'][value='Fechar']")
	private WebElement btnFechar;
	
	public PopupTextarea(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTextArea() {
		return textArea.getAttribute("value");
	}

	public PopupTextarea setTextArea(String textArea) {
		this.textArea.clear();
		this.textArea.sendKeys(textArea);
		return this;
	}

	public WebElement getBtnOk() {
		return btnOk;
	}

	public PopupTextarea clickBtnOk() {
		this.btnOk.click();
		return this;
	}

	public WebElement getBtnFechar() {
		return btnFechar;
	}

	public PopupTextarea clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}

}
