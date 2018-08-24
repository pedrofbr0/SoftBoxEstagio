package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.COD_NRO_LOJA;
import static br.com.mv.model.SessionVariables.NRO_NOTA_FISCAL;
import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.dao.NotaFiscalDAO;
import br.com.mv.model.NotaFiscal;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class PopupParametrosPage extends PageObject {

	@FindBy(id = "txtNumeroCupomFiscal")
	private WebElement nroCumpomFiscal;
	
	@FindBy(id = "cmbEmpresasCupomFiscal")
	private WebElement empresaCupomFiscal;
	
	@FindBy(id = "txtCodNumeroLojaCupomFiscal")
	private WebElement codNroLojaCupomFiscal;
	
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
	private WebElement numeroCupom;
	
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
	
	@FindBy(id = "radioCliente")
	private WebElement radioCliente;
	
	@FindBy(id = "radioFornecedor")
	private WebElement radioFornecedor;
	
	@FindBy(id = "btnPesquisarCliente")
	private WebElement btnPesquisarCliente;
	
	@FindBy(css = "#scFornecedor .buttonSearchField")
	private WebElement btnPesquisarFornecedor;
	
	@FindBy(id = "txtNumeroNotaFiscalEntrada")
	private WebElement txtNumeroNotaFiscalEntrada;
	
	@FindBy(id = "cbxFornecedor")
	private WebElement cbxFornecedor;
	
	NotaFiscalDAO nfDAO;
	NotaFiscal cupomFiscal;
	ControleJanela controleJanela;
	
	public PopupParametrosPage() {
		nfDAO = new NotaFiscalDAO();
		//this.setDadosCupomFiscal();
	}

	public void clicarRadioCliente() throws InterruptedException {
		Thread.sleep(1000);
		this.radioCliente.click();
	}
	
	public void clicarRadioFornecedor() {
		this.radioFornecedor.click();
	}
	
	public void clicarBtnPesquisarCliente() throws InterruptedException {
		controleJanela.abrirPopupClick(btnPesquisarCliente);
	}
	
	public void clicarBtnPesquisarFornecedor() throws InterruptedException {
		controleJanela.abrirPopupClick(btnPesquisarFornecedor);
	}
	
	public void selecionarEnderecoCliente() {
		$(".//*[@id='tableEnderecos:tContent']/tbody/tr[1]/td[1]/input").click();
	}
	
	public String getNroCumpomFiscal() {
		return nroCumpomFiscal.getAttribute("value");
	}

	public void setDadosNotaFiscal(String tiposNF) {
		String nroPedido = Serenity.sessionVariableCalled(NRO_PEDIDO);
		String codNroLoja = Serenity.sessionVariableCalled(COD_NRO_LOJA);
		String nroNF = (String) Serenity.sessionVariableCalled(NRO_NOTA_FISCAL);
		
		//String tiposNF = "5"; // NFCE
		
		System.out.println("nroPedido: " + nroPedido + " codNroLoja: " + codNroLoja + " tiposNF: " + tiposNF);
		
		if (tiposNF.equals("51") || tiposNF.equals("301") || tiposNF.equals("211")) {
			this.cupomFiscal = nfDAO.getNotaFiscalSemPedido(codNroLoja, tiposNF, nroNF);
		} else {
			this.cupomFiscal = nfDAO.getNotaFiscal(codNroLoja, nroPedido,  tiposNF);
		}
	}
	
	public PopupParametrosPage setNroCumpomFiscal(String tipoNF) {
		
		//Serenity.setSessionVariable(NRO_PEDIDO).to("4931664");
		
		/*String nroPedido = "4634820"; //Serenity.sessionVariableCalled(NRO_PEDIDO);
		String codNroLoja = "291"; //Serenity.sessionVariableCalled(COD_NRO_LOJA);
		String tiposNF = "1";
		
		System.out.println("nroPedido: " + nroPedido + " codNroLoja: " + codNroLoja + " tiposNF: " + tiposNF);
		
		NotaFiscal cupomFiscal = nfDAO.getNotaFiscal(codNroLoja, nroPedido,  tiposNF);*/
		
		setDadosNotaFiscal(tipoNF);
		
		String nroCupomFiscal = "";
		
		if (tipoNF.equals("1") || tipoNF.equals("5")) {
			nroCupomFiscal = Integer.toString(this.cupomFiscal.getNroCupom());
			
			this.nroCumpomFiscal.clear();
			this.nroCumpomFiscal.sendKeys(nroCupomFiscal);
			
		} else {
			nroCupomFiscal = Integer.toString(this.cupomFiscal.getNroNf());
			this.setNroNotaFiscal(nroCupomFiscal);
		}
		
		System.out.println("nroCupomFiscal: " + nroCupomFiscal);
		
		
		return this;
	}
	
	public String getEmpresaCupomFiscal() {
		return new Select(empresaCupomFiscal).getFirstSelectedOption().getText();
	}

	public PopupParametrosPage setEmpresaCupomFiscal(String tipoNF) {
		
		//String empresaCupomFiscal = "";
		//new Select(this.empresaCupomFiscal).selectByVisibleText(empresaCupomFiscal);
		return this;
	}

	public String getCodNroLojaCupomFiscal() {
		return codNroLojaCupomFiscal.getAttribute("value");
	}

	public PopupParametrosPage setCodNroLojaCupomFiscal(String tipoNF) {
		
		setDadosNotaFiscal(tipoNF);
		String codNroLojaCumpomFiscal = Integer.toString(this.cupomFiscal.getCodNroLoja());
		
		if (tipoNF.equals("1")  || tipoNF.equals("5")) {
			this.codNroLojaCupomFiscal.clear();
			this.codNroLojaCupomFiscal.sendKeys(codNroLojaCumpomFiscal);
			
		} else {
			this.setCodNroLojaNotaFiscal(codNroLojaCumpomFiscal);
		}
		
		//System.out.println("codNroLojaCumpomFiscal: " + codNroLojaCumpomFiscal);
		
		
		return this;
	}

	public String getNroSerieCF() {
		return nroSerieCF.getAttribute("value");
	}

	public PopupParametrosPage setNroSerieCF(String tipoNF) {
		
		setDadosNotaFiscal(tipoNF);
		
		String nroSerieCF = this.cupomFiscal.getSerieNf();
		
		if (tipoNF.equals("1") || tipoNF.equals("5")) {
			this.nroSerieCF.clear();
			this.nroSerieCF.sendKeys(nroSerieCF);
			
		} else {
			this.setNroSerieNF(nroSerieCF);
		}
		
		//System.out.println("nroSerieCF: " + nroSerieCF);
		
		return this;
	}

	public WebElement getBtnSearchBuscarCupomFiscal() {
		return btnSearchBuscarCupomFiscal;
	}

	public PopupParametrosPage clickBtnSearchBuscarCupomFiscal(String tipoNF) {
		
		if (tipoNF.equals("1") || tipoNF.equals("5")) {
			this.btnSearchBuscarCupomFiscal.click();
		} else {
			this.clickBtnSearchBuscarNotaFiscal();
		}
		
		return this;
	}

	public String getNroNotaFiscal() {
		return nroNotaFiscal.getAttribute("value");
	}

	public PopupParametrosPage setNroNotaFiscal(String nroNotaFiscal) {
		this.nroNotaFiscal.clear();
		this.nroNotaFiscal.sendKeys(nroNotaFiscal);
		return this;
	}

	public String getEmpresaNotaFiscal() {
		return new Select(empresaNotaFiscal).getFirstSelectedOption().getText();
	}

	public PopupParametrosPage setEmpresaNotaFiscal(String empresaNotaFiscal) {
		new Select(this.empresaNotaFiscal).selectByVisibleText(empresaNotaFiscal);
		return this;
	}

	public String getCodNroLojaNotaFiscal() {
		return codNroLojaNotaFiscal.getAttribute("value");
	}

	public PopupParametrosPage setCodNroLojaNotaFiscal(String codNroLojaNotaFiscal) {
		this.codNroLojaNotaFiscal.clear();
		this.codNroLojaNotaFiscal.sendKeys(codNroLojaNotaFiscal);
		return this;
	}

	public String getNroSerieNF() {
		return nroSerieNF.getAttribute("value");
	}

	public PopupParametrosPage setNroSerieNF(String nroSerieNF) {
		this.nroSerieNF.clear();
		this.nroSerieNF.sendKeys(nroSerieNF);
		return this;
	}

	public WebElement getBtnSearchBuscarNotaFiscal() {
		return btnSearchBuscarNotaFiscal;
	}

	public PopupParametrosPage clickBtnSearchBuscarNotaFiscal() {
		this.btnSearchBuscarNotaFiscal.click();
		return this;
	}

	public String getNumeroCumpom() {
		return numeroCupom.getAttribute("value");
	}

	public PopupParametrosPage setNumeroCumpom(String numeroCumpom) {
		this.numeroCupom.clear();
		this.numeroCupom.sendKeys(numeroCumpom);
		return this;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal.getAttribute("value");
	}

	public PopupParametrosPage setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal.clear();
		this.numeroNotaFiscal.sendKeys(numeroNotaFiscal);
		return this;
	}

	public String getDataEmissao() {
		return dataEmissao.getAttribute("value");
	}

	public PopupParametrosPage setDataEmissao(String dataEmissao) {
		this.dataEmissao.clear();
		this.dataEmissao.sendKeys(dataEmissao);
		return this;
	}

	public String getValorTotalNF() {
		return valorTotalNF.getAttribute("value");
	}

	public PopupParametrosPage setValorTotalNF(String valorTotalNF) {
		this.valorTotalNF.clear();
		this.valorTotalNF.sendKeys(valorTotalNF);
		return this;
	}

	public WebElement getBtnConcluir() {
		return btnConcluir;
	}

	public PopupParametrosPage clickBtnConcluir() {
		this.btnConcluir.click();
		return this;
	}
	
	public PopupParametrosPage selecionarNFCF() throws InterruptedException {
		
		String nroPedido = Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		//AguardaCarregamento agCarregamento = new AguardaCarregamento(this.driver);
		$("//table[@id='tableNFCF:tContent']/tbody/tr/td[text()='" + nroPedido + "']/parent::tr/td[2]").click();
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		return this;
	}

	public PopupParametrosPage selecionarNFPorTipo(String tipoNF) throws InterruptedException {
		
		//AguardaCarregamento agCarregamento = new AguardaCarregamento(this.driver);
		$("//table[@id='tableNFCF:tContent']/tbody/tr/td[text()='" + tipoNF + "']/parent::tr/td[2]").click();
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		return this;
	}
	
	
	public PopupParametrosPage setEmpresa(String empresa) {
		new Select(this.cbxEmpresa).selectByVisibleText(empresa);
		return this;
	}
	
	public PopupParametrosPage setLojaDestino(String lojaDestino) {
		new Select(this.cbxLojaDestino).selectByVisibleText(lojaDestino);
		return this;
	}
	
	public PopupParametrosPage setGrupoEstoqueDestino(String grupoEstoque) {
		new Select(this.cbxGrupoEstoqueDestino).selectByVisibleText(grupoEstoque);
		return this;
	}
	
	public PopupParametrosPage clicarBotaConcluirEFecharJanela() throws InterruptedException {
		controleJanela.fecharJanelaVoltarUltimoModal(this.getBtnConcluir());
		//controleJanela.fecharJanela(this.getBtnConcluir());
		return this;
	}
	
	public void informarNumeroNotaMais90Dias() {
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoMais90Dias();
		
		nroCumpomFiscal.clear();
		nroCumpomFiscal.sendKeys(Integer.toString(nf.getNroCupom()));
	}
	
	public void informarEmpresaNotaMais90Dias() {
		new Select(empresaCupomFiscal).selectByVisibleText("5 - RN COMERCIO VAREJISTA S.A RELOH");
	}
	
	public void informarCodigoLojaNotaMais90Dias() {
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoMais90Dias();
		
		codNroLojaCupomFiscal.clear();
		codNroLojaCupomFiscal.sendKeys(Integer.toString(nf.getCodNroLoja()));
	}
	
	public void informarNumeroSerie90Dias() {
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoMais90Dias();
		
		nroSerieCF.clear();
		nroSerieCF.sendKeys(nf.getSerieNf());
	}
	
	public PopupParametrosPage selecionarCF() throws InterruptedException {
		
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoMais90Dias();
		
		//AguardaCarregamento agCarregamento = new AguardaCarregamento(this.driver);
		$("//table[@id='tableNFCF:tContent']/tbody/tr/td[text()='" + nf.getNroCupom() + "']/parent::tr/td[2]").click();
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		return this;
	}
	
	public void informarNumeroCupomPedidoFaturado() {
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoFaturado();
		
		nroCumpomFiscal.clear();
		nroCumpomFiscal.sendKeys(Integer.toString(nf.getNroCupom()));
	}
	
	public void informarEmpresaCupomPedidoFaturado() {
		new Select(empresaCupomFiscal).selectByVisibleText("5 - RN COMERCIO VAREJISTA S.A RELOH");
	}
	
	public void informarLojaCupomPedidoFaturado() {
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoFaturado();
		
		codNroLojaCupomFiscal.clear();
		codNroLojaCupomFiscal.sendKeys(Integer.toString(nf.getCodNroLoja()));
	}
	
	public void informarSerieCupomPedidoFaturado() {
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoFaturado();
		
		nroSerieCF.clear();
		nroSerieCF.sendKeys(nf.getSerieNf());
	}
	
	public PopupParametrosPage selecionarCupomPedidoFaturado() throws InterruptedException {
		
		//NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteConsumidor();
		NotaFiscal nf = nfDAO.getNotaFiscalPedidoFaturado();
		
		//AguardaCarregamento agCarregamento = new AguardaCarregamento(this.driver);
		$("//table[@id='tableNFCF:tContent']/tbody/tr/td[text()='" + nf.getNroCupom() + "']/parent::tr/td[2]").click();
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		return this;
	}
	
	public void informarNumeroCupomPedidoFaturadoConsumidorNfce() {
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteConsumidor();
		
		nroCumpomFiscal.clear();
		nroCumpomFiscal.sendKeys(Integer.toString(nf.getNroCupom()));
	}
	
	public void informarEmpresaCupomPedidoFaturadoConsumidorNfce() {
		
		Map<String, String> metadata = HomePage.getTags();
		
		String base = "RELOH";
		
		if (!metadata.get("base").isEmpty()) {
			base = metadata.get("base");
		}
		
		String empresa = "5 - RN COMERCIO VAREJISTA S.A RELOH";
		
		if (base.equals("MVSH")) {
			empresa = "18 - LOJAS SALFER S/A.";
		}
		
		new Select(empresaCupomFiscal).selectByVisibleText(empresa);
	}
	
	public void informarLojaCupomPedidoFaturadoConsumidorNfce() {
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteConsumidor();
		
		codNroLojaCupomFiscal.clear();
		codNroLojaCupomFiscal.sendKeys(Integer.toString(nf.getCodNroLoja()));
	}
	
	public void informarSerieCupomPedidoFaturadoConsumidorNfce() {
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteConsumidor();
		
		nroSerieCF.clear();
		nroSerieCF.sendKeys(nf.getSerieNf());
	}
	
	public PopupParametrosPage selecionarCupomPedidoFaturadoConsumidorNfce() throws InterruptedException {
		
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteConsumidor();
		
		//AguardaCarregamento agCarregamento = new AguardaCarregamento(this.driver);
		$("//table[@id='tableNFCF:tContent']/tbody/tr/td[text()='" + nf.getNroCupom() + "']/parent::tr/td[2]").click();
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		return this;
	}
	
	public void clicarOkparaInformarCpf() throws InterruptedException {
		clickBtnConcluir();
		
		new WebDriverWait(this.getDriver(), 40).until(ExpectedConditions.alertIsPresent());
		controleJanela.abrirPopupAlert(true);
	}
	
	public void informarNumeroCupomPedidoFaturadoCpfNfce() {
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteComCPF();
		
		nroCumpomFiscal.clear();
		nroCumpomFiscal.sendKeys(Integer.toString(nf.getNroCupom()));
	}
	
	public void informarEmpresaCupomPedidoFaturadoCpfNfce() {
		
		Map<String, String> metadata = HomePage.getTags();
		
		String base = "RELOH";
		
		if (!metadata.get("base").isEmpty()) {
			base = metadata.get("base");
		}
		
		String empresa = "5 - RN COMERCIO VAREJISTA S.A RELOH";
		
		if (base.equals("MVSH")) {
			empresa = "18 - LOJAS SALFER S/A.";
		}
		
		new Select(empresaCupomFiscal).selectByVisibleText(empresa);
	}
	
	public void informarLojaCupomPedidoFaturadoCpfNfce() {
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteComCPF();
		
		codNroLojaCupomFiscal.clear();
		codNroLojaCupomFiscal.sendKeys(Integer.toString(nf.getCodNroLoja()));
	}
	
	public void informarSerieCupomPedidoFaturadoCpfNfce() {
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteComCPF();
		
		nroSerieCF.clear();
		nroSerieCF.sendKeys(nf.getSerieNf());
	}
	
	public PopupParametrosPage selecionarCupomPedidoFaturadoCpfNfce() throws InterruptedException {
		
		NotaFiscal nf = nfDAO.getNotaFiscalNFCEClinteComCPF();
		
		//AguardaCarregamento agCarregamento = new AguardaCarregamento(this.driver);
		$("//table[@id='tableNFCF:tContent']/tbody/tr/td[text()='" + nf.getNroCupom() + "']/parent::tr/td[2]").click();
		//agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		return this;
	}
	
	public void informarNumeroNotaViaIntegracaoTipo(String tipoNF) {
		NotaFiscal nf = nfDAO.getNotaFiscalViaIntegracao(tipoNF);
		
		nroNotaFiscal.clear();
		nroNotaFiscal.sendKeys(Integer.toString(nf.getNroNf()));
	}
	
	public void informarEmpresaNotaViaIntegracaoTipo(String tipoNF) {
		new Select(empresaNotaFiscal).selectByVisibleText("5 - RN COMERCIO VAREJISTA S.A RELOH");
	}
	
	public void informarCodigoLojaNotaViaIntegracaoTipo(String tipoNF) {
		NotaFiscal nf = nfDAO.getNotaFiscalViaIntegracao(tipoNF);
		
		codNroLojaNotaFiscal.clear();
		codNroLojaNotaFiscal.sendKeys(Integer.toString(nf.getCodNroLoja()));
	}
	
	public void informarNumeroSerieViaIntegracaoTipo(String tipoNF) {
		NotaFiscal nf = nfDAO.getNotaFiscalViaIntegracao(tipoNF);
		
		nroSerieNF.clear();
		nroSerieNF.sendKeys(nf.getSerieNf());
	}
	
	public void informarNumeroNotaPorTipoELoja(String tipoNF, String codNroLoja) {
		NotaFiscal nf = nfDAO.getNotaFiscalPorNumeroELoja(tipoNF, codNroLoja);
		
		nroNotaFiscal.clear();
		nroNotaFiscal.sendKeys(Integer.toString(nf.getNroNf()));
	}
	
	public void informarEmpresaNotaPorTipoELoja(String tipoNF, String codNroLoja) {
		new Select(empresaNotaFiscal).selectByVisibleText("5 - RN COMERCIO VAREJISTA S.A RELOH");
	}
	
	public void informarCodigoLojaNotaPorTipoELoja(String tipoNF, String codNroLoja) {
		NotaFiscal nf = nfDAO.getNotaFiscalPorNumeroELoja(tipoNF, codNroLoja);
		
		codNroLojaNotaFiscal.clear();
		codNroLojaNotaFiscal.sendKeys(Integer.toString(nf.getCodNroLoja()));
	}
	
	public void informarNumeroSerieNotaPorTipoELoja(String tipoNF, String codNroLoja) {
		NotaFiscal nf = nfDAO.getNotaFiscalPorNumeroELoja(tipoNF, codNroLoja);
		
		nroSerieNF.clear();
		nroSerieNF.sendKeys(nf.getSerieNf());
	}
	
	public void informarNumeroNotaEntradaViaIntegracaoTipo(String tipoNF) {
		NotaFiscal nf = nfDAO.getNotaFiscalViaIntegracao(tipoNF);
		
		txtNumeroNotaFiscalEntrada.clear();
		txtNumeroNotaFiscalEntrada.sendKeys(Integer.toString(nf.getNroNf()));
	}
	
	public void selecionarFornecedorNotaViaIntegracaoTipo(String tipoNF) {
		NotaFiscal nf = nfDAO.getNotaFiscalViaIntegracao(tipoNF);
		
		new Select(cbxFornecedor).selectByVisibleText(nf.getNroFornecedor());	
	}
	
	public void selecionarEnderecoFornecedor() {
		$(".//*[@id='tableEnderecos:tContent']/tbody/tr[1]/td[1]/input").click();
	}
	
}	