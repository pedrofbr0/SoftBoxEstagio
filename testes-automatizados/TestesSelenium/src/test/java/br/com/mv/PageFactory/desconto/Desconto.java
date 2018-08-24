package br.com.mv.PageFactory.desconto;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.Utils;

public class Desconto {

	WebDriver driver;
	
	@FindBy(css = "#descricao-desconto")
	private WebElement descricao;
	
	@FindBy(css = "#vigencia")
	private WebElement vigencia;
	
	@FindBy(css = "#percentual-desconto")
	private WebElement percentualDesconto;
	
	@FindBy(css = "#fora-linha")
	private WebElement foraLinha;
	
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
	
	public Desconto(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public Desconto setDescricao(String descricao) {
		this.descricao.clear();
		this.descricao.sendKeys(descricao);
		return this;
	}
	
	public Desconto clickVigencia() {
		this.vigencia.click();
		return this;
	}
	
	public Desconto setPercentualDesconto(String percentualComercializacao) {
		this.percentualDesconto.clear();
		this.percentualDesconto.sendKeys(percentualComercializacao);
		return this;
	}
	
	public Desconto marcarForaLinha(Boolean flag) {
		if (!this.foraLinha.isSelected() && flag == true || this.foraLinha.isSelected() && flag == false) {
			this.foraLinha.click();
		}
		
		return this;
	}
	
	public Desconto setTipoVenda(List<String> tiposVenda) {
		this.utils.preencherCampoTypeAHead(tiposVenda, this.tipoVenda, this.opcaoTipoVenda);
		return this;
	}
	
	public Desconto setFlagAcesso(List<String> flagsAcesso) {
		this.utils.preencherCampoTypeAHead(flagsAcesso, this.flagAcesso, this.opcaoFlagAcesso);
		return this;
	}
	
	public Desconto setDepartamento(List<String> departamento) {
		this.utils.preencherCampoTypeAHead(departamento, this.departamento, this.opcaoDepartamento);
		return this;
	}
	
	public Desconto setProdutos(List<String> produtos) {
		this.utils.preencherCampoTypeAHead(produtos, this.produto, this.opcaoProduto);
		return this;
	}
	
	public Desconto setGrupoEstoque(List<String> gruposEstoque) {
		this.utils.preencherCampoTypeAHead(gruposEstoque, this.grupoEstoque, this.opcaoGrupoEstoque);
		return this;
	}
	
	public Desconto clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
}