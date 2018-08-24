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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.ferstl.junit.testgroups.TestGroup;

import br.com.mv.PageFactory.Impressao;
import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.relatorio.PopupControleComissao;
import br.com.mv.PageFactory.relatorio.RelatorioComissao;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class RelatorioComissaoSintetico {

	private WebDriver driver;
	private FirefoxProfile profile;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Toolbar toolbar;
	private PopupControleComissao popupControleComissao;
	
	private RelatorioComissao relatorioComissao;
	private Impressao impressao;
	
	private static final String IP = "http://10.41.0.32:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	
	private static final String ANO_REFERENCIA = "2013";
	private static final String DT_REF_INICIAL = "18/03/2013";
	private static final String DT_REF_FINAL = "19/04/2013";
	
	@Before
	public void setup() {
		/*this.profile = new FirefoxProfile();
		this.profile.setPreference("dom.popup_maximum", "9999999");
		this.profile.setPreference("browser.popups.showPopupBlocker", false);
		this.profile.setPreference("dom.successive_dialog_time_limit", 0);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");*/
		
		System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chromedriver");
		
		this.driver = new ChromeDriver();
		
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.load = new AguardaCarregamento(driver);
		this.menu = new ControleMenu(driver);
		this.controleJanela = new ControleJanela(driver);
		this.toolbar = new Toolbar(driver);
		
		this.popupControleComissao = new PopupControleComissao(driver);
		this.relatorioComissao = new RelatorioComissao(driver);
		this.impressao = new Impressao(driver);
	}
	
	
	public void testComissaoSintetico() throws InterruptedException {
		// Loga no sistema e abre a tela de Relatório de Comissão Sintético
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//load.aguardarCarregamentoNovaArquitetura();
		
		menu.navegar("Fopag", "Relatório", "Comissão Sintético");
		
		controleJanela.abrirPopupClick(relatorioComissao.getBtnSearchReferencia());
		
		popupControleComissao.setAnoComissao(ANO_REFERENCIA);
		
		toolbar.clickPesquisar();
		
		load.aguardarCarregamentoArquiteturaAntiga();
		popupControleComissao.selecionarMes();
		
		relatorioComissao.setCodNroLoja(LOJA).clickIconeSearchLoja();
		
		Thread.sleep(3000);
		//relatorioComissao.clickBtnImprimir();
		controleJanela.abrirPopupClick(relatorioComissao.getBtnImprimir(), true);
		
		int tempoEmissaoRelatorio = 180;
		
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
	
	@Test
	public void testComissaoSinteticoPorData() throws InterruptedException {
		// Loga no sistema e abre a tela de Relatório de Comissão Sintético
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//load.aguardarCarregamentoNovaArquitetura();
		
		menu.navegar("Fopag", "Relatório", "Comissão Sintético Por Data");
		
		//controleJanela.abrirPopupClick(relatorioComissao.getBtnSearchReferencia());
		
		//popupControleComissao.setAnoComissao(ANO_REFERENCIA);
		popupControleComissao.setDtRefInicial(DT_REF_INICIAL);
		popupControleComissao.setDtRefFinal(DT_REF_FINAL);
		
		//toolbar.clickPesquisar();
		
		load.aguardarCarregamentoArquiteturaAntiga();
		//popupControleComissao.selecionarMes();
		
		relatorioComissao.setCodNroLoja(LOJA).clickIconeSearchLoja();
		
		Thread.sleep(2000);
		controleJanela.abrirPopupClick(relatorioComissao.getBtnImprimir(), true);
		
		int tempoEmissaoRelatorio = 120;
		
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