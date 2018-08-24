package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_CARGA_VEICULO;
import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.mv.dao.CargaDAO;
import br.com.mv.dao.NotaFiscalDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class CargaVeiculoPage extends PageObject {

	@FindBy(id = "txtCodigo")
	private WebElement numCarga;
	
	@FindBy(id = "txtDescricao")
	private WebElement descCarga;
	
	@FindBy(css = "#searchCidadeDestino input.buttonSearchField")
	private WebElement btnSearchCidadeDestino;
	
	@FindBy(id = "txtNomeMotorista")
	private WebElement nomeMotorista;
	
	@FindBy(id = "txtTelefoneMotorista")
	private WebElement telefoneMotorista;
	
	@FindBy(css = "#searchVeiculo input.buttonSearchField")
	private WebElement btnSearchVeiculo;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnNew']")
	private WebElement btnNovo;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnSave']")
	private WebElement btnSalvar;
	
	ToolbarPage toolbar;
	private AguardaCarregamento agCarregamento;
	ControleJanela controleJanela;
	
	public CargaVeiculoPage() {
		
	}
	
	public String getNumCarga() {
		return numCarga.getAttribute("value");
	}
	
	public CargaVeiculoPage setNumCarga(String numCarga) {
		this.numCarga.sendKeys(numCarga);
		return this;
	}
	
	public WebElement getBtnNovo() {
		return btnNovo;
	}

	public CargaVeiculoPage clickBtnNovo() {
		this.btnNovo.click();
		return this;
	}
	
	public CargaVeiculoPage clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public CargaVeiculoPage setDescCarga(String descCarga) {
		this.descCarga.clear();
		this.descCarga.sendKeys(descCarga);
		return this;
	}

	public String getDescCarga() {
		return descCarga.getAttribute("value");
	}
	
	public WebElement getBtnSearchCidadeDestino() {
		return btnSearchCidadeDestino;
	}

	public CargaVeiculoPage clickBtnSearchCidadeDestino() {
		this.btnSearchCidadeDestino.click();
		return this;
	}
	
	public CargaVeiculoPage setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista.clear();
		this.nomeMotorista.sendKeys(nomeMotorista);
		return this;
	}

	public String getNomeMotorista() {
		return nomeMotorista.getAttribute("value");
	}
	
	public CargaVeiculoPage setTelefoneMotorista(String telefoneMotorista) {
		this.telefoneMotorista.clear();
		this.telefoneMotorista.sendKeys(telefoneMotorista);
		return this;
	}

	public String getTelefoneMotorista() {
		return telefoneMotorista.getAttribute("value");
	}
	
	public WebElement getBtnSearchVeiculo() {
		return btnSearchVeiculo;
	}

	public CargaVeiculoPage clickBtnSearchVeiculo() {
		this.btnSearchVeiculo.click();
		return this;
	}
	
	public CargaVeiculoPage clickPesquisar() {
		toolbar.clickPesquisar();
		return this;
	}
	
	public CargaVeiculoPage selecionarCarga(String numCarga) throws InterruptedException {
		$("//table[@id='table:tContent']/tbody/tr/td[text()='" + numCarga + "']/parent::tr/td[2]").click();
		controleJanela.voltarJanelaPrincipal();
		
		return this;
	}
	
	public void associarCargaControleFaturamento(String deposito, String nroLojaPedido) {
		
		String nroPedido = Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		CargaDAO cargaDAO = new CargaDAO();
		//int nroCargaVeiculo = cargaDAO.getCarga(Integer.parseInt(deposito), Integer.parseInt(nroPedido), Integer.parseInt(nroLojaPedido)).getNroCargaVeiculo();
		
		//System.out.println("nroCargaVeiculo: " + nroCargaVeiculo);
		//Serenity.setSessionVariable(NRO_CARGA_VEICULO).to(nroCargaVeiculo);
		
		String nroCargaVeiculo = Serenity.sessionVariableCalled(NRO_CARGA_VEICULO);
		System.out.println("nroCargaVeiculo: " + nroCargaVeiculo);
		
		
		NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
		notaFiscalDAO.associarCargaControleFaturamento(Integer.parseInt(nroPedido), Integer.parseInt(nroLojaPedido), Integer.parseInt(nroCargaVeiculo), Integer.parseInt(deposito));
	}
	
	public void clicarOkAlertCadastroSucesso() throws InterruptedException {
		this.waitFor(ExpectedConditions.alertIsPresent());
		this.getAlert().accept();
		
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
	}
}
