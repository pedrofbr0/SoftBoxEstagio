package br.com.mv.PageFactory.cliente;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cliente {
	
	WebDriver driver;
	
	@FindBy(css = "#btn-elaborar-pedido")
	private WebElement btnElaborarPedido;
	
	@FindBy(css = "#cbx-cpf")
	private WebElement cpf;
	
	@FindBy(css = "#cbx-cnpj")
	private WebElement cnpj;
	
	@FindBy(css = "#ckb-tipo-pessoa")
	private WebElement tipoPessoa;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#btn-credito-preaprovado")
	private WebElement btnCreditoPreaprovado;
	
	public Cliente(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Cliente setCPF(String cpf) {
		this.cpf.clear();
		this.cpf.sendKeys(cpf);
		
		return this;
	}
	
	public Cliente setCNPJ(String cnpj) {
		this.cnpj.clear();
		this.cnpj.sendKeys(cnpj);
		
		return this;
	}
	
	public Cliente clickElaborarPedido() {
		this.btnElaborarPedido.click();
		return this;
	}
	
	public Cliente clickTipoPessoa() {
		this.tipoPessoa.click();
		return this;
	}
	
	public Cliente clickPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public Cliente clickCreditoPreAprovado() {
		this.btnCreditoPreaprovado.click();
		return this;
	}
}
