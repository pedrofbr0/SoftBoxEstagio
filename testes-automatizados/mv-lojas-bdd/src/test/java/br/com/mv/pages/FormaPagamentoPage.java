package br.com.mv.pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class FormaPagamentoPage extends PageObject {

	@FindBy(css = "#txtCodigo")
	private WebElement txtCodigo;
	
	@FindBy(css = "input[namebutton='Pesquisar'")
	private WebElement btnPesquisar;
	
	@FindBy(xpath = "//*[@id='table:tContent']/tbody/tr/td[2]")
	private WebElement formaPagamento;
	
	ControleJanela controleJanela;
	
	public FormaPagamentoPage() {
	}
	
	public void informarCodigo(String codigo) {
		this.txtCodigo.sendKeys(codigo);
	}
	
	public void clicarBtnPesquisar() {
		this.btnPesquisar.click();
	}
	
	public FormaPagamentoPage selecionarFormaPagamento() throws InterruptedException {
		controleJanela.fecharJanelaVoltarUltimoModal(this.formaPagamento);
		return this;
	}
}