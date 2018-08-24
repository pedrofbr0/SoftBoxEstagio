package br.com.mv.PageFactory.preRecibo;

import java.awt.Button;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.dao.pedido.ItemFluxoAprovacao;
import br.com.mv.dao.pedido.Servico;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;

public class PopupPreRecibo {
	
	WebDriver driver;
	
	@FindBy(id = "txtPedido")
	private WebElement nroPedido;
	
	@FindBy(id = "cmbEmpresas")
	private WebElement empresa;
	
	@FindBy(id = "txtCodNumeroLoja")
	private WebElement codNroLoja;
	
	@FindBy(id = "cmbProcesso")
	private WebElement processo;
	
	@FindBy(id = "chkReversao")
	private WebElement reversaoCredito;
	
	@FindBy(id = "txtValorTotalPedido")
	private WebElement valorTotalpedido;
	
	@FindBy(css = "input[id='toolbar:btnNew']")
	private WebElement btnNovo;
	
	@FindBy(css = "input[id='toolbar:btnSearch']")
	private WebElement btnPesquisar;
	
	@FindBy(id = "txtNumeroPreRecibo")
	private WebElement nroPreRecibo;
	
	@FindBy(id = "txtSituacaoPreRecibo")
	private WebElement situacaoPreRecibo;
	
	@FindBy(id = "txtValorTotalPreRecibo")
	private WebElement valorTotalPreRecibo;
	
	@FindBy(id = "btnRemoveItemProduto")
	private WebElement btnRemoverProduto;
	
	@FindBy(css = "input[value='Produtos do Pedido'")
	private WebElement btnProdutosPedido;
	
	@FindBy(id = "txtTotalProdutosPedido")
	private WebElement totalProdutosPedido;
	
	@FindBy(id = "txtTotalProdutosTrocaDevolucao")
	private WebElement totalProdutosTrocaDevolucao;
	
	@FindBy(id = "btnRemoveItemServico")
	private WebElement btnRemoverServico;
	
	@FindBy(css = "input[value='Servicos do Pedido'")
	private WebElement btnServicosPedido;
	
	@FindBy(id = "txtTotalServicosPedido")
	private WebElement totalServicosPedido;
	
	@FindBy(id = "txtTotalServicosTrocaDevolucao")
	private WebElement totalServicosTrocaDevolucao;
	
	@FindBy(id = "btnOk")
	private WebElement btnOk;
	
	@FindBy(id = "btnCancelar")
	private WebElement btnCancelar;
	
	@FindBy(css = "#toolbarProdutos > tbody > tr > td > input[value='Fluxo Aprovacao'")
	private WebElement btnFluxoAprovacaoProduto;
	
	@FindBy(css = "#toolbarProdutos > tbody > tr > td > input[value='Fluxo Aprovacao ADM'")
	private WebElement btnFluxoAprovacaoADMProduto;
	
	@FindBy(css = "#toolbarProdutos > tbody > tr > td > input[value='Haverá Desmontagem?'")
	private WebElement btnHaveraDesmontagem;
	
	@FindBy(css = "#toolbarServicos > tbody > tr > td > input[value='Fluxo Aprovacao'")
	private WebElement btnFluxoAprovacaoServico;
	
	@FindBy(css = "input[type='button'][value='Planilhas']")
	private WebElement btnPlanilhas;
	
	@FindBy(css = "#txtSituacaoReversao")
	private WebElement situacaoReversao;
	
	@FindBy(css = "input[type='button'][value='Aprov. Reversão']")
	private WebElement btnAprovReversao;
	
	@FindBy(css = "input[type='button'][value='Reprov. Reversão']")
	private WebElement btnReprovReversao;
	
	private ControleJanela controleJanela;
	private WebDriverWait wait;
	private AguardaCarregamento agCarregamento;
	
