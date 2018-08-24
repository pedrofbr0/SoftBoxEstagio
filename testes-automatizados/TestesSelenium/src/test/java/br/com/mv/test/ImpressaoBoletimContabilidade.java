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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.ferstl.junit.testgroups.TestGroup;
import com.google.common.annotations.VisibleForTesting;

import br.com.mv.PageFactory.Impressao;
import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.caixa.BoletimContabilidade;
import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.pedido.AnaliseCredito;
import br.com.mv.PageFactory.pedido.ConsultaPreSimplificada;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.Planilha;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.pedido.ServicoPedido;
import br.com.mv.dao.caixa.ControleCaixa;
import br.com.mv.dao.caixa.ControleCaixaDAO;
import br.com.mv.dao.pedido.ConsultaPreSimplificadaDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

@TestGroup("suporteNivel2")
public class ImpressaoBoletimContabilidade {

	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	
	private Toolbar toolbar;
	private BoletimContabilidade boletimContabilidade;
	private Impressao impressao;
	
	private static final String IP = "http://10.41.0.32:8080/"; 
	private static final String LOJA = "291";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	@Before
	public void setup() {
		/*this.profile = new FirefoxProfile();
		this.profile.setPreference("dom.popup_maximum", "9999999");
		this.profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");*/
		
		System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chromedriver");
		
		this.driver = new ChromeDriver();
		
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		this.driver.manage().window().maximize();
		
		this.load = new AguardaCarregamento(driver);
		this.menu = new ControleMenu(driver);
		this.controleJanela = new ControleJanela(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.boletimContabilidade = new BoletimContabilidade(driver);
		this.toolbar = new Toolbar(driver);
		this.impressao = new Impressao(driver);
		
	}
	
	@Test
	public void test() throws InterruptedException {
		// loga no sistema e abre a tela de boletim de contabilidade
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//load.aguardarCarregamentoNovaArquitetura();
		
		menu.navegar("Caixa", "Boletim Contabilidade");
		
		load.aguardarCarregamentoArquiteturaAntiga();
		ControleCaixa controleCaixa = new ControleCaixaDAO().getControleCaixa(Integer.parseInt(LOJA));
		
		toolbar.clickPesquisar();
		
		boletimContabilidade.setNumeroControle(Integer.toString(controleCaixa.getNumeroControle())).setDataAbertura(controleCaixa.getDataAberturaCaixa())
			.setDataFechamento(controleCaixa.getDataFechamentoCaixa());
		
		toolbar.clickPesquisar();
		
		boletimContabilidade.selecionarControleCaixa(Integer.toString(controleCaixa.getNumeroControle()));
		
		controleJanela.abrirPopupClick(boletimContabilidade.getBtnEmitirRelatorio());
		
		Thread.sleep(3000);
		controleJanela.abrirPopupClick(impressao.getBtnImprimir());
		
		Thread.sleep(3000);
		controleJanela.fecharJanela(impressao.getBtnFecharRelatorio());
		//controleJanela.fecharJanela(impressao.getBtnFecharImpressao());
		
		driver.close();
	}
}