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

public class ElaboraPedidoRoteiro {

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
	
	private static final String IP = "http://10.41.0.32:8080/"; //10.41.0.100  - 10.30.237.21
	private static final String LOJA = "291"; // 6005
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String CPF = "37778670550";
	
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
		
		load.aguardarCarregamentoNovaArquitetura();
		cliente.setCPF(CPF).clickPesquisar();
		
		// Setando valores para o teste
		String tipoVenda = "1 - A VISTA";
		
		// Consulta Cliente
		load.aguardarCarregamentoNovaArquitetura();
		cliente.clickElaborarPedido();
		
		//Thread.sleep(3000);
		load.aguardarCarregamentoNovaArquitetura();
		pedido.setTipoVenda(tipoVenda);
		
		ArrayList<HashMap<String, String>> produtos = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> produto = new HashMap<String, String>();
		produto.put("id_linha", "1");
		produto.put("cod_nro_produto", "139756");
		produto.put("local_empenho", "DEPOSITO");
		produto.put("opcao_entrega", "NORMAL");
		produto.put("empenha_emitindo_mensagem", "true");
		produto.put("ind_promocao_ativa", "true");
		produto.put("ind_sel_promocao", "false");
		produto.put("ind_valida_roteiro", "true");
		produto.put("cod_nro_loja_empenho", "47");
		
		produtos.add(produto);
		
		for (HashMap<String, String> prod : produtos) {
			pedido.adicionarProduto(prod);
		}
		
		driver.close();
	}
	
}