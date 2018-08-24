package br.com.mv.test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.Loja;
import br.com.mv.PageFactory.cadastro.PopupCadastroLogradouro;
import br.com.mv.PageFactory.cadastro.PopupSearchEmpresaLoja;
import br.com.mv.PageFactory.faturamento.CRE;
import br.com.mv.PageFactory.faturamento.PopupConsultaCrePendente;
import br.com.mv.PageFactory.faturamento.PopupInsercaoCre;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.dao.faturamento.ControleRotativoEstoqueDAO;
import br.com.mv.dao.pedido.ProdutoDAO;
import br.com.mv.dao.trocadevolucao.PreReciboDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;
import br.com.mv.utils.Utils;

public class ControleRotativoEstoque {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private CRE cre;
	private PopupConsultaCrePendente consultaCrePendente;
	private PopupInsercaoCre insercaoCre;
	private ProdutoDAO produtoDAO;
	private ControleRotativoEstoqueDAO creDAO;
	
	private static final String IP = "http://10.41.0.32:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOEXA";
	
	private static final String NRO_LOJA = "1101";
	private static final String NRO_CRE = "187229";
	private static String nroCre;
	private static String statusCre;
	
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
		
		this.cre = new CRE(driver);
		this.consultaCrePendente = new PopupConsultaCrePendente(driver);
		this.insercaoCre = new PopupInsercaoCre(driver);
		this.produtoDAO = new ProdutoDAO();
		this.creDAO = new ControleRotativoEstoqueDAO();
	}
	
	public void logarEAcessarMenu() throws InterruptedException {
		// Loga e abre a tela de cadastro de loja
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Faturamento", "Estoque", "CRE");
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
	}
	
	@Test
	public void testeA() throws InterruptedException {
		this.logarEAcessarMenu();
		
		statusCre = cre.getStatusCre();
		
		System.out.println("Status CRE: " + statusCre);
		
		if (statusCre.equals("PROCESSADO")) {
			// ---------------------------------
			// GERAÇÃO DE CRE
			// ---------------------------------
			System.out.println("... Gerando CRE");
			controleJanela.abrirPopupClick(cre.getBtnGeracao());
			insercaoCre.setTipoGeracaoCre("Normal").clickBtnProcessa();
			
			//Confirma a inclusão deste CRE ?
			driver.switchTo().alert().accept();
			
			//Bloquear janelas de confirmação desta página?
			//driver.switchTo().alert().dismiss();
			//controleJanela.voltarJanelaPrincipal();
		} else {
			System.out.println("... Não foi necessário gerar novo CRE.");
		}
		
		driver.close();
	}
	
	@Test
	public void testeB() throws InterruptedException, IOException {
		this.logarEAcessarMenu();
		
		System.out.println("Nro CRE: " + cre.getNumeroCre());
		
		if (cre.getNumeroCre().isEmpty()) {
			// ---------------------------------
			// PESQUISA POR ALGUM CRE PENDENTE
			// ---------------------------------
			System.out.println("... Pesquisando por algum CRE pendente.");
			controleJanela.abrirPopupClick(cre.getBtnBuscarCrePendente());
			consultaCrePendente.setQtdeRegistros("1").selecionarCrePendente();
			controleJanela.fecharJanela(consultaCrePendente.getBtnSelecionar());
		}
		
		nroCre = cre.getNumeroCre();
		
		Assert.assertTrue("O campo Gerado Por, está vazio.", cre.getUsuarioCre().getText().isEmpty());
		
		// ---------------------------------
		// BUSCA POR ALGUM CRE PENDENTE [NRO FIXO]
		// ---------------------------------
		//cre.setNumeroCre(NRO_CRE).setNumeroLoja(LOJA).clickBtnBuscarCre();
		cre.setNumeroCre(nroCre).setNumeroLoja(LOJA).clickBtnBuscarCre();
		System.out.println("... Buscando CRE");
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		if (statusCre.equals("PROCESSADO")) {
			cre.clickBtnBuscarCre();
		}
		
		List<String> deptos = cre.departamentosCre(Integer.parseInt(cre.getNumeroCre()), Integer.parseInt(NRO_LOJA));
		
		for (String depto : deptos) {
			System.out.println("Departamento: " + depto);
			cre.setDepartamento(depto);
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			
			List<WebElement> itensCre = cre.itensCre();
			
			for (WebElement itemCre : itensCre) {
				
				String codNroProduto = itemCre.findElement(By.xpath("./td[2]")).getText();
				String codCorProduto = itemCre.findElement(By.xpath("./td[3]")).getText();
				String codTipoVoltagem = itemCre.findElement(By.xpath("./td[4]")).getText();
				
				int nroProduto = produtoDAO.getNroProduto(Integer.parseInt(codNroProduto), Integer.parseInt(codCorProduto), codTipoVoltagem);
				System.out.println("codNroProduto: " + codNroProduto + " codCorProduto: " + codCorProduto + " codTipoVoltagem: " + codTipoVoltagem);
				
				int qtdeEstoqueSistema = creDAO.getQtdeEstoqueSistema(Integer.parseInt(cre.getNumeroCre()), Integer.parseInt(NRO_LOJA), nroProduto);
				System.out.println("qtdeEstoqueSistema: " + qtdeEstoqueSistema);
				
				cre.setQtdeEstoqueInformada(itemCre, qtdeEstoqueSistema);
			}
			
			cre.clickBtnAtualizarQuantidades();
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		}
		
		cre.clickBtnProcessa();
		
		// Deseja processar esse CRE?
		driver.switchTo().alert().accept();
		
		// CRE processado com sucesso.
		driver.switchTo().alert().accept();
		
		//String qtdContagem = driver.findElement(By.cssSelector("txtNumeroContagem")).getText();
		
		statusCre = cre.getStatusCre();
		
		System.out.println("Status CRE: " + statusCre);
		
		if (statusCre.equals("NÃO PROCESSADO")) {
			this.testeB();
		}
		
		System.out.println(".. CRE: " + nroCre + " processado!!");
		
		driver.close();
	}
	
	@Test
	public void testeC() throws InterruptedException {
		this.logarEAcessarMenu();
		
		System.out.println(".. Validando o status do CRE.");
		
		cre.setNumeroCre(nroCre).setNumeroLoja(LOJA).clickBtnBuscarCre();
		
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		statusCre = cre.getStatusCre();
		
		Assert.assertEquals("O CRE [" + nroCre + "] não foi processado. Status CRE: " + statusCre, "PROCESSADO", statusCre);
		
		System.out.println("Status CRE: " + statusCre);
		
		driver.close();
	}
}