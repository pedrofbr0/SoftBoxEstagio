package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.thucydides.core.pages.PageObject;

public class MensagemPage extends PageObject {

	@FindBy(id = "btnFechar")
	private WebElement btnFechar;
	
	@FindBy(xpath = "//table/tbody[@id='tbodyTabMensagens']/tr[1]/td[2]")
	private WebElement msg;
	
	@FindBy(xpath = "//div[contains(@class, 'alert') and contains(@style, 'block')]/div/div/div[contains(@class, 'modal-footer')]/button")
	private WebElement btnOkDialog;
	
	public MensagemPage() {
	}
	
	public WebElement getBtnFechar() {
		return this.btnFechar;
	}
	
	public MensagemPage clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public String getMsg() {
		return this.msg.getText();
	}
	
	public MensagemPage clickBtnOkDialog() {
		this.btnOkDialog.click();
		return this;
	}
}
