package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class AtendimentoClientePage extends PageObject {

	@FindBy(css = "#txt-cpf")
	private WebElement filtroCpf;
	
	@FindBy(css = "#txt-pedido")
	private WebElement filtroPedido;
	
	@FindBy(css = "#loja")
	private WebElement filtroLoja;
	
	@FindBy(css = "#loja ~ ul li a")
	private WebElement filtroOpcaoLoja;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(xpath = ".//*/a[contains(text(), 'Próximo')]")
	private WebElement btnProximo;
	
	@FindBy(xpath = ".//*[@id='form-views']/div[6]/fieldset/textarea")
	private WebElement obsTratativa;
	
	@FindBy(xpath = ".//*[@id='div-botoes-acoes']/div[1]/button")
	private WebElement acaoDevolucao;
	
	@FindBy(xpath = ".//*[@id='div-botoes-acoes']/div[2]/button")
	private WebElement acaoTroca;
	
	@FindBy(xpath = ".//*[@id='div-botoes-acoes']/div[3]/button")
	private WebElement acaoReclamacao;
	
	@FindBy(xpath = ".//*[@id='div-botoes-acoes']/div[4]/button")
	private WebElement acaoAssistenciaTecnica;
	
	@FindBy(xpath = ".//*/a[contains(text(), 'Concluir')]")
	private WebElement btnConcluir;
	
	@FindBy(xpath = ".//button[contains(text(), 'Sim')]")
	private WebElement btnSim;
	
	Utils utils;
	
	public AtendimentoClientePage() {
		this.utils = new Utils();
	}
	
	// IDENTIFICACAO
	
	public void informarCpf(String cpf) {
		filtroCpf.clear();
		filtroCpf.sendKeys(cpf);
	}
	
	public void informarPedido() {
		filtroPedido.clear();
		filtroPedido.sendKeys((String) Serenity.sessionVariableCalled(NRO_PEDIDO));
	}
	
	public void selecionarLoja(String loja) throws InterruptedException {
		utils.preencherCampoTypeAHead(loja , this.filtroLoja);
		Thread.sleep(1000);
		this.filtroOpcaoLoja.click();
	}
	
	public void clicarBtnPesquisar() {
		btnPesquisar.click();
	}

	public void clicarBtnProximo() {
		btnProximo.click();
	}

	public void selecionarItemAtendimento(String codigoItem) throws InterruptedException {
		Thread.sleep(2000);
		$(".//*[@id='form-views']/div[4]/div/table/tbody/*/td[contains(text(), '" + codigoItem + "')]/parent::tr/td[1]/input").click();
	}
	
	// TRATATIVA
	
	public void selecionarTratativa(String tratativa) {
		$(".//*[@id='div-botoes-tratativas']/*/label[contains(text(), '" + tratativa + "')]").click();
	}
	
	public void informarObservacaoTratativa(String obsTratativa) {
		this.obsTratativa.clear();
		this.obsTratativa.sendKeys(obsTratativa);
	}
	
	// ACAO
	
	public void selecionarAcaoDevolucao() {
		acaoDevolucao.click();
	}
	
	public void selecionarAcaoTroca() {
		acaoTroca.click();
	}
	
	public void selecionarAcaoReclamacao() {
		acaoReclamacao.click();
	}
	
	public void selecionarAcaoAssistenciaTecnica() {
		acaoAssistenciaTecnica.click();
	}
	
	public void clicarBtnConcluir() {
		btnConcluir.click();
	}
	
	public void selecionarTipoTroca(String tipoTroca) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		$(".//*[@id='form-tratativas']/div[4]/fieldset/div/button[contains(text(), '" + tipoTroca + "')]").click();	
	}
	
	public void clicarBtnSim() {
		this.btnSim.click();
	}
}