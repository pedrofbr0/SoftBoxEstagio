package br.com.mv.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Mensagens
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class Mensagens {
	
	WebDriver driver;
	
	@FindBy(id = "btnFechar")
	private WebElement btnFechar;
	
	@FindBy(xpath = "//table/tbody[@id='tbodyTabMensagens']/tr[1]/td[2]")
	private WebElement msg;
	
	@FindBy(xpath = "//div[contains(@class, 'alert') and contains(@style, 'block')]/div/div/div[contains(@class, 'modal-footer')]/button")
	private WebElement btnOkDialog;
	
	public Mensagens(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getBtnFechar() {
		return this.btnFechar;
	}
	
	public Mensagens clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public String getMsg() {
		return this.msg.getText();
	}
	
	public Mensagens clickBtnOkDialog() {
		this.btnOkDialog.click();
		return this;
	}

}
