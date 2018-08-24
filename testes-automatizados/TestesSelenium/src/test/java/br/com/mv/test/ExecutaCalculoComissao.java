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

import br.com.mv.PageFactory.EnvioEmail;
import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.relatorio.CalculoComissao;
import br.com.mv.PageFactory.relatorio.PopupConsultaExecucaoCalculo;
import br.com.mv.PageFactory.relatorio.PopupControleComissao;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class ExecutaCalculoComissao {

	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Toolbar toolbar;
	private CalculoComissao calculoComissao;
	private PopupControleComissao popupControleComissao;
	private EnvioEmail envioEmail;
	private PopupConsultaExecucaoCalculo popupConsultaExecucaoCalculo;
	
	private static final String IP = "http://10.41.0.101:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String ANO_REFERENCIA = "2013";
	
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
		this.menu = new ControleMenu(driver);
		this.controleJanela = new ControleJanela(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.toolbar = new Toolbar(driver);
		this.envioEmail = new EnvioEmail(driver);
		
		this.calculoComissao = new CalculoComissao(driver);
		this.popupControleComissao = new PopupControleComissao(driver);
		this.popupConsultaExecucaoCalculo = new PopupConsultaExecucaoCalculo(driver);
	}
	
	@Test
	public void test() throws InterruptedException {
		// Loga no sistema e abre a tela de Cálculo de Comissão
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		menu.navegar("Fopag", "Cálculo", "Cálculo Comissão");
		
		controleJanela.abrirPopupClick(calculoComissao.getBtnSearchReferencia());
		
		popupControleComissao.setAnoComissao(ANO_REFERENCIA);
		
		toolbar.clickPesquisar();
		
		load.aguardarCarregamentoArquiteturaAntiga();
		popupControleComissao.selecionarMes();
		
		calculoComissao.clickBtnCalcular();
		
		// Tem certeza que deseja calcular comissões deste período/referência?
		wait.until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(true);
		
		envioEmail.setObsHistorico("teste@teste.com.br").clickBtnOk();
		
		int tempoCalculoComissao = 180;
		
		for (int i = 0; i < tempoCalculoComissao; i++) {
			Thread.sleep(1000);
		}
		
		// Consultar status do cálculo de comissão [APÓS AGUARDAR A EXECUÇÃO - VERIFICAR O TEMPO MÍNIMO PARA CONSULTA]
		controleJanela.abrirPopupClick(calculoComissao.getBtnStatusProcessos());
		
		String status = popupConsultaExecucaoCalculo.getStatus();
		
		Assert.assertTrue("O cálculo de comissões não foi realizado.", status.equals("Finalizado"));
	}
}
