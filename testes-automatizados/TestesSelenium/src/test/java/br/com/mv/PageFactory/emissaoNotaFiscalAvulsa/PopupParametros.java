package br.com.mv.PageFactory.emissaoNotaFiscalAvulsa;
	
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.AguardaCarregamento;

public class PopupParametros {
	
	WebDriver driver;
	
	@FindBy(id = "txtNumeroCupomFiscal")
	private WebElement nroCumpomFiscal;
	
	@FindBy(id = "cmbEmpresasCupomFiscal")
	private WebElement empresaCumpomFiscal;
	
	@FindBy(id = "txtCodNumeroLojaCupomFiscal")
	private WebElement codNroLojaCumpomFiscal;
	
	@FindBy(id = "txtNumeroSerieCF")
	private WebElement nroSerieCF;
	
	@FindBy(id = "btnBuscarCupomFiscal")
	private WebElement btnSearchBuscarCupomFiscal;
	
	@FindBy(id = "txtNumeroNotaFiscal")
	private WebElement nroNotaFiscal;
	
	@FindBy(id = "cmbEmpresasNotaFiscal")
	private WebElement empresaNotaFiscal;
	
	@FindBy(id = "txtCodNumeroLojaNotaFiscal")
	private WebElement codNroLojaNotaFiscal;
	
	@FindBy(id = "txtNumeroSerieNF")
	private WebElement nroSerieNF;
	
	@FindBy(id = "btnBuscarNotaFiscal")
	private WebElement btnSearchBuscarNotaFiscal;
	
	@FindBy(id = "txtNumeroCF")
	private WebElement numeroCumpom;
	
	@FindBy(id = "txtNumeroNF")
	private WebElement numeroNotaFiscal;
	
	@FindBy(id = "txtDataEmissao")
	private WebElement dataEmissao;
	
	@FindBy(id = "txtValorTotalNF")
	private WebElement valorTotalNF;
	
	@FindBy(id = "btnConcluir")
	private WebElement btnConcluir;
	
	@FindBy(id = "cbxEmpresa")
	private WebElement cbxEmpresa;
	
	@FindBy(id = "cbxLojaDestino")
	private WebElement cbxLojaDestino;
	
	@FindBy(id = "cbxGrupoEstoqueDestino")
	private WebElement cbxGrupoEstoqueDestino;
	
