package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupSearchEmpresaLoja {

	WebDriver driver;
	
	@FindBy(css = "#cbxEmpresa")
	private WebElement cbxEmpresa;
	
	@FindBy(xpath = ".//*[@id='searchLoja']/tbody/tr/td[1]/input")
	private WebElement codLoja;
	
	@FindBy(css = ".buttonSearchField")
	private WebElement btnSearchLoja;
	
	@FindBy(css = "input[value='Ok']")
	private WebElement btnOk;
	
	public PopupSearchEmpresaLoja(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupSearchEmpresaLoja setEmpresa(String empresa) {
		new Select(this.cbxEmpresa).selectByVisibleText(empresa);
		return this;
	}
	
	public PopupSearchEmpresaLoja setLoja(String codLoja) {
		this.codLoja.clear();
		this.codLoja.sendKeys(codLoja);
		return this;
	}
	
	public PopupSearchEmpresaLoja clickBtnPesquisarLoja() {
		this.btnSearchLoja.click();
		return this;
	}
	
	public WebElement getBtnOk() {
		return btnOk;
	}
}