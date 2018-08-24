package br.com.mv.PageFactory.preRecibo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.utils.Utils;

public class PesquisaPreRecibo {

	WebDriver driver;
	Utils utils;
	
	// Filtros para pesquisa
	
	@FindBy(id = "radio-pre-recibo")
	private WebElement radioPreRecibo;
	
	@FindBy(id = "radio-pedido")
	private WebElement radioPedido;
	
	@FindBy(id = "radio-loja")
	private WebElement radioLoja;
	
	@FindBy(id = "radio-regiao")
	private WebElement radioRegiao;
	
	@FindBy(id = "radio-uf")
	private WebElement radioUf;
	
	// Pesquisa por Pre-recibo
	
	@FindBy(id = "filtro-nro-prerecibo")
	private WebElement filtroNroPreRecibo;
	
	// Pesquisa por Pedido
	
	@FindBy(id = "filtro-nro-pedido")
	private WebElement nroPedido;
	
	@FindBy(id = "filtro-bandeira")
	private WebElement bandeira;
	
	@FindBy(css = "#filtro-bandeira ~ ul li a")
	private WebElement opcaoBandeira;
	
	@FindBy(id = "filtro-loja")
	private WebElement lojaPedido;
	
	@FindBy(css = "#filtro-loja ~ ul li a")
	private WebElement opcaoLojaPedido;
	
	// Pesquisa por Loja
	
	@FindBy(css = "input[ng-model='lojaSelecionada']")
	private WebElement lojaSelecionada;
	
	@FindBy(css = "input[ng-model='lojaSelecionada'] ~ ul li a")
	private WebElement opcaoLojaSelecionada;
	
	@FindBy(id = "status_pre_recibo")
	private WebElement statusPreRecibo;
	
	@FindBy(id = "tipo-pre-recibo")
	private WebElement tipoPreRecibo;
	
	@FindBy(id = "data-inicio")
	private WebElement dataInicio;
	
	@FindBy(id = "data-fim")
	private WebElement dataFim;
	
	// Pesquisa por Regiao
	
	@FindBy(id = "filtro-grupo-regional")
	private WebElement grupoRegional;
	
	@FindBy(css = "#filtro-grupo-regional ~ ul li a")
	private WebElement opcaoGrupoRegional;
	
	// pesquisa por Estado
	
	@FindBy(id = "filtro-estado")
	private WebElement estado;
	
	// Botoes
	
	@FindBy(id = "btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(id = "btn-limpar-resultado")
	private WebElement btnLimparResultado;
	
	@FindBy(id = "btn-criar-pre-recibo")
	private WebElement btnCriarPreRecibo;
	
	// Novo Pre-recibo mudar para outro o arquivo ElaboracaoPreRecibo
	
	@FindBy(id = "btn-pesquisar-pedido")
	private WebElement btnPesquisarPedido;
	
	private WebDriverWait wait;
	
	public PesquisaPreRecibo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PesquisaPreRecibo clickRadioPreRecibo() {
		this.radioPreRecibo.click();
		return this;
	}
	
	public PesquisaPreRecibo clickRadioPedido() {
		this.radioPedido.click();
		return this;
	}
	
	public PesquisaPreRecibo clickRadioLoja() {
		this.radioLoja.click();
		return this;
	}
	
	public PesquisaPreRecibo clickRadioRegiao() {
		this.radioRegiao.click();
		return this;
	}
	
	public PesquisaPreRecibo clickRadioUf() {
		this.radioUf.click();
		return this;
	}
	
	public PesquisaPreRecibo setFiltroNroPedido(String pedido) {
		this.nroPedido.clear();
		this.nroPedido.sendKeys(pedido);
		return this;
	}
	
	public PesquisaPreRecibo setFiltroBandeira(String bandeira) {
		this.utils.preencherCampoTypeAHead(bandeira, this.bandeira);
		return this;
	}
	
	public PesquisaPreRecibo setFiltroLojaPedido(String loja) {
		this.utils.preencherCampoTypeAHead(loja, this.lojaPedido);
		return this;
	}
	
	public PesquisaPreRecibo setFiltroNroPreRecibo(String nroPreRecibo) {
		this.filtroNroPreRecibo.clear();
		this.filtroNroPreRecibo.sendKeys(nroPreRecibo);
		return this;
	}
	
	public PesquisaPreRecibo setStatusPreRecibo(String statusPreRecibo) {
		this.utils.preencherCampoTypeAHead(statusPreRecibo, this.statusPreRecibo);
		return this;
	}
	
	public PesquisaPreRecibo setTipoPreRecibo(String tipoPreRecibo) {
		new Select(this.tipoPreRecibo).selectByVisibleText(tipoPreRecibo);
		return this;
	}
	
	public PesquisaPreRecibo setDataInicio(String dataInicio) {
		this.dataInicio.clear();
		this.dataInicio.sendKeys(dataInicio);
		return this;
	}
	
	public PesquisaPreRecibo setDataFim(String dataFim) {
		this.dataFim.clear();
		this.dataFim.sendKeys(dataFim);
		return this;
	}
	
	public PesquisaPreRecibo setGrupoRegional(String grupoRegional) {
		this.utils.preencherCampoTypeAHead(grupoRegional, this.grupoRegional);
		return this;
	}
	
	public PesquisaPreRecibo setEstado(String estado) {
		this.utils.preencherCampoTypeAHead(estado, this.estado);
		return this;
	}
	
	public PesquisaPreRecibo clickPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public PesquisaPreRecibo clickLimparResultado() {
		this.btnLimparResultado.click();
		return this;
	}
	
	public PesquisaPreRecibo clickCriarPreRecibo() {
		this.btnCriarPreRecibo.click();
		return this;
	}
	
	public PesquisaPreRecibo clickEditarPreRecibo(int nroPreRecibo) {
		driver.findElement(By.cssSelector("#btn-editar-" + nroPreRecibo)).click();
		return this;
	}
	
	public PesquisaPreRecibo clickOrdemRecolhimentoPreRecibo(int nroPreRecibo) {
		driver.findElement(By.cssSelector("#btn-ordem-recolhimento-" + nroPreRecibo)).click();
		return this;
	}
	
	public PesquisaPreRecibo clickGerarCredito(int nroPreRecibo) {
		try {
			driver.findElement(By.cssSelector("#btn-gerar-credito-" + nroPreRecibo)).click();
			driver.switchTo().alert().accept();
		} catch (NoSuchElementException e) {
			Assert.assertTrue("Pré-recibo: " + nroPreRecibo + " , botão gerar crédito não foi encontrado!", false);
		}
		
		return this;
	}
	
	public PesquisaPreRecibo clickImprimirReciboTroca(int nroPreRecibo) {
		try {
			//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn-imprimir-" + nroPreRecibo)));
			
			driver.findElement(By.id("btn-imprimir-" + nroPreRecibo)).click();
		} catch (NoSuchElementException e) {
			Assert.assertTrue("Pré-recibo: " + nroPreRecibo + " , botão imprimir recibo não foi encontrado!", false);
		}
		
		return this;
	}
}