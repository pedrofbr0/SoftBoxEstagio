package br.com.mv.notaFiscalTransferencia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class NotaFiscalTransferencia {

	WebDriver driver;
	
	@FindBy(id = "btnPesquisarNotaFiscal")
	private WebElement btnPesquisarNotaFiscal;
	
	@FindBy(id = "processar")
	private WebElement processar;
	
	@FindBy(id = "txtObservacao")
	private WebElement txtObservacao;
	
	@FindBy(xpath = "//table[@id='tabelaCamposLinha:tContent']/tbody/tr[1]/td[8]/input")
	private WebElement qtdConferida;
	
	@FindBy(xpath = "//table[@id='table:tContent']/tbody/tr[1]/td[7]/select")
	private WebElement gruposEstoque;
	
	public NotaFiscalTransferencia(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getBtnPesquisarNotaFiscal() {
		return btnPesquisarNotaFiscal;
	}

	public NotaFiscalTransferencia clickPesquisarNotaFiscal() {
		this.btnPesquisarNotaFiscal.click();
		return this;
	}
	
	public WebElement getGrupoEstoque() {
		return gruposEstoque;
	}
	
	public WebElement getQtdConferida() {
		return qtdConferida;
	}
	
	public NotaFiscalTransferencia setGrupoEstoque(String grupoEstoque) throws InterruptedException {
		new Select(this.gruposEstoque).selectByVisibleText(grupoEstoque);
		return this;
	}
	
	public NotaFiscalTransferencia setQtdConferida(String qtde) throws InterruptedException {
		this.qtdConferida.clear();
		this.qtdConferida.sendKeys(qtde);
		return this;
	}
	
	public NotaFiscalTransferencia setObservacao() throws InterruptedException {
		this.txtObservacao.clear();
		this.txtObservacao.sendKeys("Teste aut.");
		return this;
	}
	
	public NotaFiscalTransferencia clickProcessar() {
		this.processar.click();
		return this;
	}
	
	public WebElement getBtnProcessar() {
		return processar;
	}
	
}
