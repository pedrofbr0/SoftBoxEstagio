package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.ControleJanela;

public class PopupAprovacaoProdutoTrocaDevolucao {
	
	WebDriver driver;
	
	@FindBy(id = "nroAPTD")
	private WebElement nroSolicitacao;
	
	@FindBy(id = "stsAPTD")
	private WebElement status;
	
	@FindBy(id = "tipoAPTD")
	private WebElement tipo;
	
	@FindBy(id = "txtPedidoAPTD")
	private WebElement nroPedido;
	
	@FindBy(id = "empresaAPTD")
	private WebElement empresa;
	
	@FindBy(id = "txtCodLojaAPTD")
	private WebElement codNroLoja;
	
	@FindBy(id = "txtMotivo")
	private WebElement motivo;
	
	@FindBy(id = "txtObsSol")
	private WebElement obsSolicitacao;
	
	@FindBy(id = "nroServicoAPTD")
	private WebElement nroProduto;
	
	@FindBy(id = "descProdutoAPTD")
	private WebElement descProduto;
	
	@FindBy(id = "btnAprov")
	private WebElement btnAprovar;
	
	@FindBy(id = "btnReprov")
	private WebElement btnReprovar;
	
	@FindBy(id = "btnFe")
	private WebElement btnFechar;
	
	public PopupAprovacaoProdutoTrocaDevolucao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getNroSolicitacao() {
		return nroSolicitacao.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setNroSolicitacao(String nroSolicitacao) {
		this.nroSolicitacao.clear();
		this.nroSolicitacao.sendKeys(nroSolicitacao);
		return this;
	}

	public String getStatus() {
		return status.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setStatus(String status) {
		this.status.clear();
		this.status.sendKeys(status);
		return this;
	}

	public String getTipo() {
		return tipo.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setTipo(String tipo) {
		this.tipo.clear();
		this.tipo.sendKeys(tipo);
		return this;
	}

	public String getNroPedido() {
		return nroPedido.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setNroPedido(String nroPedido) {
		this.nroPedido.clear();
		this.nroPedido.sendKeys(nroPedido);
		return this;
	}

	public String getEmpresa() {
		return empresa.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setEmpresa(String empresa) {
		this.empresa.clear();
		this.empresa.sendKeys(empresa);
		return this;
	}

	public String getCodNroLoja() {
		return codNroLoja.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setCodNroLoja(String codNroLoja) {
		this.codNroLoja.clear();
		this.codNroLoja.sendKeys(codNroLoja);
		return this;
	}

	public String getMotivo() {
		return motivo.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setMotivo(String motivo) {
		this.motivo.clear();
		this.motivo.sendKeys(motivo);
		return this;
	}

	public String getObsSolicitacao() {
		return obsSolicitacao.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setObsSolicitacao(String obsSolicitacao) {
		this.obsSolicitacao.clear();
		this.obsSolicitacao.getAttribute(obsSolicitacao);
		return this;
	}

	public String getNroProduto() {
		return nroProduto.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setNroProduto(String nroProduto) {
		this.nroProduto.clear();
		this.nroProduto.sendKeys(nroProduto);
		return this;
	}

	public String getDescProduto() {
		return descProduto.getAttribute("value");
	}

	public PopupAprovacaoProdutoTrocaDevolucao setDescProduto(String descProduto) {
		this.descProduto.clear();
		this.descProduto.sendKeys(descProduto);
		return this;
	}

	public WebElement getBtnAprovar() {
		return btnAprovar;
	}

	public PopupAprovacaoProdutoTrocaDevolucao clickBtnAprovar() {
		this.btnAprovar.click();
		return this;
	}

	public WebElement getBtnReprovar() {
		return btnReprovar;
	}

	public PopupAprovacaoProdutoTrocaDevolucao clickBtnReprovar() {
		this.btnReprovar.click();
		return this;
	}

	public WebElement getBtnFechar() {
		return btnFechar;
	}

	public PopupAprovacaoProdutoTrocaDevolucao setBtnFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public PopupAprovacaoProdutoTrocaDevolucao selecionarAprovacaoPendente(String descAprovacao) throws InterruptedException {
		ControleJanela controleJanela = new ControleJanela(driver);
		driver.findElement(By.xpath("//table[@id='Etapas:tContent']/tbody/tr/td[text()='" + descAprovacao + "']/parent::tr/td[1]")).click();
		Thread.sleep(1000);
		By btnObs = By.xpath("//table[@id='Etapas:tContent']/tbody/tr/td[text()='" + descAprovacao + "']/parent::tr/td/input[@type='button']");
		controleJanela.abrirPopupClick(btnObs);
		
		return this;
	}
	
}
