package br.com.mv.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.comercializacao.Comercializacao;
import br.com.mv.PageFactory.comercializacao.PesquisaComercializacao;
import br.com.mv.PageFactory.comercializacao.PopupVigencia;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CadastroEValidacaoComercializacao {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private ControleMenu controleMenu;
	private PesquisaComercializacao pesquisaComercializacao;
	private Comercializacao comercializacao;
	private PopupVigencia popupVigencia;
	private List<String> lojas, regionais, ufs, bandeiras, tiposVendas, conceitosCliente, departamentos, produtos;
	private Cliente cliente;
	private Pedido pedido;
	private ProdutoPedido produto;
	private String dataInicio, dataFim, perComercializacao, perComercializacaoAcimaPl;
	
	private static String IP = "http://10.41.0.32:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	
	@Before
	public void setup() throws MalformedURLException {
		/*FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.popup_maximum", "9999999");
		profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");*/
		
		System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chromedriver");
		
		this.driver = new ChromeDriver();
		
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.agCarregamento = new AguardaCarregamento(driver);
		this.controleMenu = new ControleMenu(driver);
		this.pesquisaComercializacao = new PesquisaComercializacao(driver);
		this.comercializacao = new Comercializacao(driver);
		this.popupVigencia = new PopupVigencia(driver);
		this.lojas = new ArrayList<String>();
		this.regionais = new ArrayList<String>();
		this.ufs = new ArrayList<String>();
		this.bandeiras = new ArrayList<String>();
		this.tiposVendas = new ArrayList<String>();
		this.conceitosCliente = new ArrayList<String>();
		this.departamentos = new ArrayList<String>();
		this.produtos = new ArrayList<String>();
		this.cliente = new Cliente(driver);
		this.pedido = new Pedido(driver);
		this.produto = new ProdutoPedido(driver);
		
		// Setando valores para o teste
		this.dataInicio = "25/04/2017";
		this.dataFim = "26/04/2017";
		this.perComercializacao = "10,00";
		this.perComercializacaoAcimaPl = "80,00";
		
		//lojas.add("F059 - LAURO DE FREITAS");
		lojas.add("9001 - CASA AMARELA I - PE"); // 001 Uberlandia T Vilela
		tiposVendas.add("1");
		produtos.add("TOCA CD LENOXX AD1826 DVD T. LCD3 USB");
		//regionais.add("MACEIO");
		//ufs.add("BAHIA");
		//bandeiras.add("RICARDO ELETRO");
		//tiposVendas.add("2");
		//conceitosCliente.add("PC");
		//departamentos.add("AUDIO_");
				
	}
	
	@Test
	public void testeA() throws InterruptedException {
		
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		controleMenu.navegar("Administração", "Política Comercial", "Comercialização");
		
		// -------------------------------
		//	Criando uma comercialização
		// -------------------------------
		System.out.println("Criando uma comercialização");
		
		pesquisaComercializacao.clickBtnCriarComercializacao();
		
		comercializacao.clickVigencia();
		
		Thread.sleep(2000);
		popupVigencia.setDataInicio(dataInicio).setDataFim(dataFim).setLoja(lojas)
					.setRegional(regionais).setUf(ufs).setBandeira(bandeiras).clickOk();
		
		comercializacao.setPercentualComercializacao(perComercializacao).setPercentualComercializacaoAcimaPl(perComercializacaoAcimaPl)
					.setTipoVenda(tiposVendas).setConceitoCliente(conceitosCliente).setDepartamento(departamentos)
					.setProdutos(produtos).clickSalvar();
		
		Thread.sleep(1000);
		
		// Valida mensagem de registro salvo
		String msg = driver.findElement(By.cssSelector(".alert")).getText();
		
		Assert.assertTrue("A comercialização não foi salva.", "Comercialização salva com sucesso!".equals(msg));
		
		
		
		// Validando aplicação da comercialização
		//controleMenu.navegar("Página inicial");
		//driver.findElement(By.xpath("html/body/div[1]/nav/ul[1]/li/a[contains(text(), 'Página inicial')]")).click();
		
		driver.quit();
	}
	/*
	@Test
	public void testeB() throws InterruptedException, MalformedURLException {
		// -------------------------------
		//	Criando um pedido
		// -------------------------------
		System.out.println("Criando um pedido");
		
		IP = "http://10.30.232.18:8080/"; 
		
		this.setup();
		
		// Setando valores para o teste
		String cpf = "000.000.001-91";
		String tipoVenda = "1 - A VISTA";
		String codNroProduto = "15066";
		//String vlrProdutoComComercializacao = "355,44";
		String vlrProdutoComComercializacao = "329,90";
				
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		Thread.sleep(3000);
		
		// Consulta Cliente
		cliente.setCPF(cpf).clickPesquisar();
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		cliente.clickElaborarPedido();
		
		Thread.sleep(3000);
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		pedido.setTipoVenda(tipoVenda);
		
		HashMap<String, String> prod = new HashMap<String, String>();
		prod.put("id_linha", "1");
		prod.put("cod_nro_produto", "15066");
		
		pedido.adicionarProduto(prod);
		
		String vlrProduto = produto.buscarValorDoProduto(1);
		
		Assert.assertTrue("A comercialização não foi aplicada.", vlrProdutoComComercializacao.equals(vlrProduto));
		
		driver.quit();
	}
	
	@Test
	public void testeConsulta() throws InterruptedException, MalformedURLException {
		
		IP = "http://10.30.232.11:8080/"; 
		this.setup();
		
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		controleMenu.navegar("Administração", "Política Comercial", "Comercialização");
		
		// -------------------------------
		//	Consultando uma comercialização
		// -------------------------------
		System.out.println("Consultando uma comercialização");
		
		pesquisaComercializacao.setDataInicio(dataInicio).setLoja(lojas).setTipoVenda(tiposVendas)
						.setProdutos(produtos).clickBtnPesquisar();
		
		try {
			driver.findElement(By.cssSelector("table"));
			System.out.println("Resultado encontrado.");
			
		} catch (NoSuchElementException e) {
			driver.quit();
			throw new NoSuchElementException("A Comercialização não foi encontrada.");
		}
		
		driver.quit();
	}*/
}