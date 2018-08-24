package br.com.mv.PageFactory.controleFaturamentoCarga;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.PageFactory.preRecibo.PreReciboTrocaDevolucao;

public class ControleFaturamentoCarga {

	WebDriver driver;
	
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
	
	public ControleFaturamentoCarga(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ControleFaturamentoCarga setEmpresaEnterprise(String empresa) {
		new Select(this.comboEmpresaEnterprise).selectByVisibleText(empresa);
		return this;
	}
	
	public ControleFaturamentoCarga clickAssumirDevolucao() {
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
	
	public ControleFaturamentoCarga clickAtribuir() {
		this.btnAtribuir.click();
		return this;
	}
	
	public ControleFaturamentoCarga clickFaturar() {
		this.btnFaturar.click();
		return this;
	}
	
	public ControleFaturamentoCarga clickControleDevolucoes() {
		this.btnControleDevolucoes.click();
		return this;
	}
	
	public ControleFaturamentoCarga setTipoFrete(String tipoFrete) {
		new Select(this.tipoFrete).selectByVisibleText(tipoFrete);
		return this;
	}
	
	public String getTipoFrete() {
		return this.tipoFrete.getText();
	}
	
	public ControleFaturamentoCarga setLojaPedidoExterno(String nroLojaPedidoExterno) {
		this.searchLojaPedidoExterno.clear();
		this.searchLojaPedidoExterno.sendKeys(nroLojaPedidoExterno);
		return this;
	}
	
	public ControleFaturamentoCarga setPedidoExterno(String nroPedidoExterno) {
		this.searchPedidoExterno.clear();
		this.searchPedidoExterno.sendKeys(nroPedidoExterno);
		return this;
	}
	
	public ControleFaturamentoCarga setOpcaoRecolhimento(String opcaoRecolhimento) {
		new Select(this.comboTipoRecolhimento).selectByVisibleText(opcaoRecolhimento);
		return this;
	}
	
	public ControleFaturamentoCarga clickConsultar() {
		this.btnConsultar.click();
		return this;
	}
	
	public ControleFaturamentoCarga selecionarControleFaturamento() throws InterruptedException {
		// Seleciona o primeiro encontrado
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr[1]/td[1]/input")).click();
		return this;
	}
	
	public ControleFaturamentoCarga limparLojaOrigem() {
		driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(), 'Loja de Origem')]/parent::tr/td[4]/input")).click();
		return this;
	}
	
	public WebElement getBtnPesquisarLojaOrigem() {
		return this.btnPesquisarLojaOrigem;
	}
	
	public ControleFaturamentoCarga setCbxEmpresa(String txtEmpresa) {
		new Select(cbxEmpresa).selectByVisibleText(txtEmpresa);
		return this;
	}
	
	public ControleFaturamentoCarga setCodNroLoja(String txtCodNroLoja) {
		codNroLoja.clear();
		codNroLoja.sendKeys(txtCodNroLoja);
		driver.findElement(By.cssSelector(".buttonSearchField")).click();
		return this;
	}
	
	public WebElement getBtnOkPesquisarEmpresa() {
		return this.btnOkPesquisarEmpresa;
	}
	
	
}
