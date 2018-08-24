package br.com.mv.test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.criterion.PropertySubqueryExpression;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.PageFactory.cargaVeiculo.PopupVeiculo;
import br.com.mv.PageFactory.cargaVeiculo.Transporte;
import br.com.mv.PageFactory.controleFaturamentoCarga.ControleFaturamentoCarga;
import br.com.mv.PageFactory.login.Login;
import br.com.mv.PageFactory.preRecibo.AprovacaoTrocaDevolucao;
import br.com.mv.PageFactory.preRecibo.ElaboracaoPreRecibo;
import br.com.mv.PageFactory.preRecibo.OrdemRecolhimento;
import br.com.mv.PageFactory.preRecibo.PesquisaPreRecibo;
import br.com.mv.PageFactory.preRecibo.PlanilhaAprovacao;
import br.com.mv.PageFactory.preRecibo.SolicitacaoTrocaDevolucao;
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
import br.com.mv.utils.Utils;

/**
 * TesteTrocaDevolucao
 * 
 * @author Weverton <weverton@softbox.com.br>
 *
 */
public class NovoTrocaUmProdutoLoja {
	
	private WebDriver driver;
	
	private AguardaCarregamento agCarregamento;
	private Toolbar toolbar;
	private WebDriverWait wait;
	private ControleJanela controleJanela;
	private ControleMenu controleMenu;
	private PesquisaPreRecibo pesquisaPreRecibo;
	private ElaboracaoPreRecibo elaboracaoPreRecibo;
	private SolicitacaoTrocaDevolucao solicitacaoTrocaDevolucao;
	private AprovacaoTrocaDevolucao aprovacaoTrocaDevolucao;
	private PlanilhaAprovacao planilhaAprovacao;
	private OrdemRecolhimento ordemRecolhimento;
	
	private static final String IP = "http://10.41.0.32:8080/"; 
	private static final String LOJA = "291";
	private static final String USUARIO = "6699";
	private static final String SENHA = "1";
	
	private static final String DESC_EMPRESA = "5 - RN COMERCIO VAREJISTA S.A RELOH";
	
	private static final int NRO_PRE_RECIBO = 2;
	
	private static final String BANDEIRA = "INSINUANTE";
	private static int NRO_PEDIDO = 464970; //1153192; //1153031; //1153030;
	private static int NRO_LOJA_PEDIDO = 277;
	private static final String LOCALIZACAO_PRODUTO = "Loja"; // Ordem de recolhimento
	
	private static final int TEMPO_AGUARDAR_INTEGRACAO = 10; // 10 segundos
	
	// TIPOS FLUXO APROVAÇÃO
	private static final int TIPO_PROCESSO = 1; // 1 - Troca, 2 - Devolução, 3 - Ambos 
	private static final String TIPO_PROCESSO_DESC = "Troca"; // Troca, Devolução, Ambos
	
	// Status pré-recibo troca/devolução
	private static final int EM_ELABORACAO = 0;
	private static final int AG_APROVACAO_PRODUTOS = 1;
	private static final int AG_APROVACAO_SERVICOS = 2;
	private static final int AG_APROVACAO_PLANILHA = 3;
	private static final int AG_APROVACAO_REVERSAO = 4;
	private static final int APROVADO = 5;
	private static final int REPROVADO = 6;
	private static final int FINALIZADO = 7;
	private static final int AG_GERACAO_ORDEM_RECOLHIMENTO = 8;
	private static final int AG_RECOLHIMENTO_PRODUTO = 9;
	
	@Before
	public void setup() {
		String nroPedido = System.getenv("NUM_PEDIDO_INSINUANTE");
		
		if (nroPedido != null) {
			NRO_PEDIDO = Integer.parseInt(nroPedido);	
		}

		String nroLojaPedido = System.getenv("NRO_LOJA_PEDIDO_INSINUANTE");
		
		if (nroLojaPedido != null) {
			NRO_LOJA_PEDIDO = Integer.parseInt(nroLojaPedido);
		}
		
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
		this.pesquisaPreRecibo = new PesquisaPreRecibo(driver);
		this.elaboracaoPreRecibo = new ElaboracaoPreRecibo(driver);
		this.solicitacaoTrocaDevolucao = new SolicitacaoTrocaDevolucao(driver);
		this.aprovacaoTrocaDevolucao = new AprovacaoTrocaDevolucao(driver);
		this.planilhaAprovacao = new PlanilhaAprovacao(driver);
		this.ordemRecolhimento = new OrdemRecolhimento(driver);
	}
	
