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

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cadastro.PopupConsultaProduto;
import br.com.mv.PageFactory.emissaoNotaFiscalAvulsa.EmissaoNotaFiscalAvulsa;
import br.com.mv.PageFactory.emissaoNotaFiscalAvulsa.PopupParametros;
import br.com.mv.PageFactory.faturamento.Autorizacao;
import br.com.mv.PageFactory.faturamento.PopupItensAutorizacao;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class NotaFiscalDeAutorizacao {
	
	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Autorizacao autorizacao;
	private Toolbar toolbar;
	private PopupItensAutorizacao itensAutorizacao;
	private PopupConsultaProduto popupConsultaProduto;
	private EmissaoNotaFiscalAvulsa nfAvulsa;
	private PopupParametros parametros;
	
	private static final String IP = "http://10.30.232.81:8080/";
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
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
		this.wait = new WebDriverWait(driver, 20);
		
		this.autorizacao = new Autorizacao(driver);
		this.toolbar = new Toolbar(driver);
		this.itensAutorizacao = new PopupItensAutorizacao(driver);
		this.popupConsultaProduto = new PopupConsultaProduto(driver);
		this.nfAvulsa = new EmissaoNotaFiscalAvulsa(driver);
		this.parametros = new PopupParametros(driver);
	}
	
	@Test
	public void test() throws InterruptedException {
		// Loga no sistema e abre a tela de Pré-Recibos
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		
		menu.navegar("Faturamento", "Cadastros", "Autorização");
		
		// --------------------------
		// CRIANDO NOVA AUTORIZACAO
		// --------------------------
		
		toolbar.clickNovo();
		autorizacao.setTipoAutorizacao("1 - NOTA FISCAL").setLojaDestino("47").setObservacao("Teste").setTipoNotaFiscal("25 - TRANSFERENCIA - UF2120");
		toolbar.clickSalvar();
		
		// Registro salvo com sucesso
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		// Deseja preencher os itens da autorizacao?
		wait.until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(true);
		
		// --------------------------
		// ADICIONANDO PRODUTO NA AUTORIZACAO
		// --------------------------
		
		Thread.sleep(2000);
		
		controleJanela.abrirPopupClick(itensAutorizacao.getBtnInserir());
		
		HashMap<String, String> pesquisaProduto = new HashMap<String, String>();
		//pesquisaProduto.put("departamento", "26 - 03 AUDIO_");
		//pesquisaProduto.put("situacao", "Ativo");
		pesquisaProduto.put("codNroProduto", "16397");
		
		popupConsultaProduto.buscaUmProduto(pesquisaProduto);
		
		itensAutorizacao.setQtdSolicitada("1");
		itensAutorizacao.clickBtnSalvar();
		
		// Item salvo com sucesso
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		controleJanela.fecharJanela(itensAutorizacao.getBtnSair());
		
		String codAutorizacao = autorizacao.getCodigo();
		
		System.out.println("CodAutorização: " + codAutorizacao);
		
		// --------------------------
		// PROCESSANDO AUTORIZACAO
		// --------------------------
		autorizacao.clickBtnProcessar();
		
		// Autorizacao processada com sucesso
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		// --------------------------
		// PESQUISANDO AUTORIZACAO
		// --------------------------
		
		Thread.sleep(1000);
		toolbar.clickPesquisar();
		
		Thread.sleep(3000);
		autorizacao.setCodigo(codAutorizacao).setStatusAutorizacao("Pendente");
		
		Thread.sleep(3000);
		
		toolbar.clickPesquisar();
		
		// --------------------------
		// INFORMANDO A QUANTIDADE AUTORIZADA
		// --------------------------
		
		Thread.sleep(3000);
		autorizacao.selecionarAutorizacao(codAutorizacao);
		
		controleJanela.abrirPopupClick(autorizacao.getBtnItens());
		
		itensAutorizacao.setQtdAutorizada("1");
		itensAutorizacao.clickBtnSalvar();
		
		// Item salvo
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		controleJanela.fecharJanela(itensAutorizacao.getBtnSair());
		
		autorizacao.clickBtnProcessar();
		
		// Autorizacao processada com sucesso
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		autorizacao.selecionarAutorizacao(codAutorizacao);
		
		controleJanela.abrirPopupClick(autorizacao.getBtnProcessar());
		
		// --------------------------
		// GERAR NF AVULSA DE TRANSFERENCIA
		// --------------------------
		
		nfAvulsa.setGrupoEstoqueOrigem("1 - Loja");
		
		controleJanela.abrirPopupClick(nfAvulsa.getBtnInformarParametros());
		
		parametros.setEmpresa("5 - RN COMERCIO VAREJISTA S.A RELOH");
		parametros.setLojaDestino("601 - CD 47 - CD CONTAGEM 1 - MG"); //601 - CD_047_MG
		parametros.setGrupoEstoqueDestino("60 - Transito");
		
		controleJanela.fecharJanela(parametros.getBtnConcluir());
		
		nfAvulsa.clickBtnConcluir();
		
		// Confirma esta nota fiscal?
		wait.until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(true);
	}
}