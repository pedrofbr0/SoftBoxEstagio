package br.com.mv.PageFactory.devolucaoProdutos;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Toolbar;
import br.com.mv.dao.notafiscal.NotaFiscal;
import br.com.mv.dao.notafiscal.NotaFiscalDAO;
import br.com.mv.dao.notafiscal.ProdutoNf;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;

public class ObservacaoDevolucaoItem {
	
	WebDriver driver;
	
	@FindBy(id = "txtNumeroNf")
	private WebElement nroNF;
	
	@FindBy(id = "txtNumeroLojaNf")
	private WebElement nroLojaNF;
	
	@FindBy(id = "btnPesquisarNotaFiscal")
	private WebElement btnPesquisarNotaFiscal;
	
	@FindBy(id = "btnLimparNotaFiscal")
	private WebElement btnLimparNotaFiscal;
	
	@FindBy(id = "cbxStatus")
	private WebElement status;
	
	@FindBy(id = "codigoNumeroProduto")
	private WebElement nroProduto;
	
	@FindBy(id = "descricaoProduto")
	private WebElement descProduto;
	
	@FindBy(id = "btnPesquisarProduto")
	private WebElement btnPesquisarProduto;
	
	@FindBy(id = "btnLimparProduto")
	private WebElement btnLimparProduto;
	
	@FindBy(css = "#scUsuario input[type='text']")
	private WebElement usuario;
	
	@FindBy(xpath = "xpath=(//input[@type='button'])[6]")
	private WebElement btnPesquisarUsuario;
	
	@FindBy(xpath = "xpath=(//input[@type='button'])[7]")
	private WebElement btnLimparUsuario;
	
	@FindBy(id = "txtObservacao")
	private WebElement observacao;
	
	@FindBy(id = "txtDataInclusao")
	private WebElement dataInclusao;
	
	@FindBy(id = "txtDataAlteracao")
	private WebElement dataAlteracao;
	
	@FindBy(id = "btnAutorizarEmissaoRecibo")
	private WebElement btnAutorizarEmissaoRecibo;
	
	private Toolbar toolbar;
	private ControleJanela controleJanela;
	private AguardaCarregamento agCarregamento;
	private WebDriverWait wait;
	
	public ObservacaoDevolucaoItem(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.toolbar = new Toolbar(driver);
		this.controleJanela = new ControleJanela(driver);
		this.agCarregamento = new AguardaCarregamento(driver);
		this.wait = new WebDriverWait(driver, 20);
	}

