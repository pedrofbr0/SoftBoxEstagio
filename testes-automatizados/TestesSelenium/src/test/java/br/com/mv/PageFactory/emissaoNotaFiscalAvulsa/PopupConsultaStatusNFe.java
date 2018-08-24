package br.com.mv.PageFactory.emissaoNotaFiscalAvulsa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.ControleJanela;

public class PopupConsultaStatusNFe {
	
	WebDriver driver;
	
	@FindBy(css = "input[type='radio'][value='1']")
	private WebElement radioMostrarPendenciaSim;
	
	@FindBy(css = "input[type='radio'][value='2']")
	private WebElement radioMostrarPendenciaNao;
	
	@FindBy(id = "cbxEmpresaOrigem")
	private WebElement empresaOrigem;
	
	@FindBy(css = "#searchLoja input[type='text']:eq(0)")
	private WebElement nroLojaOrigem;
	
	@FindBy(css = "#searchLoja input[type='text']:eq(1)")
	private WebElement descLojaOrigem;
	
	@FindBy(css = "#searchLoja input:eq(3)")
	private WebElement btnLimparLojaOrigiem;
	
	@FindBy(id = "comboAcao")
	private WebElement acao;
	
	@FindBy(id = "btnAcao")
	private WebElement btnAcao;
	
	@FindBy(id = "txtDataEmissaoInicio")
	private WebElement dataEmissaoInicio;
	
	@FindBy(id = "txtDataEmissaoFim")
	private WebElement dataEmissaoFim;
	
	@FindBy(id = "txtNumeroNf")
	private WebElement notaFiscal;
	
	@FindBy(id = "txtLojaDestino")
	private WebElement nroLojaDestino;
	
	@FindBy(id = "cbxEmpresaDestino")
	private WebElement empresaDestino;
	
	@FindBy(css = "#searchCrudCargaVeiculo input[type='text']")
	private WebElement carga;
	
	@FindBy(css = "#searchCrudCargaVeiculo input[type='button']:eq(0)")
	private WebElement btnSearchCarga;
	
	@FindBy(css = "#searchCrudCargaVeiculo input[type='button']:eq(1)")
	private WebElement btnLimparCarga;
	
	@FindBy(id = "btnFecharPopup")
	private WebElement btnFechar;
	
	@FindBy(id = "btnPesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(id = "btnSelecionar")
	private WebElement btnSelecionar;
	
	@FindBy(id = "btnImpDanfe")
	private WebElement btnImprimirDanfe;
	
	@FindBy(id = "btnImpLote")
	private WebElement btnImprimirLote;
	
	@FindBy(id = "btnImpCarga")
	private WebElement btnImprimirCarga;
	
	@FindBy(id = "btnCancelaNFe")
	private WebElement btnCancelarNFe;
	
	@FindBy(id = "btnImprimirEspelho")
	private WebElement btnImprimirEspelho;
	
	@FindBy(id = "btnReaplicarTributacao")
	private WebElement btnReplicarTributacao;
	
	@FindBy(id = "btnConfirmarEspelho")
	private WebElement btnConfirmarEspelho;
	
	@FindBy(id = "btnCancelarEspelho")
	private WebElement btnCancelarEspelho;
	
	ControleJanela controleJanela = new ControleJanela(driver);
	
	public PopupConsultaStatusNFe(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupConsultaStatusNFe clickRadioMostrarPendenciaSim() {
		this.radioMostrarPendenciaSim.click();
		return this;
	}

	public PopupConsultaStatusNFe clickRadioMostrarPendenciaNao() {
		this.radioMostrarPendenciaNao.click();
		return this;
	}

	public String getEmpresaOrigem() {
		return empresaOrigem.getAttribute("value");
	}

	public PopupConsultaStatusNFe setEmpresaOrigem(String empresaOrigem) {
		new Select(this.empresaOrigem).selectByVisibleText(empresaOrigem);
		return this;
	}

	public String getNroLojaOrigem() {
		return nroLojaOrigem.getAttribute("value");
	}

	public PopupConsultaStatusNFe setNroLojaOrigem(String nroLojaOrigem) {
		this.nroLojaOrigem.clear();
		this.nroLojaOrigem.sendKeys(nroLojaOrigem);
		return this;
	}

	public String getDescLojaOrigem() {
		return descLojaOrigem.getAttribute("value");
	}

	public PopupConsultaStatusNFe setDescLojaOrigem(String descLojaOrigem) {
		this.descLojaOrigem.clear();
		this.descLojaOrigem.sendKeys(descLojaOrigem);
		return this;
	}

	public WebElement getBtnLimparLojaOrigiem() {
		return btnLimparLojaOrigiem;
	}

	public PopupConsultaStatusNFe clickBtnLimparLojaOrigiem() {
		this.btnLimparLojaOrigiem.click();
		return this;
	}

	public String getAcao() {
		return acao.getAttribute("value");
	}

	public PopupConsultaStatusNFe setAcao(String acao) {
		this.acao.clear();
		this.acao.sendKeys(acao);
		return this;
	}

	public WebElement getBtnAcao() {
		return btnAcao;
	}

	public PopupConsultaStatusNFe clickBtnAcao() {
		this.btnAcao.click();
		return this;
	}

	public String getDataEmissaoInicio() {
		return dataEmissaoInicio.getAttribute("value");
	}

	public PopupConsultaStatusNFe setDataEmissaoInicio(String dataEmissaoInicio) {
		this.dataEmissaoInicio.clear();
		this.dataEmissaoInicio.sendKeys(dataEmissaoInicio);
		return this;
	}

	public String getDataEmissaoFim() {
		return dataEmissaoFim.getAttribute("value");
	}

	public PopupConsultaStatusNFe setDataEmissaoFim(String dataEmissaoFim) {
		this.dataEmissaoFim.clear();
		this.dataEmissaoFim.sendKeys(dataEmissaoFim);
		return this;
	}

	public String getNotaFiscal() {
		return notaFiscal.getAttribute("value");
	}

	public PopupConsultaStatusNFe setNotaFiscal(String notaFiscal) {
		this.notaFiscal.clear();
		this.notaFiscal.sendKeys(notaFiscal);
		return this;
	}

	public String getNroLojaDestino() {
		return nroLojaDestino.getAttribute("value");
	}

	public PopupConsultaStatusNFe setNroLojaDestino(String nroLojaDestino) {
		this.nroLojaDestino.clear();
		this.nroLojaDestino.sendKeys(nroLojaDestino);
		return this;
	}

	public String getEmpresaDestino() {
		return empresaDestino.getAttribute("value");
	}

	public PopupConsultaStatusNFe setEmpresaDestino(String empresaDestino) {
		new Select(this.empresaDestino).selectByVisibleText(empresaDestino);
		return this;
	}

	public String getCarga() {
		return carga.getAttribute("value");
	}

	public PopupConsultaStatusNFe setCarga(String carga) {
		this.carga.clear();
		this.carga.sendKeys(carga);
		return this;
	}

	public WebElement getBtnSearchCarga() {
		return btnSearchCarga;
	}

	public PopupConsultaStatusNFe clickBtnSearchCarga() {
		this.btnSearchCarga.click();
		return this;
	}

	public String getBtnLimparCarga() {
		return btnLimparCarga.getAttribute("value");
	}

	public PopupConsultaStatusNFe clickBtnLimparCarga() {
		this.btnLimparCarga.click();
		return this;
	}

	public WebElement getBtnFechar() {
		return btnFechar;
	}

	public PopupConsultaStatusNFe clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}

	public WebElement getBtnPesquisar() {
		return btnPesquisar;
	}

	public PopupConsultaStatusNFe clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}

