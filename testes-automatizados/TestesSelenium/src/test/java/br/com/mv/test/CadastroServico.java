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
import br.com.mv.PageFactory.cadastro.Servico;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class CadastroServico {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private Servico servico;
	private PopupCadastroOperacaoCaixa popupCadastroOperacaoCaixa;
	
	private static final String IP = "http://10.41.0.32:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
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
		this.servico = new Servico(driver);
		this.popupCadastroOperacaoCaixa = new PopupCadastroOperacaoCaixa(driver);
	}
	
	@Test
	public void teste() throws InterruptedException {
		// Loga e abre a tela de cadastro de caixa
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Administração", "Serviços", "Cadastro [Novo]", "Serviços");
		
		// ************************
		// CADASTRO
		// ************************
		
		toolbar.clickNovo();
		
		servico.setDescricao("Teste aut. serviço").setDescricaoAbreviada("TAS")/*.setOperacaoCaixaCredito("685").setOperacaoCaixaJurosCredito("685")
				.setOperacaoCaixaDebito("685").setOperacaoCaixaJurosDebito("685")*/;
		
		servico.setComboAtivo("Nao").setComboTipoPessoa("Ambas");
		
		controleJanela.abrirPopupClick(servico.getBtnPesquisaTipoServico());
		
		// Preencher tipo serviço
		toolbar.clickPesquisar();
		//servico.selecionarServico();
		Thread.sleep(2000);
		
		controleJanela.fecharJanela(driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]/input")));
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(".//*[@id='imgCenter1_tabbed']/span")).click();
		
		servico.setValorProdutoIni("0,00").setValorProdutoFim("0,00");
		servico.setValorPrestacaoIni("0,00").setValorPrestacaoFim("0,00");
		servico.setValorPedidoIni("0,00").setValorPedidoFim("0,00");
		servico.setQuantidadeInicial("1").setQuantidadeFim("3");
		servico.setPlanoInicial("0").setPlanoFinal("999");
		
		servico.setComboDispConsumidor("Nao").setComboCalculaTAC("Sim").setComboVariarPl("Nao");
		servico.setComboVariarQde("Sim").setComboInfoBancaria("Nao").setComboNroSorteio("Nao").setComboIndGerenciamento("Nao");
		
		servico.setPercentualPrevVenda("50,00").setTipoProdutoFinanceira("36");
		servico.setComboRestricaoAposentado("Nao").setComboVendaExterna("Nao");
		
		servico.setComboIndObrig("Não");
		
		toolbar.clickSalvar();
		
		wait.until(ExpectedConditions.alertIsPresent());
		String msg = driver.switchTo().alert().getText();
		
		Assert.assertTrue("O serviço não foi cadastrado corretamente.", msg.equals("Ao desativar este serviço as associações de Serviço x Loja, Serviço x Tipo Venda e Serviço com Item grupo agregado serão excluídas. Tem certeza que deseja continuar?"));
		
		//Assert.assertTrue("O serviço não foi cadastrado corretamente.", msg.equals("Serviço salvo com sucesso!"));
		
		System.out.println("Serviço salvo com sucesso!");
		
		// Registro salvo
		driver.switchTo().alert().accept();
		
		driver.quit();
	}
}