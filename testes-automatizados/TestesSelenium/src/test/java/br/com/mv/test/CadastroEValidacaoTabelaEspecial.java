package br.com.mv.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.comercializacao.Comercializacao;
import br.com.mv.PageFactory.comercializacao.PesquisaComercializacao;
import br.com.mv.PageFactory.comercializacao.PopupVigencia;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.login.ValidacaoUsuario;
import br.com.mv.PageFactory.pedido.Liberacao;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.tabelaEspecial.PesquisaTabelaEspecial;
import br.com.mv.PageFactory.tabelaEspecial.TabelaEspecial;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CadastroEValidacaoTabelaEspecial {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private ControleMenu controleMenu;
	private PesquisaTabelaEspecial pesquisaTabelaEspecial;
	private TabelaEspecial tabelaEspecial;
	private PopupVigencia popupVigencia;
	private List<String> lojas, regionais, ufs, bandeiras, tiposVendas, tiposTabelas, departamentos, produtos, flagsAcesso;
	private Cliente cliente;
	private Pedido pedido;
	private ProdutoPedido produto;
	private Liberacao liberacao;
	
	private String dataInicio, dataFim, perLimite;
	
	private static String IP = "http://10.41.0.32:8080/"; 
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	
	@Before
	public void setup() {
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
		this.pesquisaTabelaEspecial = new PesquisaTabelaEspecial(driver);
		this.tabelaEspecial = new TabelaEspecial(driver);
		this.popupVigencia = new PopupVigencia(driver);
		this.lojas = new ArrayList<String>();
		this.regionais = new ArrayList<String>();
		this.ufs = new ArrayList<String>();
		this.bandeiras = new ArrayList<String>();
		this.tiposVendas = new ArrayList<String>();
		this.tiposTabelas = new ArrayList<String>();
		this.departamentos = new ArrayList<String>();
		this.produtos = new ArrayList<String>();
		this.flagsAcesso = new ArrayList<String>();
		this.cliente = new Cliente(driver);
		this.pedido = new Pedido(driver);
		this.produto = new ProdutoPedido(driver);
		this.liberacao = new Liberacao(driver);
		
		// Setando valores para o teste
		 
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");    
		
		this.dataInicio = sdf.format(new Date());
		this.dataFim = sdf.format(new Date());
		this.perLimite = "30,00";
		
		lojas.add("9001 - CASA AMARELA I - PE");
		tiposVendas.add("66");
		tiposTabelas.add("TABELA 89");
		flagsAcesso.add("Gerente da Loja");
		produtos.add("TOCA CD LENOXX AD1826 DVD T. LCD3 USB");
	}
	
	@Test
	public void testeA() throws InterruptedException {
		
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		controleMenu.navegar("Administração", "Política Comercial", "Tabela Especial");
		
		// -------------------------------
		//	Criando uma tabela especial
		// -------------------------------
		System.out.println("Criando uma tabela especial");
		
		pesquisaTabelaEspecial.clickBtnCriarTabelaEspecial();
		
		tabelaEspecial.clickVigencia();
		
		Thread.sleep(2000);
		popupVigencia.setDataInicio(dataInicio).setDataFim(dataFim).setLoja(lojas)
					.setRegional(regionais).setUf(ufs).setBandeira(bandeiras).clickOk();
		
		tabelaEspecial.setPercentualLimite(perLimite).setTipoVenda(tiposVendas).setTipoTabela(tiposTabelas).setFlagAcesso(flagsAcesso)
						.setDepartamento(departamentos).setProdutos(produtos).clickSalvar();
		
		Thread.sleep(1000);
		
		// Valida mensagem de registro salvo
		String msg = driver.findElement(By.cssSelector(".alert")).getText();
		
		Assert.assertTrue("A tabela especial não foi salva.", "Tabela Especial salva com sucesso!".equals(msg));
		
		driver.quit();
	}
	/*
	@Test
	public void testeB() throws InterruptedException {
		
		IP = "http://10.30.232.18:8080/"; 
		
		this.setup();
				
		// -------------------------------
		//	Criando um pedido
		// -------------------------------
		System.out.println("Criando um pedido");
		
		// Setando valores para o teste
		String cpf = "000.000.001-91";
		String tipoVenda = "66 - Carne Losango SCRED";
		String tabelaEspecial = "89 - TABELA 89";
		
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
		
		produto.setTabelaEspecial(1, tabelaEspecial);
		
		driver.findElement(By.cssSelector(".modal.in .btn.Sim")).click();
		
		liberacao.clickAbaLiberacao();
		
		Assert.assertTrue("A Liberacao de tabela não foi encontrada.", liberacao.getTipoLiberacao(2).equals("LIBERACAO DE TABELA"));
		Assert.assertTrue("A flag 'G' da liberação de preço não foi encontrada.", liberacao.getFlagLiberacao(2).equals("G"));
		
		liberacao.selecionarLiberacao(2).clickBtnLiberar();
		
		driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
		
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		liberacao.clickAbaLiberacao();
		liberacao.selecionarLiberacao(2).clickBtnLiberar().setObsLiberacao("Teste liberação").clickBtnLiberarLiberacao();
		
		new ValidacaoUsuario(driver).setUsuario("2906").setSenha("1").clickBtnOk();
		
		new Mensagens(driver).clickBtnOkDialog();
		
		Assert.assertTrue("A liberação de tabela não foi aprovada.", liberacao.getFlagLiberacao(2).equals("Sim"));
		
		driver.quit();
	}
	
	@Test
	public void testeConsulta() throws InterruptedException {
		
		IP = "http://10.30.232.11:8080/";
		
		this.setup();
		
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		controleMenu.navegar("Administração", "Política Comercial", "Tabela Especial");
		
		// -------------------------------
		//	Consultando uma tabela especial
		// -------------------------------
		System.out.println("Consultando uma tabela especial");
		
		pesquisaTabelaEspecial.setDataInicio(dataInicio).setLoja(lojas).setTipoVenda(tiposVendas).setTipoTabela(tiposTabelas)
						.setFlagAcesso(flagsAcesso).setProdutos(produtos).clickBtnPesquisar();
		
		try {
			driver.findElement(By.cssSelector("table"));
			System.out.println("Resultado encontrado.");
			
			driver.quit();
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("A Tabela Especial não foi encontrada.");
		}
	}*/
}