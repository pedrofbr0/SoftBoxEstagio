package br.com.mv.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.Utils;
import net.thucydides.core.pages.PageObject;

public class RegraTratativaPage extends PageObject {

	@FindBy(css = "#btn-criar")
	private WebElement btnCriar;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "a.glyphicon-edit")
	private WebElement iconeEditar;
	
	@FindBy(css = "#descricao-tratativa")
	private WebElement descricaoTratativa;
	
	@FindBy(css = "#periodo-dias")
	private WebElement periodoDias;
	
	@FindBy(css = "#periodo-horas")
	private WebElement periodoHoras;
	
	@FindBy(css = "#periodo-minutos")
	private WebElement periodoMinutos;
	
	@FindBy(xpath = ".//button[contains(text(), 'Salvar')]")
	private WebElement btnSalvar;
	
	Utils utils;
	
	public RegraTratativaPage() {
		this.utils = new Utils();
	}
	
	public void clicarBtnCriar() {
		btnCriar.click();
	}
	
	public void clicarIconeEditar() {
		iconeEditar.click();
	}
	
	public void informarDescricao(String descricao) {
		this.descricaoTratativa.clear();
		this.descricaoTratativa.sendKeys(descricao);
	}
	
	public void informarPeriodoDias(String dias) {
		this.periodoDias.clear();
		this.periodoDias.sendKeys(dias);
	}
	
	public void informarPeriodoHoras(String horas) {
		this.periodoHoras.clear();
		this.periodoHoras.sendKeys(horas);
	}

	public void informarPeriodoMinutos(String minutos) {
		this.periodoMinutos.clear();
		this.periodoMinutos.sendKeys(minutos);
	}
	
	public void clicarBtnSalvar() {
		this.btnSalvar.click();
	}
	
	public void clicarBtnPesquisar() {
		this.btnPesquisar.click();
	}
	
	public void validarMensagemSucesso() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao salvar regra tratativa.", $(".alert").containsText("Registro salvo com sucesso!"));
	}
}