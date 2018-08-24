package br.com.mv.PageFactory.pedido;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.Utils;

public class ProdutoPedido {

	WebDriver driver;
	
	@FindBy(css = "input[data-column='cod_nro_produto']")
	private WebElement codNroProduto;
	
	@FindBy(css = "#btn-prod-pesq-prod")
	private WebElement btnProdPesqProd;
	
	@FindBy(xpath = "//div[contains(@class, 'mdl-consulta_produtos')]/div/div/div[3]/button[1]")
	private WebElement btnSelecionar;
	
	@FindBy(css = "#btn-insere-produto")
	private WebElement btnInsereProduto;
	
	@FindBy(css = "#tab-produtos")
	private WebElement abaProdutos;
	
	@FindBy(css = "#btn-empenhar-produto")
	private WebElement btnEmpenharProduto;
	
	@FindBy(css = "#cbx_local_empenho_chosen")
	private WebElement localEmpenho;
	
	@FindBy(css = "#cbx_opcao_entrega_chosen")
	private WebElement opcaoEntrega;
	
	@FindBy(css = ".radio-outra-loja")
	private WebElement outraLoja;
	
	@FindBy(css = "#btn-escolher-loja-empenho")
	private WebElement btnSelecionarLojaEmpenho;
	
	@FindBy(css = "input[name='desc_info_adicional']")
	private WebElement descInfoAdicional;
	
	private AguardaCarregamento agCarregamento;
	private WebDriverWait wait;
	private Utils utils;
	
	public ProdutoPedido(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.agCarregamento = new AguardaCarregamento(driver);
		this.wait = new WebDriverWait(driver, 30);
		this.utils = new Utils();
	}
	
	public ProdutoPedido clickAbaProdutos() {
		this.abaProdutos.click();
		return this;
	}
	
	public ProdutoPedido pesquisarProduto(String codNroProduto) {
		this.codNroProduto.sendKeys(codNroProduto);
		this.btnProdPesqProd.click();
		return this;
	}
	
	public ProdutoPedido clickBtnInsereProduto() {
		this.btnInsereProduto.click();
		return this;
	}
	
	public ProdutoPedido adicionar(HashMap<String, String> prod) throws InterruptedException {
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		this.clickBtnInsereProduto();
		
		//Thread.sleep(3000);
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-column='nro_produto']")));
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		this.pesquisarProduto(prod.get("cod_nro_produto"));
		
		//Thread.sleep(3000);
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		driver.findElement(By.xpath("//td[contains(text(), '" + prod.get("cod_nro_produto") + "')]/parent::tr/td[1]/input")).click();
		
		// Ind promoção
		if (Boolean.parseBoolean(prod.get("ind_promocao_ativa"))) {
			
			if (Boolean.parseBoolean(prod.get("ind_sel_promocao")) == false) {
				agCarregamento.aguardarCarregamentoNovaArquitetura();
				driver.findElement(By.cssSelector(".btn.Ok")).click();
				
				agCarregamento.aguardarCarregamentoNovaArquitetura();
				driver.findElement(By.cssSelector(".btn.Sim")).click();
				
			} else {
				agCarregamento.aguardarCarregamentoNovaArquitetura();
				this.setPromocao();
				
				agCarregamento.aguardarCarregamentoNovaArquitetura();
				driver.findElement(By.cssSelector(".btn.Ok")).click();
			}
		}
		
		if (Boolean.parseBoolean(prod.get("ind_valida_roteiro"))) {
			String codNroLojaEmpenho = driver.findElement(By.xpath("//td[contains(text(), '" + prod.get("cod_nro_produto") + "')]/parent::tr/td[12]")).getText();
			
			Assert.assertEquals("Loja de empenho incorreta.", prod.get("cod_nro_loja_empenho"), codNroLojaEmpenho);
		}
		
		//Thread.sleep(3000);
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mdl-consulta_produtos')]/div/div/div[3]/button[1]")));
		this.clickSelecionar();
		
