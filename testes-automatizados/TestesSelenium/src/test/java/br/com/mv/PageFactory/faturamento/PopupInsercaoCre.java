package br.com.mv.PageFactory.faturamento;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupInsercaoCre {

	WebDriver driver;
	
	@FindBy(css = "#cbxTipoGeracaoCre")
	private WebElement cbxTipoGeracaoCre;
	
	@FindBy(css = "#btnProcessa")
	private WebElement btnProcessa;
	
	public PopupInsercaoCre(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupInsercaoCre setTipoGeracaoCre(String tipoGeracaoCre) {
		new Select(this.cbxTipoGeracaoCre).selectByVisibleText(tipoGeracaoCre);
		return this;
	}
	
	public PopupInsercaoCre clickBtnProcessa() {
		this.btnProcessa.click();
		return this;
	}
}