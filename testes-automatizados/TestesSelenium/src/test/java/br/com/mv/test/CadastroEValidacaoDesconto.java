package br.com.mv.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.comercializacao.Comercializacao;
import br.com.mv.PageFactory.comercializacao.PesquisaComercializacao;
import br.com.mv.PageFactory.comercializacao.PopupVigencia;
import br.com.mv.PageFactory.desconto.Desconto;
import br.com.mv.PageFactory.desconto.PesquisaDesconto;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.login.ValidacaoUsuario;
import br.com.mv.PageFactory.pedido.Liberacao;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CadastroEValidacaoDesconto {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private ControleMenu controleMenu;
	private PesquisaDesconto pesquisaDesconto;
	private Desconto desconto;
	private PopupVigencia popupVigencia;
	private List<String> lojas, regionais, ufs, bandeiras, tiposVendas, flagsAcesso, departamentos, produtos, gruposEstoque;
	private Cliente cliente;
	private Pedido pedido;
	private ProdutoPedido produto;
	private Liberacao liberacao;
	
	String dataInicio, dataFim, perDesconto;
	Boolean marcarForaLinha;
	
	/*private static final String IP = "http://10.25.235.190:8080/"; 
	private static final String LOJA = "59";
	private static final String USUARIO = "9995555";
	private static final String SENHA = "1";*/
	
	private static String IP = "http://10.30.232.81:8080/"; 
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	
	@Before
	public void setup() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.popup_maximum", "9999999");
		profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.agCarregamento = new AguardaCarregamento(driver);
		this.controleMenu = new ControleMenu(driver);
		this.pesquisaDesconto = new PesquisaDesconto(driver);
		this.desconto = new Desconto(driver);
		this.popupVigencia = new PopupVigencia(driver);
		this.lojas = new ArrayList<String>();
		this.regionais = new ArrayList<String>();
		this.ufs = new ArrayList<String>();
		this.bandeiras = new ArrayList<String>();
		this.tiposVendas = new ArrayList<String>();
		this.flagsAcesso = new ArrayList<String>();
		this.departamentos = new ArrayList<String>();
		this.produtos = new ArrayList<String>();
		this.gruposEstoque = new ArrayList<String>();
		this.cliente = new Cliente(driver);
		this.pedido = new Pedido(driver);
		this.produto = new ProdutoPedido(driver);
		this.liberacao = new Liberacao(driver);
		
		// Setando valores para o teste
		this.dataInicio = "25/04/2017";
		this.dataFim = "26/04/2017";
		this.perDesconto = "30,00";
		this.marcarForaLinha = true;
		
		tiposVendas.add("1");
		lojas.add("F059  LAURO DE FREITAS");
		produtos.add("TOCA CD LENOXX AD1826 DVD T. LCD3 USB");
		flagsAcesso.add("Gerente Regional das Lojas");
		gruposEstoque.add("Loja");
	}
	/*
	@Test
	public void testeA() throws InterruptedException {
		
		//lojas.add("F001 - DIVINÓPOLIS II -MG");
		//produtos.add("MINI SYSTEM SAMSUNG MX-J640 200W BIV");
		
		//regionais.add("MACEIO");
		//ufs.add("BAHIA");
		//bandeiras.add("RICARDO ELETRO");
		//tiposVendas.add("2");
		//conceitosCliente.add("PC");
		//departamentos.add("AUDIO_");
		
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		controleMenu.navegar("Administração", "Política Comercial", "Descontos");
		
		// -------------------------------
		//	Criando um Desconto
		// -------------------------------
		System.out.println("Criando um desconto");
		
		pesquisaDesconto.clickBtnCriarDesconto();
		
		desconto.clickVigencia();
		
		popupVigencia.setDataInicio(dataInicio).setDataFim(dataFim).setLoja(lojas)
					.setRegional(regionais).setUf(ufs).setBandeira(bandeiras).clickOk();
		
		desconto.setDescricao("Teste automatizado desconto").setPercentualDesconto(perDesconto).marcarForaLinha(marcarForaLinha)
					.setTipoVenda(tiposVendas).setFlagAcesso(flagsAcesso).setDepartamento(departamentos)
					.setProdutos(produtos).clickSalvar();
		
		Thread.sleep(1000);
		
		// Valida mensagem de registro salvo
		String msg = driver.findElement(By.cssSelector(".alert")).getText();
		
		Assert.assertTrue("O desconto não foi salvo.", "Desconto salvo com sucesso!".equals(msg));
	}
	*/
	@Test
	public void testeB() throws InterruptedException {
		
		IP = "http://10.30.232.18:8080/"; 
		
		this.setup();
		
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		// -------------------------------
		//	Criando um pedido
		// -------------------------------
		System.out.println("Criando um pedido");
		
		// Setando valores para o teste
		String cpf = "000.000.001-91"; // "339.179.793-22";
		String tipoVenda = "1 - A VISTA";
		String codNroProduto = "15066"; // 218355
		
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
		
		produto.setValorProduto(1, "250,00"); // Desconto de 0,01
		
		liberacao.clickAbaLiberacao();
		
		System.out.println(liberacao.getTipoLiberacao(2));
		Assert.assertTrue("A liberação de preço não foi encontrada.", liberacao.getTipoLiberacao(2).equals("LIBERACAO DE PRECO"));
		
		System.out.println(liberacao.getFlagLiberacao(2));
		Assert.assertTrue("A flag 'C' da liberação de preço não foi encontrada.", liberacao.getFlagLiberacao(2).equals("C"));
		
		liberacao.selecionarLiberacao(2).clickBtnLiberar().setObsLiberacao("Teste liberação").clickBtnLiberarLiberacao();
		
		new ValidacaoUsuario(driver).setUsuario("7380").setSenha("1").clickBtnOk();
		
		new Mensagens(driver).clickBtnOkDialog();
		
		Assert.assertTrue("A liberação de preço não foi aprovada.", liberacao.getFlagLiberacao(2).equals("Sim"));
		
		Thread.sleep(2000);
		
		produto.clickAbaProdutos().setValorProduto(1, "639,80"); // Preço acima do máximo
		
		Assert.assertTrue("O valor máximo do produto não foi obedecido.", produto.buscarValorDoProduto(1).equals("639,79"));
	}
	
	@Test
	public void testeConsulta() throws InterruptedException {
		IP = "http://10.30.232.81:8080/"; 
		this.setup();
		
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		controleMenu.navegar("Administração", "Política Comercial", "Descontos");
		
		// -------------------------------
		//	Consultando uma tabela especial
		// -------------------------------
		System.out.println("Consultando uma tabela especial");
		
		pesquisaDesconto.setDataInicio(dataInicio).setLoja(lojas).setTipoVenda(tiposVendas).setGrupoEstoque(gruposEstoque)
						.setFlagAcesso(flagsAcesso).setProdutos(produtos).clickBtnPesquisar();
		
		try {
			driver.findElement(By.cssSelector("table"));
			System.out.println("Resultado encontrado.");
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("O desconto não foi encontrado.");
		}
	}
}