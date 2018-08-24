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
import br.com.mv.PageFactory.pedido.ConsultaPreSimplificada;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.Planilha;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.pedido.ServicoPedido;
import br.com.mv.dao.pedido.ConsultaPreSimplificadaDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

public class ElaboraPedidoCarneLosangoPlanos {

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
	
	private static final String IP = "http://10.41.0.86:8080/"; 
	private static final String LOJA = "78";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String CPF = "00000000191";
	private int qtdConsultasPre = 0;
	
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
		
		//---------------------------------
		// CAPTURA SIMPLIFICADA
		//---------------------------------
		load.aguardarCarregamentoNovaArquitetura2();
		
		load.aguardarCarregamentoNovaArquitetura();
		cliente.clickCreditoPreAprovado();
		
		// CONSULTA EM PROCESSAMENTO, FAVOR AGUARDAR ALGUNS INSTANTES
		load.aguardarCarregamentoNovaArquitetura();
		
		this.consultaPreSimplificada();
		
		//---------------------------------
		// ELABORACAO DE PEDIDO
		//---------------------------------
		
		// Setando valores para o teste
		String tipoVenda = "66 - CARNE LOSANGO SCRED";
		//String plano8 = "8 X";
		//String plano10 = "10 X";
		//String plano21 = "21 X";
		
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
		produto.put("cod_tabela", "98 - TABELA 98");
		produto.put("ind_promocao_ativa", "true");
		produto.put("ind_sel_promocao", "false");
		
		produtos.add(produto);
		
		int i = 1;
		
		for (HashMap<String, String> prod : produtos) {
			pedido.adicionarProduto(prod);
			
			load.aguardarCarregamentoNovaArquitetura2();
			produtoPedido.empenharProduto(prod);
			
			if (prod.containsKey("cod_tabela")) {
				load.aguardarCarregamentoNovaArquitetura2();
				produtoPedido.setTabelaEspecial(i, prod.get("cod_tabela"));
			}
			
			i++;
		}
		
		String valorPrestacao = "";
		String valorTotalPedido = "";
		
		ArrayList<HashMap<String, String>> planos = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> plano8 = new HashMap<String, String>();
		plano8.put("plano", "8 X");
		plano8.put("vr_prestacao", "15,91");
		plano8.put("vr_total_pedido", "127,28");
		
		HashMap<String, String> plano10 = new HashMap<String, String>();
		plano10.put("plano", "10 X");
		plano10.put("vr_prestacao", "13,46");
		plano10.put("vr_total_pedido", "134,60");
		
		HashMap<String, String> plano24 = new HashMap<String, String>();
		plano24.put("plano", "24 X");
		plano24.put("vr_prestacao", "8,19");
		plano24.put("vr_total_pedido", "196,56");
		
		planos.add(plano8);
		planos.add(plano10);
		planos.add(plano24);
		
		for (HashMap<String, String> plano : planos) {
			load.aguardarCarregamentoNovaArquitetura2();
			valorPrestacao = pedido.setPlano(plano.get("plano")).getValorPrestacao();
			Assert.assertEquals("O valor da prestação do plano \"" + plano.get("plano") + "\" está incorreto.", valorPrestacao, plano.get("vr_prestacao"));
			
			valorTotalPedido = pedido.getValorTotalPedido();
			Assert.assertEquals("O valor total do pedido para o plano \"" + plano.get("plano") + "\" está incorreto.", valorTotalPedido, plano.get("vr_total_pedido"));
		}
		
		//load.aguardarCarregamentoNovaArquitetura2();
		//servicoPedido.removerTodosServicos();
		
		load.aguardarCarregamentoNovaArquitetura2();
		planilha.adicionarFormaPagamento(1, "F", "Carne Losango", "");
		
		load.aguardarCarregamentoNovaArquitetura2();
		pedido.clickFechamentoTotalPedido();
		
		Thread.sleep(1000);
		
		// Análise de credito
		load.aguardarCarregamentoNovaArquitetura();
		driver.findElement(By.cssSelector("div[id^=mdl].modal.in .modal-dialog .modal-footer .Não")).click();
		
		Thread.sleep(1000);
		pedido.clickBtnAnaliseCredito();
		
		// Envia a proposta
		this.enviarProposta();
		