	public String getNroNF() {
		return nroNF.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setNroNF(String nroNF) {
		this.nroNF.clear();
		this.nroNF.sendKeys(nroNF);
		return this;
	}

	public String getNroLojaNF() {
		return nroLojaNF.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setNroLojaNF(String nroLojaNF) {
		this.nroLojaNF.clear();
		this.nroLojaNF.sendKeys(nroLojaNF);
		return this;
	}

	public WebElement getBtnPesquisarNotaFiscal() {
		return btnPesquisarNotaFiscal;
	}

	public ObservacaoDevolucaoItem clickBtnPesquisarNotaFiscal() {
		this.btnPesquisarNotaFiscal.click();
		return this;
	}

	public WebElement getBtnLimparNotaFiscal() {
		return btnLimparNotaFiscal;
	}

	public ObservacaoDevolucaoItem clickBtnLimparNotaFiscal() {
		this.btnLimparNotaFiscal.click();
		return this;
	}

	public String getStatus() {
		return status.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setStatus(String status) {
		new Select(this.status).selectByVisibleText(status);
		return this;
	}

	public String getNroProduto() {
		return nroProduto.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setNroProduto(String nroProduto) {
		this.nroProduto.clear();
		this.nroProduto.sendKeys(nroProduto);
		return this;
	}

	public String getDescProduto() {
		return descProduto.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setDescProduto(String descProduto) {
		this.descProduto.clear();
		this.descProduto.sendKeys(descProduto);
		return this;
	}

	public WebElement getBtnPesquisarProduto() {
		return btnPesquisarProduto;
	}

	public ObservacaoDevolucaoItem clickBtnPesquisarProduto() {
		this.btnPesquisarProduto.click();
		return this;
	}

	public WebElement getBtnLimparProduto() {
		return btnLimparProduto;
	}

	public ObservacaoDevolucaoItem clickBtnLimparProduto() {
		this.btnLimparProduto.click();
		return this;
	}

	public String getUsuario() {
		return usuario.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setUsuario(String usuario) {
		this.usuario.clear();
		this.usuario.sendKeys(usuario);
		return this;
	}

	public WebElement getBtnPesquisarUsuario() {
		return btnPesquisarUsuario;
	}

	public ObservacaoDevolucaoItem clickBtnPesquisarUsuario() {
		this.btnPesquisarUsuario.click();
		return this;
	}

	public WebElement getBtnLimparUsuario() {
		return btnLimparUsuario;
	}

	public ObservacaoDevolucaoItem clickBtnLimparUsuario() {
		this.btnLimparUsuario.click();
		return this;
	}

	public String getObservacao() {
		return observacao.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setObservacao(String observacao) {
		this.observacao.clear();
		this.observacao.sendKeys(observacao);
		return this;
	}

	public String getDataInclusao() {
		return dataInclusao.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setDataInclusao(String dataInclusao) {
		this.dataInclusao.clear();
		this.dataInclusao.sendKeys(dataInclusao);
		return this;
	}

	public String getDataAlteracao() {
		return dataAlteracao.getAttribute("value");
	}

	public ObservacaoDevolucaoItem setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao.clear();
		this.dataAlteracao.sendKeys(dataAlteracao);
		return this;
	}

	public WebElement getBtnAutorizarEmissaoRecibo() {
		return btnAutorizarEmissaoRecibo;
	}

	public ObservacaoDevolucaoItem clickBtnAutorizarEmissaoRecibo() {
		this.btnAutorizarEmissaoRecibo.click();
		return this;
	}
	
	public ObservacaoDevolucaoItem selecionarItem(String descItem) {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[5][text()='" + descItem + "']/parent::tr/td[1]")).click();
		return this;
	}
	
	/**
	 * Efetua a devolução do(s) produto(s)
	 * @param notasFiscais, Lista do Tipo NotaFiscal, contendo as notas fiscais geradas para troca/devolução do(s) produto(s)
	 * @throws InterruptedException
	 */
	public void efetuarDevolucaoProduto(List<NotaFiscal> notasFiscais, int nroLoja, String descTipoNF) throws InterruptedException {
		
		PopupConsultaNotaFiscal consultaNf = new PopupConsultaNotaFiscal(driver);
		NotaFiscalDAO nfDAO = new NotaFiscalDAO();
		
		for (NotaFiscal nf : notasFiscais) {
			
			toolbar.clickPesquisar();
			Thread.sleep(1000);
			controleJanela.abrirPopupClick(getBtnPesquisarNotaFiscal());
			
			consultaNf.setNroNF(Integer.toString(nf.getNroNf())).setLojaDestino("").setProcesso("Processado").clickBtnPesquisar();
			consultaNf.selecionarNf(nf.getNroNf(), descTipoNF);
			controleJanela.fecharJanela(consultaNf.getBtnSelecionar());
			
			toolbar.clickPesquisar();
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			
			List<ProdutoNf> itensNf = nfDAO.getItensNf(nf.getNroNf(), nroLoja);
			
			for (ProdutoNf prod : itensNf) {
				selecionarItem(prod.getDescProduto());
				toolbar.clickEditar();
				Thread.sleep(1000);
				setStatus("Processado").setObservacao("Teste Automatizado - " + prod.getNroProduto() + " - devolução realizada com sucesso!");
				toolbar.clickSalvar();
				wait.until(ExpectedConditions.alertIsPresent());
				System.out.println("Alert: " + driver.switchTo().alert().getText());
				driver.switchTo().alert().accept();
				
				// Autoriza Emissão do recibo
				selecionarItem(prod.getDescProduto());
				this.clickBtnAutorizarEmissaoRecibo();
				wait.until(ExpectedConditions.alertIsPresent());
				System.out.println("Alert: " + driver.switchTo().alert().getText());
				driver.switchTo().alert().accept();
			}
		}
	}
}
