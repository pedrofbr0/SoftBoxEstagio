package br.com.mv.PageFactory.devolucaoProdutos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupConsultaProduto {
	
	WebDriver driver;
	
	@FindBy(id = "cbxDepartamento")
	private WebElement departamento;
	
	@FindBy(id = "cbxGrupoProduto")
	private WebElement grupoProduto;
	
	@FindBy(id = "cbxSubGrupoProduto")
	private WebElement subGrupoProduto;
	
	@FindBy(id = "txtCodNroProduto")
	private WebElement codNroProduto;
	
	@FindBy(id = "txtCodCor")
	private WebElement codCor;
	
	@FindBy(id = "txtCodVoltagem")
	private WebElement codVoltagem;
	
	@FindBy(id = "txtCodProduto")
	private WebElement nroProduto;
	
	@FindBy(id = "txtDescricao")
	private WebElement descProduto;
	
	@FindBy(id = "cbxSituacaoProduto")
	private WebElement situacaoProduto;
	
	@FindBy(id = "txtCodigoFiscal")
	private WebElement codFiscal;
	
	@FindBy(id = "txtDescricaoFiscal")
	private WebElement descFiscal;
	
	@FindBy(id = "btnPesquisarProduto")
	private WebElement btnPesquisar;
	
	public PopupConsultaProduto(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getDepartamento() {
		return departamento.getAttribute("value");
	}

	public PopupConsultaProduto setDepartamento(String departamento) {
		new Select(this.departamento).selectByVisibleText(departamento);
		return this;
	}

	public String getGrupoProduto() {
		return grupoProduto.getAttribute("value");
	}

	public PopupConsultaProduto setGrupoProduto(String grupoProduto) {
		new Select(this.grupoProduto).selectByVisibleText(grupoProduto);
		return this;
	}

	public String getSubGrupoProduto() {
		return subGrupoProduto.getAttribute("value");
	}

	public PopupConsultaProduto setSubGrupoProduto(String subGrupoProduto) {
		new Select(this.subGrupoProduto).selectByVisibleText(subGrupoProduto);
		return this;
	}

	public String getCodNroProduto() {
		return codNroProduto.getAttribute("value");
	}

	public PopupConsultaProduto setCodNroProduto(String codNroProduto) {
		this.codNroProduto.clear();
		this.codNroProduto.sendKeys(codNroProduto);
		return this;
	}

	public String getCodCor() {
		return codCor.getAttribute("value");
	}

	public PopupConsultaProduto setCodCor(String codCor) {
		this.codCor.clear();
		this.codCor.sendKeys(codCor);
		return this;
	}

	public String getCodVoltagem() {
		return codVoltagem.getAttribute("value");
	}

	public PopupConsultaProduto setCodVoltagem(String codVoltagem) {
		this.codVoltagem.clear();
		this.codVoltagem.sendKeys(codVoltagem);
		return this;
	}

	public String getNroProduto() {
		return nroProduto.getAttribute("value");
	}

	public PopupConsultaProduto setNroProduto(String nroProduto) {
		this.nroProduto.clear();
		this.nroProduto.sendKeys(nroProduto);
		return this;
	}

	public String getDescProduto() {
		return descProduto.getAttribute("value");
	}

	public PopupConsultaProduto setDescProduto(String descProduto) {
		this.descProduto.clear();
		this.descProduto.sendKeys(descProduto);
		return this;
	}

	public String getSituacaoProduto() {
		return situacaoProduto.getAttribute("value");
	}

	public PopupConsultaProduto setSituacaoProduto(String situacaoProduto) {
		new Select(this.situacaoProduto).selectByVisibleText(situacaoProduto);
		return this;
	}

	public String getCodFiscal() {
		return codFiscal.getAttribute("value");
	}

	public PopupConsultaProduto setCodFiscal(String codFiscal) {
		this.codFiscal.clear();
		this.codFiscal.sendKeys(codFiscal);
		return this;
	}

	public String getDescFiscal() {
		return descFiscal.getAttribute("value");
	}

	public PopupConsultaProduto setDescFiscal(String descFiscal) {
		this.descFiscal.clear();
		this.descFiscal.sendKeys(descFiscal);
		return this;
	}

	public WebElement getBtnPesquisar() {
		return btnPesquisar;
	}

	public void clickBtnPesquisar() {
		this.btnPesquisar.click();
	}
	
}
