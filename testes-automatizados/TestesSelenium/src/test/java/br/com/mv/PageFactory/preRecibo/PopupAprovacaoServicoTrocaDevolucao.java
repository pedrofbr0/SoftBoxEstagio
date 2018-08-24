package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupAprovacaoServicoTrocaDevolucao {
	
	WebDriver driver;
	
	@FindBy(id = "nroASTD")
	private WebElement nroSolicitacao;
	
	@FindBy(id = "stsASTD")
	private WebElement statusSolicitacao;
	
	@FindBy(id = "tipoASTD")
	private WebElement tipo;
	
	@FindBy(id = "txtPedidoASTD")
	private WebElement nroPedido;
	
	@FindBy(id = "empresaASTD")
	private WebElement empresa;
	
	@FindBy(id = "txtCodLojaASTD")
	private WebElement nroLoja;
	
	@FindBy(id = "txtMotivo")
	private WebElement motivo;
	
	@FindBy(id = "txtObsSol")
	private WebElement obsSolicitacao;
	
	@FindBy(id = "nroServicoASTD")
	private WebElement nroServico;
	
	@FindBy(id = "descServicoASTD")
	private WebElement descServico;
	
	@FindBy(id = "nroProdutoASTD")
	private WebElement nroProduto;
	
	@FindBy(id = "descProdutoASTD")
	private WebElement descProduto;
	
	@FindBy(id = "solASTD")
	private WebElement solicitacao;
	
	@FindBy(id = "stASTD")
	private WebElement statusServico;
	
	@FindBy(id = "dtASTD")
	private WebElement dataAprovadoReprovado;
	
	@FindBy(id = "txtObs")
	private WebElement obsAprovacaoReprovacao;
	
	@FindBy(id = "btnAprov")
	private WebElement btnAprovar;
	
	@FindBy(id = "btnReprov")
	private WebElement btnReprovar;
	
	@FindBy(id = "btnFe")
	private WebElement btnFechar;
	
	public PopupAprovacaoServicoTrocaDevolucao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getNroSolicitacao() {
		return nroSolicitacao.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setNroSolicitacao(String nroSolicitacao) {
		this.nroSolicitacao.clear();
		this.nroSolicitacao.sendKeys(nroSolicitacao);
		return this;
	}

	public String getStatusSolicitacao() {
		return statusSolicitacao.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setStatusSolicitacao(String statusSolicitacao) {
		this.statusSolicitacao.clear();
		this.statusSolicitacao.sendKeys(statusSolicitacao);
		return this;
	}

	public String getTipo() {
		return tipo.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setTipo(String tipo) {
		this.tipo.clear();
		this.tipo.sendKeys(tipo);
		return this;
	}

	public String getNroPedido() {
		return nroPedido.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setNroPedido(String nroPedido) {
		this.nroPedido.clear();
		this.nroPedido.sendKeys(nroPedido);
		return this;
	}

	public String getEmpresa() {
		return empresa.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setEmpresa(String empresa) {
		this.empresa.clear();
		this.empresa.sendKeys(empresa);
		return this;
	}

	public String getNroLoja() {
		return nroLoja.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setNroLoja(String nroLoja) {
		this.nroLoja.clear();
		this.nroLoja.sendKeys(nroLoja);
		return this;
	}

	public String getMotivo() {
		return motivo.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setMotivo(String motivo) {
		this.motivo.clear();
		this.motivo.sendKeys(motivo);
		return this;
	}

	public String getObsSolicitacao() {
		return obsSolicitacao.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setObsSolicitacao(String obsSolicitacao) {
		this.obsSolicitacao.clear();
		this.obsSolicitacao.sendKeys(obsSolicitacao);
		return this;
	}

	public String getNroServico() {
		return nroServico.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setNroServico(String nroServico) {
		this.nroServico.clear();
		this.nroServico.sendKeys(nroServico);
		return this;
	}

	public String getDescServico() {
		return descServico.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setDescServico(String descServico) {
		this.descServico.clear();
		this.descServico.sendKeys(descServico);
		return this;
	}

	public String getNroProduto() {
		return nroProduto.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setNroProduto(String nroProduto) {
		this.nroProduto.clear();
		this.nroProduto.sendKeys(nroProduto);
		return this;
	}

	public String getDescProduto() {
		return descProduto.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setDescProduto(String descProduto) {
		this.descProduto.clear();
		this.descProduto.sendKeys(descProduto);
		return this;
	}

	public String getSolicitacao() {
		return solicitacao.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setSolicitacao(String solicitacao) {
		this.solicitacao.clear();
		this.solicitacao.sendKeys(solicitacao);
		return this;
	}

	public String getStatusServico() {
		return statusServico.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setStatusServico(String statusServico) {
		this.statusServico.clear();
		this.statusServico.sendKeys(statusServico);
		return this;
	}

	public String getDataAprovadoReprovado() {
		return dataAprovadoReprovado.getAttribute("value");
	}

	public void setDataAprovadoReprovado(WebElement dataAprovadoReprovado) {
		this.dataAprovadoReprovado = dataAprovadoReprovado;
	}

	public String getObsAprovacaoReprovacao() {
		return obsAprovacaoReprovacao.getAttribute("value");
	}

	public PopupAprovacaoServicoTrocaDevolucao setObsAprovacaoReprovacao(String obsAprovacaoReprovacao) {
		this.obsAprovacaoReprovacao.clear();
		this.obsAprovacaoReprovacao.sendKeys(obsAprovacaoReprovacao);
		return this;
	}

	public WebElement getBtnAprovar() {
		return btnAprovar;
	}

	public PopupAprovacaoServicoTrocaDevolucao clickBtnAprovar() {
		this.btnAprovar.click();
		return this;
	}

	public WebElement getBtnReprovar() {
		return btnReprovar;
	}

	public PopupAprovacaoServicoTrocaDevolucao clickBtnReprovar() {
		this.btnReprovar.click();
		return this;
	}

	public WebElement getBtnFechar() {
		return btnFechar;
	}

	public PopupAprovacaoServicoTrocaDevolucao clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}

}
