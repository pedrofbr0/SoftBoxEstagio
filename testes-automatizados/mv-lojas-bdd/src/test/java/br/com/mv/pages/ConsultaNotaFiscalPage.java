package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;
import static br.com.mv.model.SessionVariables.COD_NRO_LOJA;
import static br.com.mv.model.SessionVariables.NRO_NOTA_FISCAL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class ConsultaNotaFiscalPage extends PageObject {
	
	@FindBy(id = "cbxProcessado")
	private WebElement cbxProcessado;
	
	@FindBy(id = "txtNumeroPedido")
	private WebElement txtNumeroPedido;
	
	@FindBy(id = "txtNumeroLojaPedido")
	private WebElement txtNumeroLojaPedido;
	
	@FindBy(id = "txtNumeroNf")
	private WebElement txtNumeroNf;
	
	@FindBy(xpath = ".//*[@id='toolbarvazia:btnSearch']")
	private WebElement btnPesquisar;
	
	public ConsultaNotaFiscalPage() {
		
	}
	
	public void selecionarSomenteNotas(String somenteNotas) {
		new Select(cbxProcessado).selectByVisibleText(somenteNotas);
	}
	
	public void informarNumeroPedido() {
		
		//Serenity.setSessionVariable(NRO_PEDIDO).to("4908315");
		String nroPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		txtNumeroPedido.clear();
		txtNumeroPedido.sendKeys(nroPedido);
	}
	
	public void informarCodigoLoja() {
		
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
		
		txtNumeroLojaPedido.clear();
		txtNumeroLojaPedido.sendKeys(codNroLoja);
	}
	
	public void informarNumeroNota() {
		
		String nroNotaFiscal = (String) Serenity.sessionVariableCalled(NRO_NOTA_FISCAL);
		
		txtNumeroNf.clear();
		txtNumeroNf.sendKeys(nroNotaFiscal);
	}
	
	public void validarNotasEncontradas(String tipoNota) {
		$(".//*[@id='table:tContent']/tbody/tr/td[contains(text(),'" + tipoNota + "')]");
	}
	
	public void clicarBtnPesquisar() {
		btnPesquisar.click();
	}
}