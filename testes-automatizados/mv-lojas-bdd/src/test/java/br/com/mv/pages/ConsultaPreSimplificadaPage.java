package br.com.mv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.Utils;
import net.thucydides.core.pages.PageObject;

public class ConsultaPreSimplificadaPage extends PageObject {

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
	
	@FindBy(css = "#btn-elaborar-cps")
	private WebElement btnElaborarPedido;
	
	private Utils utils;
	
	public ConsultaPreSimplificadaPage() {
		this.utils = new Utils();
	}
	
	public ConsultaPreSimplificadaPage setTipoVenda(String tipoVenda) {
		this.utils.preencherCampoChosen(this.tipoVenda, tipoVenda);
		return this;
	}
	
	public ConsultaPreSimplificadaPage clickEnviar() {
		this.btnEnviar.click();
		return this;
	}
	
	public ConsultaPreSimplificadaPage clickConsultar() {
		this.btnConsultar.click();
		return this;
	}
	
	public ConsultaPreSimplificadaPage clickElaborarPedido() {
		this.btnElaborarPedido.click();
		return this;
	}
	
	public ConsultaPreSimplificadaPage clickFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public String getCpf() {
		return this.txtCpf.getText();
	}
	
	public ConsultaPreSimplificadaPage setCpf(String cpf) {
		this.txtCpf.clear();
		this.txtCpf.sendKeys(cpf);
		return this;
	}
	
	public ConsultaPreSimplificadaPage setDataNascimento(String dataNascimento) {
		this.txtDataNascimento.clear();
		this.txtDataNascimento.sendKeys(dataNascimento);
		return this;
	}
	
	public ConsultaPreSimplificadaPage selecionarTipoVendaElegivel(String tipoVenda) {
		$(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '" + tipoVenda + "')]").click();
		return this;
	}
	
}