	public PopupPreRecibo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.controleJanela = new ControleJanela(driver);
		this.wait = new WebDriverWait(driver, 20);
		this.agCarregamento = new AguardaCarregamento(driver);
	}
	
	public PopupPreRecibo setNroPedido(String pedido) {
		nroPedido.clear();
		nroPedido.sendKeys(pedido);
		return this;
	}
	
	public String getPedido() {
		return this.nroPedido.getText();
	}
	
	public PopupPreRecibo setNroEmpresa(String empresa) {
		new Select(this.empresa).selectByVisibleText(empresa);
		return this;
	}
	
	public String getEmpresa() {
		return this.empresa.getText();
	}
	
	public PopupPreRecibo setCodNroLoja(String codNroLoja) {
		this.codNroLoja.clear();
		this.codNroLoja.sendKeys(codNroLoja);
		return this;
	}
	
	public String getCodNroLoja() {
		return this.codNroLoja.getText();
	}
	
	public PopupPreRecibo setProcesso(String processo) {
		new Select(this.processo).selectByVisibleText(processo);
		return this;
	}
	
	public String getProcesso() {
		return this.processo.getText();
	}
	
	public PopupPreRecibo clickReversaoCredito() {
		reversaoCredito.click();
		return this;
	}
	
	public String getValorTotalpedido() {
		return valorTotalpedido.getText();
	}
	
	public PopupPreRecibo clickNovo() {
		btnNovo.click();
		return this;
	}
	
	public PopupPreRecibo clickPesquisar() {
		btnPesquisar.click();
		return this;
	}
	
	public String getNroPreRecibo() {
		return nroPreRecibo.getText();
	}
	
	public String getSituacaoPreRecibo() {
		return situacaoPreRecibo.getText();
	}
	
	public String getValorTotalPreRecibo() {
		return valorTotalPreRecibo.getText();
	}
	
	public PopupPreRecibo clickRemoverProduto() {
		btnRemoverProduto.click();
		return this;
	}
	
	public PopupPreRecibo clickProdutosPedido() {
		btnProdutosPedido.click();
		return this;
	}
	
	public WebElement getBtnProdutosPedido() {
		return btnProdutosPedido;
	}
	
	public String getTotalProdutosPedido() {
		return totalProdutosPedido.getText();
	}
	
	public String getTotalProdutosTrocaDevolucao() {
		return totalProdutosTrocaDevolucao.getText();
	}
	
	public PopupPreRecibo clickRemoverServico() {
		btnRemoverServico.click();
		return this;
	}
	
	public PopupPreRecibo clickServicosPedido() {
		btnServicosPedido.click();
		return this;
	}
	
	public WebElement getBtnServicosPedidoo() {
		return btnServicosPedido;
	}
	
	public String getTotalServicosPedido() {
		return totalServicosPedido.getText();
	}
	
	public String getTotalServicosTrocaDevolucao() {
		return totalServicosTrocaDevolucao.getText();
	}
	
	public WebElement getBtnOk() {
		return this.btnOk;
	}
	
	public PopupPreRecibo clickOk() {
		btnOk.click();
		return this;
	}
	
	public PopupPreRecibo clickCancelar() {
		btnCancelar.click();
		return this;
	}
	
	public WebElement getBtnFluxoAprovacaoProduto() {
		return btnFluxoAprovacaoProduto;
	}
	
	public PopupPreRecibo clickBtnFluxoAprovacaoProduto() {
		this.btnFluxoAprovacaoProduto.click();
		return this;
	}
	
	public WebElement getBtnFluxoAprovacaoADMProduto() {
		return btnFluxoAprovacaoADMProduto;
	}
	
	public PopupPreRecibo clickBtnFluxoAprovacaoADMProduto() {
		this.btnFluxoAprovacaoADMProduto.click();
		return this;
	}
	
	public WebElement getBtnHaveraDesmontagem() {
		return btnHaveraDesmontagem;
	}
	
	public PopupPreRecibo clickBtnHaveraDesmontagem() {
		this.btnHaveraDesmontagem.click();
		return this;
	}
	
	public WebElement getBtnFluxoAprovacaoServico() {
		return btnFluxoAprovacaoServico;
	}
	
	public PopupPreRecibo clickBtnFluxoAprovacaoServico() {
		this.btnFluxoAprovacaoServico.click();
		return this;
	}
	
	public WebElement getBtnPlanilhas() {
		return this.btnPlanilhas;
	}
	
	public PopupPreRecibo clickBtnPlanilhas() {
		this.btnPlanilhas.click();
		return this;
	}
	
	public String getSituacaoReversao() {
		return situacaoReversao.getAttribute("value");
	}
	
	public boolean btnAprovReversaoHabilitado() {
		return driver.findElement(By.cssSelector("input[type='button'][value='Aprov. Reversão']")).isEnabled();
	}
	
	public boolean btnReprovReversaoHabilitado() {
		return driver.findElement(By.cssSelector("input[type='button'][value='Reprov. Reversão']")).isEnabled();
	}
	
	public PopupPreRecibo selecionarProduto(int nroProduto) {
		driver.findElement(By.xpath("//table[@id='tableProdutos:tContent']/tbody/tr/td[text()='" + nroProduto + "']/parent::tr/td[2]")).click();
		return this;
	}
	
	public PopupPreRecibo selecionarServico(int nroServico) {
		
		try {
			driver.findElement(By.xpath("//table[@id='tableServicos:tContent']/tbody/tr/td[text()='" + nroServico + "']/parent::tr/td[2]")).click();
		} catch (NoSuchElementException e) {
			System.out.println("Serviço " + nroServico + " não encontrado.");
		}
		
		return this;
	}
	
	public PopupPreRecibo selecionarProdutoTD(int nroProduto) {
		driver.findElement(By.xpath("//table[@id='Produtos:tContent']/tbody/tr/td[text()='" + nroProduto + "']/parent::tr/td[2]")).click();
		return this;
	}
	
	public PopupPreRecibo selecionarServicoTD(int nroServico) {
		driver.findElement(By.xpath("//table[@id='Servicos:tContent']/tbody/tr/td[text()='" + nroServico + "']/parent::tr/td[2]")).click();
		return this;
	}
	
	/**
	 * Aprova os produtos que serão trocados/devolvidos
	 * @param itens, Lista do tipo Produto, com todos os produtos a serem trocados
	 * @throws InterruptedException
	 */
	public void aprovarProdutoPendente(List<ItemFluxoAprovacao> itens) throws InterruptedException {
		
		PopupAprovacaoProdutoTrocaDevolucao aprovacaoProduto = new PopupAprovacaoProdutoTrocaDevolucao(driver);
		PopupObsAprovReprov obsAprovReprove= new PopupObsAprovReprov(driver);
		
		for (ItemFluxoAprovacao item : itens) {
			selecionarProdutoTD(item.getNroProduto());
			
			// aprovar produto
			this.controleJanela.abrirPopupClick(getBtnFluxoAprovacaoProduto());
			aprovacaoProduto.selecionarAprovacaoPendente(item.getDescFaseAprovacao());
			
			// observação aprovação
			obsAprovReprove.setObservacao("Teste automatizado - OK!").clickBtnOk();
			Thread.sleep(1000);
			this.controleJanela.voltarJanelaPrincipal();
			
			aprovacaoProduto.clickBtnAprovar();
			this.wait.until(ExpectedConditions.alertIsPresent());
			this.driver.switchTo().alert().accept();
			this.controleJanela.voltarJanelaPrincipal();
			this.agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		}
		
	}
	
	/**
	 * Aprova os servicos a serem trocados/devolvidos
	 * @param servicos, Lista do tipo Servico, com todos os serviços do pedido
	 * @throws InterruptedException
	 */
	public void aprovarServicoPendente(List<Servico> servicos) throws InterruptedException {
		
		PopupAprovacaoServicoTrocaDevolucao aprovacaoServicoTrocaDevolucao = new PopupAprovacaoServicoTrocaDevolucao(driver);
		
		for (Servico serv : servicos) {
			if (serv.getFlagDevolucao().equalsIgnoreCase("N")) {
				selecionarServicoTD(serv.getNroServico());
				this.controleJanela.abrirPopupClick(getBtnFluxoAprovacaoServico());
				aprovacaoServicoTrocaDevolucao.setObsAprovacaoReprovacao("aprovado").clickBtnAprovar();
				this.wait.until(ExpectedConditions.alertIsPresent());
				this.driver.switchTo().alert().accept();
				this.controleJanela.voltarJanelaPrincipal();
				this.agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			}
		}
		
	}

}
