package br.com.mv.PageFactory.cargaVeiculo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;

public class CargaVeiculo {

	WebDriver driver;
	
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
	
	private ControleJanela controleJanela;
	private AguardaCarregamento agCarregamento;
	private Mensagens msg;
	
	public CargaVeiculo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.controleJanela = new ControleJanela(driver);
		this.agCarregamento = new AguardaCarregamento(driver);
		this.msg = new Mensagens(this.driver);
	}
	
	public String getNumCarga() {
		return numCarga.getAttribute("value");
	}
	
	public WebElement getBtnNovo() {
		return btnNovo;
	}

	public CargaVeiculo clickBtnNovo() {
		this.btnNovo.click();
		return this;
	}
	
	public CargaVeiculo clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public CargaVeiculo setDescCarga(String descCarga) {
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

	public CargaVeiculo clickBtnSearchCidadeDestino() {
		this.btnSearchCidadeDestino.click();
		return this;
	}
	
	public CargaVeiculo setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista.clear();
		this.nomeMotorista.sendKeys(nomeMotorista);
		return this;
	}

	public String getNomeMotorista() {
		return nomeMotorista.getAttribute("value");
	}
	
	public CargaVeiculo setTelefoneMotorista(String telefoneMotorista) {
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

	public CargaVeiculo clickBtnSearchVeiculo() {
		this.btnSearchVeiculo.click();
		return this;
	}
}
