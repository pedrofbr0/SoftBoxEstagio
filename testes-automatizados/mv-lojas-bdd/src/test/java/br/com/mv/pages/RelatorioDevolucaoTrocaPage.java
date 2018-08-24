package br.com.mv.pages;

import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class RelatorioDevolucaoTrocaPage extends PageObject {

	@FindBy(css = "#searchFormasPagamento .buttonSearchField")
	private WebElement btnSearchFormasPagamento;
	
	@FindBy(css = "#searchFormasPagamento .inputText")
	private WebElement txtFormaPagamento;
	
	@FindBy(xpath = "//*[@id='sfLoja']/tbody/tr/td[1]/input")
	private WebElement codNroLoja;
	
	@FindBy(xpath = "//*[@id='sfLoja']/tbody/tr/td[2]/input")
	private WebElement textLoja;
	
	@FindBy(css = "#txtDataInicio")
	private WebElement txtDataInicio;
	
	@FindBy(css = "#txtDataFim")
	private WebElement txtDataFim;
	
	@FindBy(css = "#searchDepto .buttonSearchField")
	private WebElement btnSearchDepto;
	
	@FindBy(css = "#searchDepto .inputText")
	private WebElement txtDepartamento;
	
	@FindBy(css = "#btnImprimir")
	private WebElement btnImprimir;
	
	@FindBy(css = "#btn-imprimir")
	private WebElement btnGerarRelatorio;
	
	ImpressaoPage impressao;
	ControleJanela controleJanela;
	FormaPagamentoPage formaPagtoPage;
	DepartamentoPage deptoPage;
	
	public RelatorioDevolucaoTrocaPage() {
	}
	
	public void pesquisarFormaPagto(String formaPagto) throws InterruptedException {
		this.clicarBtnSearchFormaPagamento();
		
		formaPagtoPage.informarCodigo(formaPagto);
		formaPagtoPage.clicarBtnPesquisar();
		formaPagtoPage.selecionarFormaPagamento();
	}
	
	public void clicarBtnSearchFormaPagamento() throws InterruptedException {
		controleJanela.abrirPopupClick(this.btnSearchFormasPagamento);
	}
	
	public void validarTextoFormaPagamento(String textFormaPagamento) {
		Assert.assertTrue("Valor incorreto para o campo forma de pagamento", this.txtFormaPagamento.getAttribute("value").trim().equals(textFormaPagamento));
	}
	
	public void validarTextoLoja(String textLoja) throws InterruptedException {
		this.textLoja.click();
		Thread.sleep(500);
		Assert.assertTrue("Valor incorreto para o campo loja", this.textLoja.getAttribute("value").trim().equals(textLoja));
	}
	
	public void informarLoja(String codNroLoja) throws InterruptedException {
		this.codNroLoja.sendKeys(codNroLoja);
	}
	
	public void informarDataInicio(String dataInicio) {
		this.txtDataInicio.sendKeys(dataInicio);
	}
	
	public void informarDataFim(String dataFim) {
		this.txtDataFim.sendKeys(dataFim);
	}
	
	public void pesquisarDepartamento(String depto) throws InterruptedException {
		this.clicarBtnSearchDepartamento();
		
		deptoPage.informarCodigo(depto);
		deptoPage.clicarBtnPesquisar();
		deptoPage.selecionarDepartamento();
	}
	
	public void clicarBtnSearchDepartamento() throws InterruptedException {
		controleJanela.abrirPopupClick(this.btnSearchDepto);
	}
	
	public void validarTextoDepartamento(String textDepartamento) {
		Assert.assertTrue("Valor incorreto para o campo departamento", this.txtDepartamento.getAttribute("value").trim().equals(textDepartamento));
	}
	
	public RelatorioDevolucaoTrocaPage clickBtnImprimir() throws InterruptedException {
		controleJanela.abrirPopupClick(this.btnImprimir);
		return this;
	}
	
	public void validarRelatorio() throws InterruptedException {
		int tempoEmissaoRelatorio = 30;
		
		for (int i = 0; i < tempoEmissaoRelatorio; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
		}
		
		new AguardaCarregamento(getDriver()).aguardarCarregamentoArquiteturaAntiga();
		
		try {
			getDriver().findElement(By.cssSelector("#ifRelatorio"));
			
			new ControleJanela(getDriver()).fecharJanelaVoltarUltimoModal(impressao.getBtnFecharRelatorio());
			
			getDriver().close();
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("O relatório não foi encontrado!");
		}
	}
}
