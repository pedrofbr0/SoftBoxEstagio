package br.com.mv.PageFactory.pedido;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.mv.utils.Utils;

public class ConsultaPreSimplificada {

	WebDriver driver;
	
	@FindBy(css = "#cbx_tipos_venda_chosen")
	private WebElement tipoVenda;
	
	@FindBy(css = "#btn-enviar-cps")
	private WebElement btnEnviar;
	
	@FindBy(css = "#btn-consultar-cps")
	private WebElement btnConsultar;
	
	@FindBy(css = "#btn-fechar-cps")
	private WebElement btnFechar;
	
	@FindBy(css = "#txt-cpf")
	private WebElement txtCpf;
	
	@FindBy(css = "#txt-data-nascimento")
	private WebElement txtDataNascimento;
	
	private Utils utils;
	
	public ConsultaPreSimplificada(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public ConsultaPreSimplificada setTipoVenda(String tipoVenda) {
		this.utils.preencherCampoChosen(this.tipoVenda, tipoVenda);
		return this;
	}
	
	public ConsultaPreSimplificada clickEnviar() {
		this.btnEnviar.click();
		return this;
	}
	
	public ConsultaPreSimplificada clickConsultar() {
		this.btnConsultar.click();
		return this;
	}
	
	public ConsultaPreSimplificada clickFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public String getCpf() {
		return this.txtCpf.getText();
	}
	
	public ConsultaPreSimplificada setCpf(String cpf) {
		this.txtCpf.clear();
		this.txtCpf.sendKeys(cpf);
		return this;
	}
	
	public ConsultaPreSimplificada setDataNascimento(String dataNascimento) {
		this.txtDataNascimento.clear();
		this.txtDataNascimento.sendKeys(dataNascimento);
		return this;
	}
}