package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupAprovacaoPlanilhaDevolucao {
	
	WebDriver driver;
	
	@FindBy(id = "txtNroPedido")
	private WebElement nroPedido;
	
	@FindBy(id = "txtNroLojaPedido")
	private WebElement nroLojaPedido;
	
	@FindBy(id = "txtEmpresa")
	private WebElement empresa;
	
	@FindBy(id = "txtTotalProdutos")
	private WebElement valorTotalProdutos;
	
	@FindBy(id = "txtTotalServicos")
	private WebElement valorTotalServicos;
	
	@FindBy(id = "txtTotalPedido")
	private WebElement valorTotalPedido;
	
	@FindBy(id = "txtPreRecibo")
	private WebElement preRecibo;
	
	@FindBy(id = "txtSituacaoPreRecibo")
	private WebElement situacaoPreRecibo;
	
	@FindBy(id = "txtTotalProdutosPreRecibo")
	private WebElement totalProdutosPreRecibo;
	
	@FindBy(id = "txtTotalServicosPreRecibo")
	private WebElement totalServicosPreRecibo;
	
	@FindBy(id = "txtTotalDevolucaoPreRecibo")
	private WebElement totalDevolucaoPreRecibo;
	
	@FindBy(id = "btnAprovar")
	private WebElement btnAprovar;
	
	@FindBy(id = "btnReprovar")
	private WebElement btnReprovar;
	
	@FindBy(id = "btnCacelar")
	private WebElement btnCancelar;
	
//	@FindBy(id = "cmbFormaPagtoDev")
	private WebElement colFormaPagto;
	
//	@FindBy(id = "txtVlrDev")
	private WebElement colValorDevolucao;
	
	private WebElement valorSaldo;
		
	public PopupAprovacaoPlanilhaDevolucao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public int getQtdLinhasPlanilha() {
		int qtdLinhasPlanilha = driver.findElements(By.xpath("//table[@id='tabelaCamposLinha:tContent']/tbody/tr")).size() - 1;
		return qtdLinhasPlanilha;
	}
	
	public PopupAprovacaoPlanilhaDevolucao preecherPlanilhaAprovacao() {
		int qtdLinhasPlanilha = this.getQtdLinhasPlanilha();
		
		for (int i = 0; i < qtdLinhasPlanilha; i++) {
			this.setColFormaPagto(i, "98 - DEPOSITO EM CONT").setValorDevolucao(i, this.getValorSaldo(i));
		}
		
		this.setBtnAprovar();
		
		return this;
	}
	
	public String getValorSaldo(int nroLinha) {
		this.valorSaldo = driver.findElement(By.id("txtVlrSaldo" + nroLinha));
		return valorSaldo.getAttribute("value");
	}
	
	public String getColFormaPagto(int nroLinha) {
		this.colFormaPagto = driver.findElement(By.id("cmbFormaPagtoDev" + nroLinha));
		return colFormaPagto.getText();
	}
	
	public PopupAprovacaoPlanilhaDevolucao setColFormaPagto(int nroLinha, String descFormaPagto) {
		this.colFormaPagto = driver.findElement(By.id("cmbFormaPagtoDev" + nroLinha));
		new Select(this.colFormaPagto).selectByVisibleText(descFormaPagto);
		return this;
	}
	
	public String getColValorDevolucao(int nroLinha) {
		this.colValorDevolucao = driver.findElement(By.id("txtVlrDev" + nroLinha));
		return this.colValorDevolucao.getAttribute("value");
	}
	
	public PopupAprovacaoPlanilhaDevolucao setValorDevolucao(int nroLinha, String nroValor) {
		this.colValorDevolucao = driver.findElement(By.id("txtVlrDev" + nroLinha));
		this.colValorDevolucao.clear();
		this.colValorDevolucao.sendKeys(nroValor);
		return this;
	}

	public String getNroPedido() {
		return nroPedido.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setNroPedido(String nroPedido) {
		this.nroPedido.clear();
		this.nroPedido.sendKeys(nroPedido);
		return this;
	}

	public String getNroLojaPedido() {
		return nroLojaPedido.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setNroLojaPedido(String nroLojaPedido) {
		this.nroLojaPedido.clear();
		this.nroLojaPedido.sendKeys(nroLojaPedido);
		return this;
	}

	public String getEmpresa() {
		return empresa.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setEmpresa(String empresa) {
		this.empresa.clear();
		this.empresa.sendKeys(empresa);
		return this;
	}

	public String getValorTotalProdutos() {
		return valorTotalProdutos.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setValorTotalProdutos(String valorTotalProdutos) {
		this.valorTotalProdutos.clear();
		this.valorTotalProdutos.sendKeys(valorTotalProdutos);
		return this;
	}

	public String getValorTotalServicos() {
		return valorTotalServicos.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setValorTotalServicos(String valorTotalServicos) {
		this.valorTotalServicos.clear();
		this.valorTotalServicos.sendKeys(valorTotalServicos);
		return this;
	}

	public String getValorTotalPedido() {
		return valorTotalPedido.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setValorTotalPedido(String valorTotalPedido) {
		this.valorTotalPedido.clear();
		this.valorTotalPedido.sendKeys(valorTotalPedido);
		return this;
	}

	public String getPreRecibo() {
		return preRecibo.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setPreRecibo(String preRecibo) {
		this.preRecibo.clear();
		this.preRecibo.sendKeys(preRecibo);
		return this;
	}

	public String getSituacaoPreRecibo() {
		return situacaoPreRecibo.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setSituacaoPreRecibo(String situacaoPreRecibo) {
		this.situacaoPreRecibo.clear();
		this.situacaoPreRecibo.sendKeys(situacaoPreRecibo);
		return this;
	}

	public String getTotalProdutosPreRecibo() {
		return totalProdutosPreRecibo.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setTotalProdutosPreRecibo(String totalProdutosPreRecibo) {
		this.totalProdutosPreRecibo.clear();
		this.totalDevolucaoPreRecibo.sendKeys(totalProdutosPreRecibo);
		return this;
	}

	public String getTotalServicosPreRecibo() {
		return totalServicosPreRecibo.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setTotalServicosPreRecibo(String totalServicosPreRecibo) {
		this.totalServicosPreRecibo.clear();
		this.totalServicosPreRecibo.sendKeys(totalServicosPreRecibo);
		return this;
	}

	public String getTotalDevolucaoPreRecibo() {
		return totalDevolucaoPreRecibo.getAttribute("value");
	}

	public PopupAprovacaoPlanilhaDevolucao setTotalDevolucaoPreRecibo(String totalDevolucaoPreRecibo) {
		this.totalDevolucaoPreRecibo.clear();
		this.totalDevolucaoPreRecibo.sendKeys(totalDevolucaoPreRecibo);
		return this;
	}

	public WebElement getBtnAprovar() {
		return btnAprovar;
	}

	public PopupAprovacaoPlanilhaDevolucao setBtnAprovar() {
		this.btnAprovar.click();
		return this;
	}

	public WebElement getBtnReprovar() {
		return btnReprovar;
	}

	public PopupAprovacaoPlanilhaDevolucao setBtnReprovar() {
		this.btnReprovar.click();
		return this;
	}

	public WebElement getBtnCancelar() {
		return btnCancelar;
	}

	public PopupAprovacaoPlanilhaDevolucao setBtnCancelar() {
		this.btnCancelar.click();
		return this;
	}
	
}
