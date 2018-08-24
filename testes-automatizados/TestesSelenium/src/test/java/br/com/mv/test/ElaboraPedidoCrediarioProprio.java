package br.com.mv.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.pedido.AnaliseCredito;
import br.com.mv.PageFactory.pedido.ConsultaPreSimplificada;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.Planilha;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.pedido.ServicoPedido;
import br.com.mv.dao.pedido.ConsultaPreSimplificadaDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

public class ElaboraPedidoCrediarioProprio {

	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private Cliente cliente;
	private ConsultaPreSimplificada consultaPreSimplificada;
	
	private Pedido pedido;
	private Planilha planilha;
	private ServicoPedido servicoPedido;
	private ProdutoPedido produtoPedido;
	private AnaliseCredito analiseCredito;
	
	private ConsultaPreSimplificadaDAO consultaSimplicadaDAO;
	
	private static final String IP = "http://10.30.232.20:8080/"; //10.41.0.100  - 10.30.237.21
	private static final String LOJA = "1145"; // 6001 6005 //1054 1145
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String CPF = "04392414990"; // "04392414990"; //34902788365 //40719884870
	
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
		this.consultaPreSimplificada = new ConsultaPreSimplificada(driver);
		
		this.pedido = new Pedido(driver);
		this.planilha = new Planilha(driver);
		this.produtoPedido = new ProdutoPedido(driver);
		this.servicoPedido = new ServicoPedido(driver);
		this.analiseCredito = new AnaliseCredito(driver);
		
		this.consultaSimplicadaDAO = new ConsultaPreSimplificadaDAO();
		this.consultaSimplicadaDAO.limparRetornoConsultaSimplificada(CPF);
	}
	
	@Test
	public void test() throws InterruptedException {
		// loga no sistema e abre a tela de Pré-Recibos
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok")));
		load.aguardarCarregamentoNovaArquitetura();
		cliente.setCPF(CPF).clickPesquisar();
		
		// Setando valores para o teste
		String tipoVenda = "76 - CREDCASA";
		//String plano = "10 X";
		String plano = "8 X";
		
		// Consulta Cliente
		load.aguardarCarregamentoNovaArquitetura();
		cliente.clickElaborarPedido();
		
		//Thread.sleep(3000);
		load.aguardarCarregamentoNovaArquitetura();
		pedido.setTipoVenda(tipoVenda);
		
		ArrayList<HashMap<String, String>> produtos = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> produto = new HashMap<String, String>();
		produto.put("id_linha", "1");
		produto.put("cod_nro_produto", "711");
		produto.put("local_empenho", "LOJA");
		produto.put("opcao_entrega", "NORMAL");
		produto.put("empenha_emitindo_mensagem", "true");
		//produto.put("cod_tabela", "70 - TABELA 70");
		
		produtos.add(produto);
		
		//int j = 1;
		
		for (HashMap<String, String> prod : produtos) {
			pedido.adicionarProduto(prod);
			
			produtoPedido.setQtdProduto(1, "5");
			
			load.aguardarCarregamentoNovaArquitetura2();
			produtoPedido.empenharProduto(prod);
			
			/*if (prod.containsKey("cod_tabela")) {
				load.aguardarCarregamentoNovaArquitetura2();
				produtoPedido.setTabelaEspecial(j, prod.get("cod_tabela"));
			}
			
			j++;*/
		}
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.setPlano(plano);
		
		//load.aguardarCarregamentoNovaArquitetura2();
		//servicoPedido.removerTodosServicos();
		
		// Realizar captura de limite
		System.out.println("Realizando captura de limites!");
		
		int qtdTempo = 240; // 3 minutos
		String msgCaptura = "";
		
		for (int i = 0; i <= qtdTempo; i++) {
			Thread.sleep(1000);
			
			if (i == 1 || i % 60 == 0) { // Minuto a minuto
				pedido.clickBtnLimiteCredito();
				
				msgCaptura = driver.findElement(By.cssSelector("div[id^=mdl].in .modal-dialog .modal-body")).getText();
				
				//"Sem informações de captura! Será iniciada uma nova captura! "
				//"Já existe uma captura de limite sendo processada!"
				
				this.clicarOkModal();
				
				System.out.println(msgCaptura +" no minuto: " + i / 60);
				
				//"Consulta concluída! Número máximo de parcelas: -1Percentual de entrada: -100%Carência: Não Categoria: NZ Valor aprovado de parcela: R$50Valor aprovado de crédito: R$450Valor disponível de parcela: R$-9.67Valor disponível total: R$-146.7"
				if (msgCaptura.contains("Consulta concluída")) {
					break;
				}
			}
		}
		
		
		load.aguardarCarregamentoNovaArquitetura2();
		planilha.adicionarFormaPagamento(1, "F", "CARNE CRED PROPRIO", "");
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.clickFechamentoTotalPedido();
		
		Thread.sleep(1000);
		
		// Análise de credito
		load.aguardarCarregamentoNovaArquitetura();
		driver.findElement(By.cssSelector(".modal.in .btn.Sim")).click();
		
		//load.aguardarCarregamentoNovaArquitetura();
		load.aguardarCarregamentoNovaArquitetura2();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#btn-comunicacao")));
		Thread.sleep(1000);
		load.aguardarCarregamentoNovaArquitetura2();
		analiseCredito.clickBtnComunicacao();
		
		load.aguardarCarregamentoNovaArquitetura2();
		analiseCredito.clickCkbIdentidade().clickCkbCpf().clickCkbRenda();
		analiseCredito.setObservacaoComunicacao("Teste automatizado.");
		Thread.sleep(500);
		analiseCredito.clickBtnEnviar();
		
		load.aguardarCarregamentoNovaArquitetura2();
		//Proposta em processamento!
		this.clicarOkModal();
		
		load.aguardarCarregamentoNovaArquitetura2();
		
		String msgRetornoComunicacao = driver.findElement(By.xpath(".//*[@id='grid-financeira-comunicar']/tbody/tr[2]/td[contains(text(), 'Proposta salva na fila.')]")).getText();
		System.out.println(msgRetornoComunicacao);
		
		Assert.assertTrue("A proposta não foi enviada.", msgRetornoComunicacao.contains("Proposta salva na fila."));
		
		driver.close();
		
		// Consulta o retorno da proposta
		//this.consultaRetornoProposta();
	}
	
	public void consultaRetornoProposta() {
		
		String mensagemAnalise = "";
				
		// Aguarda o tempo de consulta da avaliação
		for (int i = 0; i <= 180; i++) {
			System.out.println(new Date());
			
			if (i == 180) {
				load.aguardarCarregamentoNovaArquitetura2();
				analiseCredito.clickBtnComunicacao();
				
				load.aguardarCarregamentoNovaArquitetura2();
				analiseCredito.clickBtnConsultaComunicacao();
			
				mensagemAnalise = driver.findElement(By.cssSelector("div[id^=mdl].modal.in .modal-body")).getText();
				
				if ((mensagemAnalise.contains("Tempo mínimo para consulta") || mensagemAnalise.contains("Proposta em processamento, favor aguardar para realizar uma nova consulta."))) {
					driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
					consultaRetornoProposta();
				}
			}
		}
		
		System.out.println(mensagemAnalise);
		
		Assert.assertTrue("A proposta foi negada.", mensagemAnalise.contains("aprovada"));
	}
	
	public void clicarOkModal() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id^=mdl].in .modal-dialog .modal-footer .Ok")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div[id^=mdl].in .modal-dialog .modal-footer .Ok")).click();
	}
}