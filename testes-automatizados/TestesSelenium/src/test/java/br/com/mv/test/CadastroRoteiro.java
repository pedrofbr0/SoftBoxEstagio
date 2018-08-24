package br.com.mv.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.Rota;
import br.com.mv.PageFactory.cadastro.Roteiro;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.dao.cadastro.RotaDAO;
import br.com.mv.dao.cadastro.RoteiroDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class CadastroRoteiro {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	
	private Roteiro roteiro;
	private RoteiroDAO roteiroDAO;
	
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
		
		this.roteiro = new Roteiro(driver);
		this.roteiroDAO = new RoteiroDAO();
	}
	
	@Test
	public void teste() throws InterruptedException {
		// Loga e abre a tela de cadastro de caixa
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Cadastros Adm", "Roteirização", "Roteiros");
		
		Thread.sleep(2000);
		
		roteiroDAO.removerRoteiro();
		
		roteiro.clickNovo();
		
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		// 221 - CD_221_MG
		roteiro.setCodLoja("221");
		
		roteiro.setUf("MG");
		
		Thread.sleep(3000);
		roteiro.setCidade("IBIRITE");
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		roteiro.setRota("A").setSequencia("1").setDtIntervalo("01/01/2012 - 25/09/2020").setQtdeDiasEntrega("7").setOrigem("LOJA").setEmpresaBase(DESC_EMPRESA);
		
		roteiro.clickFiltroAdicional().setTodosBairros(); //setBairro("JARDIM PATRICIA");
		
		roteiro.clickSalvar();
		
		String msg = driver.findElement(By.cssSelector("div[id^=mdl].modal.in .modal-body")).getText();
		
		Assert.assertTrue("O roteiro não foi salvo.", msg.contains("Roteiro salvo com sucesso!"));
		
		driver.quit();
	}
}