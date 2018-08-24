package br.com.mv.PageFactory.faturamento;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.dao.faturamento.ControleRotativoEstoqueDAO;

public class CRE {

	WebDriver driver;
	
	@FindBy(css = "#txtNumeroCre")
	private WebElement txtNumeroCre;
	
	@FindBy(css = "#txtNumeroLoja")
	private WebElement txtNumeroLoja;
	
	@FindBy(css = "#btnBuscarCre")
	private WebElement btnBuscarCre;
	
	@FindBy(css = "#btnBuscarCrePendente")
	private WebElement btnBuscarCrePendente;
	
	@FindBy(css = "#cbxDepartamentos")
	private WebElement cbxDepartamentos;
	
	@FindBy(css = "#btnRelatorios")
	private WebElement btnRelatorios;
	
	@FindBy(css = "#btnProcessa")
	private WebElement btnProcessa;
	
	@FindBy(css = "#btnAtualizarQuantidades")
	private WebElement btnAtualizarQuantidades;
	
	@FindBy(css = "#btnGeracao")
	private WebElement btnGeracao;
	
	@FindBy(css = "#txtStatusCre")
	private WebElement txtStatusCre;
	
	@FindBy(css = "#txtUsuarioCre")
	private WebElement txtUsuarioCre;
	
	private ControleRotativoEstoqueDAO creDAO;
	
	public CRE(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.creDAO = new ControleRotativoEstoqueDAO(); 
	}
	
	public CRE setNumeroCre(String numeroCre) {
		this.txtNumeroCre.clear();
		this.txtNumeroCre.sendKeys(numeroCre);
		return this;
	}
	
	public CRE setNumeroLoja(String numeroLoja) {
		this.txtNumeroLoja.clear();
		this.txtNumeroLoja.sendKeys(numeroLoja);
		return this;
	}
	
	public String getNumeroCre() {
		return this.txtNumeroCre.getAttribute("value");
	}
	
	public CRE setDepartamento(String departamento) {
		new Select(this.cbxDepartamentos).selectByVisibleText(departamento);
		return this;
	}
	
	public CRE clickBtnBuscarCre() {
		this.btnBuscarCre.click();
		return this;
	}
	
	public WebElement getBtnBuscarCrePendente() {
		return this.btnBuscarCrePendente;
	}
	
	public WebElement getBtnRelatorios() {
		return this.btnRelatorios;
	}
	
	public WebElement getBtnProcessa() {
		return this.btnProcessa;
	}
	
	public WebElement getBtnGeracao() {
		return this.btnGeracao;
	}
	
	public WebElement getUsuarioCre() {
		return this.txtUsuarioCre;
	}
	
	public CRE clickBtnProcessa() {
		this.btnProcessa.click();
		return this;
	}
	
	public CRE clickBtnAtualizarQuantidades() {
		this.btnAtualizarQuantidades.click();
		return this;
	}
	
	public String getStatusCre() {
		return this.txtStatusCre.getText();
	}
	
	public CRE setQtdeEstoqueInformada(WebElement item, int qtdeEstoqueInformada) {
		WebElement txtQtdeEstoqueInformada = item.findElement(By.xpath("./td[8]/input[@name='quantidadeEstoqueInformada']"));
		txtQtdeEstoqueInformada.clear();
		txtQtdeEstoqueInformada.sendKeys(Integer.toString(qtdeEstoqueInformada));
		
		return this;
	}
	
	public List<WebElement> itensCre() {
		List<WebElement> itens = driver.findElements(By.xpath(".//*[@id='tabelaCamposLinha:tContent']/tbody/tr"));
		return itens;
	}
	
	public List<String> departamentosCre(int nroCre, int nroLoja) {
		return creDAO.getDeptosCre(nroCre, nroLoja);
	}
}