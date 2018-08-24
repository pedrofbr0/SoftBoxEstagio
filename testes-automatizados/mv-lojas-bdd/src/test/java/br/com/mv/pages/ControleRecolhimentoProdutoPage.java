package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class ControleRecolhimentoProdutoPage extends PageObject {

	@FindBy(css = "#filtro-pedido")
	private WebElement filtroPedido;
	
	@FindBy(css = "#filtro-loja")
	private WebElement filtroLoja;
	
	@FindBy(css = "#filtro-loja ~ ul li a")
	private WebElement filtroOpcaoLoja;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#radio-nf")
	private WebElement radioNF;
	
	@FindBy(css = "select[ng-model='item.status']")
	private WebElement status;
			
	@FindBy(css = "select[ng-model='item.grupo_estoque_destino']")
	private WebElement grupoEstoque;

	@FindBy(css = "a.text-danger")
	private WebElement iconeObservacao;
	
	@FindBy(css = "textarea[ng-model='item.obs_devolucao']")
	private WebElement obsDevolucao;
			
	@FindBy(xpath = ".//button[contains(text(), 'Ok')]")
	private WebElement btnOk;
	
	@FindBy(css = "input[ng-model='item.check']")
	private WebElement checkRecolhimento;
			
	@FindBy(xpath = ".//*[@id='btn-pesquisar'][2]")
	private WebElement btnAutorizarRecibo;
	
	@FindBy(xpath = ".//button[contains(text(), 'OK')]")
	private WebElement btnOkModalSucesso;
	
	@FindBy(xpath = ".//button[contains(text(), 'Salvar')]")
	private WebElement btnSalvarRecolhimento;
	
	Utils utils;
	
	public ControleRecolhimentoProdutoPage() {
		this.utils = new Utils();
	}

	public void informarPedido() {
		
		//Serenity.setSessionVariable(NRO_PEDIDO).to("4931815");
		
		filtroPedido.clear();
		filtroPedido.sendKeys((String) Serenity.sessionVariableCalled(NRO_PEDIDO));
	}
	
	public void selecionarFiltroLoja(String loja) throws InterruptedException {
		utils.preencherCampoTypeAHead(loja, this.filtroLoja);
		Thread.sleep(1000);
		this.filtroOpcaoLoja.click();
	}
	
	public void clicarPesquisar() {
		btnPesquisar.click();
	}
	
	public void selecionarNotaFiscal() throws InterruptedException {
		Thread.sleep(2000);
		radioNF.click();
	}
	
	public void selecionarStatus(String status) {
		new Select(this.status).selectByVisibleText(status);
	}
	
	public void selecionarGrupoEstoque(String grupoEstoque) {
		new Select(this.grupoEstoque).selectByVisibleText(grupoEstoque);
	}
	
	public void informarObservacao(String observacao) throws InterruptedException {
		
		this.iconeObservacao.click();
		
		Thread.sleep(1000);
		this.obsDevolucao.clear();
		this.obsDevolucao.sendKeys(observacao);
	}
	
	public void clicarOk() {
		this.btnOk.click();
	}
	
	public void clickCheckRecolhimento() {
		checkRecolhimento.click();
	}
	
	public void clickBtnAutorizarRecibo() {
		btnAutorizarRecibo.click();
	}
	
	public void clickBtnSalvarRecolhimento() {
		btnSalvarRecolhimento.click();
	}
	
	public void validarMensagemAutorizacaoRecibo() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao autorizar recibo.", $(".alert").containsText("Recibo de troca liberado com sucesso."));
	}
	
	public void clicarOkModalSucesso() throws InterruptedException {
		Thread.sleep(1000);
		btnOkModalSucesso.click();
	}
}