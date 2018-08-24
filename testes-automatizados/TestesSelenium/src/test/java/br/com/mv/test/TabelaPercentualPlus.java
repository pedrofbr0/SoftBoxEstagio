package br.com.mv.test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.cadastro.PercentualPlus;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.dao.pedido.PlusDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleMenu;

public class TabelaPercentualPlus {

	private WebDriver driver;
	private WebDriverWait wait;
	private AguardaCarregamento agCarregamento;
	private ControleMenu controleMenu;
	private PercentualPlus percentualPlus;
	private PlusDAO plusDAO;
	
	private List<String> lojas;
	private String dataInicio, dataFim;
	
	private static final String IP = "http://10.41.0.86:8080/"; //10.30.237.21  
	private static final String LOJA = "6044";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	@Before
	public void setup() throws MalformedURLException {
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
		this.driver.manage().window().maximize();
		
		this.wait = new WebDriverWait(driver, 20);
		this.agCarregamento = new AguardaCarregamento(driver);
		this.controleMenu = new ControleMenu(driver);
		this.lojas = new ArrayList<String>();
		this.percentualPlus = new PercentualPlus(driver);
		this.plusDAO = new PlusDAO();
		
		// Setando valores para o teste
		this.dataInicio = "01/12/2016";
		this.dataFim = "03/12/2016";
		
		lojas.add("F6044 - CAMPO VERDE - MT");
	}
	
	@Test
	public void teste() throws Exception {
		
		// Efetua login
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		
		controleMenu.navegar("Fopag", "Cadastro", "Percentual Plus");
		
		Thread.sleep(1000);
		
		String msg = "";
			
		// -------------------------------
		//	Excluindo uma tabela percentual
		// -------------------------------
		System.out.println("Excluindo uma tabela percentual plus");
		
		percentualPlus.setDataInicio(dataInicio).setDataFim(dataFim).setPesquisaLoja(lojas).clickPesquisar();
		
		//if (this.plusDAO.existeTabelaVigente() == true) {
			
			percentualPlus.clickExcluirTabela();
			
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			
			Thread.sleep(1000);
			
			msg = driver.findElement(By.cssSelector(".alert")).getText();
			
			Assert.assertTrue("A tabela de percentual não foi excluida!", "Registro excluido com sucesso!".equals(msg));
			
		/*} else {
			System.out.println("Não foi encontrada nenhuma tabela com os dados informados!");
		}*/
			
		// -------------------------------
		//	Criando uma tabela percentual
		// -------------------------------
		System.out.println("Criando uma tabela percentual plus");
		
		percentualPlus.clickCriarMatrizPlus();
		
		Thread.sleep(1000);
		
		percentualPlus.setDataInicio(dataInicio).setDataFim(dataFim).setLoja(lojas);
		
		double[][] tabelaPercentualProdutos = { {0.01, 50.0}, {50.01, 100.0} };
		double[][] tabelaPercentualServicos = { {0.01, 50.0}, {50.01, 100.0} };
		double[] tabelaPercentualPlus 		= {  0.02, 0.03, 0.04, 0.05};
		String qtdProd = "2";
		String qtdServ = "2";
		
		percentualPlus.setQtdProd(qtdProd).setQtdServ(qtdServ).clickGerarTabela();
		
		percentualPlus.setTabelaPercentualPlus(tabelaPercentualProdutos, tabelaPercentualServicos, tabelaPercentualPlus, Integer.parseInt(qtdProd), Integer.parseInt(qtdServ));
		
		percentualPlus.clickSalvar();
		
		msg = driver.findElement(By.cssSelector(".alert")).getText();
		
		Assert.assertTrue("A tabela de percentual não foi salva!", "Registro salvo com sucesso!".equals(msg));
		
		driver.quit();
	}
}