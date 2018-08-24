package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PreReciboTrocaDevolucao {

	WebDriver driver;
	
	//Seleção das lojas do pré-recibo
	@FindBy(id = "radioLojaUnico")
	private WebElement radioLoja;
	
	@FindBy(id = "radioLoja")
	private WebElement radioAgrupamentoLoja;
	
	@FindBy(id = "radioRegiao")
	private WebElement radioRegiao;
	
	@FindBy(id = "radioEstado")
	private WebElement radioEstado;
	
	@FindBy(css = "#searchLoja input.inputText")
	private WebElement searchFieldLoja;
	
	@FindBy(xpath = "(//input[@type='button'])[4]")
	private WebElement searchAgrupamentoLoja;
	
	@FindBy(id = "cbxRegiao")
	private WebElement cbxRegiao;
	
	@FindBy(id = "cbxEstado")
	private WebElement cbxEstado;
	//--
	
	@FindBy(id = "nroPreRecibo")
	private WebElement preRecibo;
	
	@FindBy(id = "dtInicio")
	private WebElement dataInicio;
	
	@FindBy(id = "dtFim")
	private WebElement dataFim;
	
	@FindBy(id = "cbxStatus")
	private WebElement cbxStatus;
	
	@FindBy(id = "cmbTipo")
	private WebElement cbxTipo;
	
	@FindBy(id = "txtPedido")
	private WebElement nroPedido;
	
	@FindBy(id = "cmbEmpresas")
	private WebElement cbxEmpresa;
	
	@FindBy(id = "txtCodNumeroLoja")
	private WebElement codNroLoja;
	
	//botões toolbar
	@FindBy(css = "#toolbar input[id='toolbar:btnBack']")
	private WebElement btnVoltar;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnNew']")
	private WebElement btnNovo;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnEdit']")
	private WebElement btnEditar;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnSearch']")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnRemove']")
	private WebElement btnRemover;
	//--
	
	public PreReciboTrocaDevolucao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public PreReciboTrocaDevolucao clickRadioLoja() {
		radioLoja.click();
		return this;
	}
	
	public PreReciboTrocaDevolucao clickRadioAgrupamentoLoja() {
		radioAgrupamentoLoja.click();
		return this;
	}
	
	public WebElement getRadioAgrupamentoLoja() {
		return radioAgrupamentoLoja;
	}
	
	public PreReciboTrocaDevolucao setRadioRegiao() {
		radioRegiao.click();
		return this;
	}
	
	public PreReciboTrocaDevolucao setRadioEstado() {
		radioEstado.click();
		return this;
	}
	
	public PreReciboTrocaDevolucao setSearchFieldLoja(String loja) {
		searchFieldLoja.clear();
		searchFieldLoja.sendKeys(loja);
		return this;
	}
	
	public PreReciboTrocaDevolucao clickSearchAgrupamentoLoja() {
		searchAgrupamentoLoja.click();
		return this;
	}
	
	public WebElement getSearchAgrupamentoLoja() {
		return searchAgrupamentoLoja;
	}
	
	public PreReciboTrocaDevolucao setCbxRegiao(String txtRegiao) {
		new Select(this.cbxRegiao).selectByVisibleText(txtRegiao);
		return this;
	}
	
	public PreReciboTrocaDevolucao setCbxEstado(String txtEstado) {
		new Select(cbxEstado).selectByVisibleText(txtEstado);
		return this;
	}
	
	public PreReciboTrocaDevolucao preRecibo(String txtPreRecibo) {
		preRecibo.clear();
		preRecibo.sendKeys(txtPreRecibo);
		return this;
	}
	
	public String preRecibo() {
		return preRecibo.getAttribute("value");
	}
	
	public PreReciboTrocaDevolucao setDataInicio(String dtInicio) {
		dataInicio.clear();
		dataInicio.sendKeys(dtInicio);
		return this;
	}
	
	public PreReciboTrocaDevolucao setDataFim(String dtFim) {
		dataFim.clear();
		dataFim.sendKeys(dtFim);
		return this;
	}

	public PreReciboTrocaDevolucao setCbxEstatus(String txtStatus) {
		new Select(cbxStatus).selectByVisibleText(txtStatus);
		return this;
	}
	
	public PreReciboTrocaDevolucao setCbxTipo(String txtTipo) {
		new Select(cbxTipo).selectByVisibleText(txtTipo);
		return this;
	}
	
	public PreReciboTrocaDevolucao setNroPedido(String txtNroPedido) {
		nroPedido.clear();
		nroPedido.sendKeys(txtNroPedido);
		return this;
	}
	
	public PreReciboTrocaDevolucao setCbxEmpresa(String txtEmpresa) {
		new Select(cbxEmpresa).selectByVisibleText(txtEmpresa);
		return this;
	}
	
	public PreReciboTrocaDevolucao setCodNroLoja(String txtCodNroLoja) {
		codNroLoja.clear();
		codNroLoja.sendKeys(txtCodNroLoja);
		return this;
	}
	
	public PreReciboTrocaDevolucao aguardar() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	};
	
	public PreReciboTrocaDevolucao selecionarPreRecibo(int nroPreRecibo) {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + nroPreRecibo + "']/parent::tr/td[1]")).click();
		return this;
	}
	
	
}
