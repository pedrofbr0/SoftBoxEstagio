package br.com.mv.PageFactory.pedido;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.utils.Utils;

public class Pedido {

	WebDriver driver;
	
	@FindBy(css = "#cbx-nro-tipo-venda")
	private WebElement tipoVenda;
	
	@FindBy(css = "#cbx_nro_tipo_venda_chosen")
	private WebElement tipoVendaChosen;
	
	@FindBy(css = "#cbx_nro_tipo_venda_chosen .chosen-drop .chosen-search input")
	private WebElement tipoVendaSearch;
	
	@FindBy(css = "#cbx_nro_tipo_venda_chosen .chosen-results")
	private WebElement tipoVendaSelected;
	
	@FindBy(css = "#btn-fechamento-total-pedido")
	private WebElement btnFechamentoTotalPedido;
	
	@FindBy(css = "#btn-salvar-pedido")
	private WebElement btnSalvarPedido;
	
	@FindBy(css = "#cbx_plano_chosen")
	private WebElement cbxPlano;
	
	@FindBy(css = "#btn-fechar")
	private WebElement btnFechar;
	
	@FindBy(css = "#txt-pedido-venda")
	private WebElement txtPedidoVenda;
	
	@FindBy(css = "#btn-imprimir-documentos")
	private WebElement btnImprimirDocumentos;
	
	@FindBy(css = "#btn-analise-credito")
	private WebElement btnAnaliseCredito;
	
	@FindBy(css = "#btn-limite-credito")
	private WebElement btnLimiteCredito;
	
	// Impressão de documentos
	
	@FindBy(css = "#cbx_documento_chosen")
	private WebElement cbxDocumentoChosen;
	
	@FindBy(css = "#btn-modal-imprimir")
	private WebElement btnModalImprimirRelatorio;
	
	@FindBy(css = "#txt-vr-prestacao")
	private WebElement txtVrPrestacao;
	
	@FindBy(css = "#txt-vr-total-pedido")
	private WebElement txtVrTotalPedido;
	
	@FindBy(css = "#txt-comissao-plus-produto")
	private WebElement txtComissaoPlusProduto;
	
	@FindBy(css = "#txt-comissao-plus-juros")
	private WebElement txtComissaoPlusJuros;
	
	private ProdutoPedido produto;
	private Utils utils;
	private WebDriverWait wait;
	
	public Pedido(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.produto = new ProdutoPedido(driver);
		this.utils = new Utils();
		this.wait = new WebDriverWait(driver, 30);
	}
	
	public String getComissaoPlusProduto() {
		return this.txtComissaoPlusProduto.getText();
	}
	
	public String getComissaoPlusJuros() {
		return this.txtComissaoPlusJuros.getText();
	}
	
	public Pedido setTipoVenda(String tipoVenda) {
		wait.until(ExpectedConditions.visibilityOf(this.tipoVendaChosen));
		this.utils.preencherCampoChosen(this.tipoVendaChosen, tipoVenda);
		return this;
	}
	
	public Pedido setPlano(String plano) {
		this.utils.preencherCampoChosen(this.cbxPlano, plano);
		return this;
	}
	
	public Pedido adicionarProduto(HashMap<String, String> prod) throws InterruptedException {
		produto.adicionar(prod);
		
		return this;
	}
	
	public String getNumeroPedido() {
		String descNroPedido = this.txtPedidoVenda.getAttribute("value");
		String[] desc = descNroPedido.split("/", 2);
		
		return desc[0];
	}
	
	public Pedido clickFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public Pedido clickFechamentoTotalPedido() {
		this.btnFechamentoTotalPedido.click();
		return this;
	}
	
	public Pedido clickSalvarPedido() {
		this.btnSalvarPedido.click();
		return this;
	}
	
	public String getMensagemPedidoRealizado() {
		String msg = driver.findElement(By.cssSelector("div.modal.in[id^=mdl] .row")).getText();
		
		return msg;
	}
	
	public Pedido clickBtnImprimirDocumentos() {
		this.btnImprimirDocumentos.click();
		return this;
	}
	
	public Pedido setDocumento(String documento) {
		this.utils.preencherCampoChosen(this.cbxDocumentoChosen, documento);
		return this;
	}
	
	public Pedido clickBtnModalImprimirRelatorio() {
		this.btnModalImprimirRelatorio.click();
		return this;
	}
	
	public Pedido clickBtnAnaliseCredito() {
		this.btnAnaliseCredito.click();
		return this;
	}
	
	public String getValorPrestacao() {
		return this.txtVrPrestacao.getText();
	}
	
	public String getValorTotalPedido() {
		return this.txtVrTotalPedido.getText();
	}
	
	public Pedido clickBtnLimiteCredito() {
		this.btnLimiteCredito.click();
		return this;
	}
}
