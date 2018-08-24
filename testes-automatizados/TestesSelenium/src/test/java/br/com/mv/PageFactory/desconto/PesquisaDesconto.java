package br.com.mv.PageFactory.desconto;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.Utils;

public class PesquisaDesconto {

	WebDriver driver;
	
	@FindBy(css = "#btn-criar-desconto")
	private WebElement btnCriarDesconto;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#filtro-loja")
	private WebElement loja;
	
	@FindBy(css = "#filtro-loja ~ ul li a")
	private WebElement opcaoLoja;
	
	@FindBy(css = "#data-inicio")
	private WebElement dataInicio;
	
	@FindBy(css = "#filtro-produto")
	private WebElement produto;
	
	@FindBy(css = "#filtro-produto ~ ul li a")
	private WebElement opcaoProduto;
	
	@FindBy(css = "#filtro-tipo-venda")
	private WebElement tipoVenda;
	
	@FindBy(css = "#filtro-tipo-venda ~ ul li a")
	private WebElement opcaoTipoVenda;
	
	@FindBy(css = "#filtro-flag-acesso")
	private WebElement flagAcesso;
	
	@FindBy(css = "#filtro-flag-acesso ~ ul li a")
	private WebElement opcaoFlagAcesso;
	
	@FindBy(css = "#filtro-tipo-tabela")
	private WebElement tipoTabela;
	
	@FindBy(css = "#filtro-grupo-estoque ~ ul li a")
	private WebElement opcaoTipoTabela;
	
	@FindBy(css = "#filtro-grupo-estoque")
	private WebElement grupoEstoque;
	
	@FindBy(css = "#filtro-grupo-estoque ~ ul li a")
	private WebElement opcaoGrupoEstoque;
	
	private Utils utils;
	
	public PesquisaDesconto(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public PesquisaDesconto clickBtnCriarDesconto() {
		this.btnCriarDesconto.click();
		return this;
	}
	
	public PesquisaDesconto clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public PesquisaDesconto setLoja(List<String> lojas) {
		this.utils.preencherCampoTypeAHead(lojas, this.loja, this.opcaoLoja);
		return this;
	}
	
	public PesquisaDesconto setProdutos(List<String> produtos) {
		this.utils.preencherCampoTypeAHead(produtos, this.produto, this.opcaoProduto);
		return this;
	}
	
	public PesquisaDesconto setTipoTabela(List<String> tiposTabela) {
		this.utils.preencherCampoTypeAHead(tiposTabela, this.tipoTabela, this.opcaoTipoTabela);
		return this;
	}
	
	public PesquisaDesconto setFlagAcesso(List<String> flagsAcesso) {
		this.utils.preencherCampoTypeAHead(flagsAcesso, this.flagAcesso, this.opcaoFlagAcesso);
		return this;
	}
	
	public PesquisaDesconto setTipoVenda(List<String> tiposVenda) {
		this.utils.preencherCampoTypeAHead(tiposVenda, this.tipoVenda, this.opcaoTipoVenda);
		return this;
	}
	
	public PesquisaDesconto setGrupoEstoque(List<String> gruposEstoque) {
		this.utils.preencherCampoTypeAHead(gruposEstoque, this.grupoEstoque, this.opcaoGrupoEstoque);
		return this;
	}
	
	public PesquisaDesconto setDataInicio(String dataInicio) {
		this.dataInicio.clear();
		this.dataInicio.sendKeys(dataInicio);
		return this;
	}
}