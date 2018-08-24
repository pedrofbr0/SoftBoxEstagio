package br.com.mv.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unitils.dbunit.annotation.DataSet;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cargaVeiculo.CargaVeiculo;
import br.com.mv.PageFactory.cargaVeiculo.PopupCidadeDestino;
import br.com.mv.PageFactory.cargaVeiculo.PopupVeiculo;
import br.com.mv.PageFactory.cliente.Cliente;
import br.com.mv.PageFactory.controleFaturamentoCarga.ControleFaturamentoCarga;
import br.com.mv.PageFactory.controleFaturamentoCarga.PopupCargaVeiculo;
import br.com.mv.PageFactory.controleFaturamentoCarga.PopupFiltroControleFaturamento;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.login.Logoff;
import br.com.mv.PageFactory.pedido.AnaliseCredito;
import br.com.mv.PageFactory.pedido.ConsultaPedido;
import br.com.mv.PageFactory.pedido.ConsultaPreSimplificada;
import br.com.mv.PageFactory.pedido.Pedido;
import br.com.mv.PageFactory.pedido.Planilha;
import br.com.mv.PageFactory.pedido.ProdutoPedido;
import br.com.mv.PageFactory.pedido.ServicoPedido;
import br.com.mv.dao.notafiscal.NotaFiscal;
import br.com.mv.dao.notafiscal.NotaFiscalDAO;
import br.com.mv.notaFiscalTransferencia.NotaFiscalTransferencia;
import br.com.mv.notaFiscalTransferencia.PopupConsultaNotaFiscalTransferencia;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;
import br.com.mv.utils.Utils;
import br.com.mv.dao.cargaVeiculo.CargaDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class FaturaPedidoEmpenhoDeposito {

	private WebDriver driver;
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private CargaVeiculo cargaVeiculo;
	private ControleFaturamentoCarga controleFatCarga;
	private NotaFiscalDAO nfDAO;
	private Mensagens msg;
	private String nroCarga;
	private int nroNFe;
	
	private Cliente cliente;
	private Pedido pedido;
	private Planilha planilha;
	private ServicoPedido servicoPedido;
	private ProdutoPedido produtoPedido;
	private AnaliseCredito analiseCredito;
	private ConsultaPedido consultaPedido;
	
	private static final String IP = "http://10.30.232.11:8080/";
	private static final String LOJA = "47"; // Deposito
	private static final String USUARIO = "2906";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOH";
	private static final int NRO_LOJA = 1101;
	private static final int COD_NRO_LOJA = 291;
	private static int NRO_PEDIDO = 4622279;
	
	private static final String COD_LOJA = "291";
	
	private static final String CPF = "37778670550";
	
	private static final String COD_LOJA_MINI_CD = "221";
	private static final String NRO_LOJA_MINI_CD = "764";
	
	private static final int NRO_LOJA_DEPOSITO = 601; // 47-Deposito
	
	// TIPOS NFs
	private static final int TIPO_NF_CUPOM_FISCAL = 1;
	private static final int TIPO_NF_NOTA_FISCAL = 10;
		
	// Obs.: Selecionar cidade do cliente via banco
	private static final String NOM_CIDADE = "IBIRITE";
	
	private static final String DESC_CARGA = "TESTE CARGA";
	private static final String NOM_MOTORISTA = "JOAO";
	private static final String NUM_TELEFONE = "3432323232";
	
	@Before
	public void setup() throws IOException {
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
		this.cargaVeiculo = new CargaVeiculo(driver);
		this.controleFatCarga = new ControleFaturamentoCarga(driver);
		this.nfDAO = new NotaFiscalDAO();
		this.msg = new Mensagens(driver);
		
		this.cliente = new Cliente(driver);
		this.pedido = new Pedido(driver);
		this.planilha = new Planilha(driver);
		this.produtoPedido = new ProdutoPedido(driver);
		this.servicoPedido = new ServicoPedido(driver);
		this.analiseCredito = new AnaliseCredito(driver);
		this.consultaPedido = new ConsultaPedido(driver);
		
		String nomeArquivoPedido = System.getenv("NOME_ARQUIVO_PEDIDO");
		System.out.println("Nome Arquivo Pedido: " + nomeArquivoPedido);
		
		String nroPedido = new Utils().lerArquivo("C:\\Temp\\" + nomeArquivoPedido);
		System.out.println("Nro Pedido: " + nroPedido);
		//NRO_PEDIDO = 4634635;
		NRO_PEDIDO = Integer.parseInt(nroPedido.trim());
	}
	
	@Test
	@DataSet("PreparaContextoTrocaDevolucaoTest.xml")
	public void teste() throws InterruptedException {
		
		System.out.println("Pedido: " + NRO_PEDIDO);
		
		//---------------------------------------
		// FATURANDO O PEDIDO Q FOI EMPENHADO EM DEPOSITO
		//-------------------- v ----------------
		
		PopupCidadeDestino cidadeDestino = new PopupCidadeDestino(driver);
		PopupVeiculo veiculo = new PopupVeiculo(driver);
		PopupCargaVeiculo popupCargaVeiculo = new PopupCargaVeiculo(driver);
		String tiposNf;
		
		// Loga e abre a tela de carga
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Faturamento", "Cadastros", "Carga X Veículo");
		
		//---------------------------------------
		// CARGA X VEICULO
		//-------------------- v ----------------
		
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		toolbar.clickNovo();
		
		controleJanela.abrirPopupClick(cargaVeiculo.getBtnSearchCidadeDestino());
		cidadeDestino.preencherCidade(NOM_CIDADE);
		
		controleJanela.abrirPopupClick(cargaVeiculo.getBtnSearchVeiculo());
		veiculo.preecherVeiculo();
		
		cargaVeiculo.setDescCarga(DESC_CARGA).setNomeMotorista(NOM_MOTORISTA).setTelefoneMotorista(NUM_TELEFONE).clickSalvar();
		
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		nroCarga = cargaVeiculo.getNumCarga();
		
		System.out.println("Num carga: " + nroCarga);
		
		//---------------------------------------
		// ASSOCIAÇÃO DE CARGA COM O CONTROLE DE FATURAMENTO - VIA SCRIPT
		//-------------------- v ----------------
		
		nfDAO.associarCargaControleFaturamento(NRO_PEDIDO, NRO_LOJA, Integer.parseInt(nroCarga));
				
		//---------------------------------------
		// FATURAMENTO
		//-------------------- v ----------------
		
		controleMenu.navegar("Faturamento", "Controles", "Faturamento");
		
		ControleFaturamentoCarga controleFatCarga = new ControleFaturamentoCarga(driver);
		controleJanela.abrirPopupClick(controleFatCarga.getBtnSearchCarga());
		
		popupCargaVeiculo.setNumCarga(nroCarga).clickPesquisar();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		popupCargaVeiculo.selecionarCarga(nroCarga);
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		for (int i = 0; i < 50; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
		}
		
		controleJanela.voltarJanelaPrincipal();
		
		controleFatCarga.setTipoFrete("Emitente");
		
		// NÃO EXISTE O PASSO COMENTADO ABAIXO
		/*controleJanela.abrirPopupClick(controleFatCarga.getBtnFiltroControleFaturamento());
		popupFiltroControleFaturamento.setNroPedido(Integer.toString(NRO_PEDIDO));
		
		controleJanela.fecharJanela(popupFiltroControleFaturamento.getConsultar());
		
		controleFatCarga.selecionarControleFaturamento().clickAtribuir();
		
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();*/
		
		controleFatCarga.selecionarControleFaturamento();
		
		controleFatCarga.clickFaturar();
		
		// Deseja realmente iniciar o processo de faturamento ?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		// Deseja informar uma observação para as Nota Fiscais?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
		
		// Notas Fiscais Eletrônicas geradas com sucesso: 271003 (NESSE MOMENTO EH GERADA UMA NOTA DE TRANSFERENCIA)
		wait.until(ExpectedConditions.alertIsPresent());
		String text = driver.switchTo().alert().getText();
		
		driver.switchTo().alert().accept();
		
		// Deseja realizar impressão NFE?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
		
		System.out.println(text);
		
		controleMenu.navegar("Faturamento", "Controles", "Faturamento");
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		// Recupera nro nfe de transferencia
		nroNFe = nfDAO.getNotaFiscalTransferencia(NRO_LOJA, NRO_PEDIDO).getNroNf();
		
		System.out.println(nroNFe);
		
		nfDAO.aprovarNFe(NRO_LOJA_DEPOSITO, nroNFe);
		
		new Logoff(driver).clickSair();
		
		//---------------------------------------
		// NOTAS TRANSFERENCIAS
		//-------------------- v ----------------
		
		// Loga e abre a tela de transferencias
		new Login(driver).loja(COD_LOJA_MINI_CD).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
				
		controleMenu.navegar("Faturamento", "Notas Transferências");
		
		NotaFiscalTransferencia notaFiscalTransf = new NotaFiscalTransferencia(driver);
		controleJanela.abrirPopupClick(notaFiscalTransf.getBtnPesquisarNotaFiscal());
		
		PopupConsultaNotaFiscalTransferencia poupConsultaNotaFiscalTrasf = new PopupConsultaNotaFiscalTransferencia(driver);
		poupConsultaNotaFiscalTrasf.setNumeroNf(Integer.toString(nroNFe)).limparLojaDestino().clickBtnSearchNota();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		poupConsultaNotaFiscalTrasf.selecionarNFe().clickBtnSelecionar();
		
		controleJanela.voltarJanelaPrincipal();
		
		notaFiscalTransf.setGrupoEstoque("40 - Base").setQtdConferida("1").setObservacao().clickProcessar();
		
		// Realizar o processamento para liberação de pré-disponível ?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
		
		// Confirma o processamento desta(s) nota(s) fiscais ?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		// Apareceu um Bloquear janelas de confirmação
		
		wait.until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(false);
		
		msg.clickBtnFechar();
		
		// Procedimento realizado com sucesso!
		controleJanela.voltarJanelaPrincipal(true);
		
		int nroCargaVeiculoTrans = new CargaDAO().getCarga(Integer.parseInt(NRO_LOJA_MINI_CD), NRO_PEDIDO, NRO_LOJA).getNroCargaVeiculo();
		
		System.out.println("Carga transf: " + nroCargaVeiculoTrans);
		
		//---------------------------------------
		// FATURAMENTO - NOTA TRANSFERENCIA
		//-------------------- v ----------------
		
		controleMenu.navegar("Faturamento", "Controles", "Faturamento");
		
		controleJanela.abrirPopupClick(controleFatCarga.getBtnSearchCarga());
		
		popupCargaVeiculo.setNumCarga(Integer.toString(nroCargaVeiculoTrans)).clickPesquisar();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		popupCargaVeiculo.selecionarCarga(Integer.toString(nroCargaVeiculoTrans));
		
		for (int i = 0; i < 50; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
		}
		
		controleJanela.voltarJanelaPrincipal();
		
		controleFatCarga.setTipoFrete("Emitente");
		
		controleFatCarga.clickFaturar();
		
		// Deseja realmente iniciar o processo de faturamento ?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		
		// Deseja informar uma observação para as Nota Fiscais?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
		
		// Notas Fiscais Eletrônicas geradas com sucesso: 271003 (NESSE MOMENTO EH GERADA UMA NOTA DE TRANSFERENCIA)
		wait.until(ExpectedConditions.alertIsPresent());
		text = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		
		System.out.println(text);
		
		// Deseja realizar impressão NFE?
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
		
		tiposNf = TIPO_NF_CUPOM_FISCAL + ", " + TIPO_NF_NOTA_FISCAL;
		
		List<NotaFiscal> cuponsFiscais = nfDAO.getNotaFiscal(NRO_LOJA, NRO_PEDIDO,  tiposNf);
		nroNFe = cuponsFiscais.get(0).getNroNf();
		
		System.out.println(nroNFe);
		
		nfDAO.aprovarNFe(Integer.parseInt(NRO_LOJA_MINI_CD), nroNFe);
	}
}