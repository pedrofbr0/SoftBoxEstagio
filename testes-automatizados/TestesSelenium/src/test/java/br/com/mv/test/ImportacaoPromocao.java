package br.com.mv.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.ImportacaoPromo;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;
import br.com.mv.utils.Utils;

public class ImportacaoPromocao {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	
	private ImportacaoPromo importacaoPromo;
	
	private static final String IP = "http://10.30.232.80:8080/";
	private static final String LOJA = "6044"; 
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "DISMOBRAS IMP EXP DIS MOV ELET - MVSH";
	
	private static String PATH_ARQUIVO_IMPORTACAO = System.getProperty("user.dir") + "\\src\\test\\resources\\br\\com\\mv\\test\\";
	
	@Before
	public void setup() {
		/*FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.popup_maximum", "9999999");
		profile.setPreference("browser.popups.showPopupBlocker", false);
		
		this.driver = new FirefoxDriver(profile);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");*/
		
		//System.setProperty("webdriver.chrome.driver", "/opt/google/chrome/chromedriver");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\weverton\\chromedriver2.37\\chromedriver.exe");
		
		this.driver = new ChromeDriver();
		
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.agCarregamento = new AguardaCarregamento(driver);
		this.toolbar = new Toolbar(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.controleJanela = new ControleJanela(driver);
		this.controleMenu = new ControleMenu(driver);
		
		this.importacaoPromo = new ImportacaoPromo(driver);
		
		String sistemaOperacional = System.getProperty("os.name");
		
		if (sistemaOperacional.equals("Linux")) {
			PATH_ARQUIVO_IMPORTACAO = System.getProperty("user.dir") + "/src/test/resources/br/com/mv/test/";
		}
	}
	
	@Test
	public void teste() throws InterruptedException {
		// Loga e abre a tela de cadastro de caixa
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Consultar", "Promoção", "Importação de Promoção");
		
		System.out.println(System.getProperty("user.dir"));
		
		Thread.sleep(2000);
		
		importacaoPromo.clickBtnNovaImportacao();
		
		String dataAtual = new Utils().getDataAtual();
		
		System.out.println("Data da importação: " + dataAtual);
		
		importacaoPromo.setDescPromocao("desc promoção").setDtInicio(dataAtual).setDtFim(dataAtual).clickPermiteVariarPlano()
			.clickTipoPraca().clickTipoItem().clickVisualizaAlerta().clickReplicaProdutoPai();
		
		importacaoPromo.setTipoPromocao("TABLOIDE");
		
		Thread.sleep(1000);
		
		// -----------------------------------
		// Validação de erro de preenchimento
		// -----------------------------------
		
		String arqErroInicial = "Importação de Promoção - Erro inicial.xlsx";
		
		importacaoPromo.setArquivo(PATH_ARQUIVO_IMPORTACAO + arqErroInicial);
		
		importacaoPromo.clickBtnValidarArquivo();
		
		String msg = driver.findElement(By.xpath(".//*[@id='divFormulario']/form/*/*/fieldset/div[2]")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("O arquivo não foi validado corretamente.", msg.contains("4 erro(s) de preenchimento"));
		
		// -----------------------------------
		// Validação de erro de inconsistência
		// -----------------------------------
		
		Thread.sleep(4000);
		
		String arqErroProcessamento = "Importação de Promoção - Erro de Processamento.xlsx";
		
		importacaoPromo.setArquivo(PATH_ARQUIVO_IMPORTACAO + arqErroProcessamento);
		
		Thread.sleep(1000);
		importacaoPromo.clickBtnValidarArquivo();
		
		// Validação de inconsistência de dados executando em segundo plano. O processo pode ser acompanhado na tela de pesquisa com código de importação: 258
		
		msg = driver.findElement(By.cssSelector("#divFormulario .alert")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("Erro ao validar a inconsistência do arquivo.", msg.contains("Validação de inconsistência de dados executando em segundo plano. O processo pode ser acompanhado na tela de pesquisa com código de importação:"));
		
		String nroImportacao = msg.split(":")[1].trim();
		
		System.out.println("Nro importação: " + nroImportacao);
		
		// -----------------------------------
		// Validação pesquisa de arquivo de importação de promoção
		// -----------------------------------

		Thread.sleep(1000);
		importacaoPromo.clickPesquisar();
		
		importacaoPromo.setCodPromocao(nroImportacao);
		
		Thread.sleep(1000);
		importacaoPromo.setStatusPesquisa("Validação concluída");
		
		importacaoPromo.clickPesquisarImportacao();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//a[contains(text() , '" + nroImportacao + "')]")).click();
		
		// Valida mensagem de erro de inconsistencia
		msg = driver.findElement(By.xpath(".//*[@id='divFormulario']/form/*/*/fieldset/div[2]/div[1]")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A mensagem de erro de inconsistência de dados não foi apresentada.", msg.contains("5 erro(s) de inconsistência de dados"));
		
		// Valida mensagem de alerta de inconsistencia
		msg = driver.findElement(By.xpath(".//*[@id='divFormulario']/form/*/*/fieldset/div[2]/div[2]")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A mensagem de alerta inconsistência de dados não foi apresentada.", msg.contains("0 alerta(s) de inconsistência de dados"));
		
		// -----------------------------------
		// Validação importação com sucesso
		// -----------------------------------
		
		String arqValido = "Importação de Promoção - Arquivo Válido.xlsx";
		
		importacaoPromo.setArquivo(PATH_ARQUIVO_IMPORTACAO + arqValido);
		
		Thread.sleep(1000);
		importacaoPromo.clickBtnValidarArquivo();
		
		// Validação de inconsistência de dados executando em segundo plano. O processo pode ser acompanhado na tela de pesquisa com código de importação: 258
		
		msg = driver.findElement(By.cssSelector("#divFormulario .alert")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("Erro ao validar a inconsistência do arquivo", msg.contains("Validação de inconsistência de dados executando em segundo plano. O processo pode ser acompanhado na tela de pesquisa com código de importação:"));
		
		// Validacao final
		Thread.sleep(1000);
		importacaoPromo.clickPesquisar();
		
		importacaoPromo.setCodPromocao(nroImportacao);
		
		Thread.sleep(1000);
		importacaoPromo.setStatusPesquisa("Validação concluída");
		
		importacaoPromo.clickPesquisarImportacao();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//a[contains(text() , '" + nroImportacao + "')]")).click();
		
		// Valida mensagem de erro de inconsistencia
		msg = driver.findElement(By.xpath(".//*[@id='divFormulario']/form/*/*/fieldset/div[2]/div[1]")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A mensagem de erro de inconsistência de dados não foi apresentada.", msg.contains("0 erro(s) de inconsistência de dados"));
		
		// Valida mensagem de alerta de inconsistencia
		msg = driver.findElement(By.xpath(".//*[@id='divFormulario']/form/*/*/fieldset/div[2]/div[2]")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A mensagem de alerta inconsistência de dados não foi apresentada.", msg.contains("0 alerta(s) de inconsistência de dados"));
		 
		// Efetivar Importação
		Thread.sleep(1000);
		importacaoPromo.clickEfetivarImportacao();
		
		Thread.sleep(1000);
		msg = driver.findElement(By.cssSelector("#divFormulario .alert")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A promoção não foi efetivada.", msg.contains("Promoção sendo efetivada em segundo plano. O processo pode ser acompanhado na tela de pesquisa com código de importação:"));
		
		// Validacao final
		importacaoPromo.clickPesquisar();
		
		importacaoPromo.setCodPromocao(nroImportacao);
		
		Thread.sleep(1000);
		importacaoPromo.setStatusPesquisa("Importação concluída");
		
		importacaoPromo.clickPesquisarImportacao();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//a[contains(text() , '" + nroImportacao + "')]")).click();
		
		Thread.sleep(1000);
		msg = driver.findElement(By.cssSelector("#divFormulario .alert")).getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A importação da promoção não foi realizada.", msg.contains("Importação da promoção concluída com sucesso. A nova promoção gerada possui o código:"));
		
		driver.quit();
	}
}