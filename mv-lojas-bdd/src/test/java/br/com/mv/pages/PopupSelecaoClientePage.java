package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class PopupSelecaoClientePage extends PageObject{

	@FindBy(id = "txtCpf")
	private WebElement txtCpf;
	
	@FindBy(id = "btnPesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(id = "btnSelecionar")
	private WebElement btnSelecionar;
	
	ControleJanela controleJanela;
	
	public PopupSelecaoClientePage() {
		
	}
	
	public void informarCpf(String cpf) {
		txtCpf.clear();
		txtCpf.sendKeys(cpf);
	}
	
	public void clicarBtnPesquisar() {
		btnPesquisar.click();
	}
	
	public void checarClienteEncontrado() {
		$(".//*[@id='table:tContent']/tbody/tr/td[1]/input").click();
	}
	
	public void selecionarCliente() throws InterruptedException {
		controleJanela.fecharJanelaVoltarUltimoModal(btnSelecionar);
	}
}
