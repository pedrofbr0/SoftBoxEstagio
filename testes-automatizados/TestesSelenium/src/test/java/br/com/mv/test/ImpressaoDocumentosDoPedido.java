package br.com.mv.test;

import java.util.ArrayList;
import java.util.Date;
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
import br.com.mv.PageFactory.pedido.AnaliseCredito;
import br.com.mv.PageFactory.pedido.ConsultaPedido;
import br.com.mv.PageFactory.pedido.ConsultaPreSimplificada;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.Planilha;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.pedido.ServicoPedido;
import br.com.mv.dao.pedido.ConsultaPreSimplificadaDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class ImpressaoDocumentosDoPedido {

	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Cliente cliente;
	private ConsultaPreSimplificada consultaPreSimplificada;
	
	private Pedido pedido;
	private Planilha planilha;
	private ServicoPedido servicoPedido;
	private ProdutoPedido produtoPedido;
	private AnaliseCredito analiseCredito;
	private ConsultaPedido consultaPedido;
	
	private ConsultaPreSimplificadaDAO consultaSimplicadaDAO;
	
	private static String IP = "http://10.41.0.86:8080/"; 
	private static final String LOJA = "6301";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String CPF = "35753185304"; //00000000191
	
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
		this.controleJanela = new ControleJanela(driver);
		this.wait = new WebDriverWait(driver, 20);
		
		this.cliente = new Cliente(driver);
		this.consultaPreSimplificada = new ConsultaPreSimplificada(driver);
		
		this.pedido = new Pedido(driver);
		this.planilha = new Planilha(driver);
		this.produtoPedido = new ProdutoPedido(driver);
		this.servicoPedido = new ServicoPedido(driver);
		this.analiseCredito = new AnaliseCredito(driver);
		this.consultaPedido = new ConsultaPedido(driver);
		
		this.consultaSimplicadaDAO = new ConsultaPreSimplificadaDAO();
		this.consultaSimplicadaDAO.limparRetornoConsultaSimplificada(CPF);
	}
	
	@Test
	public void test() throws InterruptedException {
		// loga no sistema e abre a tela de Pré-Recibos
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		load.aguardarCarregamentoNovaArquitetura2();
		cliente.setCPF(CPF).clickPesquisar();
		
		load.aguardarCarregamentoNovaArquitetura2();
		
		load.aguardarCarregamentoNovaArquitetura();
		cliente.clickCreditoPreAprovado();
		
		// CONSULTA EM PROCESSAMENTO, FAVOR AGUARDAR ALGUNS INSTANTES
		load.aguardarCarregamentoNovaArquitetura();
		
		String msgConsulta = driver.findElement(By.xpath(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '66 - Carne Losango SCRED')]/parent::tr/td[4]")).getText();
		
		System.out.println(msgConsulta);
		
		// !msgConsulta.equals("CONSULTA SIMPLIFICADA PROCESSADA, ACIONAR O BOTÃO [CONSULTAR]") && 
		// !msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")
		/*if (msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok"))); // às vezes nao aparece
			driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
		}*/
		
		// Aguarda o tempo de consulta da avaliação
		for (int i = 0; i < 120; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
		}
		
		consultaPreSimplificada.clickConsultar();
		
		// CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR ou PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!
		load.aguardarCarregamentoNovaArquitetura();
		
		// Elegível
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok")));
		driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
		
		load.aguardarCarregamentoNovaArquitetura();
		consultaPreSimplificada.clickFechar();
	
		// Setando valores para o teste
		String tipoVenda = "66 - CARNE LOSANGO SCRED";
		String plano = "10 X";
		
		// Consulta Cliente
		load.aguardarCarregamentoNovaArquitetura();
		cliente.clickElaborarPedido();
		
		Thread.sleep(3000);
		load.aguardarCarregamentoNovaArquitetura();
		pedido.setTipoVenda(tipoVenda);
		
		ArrayList<HashMap<String, String>> produtos = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> produto = new HashMap<String, String>();
		produto.put("id_linha", "1");
		produto.put("cod_nro_produto", "219254");
		produto.put("local_empenho", "LOJA");
		produto.put("opcao_entrega", "NORMAL");
		produto.put("nro_serie", "0001");
		produto.put("empenha_emitindo_mensagem", "true");
		produto.put("ind_promocao_ativa", "false");
		produto.put("ind_sel_promocao", "false");
		//produto.put("sem_faixa_cep", "true");
		
		produtos.add(produto);
		
		for (HashMap<String, String> prod : produtos) {
			pedido.adicionarProduto(prod);
			
			load.aguardarCarregamentoNovaArquitetura2();
			produtoPedido.empenharProduto(prod);
		}
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.setPlano(plano);
		
		//load.aguardarCarregamentoNovaArquitetura2();
		//servicoPedido.removerTodosServicos();
		
		load.aguardarCarregamentoNovaArquitetura2();
		planilha.adicionarFormaPagamento(1, "F", "Carne Losango", "");
		
		String nroPedido = pedido.getNumeroPedido();
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.clickFechamentoTotalPedido();
		
		// Efetua novo login
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal.in .btn.Sim")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".modal.in .btn.Sim")).click();
		
		/*new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		menu.navegar("Consultar", "Pedido");
		load.aguardarCarregamentoNovaArquitetura();
		
		consultaPedido.setNroPedido(nroPedido).clickBtnConsultarPedido();
		consultaPedido.selecionarPedido(nroPedido);
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.clickBtnAnaliseCredito();*/
		
		// Análise de credito
		//load.aguardarCarregamentoNovaArquitetura();
		//driver.findElement(By.cssSelector(".modal.in .btn.Sim")).click();
		
		load.aguardarCarregamentoNovaArquitetura();
		load.aguardarCarregamentoNovaArquitetura2();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#btn-comunicacao")));
		analiseCredito.clickBtnComunicacao();
		
		load.aguardarCarregamentoNovaArquitetura2();
		analiseCredito.clickCkbIdentidade().clickCkbCpf().clickCkbRenda();
		analiseCredito.setObservacaoComunicacao("Teste automatizado.");
		Thread.sleep(500);
		analiseCredito.clickBtnEnviar();
		
		load.aguardarCarregamentoNovaArquitetura2();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal.in .btn.Ok")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
		
		// Aguarda o tempo de consulta da avaliação
		for (int i = 0; i < 180; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
		}
		
		load.aguardarCarregamentoNovaArquitetura2();
		analiseCredito.clickBtnComunicacao();
		
		load.aguardarCarregamentoNovaArquitetura2();
		analiseCredito.clickBtnConsultaComunicacao();
		
		String mensagemAnalise = driver.findElement(By.cssSelector("div[id^=mdl].modal.in .modal-body")).getText();
		
		System.out.println(mensagemAnalise);
		 
		Assert.assertTrue("A proposta foi negada.", mensagemAnalise.contains("aprovada"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id^=mdl].modal.in .btn.Ok")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div[id^=mdl].modal.in .btn.Ok")).click();
		
		load.aguardarCarregamentoNovaArquitetura();
		analiseCredito.clickBtnFecharAnaliseCredito();
		load.aguardarCarregamentoNovaArquitetura();
		
		//String nroPedido = pedido.getNumeroPedido();
		
		pedido.clickFechar();
		load.aguardarCarregamentoNovaArquitetura();
		
		menu.navegar("Consultar", "Pedido");
		load.aguardarCarregamentoNovaArquitetura();
		
		consultaPedido.setNroPedido(nroPedido).clickBtnConsultarPedido();
		consultaPedido.selecionarPedido(nroPedido);
		
		load.aguardarCarregamentoNovaArquitetura();
		pedido.clickBtnImprimirDocumentos();
		
		load.aguardarCarregamentoNovaArquitetura();
		pedido.setDocumento("COMPROVANTE DE DEBITO").clickBtnModalImprimirRelatorio();
	}
}
