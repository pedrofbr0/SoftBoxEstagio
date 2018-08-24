package br.com.mv.PageFactory.faturamento;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegularizacaoEstoque {

	WebDriver driver;
	
	@FindBy(css = "#btnPesquisarProdutoSaida")
	private WebElement btnPesquisarProdutoSaida;
	
	@FindBy(css = "#btnPesquisarProdutoEntrada")
	private WebElement btnPesquisarProdutoEntrada;
	
	@FindBy(css = "#txtQuantidade")
	private WebElement txtQuantidade;
	
	@FindBy(css = "#cbxGESaida")
	private WebElement cbxGESaida;
	
	@FindBy(css = "#cbxGEEntrada")
	private WebElement cbxGEEntrada;
	
	@FindBy(css = "#txtObservacao")
	private WebElement txtObservacao;
	
	@FindBy(css = "#btnConcluir")
	private WebElement btnConcluir;
	
	@FindBy(css = "#txtDuplicada")
	private WebElement txtDuplicata;
	
	@FindBy(css = "#txtMatricula")
	private WebElement txtMatricula;
	
	@FindBy(css = "#txtSenhaMatricula")
	private WebElement txtSenhaMatricula;
	
	@FindBy(css = "#btnInserir")
	private WebElement btnInserir;
	
	public RegularizacaoEstoque(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getBtnPesquisarProdutoSaida() {
		return btnPesquisarProdutoSaida;
	}
	
	public WebElement getBtnPesquisarProdutoEntrada() {
		return btnPesquisarProdutoEntrada;
	}
	
	public RegularizacaoEstoque setTxtQuantidade(String quantidade) {
		this.txtQuantidade.clear();
		this.txtQuantidade.sendKeys(quantidade);
		
		return this;
	}
	
	public RegularizacaoEstoque setCbxGESaida(String GESaida) {
		new Select(this.cbxGESaida).selectByVisibleText(GESaida);
		return this;
	}
	
	public RegularizacaoEstoque setCbxGEEntrada(String GEEntrada) {
		new Select(this.cbxGEEntrada).selectByVisibleText(GEEntrada);
		return this;
	}
	
	public RegularizacaoEstoque setTxtObservacao(String observacao) {
		this.txtObservacao.clear();
		this.txtObservacao.sendKeys(observacao);
		return this;
	}
	
	public RegularizacaoEstoque clickBtnConcluir() {
		this.btnConcluir.click();
		return this;
	}
	
	public RegularizacaoEstoque setNroDuplicata(String nroDuplicata) {
		this.txtDuplicata.clear();
		this.txtDuplicata.sendKeys(nroDuplicata);
		return this;
	}
	
	public RegularizacaoEstoque setMatricula(String nroMatricula) {
		this.txtMatricula.clear();
		this.txtMatricula.sendKeys(nroMatricula);
		return this;
	}
	
	public RegularizacaoEstoque setSenhaMatricula(String senhaMatricula) {
		this.txtSenhaMatricula.clear();
		this.txtSenhaMatricula.sendKeys(senhaMatricula);
		return this;
	}
	
	public RegularizacaoEstoque clickBtnInserir() {
		this.btnInserir.click();
		return this;
	}
}