package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.COD_NRO_LOJA;
import static br.com.mv.model.SessionVariables.NRO_BORDERO;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.dao.ConfirmacaoDepositoDAO;
import br.com.mv.model.Bordero;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class ConfirmacaoDepositoPage extends PageObject{

	@FindBy(css = "#btnConfirmar")
	private WebElement btnConfirmar;
	
	@FindBy(css = "#dataDeposito")
	private WebElement dataDeposito;
	
	@FindBy(css = "#horaDeposito")
	private WebElement horaDeposito;
	
	@FindBy(css = "#vlrDepDinheiro")
	private WebElement vlrDepDinheiro;
	
	@FindBy(css = "#bancoForaPadrao")
	private WebElement bancoForaPadrao;
	
	@FindBy(css = "#nroCompDepDinheiro")
	private WebElement nroCompDepDinheiro;
	
	@FindBy(css = "#nroCompDepCheque")
	private WebElement nroCompDepCheque;
	
	@FindBy(css = "#observacao")
	private WebElement observacao;
	
	@FindBy(css = "#btnConfirm")
	private WebElement btnConfirm;
	
	@FindBy(css = "#comboBancos")
	private WebElement comboBancos;
	
	@FindBy(xpath = ".//*[@id='btnImprimir']")
	private WebElement btnImprimirRelatorioDeposito;
	
	ConfirmacaoDepositoDAO confirmacaoDepositoDAO;
	ControleJanela controleJanela;
	RelatorioComissaoPage relatorio;
	
	String nroBordero;
	
	public ConfirmacaoDepositoPage() {
		confirmacaoDepositoDAO = new ConfirmacaoDepositoDAO();
	}
	
	public void selecionarBordero() {
		$(".//*[@id='table:tContent']/tbody/tr[1]/td[1]/input").click();
	}
	
	public void clicarBotaoConfirmar() throws InterruptedException {
		controleJanela.abrirPopupClick(this.btnConfirmar);
		//btnConfirmar.click();
	}
	
	public void informarDataDeposito() {
		// Buscar do Banco a data
		this.nroBordero = $("#nroBordero").getValue();
		
		Serenity.setSessionVariable(NRO_BORDERO).to(this.nroBordero.trim());
		
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
		
		Bordero bordero = confirmacaoDepositoDAO.getBordero(this.nroBordero, codNroLoja);
		
		dataDeposito.clear();
		dataDeposito.sendKeys(bordero.getDtGeracaoBordero());
	}
	
	public void informarHoraDeposito(String horaDep) {
		horaDeposito.clear();
		horaDeposito.sendKeys(horaDep);
	}
	
	public void informarValorDeposito() {
		
		String vlrDep = $("#vlrBorderoDinheiro").getValue();
		
		System.out.println("vlrDep: " + vlrDep);
		
		vlrDepDinheiro.clear();
		vlrDepDinheiro.sendKeys(vlrDep + "00");
	}
	
	public void selecionarCheckboxBancoForaPadrao() {
		bancoForaPadrao.click();
	}
	
	public void informarNumeroComprovanteDinheiro(String nroCompDinheiro) {
		nroCompDepDinheiro.clear();
		nroCompDepDinheiro.sendKeys(nroCompDinheiro);
	}
	
	public void informarNumeroComprovanteCheque(String nroCompCheque) {
		nroCompDepCheque.clear();
		nroCompDepCheque.sendKeys(nroCompCheque);
	}
	
	public void informarObservacao(String obs) {
		observacao.clear();
		observacao.sendKeys(obs);
	}
	
	public void clicarBtnOkConfirmacao() throws InterruptedException {
		Thread.sleep(1000);
		
		$(".//*[@id='btnConfirm']").click();
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		
		this.getDriver().switchTo().alert().accept();
	}
	
	public void selecionarComboBanco(String banco) throws InterruptedException {
		System.out.println(banco);
		Thread.sleep(3000);
		new Select(comboBancos).selectByVisibleText(banco);
	}
	
	public void validarMensagemConfirmacao() throws InterruptedException {
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		String msgAlert = this.getDriver().switchTo().alert().getText();
		
		String msg = "Confirmação de depósito salva com sucesso!";
		
		Assert.assertTrue("Erro ao salvar confirmação de depósito", msgAlert.equals(msg));
	}
	
	public void validarBancoForaPadrao(String codBanco) {
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
		
		Boolean bancoValidado = confirmacaoDepositoDAO.validarCodBanco(this.nroBordero, codNroLoja, codBanco);
		
		Assert.assertTrue("Banco não foi salvo corretamente.", bancoValidado);
	}
	
	public void informarNumeroBordero() {
		String nroBordero = (String) Serenity.sessionVariableCalled(NRO_BORDERO);
		$(".//*[@id='numeroBordero']").sendKeys(nroBordero);
	}
	
	public void clicarBtnPesquisarDepositos() {
		$(".//*[@id='toolbar:btnSearch']").click();
	}
	
	public void validarBorderoEncontrado() {
		String nroBordero = (String) Serenity.sessionVariableCalled(NRO_BORDERO);
		Assert.assertTrue("Confirmação de depósito não encontrada.", $(".//*[@id='table:tContent']/tbody/tr/td[4]").getText().equals(nroBordero));
	}
	
	public void informarDtaInicioBordero(String dtaInicio) throws InterruptedException {
		$(".//*[@id='dataInicial']").sendKeys(dtaInicio);
	}
	
	public void informarDtaFimBordero(String dtaFinal) {
		$(".//*[@id='dataFinal']").sendKeys(dtaFinal);
	}
	
	public void informarCodLojaBordero() {
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
		$(".//*[@id='searchLoja']/tbody/tr/td[1]/input").clear();
		$(".//*[@id='searchLoja']/tbody/tr/td[1]/input").sendKeys(codNroLoja);
	}
	
	public void clicarBtnImprimirRelatorio() throws InterruptedException {
		controleJanela.abrirPopupClick(this.btnImprimirRelatorioDeposito);
	}
	
	public void validarImpressaoRelatorio() throws InterruptedException {
		relatorio.validarRelatorio();
	}
}