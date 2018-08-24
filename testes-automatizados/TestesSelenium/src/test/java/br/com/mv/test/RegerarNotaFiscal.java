package br.com.mv.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.faturamento.ConsultaStatusNFe;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.relatorio.PopupControleComissao;
import br.com.mv.PageFactory.relatorio.RelatorioComissao;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class RegerarNotaFiscal {

	private WebDriver driver;
	private FirefoxProfile profile;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Toolbar toolbar;
	private ConsultaStatusNFe consultaStatusNFe;
	
	private RelatorioComissao relatorioComissao;
	
	private static final String IP = "http://10.30.232.81:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
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
		this.toolbar = new Toolbar(driver);
		
		this.consultaStatusNFe = new ConsultaStatusNFe(driver);
	}
	
	@Test
	public void test() throws InterruptedException {
		// Loga no sistema e abre a tela de Relatório de Comissão Sintético
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		Thread.sleep(1000);
		menu.navegar("Faturamento", "NFE", "Consultar Status NFE");
		
		//String numeroNf = "1964";
		
		load.aguardarCarregamentoArquiteturaAntiga();
		//setNumeroNf(numeroNf).
		consultaStatusNFe.setFiltroPendentes(false).setComboAcao("Regerar Arquivo NFE").clickBtnPesquisar().selecionaNotaFiscal().clickBtnAcao();
	}
}