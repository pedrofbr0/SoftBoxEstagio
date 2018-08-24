package br.com.mv.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import br.com.mv.PageFactory.baixaRecibo.PesquisaBackofficeCancelamento;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidaRegraBaixaPrioritaria {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private ControleMenu controleMenu;
	
	private static final String IP = "http://10.41.0.32:8080/"; 
	private static final String LOJA = "47"; // Deposito
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOEXA";
	private static final int NRO_PEDIDO = 55341;
	
	@Before
	public void setup() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.popup_maximum", "9999999");
		profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.agCarregamento = new AguardaCarregamento(driver);
		this.controleMenu = new ControleMenu(driver);
	}
	
	@Test
	public void teste() throws InterruptedException {
		
		// Loga e abre a tela de carga
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		//---------------------------------------
		// REGRA DE BAIXA PRIORITARIA
		//-------------------- v ----------------
		
		controleMenu.navegar("Faturamento", "Troca/Devolução", "Pesquisa de Backoffice de Cancelamentos");
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		PesquisaBackofficeCancelamento pesquisaBackoffice = new PesquisaBackofficeCancelamento(driver);
		pesquisaBackoffice.setCbxEmpresa(DESC_EMPRESA).clickPesquisar();
		
		boolean existePedidoBaixaPrioritaria = pesquisaBackoffice.verificarPedidoComBaixaPrioritaria(NRO_PEDIDO);
		
		Assert.assertEquals("O pedido " + NRO_PEDIDO + " não foi marcado com baixa prioritaria.", true, existePedidoBaixaPrioritaria);
	}
}
