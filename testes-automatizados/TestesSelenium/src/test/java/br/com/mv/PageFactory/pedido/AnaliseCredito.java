package br.com.mv.PageFactory.pedido;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AnaliseCredito {

	WebDriver driver;
	
	@FindBy(css = "#btn-comunicacao")
	private WebElement btnComunicacao;
	
	@FindBy(css = "#btn-enviar")
	private WebElement btnEnviar;
	
	@FindBy(css = "#btn-consulta-comunicacao")
	private WebElement btnConsultaComunicacao;
	
	@FindBy(css = "#btnCancelar")
	private WebElement btnCancelar;
	
	@FindBy(css = "#ckb-identidade")
	private WebElement ckbIdentidade;
	
	@FindBy(css = "#ckb-cpf")
	private WebElement ckbCpf;
	
	@FindBy(css = "#ckb-renda")
	private WebElement ckbRenda;
	
	@FindBy(css = "#txtarea-observacoes-comunicacao")
	private WebElement observacoesComunicacao;
	
	@FindBy(css = "#btn-fechar-modal-analise-credito")
	private WebElement btnFecharAnaliseCredito;
	
	public AnaliseCredito(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AnaliseCredito clickBtnComunicacao() {
		this.btnComunicacao.click();
		return this;
	}
	
	public AnaliseCredito clickBtnEnviar() {
		this.btnEnviar.click();
		return this;
	}
	
	public AnaliseCredito clickBtnFecharAnaliseCredito() {
		this.btnFecharAnaliseCredito.click();
		return this;
	}
	
	public AnaliseCredito clickBtnCancelar() {
		this.btnCancelar.click();
		return this;
	}
	
	public AnaliseCredito clickBtnConsultaComunicacao() {
		this.btnConsultaComunicacao.click();
		return this;
	}
	
	public AnaliseCredito clickCkbIdentidade() {
		this.ckbIdentidade.click();
		return this;
	}
	
	public AnaliseCredito clickCkbCpf() {
		this.ckbCpf.click();
		return this;
	}
	
	public AnaliseCredito clickCkbRenda() {
		this.ckbRenda.click();
		return this;
	}
	
	public AnaliseCredito setObservacaoComunicacao(String obsComunicacao) {
		this.observacoesComunicacao.sendKeys(obsComunicacao);
		return this;
	}
}