		return this;
	}
	
	public ProdutoPedido setPromocao() {
		driver.findElement(By.cssSelector("#grid-consulta-promocao input[data-id='1'].radio-seleciona-promocao")).click();
		return this;
	}
	
	public ProdutoPedido clickSelecionar() {
		this.btnSelecionar.click();
		return this;
	}
	
	public List<WebElement> buscarProduto(int nroLinha) {
		List<WebElement> dadosProduto = driver.findElements(By.xpath("//*[@id='grd-produtos']/tbody/tr[contains(@class, 'ui-widget-content')][" + nroLinha + "]/td"));
		
		return dadosProduto;
	}
	
	public String buscarValorDoProduto(int nroLinha) {
		List<WebElement> elementoProduto = buscarProduto(nroLinha);
		String vlrProduto = elementoProduto.get(8).getAttribute("title");
		
		return vlrProduto;
	}
	
	public ProdutoPedido setValorProduto(int nroLinha, String vlrProduto) {
		List<WebElement> elementoProduto = buscarProduto(nroLinha);
		elementoProduto.get(8).findElement(By.cssSelector("input[name='vr_unitario_produto']")).clear();
		elementoProduto.get(8).findElement(By.cssSelector("input[name='vr_unitario_produto']")).sendKeys(vlrProduto + Keys.ENTER);
		
		return this;
	}
	
	public ProdutoPedido setTabelaEspecial(int nroLinha, String tabelaEspecial) {
		List<WebElement> elementoProduto = buscarProduto(nroLinha);
		WebElement tipoTabela = elementoProduto.get(7).findElement(By.cssSelector("select[name='cod_tipo_tabela']"));
		
		new Select(tipoTabela).selectByVisibleText(tabelaEspecial);
		
		return this;
	}
	
	public ProdutoPedido setQtdProduto(int nroLinha, String qtdProduto) {
		List<WebElement> elementoProduto = buscarProduto(nroLinha);
		elementoProduto.get(6).findElement(By.cssSelector("input[name='qtde_pedida_produto']")).sendKeys(Keys.BACK_SPACE + qtdProduto + Keys.ENTER);
		
		return this;
	}

	public ProdutoPedido clickEmpenharProduto() {
		this.btnEmpenharProduto.click();
		return this;
	}
	
	public ProdutoPedido setLocalEmpenho(String localEmpenho) {
		utils.preencherCampoChosen(this.localEmpenho, localEmpenho);
		return this;
	}
	
	public ProdutoPedido setOpcaoEntrega(String opcaoEntrega) {
		utils.preencherCampoChosen(this.opcaoEntrega, opcaoEntrega);
		return this;
	}
	
	public ProdutoPedido setEmpenhoOutraLoja() {
		this.outraLoja.click();
		return this;
	}
	
	public ProdutoPedido clickSelecionarOutraLojaEmpenho() {
		this.btnSelecionarLojaEmpenho.click();
		return this;
	}
	
	public ProdutoPedido clickBtnDataEntregaAgendada() {
		driver.findElement(By.cssSelector("#btn-buscar-data-entrega-agendada")).click();
		return this;
	}
	
	public ProdutoPedido clickSelecionarDataEntrega() {
		driver.findElement(By.cssSelector("#grd-data-entrega #1")).click();
		driver.findElement(By.cssSelector("#btn-escolher-data")).click();
		return this;
	}
	
	public ProdutoPedido empenharProduto(HashMap<String, String> produto) throws InterruptedException {
		driver.findElement(By.cssSelector("#btn-detalhes-item-" + produto.get("id_linha"))).click();
		
		//Thread.sleep(1000);
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		if (Boolean.parseBoolean(produto.get("sem_faixa_cep"))) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.Ok")));
			driver.findElement(By.cssSelector(".btn.Ok")).click();
		}
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		setLocalEmpenho(produto.get("local_empenho")).setOpcaoEntrega(produto.get("opcao_entrega"));
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		if (produto.containsKey("nro_serie")) {
			setNumeroSerie(produto.get("nro_serie"));
		}
		
		if (produto.containsKey("informar_data_agendamento") && Boolean.parseBoolean(produto.get("informar_data_agendamento"))) {
			clickBtnDataEntregaAgendada().clickSelecionarDataEntrega();
		}
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		
		clickEmpenharProduto();
		
		if (produto.get("opcao_entrega").equals("RETIRADA OUTRA LOJA COM EMPENHO DEPOSITO")) {
			agCarregamento.aguardarCarregamentoNovaArquitetura();
			setEmpenhoOutraLoja().clickSelecionarOutraLojaEmpenho();
		}
		
		if (produto.containsKey("empenha_emitindo_mensagem") && Boolean.parseBoolean(produto.get("empenha_emitindo_mensagem"))) {
			agCarregamento.aguardarCarregamentoNovaArquitetura();
			driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
		}
		
		return this;
	}
	
	public ProdutoPedido setNumeroSerie(String nroSerie) {
		this.descInfoAdicional.sendKeys(nroSerie);
		
		return this;
	}
}
