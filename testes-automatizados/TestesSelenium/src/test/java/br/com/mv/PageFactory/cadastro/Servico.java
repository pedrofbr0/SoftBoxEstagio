package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Servico {

	WebDriver driver;
	
	@FindBy(css = "#txtDescricao")
	private WebElement txtDescricao;
	
	@FindBy(css = "#txtDescricaoAbreviada")
	private WebElement txtDescricaoAbreviada;
	
	@FindBy(xpath = ".//*[@id='searchOpCaixaCredito']/tbody/tr/td[2]/input")
	private WebElement operacaoCaixaCredito;
	
	@FindBy(xpath = ".//*[@id='searchOpCaixaJurosCredito']/tbody/tr/td[2]/input")
	private WebElement operacaoCaixaJurosCredito;
	
	@FindBy(xpath = ".//*[@id='searchOpCaixaDebito']/tbody/tr/td[2]/input")
	private WebElement operacaoCaixaDebito;
	
	@FindBy(xpath = ".//*[@id='searchOpCaixaJurosDebito']/tbody/tr/td[2]/input")
	private WebElement operacaoCaixaJurosDebito;
	
	@FindBy(xpath = ".//*[@id='comboAtivo']")
	private WebElement comboAtivo;
	
	@FindBy(xpath = ".//*[@id='comboTipoPessoa']")
	private WebElement comboTipoPessoa;
	
	@FindBy(xpath = ".//*[@id='searchTipoServico']/tbody/tr/td[2]/input")
	private WebElement pesquisaTipoServico;
	
	// Aba informações Gerais
	
	@FindBy(css = "#txtValorProdutoIni")
	private WebElement txtValorProdutoIni;
	
	@FindBy(css = "#txtValorProdutoFim")
	private WebElement txtValorProdutoFim;
	
	@FindBy(css = "#txtValorPrestacaoIni")
	private WebElement txtValorPrestacaoIni;
	
	@FindBy(css = "#txtValorPrestacaoFim")
	private WebElement txtValorPrestacaoFim;
	
	@FindBy(css = "#txtValorPedidoIni")
	private WebElement txtValorPedidoIni;
	
	@FindBy(css = "#txtValorPedidoFim")
	private WebElement txtValorPedidoFim;
	
	@FindBy(css = "#txtQuantidadeInicial")
	private WebElement txtQuantidadeInicial;
	
	@FindBy(css = "#txtQuantidadeFim")
	private WebElement txtQuantidadeFim;
	
	@FindBy(css = "#txtPlanoInicial")
	private WebElement txtPlanoInicial;
	
	@FindBy(css = "#txtPlanoFinal")
	private WebElement txtPlanoFinal;
	
	@FindBy(css = "#txtIdadeInicial")
	private WebElement txtIdadeInicial;
	
	@FindBy(css = "#txtIdadeFim")
	private WebElement txtIdadeFim;
	
	@FindBy(css = "#comboIndObrig")
	private WebElement comboIndObrig;
	
	@FindBy(css = "#comboIndGerenciamento")
	private WebElement comboIndGerenciamento;
	
	@FindBy(css = "#comboNroSorteio")
	private WebElement comboNroSorteio;
	
	@FindBy(css = "#comboInfoBancaria")
	private WebElement comboInfoBancaria;
	
	@FindBy(css = "#comboVariarQde")
	private WebElement comboVariarQde;
	
	@FindBy(css = "#comboVariarPl")
	private WebElement comboVariarPl;
	
	@FindBy(css = "#comboCalculaTAC")
	private WebElement comboCalculaTAC;
	
	@FindBy(css = "#comboDispConsumidor")
	private WebElement comboDispConsumidor;
	
	@FindBy(css = "#txtPercentualPrevVenda")
	private WebElement txtPercentualPrevVenda;
	
	@FindBy(css = "#txtTipoProdutoFinanceira")
	private WebElement txtTipoProdutoFinanceira;
	
	@FindBy(css = "#comboRestricaoAposentado")
	private WebElement comboRestricaoAposentado;
	
	@FindBy(css = "#comboVendaExterna")
	private WebElement comboVendaExterna;
	
	public Servico(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Servico setPercentualPrevVenda(String percentualPrevVenda) {
		this.txtPercentualPrevVenda.clear();
		this.txtPercentualPrevVenda.sendKeys(percentualPrevVenda);
		return this;
	}
	
	public Servico setTipoProdutoFinanceira(String tipoProdutoFinanceira) {
		this.txtTipoProdutoFinanceira.clear();
		this.txtTipoProdutoFinanceira.sendKeys(tipoProdutoFinanceira);
		return this;
	}
	
	public Servico setValorProdutoIni(String valorProdutoIni) {
		this.txtValorProdutoIni.clear();
		this.txtValorProdutoIni.sendKeys(valorProdutoIni);
		return this;
	}
	
	public Servico setValorProdutoFim(String valorProdutoFim) {
		this.txtValorProdutoFim.clear();
		this.txtValorProdutoFim.sendKeys(valorProdutoFim);
		return this;
	}
	
	public Servico setValorPrestacaoIni(String valorPrestacaoIni) {
		this.txtValorPrestacaoIni.clear();
		this.txtValorPrestacaoIni.sendKeys(valorPrestacaoIni);
		return this;
	}
	
	public Servico setValorPrestacaoFim(String valorPrestacaoFim) {
		this.txtValorPrestacaoFim.clear();
		this.txtValorPrestacaoFim.sendKeys(valorPrestacaoFim);
		return this;
	}
	
	public Servico setValorPedidoIni(String valorPedidoIni) {
		this.txtValorPedidoIni.clear();
		this.txtValorPedidoIni.sendKeys(valorPedidoIni);
		return this;
	}
	
	public Servico setValorPedidoFim(String valorPedidoFim) {
		this.txtValorPedidoFim.clear();
		this.txtValorPedidoFim.sendKeys(valorPedidoFim);
		return this;
	}
	
	public Servico setQuantidadeInicial(String quantidadeInicial) {
		this.txtQuantidadeInicial.clear();
		this.txtQuantidadeInicial.sendKeys(quantidadeInicial);
		return this;
	}
	
	public Servico setQuantidadeFim(String quantidadeFim) {
		this.txtQuantidadeFim.clear();
		this.txtQuantidadeFim.sendKeys(quantidadeFim);
		return this;
	}
	
	public Servico setPlanoInicial(String planoInicial) {
		this.txtPlanoInicial.clear();
		this.txtPlanoInicial.sendKeys(planoInicial);
		return this;
	}
	
	public Servico setPlanoFinal(String planoFinal) {
		this.txtPlanoFinal.clear();
		this.txtPlanoFinal.sendKeys(planoFinal);
		return this;
	}
	
	public Servico setIdadeInicial(String idadeInicial) {
		this.txtIdadeInicial.clear();
		this.txtIdadeInicial.sendKeys(idadeInicial);
		return this;
	}
	
	public Servico setIdadeFim(String idadeFim) {
		this.txtIdadeFim.clear();
		this.txtIdadeFim.sendKeys(idadeFim);
		return this;
	}
	
	public Servico setDescricao(String descricao) {
		this.txtDescricao.clear();
		this.txtDescricao.sendKeys(descricao);
		return this;
	}
	
	public Servico setDescricaoAbreviada(String descricaoAbreviada) {
		this.txtDescricaoAbreviada.clear();
		this.txtDescricaoAbreviada.sendKeys(descricaoAbreviada);
		return this;
	}
	
	public Servico setOperacaoCaixaCredito(String codOperacao) {
		this.operacaoCaixaCredito.clear();
		this.operacaoCaixaCredito.sendKeys(codOperacao);
		return this;
	}
	
	public Servico setOperacaoCaixaJurosCredito(String codOperacao) {
		this.operacaoCaixaJurosCredito.clear();
		this.operacaoCaixaJurosCredito.sendKeys(codOperacao);
		return this;
	}
	
	public Servico setOperacaoCaixaDebito(String codOperacao) {
		this.operacaoCaixaDebito.clear();
		this.operacaoCaixaDebito.sendKeys(codOperacao);
		return this;
	}
	
	public Servico setOperacaoCaixaJurosDebito(String codOperacao) {
		this.operacaoCaixaJurosDebito.clear();
		this.operacaoCaixaJurosDebito.sendKeys(codOperacao);
		return this;
	}
	
	public Servico setComboAtivo(String ativo) {
		new Select(this.comboAtivo).selectByVisibleText(ativo);
		return this;
	}
	
	public Servico setComboTipoPessoa(String tipoPessoa) {
		new Select(this.comboTipoPessoa).selectByVisibleText(tipoPessoa);
		return this;
	}
	
	public WebElement getBtnPesquisaTipoServico() {
		return this.pesquisaTipoServico;
	}
	
	public Servico selecionarServico() {
		driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]/input")).click();
		return this;
	}
	
	public Servico setComboIndObrig(String indObrig) {
		new Select(this.comboIndObrig).selectByVisibleText(indObrig);
		return this;
	}
	
	public Servico setComboIndGerenciamento(String indGerenciamento) {
		new Select(this.comboIndGerenciamento).selectByVisibleText(indGerenciamento);
		return this;
	}
	
	public Servico setComboNroSorteio(String nroSorteio) {
		new Select(this.comboNroSorteio).selectByVisibleText(nroSorteio);
		return this;
	}
	
	public Servico setComboInfoBancaria(String infoBancaria) {
		new Select(this.comboInfoBancaria).selectByVisibleText(infoBancaria);
		return this;
	}
	
	public Servico setComboVariarQde(String variarQde) {
		new Select(this.comboVariarQde).selectByVisibleText(variarQde);
		return this;
	}
	
	public Servico setComboVariarPl(String variarPl) {
		new Select(this.comboVariarPl).selectByVisibleText(variarPl);
		return this;
	}
	
	public Servico setComboCalculaTAC(String calculaTAC) {
		new Select(this.comboCalculaTAC).selectByVisibleText(calculaTAC);
		return this;
	}
	
	public Servico setComboDispConsumidor(String dispConsumidor) {
		new Select(this.comboDispConsumidor).selectByVisibleText(dispConsumidor);
		return this;
	}
	
	public Servico setComboRestricaoAposentado(String restricaoAposentado) {
		new Select(this.comboRestricaoAposentado).selectByVisibleText(restricaoAposentado);
		return this;
	}
	
	public Servico setComboVendaExterna(String vendaExterna) {
		new Select(this.comboVendaExterna).selectByVisibleText(vendaExterna);
		return this;
	}
}