package br.com.mv.pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class DepartamentoPage extends PageObject {

	@FindBy(css = "#txtCodigoDepartamento")
	private WebElement txtCodigoDepartamento;
	
	@FindBy(css = "input[namebutton='Pesquisar'")
	private WebElement btnPesquisar;
	
	@FindBy(xpath = "//*[@id='table:tContent']/tbody/tr/td[2]")
	private WebElement departamento;
	
	ControleJanela controleJanela;
	
	public DepartamentoPage() {
	}
	
	public void informarCodigo(String codigo) {
		this.txtCodigoDepartamento.sendKeys(codigo);
	}
	
	public void clicarBtnPesquisar() {
		this.btnPesquisar.click();
	}
	
	public DepartamentoPage selecionarDepartamento() throws InterruptedException {
		controleJanela.fecharJanelaVoltarUltimoModal(this.departamento);
		return this;
	}
}