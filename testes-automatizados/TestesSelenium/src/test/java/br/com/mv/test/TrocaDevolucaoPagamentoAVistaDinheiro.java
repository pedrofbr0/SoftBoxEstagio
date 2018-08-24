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
import br.com.mv.PageFactory.preRecibo.PopupCadastroSolicitacaoTD;
import br.com.mv.PageFactory.preRecibo.PopupPreRecibo;
import br.com.mv.PageFactory.preRecibo.PreReciboTrocaDevolucao;
import br.com.mv.dao.notafiscal.NotaFiscal;
import br.com.mv.dao.notafiscal.NotaFiscalDAO;
import br.com.mv.dao.pedido.ItemFluxoAprovacao;
import br.com.mv.dao.pedido.ItemFluxoAprovacaoDAO;
import br.com.mv.dao.pedido.Produto;
import br.com.mv.dao.pedido.ProdutoDAO;
import br.com.mv.dao.pedido.Servico;
import br.com.mv.dao.pedido.ServicoDAO;
import br.com.mv.dao.trocadevolucao.PreReciboDAO;
import br.com.mv.dao.trocadevolucao.TipoVendaRegraTrocaDevolucaoDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;
import br.com.mv.utils.Utils;

/**
 * TesteTrocaDevolucao
 * 
 * @author Antonio Júnior <antoniojunior@softbox.com.br>
 *
 */
public class TrocaDevolucaoPagamentoAVistaDinheiro {
	
	private WebDriver driver;
	
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private ObservacaoDevolucaoItem obsDevolucaoItem;
	private PopupPreRecibo preRecibo;
	private PopupCadastroSolicitacaoTD cadSolicitacao;
	private NotaFiscalDAO nfDAO;
	private EmissaoNotaFiscalAvulsa nfAvulsa;
	
	private static final String IP = "http://10.41.0.101:8080/";//"http://10.41.0.100:8080/"; 
	private static final String LOJA =  "291"; //"1054";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOH EXA"; // "18 - HOMOLOGACAO LOJAS SALFER S/A. 18";
	private static final int NRO_LOJA = 1101; //321;
	private static final int COD_NRO_LOJA = 291; //1054;
	private static final String COD_LOJA_MINI_CD = "221";

	private static int NRO_PEDIDO = 4633828; //57885; //4633442 
	
	// regra 4-ALERTAR_AUTOMATICAMENTE_PDVS_REC_DEV_PENDENTE; NRO_MOTIVO_TROCA_DEVOL = 404
	// regra 2-LIBERAR_EMISSAO_RECIBO_DEVOLUCAO_SEM_NF_DEV_CONFIRMADA - validada via PDV; NRO_MOTIVO_TROCA_DEVOL = 409
	// regra 8-NAO_PERMITE_DEVOLUCAO = 411
	
	private static int NRO_MOTIVO_TROCA_DEVOL = 409;
	private static final int NRO_MOTIVO_TROCA_LIBERA_EMISSAO_RECIBO = 409;
	private static final int NRO_MOTIVO_NAO_PERMITE_DEVOLUCAO = 411;
	
	private static final int NRO_TIPO_VENDA_AVISTA = 1;
	private static final int NRO_SEQ_REGRA_NAO_PERMITE_DEVOLUCAO = 8;
	
	// TIPOS NFs
	private static final int TIPO_NF_CUPOM_FISCAL = 1;
	private static final int TIPO_NF_NOTA_FISCAL = 10;
	private static final int TIPO_NF_DEVOLUCAO_VENDA = 22;
	
	private static final String DESC_TIPO_NF_DEVOLUCAO_VENDA = "22 - DEVOLUCAO DE VENDA - UF1280";
	
	//TIPOS FLUXO APROVAÇÃO
	private static final int TIPO_FLUXO_APROVACAO = 1; // 1 - Troca, 2 - Devolução, 3 - Ambos 
	private static final String TIPO_FLUXO_APROVACAO_DESC = "Troca"; // Troca, Devolucao, Ambos
	
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
		this.obsDevolucaoItem = new ObservacaoDevolucaoItem(driver);
		this.preRecibo = new PopupPreRecibo(driver);
		this.cadSolicitacao = new PopupCadastroSolicitacaoTD(driver);
		this.nfDAO = new NotaFiscalDAO();
		this.nfAvulsa = new EmissaoNotaFiscalAvulsa(driver);
		
		String nroMotivoTrocaDevol = "409"; //System.getenv("NRO_MOTIVO_TROCA_DEVOL");
		System.out.println("Nro Motivo Troca/Devol: " + nroMotivoTrocaDevol);
		NRO_MOTIVO_TROCA_DEVOL = Integer.parseInt(nroMotivoTrocaDevol.trim());
		
		/*NOME_ARQUIVO_PEDIDO=032_regra_2
				NRO_MOTIVO_TROCA_DEVOL=409*/
		
		String nomeArquivoPedido = "032_regra_4"; //System.getenv("NOME_ARQUIVO_PEDIDO");
		System.out.println("Nome Arquivo Pedido: " + nomeArquivoPedido);
		
