package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;
import static br.com.mv.model.SessionVariables.NRO_CARGA_VEICULO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.dao.NotaFiscalDAO;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class NotaFiscalTransferenciaPage extends PageObject {

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
	
	private WebDriverWait wait;
	
	ControleJanela controleJanela;
	
	public NotaFiscalTransferenciaPage() {
		
	}
	
	public WebElement getBtnPesquisarNotaFiscal() {
		return btnPesquisarNotaFiscal;
	}

	public NotaFiscalTransferenciaPage clickPesquisarNotaFiscal() {
		this.btnPesquisarNotaFiscal.click();
		return this;
	}
	
	public WebElement getGrupoEstoque() {
		return gruposEstoque;
	}
	
	public WebElement getQtdConferida() {
		return qtdConferida;
	}
	
	public NotaFiscalTransferenciaPage setGrupoEstoque(String grupoEstoque) throws InterruptedException {
		new Select(this.gruposEstoque).selectByVisibleText(grupoEstoque);
		return this;
	}
	
	public NotaFiscalTransferenciaPage setQtdConferida(String qtde) throws InterruptedException {
		this.qtdConferida.clear();
		this.qtdConferida.sendKeys(qtde);
		return this;
	}
	
	public NotaFiscalTransferenciaPage setObservacao(String obs) throws InterruptedException {
		this.txtObservacao.clear();
		this.txtObservacao.sendKeys(obs);
		return this;
	}
	
	public NotaFiscalTransferenciaPage clickProcessar() throws InterruptedException {
		this.processar.click();
		/*
		// Realizar o processamento para liberação de pré-disponível ?
		this.waitFor(ExpectedConditions.alertIsPresent());
		this.getAlert().dismiss();
				
		// Confirma o processamento desta(s) nota(s) fiscais ?
		this.waitFor(ExpectedConditions.alertIsPresent());
		this.getAlert().accept();
				
		// Apareceu um Bloquear janelas de confirmação
		
		this.waitFor(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(false);
				
		new MensagemPage().clickBtnFechar();
				
		// Procedimento realizado com sucesso!
		controleJanela.voltarJanelaPrincipal(true);
				*/
				
		return this;
	}
	
	public WebElement getBtnProcessar() {
		return processar;
	}
	
	public void clicarBotaoCancelarProcessarLiberacaoPreDisponivel() throws InterruptedException {
		//this.waitFor(ExpectedConditions.alertIsPresent());
		//Thread.sleep(2000);
		//this.getAlert().dismiss();
		System.out.println("1");
		
		new WebDriverWait(this.getDriver(), 20).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		this.getDriver().switchTo().alert().dismiss();
		
		System.out.println("2");
		//$("#processar").click();
		new WebDriverWait(this.getDriver(), 20).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		this.getDriver().switchTo().alert().accept();
		
		System.out.println("3");
		
	}
	
	public void clicarBotaoOkConfirmarProcessamentoNota() throws InterruptedException {
		//this.waitFor(ExpectedConditions.alertIsPresent());
		//Thread.sleep(2000);
		//this.getAlert().accept();
	}
	
	public void salvarCargaNaSessao(String deposito, String lojaPedido) {
		
		String nroPedido = Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		int nroCargaVeiculo = new NotaFiscalDAO().getCargaVeiculoPorPedidoLoja(Integer.parseInt(deposito), Integer.parseInt(lojaPedido), Integer.parseInt(nroPedido));
		System.out.println("nroCargaVeiculo: " + nroCargaVeiculo);
		Serenity.setSessionVariable(NRO_CARGA_VEICULO).to(Integer.toString(nroCargaVeiculo));
	}
	
	public void salvarCargaPorTipoControleFaturamentoNaSessao(String deposito, String lojaPedido, String tipoControleFaturamento) {
		
		String nroPedido = Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		int nroCargaVeiculo = new NotaFiscalDAO().getCargaVeiculoPorPedidoLojaETipoControleFaturamento(Integer.parseInt(deposito), Integer.parseInt(lojaPedido), Integer.parseInt(nroPedido), tipoControleFaturamento);
		System.out.println("nroCargaVeiculo: " + nroCargaVeiculo);
		Serenity.setSessionVariable(NRO_CARGA_VEICULO).to(Integer.toString(nroCargaVeiculo));
	}
	
}