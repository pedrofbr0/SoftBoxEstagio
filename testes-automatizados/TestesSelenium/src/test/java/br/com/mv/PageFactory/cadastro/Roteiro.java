package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.PageFactory.pedido.ConsultaPreSimplificada;
import br.com.mv.utils.Utils;

public class Roteiro {

	WebDriver driver;
	
	@FindBy(css = "#txt-sf-loja-cod-nro-loja-key")
	private WebElement codLoja;
	
	@FindBy(css = "#btn-sf-loja-search")
	private WebElement lojaSearch;
	
	@FindBy(css = "#inputRota")
	private WebElement rota;
	
	@FindBy(css = "#inputSequencia")
	private WebElement sequencia;
	
	@FindBy(css = "#dt_intervalo")
	private WebElement dtIntervalo;
	
	@FindBy(css = "#inputLojaPedido")
	private WebElement lojaPedido;
	
	@FindBy(css = "input[data-column='qtde_dias_entrega']")
	private WebElement qtdeDiasEntrega;
	
	@FindBy(css = "#cbx_uf_chosen")
	private WebElement uf;
	
	@FindBy(css = "#cbx_cod_cgl_chosen")
	private WebElement cidade;
	
	@FindBy(css = "#cbx_origem_chosen")
	private WebElement origem;
	
	@FindBy(css = "#cbx_empresa_base_chosen")
	private WebElement empresaBase;
	
	@FindBy(css = "#cbx_bairro_chosen")
	private WebElement bairro;
	
	@FindBy(css = "#btn-novo")
	private WebElement btnNovo;
	
	@FindBy(css = "#btn-salvar")
	private WebElement btnSalvar;
	
	@FindBy(css = "#hd-filtro-adicional")
	private WebElement filtroAdicional;
	
	@FindBy(css = "#ckb-todos-bairros")
	private WebElement chkTodosBairros;
	
	private Utils utils;
	
	public Roteiro(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public Roteiro setCodLoja(String codLoja) {
		this.codLoja.clear();
		this.codLoja.sendKeys(codLoja + Keys.TAB);
		return this;
	}
	
	public Roteiro setRota(String rota) {
		this.rota.clear();
		this.rota.sendKeys(rota);
		return this;
	}
	
	public Roteiro setSequencia(String sequencia) {
		this.sequencia.clear();
		this.sequencia.sendKeys(sequencia);
		return this;
	}
	
	public Roteiro setDtIntervalo(String dtIntervalo) {
		this.dtIntervalo.clear();
		this.dtIntervalo.sendKeys(dtIntervalo);
		return this;
	}
	
	public Roteiro setLojaPedido(String lojaPedido) {
		this.lojaPedido.clear();
		this.lojaPedido.sendKeys(lojaPedido);
		return this;
	}
	
	public Roteiro setQtdeDiasEntrega(String qtdeDiasEntrega) {
		this.qtdeDiasEntrega.clear();
		this.qtdeDiasEntrega.sendKeys(qtdeDiasEntrega);
		return this;
	}
	
	public Roteiro setOrigem(String origem) {
		this.utils.preencherCampoChosen(this.origem, origem);
		return this;
	}
	
	public Roteiro setEmpresaBase(String empresaBase) {
		this.utils.preencherCampoChosen(this.empresaBase, empresaBase);
		return this;
	}
	
	public Roteiro setUf(String uf) {
		this.utils.preencherCampoChosen(this.uf, uf);
		return this;
	}
	
	public Roteiro setCidade(String cidade) {
		this.utils.preencherCampoChosen(this.cidade, cidade);
		return this;
	}
	
	public Roteiro clickNovo() {
		this.btnNovo.click();
		return this;
	}
	
	public Roteiro clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public Roteiro clickFiltroAdicional() {
		this.filtroAdicional.click();
		return this;
	}
	
	public Roteiro setBairro(String bairro) {
		this.utils.preencherCampoChosen(this.bairro, bairro);
		return this;
	}
	
	public Roteiro setTodosBairros() {
		this.chkTodosBairros.click();
		return this;
	}
}