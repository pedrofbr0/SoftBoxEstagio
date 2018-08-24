package br.com.mv.PageFactory.devolucaoProdutos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupConsultaNotaFiscal {
	
	WebDriver driver;
	
	@FindBy(id = "txtDataEmissaoInicio")
	private WebElement dataEmissaoIni;
	
	@FindBy(id = "txtDataEmissaoFim")
	private WebElement dataEmissaoFim;
	
	@FindBy(id = "txtCupom")
	private WebElement cupom;
	
	@FindBy(id = "txtNumeroNf")
	private WebElement nroNF;
	
	@FindBy(id = "cbxSituacao")
	private WebElement situacaoNF;
	
	@FindBy(id = "cbxProcessado")
	private WebElement processo;
	
	@FindBy(id = "txtLojaOrigem")
	private WebElement lojaOrigem;
	
	@FindBy(id = "cbxEmpresaOrigem")
	private WebElement empresaOrigem;
	
	@FindBy(id = "txtLojaDestino")
	private WebElement lojaDestino;
	
	@FindBy(id = "cbxEmpresaDestino")
	private WebElement empresaDestino;
	
	@FindBy(id = "txtSerieNotaFiscal")
	private WebElement serieNF;
	
	@FindBy(css = "#searchCrudTipoNF input[type='text']")
	private WebElement tipoNF;
	
	@FindBy(css = "css=input.buttonSearchField")
	private WebElement btnSearchTipoNF;
	
	@FindBy(id = "txtNumeroPedido")
	private WebElement nroLoja;
	
	@FindBy(id = "txtNumeroLojaPedido")
	private WebElement nroLojaPedido;
	
	@FindBy(css = "#toolbarvazia input[id='toolbarvazia:btnSearch']")
	private WebElement btnPesquisar;
	
	@FindBy(id = "btnSelecionar")
	private WebElement btnSelecionar;
	
	public PopupConsultaNotaFiscal(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getDataEmissaoIni() {
		return dataEmissaoIni.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setDataEmissaoIni(String dataEmissaoIni) {
		this.dataEmissaoIni.clear();
		this.dataEmissaoIni.sendKeys(dataEmissaoIni);
		return this;
	}

	public String getDataEmissaoFim() {
		return dataEmissaoFim.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim.clear();
		this.dataEmissaoFim.sendKeys(dataEmissaoFim);
		return this;
	}

	public String getCupom() {
		return cupom.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setCupom(String cupom) {
		this.cupom.clear();
		this.cupom.sendKeys(cupom);
		return this;
	}

	public String getNroNF() {
		return nroNF.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setNroNF(String nroNF) {
		this.nroNF.clear();
		this.nroNF.sendKeys(nroNF);
		return this;
	}

	public String getSituacaoNF() {
		return situacaoNF.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setSituacaoNF(String situacaoNF) {
		new Select(this.situacaoNF).selectByVisibleText(situacaoNF);
		return this;
	}

	public String getProcesso() {
		return processo.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setProcesso(String processo) {
		new Select(this.processo).selectByVisibleText(processo);
		return this;
	}

	public String getLojaOrigem() {
		return lojaOrigem.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setLojaOrigem(String lojaOrigem) {
		this.lojaOrigem.clear();
		this.lojaOrigem.sendKeys(lojaOrigem);
		return this;
	}

	public String getEmpresaOrigem() {
		return empresaOrigem.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setEmpresaOrigem(String empresaOrigem) {
		new Select(this.empresaOrigem).selectByVisibleText(empresaOrigem);
		return this;
	}

	public String getLojaDestino() {
		return lojaDestino.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setLojaDestino(String lojaDestino) {
		this.lojaDestino.clear();
		this.lojaDestino.sendKeys(lojaDestino);
		return this;
	}

	public String getEmpresaDestino() {
		return empresaDestino.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setEmpresaDestino(String empresaDestino) {
		new Select(this.empresaDestino).selectByVisibleText(empresaDestino);
		return this;
	}

	public String getSerieNF() {
		return serieNF.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setSerieNF(String serieNF) {
		this.serieNF.clear();
		this.serieNF.sendKeys(serieNF);
		return this;
	}

	public String getTipoNF() {
		return tipoNF.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setTipoNF(String tipoNF) {
		this.tipoNF.clear();
		this.tipoNF.sendKeys(tipoNF);
		return this;
	}

	public WebElement getBtnSearchTipoNF() {
		return btnSearchTipoNF;
	}

	public PopupConsultaNotaFiscal clickBtnSearchTipoNF() {
		this.btnSearchTipoNF.click();
		return this;
	}

	public String getNroLoja() {
		return nroLoja.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setNroLoja(String nroLoja) {
		this.nroLoja.clear();
		this.nroLoja.sendKeys(nroLoja);
		return this;
	}

	public String getNroLojaPedido() {
		return nroLojaPedido.getAttribute("value");
	}

	public PopupConsultaNotaFiscal setNroLojaPedido(String nroLojaPedido) {
		this.nroLojaPedido.clear();
		this.nroLojaPedido.sendKeys(nroLojaPedido);
		return this;
	}

	public WebElement getBtnSelecionar() {
		return btnSelecionar;
	}

	public PopupConsultaNotaFiscal setBtnSelecionar() {
		this.btnSelecionar.click();
		return this;
	}
	
	public WebElement getBtnPesquisar() {
		return this.btnPesquisar;
	}
	
	public PopupConsultaNotaFiscal clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public PopupConsultaNotaFiscal selecionarNf(int nroNF, String descTipoNF) {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[contains(text(), '" + descTipoNF + "')]/parent::tr/td[3]/a[text()='" + nroNF + "']/parent::td/parent::tr/td[1]/input")).click();
		
		//table[@id='table:tContent']/tbody/tr//parent::td/following-sibling::td[1]
		
		return this;
	}
	
}
