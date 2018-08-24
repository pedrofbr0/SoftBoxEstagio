package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupObsAprovReprov {
	
	WebDriver driver;
	
	@FindBy(id = "txtObsAprovReprov")
	private WebElement observacao;
	
	@FindBy(id = "btnCan")
	private WebElement btnCancelar;
	
	@FindBy(id = "btnFe")
	private WebElement btnOk;
	
	public PopupObsAprovReprov(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getObservacao() {
		return observacao.getAttribute("value");
	}

	public PopupObsAprovReprov setObservacao(String observacao) {
		this.observacao.clear();
		this.observacao.sendKeys(observacao);
		return this;
	}

	public WebElement getBtnCancelar() {
		return btnCancelar;
	}

	public PopupObsAprovReprov clickBtnCancelar() {
		this.btnCancelar.click();
		return this;
	}

	public WebElement getBtnOk() {
		return btnOk;
	}

	public PopupObsAprovReprov clickBtnOk() {
		this.btnOk.click();
		return this;
	}
	
	

}
