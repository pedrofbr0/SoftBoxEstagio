package br.com.mv.PageFactory.comercializacao;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.PageFactory.tabelaEspecial.PesquisaTabelaEspecial;
import br.com.mv.utils.Utils;

public class PesquisaComercializacao {

	WebDriver driver;
	
	@FindBy(css = "#btn-criar-comercializacao")
	private WebElement btnCriarComercializacao;
	
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
	
	@FindBy(css = "#filtro-conceito-cliente")
	private WebElement conceitoCliente;
	
	@FindBy(css = "#filtro-conceito-cliente ~ ul li a")
	private WebElement opcaoConceitoCliente;
	
	private Utils utils;
	
	public PesquisaComercializacao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public PesquisaComercializacao clickBtnCriarComercializacao() {
		this.btnCriarComercializacao.click();
		return this;
	}
	
	public PesquisaComercializacao clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public PesquisaComercializacao setLoja(List<String> lojas) {
		this.utils.preencherCampoTypeAHead(lojas, this.loja, this.opcaoLoja);
		return this;
	}
	
	public PesquisaComercializacao setProdutos(List<String> produtos) {
		this.utils.preencherCampoTypeAHead(produtos, this.produto, this.opcaoProduto);
		return this;
	}
	
	public PesquisaComercializacao setTipoVenda(List<String> tiposVenda) {
		this.utils.preencherCampoTypeAHead(tiposVenda, this.tipoVenda, this.opcaoTipoVenda);
		return this;
	}
	
	public PesquisaComercializacao setDataInicio(String dataInicio) {
		this.dataInicio.clear();
		this.dataInicio.sendKeys(dataInicio);
		return this;
	}	
	
	public PesquisaComercializacao setConceitoCliente(List<String> conceitosCliente) {
		this.utils.preencherCampoTypeAHead(conceitosCliente, this.conceitoCliente, this.opcaoConceitoCliente);
		return this;
	}
}