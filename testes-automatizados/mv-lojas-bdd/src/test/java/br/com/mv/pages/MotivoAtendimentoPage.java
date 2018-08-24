package br.com.mv.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.Utils;
import net.thucydides.core.pages.PageObject;

public class MotivoAtendimentoPage extends PageObject {

	@FindBy(css = "#btn-criar")
	private WebElement btnCriar;
	
	@FindBy(css = "#descricao-motivo")
	private WebElement descricaoMotivo;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(xpath = ".//button[contains(text(), 'Salvar')]")
	private WebElement btnSalvar;
	
	@FindBy(css = "a.glyphicon-edit")
	private WebElement iconeEditar;
	
	@FindBy(css = "a.glyphicon-trash")
	private WebElement iconeExcluir;
	
	@FindBy(xpath = ".//button[contains(text(), 'Sim')]")
	private WebElement btnSim;
	
	Utils utils;
	
	public MotivoAtendimentoPage() {
		this.utils = new Utils();
	}
	
	public void informarDescricao(String descricao) {
		descricaoMotivo.clear();
		descricaoMotivo.sendKeys(descricao);
	}
	
	public void clicarBtnSim() {
		this.btnSim.click();
	}
	
	public void clicarBtnCriar() {
		btnCriar.click();
	}
	
	public void clicarIconeExcluir() {
		iconeExcluir.click();
	}
	
	public void clicarIconeEditar() {
		iconeEditar.click();
	}
	
	public void clicarBtnPesquisar() {
		btnPesquisar.click();
	}
	
	public void clicarBtnSalvar() {
		btnSalvar.click();
	}
	
	public void validarMensagemSucesso() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao salvar motivo atendimento.", $(".alert").containsText("Registro salvo com sucesso!"));
	}
	
	public void validarMotivoJaExistente() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao validar existência motivo atendimento.", $(".alert").containsText("Já existe um motivo cadastrado com essa descrição."));
	}
	
	public void validarMensagemSucessoExclusao() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("Erro ao excluir motivo atendimento.", $(".alert").containsText("Motivo excluido com sucesso!"));
	}
}