package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class ControleFaturamentoPdvPage extends PageObject {

	@FindBy(css = "#txtPedido")
	private WebElement txtPedido;
	
	@FindBy(css = "#btnPesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#btnFaturar")
	private WebElement btnFaturar;
	
	EmissaoNotaFiscalAvulsaPage emissaoNotaFiscalAvulsa;
	ControleJanela controleJanela;
	
	public ControleFaturamentoPdvPage() {
		
	}

	public void clickBtnPesquisar() {
		btnPesquisar.click();
	}
	
	public void clickBtnFaturar() throws InterruptedException {
		btnFaturar.click();
		
		// Deseja realizar o faturamento dos pedidos selecionados?
		//this.waitFor(ExpectedConditions.alertIsPresent());
		//this.getAlert().accept();
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(true);
		
		emissaoNotaFiscalAvulsa.salvarNroNotaFiscalSessao();
		
		this.getDriver().quit();
	}
	
	public void setTxtPedido() {
		String nroPedido = Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		txtPedido.clear();
		txtPedido.sendKeys(nroPedido);
	}
	
	public void selecionarPedido() throws InterruptedException {
		String nroPedido = Serenity.sessionVariableCalled(NRO_PEDIDO);
		System.out.println("nroPedido: " + nroPedido);
		Thread.sleep(1000);
		$("//table[@id='table:tContent']/tbody/tr/td[2][text()='" + nroPedido + "']/parent::tr/td[1]").click();
	}
}