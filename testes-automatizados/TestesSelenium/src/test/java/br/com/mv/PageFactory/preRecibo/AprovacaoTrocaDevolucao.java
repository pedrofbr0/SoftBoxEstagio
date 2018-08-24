package br.com.mv.PageFactory.preRecibo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.dao.pedido.ItemFluxoAprovacao;
import br.com.mv.dao.pedido.ItemFluxoAprovacaoDAO;
import br.com.mv.dao.pedido.Produto;
import br.com.mv.dao.pedido.Servico;
import br.com.mv.utils.AguardaCarregamento;

public class AprovacaoTrocaDevolucao {

	WebDriver driver;
	
	@FindBy(id = "btn-creditos")
	private WebElement btnCreditos;
	
	@FindBy(id = "btn-fluxo-aprovacao-produto")
	private WebElement btnFluxoAprovacaoProduto;
	
	@FindBy(id = "btn-fluxo-aprovacao-adm-produto")
	private WebElement btnFluxoAprovacaoAdmProduto;
	
	@FindBy(id = "btn-solicitar-desmontagem")
	private WebElement btnSolicitarDesmontagem;
	
	@FindBy(id = "btn-produtos-pedido")
	private WebElement btnProdutosPedido;
	
	@FindBy(id = "btn-fluxo-aprovacao-sevico")
	private WebElement btnFluxoAprovacaoServico;
	
	@FindBy(id = "btn-servicos-pedido")
	private WebElement btnServicosPedido;
	
	@FindBy(id = "btn-voltar")
	private WebElement btnVoltar;
	
	@FindBy(id = "btn-aprovar-reversao")
	private WebElement btnAprovarReversao;
	
	@FindBy(id = "btn-planilhas")
	private WebElement btnPlanilhas;
	
	private AguardaCarregamento agCarregamento;
	
	public AprovacaoTrocaDevolucao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		this.agCarregamento = new AguardaCarregamento(driver);
	}
	
	public AprovacaoTrocaDevolucao clickAprovarReversao() {
		this.btnAprovarReversao.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickCreditos() {
		this.btnCreditos.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickFluxoAprovacaoProduto() {
		this.btnFluxoAprovacaoProduto.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickFluxoAprovacaoAdmProduto() {
		this.btnFluxoAprovacaoAdmProduto.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickSolicitarDesmontagem() {
		this.btnSolicitarDesmontagem.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickProdutosPedido() {
		this.btnProdutosPedido.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickFluxoAprovacaoServico() {
		this.btnFluxoAprovacaoServico.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickServicosPedido() {
		this.btnServicosPedido.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickVoltar() {
		this.btnVoltar.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao clickPlanilhas() {
		this.btnPlanilhas.click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao selecionarProduto(int idProdutoReverso) {
		driver.findElement(By.cssSelector("input[name='produto_selecionado'][value='" + idProdutoReverso + "']")).click();
		return this;
	}
	
	public AprovacaoTrocaDevolucao selecionarServico(int idServicoReverso) {
		driver.findElement(By.cssSelector("input[name='servico_selecionado'][value='" + idServicoReverso + "']")).click();
		return this;
	}
	
	/**
	 * Aprova os produtos que serão trocados/devolvidos
	 * @param itens, Lista do tipo Produto, com todos os produtos a serem trocados
	 * @throws InterruptedException
	 */
	public void aprovarProdutoPendente(List<Produto> produtosPreReciboReverso, int nroPreReciboReverso, int nroUsuario) throws InterruptedException {
		
		AprovacaoTrocaDevolucao aprovacaoTrocaDevolucao = new AprovacaoTrocaDevolucao(driver);
		AprovacaoProduto aprovacaoProduto = new AprovacaoProduto(driver);
		ItemFluxoAprovacaoDAO itemAprovacaoDAO = new ItemFluxoAprovacaoDAO();
		
		for (Produto produto : produtosPreReciboReverso) {
			
			agCarregamento.aguardarCarregamentoNovaArquitetura2();
			selecionarProduto(produto.getIdProdutoReverso());
			
			agCarregamento.aguardarCarregamentoNovaArquitetura2();
			aprovacaoTrocaDevolucao.clickFluxoAprovacaoProduto();
			
			int nroAprovacaoReverso = itemAprovacaoDAO.getNroAprovacaoReverso(nroPreReciboReverso, produto.getIdProdutoReverso());
			
			System.out.println("Nro aprovação reverso: " + nroAprovacaoReverso);
			
			List<ItemFluxoAprovacao> itensAprovacao = itemAprovacaoDAO.getItensAprovacaoReverso(nroUsuario, nroAprovacaoReverso);
			
			for (ItemFluxoAprovacao item : itensAprovacao) {
				agCarregamento.aguardarCarregamentoNovaArquitetura2();
				aprovacaoProduto.selecionarAprovacaoPendente(item.getDescFaseAprovacao());
				aprovacaoProduto.setObservacao("Teste automatizado - OK!");
				aprovacaoProduto.clickAprovar();
			}
			
			agCarregamento.aguardarCarregamentoNovaArquitetura2();
			aprovacaoProduto.clickVoltar();
			Thread.sleep(1000);
		}
	}
	
	/**
	 * Aprova os servicos a serem trocados/devolvidos
	 * @param servicos, Lista do tipo Servico, com todos os serviços do pedido
	 * @throws InterruptedException
	 */
	public void aprovarServicoPendente(List<Servico> servicosPreReciboReverso) throws InterruptedException {
		
		AprovacaoTrocaDevolucao aprovacaoTrocaDevolucao = new AprovacaoTrocaDevolucao(driver);
		AprovacaoServico aprovacaoServico = new AprovacaoServico(driver);
		
		for (Servico servico : servicosPreReciboReverso) {
			
			agCarregamento.aguardarCarregamentoNovaArquitetura2();
			selecionarServico(servico.getIdServicoReverso());
			
			agCarregamento.aguardarCarregamentoNovaArquitetura2();
			aprovacaoTrocaDevolucao.clickFluxoAprovacaoServico();
			
			//if (servico.getFlagDevolucao().equalsIgnoreCase("N")) {
				
				agCarregamento.aguardarCarregamentoNovaArquitetura2();
				aprovacaoServico.setObservacao("Aprovado").clickAprovar();
				Thread.sleep(1000);
				//aprovacaoServico.clickVoltar();
			//}
		}
	}
}