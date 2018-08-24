package br.com.mv.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.relatorio.AberturaControleComissao;
import br.com.mv.PageFactory.relatorio.ComissaoAnaliticoEspecial;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class RelatorioComissaoAnaliticoEspecial {

	private WebDriver driver;
	private WebDriverWait wait;
	private FirefoxProfile profile;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private AberturaControleComissao aberturaControleComissao;
	private ComissaoAnaliticoEspecial comissaoAnaliticoEspecial;
	
	private static final String IP = "http://10.30.232.81:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	
	private static final String ANO_REFERENCIA = "2013";
	private static final String MES_REFERENCIA = "03";
	private static final String MES_GRID_REFERENCIA = "3";
	
	private static final String NRO_USUARIO = "9018408";
	
	@Before
	public void setup() {
		this.profile = new FirefoxProfile();
		this.profile.setPreference("dom.popup_maximum", "9999999");
		this.profile.setPreference("browser.popups.showPopupBlocker", false);
		this.profile.setPreference("dom.successive_dialog_time_limit", 0);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.load = new AguardaCarregamento(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.menu = new ControleMenu(driver);
		this.controleJanela = new ControleJanela(driver);
		
		this.aberturaControleComissao = new AberturaControleComissao(driver);
		this.comissaoAnaliticoEspecial = new ComissaoAnaliticoEspecial(driver);
	}
	
	@Test
	public void testComissaoAnaliticoEspecial() throws InterruptedException {
		// Loga no sistema e abre a tela de Relatório de Comissão Sintético
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		Thread.sleep(1000);
		menu.navegar("Fopag", "Relatório", "Comissão Analítico Especial");
		
		comissaoAnaliticoEspecial.clickPesquisarReferencia();
		
		aberturaControleComissao.setMesAnoComissao(MES_REFERENCIA + "/" + ANO_REFERENCIA);
		
		Thread.sleep(3000);
		aberturaControleComissao.clickBtnPesquisar();
		
		load.aguardarCarregamentoNovaArquitetura();
		aberturaControleComissao.selecionarAnoMes(ANO_REFERENCIA, MES_GRID_REFERENCIA);
		
		comissaoAnaliticoEspecial.setCodFuncionario(NRO_USUARIO);
		
		Thread.sleep(1000);
		//controleJanela.abrirPopupClick(comissaoAnaliticoEspecial.getBtnImprimir());
		comissaoAnaliticoEspecial.clickBtnImprimir();
		
		//Thread.sleep(2000);
		// Tem certeza que deseja calcular comissões deste período/referência?
		//wait.until(ExpectedConditions.alertIsPresent());
		//driver.switchTo().alert().accept();
		//controleJanela.abrirPopupAlert(true);
		
	}
}