	@Test
	public void teste1() throws InterruptedException, SQLException, IOException {
		
		// Workspace do jenkins
		String workspace = System.getenv("PATH_WORKSPACE");
		
		if (workspace == null) { // Para executar local via IDE
			workspace = "C:\\Users\\weverton\\mv-lojas-vendas";
			//workspace = "/var/lib/jenkins/jobs/mv-troca-devolucao-TESTES/workspace/";
		}
		
		String sql = new Utils().lerArquivo(workspace + "/scripts_sql_reset_base_testes/RESET_PEDIDO_REVERSO_TROCA_DEVOLUCAO.sql");
		new PreReciboDAO().resetPedidoReverso(sql, NRO_PEDIDO, NRO_LOJA_PEDIDO);
		
		// Loga e abre a tela de pré-recibo
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		Thread.sleep(2000);
		controleMenu.navegar("Faturamento", "Troca/Devolução", "Pré-Recibos Enterprise");

		// Novo pré-recibo
		pesquisaPreRecibo.clickCriarPreRecibo();
		
		Thread.sleep(2000);
		
		// -------------------------------------
		// Elaboração do pré-recibo
		// -------------------------------------
		
		elaboracaoPreRecibo.setFiltroBandeira(BANDEIRA).setFiltroPedido(Integer.toString(NRO_PEDIDO)).setFiltroLoja(Integer.toString(NRO_LOJA_PEDIDO)).setComboProcesso(TIPO_PROCESSO_DESC).clickPesquisar();
		
		if (elaboracaoPreRecibo.validarIntegracaoPedido(TEMPO_AGUARDAR_INTEGRACAO) == false) {
			throw new ElementNotVisibleException("O pedido " + NRO_PEDIDO + " não foi integrado, verificar se o Bpel está ativo.");
		}

		List<Servico> servicos = new ServicoDAO().getServicoReverso(NRO_LOJA_PEDIDO, NRO_PEDIDO);
		List<Produto> produtos = new ProdutoDAO().getProdutoReverso(NRO_LOJA_PEDIDO, NRO_PEDIDO);
		
		// Quando o pre recibo estah em elaboração o processo ja abre na edição
		elaboracaoPreRecibo.removerTodosServicos(servicos);
		elaboracaoPreRecibo.removerProdutos(produtos); // Deixa um produto pro processo
		
		elaboracaoPreRecibo.clickConfirmar();
		
		// -------------------------------------
		// Solicitação de Troca/Devolução - Motivos
		// -------------------------------------
	
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		
		int nroLojaPreReciboReverso = new PreReciboDAO().getNroLojaPreReciboReverso(NRO_LOJA_PEDIDO, NRO_PEDIDO, EM_ELABORACAO);
		
		List<Produto> produtosPreReciboReverso = new ProdutoDAO().getPreReciboProdutoReverso(NRO_PEDIDO, NRO_LOJA_PEDIDO);
		solicitacaoTrocaDevolucao.informarMotivoProduto(produtosPreReciboReverso, Integer.parseInt(LOJA), NRO_PEDIDO, nroLojaPreReciboReverso, TIPO_PROCESSO); //NRO_MOTIVO_TROCA_DEVOL
		
		List<Servico> servicosPreReciboReverso = new ServicoDAO().getPreReciboServicoReverso(NRO_PEDIDO, NRO_LOJA_PEDIDO);
		solicitacaoTrocaDevolucao.informarMotivoServico(servicosPreReciboReverso, "SERVICO NAO UTILIZADO", "Teste da troca/devolução de um pedido a vista.");
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		solicitacaoTrocaDevolucao.clickSalvar();
		
		int nroPreReciboReverso = new PreReciboDAO().getPreReciboReverso(NRO_LOJA_PEDIDO, NRO_PEDIDO, AG_APROVACAO_PRODUTOS);
		
		System.out.println("Pré-recibo reverso: " + nroPreReciboReverso);
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		pesquisaPreRecibo.setFiltroNroPreRecibo(Integer.toString(nroPreReciboReverso)).clickPesquisar();
		
		//Thread.sleep(2000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#btn-editar-" + nroPreReciboReverso)));
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		pesquisaPreRecibo.clickEditarPreRecibo(nroPreReciboReverso);
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		aprovacaoTrocaDevolucao.aprovarProdutoPendente(produtosPreReciboReverso, nroPreReciboReverso, Integer.parseInt(USUARIO));
		aprovacaoTrocaDevolucao.aprovarServicoPendente(servicosPreReciboReverso);
		
		//Thread.sleep(2000);
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		aprovacaoTrocaDevolucao.clickVoltar();
		
		// -------------------------------------
		// Gerar ordem de recolhimento
		// -------------------------------------
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#btn-ordem-recolhimento-" + nroPreReciboReverso)));
		pesquisaPreRecibo.clickOrdemRecolhimentoPreRecibo(nroPreReciboReverso);
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		ordemRecolhimento.preencherOrdemRecolhimentoProduto(produtosPreReciboReverso, LOCALIZACAO_PRODUTO);
		
		ordemRecolhimento.clickConfirmar();
		
		//---------------------------------------
		//  CONTROLE DE FATURAMENTO - GERAR NF DEVOLUÇÃO
		//-------------------- v ----------------
		
		// troca x loja: ag. recolhimento de produto
		
		driver.findElement(By.linkText("Página inicial")).click();
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		controleMenu.navegar("Faturamento", "Controles", "Faturamento");
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		ControleFaturamentoCarga controleFatCarga = new ControleFaturamentoCarga(driver);
		
		controleJanela.abrirPopupClick(controleFatCarga.getBtnControleDevolucoes());
		
		controleFatCarga.setPedidoExterno(Integer.toString(NRO_PEDIDO)).setLojaPedidoExterno(Integer.toString(NRO_LOJA_PEDIDO))
			.setOpcaoRecolhimento("Recolhimento/Entrega").setEmpresaEnterprise(BANDEIRA).clickConsultar().selecionarControleFaturamento();
		
		// Regra FATURAR - Loja de origem deve ser igual à loja logada
		controleJanela.abrirPopupClick(controleFatCarga.getBtnFaturar());
		
		Transporte transporte = new Transporte(driver);
		transporte.setNomeMotorista("Teste automatizado").setTelefoneMotorista("3432323232");
		
		controleJanela.abrirPopupClick(transporte.getBtnPesquisarVeiculo());
		
		PopupVeiculo veiculo = new PopupVeiculo(driver);
		veiculo.preecherVeiculo();
		
		transporte.clickOk();
	}
	
	@Test
	public void teste2GerarCredito() throws InterruptedException {
		// -------------------------------------
		// Gerar crédito cliente: Processo Devolução
		// -------------------------------------
		
		int nroPreReciboReverso = new PreReciboDAO().getPreReciboReverso(NRO_LOJA_PEDIDO, NRO_PEDIDO, APROVADO);
		
		if (nroPreReciboReverso == 0) {
			throw new ElementNotVisibleException("O pedido " + NRO_PEDIDO + " não foi integrado, verificar se o Bpel está ativo.");
		}
		
		// Loga e abre a tela de pré-recibo
		new Login(driver).loja(LOJA).usuario(USUARIO).senha(SENHA).clickLogin();
		
		agCarregamento.aguardarCarregamentoNovaArquitetura();
		Thread.sleep(1000);
		controleMenu.navegar("Faturamento", "Troca/Devolução", "Pré-Recibos Enterprise");
		
		pesquisaPreRecibo.setFiltroNroPreRecibo(Integer.toString(nroPreReciboReverso)).clickPesquisar();
		
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		pesquisaPreRecibo.clickGerarCredito(nroPreReciboReverso);
				
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		pesquisaPreRecibo.clickImprimirReciboTroca(nroPreReciboReverso);
	}
}