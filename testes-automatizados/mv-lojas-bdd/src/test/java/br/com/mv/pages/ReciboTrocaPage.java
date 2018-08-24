package br.com.mv.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.Utils;
import net.thucydides.core.pages.PageObject;

public class ReciboTrocaPage extends PageObject {

	@FindBy(css = "a[title='Salvar Motivo']")
	private WebElement btnSalvarMotivo;
	
	@FindBy(css = "#local-produto")
	private WebElement localProduto;
	
	@FindBy(css = "#motivo-atendimento")
	private WebElement motivoAtendimento;
	
	@FindBy(xpath = ".//button[contains(text(), 'Sim')]")
	private WebElement btnSim;
	
	@FindBy(css = "a[title='Aprovar']")
	private WebElement iconeAprovar;
	
	@FindBy(css = "a[title='Reprovar']")
	private WebElement iconeReprovar;
	
	@FindBy(css = "textarea[ng-model='observacaoAprovaReprova']")
	private WebElement observacaoAprovaReprova;
	
	@FindBy(xpath = ".//button[contains(text(), 'OK')]")
	private WebElement btnOk;
	
	@FindBy(xpath = ".//button[contains(text(), 'Ok')]")
	private WebElement btnOkAvaliacao;
	
	@FindBy(xpath = ".//button[contains(text(), 'Ok')]")
	private WebElement btnOkDataEntrega;
	
	@FindBy(xpath = ".//button[contains(text(), 'OK')]")
	private WebElement btnOkAgendamentoEntrega;
	
	@FindBy(css = "#btn-imprimir")
	private WebElement btnImprimir;
	
	@FindBy(css = ".fa-truck")
	private WebElement btnAgendamentoEntrega;
	
	@FindBy(xpath = "html/body/div[4]/div/div/div[2]/div[2]/table/tbody[1]/tr/td[1]/input")
	private WebElement opcaoDataEntrega;
	
	@FindBy(css = "#btn-confirmar")
	private WebElement btnConfirmarEnderecoCliente;
	
	Utils utils;
	
	public ReciboTrocaPage() {
		this.utils = new Utils();
	}
	
	public void clicarBtnSalvarMotivo() {
		btnSalvarMotivo.click();
	}
	
	public void selecionarLocalProduto(String localProduto) {
		new Select(this.localProduto).selectByVisibleText(localProduto);
	}
	
	public void selecionarMotivoAtendimento(String motivoAtendimento) {
		new Select(this.motivoAtendimento).selectByVisibleText(motivoAtendimento);
	}
	
	public void selecionarOpcaoSemReversao() throws InterruptedException {
		Thread.sleep(3000);
		$("input#radio-sem-reversao[value=N]").click();
	}
	
	public void selecionarOpcaoComReversao() throws InterruptedException {
		Thread.sleep(3000);
		$("input#radio-sem-reversao[value=S]").click();
	}
	
	public void mensagemSucessoReciboTroca() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao gerar recibo de troca.", $(".alert").containsText("Recibo de troca gerado com sucesso!"));
	}
	
	public void mensagemSucessoReciboTrocaSimplificada() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao gerar recibo de troca simplificada.", $(".alert").containsText("Relatório gerado com sucesso!"));
	}
	
	public void clicarBtnSim() {
		this.btnSim.click();
	}
	
	public void clicarIconeAprovar() throws InterruptedException {
		Thread.sleep(1000);
		this.iconeAprovar.click();
	}
	
	public void clicarIconeReprovar() {
		this.iconeReprovar.click();
	}
	
	public void informarObservacaoAprovaReprova(String obs) {
		this.observacaoAprovaReprova.sendKeys(obs);
	}
	
	public void clicarBtnOk() throws InterruptedException {
		this.btnOk.click();
	}
	
	public void clicarBtnOkAvaliacao() throws InterruptedException {
		this.btnOkAvaliacao.click();
	}
	
	public void clicarBtnImprimir() throws InterruptedException {
		this.btnImprimir.click();
	}
	
	public void clicarBtnAgendamentoEntrega() throws InterruptedException {
		this.btnAgendamentoEntrega.click();
	}
	
	public void selecionarOpcaoDataEntrega() throws InterruptedException {
		this.opcaoDataEntrega.click();
	}
	
	public void clicarBtnOkDataEntrega() throws InterruptedException {
		this.btnOkDataEntrega.click();
	}
	
	public void clicarBtnSimOkAgendamentoEntrega() throws InterruptedException {
		this.btnOkAgendamentoEntrega.click();
	}
	
	public void clicarBtnConfirmarEnderecoCliente() throws InterruptedException {
		this.btnConfirmarEnderecoCliente.click();
	}
}