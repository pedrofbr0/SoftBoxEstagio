package br.com.mv.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.emissaoNotaFiscalAvulsa.EmissaoNotaFiscalAvulsa;
import br.com.mv.PageFactory.faturamento.FaturamentoPDV;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.pedido.AnaliseCredito;
import br.com.mv.PageFactory.pedido.ConsultaPedido;
import br.com.mv.PageFactory.pedido.ConsultaPreSimplificada;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.Planilha;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.pedido.ServicoPedido;
import br.com.mv.dao.notafiscal.NotaFiscal;
import br.com.mv.dao.notafiscal.NotaFiscalDAO;
import br.com.mv.dao.pedido.ConsultaPreSimplificadaDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;
import br.com.mv.utils.Utils;

public class NotaFiscalDeAcompanhamento {

	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Cliente cliente;
	private ConsultaPreSimplificada consultaPreSimplificada;
	private NotaFiscalDAO nfDAO;
	private EmissaoNotaFiscalAvulsa nfAvulsa;
	private FaturamentoPDV faturamentoPDV;
	
	private Pedido pedido;
	private Planilha planilha;
	private ServicoPedido servicoPedido;
	private ProdutoPedido produtoPedido;
	private AnaliseCredito analiseCredito;
	private ConsultaPedido consultaPedido;
	
	private ConsultaPreSimplificadaDAO consultaSimplicadaDAO;
	
	private static final String IP = "http://10.30.232.11:8080/"; 
	private static final String LOJA = "4010";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String CPF = "57448469649";
	
	private static int NRO_PEDIDO = 4622412;
	
	// TIPOS NFs
	private static final int TIPO_NF_CUPOM_FISCAL = 1;
	private static final int TIPO_NF_NOTA_FISCAL = 10;
	private static final int TIPO_NF_ACOMPANHAMENTO = 29;
	private static final int TIPO_NF_NFCE = 5;
	
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOH EXA";
	private static final int NRO_LOJA = 1385;
	private static final int COD_NRO_LOJA = 4010;
	
	@Before
	public void setup() throws IOException {
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
		this.faturamentoPDV = new FaturamentoPDV(driver);
		
		this.pedido = new Pedido(driver);
		this.planilha = new Planilha(driver);
		this.produtoPedido = new ProdutoPedido(driver);
		this.servicoPedido = new ServicoPedido(driver);
		this.analiseCredito = new AnaliseCredito(driver);
		this.consultaPedido = new ConsultaPedido(driver);
		
		this.nfDAO = new NotaFiscalDAO();
		this.nfAvulsa = new EmissaoNotaFiscalAvulsa(driver);
		
		String nomeArquivoPedido = System.getenv("NOME_ARQUIVO_PEDIDO");
		System.out.println("Nome Arquivo Pedido: " + nomeArquivoPedido);
		
		String nroPedido = new Utils().lerArquivo("C:\\Temp\\" + nomeArquivoPedido);
		System.out.println("Nro Pedido: " + nroPedido);
		
		//NRO_PEDIDO = 4634532; //Integer.parseInt(nroPedido.trim());
		NRO_PEDIDO = Integer.parseInt(nroPedido.trim());
	}
	
	@Test
	public void test() throws InterruptedException {
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		System.out.println("Pedido: " + NRO_PEDIDO + " faturado com sucesso!");
		
		//---------------------------------------
		// EMISSÃO NF AVULSA - NOTA FISCAL DE ACOMPANHAMENTO
		//-------------------- v ----------------
		
		menu.navegar("Faturamento", "Emissão NF Avulsa");
		load.aguardarCarregamentoArquiteturaAntiga();
		
		String tiposNf = TIPO_NF_CUPOM_FISCAL + ", " + TIPO_NF_NOTA_FISCAL + ", " + TIPO_NF_NFCE;
		
		List<NotaFiscal> cuponsFiscais = nfDAO.getNotaFiscal(NRO_LOJA, NRO_PEDIDO,  tiposNf);
		nfAvulsa.gerarNfAvulsaSaida(cuponsFiscais, DESC_EMPRESA, NRO_PEDIDO, TIPO_NF_ACOMPANHAMENTO);
		
		driver.close();
	}
}