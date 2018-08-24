package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class PesquisaAtendimentoPage extends PageObject {

	@FindBy(css = "#filtro-protocolo-atendimento")
	private WebElement filtroProtocoloAtendimento;
	
	@FindBy(css = "#data-inicio")
	private WebElement filtroDataInicio;
	
	@FindBy(css = "#data-fim")
	private WebElement filtroDataFim;
	
	@FindBy(css = "#filtro-cpf-cnpj")
	private WebElement filtroCpjCnpj;
	
	@FindBy(css = "#loja")
	private WebElement filtroLoja;
	
	@FindBy(css = "#loja ~ ul li a")
	private WebElement filtroOpcaoLoja;
	
	@FindBy(css = "#filtro-pedido")
	private WebElement filtroPedido;
	
	@FindBy(css = "select[ng-model='paramnsPesquisa.statusAtendimento']")
	private WebElement filtroStatus;
	
	@FindBy(css = "#filtro-tratativa")
	private WebElement filtroTratativa;
	
	@FindBy(css = "#filtro-tratativa ~ ul li a")
	private WebElement filtroOpcaoTratativa;
	
	@FindBy(css = "#filtro-acao")
	private WebElement filtroAcao;
	
	@FindBy(css = "#filtro-acao ~ ul li a")
	private WebElement filtroOpcaoAcao;
	
	@FindBy(css = "select[ng-model='paramnsPesquisa.uf']")
	private WebElement filtroUf;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "a.glyphicon-edit")
	private WebElement iconeEditar;
	
	Utils utils;
	
	public PesquisaAtendimentoPage() {
		this.utils = new Utils();
	}
	
	public void clicarBtnPesquisar() {
		btnPesquisar.click();
	}
	
	public void clicarBtnEditar() {
		iconeEditar.click();
	}
	
	public void informarProtocoloAtendimento(String protocolo) {
		filtroProtocoloAtendimento.clear();
		filtroProtocoloAtendimento.sendKeys(protocolo);
	}
	
	public void informarDataInicio(String dataInicio) {
		filtroDataInicio.clear();
		filtroDataInicio.sendKeys(dataInicio);
	}
	
	public void informarDataFim(String dataFim) {
		filtroDataFim.clear();
		filtroDataFim.sendKeys(dataFim);
	}
	
	public void informarCpfCnpj(String cpfCnpj) {
		filtroCpjCnpj.clear();
		filtroCpjCnpj.sendKeys(cpfCnpj);
	}
	
	public void selecionarLoja(String loja) throws InterruptedException {
		utils.preencherCampoTypeAHead(loja , this.filtroLoja);
		Thread.sleep(1000);
		this.filtroOpcaoLoja.click();
	}
	
	public void informarPedido(String pedido) {
		filtroPedido.clear();
		filtroPedido.sendKeys(pedido);
	}
	
	public void informarPedidoSessao() {
		filtroPedido.clear();
		filtroPedido.sendKeys((String) Serenity.sessionVariableCalled(NRO_PEDIDO));
	}
	
	public void informarStatus(String status) {
		new Select(this.filtroStatus).selectByVisibleText(status);
	}
	
	public void selecionarTratativa(String tratativa) throws InterruptedException {
		utils.preencherCampoTypeAHead(tratativa , this.filtroTratativa);
		Thread.sleep(1000);
		this.filtroOpcaoTratativa.click();
	}
	
	public void selecionarAcao(String acao) throws InterruptedException {
		utils.preencherCampoTypeAHead(acao , this.filtroAcao);
		Thread.sleep(1000);
		this.filtroOpcaoAcao.click();
	}
	
	public void selecionarUf(String uf) {
		new Select(this.filtroUf).selectByVisibleText(uf);
	}
	
	public void validarDetalhesProtocoloAtendimento(String protocolo) throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao validar informação protocolo em detalhes do atendimento", $(".//*[@id='form-tratativas']/div[1]/fieldset[1]/div").containsText(protocolo));
	}
}