package br.com.mv.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import br.com.mv.PageFactory.Impressao;
import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.relatorio.PopupConferenciaMovimentacaoProduto;
import br.com.mv.PageFactory.relatorio.PopupControleComissao;
import br.com.mv.PageFactory.relatorio.RelatorioComissao;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class RelatorioConferenciaMovimentacaoProduto {

	private WebDriver driver;
	private FirefoxProfile profile;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Toolbar toolbar;
	private PopupConferenciaMovimentacaoProduto popupConferenciaMovProduto;
	private Impressao impressao;
	
	private static final String IP = "http://10.30.232.81:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	
	private static final String ANO_REFERENCIA = "2013";
	
	@Before
	public void setup() {
		/*this.profile = new FirefoxProfile();
		this.profile.setPreference("dom.popup_maximum", "9999999");
		this.profile.setPreference("browser.popups.showPopupBlocker", false);
		this.profile.setPreference("dom.successive_dialog_time_limit", 0);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");*/
		
		//System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chromedriver");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\weverton\\chromedriver2.37\\chromedriver.exe");
		
		this.driver = new ChromeDriver();
		
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.load = new AguardaCarregamento(driver);
		this.menu = new ControleMenu(driver);
		this.controleJanela = new ControleJanela(driver);
		this.toolbar = new Toolbar(driver);
		
		this.popupConferenciaMovProduto = new PopupConferenciaMovimentacaoProduto(driver);
		this.impressao = new Impressao(driver);
	}
	
	@Test
	public void testComissaoEmpresa() throws InterruptedException {
		// Loga no sistema e abre a tela de Relatório de Comissão Sintético
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//load.aguardarCarregamentoNovaArquitetura();
		
		//Thread.sleep(1000);
		menu.navegar("Faturamento", "Relatórios", "Conferência Movimentação Produtos");
		
		Thread.sleep(1000);
		
		int qtdJanelas = driver.findElements(By.tagName("iframe")).size();
		System.out.println("qtdJanelas selecionar Popup: " + qtdJanelas);
		
		driver.switchTo().frame(qtdJanelas - 1);
		
		Thread.sleep(1000);
		popupConferenciaMovProduto.setCodNroLoja(LOJA).clickIconeSearchLoja();
		
		Thread.sleep(1000);
		//controleJanela.abrirPopupClick(popupConferenciaMovProduto.getBtnImprimir(), true);
		popupConferenciaMovProduto.clickBtnImprimir();
		
		
		Thread.sleep(1000);
		controleJanela.abrirPopupClick(popupConferenciaMovProduto.getBtnImprimir(), true);
		int tempoEmissaoRelatorio = 5;
		
		for (int i = 0; i < tempoEmissaoRelatorio; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
		}
		
		load.aguardarCarregamentoArquiteturaAntiga();
		
		try {
			driver.findElement(By.cssSelector("#ifRelatorio"));
			
			controleJanela.fecharJanela(impressao.getBtnFecharRelatorio());
			
			driver.close();
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("O relatório não foi encontrado!");
		}
	}
}