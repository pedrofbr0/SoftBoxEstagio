package br.com.mv.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.devolucaoProdutos.ObservacaoDevolucaoItem;
import br.com.mv.PageFactory.emissaoNotaFiscalAvulsa.EmissaoNotaFiscalAvulsa;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.preRecibo.PopupAprovacaoPlanilhaDevolucao;
import br.com.mv.PageFactory.preRecibo.PopupCadastroSolicitacaoTD;
import br.com.mv.PageFactory.preRecibo.PopupPreRecibo;
import br.com.mv.PageFactory.preRecibo.PreReciboTrocaDevolucao;
import br.com.mv.dao.notafiscal.NotaFiscal;
import br.com.mv.dao.notafiscal.NotaFiscalDAO;
import br.com.mv.dao.pedido.ItemFluxoAprovacao;
import br.com.mv.dao.pedido.ItemFluxoAprovacaoDAO;
import br.com.mv.dao.pedido.MotivoAprovacaoTrocadevol;
import br.com.mv.dao.pedido.Produto;
import br.com.mv.dao.pedido.ProdutoDAO;
import br.com.mv.dao.pedido.Servico;
import br.com.mv.dao.pedido.ServicoDAO;
import br.com.mv.dao.trocadevolucao.PreReciboDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;
import br.com.mv.utils.Utils;

public class DevolucaoProdutoPagamentoFinanciado {
	
	private WebDriver driver;
	private FirefoxProfile profile;
	private WebDriverWait wait;
	
	private AguardaCarregamento load;
	private ControleMenu menu;
	private ControleJanela controleJanela;
	private Toolbar toolbar;
	private PopupPreRecibo preRecibo;
	private EmissaoNotaFiscalAvulsa nfAvulsa;
	private NotaFiscalDAO nfDAO;
	private ObservacaoDevolucaoItem obsDevolucaoItem;
	private MotivoAprovacaoTrocadevol motivoAprovacao;
	
	private static final String IP = "http://10.41.0.32:8080/"; 
	private static final String LOJA = "291";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOEXA";
	private static final int NRO_LOJA = 1101;
	private static final int COD_NRO_LOJA = 291;
	private static int NRO_PEDIDO = 55692;
	private static final int NRO_EMPRESA = 5;
	
	// regra 5-EXIGE_APROVACAO_FINANCEIRO = 406
	// regra 6-APROVA_REVERSAO_AUTOMATICO = 407
	// regra 3-MARCAR_CONTROLE_BAIXA_PRIORITARIO = 408 - validada por ValidaRegraBaixaPrioritaria
	
	private static final int NRO_MOTIVO_TROCA_DEVOL = 407;
	
	private static final int TIPO_NF_CUPOM_FISCAL = 1;
	private static final int TIPO_NF_NOTA_FISCAL = 10;
	private static final int TIPO_NF_DEVOLUCAO_VENDA = 22;
	private static final int TIPO_NF_DEVOLUCAO_VENDA_MAIS_NOVENTA_DIAS = 23;
	
	private static final String DESC_TIPO_NF_DEVOLUCAO_VENDA = "22 - DEVOLUCAO DE VENDA - UF1280";
	
	private static final int TIPO_FLUXO_APROVACAO = 2; // 1 - Troca, 2 - Devolução, 3 - Ambos 
	private static final String TIPO_FLUXO_APROVACAO_DESC = "Devolucao"; // Troca, Devolucao, Ambos
	
	// status pré-recibo troca/devolução;
	private static final int EM_ELABORACAO = 0;
	private static final int AG_APROVACAO_PRODUTOS = 1;
	private static final int AG_APROVACAO_SERVICOS = 2;
	private static final int AG_APROVACAO_PLANILHA = 3;
	private static final int AG_APROVACAO_REVERSAO = 4;
	private static final int APROVADO = 5;
	private static final int REPROVADO = 6;
	private static final int FINALIZADO = 7;
	
