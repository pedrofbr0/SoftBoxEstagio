package br.com.mv.PageFactory.devolucaoProdutos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupHistoricoControleDevolucao {
	
	WebDriver driver;
	
	@FindBy(id = "txtCodLoja")
	private WebElement nroLoja;
	
	@FindBy(id = "descLoja")
	private WebElement descLoja;
	
	@FindBy(id = "txtCodEmpresa")
	private WebElement nroEmpresa;
	
	@FindBy(id = "txtDescEmpresa")
	private WebElement descEmpresa;
	
	@FindBy(id = "txtNroPedido")
	private WebElement nroPedido;
	
	@FindBy(id = "txtStatus")
	private WebElement status;
	
	@FindBy(id = "txtDataHora")
	private WebElement dataHora;
	
	@FindBy(id = "txtObservacao")
	private WebElement observacao;
	
	@FindBy(id = "btnFechar")
	private WebElement btnFechar;
	
	public PopupHistoricoControleDevolucao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getNroLoja() {
		return nroLoja.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setNroLoja(String nroLoja) {
		this.nroLoja.clear();
		this.nroLoja.sendKeys(nroLoja);
		return this;
	}

	public String getDescLoja() {
		return descLoja.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setDescLoja(String descLoja) {
		this.descLoja.clear();
		this.descLoja.sendKeys(descLoja);
		return this;
	}

	public String getNroEmpresa() {
		return nroEmpresa.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setNroEmpresa(String nroEmpresa) {
		this.nroEmpresa.clear();
		this.nroEmpresa.sendKeys(nroEmpresa);
		return this;
	}

	public String getDescEmpresa() {
		return descEmpresa.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setDescEmpresa(String descEmpresa) {
		this.descEmpresa.clear();
		this.descEmpresa.sendKeys(descEmpresa);
		return this;
	}

	public String getNroPedido() {
		return nroPedido.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setNroPedido(String nroPedido) {
		this.nroPedido.clear();
		this.nroPedido.sendKeys(nroPedido);
		return this;
	}

	public String getStatus() {
		return status.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setStatus(String status) {
		this.status.clear();
		this.status.sendKeys(status);
		return this;
	}

	public String getDataHora() {
		return dataHora.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setDataHora(String dataHora) {
		this.dataHora.clear();
		this.dataHora.sendKeys(dataHora);
		return this;
	}

	public String getObservacao() {
		return observacao.getAttribute("value");
	}

	public PopupHistoricoControleDevolucao setObservacao(String observacao) {
		this.observacao.clear();
		this.observacao.sendKeys(observacao);
		return this;
	}

	public WebElement getBtnFechar() {
		return btnFechar;
	}

	public PopupHistoricoControleDevolucao clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}
	
	

}
