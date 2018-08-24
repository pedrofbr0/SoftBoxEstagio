package br.com.mv.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.Loja;
import br.com.mv.PageFactory.cadastro.PopupCadastroLogradouro;
import br.com.mv.PageFactory.cadastro.PopupSearchEmpresaLoja;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.preRecibo.PesquisaPreRecibo;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;
import br.com.mv.utils.Utils;

public class CadastroLoja {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private Loja loja;
	private PopupCadastroLogradouro cadastroLogradouro;
	private PopupSearchEmpresaLoja pequisarEmpresaLoja;
	private Utils utils;
	
	private static final String IP = "http://10.41.0.32:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A RELOH";
	
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
		
		this.loja = new Loja(driver);
		this.cadastroLogradouro = new PopupCadastroLogradouro(driver);
		this.pequisarEmpresaLoja = new PopupSearchEmpresaLoja(driver);
		this.utils = new Utils();
		
		Random rand = new Random();
		System.out.println(rand.nextInt());
		System.out.println(Integer.toString(rand.nextInt()).substring(0, 3));
	}
	
	@Test
	public void teste() throws InterruptedException {
		// Loga e abre a tela de cadastro de loja
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Cadastros Adm", "Loja");
		
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		Thread.sleep(6000);
		controleJanela.abrirPopupClick(toolbar.getBtnNovo());
		
		String cnpj = utils.gerarCnpj();

		// ************************
		// ABA DE CAMPOS LOJA
		// ************************
		Random rand = new Random();
		String codNroLoja = Integer.toString(rand.nextInt()).substring(0, 6).replaceAll("-", "");
		
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		loja.setCodigoLoja(codNroLoja).setGrupoRegional("71 - R 03 - MG Metropolitana").setClassificacaoTipoLoja("L").setTipoConfirmacao("SIMPLES").setAvisoSenha("40")
			.setIpServidor("10.32.91.207").setCentroCusto("LOJAS RICARDO ELETRO 2").setTransferencia("Depósito").setPraca("25")
			.setBaseCalculoMVA("").setDescricao("F9999 - TA - " +  codNroLoja).setClassificacao("A").setTipoLoja("Loja").setEmail("9999vendas@maquinadev.com.br")
			.setValidadeSenha("100").setQtdNotas("20").setRenovacaoNF("0").setServidor("0").setLojaEmpenho("Permite somente lojas configuradas.")
			.setCodigoSerieNFLoja("111").setBandeira("1 - RICARDO ELETRO");
		
		controleJanela.abrirPopupClick(loja.getBtnLojaAbastecimento());
		
		pequisarEmpresaLoja.setEmpresa(DESC_EMPRESA).setLoja("47").clickBtnPesquisarLoja();
		
		controleJanela.fecharJanelaVoltarUltimoModal(pequisarEmpresaLoja.getBtnOk()); 
		
		
		// ************************
		// ABA DE ENDEREÇO
		// ************************
		
		loja.tabCadastroLoja("Endereço");
		
		controleJanela.abrirPopupClick(loja.getBtnPesquisarLogradouro());
		
		cadastroLogradouro.setCidade("IBIRITE").setLogradouro("DURVAL DE BARROS");
		toolbar.clickPesquisar();
		controleJanela.fecharJanelaVoltarUltimoModal(cadastroLogradouro.selecionarLogradouro());
		
		loja.setDDD("31").setNumeroFone("32490400").setNumero("1110");
		
		
		// ************************
		// ABA DE GRUPOS
		// ************************
		
		loja.tabCadastroLoja("Grupos");
		
		loja.setGrupoPreco("GRUPO PRECO 0").setGrupoVenda("INATIVO").setGrupoCredito("TESTE").setGrupoParametro("LOJA291RE").setGrupoPromocao("GRANDE BH").setGrupoMensagem("Minas Gerais");
		
		
		// ************************
		// ABA DE PESSOA JURIDICA
		// ************************
		
		loja.tabCadastroLoja("Jurídica");

		
		
		loja.setCnpj(cnpj).setRazaoSocial("RNV- RN COMERCIO VAREJISTA S.A 13.481.30").setFantasia("RN COMERCIO VAREJISTA").setInscricaoEstadual("5343870830043").setInscricaoMunicipal("");
		
		
		// ************************
		// ABA DE ATIVOS
		// ************************
		
		loja.tabCadastroLoja("Ativos");
		
		loja.setAtivo().setVariosEnd().setImprimeDataSaidaDanfe().setVendaManual().setImprimeRecibo().setLojaRE().setPermiteImpressaoContrato();
		
		
		// ************************
		// ABA DE DADOS ADICIONAIS
		// ************************
		
		loja.tabCadastroLoja("Adicionais");
		
		loja.setIdentificadorTEF("50291").setProcessoTributario("ORFM").setQtdDiasValidadeCarga("5").setShipCustomerRef("10301543").setBillAddressRef("10301543")
			.setCodigoLojaMastersaf("50291").setCodigoLojaOrms("50291").setEmiteNfe("Sim").setRecebeEmailNFE("Nao").setTipoFechamentoCaixa("Fechamento Caixa")
			.setShipAddressRef("013481309-0265-82-01").setBillAddressRef("013481309-0265-82-01");

		Thread.sleep(2000);
		
		toolbar.clickSalvar();
		
		// Registro salvo com sucesso!
		wait.until(ExpectedConditions.alertIsPresent());
		String msg = driver.switchTo().alert().getText();
		
		Assert.assertTrue("A loja não foi salva!", msg.contains("Registro salvo com sucesso"));
		
		driver.quit();
	}
}