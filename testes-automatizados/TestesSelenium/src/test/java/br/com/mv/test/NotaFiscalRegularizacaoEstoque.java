package br.com.mv.test;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unitils.dbunit.annotation.DataSet;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.PopupConsultaProduto;
import br.com.mv.PageFactory.faturamento.RegularizacaoEstoque;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class NotaFiscalRegularizacaoEstoque {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private RegularizacaoEstoque regularizacaoEstoque;
	private PopupConsultaProduto popupConsultaProduto;
	private Mensagens mensagens;
	
	private static final String IP = "http://10.30.232.11:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A RELOH";
	private static final int NRO_LOJA = 1101;
	private static final int COD_NRO_LOJA = 291;
	
	@Before
	public void setup() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.popup_maximum", "9999999");
		profile.setPreference("browser.popups.showPopupBlocker", false);
		profile.setPreference("dom.successive_dialog_time_limit", 0);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.agCarregamento = new AguardaCarregamento(driver);
		this.toolbar = new Toolbar(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.controleJanela = new ControleJanela(driver);
		this.controleMenu = new ControleMenu(driver);
		this.regularizacaoEstoque = new RegularizacaoEstoque(driver);
		this.popupConsultaProduto = new PopupConsultaProduto(driver);
		this.mensagens = new Mensagens(driver);
	}
	
	@Test
	public void teste() throws InterruptedException {
		// Loga e abre a tela de carga
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Faturamento", "Estoque", "Regularização de Estoque");
		
		// --------------------------
		// CRIANDO REGULARIZAÇÃO DE SAÍDA
		// --------------------------
		
		System.out.println("CRIANDO REGULARIZAÇÃO DE SAÍDA");
		
		controleJanela.abrirPopupClick(regularizacaoEstoque.getBtnPesquisarProdutoSaida());
		
		HashMap<String, String> pesquisaProduto = new HashMap<String, String>();
		pesquisaProduto.put("codNroProduto", "711");
		
		popupConsultaProduto.buscaUmProduto(pesquisaProduto);
		
		regularizacaoEstoque.setCbxGESaida("1 - Loja").setTxtQuantidade("1").clickBtnInserir();
		
		regularizacaoEstoque.setTxtObservacao("Teste").setNroDuplicata("123456").setMatricula(USUARIO).setSenhaMatricula(SENHA);
		
		regularizacaoEstoque.clickBtnConcluir();
		
		// Confirmar notas
		//wait.until(ExpectedConditions.alertIsPresent());
		//driver.switchTo().alert().accept();
		
		// Bloquear Janela
		//wait.until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(true);
		
		String msg = mensagens.getMsg();
		
		System.out.println(msg);
		
		Assert.assertTrue("A nota de regularização de saída não foi gerada!", msg.contains("Notas Fiscais Eletrônicas geradas com sucesso"));
		
		mensagens.clickBtnFechar();
		
		controleJanela.voltarJanelaPrincipal(false);
		
		// --------------------------
		// CRIANDO REGULARIZAÇÃO DE ENTRADA
		// --------------------------
		
		System.out.println("CRIANDO REGULARIZAÇÃO DE ENTRADA");
		
		controleJanela.abrirPopupClick(regularizacaoEstoque.getBtnPesquisarProdutoEntrada());
		
		popupConsultaProduto.buscaUmProduto(pesquisaProduto);
		
		regularizacaoEstoque.setCbxGEEntrada("1 - Loja").setTxtQuantidade("1").clickBtnInserir();
		
		regularizacaoEstoque.setTxtObservacao("Teste").setNroDuplicata("123456").setMatricula(USUARIO).setSenhaMatricula(SENHA);
		
		regularizacaoEstoque.clickBtnConcluir();
		
		// Confirmar notas
		//wait.until(ExpectedConditions.alertIsPresent());
		//driver.switchTo().alert().accept();
		
		// Bloquear Janela
		//wait.until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(true);
		
		msg = mensagens.getMsg();
		
		System.out.println(msg);
		
		Assert.assertTrue("A nota de regularização de entrada não foi gerada!", msg.contains("Notas Fiscais Eletrônicas geradas com sucesso"));
		
		mensagens.clickBtnFechar();
		
		controleJanela.voltarJanelaPrincipal(false);
		
		driver.close();
	}
}