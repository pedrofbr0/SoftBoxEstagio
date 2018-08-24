package br.com.mv.test;

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
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.ControleMenu;

public class TrocaUmProduto {

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
	
	private static final String IP = "http://10.41.0.32:8080/"; 
	private static final String LOJA = "291";
	private static final String USUARIO = "7380";
	private static final String SENHA = "1";
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A - RELOEXA";
	private static final int NRO_LOJA = 1101;
	private static final int COD_NRO_LOJA = 291;
	private static final int NRO_PEDIDO = 55205; // 47601
	//private static final int NRO_MOTIVO_TROCA_DEVOL = 405;
	//private static final String DESC_MOTIVO_TROCA_DEVOL = "TESTE AUT. - PER_GER_CON_FAT_DEV_APR - TROCA";
	
	// TIPOS NFs
	private static final int TIPO_NF_CUPOM_FISCAL = 1;
	private static final int TIPO_NF_NOTA_FISCAL = 10;
	private static final int TIPO_NF_DEVOLUCAO_VENDA = 22;
	
	private static final String DESC_TIPO_NF_DEVOLUCAO_VENDA = "22 - DEVOLUCAO DE VENDA - UF1280";
	
	//TIPOS FLUXO APROVAÇÃO
	private static final int TIPO_FLUXO_APROVACAO = 1; //1 - Troca, 2 - Devolução, 3 - Ambos 
	private static final String TIPO_FLUXO_APROVACAO_DESC = "Troca"; //Troca, Devolucao, Ambos
	
	@Before
	public void setup() {
		
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
	}
	
	@Test
	public void teste() throws InterruptedException, SQLException {
		
		PreReciboTrocaDevolucao trocaDevolucao = new PreReciboTrocaDevolucao(driver);
		ItemFluxoAprovacaoDAO itemAprovacaoDAO = new ItemFluxoAprovacaoDAO();
		
		String tiposNf;
		
		//loga e abre a tela de pre-recibo
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Faturamento", "Troca/Devolução", "Pré-Recibos");
		
		//novo pre-recibo
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		trocaDevolucao.aguardar();
		controleJanela.abrirPopupClick(toolbar.getBtnNovo());
		
		//popup novo pre-recibo
		preRecibo.setNroPedido(Integer.toString(NRO_PEDIDO)).setNroEmpresa(DESC_EMPRESA).setCodNroLoja(Integer.toString(COD_NRO_LOJA)).setProcesso(TIPO_FLUXO_APROVACAO_DESC).clickPesquisar();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		List<Produto> produtos = new ProdutoDAO().getProduto(NRO_LOJA, NRO_PEDIDO);
		List<Servico> servicos = new ServicoDAO().getServico(NRO_LOJA, NRO_PEDIDO, 0);
		
		removerProdutos(produtos);
		removerServicos(servicos);
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", preRecibo.getBtnOk());
		Thread.sleep(1000);
		controleJanela.abrirPopupClick(preRecibo.getBtnOk());
		
		cadSolicitacao.informarMotivoProduto(produtos, NRO_LOJA, NRO_PEDIDO, NRO_LOJA, TIPO_FLUXO_APROVACAO, 0); // se informar o valor 0 pega o motivo de forma randomica
		
		int nroPreRecibo = new PreReciboDAO().getPreRecibo(NRO_LOJA, NRO_PEDIDO, 0);
		
		//cadSolicitacao.informarMotivoServico(servicos, "3 - SERVICO NAO UTILIZADO", DESC_MOTIVO_TROCA_DEVOL);
		
		cadSolicitacao.clickBtnSalvar();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		//tela pre-recibo troca/devolução
		controleJanela.voltarJanelaPrincipal();

		//int nroPreRecibo = new PreReciboDAO().getPreRecibo(NRO_LOJA, NRO_PEDIDO, 1);
		
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
		
		List<NotaFiscal> cuponsFiscais = nfDAO.getNotaFiscal(NRO_LOJA, NRO_PEDIDO, tiposNf);
		nfAvulsa.gerarNfAvulsa(cuponsFiscais, DESC_EMPRESA, NRO_PEDIDO, TIPO_NF_DEVOLUCAO_VENDA);
		
		//---------------------------------------
		// CONTROLE DE DEVOLUÇÃO DE PRODUTOS
		//-------------------- v ----------------
		
		controleMenu.navegar("Faturamento", "Controles", "Devolução de Produtos");
		
		tiposNf = Integer.toString(TIPO_NF_DEVOLUCAO_VENDA);
		
		List<NotaFiscal> notasFiscais = new NotaFiscalDAO().getNotaFiscal(NRO_LOJA, NRO_PEDIDO, tiposNf);
		obsDevolucaoItem.efetuarDevolucaoProduto(notasFiscais, NRO_LOJA, DESC_TIPO_NF_DEVOLUCAO_VENDA);
		
		//---------------------------------------
		// VALIDACAO DE MOTIVO
		//-------------------- v ----------------
		
		//cadSolicitacao.validarRegra(NRO_MOTIVO_TROCA_DEVOL, NRO_LOJA, NRO_PEDIDO, Integer.parseInt(USUARIO));
	}
	
	/**
	 * Deixa apenas o primeiro produto de um pedido no caso do pedido ter mais de um produto
	 * @param produtos, Lista com os produtos existente em um pedido
	 */
	private void removerProdutos(List<Produto> produtos) {

		int qtdProdutos = produtos.size();
		
		for (int i = 1; i < qtdProdutos; i++) {
			preRecibo.selecionarProduto(produtos.get(i).getNroProduto()).clickRemoverProduto();
		}
		
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