	public PopupParametros(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getNroCumpomFiscal() {
		return nroCumpomFiscal.getAttribute("value");
	}

	public PopupParametros setNroCumpomFiscal(String nroCumpomFiscal) {
		this.nroCumpomFiscal.clear();
		this.nroCumpomFiscal.sendKeys(nroCumpomFiscal);
		return this;
	}

	public String getEmpresaCumpomFiscal() {
		return new Select(empresaCumpomFiscal).getFirstSelectedOption().getText();
	}

	public PopupParametros setEmpresaCumpomFiscal(String empresaCumpomFiscal) {
		new Select(this.empresaCumpomFiscal).selectByVisibleText(empresaCumpomFiscal);
		return this;
	}

	public String getCodNroLojaCumpomFiscal() {
		return codNroLojaCumpomFiscal.getAttribute("value");
	}

	public PopupParametros setCodNroLojaCumpomFiscal(String codNroLojaCumpomFiscal) {
		this.codNroLojaCumpomFiscal.clear();
		this.codNroLojaCumpomFiscal.sendKeys(codNroLojaCumpomFiscal);
		return this;
	}

	public String getNroSerieCF() {
		return nroSerieCF.getAttribute("value");
	}

	public PopupParametros setNroSerieCF(String nroSerieCF) {
		this.nroSerieCF.clear();
		this.nroSerieCF.sendKeys(nroSerieCF);
		return this;
	}

	public WebElement getBtnSearchBuscarCupomFiscal() {
		return btnSearchBuscarCupomFiscal;
	}

	public PopupParametros clickBtnSearchBuscarCupomFiscal() {
		this.btnSearchBuscarCupomFiscal.click();
		return this;
	}

	public String getNroNotaFiscal() {
		return nroNotaFiscal.getAttribute("value");
	}

	public PopupParametros setNroNotaFiscal(String nroNotaFiscal) {
		this.nroNotaFiscal.clear();
		this.nroNotaFiscal.sendKeys(nroNotaFiscal);
		return this;
	}

	public String getEmpresaNotaFiscal() {
		return new Select(empresaNotaFiscal).getFirstSelectedOption().getText();
	}

	public PopupParametros setEmpresaNotaFiscal(String empresaNotaFiscal) {
		new Select(this.empresaNotaFiscal).selectByVisibleText(empresaNotaFiscal);
		return this;
	}

	public String getCodNroLojaNotaFiscal() {
		return codNroLojaNotaFiscal.getAttribute("value");
	}

	public PopupParametros setCodNroLojaNotaFiscal(String codNroLojaNotaFiscal) {
		this.codNroLojaNotaFiscal.clear();
		this.codNroLojaNotaFiscal.sendKeys(codNroLojaNotaFiscal);
		return this;
	}

	public String getNroSerieNF() {
		return nroSerieNF.getAttribute("value");
	}

	public PopupParametros setNroSerieNF(String nroSerieNF) {
		this.nroSerieNF.clear();
		this.nroSerieNF.sendKeys(nroSerieNF);
		return this;
	}

	public WebElement getBtnSearchBuscarNotaFiscal() {
		return btnSearchBuscarNotaFiscal;
	}

	public PopupParametros clickBtnSearchBuscarNotaFiscal() {
		this.btnSearchBuscarNotaFiscal.click();
		return this;
	}

	public String getNumeroCumpom() {
		return numeroCumpom.getAttribute("value");
	}

	public PopupParametros setNumeroCumpom(String numeroCumpom) {
		this.numeroCumpom.clear();
		this.numeroCumpom.sendKeys(numeroCumpom);
		return this;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal.getAttribute("value");
	}

	public PopupParametros setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal.clear();
		this.numeroNotaFiscal.sendKeys(numeroNotaFiscal);
		return this;
	}

	public String getDataEmissao() {
		return dataEmissao.getAttribute("value");
	}

	public PopupParametros setDataEmissao(String dataEmissao) {
		this.dataEmissao.clear();
		this.dataEmissao.sendKeys(dataEmissao);
		return this;
	}

	public String getValorTotalNF() {
		return valorTotalNF.getAttribute("value");
	}

	public PopupParametros setValorTotalNF(String valorTotalNF) {
		this.valorTotalNF.clear();
		this.valorTotalNF.sendKeys(valorTotalNF);
		return this;
	}

	public WebElement getBtnConcluir() {
		return btnConcluir;
	}

	public PopupParametros clickBtnConcluir() {
		this.btnConcluir.click();
		return this;
	}
	
	public PopupParametros selecionarNFCF(int nroPedido) throws InterruptedException {
		AguardaCarregamento agCarregamento = new AguardaCarregamento(this.driver);
		driver.findElement(By.xpath("//table[@id='tableNFCF:tContent']/tbody/tr/td[text()='" + nroPedido + "']/parent::tr/td[2]")).click();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		return this;
	}

	public PopupParametros setEmpresa(String empresa) {
		new Select(this.cbxEmpresa).selectByVisibleText(empresa);
		return this;
	}
	
	public PopupParametros setLojaDestino(String lojaDestino) {
		new Select(this.cbxLojaDestino).selectByVisibleText(lojaDestino);
		return this;
	}
	
	public PopupParametros setGrupoEstoqueDestino(String grupoEstoque) {
		new Select(this.cbxGrupoEstoqueDestino).selectByVisibleText(grupoEstoque);
		return this;
	}
}