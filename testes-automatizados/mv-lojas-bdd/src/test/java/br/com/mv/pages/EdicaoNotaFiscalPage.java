package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_NOTA_FISCAL;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class EdicaoNotaFiscalPage extends PageObject {
	
	@FindBy(id = "filtro-nf")
	private WebElement filtroNF;
	
	@FindBy(id = "filtro-serie-nf")
	private WebElement filtroSerieNF;
	
	@FindBy(id = "filtro-loja-nf")
	private WebElement filtroLojaNF;
	
	@FindBy(css = "#filtro-loja-nf ~ ul li a")
	private WebElement opcaoFiltroLojaNF;
	
	@FindBy(id = "btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(id = "radio-nf")
	private WebElement radioNF;
	
	@FindBy(xpath = "html/body/div[2]/div/div[6]/table/tbody/tr/td[7]/button")
	private WebElement btnAcao;
	
	@FindBy(css = "#vrBaseIcmsItemNf")
	private WebElement vrBaseIcmsItemNF;
	
	@FindBy(xpath = ".//button[contains(text(), 'Salvar')]")
	private WebElement btnSalvar;
	
	Utils utils;
	
	public EdicaoNotaFiscalPage() {
		this.utils = new Utils();
	}
	
	public void informarNumeroNF() {
		String nroNF = (String) Serenity.sessionVariableCalled(NRO_NOTA_FISCAL);
		
		this.filtroNF.clear();
		this.filtroNF.sendKeys(nroNF);
	}
	
	public void informarSerieNF(String serieNF) {
		this.filtroSerieNF.clear();
		this.filtroSerieNF.sendKeys(serieNF);
	}
	
	public void informarLojaNF(String lojaNF) throws InterruptedException {
		utils.preencherCampoTypeAHead(lojaNF, this.filtroLojaNF);
		Thread.sleep(1000);
		this.opcaoFiltroLojaNF.click();
	}
	
	public void clicarBtnPesquisar() {
		this.btnPesquisar.click();
	}
	
	public void selecionarNotaEncontrada() {
		this.radioNF.click();
	}
	
	public void clicarBtnAcao() {
		this.btnAcao.click();
	}
	
	public void informarValorBaseICMS(String vrBaseICMS) {
		this.vrBaseIcmsItemNF.clear();
		this.vrBaseIcmsItemNF.sendKeys(vrBaseICMS);
	}
	
	public void clicarBtnSalvar() {
		this.btnSalvar.click();
	}
	
	public void validarMensagemSucesso() {
		Assert.assertTrue("Erro ao salvar nota fiscal.", $(".modal-content .modal-body p").containsText("Registro salvo com sucesso!"));
	}
}