	@Before
	public void setup() {
		this.profile = new FirefoxProfile();
		this.profile.setPreference("dom.popup_maximum", "9999999");
		this.profile.setPreference("browser.popups.showPopupBlocker", false);
		this.driver = new FirefoxDriver(profile);
			
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver.get(IP + "lojas/seguranca?action=login");
		
		this.load = new AguardaCarregamento(driver);
		this.menu = new ControleMenu(driver);
		this.controleJanela = new ControleJanela(driver);
		this.toolbar = new Toolbar(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.preRecibo = new PopupPreRecibo(driver);
		this.nfAvulsa = new EmissaoNotaFiscalAvulsa(driver);
		this.nfDAO = new NotaFiscalDAO();
		this.obsDevolucaoItem = new ObservacaoDevolucaoItem(driver);
		this.motivoAprovacao = new MotivoAprovacaoTrocadevol();
	}
	
	@Test
	public void test() throws InterruptedException, SQLException, IOException {
		
		String nroPedido = new Utils().lerArquivo("C:\\Temp\\030");
		
		System.out.println("Nro Pedido: " + nroPedido);
		
		NRO_PEDIDO = Integer.parseInt(nroPedido.trim());
		
		PreReciboTrocaDevolucao trocaDevolucao = new PreReciboTrocaDevolucao(driver);
		
		PopupCadastroSolicitacaoTD cadSolicitacao = new PopupCadastroSolicitacaoTD(driver);
		ItemFluxoAprovacaoDAO itemAprovacaoDAO = new ItemFluxoAprovacaoDAO();
		PopupAprovacaoPlanilhaDevolucao planilha = new PopupAprovacaoPlanilhaDevolucao(driver);
		
		String tiposNf;

		// loga no sistema e abre a tela de Pré-Recibos
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		load.aguardarCarregamentoNovaArquitetura();
		menu.navegar("Faturamento", "Troca/Devolução", "Pré-Recibos");
		load.aguardarCarregamentoArquiteturaAntiga();
		
		// clica em novo para solcitar uma devolução de um produto de um pedido
		controleJanela.abrirPopupClick(toolbar.getBtnNovo());
		preRecibo.setNroPedido(Integer.toString(NRO_PEDIDO))
									.setNroEmpresa(DESC_EMPRESA).setCodNroLoja(Integer.toString(COD_NRO_LOJA)).setProcesso(TIPO_FLUXO_APROVACAO_DESC);
		
		toolbar.clickPesquisar();
		load.aguardarCarregamentoArquiteturaAntiga();
		
		List<Servico> servicos = new ServicoDAO().getServico(NRO_LOJA, NRO_PEDIDO, 0);
		//removerServicos(servicos); // Devolução no mesmo dia tem q ser total
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", preRecibo.getBtnOk());
		controleJanela.abrirPopupClick(preRecibo.getBtnOk());
		
		List<Produto> produtos = new ProdutoDAO().getProduto(NRO_LOJA, NRO_PEDIDO);
		cadSolicitacao.informarMotivoProduto(produtos, NRO_LOJA, NRO_PEDIDO, NRO_LOJA, TIPO_FLUXO_APROVACAO, NRO_MOTIVO_TROCA_DEVOL);
				
		cadSolicitacao.clickBtnSalvar();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		
		//tela pre-recibo troca/devolução
		controleJanela.voltarJanelaPrincipal();
		
		int nroPreRecibo = new PreReciboDAO().getPreRecibo(NRO_LOJA, NRO_PEDIDO, AG_APROVACAO_PRODUTOS);
		toolbar.clickPesquisar();
		Thread.sleep(1000);
		trocaDevolucao.preRecibo(Integer.toString(nroPreRecibo));
		toolbar.clickPesquisar();
		load.aguardarCarregamentoArquiteturaAntiga();
		trocaDevolucao.aguardar();
		trocaDevolucao.selecionarPreRecibo(nroPreRecibo);
		
		//abrir edição do pré-recibo
		controleJanela.abrirPopupClick(toolbar.getBtnEditar());
		
		List<ItemFluxoAprovacao> itensAprovacao = itemAprovacaoDAO.getItensAprovacao(NRO_LOJA, NRO_PEDIDO);
		
		//---------------------------------------
		// VALIDACAO DE MOTIVO
		//-------------------- v ----------------
		
		cadSolicitacao.validarRegra(NRO_MOTIVO_TROCA_DEVOL, NRO_LOJA, NRO_PEDIDO, Integer.parseInt(USUARIO));
		
		preRecibo.aprovarProdutoPendente(itensAprovacao);
		
		preRecibo.clickOk();
		controleJanela.voltarJanelaPrincipal();
		
		toolbar.clickPesquisar();
		Thread.sleep(1000);
		trocaDevolucao.preRecibo(Integer.toString(nroPreRecibo));
		toolbar.clickPesquisar();
		load.aguardarCarregamentoArquiteturaAntiga();
		trocaDevolucao.aguardar();
		trocaDevolucao.selecionarPreRecibo(nroPreRecibo);
		
		//abrir edição do pré-recibo
		controleJanela.abrirPopupClick(toolbar.getBtnEditar());
		
		load.aguardarCarregamentoArquiteturaAntiga();
		
		System.out.println("AGUARDANDO ANTES DE CLICAR NO BOTÃO PLANILHAS...");
		
		controleJanela.abrirPopupClick(preRecibo.getBtnPlanilhas());
		
		if (planilha.getBtnAprovar().isEnabled() == true) {

			planilha.preecherPlanilhaAprovacao();
			
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			
		} else {
			planilha.setBtnCancelar();
		}
		
		controleJanela.voltarJanelaPrincipal();
		preRecibo.clickOk();
		controleJanela.voltarJanelaPrincipal();
		load.aguardarCarregamentoArquiteturaAntiga();
		
		toolbar.clickPesquisar();
		Thread.sleep(1000);
		trocaDevolucao.preRecibo(Integer.toString(nroPreRecibo));
		toolbar.clickPesquisar();
		load.aguardarCarregamentoArquiteturaAntiga();
		
		//---------------------------------------
		// EMISSÃO NF AVULSA
		//-------------------- v ----------------
		
		menu.navegar("Faturamento", "Emissão NF Avulsa");
		load.aguardarCarregamentoArquiteturaAntiga();
		
		tiposNf = TIPO_NF_CUPOM_FISCAL + ", " + TIPO_NF_NOTA_FISCAL;
		
		List<NotaFiscal> cuponsFiscais = nfDAO.getNotaFiscal(NRO_LOJA, NRO_PEDIDO,  tiposNf);
		nfAvulsa.gerarNfAvulsa(cuponsFiscais, DESC_EMPRESA, NRO_PEDIDO, TIPO_NF_DEVOLUCAO_VENDA);
		
		//---------------------------------------
		// CONTROLE DE DEVOLUÇÃO DE PRODUTOS
		//-------------------- v ----------------
		
		menu.navegar("Faturamento", "Controles", "Devolução de Produtos");
			
		tiposNf = Integer.toString(TIPO_NF_DEVOLUCAO_VENDA);
		List<NotaFiscal> notasFiscais = new NotaFiscalDAO().getNotaFiscal(NRO_LOJA, NRO_PEDIDO, tiposNf);
		obsDevolucaoItem.efetuarDevolucaoProduto(notasFiscais, NRO_LOJA, DESC_TIPO_NF_DEVOLUCAO_VENDA);
	}
	
	/**
	 * Remove todos os serviços existentes no pedido
	 * @param servicos, Lista com os serviços do pedido
	 */
	private void removerServicos(List<Servico> servicos) {
		
		for (Servico serv : servicos) {
			preRecibo.selecionarServico(serv.getNroServico()).clickRemoverServico();
		}
	}

}
