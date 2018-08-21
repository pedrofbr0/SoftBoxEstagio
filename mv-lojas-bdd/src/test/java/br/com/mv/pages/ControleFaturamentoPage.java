package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.dao.NotaFiscalDAO;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class ControleFaturamentoPage extends PageObject {

	@FindBy(css = "table#scCarga input.buttonSearchField")
	private WebElement btnSearchCarga;
	
	@FindBy(css = "#toolbarvazia  input[value='Filtro de Controles de Faturamentos']")
	private WebElement btnFiltroControleFaturamento;
	
	@FindBy(css = "#btnAtribuir")
	private WebElement btnAtribuir;
	
	@FindBy(css = "#comboTipoFrete")
	private WebElement tipoFrete;
	
	@FindBy(css = "#btnFaturar")
	private WebElement btnFaturar;
	
	@FindBy(css = "#btnControleDevolucoes")
	private WebElement btnControleDevolucoes;
	
	@FindBy(id = "searchPedidoExterno")
	private WebElement searchPedidoExterno;
	
	@FindBy(id = "searchLojaPedidoExterno")
	private WebElement searchLojaPedidoExterno;
	
	@FindBy(id = "comboTipoRecolhimento")
	private WebElement comboTipoRecolhimento;
	
	@FindBy(id = "btnConsultar")
	private WebElement btnConsultar;
	
	@FindBy(id = "comboEmpresaEnterprise")
	private WebElement comboEmpresaEnterprise;
	
	@FindBy(id = "btnAssumirDev")
	private WebElement btnAssumirDev;
	
	@FindBy(xpath = "//table/tbody/tr/td[contains(text(), 'Loja de Origem')]/parent::tr/td[3]/input")
	private WebElement btnPesquisarLojaOrigem;
	
	@FindBy(id = "cbxEmpresa")
	private WebElement cbxEmpresa;
	
	@FindBy(css = "#searchLoja input")
	private WebElement codNroLoja;
	
	@FindBy(css = "input[value='Ok']")
	private WebElement btnOkPesquisarEmpresa;
	
	@FindBy(css = "#txtPedido")
	private WebElement txtPedido;
	
	ControleJanela controleJanela;
	
	public ControleFaturamentoPage() {
		
	}
	
	public ControleFaturamentoPage setPedido() {
		this.txtPedido.clear();
		this.txtPedido.sendKeys((String) Serenity.sessionVariableCalled(NRO_PEDIDO));
		return this;
	}
	
	public ControleFaturamentoPage setEmpresaEnterprise(String empresa) {
		new Select(this.comboEmpresaEnterprise).selectByVisibleText(empresa);
		return this;
	}
	
	public ControleFaturamentoPage clickAssumirDevolucao() {
		this.btnAssumirDev.click();
		return this;
	}
	
	public WebElement getBtnSearchCarga() {
		return btnSearchCarga;
	}
	
	public WebElement getBtnFiltroControleFaturamento() {
		return btnFiltroControleFaturamento;
	}
	
	public WebElement getBtnControleDevolucoes() {
		return btnControleDevolucoes;
	}
	
	public WebElement getBtnFaturar() {
		return btnFaturar;
	}
	
	public ControleFaturamentoPage clickAtribuir() {
		this.btnAtribuir.click();
		return this;
	}
	
	public ControleFaturamentoPage clickFaturar() throws InterruptedException {
		/*Actions actions = new Actions(this.getDriver());
		actions.moveToElement(btnFaturar).build().perform();
		this.btnFaturar.click();*/
		
		//this.btnFaturar.click();
		//$(".//*[@id='btnFaturar']").click();
		
		return this;
	}
	
	public ControleFaturamentoPage clickControleDevolucoes() {
		this.btnControleDevolucoes.click();
		return this;
	}
	
	public ControleFaturamentoPage setTipoFrete(String tipoFrete) {
		new Select(this.tipoFrete).selectByVisibleText(tipoFrete);
		return this;
	}
	
	public String getTipoFrete() {
		return this.tipoFrete.getText();
	}
	
	public ControleFaturamentoPage setLojaPedidoExterno(String nroLojaPedidoExterno) {
		this.searchLojaPedidoExterno.clear();
		this.searchLojaPedidoExterno.sendKeys(nroLojaPedidoExterno);
		return this;
	}
	
	public ControleFaturamentoPage setPedidoExterno(String nroPedidoExterno) {
		this.searchPedidoExterno.clear();
		this.searchPedidoExterno.sendKeys(nroPedidoExterno);
		return this;
	}
	
	public ControleFaturamentoPage setOpcaoRecolhimento(String opcaoRecolhimento) {
		new Select(this.comboTipoRecolhimento).selectByVisibleText(opcaoRecolhimento);
		return this;
	}
	
	public ControleFaturamentoPage clickConsultar() {
		this.btnConsultar.click();
		return this;
	}
	
	public ControleFaturamentoPage selecionarControleFaturamento() throws InterruptedException {
		// Seleciona o primeiro encontrado
		$("//table[@id='table:tContent']/tbody/tr[1]/td[1]/input").click();
		return this;
	}
	
	public ControleFaturamentoPage limparLojaOrigem() {
		$("//table/tbody/tr/td[contains(text(), 'Loja de Origem')]/parent::tr/td[4]/input").click();
		return this;
	}
	
	public WebElement getBtnPesquisarLojaOrigem() {
		return this.btnPesquisarLojaOrigem;
	}
	
	public ControleFaturamentoPage setCbxEmpresa(String txtEmpresa) {
		new Select(cbxEmpresa).selectByVisibleText(txtEmpresa);
		return this;
	}
	
	public ControleFaturamentoPage setCodNroLoja(String txtCodNroLoja) {
		codNroLoja.clear();
		codNroLoja.sendKeys(txtCodNroLoja);
		$(".buttonSearchField").click();
		return this;
	}
	
	public WebElement getBtnOkPesquisarEmpresa() {
		return this.btnOkPesquisarEmpresa;
	}
	
	public void clicarBotaoOkIniciarProcessoFaturamento() throws InterruptedException {
		//this.waitFor(ExpectedConditions.alertIsPresent());
		//this.getAlert().accept();
		
		$(".//*[@id='btnFaturar']").click();
		
		System.out.println("1");
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		this.getDriver().switchTo().alert().accept();
		
		System.out.println("2");
		//$("#processar").click();
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		this.getDriver().switchTo().alert().dismiss();
		
		System.out.println("3");
	}
	
	public void clicarBotaoCancelarInformarObsNota() {
		//this.waitFor(ExpectedConditions.alertIsPresent());
		//this.getAlert().dismiss();
	}
	
	public void aprovarNotaFiscalPorTipoControleFaturamento(String loja, String lojaPedido, String tipoControleFaturamento) {
		String nroPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO);
		int nroNFe = new NotaFiscalDAO().getNotaFiscalPorTipoControleFaturamento(Integer.parseInt(loja), Integer.parseInt(lojaPedido), Integer.parseInt(nroPedido), tipoControleFaturamento);
		
		System.out.println("nroNFe: " + nroNFe);
		
		new NotaFiscalDAO().aprovarNFe(Integer.parseInt(loja), nroNFe);
	}
	
}