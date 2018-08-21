package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;

public class MensagensPage extends PageObject{

	@FindBy(id = "btnFechar")
	private WebElement btnFechar;
	
	@FindBy(xpath = "//table/tbody[@id='tbodyTabMensagens']/tr[1]/td[2]")
	private WebElement msg;
	
	@FindBy(xpath = "//div[contains(@class, 'alert') and contains(@style, 'block')]/div/div/div[contains(@class, 'modal-footer')]/button")
	private WebElement btnOkDialog;
	
	public MensagensPage() {
	}
	
	public WebElement getBtnFechar() {
		return this.btnFechar;
	}
	
	public MensagensPage clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public String getMsg() {
		return this.msg.getText();
	}
	
	public MensagensPage clickBtnOkDialog() {
		this.btnOkDialog.click();
		return this;
	}
}