	public WebElement getBtnSelecionar() {
		return btnSelecionar;
	}

	public PopupConsultaStatusNFe clickBtnSelecionar() {
		this.btnSelecionar.click();
		return this;
	}

	public WebElement getImprimirDanfe() {
		return this.btnImprimirDanfe;
	}

	public PopupConsultaStatusNFe clickBtnImprimirDanfe() {
		this.btnImprimirDanfe.click();
		return this;
	}

	public WebElement getBtnImprimirLote() {
		return btnImprimirLote;
	}

	public PopupConsultaStatusNFe clickBtnImprimirLote() {
		this.btnImprimirLote.click();
		return this;
	}

	public WebElement getBtnImprimirCarga() {
		return btnImprimirCarga;
	}

	public PopupConsultaStatusNFe clickBtnImprimirCarga() {
		this.btnImprimirCarga.click();
		return this;
	}

	public WebElement getBtnCancelarNFe() {
		return btnCancelarNFe;
	}

	public PopupConsultaStatusNFe clickBtnCancelarNFe() {
		this.btnCancelarNFe.click();
		return this;
	}

	public WebElement getBtnImprimirEspelho() {
		return btnImprimirEspelho;
	}

	public PopupConsultaStatusNFe clickBtnImprimirEspelho() {
		this.btnImprimirEspelho.click();
		return this;
	}

	public WebElement getBtnReplicarTributacao() {
		return btnReplicarTributacao;
	}

	public PopupConsultaStatusNFe clickBtnReplicarTributacao() {
		this.btnReplicarTributacao.click();
		return this;
	}

	public WebElement getBtnConfirmarEspelho() {
		return btnConfirmarEspelho;
	}

	public PopupConsultaStatusNFe clickBtnConfirmarEspelho() {
		this.btnConfirmarEspelho.click();
		return this;
	}

	public WebElement getBtnCancelarEspelho() {
		return btnCancelarEspelho;
	}

	public PopupConsultaStatusNFe clickBtnCancelarEspelho() {
		this.btnCancelarEspelho.click();
		return this;
	}

	public PopupConsultaStatusNFe selecionarServicos(int[] notas) {
		for (int i=0; i<notas.length; i++) {
			driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + notas[i] + "']/parent::tr/td[1]")).click();
		}
		return this;
	}
	
	public PopupConsultaStatusNFe clicarDetalhesNotaFiscal(int nroNota) {
		By linkNF = By.cssSelector("table[id='table:tContent'] > tbody > tr > td > a:contains('" + nroNota + "')");
		try {
			controleJanela.abrirPopupClick(linkNF);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	

}