		String nroPedido = "4633830"; //new Utils().lerArquivo("C:\\Temp\\" + nomeArquivoPedido);
		System.out.println("Nro Pedido: " + nroPedido);
		NRO_PEDIDO = Integer.parseInt(nroPedido.trim());
	}
	
	@Test
	public void testeTrocaDevolucao() throws InterruptedException, SQLException {
		
		PreReciboTrocaDevolucao trocaDevolucao = new PreReciboTrocaDevolucao(driver);
		ItemFluxoAprovacaoDAO itemAprovacaoDAO = new ItemFluxoAprovacaoDAO();
		
		String tiposNf;
		
		// Pré-condição para a regra NAO_PERMITE_DEVOLUCAO
		if (NRO_MOTIVO_TROCA_DEVOL == NRO_MOTIVO_NAO_PERMITE_DEVOLUCAO) {
			new TipoVendaRegraTrocaDevolucaoDAO().inserirTipoVendaRegraTrocaDevolucao(NRO_TIPO_VENDA_AVISTA, NRO_SEQ_REGRA_NAO_PERMITE_DEVOLUCAO);
		}
				
		//loga e abre a tela de pre-recibo
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Faturamento", "Troca/Devolução", "Pré-Recibos");
		
		//novo pre-recibo
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		trocaDevolucao.aguardar();
		controleJanela.abrirPopupClick(toolbar.getBtnNovo());
		
		//popup novo pre-recibo
		preRecibo.setNroPedido(Integer.toString(NRO_PEDIDO)).setNroEmpresa(DESC_EMPRESA).
								setCodNroLoja(Integer.toString(COD_NRO_LOJA)).setProcesso(TIPO_FLUXO_APROVACAO_DESC).clickPesquisar();
		
		//---------------------------------------
		// VALIDA REGRA - NAO_PERMITE_DEVOLUCAO
		//-------------------- v ----------------
		if (NRO_MOTIVO_TROCA_DEVOL == NRO_MOTIVO_NAO_PERMITE_DEVOLUCAO) {
			cadSolicitacao.validarRegra(NRO_MOTIVO_TROCA_DEVOL, NRO_LOJA, NRO_PEDIDO, Integer.parseInt(USUARIO));
		}
		
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", preRecibo.getBtnOk());
		Thread.sleep(1000);
				
		controleJanela.abrirPopupClick(preRecibo.getBtnOk());
		
		List<Produto> produtos = new ProdutoDAO().getProduto(NRO_LOJA, NRO_PEDIDO);
		cadSolicitacao.informarMotivoProduto(produtos, NRO_LOJA, NRO_PEDIDO, NRO_LOJA, TIPO_FLUXO_APROVACAO, NRO_MOTIVO_TROCA_DEVOL);
		
		int nroPreRecibo = new PreReciboDAO().getPreRecibo(NRO_LOJA, NRO_PEDIDO, 0);
		
		System.out.println("Nro Pre-recibo: " + nroPreRecibo);
		
		List<Servico> servicos = new ServicoDAO().getServico(NRO_LOJA, NRO_PEDIDO, nroPreRecibo);
		cadSolicitacao.informarMotivoServico(servicos, "3 - SERVICO NAO UTILIZADO", "Teste da troca/devolução de um pedido a vista.");
		
		cadSolicitacao.clickBtnSalvar();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		//tela pre-recibo troca/devolução
		controleJanela.voltarJanelaPrincipal();

		toolbar.clickPesquisar();
		Thread.sleep(1000);
		trocaDevolucao.preRecibo(Integer.toString(nroPreRecibo));
		toolbar.clickPesquisar();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		trocaDevolucao.aguardar();
		trocaDevolucao.selecionarPreRecibo(nroPreRecibo);
		
		//abrir edição do pré-recibo
		controleJanela.abrirPopupClick(toolbar.getBtnEditar());
		
		List<ItemFluxoAprovacao> itensAprovacao = itemAprovacaoDAO.getItensAprovacao(NRO_LOJA, NRO_PEDIDO);
		
		preRecibo.aprovarProdutoPendente(itensAprovacao);
		preRecibo.aprovarServicoPendente(servicos);
		
		preRecibo.clickOk();
		controleJanela.voltarJanelaPrincipal();
		
		Thread.sleep(1000);
		toolbar.clickPesquisar();
		Thread.sleep(1000);
		trocaDevolucao.preRecibo(Integer.toString(nroPreRecibo));
		toolbar.clickPesquisar();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		//---------------------------------------
		// EMISSÃO NF AVULSA
		//-------------------- v ----------------
		
		controleMenu.navegar("Faturamento", "Emissão NF Avulsa");
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		tiposNf = TIPO_NF_CUPOM_FISCAL + ", " + TIPO_NF_NOTA_FISCAL;
		
		List<NotaFiscal> cuponsFiscais = nfDAO.getNotaFiscal(NRO_LOJA, NRO_PEDIDO,  tiposNf);
		nfAvulsa.gerarNfAvulsa(cuponsFiscais, DESC_EMPRESA, NRO_PEDIDO, TIPO_NF_DEVOLUCAO_VENDA);
		
		//---------------------------------------
		// CONTROLE DE DEVOLUÇÃO DE PRODUTOS
		//-------------------- v ----------------
		
		if (NRO_MOTIVO_TROCA_DEVOL != NRO_MOTIVO_TROCA_LIBERA_EMISSAO_RECIBO) {
			controleMenu.navegar("Faturamento", "Controles", "Devolução de Produtos");
			
			tiposNf = Integer.toString(TIPO_NF_DEVOLUCAO_VENDA);
			List<NotaFiscal> notasFiscais = new NotaFiscalDAO().getNotaFiscal(NRO_LOJA, NRO_PEDIDO, tiposNf);
			obsDevolucaoItem.efetuarDevolucaoProduto(notasFiscais, NRO_LOJA, DESC_TIPO_NF_DEVOLUCAO_VENDA);
		}
		
		//---------------------------------------
		// VALIDACAO DE MOTIVO
		//-------------------- v ----------------
		cadSolicitacao.validarRegra(NRO_MOTIVO_TROCA_DEVOL, NRO_LOJA, NRO_PEDIDO, Integer.parseInt(USUARIO));
	}
	
}

