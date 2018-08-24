package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupCreditoTrocaAnterior {
	
	WebDriver driver;
	
	@FindBy(id = "txtNroPreRecibo")
	private WebElement preRecibo;
	
	@FindBy(id = "txtProcesso")
	private WebElement processo;
	
	@FindBy(id = "txtTotalCreditos")
	private WebElement total;
	
	@FindBy(id = "btnOk")
	private WebElement btnOk;
	
	public PopupCreditoTrocaAnterior(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getPreRecibo() {
		return preRecibo.getAttribute("value");
	}

	public PopupCreditoTrocaAnterior setPreRecibo(String preRecibo) {
		this.preRecibo.clear();
		this.preRecibo.sendKeys(preRecibo);
		return this;
	}

	public String getProcesso() {
		return processo.getAttribute("value");
	}

	public PopupCreditoTrocaAnterior setProcesso(String processo) {
		this.processo.clear();
		this.processo.sendKeys(processo);
		return this;
	}

	public String getTotal() {
		return total.getAttribute("value");
	}

	public PopupCreditoTrocaAnterior setTotal(String total) {
		this.total.clear();
		this.total.sendKeys(total);
		return this;
	}

	public WebElement getBtnOk() {
		return btnOk;
	}

	public PopupCreditoTrocaAnterior clickBtnOk() {
		this.btnOk.click();
		return this;
	}

}
