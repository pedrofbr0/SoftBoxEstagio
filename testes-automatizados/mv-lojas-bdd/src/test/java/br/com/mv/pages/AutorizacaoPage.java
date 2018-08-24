package br.com.mv.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class AutorizacaoPage extends PageObject {

	@FindBy(css = "#txtCodigo")
	private WebElement txtCodigo;
	
	@FindBy(css = "#cbxTipoAutorizacao")
	private WebElement cbxTipoAutorizacao;
	
	@FindBy(xpath = ".//*[@id='sfLojaDestino']/tbody/tr/td[1]/input")
	private WebElement codNroLojaDestino;
	
	@FindBy(css = "#txtObsUsrSolicitante")
	private WebElement txtObsUsrSolicitante;
	
	@FindBy(css = "#cbxTipoNotaFiscal")
	private WebElement cbxTipoNotaFiscal;
	
	@FindBy(css = "#cbxStatusAutorizacao")
	private WebElement cbxStatusAutorizacao;
	
	@FindBy(css = "#btnItens")
	private WebElement btnItens;
	
	@FindBy(css = "#btnProcessa")
	private WebElement btnProcessa;
	
	@FindBy(css = "#btnSair")
	private WebElement btnSair;
	
	@FindBy(css = "#btnInserir")
	private WebElement btnInserir;
	
	@FindBy(css = "#btnSalvar")
	private WebElement btnSalvar;
	
	// Popup Itens
	@FindBy(css = "input[id^='txtQtdSolicitada']")
	public WebElement txtQtdSolicitada;
	
	@FindBy(css = "input[id^='txtQtdAutorizada']")
	public WebElement txtQtdAutorizada;
	
	ToolbarPage toolbar;
	ControleJanela controleJanela;
	PopupConsultaProdutoPage popupConsultaProduto;
	String codAutorizacao;
	
	public AutorizacaoPage() {
		toolbar = new ToolbarPage();
	}
	
	public void clicarBotaoNovo() {
		toolbar.clickNovo();
	}
	
	public WebElement getBtnInserir() {
		return this.btnInserir;
	}
	
	public WebElement getBtnSair() {
		return this.btnSair;
	}
	
	public void setTipoAutorizacao(String tipoAutorizacao) {
		new Select(this.cbxTipoAutorizacao).selectByVisibleText(tipoAutorizacao);
	}
	
	public void setLojaDestino(String codNroLojaDestino) {
		this.codNroLojaDestino.clear();
		this.codNroLojaDestino.sendKeys(codNroLojaDestino);
	}
	
	public void setObservacao(String observacao) {
		this.txtObsUsrSolicitante.clear();
		this.txtObsUsrSolicitante.sendKeys(observacao);
	}
	
	public void setTipoNotaFiscal(String tipoNotaFiscal) {
		new Select(this.cbxTipoNotaFiscal).selectByVisibleText(tipoNotaFiscal);
	}
	
	public void clicarBotaoSalvar() throws InterruptedException {
		toolbar.clickSalvar();
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		this.getDriver().switchTo().alert().accept();
		
		
		Thread.sleep(2000);
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		
		controleJanela.abrirPopupAlert(true);
	}
	
	public void clicarBotaoSalvarPopupItens() throws InterruptedException {
		$("#btnSalvar").click();
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		this.getDriver().switchTo().alert().accept();
	}
	
	public void clicarOkAlert() throws InterruptedException {
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		//this.getDriver().switchTo().alert().accept();
		this.getAlert().accept();
	}
	
	public void clicarSimAlert() throws InterruptedException {
		// Deseja preencher os itens da autorizacao?
		System.out.println("Antes");
		Thread.sleep(2000);
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		System.out.println("Antes 2");
		controleJanela.abrirPopupAlert(true);
		System.out.println("Depois");
	}
	
	public void clicarSimAlertPopupItens() throws InterruptedException {
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		this.getDriver().switchTo().alert().accept();
		controleJanela.voltarUltimoModal();
		
		codAutorizacao = this.getCodigo();
	}
	
	public void clicarBotaoInserir() throws InterruptedException {
		controleJanela.abrirPopupClick(this.getBtnInserir());
	}
	
	public void pesquisarProduto(String codProduto) throws InterruptedException {
		HashMap<String, String> pesquisaProduto = new HashMap<String, String>();
		//pesquisaProduto.put("departamento", "26 - 03 AUDIO_");
		//pesquisaProduto.put("situacao", "Ativo");
		pesquisaProduto.put("codNroProduto", codProduto);
		
		popupConsultaProduto.buscaUmProduto(pesquisaProduto);
	}
	
	public void setQtdSolicitada(String qtdSolicitada) {
		this.txtQtdSolicitada.clear();
		this.txtQtdSolicitada.sendKeys(qtdSolicitada);
	}
	
	public void setQtdAutorizada(String qtdAutorizada) {
		this.txtQtdAutorizada.clear();
		this.txtQtdAutorizada.sendKeys(qtdAutorizada);
	}
	
	public String getCodigo() {
		//String codigo = this.txtCodigo.getText();
		
		String codigo = $(".//*[@id='table:tContent']/tbody/tr/td[2]").getText();
		return codigo;
	}
	
	public void clicarBotaoSair() throws InterruptedException {
		controleJanela.fecharJanela(this.getBtnSair());
		
		codAutorizacao = this.getCodigo();
	}
	
	public void clicarBotaoProcessar() {
		codAutorizacao = this.getCodigo();
		this.btnProcessa.click();
	}
	
	public void clicarBotaoPesquisar() throws InterruptedException {
		Thread.sleep(3000);
		toolbar.clickPesquisar();
	}
	
	public void informarCodigo() {
		this.txtCodigo.sendKeys(codAutorizacao);
	}

	public void selecionarStatusAutorizacao(String statusAutorizacao) {
		new Select(this.cbxStatusAutorizacao).selectByVisibleText(statusAutorizacao);
	}
	
	public void selecionarAutorizacao() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("codAutorizacao: " + codAutorizacao);
		$(".//*[@id='table:tContent']/tbody/tr/td[contains(text(), '" + codAutorizacao + "')]").click();
	}
	
	public WebElement getBtnItens() {
		return this.btnItens;
	}

	public void clicarBotaoItens() throws InterruptedException {
		controleJanela.abrirPopupClick(this.getBtnItens());
	}
	
	public WebElement getBtnProcessar() {
		return this.btnProcessa;
	}
	
	public void clicarBotaoProcessarPreencherNotaAvulsa() throws InterruptedException {
		controleJanela.abrirPopupClick(this.getBtnProcessar());
	}
}