package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.PERIODO_DATA_ABERTURA;
import static br.com.mv.model.SessionVariables.PERIODO_DATA_FECHAMENTO;

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
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class RelatorioDemonstrativoVendaServicosPage extends PageObject {

	@FindBy(css = "input[value='Período']")
	private WebElement btnPeriodo;
	
	@FindBy(xpath = "//*[@id=\"table:tContent\"]/tbody/tr[1]/td[1]/input")
	private WebElement periodoEncontrado;
	
	@FindBy(xpath = "//*[@id=\"table:tContent\"]/tbody/tr[1]/td[2]")
	private WebElement textoDataAbertura;
	
	@FindBy(xpath = "//*[@id=\"table:tContent\"]/tbody/tr[1]/td[3]")
	private WebElement textoDataFechamento;
	
	@FindBy(css = "input[value='Selecionar']")
	private WebElement btnSelecionar;
	
	@FindBy(css = "#dataIni")
	private WebElement txtDataInicio;
	
	@FindBy(css = "#dataFin")
	private WebElement txtDataFim;
	
	@FindBy(css = "input[value='OK']")
	private WebElement btnOk;
	
	@FindBy(css = "#btnImprimir")
	private WebElement btnImprimir;
	
	@FindBy(css = "#btn-imprimir")
	private WebElement btnGerarRelatorio;
	
	ImpressaoPage impressao;
	ControleJanela controleJanela;
	
	public RelatorioDemonstrativoVendaServicosPage() {
	}
	
	public void clicarBtnPeriodo() throws InterruptedException {
		controleJanela.abrirPopupClick(this.btnPeriodo);
	}
	
	public void selecionarPeriodoEncontrado() throws InterruptedException {
		this.periodoEncontrado.click();
		this.gravarDatasSessao();
	}
	
	public void gravarDatasSessao() throws InterruptedException {
		Serenity.setSessionVariable(PERIODO_DATA_ABERTURA).to(this.textoDataAbertura.getText().trim());
		Serenity.setSessionVariable(PERIODO_DATA_FECHAMENTO).to(this.textoDataFechamento.getText().trim());
	}
	
	public void clicarBtnSelecionar() throws InterruptedException {
		controleJanela.fecharJanelaVoltarUltimoModal(this.btnSelecionar);
	}
	
	public void compararDataAbertura() throws InterruptedException {
		Thread.sleep(500);
		Assert.assertTrue("As Datas de abertura estao divergentes.", this.txtDataInicio.getAttribute("value").trim().equals(Serenity.sessionVariableCalled(PERIODO_DATA_ABERTURA)));
	}
	
	public void compararDataFechamento() throws InterruptedException {
		Thread.sleep(500);
		Assert.assertTrue("As Datas de fechamento estao divergentes.", this.txtDataFim.getAttribute("value").trim().equals(Serenity.sessionVariableCalled(PERIODO_DATA_FECHAMENTO)));
	}
	
	public void clicarBtnOk() throws InterruptedException {
		this.compararDataAbertura();
		this.compararDataFechamento();
		
		Thread.sleep(500);
		controleJanela.abrirPopupClick(this.btnOk);
	}
	
	public RelatorioDemonstrativoVendaServicosPage clickBtnImprimir() throws InterruptedException {
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
