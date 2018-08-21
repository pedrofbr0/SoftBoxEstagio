package br.com.mv.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

import static br.com.mv.model.SessionVariables.NRO_IMPORTACAO_PROMOCAO;
import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

public class ConfiguracaoPromocaoPage extends PageObject{

	@FindBy(css = "#btn-nova-importacao")
	private WebElement btnNovaImportacao;
	
	/* Pesquisa de Promocao */
	@FindBy(css = "input[ng-model='promocaoPesquisa.filtros.codigo']")
	private WebElement codigoPromocao;
	
	@FindBy(css = "input[ng-model='promocaoPesquisa.filtros.descricao']")
	private WebElement descricaoPromocao;
	
	@FindBy(css = "input[ng-model='promocaoPesquisa.filtros.status']")
	private WebElement statusPromocao;

	@FindBy(css = "input[ng-model='promocaoPesquisa.filtros.dtInicio']")
	private WebElement dtInicio;
	
	@FindBy(css = "input[ng-model='promocaoPesquisa.filtros.dtFim']")
	private WebElement dtFim;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisarPromocao;
	
	@FindBy(css = "#btn-limpar-resultado")
	private WebElement btnLimparResultado;
	
	@FindBy(css = "input[ng-model='tiposServico']")
	private WebElement tipoServico;
	
	@FindBy(css = "input[ng-model='tiposServico'] ~ ul li a")
	private WebElement opcaoTipoServico;
	
	@FindBy(css = "input[ng-model='servico']")
	private WebElement servico;
	
	@FindBy(css = "input[ng-model='servico'] ~ ul li a")
	private WebElement opcaoServico;
	
	@FindBy(css = "#btn-salvar")
	private WebElement btnSalvar;
	
	Utils utils;
	
	public ConfiguracaoPromocaoPage() {
		utils = new Utils();
	}
	
	public void informarDescricao(String descPromocao) {
		this.descricaoPromocao.clear();
		this.descricaoPromocao.sendKeys(descPromocao);
	}
	
	public void clicarBtnPesquisarPromocao() {
		this.btnPesquisarPromocao.click();
	}
	
	public void informarCodigoPromocao(String codigoPromocao) throws InterruptedException {
		this.codigoPromocao.clear();
		this.codigoPromocao.sendKeys(codigoPromocao);
	}
	
	public void selecionarStatusPromocao(String statusPromocao) {
		$("#combo-status").selectByVisibleText(statusPromocao);
	}
	
	public void informarDataInicioVigencia() {
		String dataAtual = new Utils().getDataAtual();
		
		this.dtInicio.clear();
		this.dtInicio.sendKeys(dataAtual);
	}
	
	public void informarDataFimVigencia() {
		String dataAtual = new Utils().getDataAtual();
		
		this.dtFim.clear();
		this.dtFim.sendKeys(dataAtual);
	}
	
	public void clicarEmAcoesPromocaoPesquisada() {
		String codigoPromocao = "";
		$(".//table/*/tr/td[contains(text(), '" + codigoPromocao + "')]/parent::tr/td[7]").click();
	}
	
	public void clicarEmAcoesPrimeiraPromocaoEncontrada() {
		//$(".//table/tbody[1]/tr/td/parent::tr/td[7]").click();
		$(".//table/*/tr/td/parent::tr/td[5]/a/i[contains(@class, 'glyphicon-remove-sign')]/parent::a/parent::td/parent::tr/td[6]/a/i[contains(@class, 'glyphicon-remove-sign')]/parent::a/parent::td/parent::tr/td[7]").click();
	}
	
	public void selecionarCondicional(String condicional) {
		$("#radio-" + condicional.toLowerCase()).click();
	}
	
	public void selecionarTipoServico(String tipoServico) throws InterruptedException {
		this.tipoServico.clear();
		this.utils.preencherCampoTypeAHead(tipoServico, this.tipoServico);
		
		Thread.sleep(1000);
		this.opcaoTipoServico.click();
	}
	
	public void selecionarServico(String servico) throws InterruptedException {
		this.servico.clear();
		this.utils.preencherCampoTypeAHead(servico, this.servico);
		
		Thread.sleep(1000);
		this.opcaoServico.click();
	}
	
	public void clicarBtnSalvar() {
		this.btnSalvar.click();
	}
	
	public void validarMensagemSucesso() {
		Assert.assertTrue("Associação não foi realizada", $(".modal-content .modal-body").getText().contains("Associação realizada com sucesso."));
	}
}