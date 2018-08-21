package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class PopupSelecaoFornecedorPage extends PageObject{

	@FindBy(id = "txtCodigo")
	private WebElement txtCodigo;
	
	@FindBy(id = "toolbar:btnSearch")
	private WebElement btnPesquisar;
	
	@FindBy(id = "btnSelecionar")
	private WebElement btnSelecionar;
	
	@FindBy(xpath = ".//*[@id='table:tContent']/tbody/tr/td[1]/input")
	private WebElement chkSelecaoFornecedor;
	
	ControleJanela controleJanela;
	
	public PopupSelecaoFornecedorPage() {
		
	}
	
	public void informarCodigo(String cpf) {
		txtCodigo.clear();
		txtCodigo.sendKeys(cpf);
	}
	
	public void clicarBtnPesquisar() {
		btnPesquisar.click();
	}
	
	public void selecionarFornecedorEncontrado() throws InterruptedException {
		controleJanela.fecharJanelaVoltarUltimoModal(chkSelecaoFornecedor);
	}
	
}
