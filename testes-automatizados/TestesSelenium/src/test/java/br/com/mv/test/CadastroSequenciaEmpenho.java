package br.com.mv.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.Rota;
import br.com.mv.PageFactory.cadastro.SequenciaEmpenho;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.dao.cadastro.RotaDAO;
import br.com.mv.dao.cadastro.SequenciaEmpenhoDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class CadastroSequenciaEmpenho {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	
	private SequenciaEmpenho seqEmpenho;
	private SequenciaEmpenhoDAO seqEmpenhoDAO;
	
	private static final String IP = "http://10.30.232.81:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOH EXA";
	
	@Before
	public void setup() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.popup_maximum", "9999999");
		profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.agCarregamento = new AguardaCarregamento(driver);
		this.toolbar = new Toolbar(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.controleJanela = new ControleJanela(driver);
		this.controleMenu = new ControleMenu(driver);
		
		this.seqEmpenho = new SequenciaEmpenho(driver);
		this.seqEmpenhoDAO = new SequenciaEmpenhoDAO();
	}
	
	@Test
	public void teste() throws InterruptedException {
		// Loga e abre a tela de cadastro de caixa
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Cadastros Adm", "Roteirização", "Sequência de Empenho");
		
		seqEmpenhoDAO.removerSequenciaEmpenho();
		
		toolbar.clickNovo();
		
		seqEmpenho.setBase("9221").setSequencia("1").setLojaEmpenho("47").setEmpresaBase("5").setDtInicio("01/01/2010").setDtFim("25/09/2020");
		
		toolbar.clickSalvar();
		
		// Registro salvo com sucesso!
		wait.until(ExpectedConditions.alertIsPresent());
		String msg = driver.switchTo().alert().getText();
		
		Assert.assertTrue("A sequência de empenho não foi salva!", msg.contains("Registro salvo com sucesso"));
		
		driver.quit();
	}
}