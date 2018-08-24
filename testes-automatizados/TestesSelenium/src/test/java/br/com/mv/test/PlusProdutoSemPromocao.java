package br.com.mv.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.pedido.AnaliseComercial;
import br.com.mv.PageFactory.pedido.AnaliseCredito;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.Planilha;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.pedido.ServicoPedido;
import br.com.mv.dao.pedido.Plus;
import br.com.mv.dao.pedido.PlusDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

public class PlusProdutoSemPromocao {

	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	private AguardaCarregamento load;
	private ControleMenu menu;
	
	private Cliente cliente;
	private Pedido pedido;
	private Planilha planilha;
	private ServicoPedido servicoPedido;
	private ProdutoPedido produtoPedido;
	private AnaliseComercial analiseComercial;
	
	private PlusDAO plusDAO;
	
	private static final String IP = "http://10.41.0.86:8080/"; 
	private static final String LOJA = "6044";
	private static final String USUARIO = "3000200";
	private static final String SENHA = "1";
	private static final String NRO_LOJA = "162";
	private static final String NRO_EMPRESA = "5";
	
	private static final String CPF = "00000000191";
	
	@Before
	public void setup() {
		this.profile = new FirefoxProfile();
		this.profile.setPreference("dom.popup_maximum", "9999999");
		this.profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.load = new AguardaCarregamento(driver);
		this.menu = new ControleMenu(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.cliente = new Cliente(driver);
		
		this.pedido = new Pedido(driver);
		this.planilha = new Planilha(driver);
		this.produtoPedido = new ProdutoPedido(driver);
		this.servicoPedido = new ServicoPedido(driver);
		this.analiseComercial = new AnaliseComercial(driver);
		
		this.plusDAO = new PlusDAO();
	}
	
	@Test
	public void test() throws InterruptedException, SQLException {
		
		this.plusDAO.atualizarDataMetaVendedor();
		
		// loga no sistema e abre a tela de Pré-Recibos
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		System.out.println("PLUS - PRODUTO SEM PROMOÇÃO");
		
		load.aguardarCarregamentoNovaArquitetura();
		cliente.setCPF(CPF).clickPesquisar();
		
		// Setando valores para o teste
		String tipoVenda = "53 - CARTAO DE CREDITO";
		String plano = "10 X";
		
		// Consulta Cliente
		load.aguardarCarregamentoNovaArquitetura();
		cliente.clickElaborarPedido();
		
		//Thread.sleep(3000);
		load.aguardarCarregamentoNovaArquitetura();
		pedido.setTipoVenda(tipoVenda);
		
		ArrayList<HashMap<String, String>> produtos = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> produto = new HashMap<String, String>();
		produto.put("id_linha", "1");
		produto.put("cod_nro_produto", "217957"); //  FOG ATLAS 4B FAST COOK BCO BIV
		produto.put("local_empenho", "LOJA");
		produto.put("opcao_entrega", "NORMAL");
		produto.put("empenha_emitindo_mensagem", "true");
		produto.put("cod_tabela", "01 - TABELA 01");
		produto.put("ind_promocao_ativa", "true");
		produto.put("ind_sel_promocao", "false");
		produto.put("vlr_produto_a_maior", "650,00");
		
		produtos.add(produto);
		
		int i = 1;
		
		for (HashMap<String, String> prod : produtos) {
			produtoPedido.clickAbaProdutos();
			pedido.adicionarProduto(prod);
			
			if (prod.containsKey("cod_tabela")) {
				load.aguardarCarregamentoNovaArquitetura2();
				produtoPedido.setTabelaEspecial(i, prod.get("cod_tabela"));
			}
			
			analiseComercial.setValorProduto(prod);
			
			i++;
		}
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.setPlano(plano);
		
		load.aguardarCarregamentoNovaArquitetura2();
		planilha.adicionarFormaPagamento(1, "F", "Cartao Visa", "");
		
		// Forma de Pagamento Cartao exige motivo!
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal.in .btn.Ok")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.clickSalvarPedido();
		
		// Pedido salvo com sucesso
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal.in .btn.Ok")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
				
		load.aguardarCarregamentoNovaArquitetura2();
		
		String nroPedido = pedido.getNumeroPedido();
		
		Plus plus = plusDAO.getCalculoPlusProdEJuros(NRO_LOJA, nroPedido, NRO_EMPRESA, USUARIO);
		
		Double comissaoPlusProd = (Double) ((FirefoxDriver)driver).executeScript("return Mv.Pedido.Controle.getPedido().vr_comissao_produto_plus");
		Double comissaoPlusJuros = (Double) ((FirefoxDriver)driver).executeScript("return Mv.Pedido.Controle.getPedido().vr_comissao_plus_juros");
		
		System.out.println("Comissao plus prod banco: " + Float.toString(plus.getPlusProdSemPromocao()));
		System.out.println("Comissao plus prod tela: " + Double.toString(comissaoPlusProd));
		
		System.out.println("Comissao plus juros banco: " + Float.toString(plus.getPlusJurosComPromocao()));
		System.out.println("Comissao plus juros tela: " + Double.toString(comissaoPlusJuros));
		
		// Realizar a validação
		Assert.assertEquals("O valor de plus do produto sem promoção está divergente.", Float.toString(plus.getPlusProdSemPromocao()), Double.toString(comissaoPlusProd));
		Assert.assertEquals("O valor de plus do juros sem promoção está divergente.", Float.toString(plus.getPlusJurosSemPromocao()), Double.toString(comissaoPlusJuros));
		
		driver.quit();
	}
}