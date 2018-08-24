package br.com.mv.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.Caixa;
import br.com.mv.PageFactory.cadastro.PopupCadastroOperacaoCaixa;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class CadastroCaixa {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private Caixa caixa;
	private PopupCadastroOperacaoCaixa popupCadastroOperacaoCaixa;
	
	private static final String IP = "http://10.41.0.32:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOEXA";
	
	@Before
	public void setup() {
		/*FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.popup_maximum", "9999999");
		profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");*/

		System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chromedriver");
		
		this.driver = new ChromeDriver();
		
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.agCarregamento = new AguardaCarregamento(driver);
		this.toolbar = new Toolbar(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.controleJanela = new ControleJanela(driver);
		this.controleMenu = new ControleMenu(driver);
		this.caixa = new Caixa(driver);
		this.popupCadastroOperacaoCaixa = new PopupCadastroOperacaoCaixa(driver);
	}
	
	@Test
	public void teste() throws InterruptedException {
		// Loga e abre a tela de cadastro de caixa
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Caixa", "Cadastros", "Caixa");
		
		String descricao = "TESTE AUTOMATIZADO CAIXA LOJA 259";
		String codLoja = "259";
		String descLoja = "CD_0259_BA";
		
		// ************************
		// CADASTRO
		// ************************
		
		toolbar.clickNovo();
		
		caixa.setDescricao(descricao).setCodLoja(codLoja).clickSearchLoja();
		
		Thread.sleep(2000);
		controleJanela.abrirPopupClick(caixa.getBtnPesquisarOperacaoContaCredito());
		toolbar.clickPesquisar();
		controleJanela.fecharJanela(popupCadastroOperacaoCaixa.selecionarOperacaoCaixa());
		
		controleJanela.abrirPopupClick(caixa.getBtnPesquisarOperacaoCaixaCredito());
		toolbar.clickPesquisar();
		controleJanela.fecharJanela(popupCadastroOperacaoCaixa.selecionarOperacaoCaixa());
		
		toolbar.clickSalvar();
		
		wait.until(ExpectedConditions.alertIsPresent());
		String msg = driver.switchTo().alert().getText();
		
		Assert.assertTrue("O caixa não foi cadastrado corretamente.", msg.equals("Registro salvo com sucesso!"));
		
		System.out.println("Caixa salvo com sucesso!");
		
		// Registro salvo
		driver.switchTo().alert().accept();

		
		// ************************
		// EXCLUSÃO
		// ************************
		
		toolbar.clickPesquisar();
		caixa.setDescricao(descricao);
		toolbar.clickPesquisar();
		
		caixa.selecionarCaixa(descricao, descLoja);
		toolbar.clickRemover();
		
		// Deseja remover?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		// Removido com sucesso
		wait.until(ExpectedConditions.alertIsPresent());
		String msgRemocao = driver.switchTo().alert().getText();
		
		Assert.assertTrue("O caixa não foi removido corretamente.", msgRemocao.equals("Registro removido com sucesso!"));

		System.out.println("Caixa removido com sucesso!");
		
		// Registro removido
		driver.switchTo().alert().accept();
	}
}