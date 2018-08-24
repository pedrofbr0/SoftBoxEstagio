package br.com.mv.PageFactory.comercializacao;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.Utils;

public class Comercializacao {

	WebDriver driver;
	
	@FindBy(css = "#vigencia")
	private WebElement vigencia;
	
	@FindBy(css = "#percentual-comercializacao")
	private WebElement percentualComercializacao;
	
	@FindBy(css = "#percentual-comercializacao-acima-pl")
	private WebElement percentualComercializacaoAcimaPl;
	
	@FindBy(css = "input[ng-model='itensSelecionados.tipo_venda']")
	private WebElement tipoVenda;
	
	@FindBy(css = "input[ng-model='itensSelecionados.tipo_venda'] ~ ul li a")
	private WebElement opcaoTipoVenda;
	
	@FindBy(css = "input[ng-model='itensSelecionados.conceito_cliente']")
	private WebElement conceitoCliente;
	
	@FindBy(css = "input[ng-model='itensSelecionados.conceito_cliente'] ~ ul li a")
	private WebElement opcaoConceitoCliente;
	
	@FindBy(css = "input[ng-model='itensSelecionados.departamento']")
	private WebElement departamento;
	
	@FindBy(css = "input[ng-model='itensSelecionados.departamento'] ~ ul li a")
	private WebElement opcaoDepartamento;
	
	@FindBy(css = "input[ng-model='itensSelecionados.produto']")
	private WebElement produto;
	
	@FindBy(css = "input[ng-model='itensSelecionados.produto'] ~ ul li a")
	private WebElement opcaoProduto;
	
	@FindBy(css = ".btn-primary")
	private WebElement btnSalvar;
	
	@FindBy(css = ".btn-default")
	private WebElement btnCancelar;
	
	private Utils utils;
	
	public Comercializacao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public Comercializacao clickVigencia() {
		this.vigencia.click();
		return this;
	}
	
	public Comercializacao setPercentualComercializacao(String percentualComercializacao) {
		this.percentualComercializacao.clear();
		this.percentualComercializacao.sendKeys(percentualComercializacao);
		return this;
	}
	
	public Comercializacao setPercentualComercializacaoAcimaPl(String percentualComercializacaoAcimaPl) {
		this.percentualComercializacaoAcimaPl.clear();
		this.percentualComercializacaoAcimaPl.sendKeys(percentualComercializacaoAcimaPl);
		return this;
	}
	
	public Comercializacao setTipoVenda(List<String> tiposVenda) {
		this.utils.preencherCampoTypeAHead(tiposVenda, this.tipoVenda, this.opcaoTipoVenda);
		return this;
	}
	
	public Comercializacao setConceitoCliente(List<String> conceitosCliente) {
		this.utils.preencherCampoTypeAHead(conceitosCliente, this.conceitoCliente, this.opcaoConceitoCliente);
		return this;
	}
	
	public Comercializacao setDepartamento(List<String> departamento) {
		this.utils.preencherCampoTypeAHead(departamento, this.departamento, this.opcaoDepartamento);
		return this;
	}
	
	public Comercializacao setProdutos(List<String> produtos) {
		this.utils.preencherCampoTypeAHead(produtos, this.produto, this.opcaoProduto);
		return this;
	}
	
	public Comercializacao clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
}