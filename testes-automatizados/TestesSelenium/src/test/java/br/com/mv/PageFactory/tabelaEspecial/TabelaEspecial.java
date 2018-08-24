package br.com.mv.PageFactory.tabelaEspecial;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.Utils;

public class TabelaEspecial {

	WebDriver driver;
	
	@FindBy(css = "#vigencia")
	private WebElement vigencia;
	
	@FindBy(css = "#percentual-limite")
	private WebElement percentualLimite;
	
	@FindBy(css = "input[ng-model='itensSelecionados.tipo_tabela']")
	private WebElement tipoTabela;
	
	@FindBy(css = "input[ng-model='itensSelecionados.tipo_tabela'] ~ ul li a")
	private WebElement opcaoTipoTabela;
	
	@FindBy(css = "input[ng-model='itensSelecionados.tipo_venda']")
	private WebElement tipoVenda;
	
	@FindBy(css = "input[ng-model='itensSelecionados.tipo_venda'] ~ ul li a")
	private WebElement opcaoTipoVenda;
	
	@FindBy(css = "input[ng-model='itensSelecionados.flag_acesso']")
	private WebElement flagAcesso;
	
	@FindBy(css = "input[ng-model='itensSelecionados.flag_acesso'] ~ ul li a")
	private WebElement opcaoFlagAcesso;
	
	@FindBy(css = "input[ng-model='itensSelecionados.departamento']")
	private WebElement departamento;
	
	@FindBy(css = "input[ng-model='itensSelecionados.departamento'] ~ ul li a")
	private WebElement opcaoDepartamento;
	
	@FindBy(css = "input[ng-model='itensSelecionados.produto']")
	private WebElement produto;
	
	@FindBy(css = "input[ng-model='itensSelecionados.produto'] ~ ul li a")
	private WebElement opcaoProduto;
	
	@FindBy(css = "input[ng-model='itensSelecionados.grupo_estoque']")
	private WebElement grupoEstoque;
	
	@FindBy(css = "input[ng-model='itensSelecionados.grupo_estoque'] ~ ul li a")
	private WebElement opcaoGrupoEstoque;
	
	@FindBy(css = ".btn-primary")
	private WebElement btnSalvar;
	
	@FindBy(css = ".btn-default")
	private WebElement btnCancelar;
	
	private Utils utils;
	
	public TabelaEspecial(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public TabelaEspecial clickVigencia() {
		this.vigencia.click();
		return this;
	}
	
	public TabelaEspecial setPercentualLimite(String percentualLimite) {
		this.percentualLimite.clear();
		this.percentualLimite.sendKeys(percentualLimite);
		return this;
	}
	
	public TabelaEspecial setTipoVenda(List<String> tiposVenda) {
		this.utils.preencherCampoTypeAHead(tiposVenda, this.tipoVenda, this.opcaoTipoVenda);
		return this;
	}
	
	public TabelaEspecial setTipoTabela(List<String> tiposTabela) {
		this.utils.preencherCampoTypeAHead(tiposTabela, this.tipoTabela, this.opcaoTipoTabela);
		return this;
	}
	
	public TabelaEspecial setFlagAcesso(List<String> flagsAcesso) {
		this.utils.preencherCampoTypeAHead(flagsAcesso, this.flagAcesso, this.opcaoFlagAcesso);
		return this;
	}
	
	public TabelaEspecial setDepartamento(List<String> departamento) {
		this.utils.preencherCampoTypeAHead(departamento, this.departamento, this.opcaoDepartamento);
		return this;
	}
	
	public TabelaEspecial setProdutos(List<String> produtos) {
		this.utils.preencherCampoTypeAHead(produtos, this.produto, this.opcaoProduto);
		return this;
	}
	
	public TabelaEspecial clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
}
