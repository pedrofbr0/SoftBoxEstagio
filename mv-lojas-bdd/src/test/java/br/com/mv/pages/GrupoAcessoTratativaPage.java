package br.com.mv.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.Utils;
import net.thucydides.core.pages.PageObject;

public class GrupoAcessoTratativaPage extends PageObject {

	@FindBy(css = "#btn-criar")
	private WebElement btnCriar;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "input[ng-model='editaService.data.perfil_acesso']")
	private WebElement paramGrupoAcesso;
	
	@FindBy(css = "input[ng-model='editaService.data.perfil_acesso'] ~ ul li a")
	private WebElement opcaoParamGrupoAcesso;
	
	@FindBy(css = "#filtro-perfil-acesso")
	private WebElement filtroParamGrupoAcesso;
	
	@FindBy(css = "#filtro-perfil-acesso ~ ul li a")
	private WebElement filtroOpcaoParamGrupoAcesso;
	
	@FindBy(css = "#paramn-tratativa")
	private WebElement paramTratativa;
	
	@FindBy(css = "#paramn-tratativa ~ ul li a")
	private WebElement opcaoParamTratativa;
	
	@FindBy(css = "#paramn-data-base")
	private WebElement paramDataBase;
	
	@FindBy(css = "#paramn-data-base ~ ul li a")
	private WebElement opcaoParamDataBase;
	
	@FindBy(css = "#paramn-linha-produto")
	private WebElement paramLinhaProduto;
	
	@FindBy(css = "#paramn-linha-produto ~ ul li a")
	private WebElement opcaoParamLinhaProduto;
	
	@FindBy(css = "#paramn-local-retirada")
	private WebElement paramLocalRetirada;
	
	@FindBy(css = "#paramn-local-retirada ~ ul li a")
	private WebElement opcaoParamLocalRetirada;
	
	@FindBy(css = "#ticket-medio")
	private WebElement ticketMedio;
	
	@FindBy(xpath = ".//button[contains(text(), 'Adicionar')]")
	private WebElement btnAdicionar;
	
	@FindBy(xpath = ".//button[contains(text(), 'Salvar')]")
	private WebElement btnSalvar;
	
	@FindBy(css = "a.glyphicon-edit")
	private WebElement iconeEditar;
	
	// Opções da regra
	
	@FindBy(css = "input[ng-model='editaService.data.regra.ind_troca']")
	private WebElement regraIndTroca;
	
	// Igual a Devolucao
	@FindBy(css = "input[ng-model='editaService.data.regra.ind_cancelamento']")
	private WebElement regraIndCancelamento;
	
	@FindBy(css = "input[ng-model='editaService.data.regra.ind_assistencia']")
	private WebElement regraIndAssistencia;
	
	@FindBy(css = "input[ng-model='editaService.data.regra.ind_reclamacao']")
	private WebElement regraIndReclamacao;
	
	@FindBy(css = "input[ng-model='editaService.data.regra.ind_abertura']")
	private WebElement regraIndAbertura;
	
	@FindBy(css = "input[ng-model='editaService.data.regra.ind_aprovacao']")
	private WebElement regraIndAprovacao;
	
	@FindBy(css = "input[ng-model='editaService.data.regra.ind_regra_forma_reembolso']")
	private WebElement regraIndFormaReembolso;	
	
	@FindBy(css = "a.glyphicon-trash")
	private WebElement iconeExcluirGrupoAcessoTratativa;
	
	@FindBy(xpath = ".//button[contains(text(), 'Sim')]")
	private WebElement btnSim;
	
	Utils utils;
	
	public GrupoAcessoTratativaPage() {
		this.utils = new Utils();
	}
	
	public void clicarBtnSim() {
		this.btnSim.click();
	}
	
	public void clicarBtnCriar() {
		btnCriar.click();
	}
	
	public void clicarIconeExcluirGrupoAcessoTratativa() {
		iconeExcluirGrupoAcessoTratativa.click();
	}
	
	public void clicarBtnPesquisar() {
		btnPesquisar.click();
	}
	
	public void clicarBtnSalvar() {
		btnSalvar.click();
	}
	
	public void clicarBtnAdicionar() {
		btnAdicionar.click();
	}
	
	public void clicarIconeEditar() {
		iconeEditar.click();
	}
	
	public void informarGrupoAcesso(String grupoAcesso) throws InterruptedException {
		utils.preencherCampoTypeAHead(grupoAcesso, this.paramGrupoAcesso);
		Thread.sleep(1000);
		this.opcaoParamGrupoAcesso.click();
	}
	
	public void informarFiltroGrupoAcesso(String grupoAcesso) throws InterruptedException {
		utils.preencherCampoTypeAHead(grupoAcesso, this.filtroParamGrupoAcesso);
		Thread.sleep(1000);
		this.filtroOpcaoParamGrupoAcesso.click();
	}
	
	public void informarTratativa(String tratativa) throws InterruptedException {
		utils.preencherCampoTypeAHead(tratativa, this.paramTratativa);
		Thread.sleep(1000);
		this.opcaoParamTratativa.click();
	}
	
	public void informarDataBase(String dataBase) throws InterruptedException {
		utils.preencherCampoTypeAHead(dataBase, this.paramDataBase);
		Thread.sleep(1000);
		this.opcaoParamDataBase.click();
	}
	
	public void informarLinhaProduto(String linhaProduto) throws InterruptedException {
		utils.preencherCampoTypeAHead(linhaProduto, this.paramLinhaProduto);
		Thread.sleep(1000);
		this.opcaoParamLinhaProduto.click();
	}
	
	public void informarRetirada(String localRetirada) throws InterruptedException {
		utils.preencherCampoTypeAHead(localRetirada, this.paramLocalRetirada);
		Thread.sleep(1000);
		this.opcaoParamLocalRetirada.click();
	}
	
	public void informarTicketMedio(String ticketMedio) {
		this.ticketMedio.clear();
		this.ticketMedio.sendKeys(ticketMedio);
	}
	
	public void marcarIndTroca() {
		regraIndTroca.click();
	}
	
	public void marcarIndCancelamento() {
		regraIndCancelamento.click();
	}
	
	public void marcarIndAssistencia() {
		regraIndAssistencia.click();
	}
	
	public void marcarIndReclamacao() {
		regraIndReclamacao.click();
	}
	
	public void marcarIndAbertura() {
		regraIndAbertura.click();
	}
	
	public void marcarIndAprovacao() {
		regraIndAprovacao.click();
	}
	
	public void marcarIndReembolso() {
		regraIndFormaReembolso.click();
	}
	
	public void validarMensagemSucesso() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao salvar grupo acesso x tratativa.", $(".alert").containsText("Registro salvo com sucesso!"));
	}
	
	public void validarMensagemSucessoExclusao() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao excluir grupo acesso x tratativa.", $(".alert").containsText("Registro excluido com sucesso!"));
	}
}