		// Consulta o retorno da proposta
		this.consultarRetornoProposta();
	}
	
	public void enviarProposta() throws InterruptedException {
		load.aguardarCarregamentoNovaArquitetura();
		load.aguardarCarregamentoNovaArquitetura2();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#btn-comunicacao")));
		analiseCredito.clickBtnComunicacao();
		
		load.aguardarCarregamentoNovaArquitetura2();
		analiseCredito.clickCkbIdentidade().clickCkbCpf().clickCkbRenda();
		analiseCredito.setObservacaoComunicacao("Teste automatizado.");
		
		Thread.sleep(1000);
		analiseCredito.clickBtnEnviar();
		
		load.aguardarCarregamentoNovaArquitetura2();
		
		// Valida msg Proposta em processamento!
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal.in .btn.Ok")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
	}
	
	public void consultaPreSimplificada() throws InterruptedException {
		
		String msgConsulta = driver.findElement(By.xpath(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '66 - Carne Losango SCRED')]/parent::tr/td[4]")).getText();
		String msgAvaliacao = driver.findElement(By.xpath(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '66 - Carne Losango SCRED')]/parent::tr/td[3]")).getText();
		
		System.out.println("Mensagem de consulta: " + msgConsulta);
		System.out.println("Mensagem de avaliação: " + msgAvaliacao);
		
		load.aguardarCarregamentoNovaArquitetura();
		load.aguardarCarregamentoNovaArquitetura2();
		
		/*if (msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok"))); // às vezes nao aparece
			driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
		}*/
		
		// !msgConsulta.equals("CONSULTA SIMPLIFICADA PROCESSADA, ACIONAR O BOTÃO [CONSULTAR]") && 
		// !msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")
		
		/*Thread.sleep(5000);
		
		ExpectedCondition<Boolean> modalAguardeConsultar = ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok"));
		ExpectedCondition<WebElement> elementoModalAguardeConsultar = ExpectedConditions. visibilityOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok"));
		
		System.out.println(modalAguardeConsultar);
		System.out.println(elementoModalAguardeConsultar);
		
		if (!Boolean.TRUE.equals(modalAguardeConsultar) == false) {
		
			System.out.println("Fechando modal!!");
			driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
		}*/
		
		if (!msgAvaliacao.equals("Elegível") && msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")){
			
			if (qtdConsultasPre == 0) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok")));
				driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
			}
			
			// Aguarda o tempo de consulta da avaliação
			for (int i = 0; i <= 120; i++) {
				Thread.sleep(1000);
				
				if (i == 120){
					consultaPreSimplificada.clickConsultar();
					
					// CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR ou PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!
					load.aguardarCarregamentoNovaArquitetura();
					
					msgAvaliacao = driver.findElement(By.xpath(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '66 - Carne Losango SCRED')]/parent::tr/td[3]")).getText();
					
					System.out.println(msgAvaliacao);
					
					if (!msgAvaliacao.equals("Elegível")){
						qtdConsultasPre++;
						consultaPreSimplificada();
						
					} else{
						// PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!
						driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
					}
				}
			}
		}
		
		load.aguardarCarregamentoNovaArquitetura();
		consultaPreSimplificada.clickFechar();
	}
	
	public void consultarRetornoProposta() {
		
		String mensagemAnalise = "";
				
		// Aguarda o tempo de consulta da avaliação
		for (int i = 0; i <= 10; i++) {
			System.out.println(new Date());
			
			if (i == 10) {
				load.aguardarCarregamentoNovaArquitetura2();
				analiseCredito.clickBtnComunicacao();
				
				load.aguardarCarregamentoNovaArquitetura2();
				analiseCredito.clickBtnConsultaComunicacao();
			
				mensagemAnalise = driver.findElement(By.cssSelector("div[id^=mdl].modal.in .modal-body")).getText();
				
				if ((mensagemAnalise.contains("Tempo mínimo para consulta") || mensagemAnalise.contains("Proposta em processamento, favor aguardar para realizar uma nova consulta."))) {
					driver.findElement(By.cssSelector(".modal.in .btn.Ok")).click();
					consultarRetornoProposta();
				}
			}
		}
		
		System.out.println(mensagemAnalise);
		
		Assert.assertTrue("A proposta foi negada.", mensagemAnalise.contains("aprovada"));
	}
}