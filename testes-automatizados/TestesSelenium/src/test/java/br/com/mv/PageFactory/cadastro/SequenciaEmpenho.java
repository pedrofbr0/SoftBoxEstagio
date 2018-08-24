package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SequenciaEmpenho {

	WebDriver driver;
	
	@FindBy(css = "#comboBase")
	private WebElement comboBase;
	
	@FindBy(css = "#txtSequencia")
	private WebElement sequencia;
	
	@FindBy(xpath = ".//*[@id='searchLojaEmpenho']/tbody/tr/td[1]/input")
	private WebElement lojaEmpenho;
	
	@FindBy(xpath = ".//*[@id='sfEmpresaBase']/tbody/tr/td[3]/input")
	private WebElement searchLojaEmpenho;
	
	@FindBy(xpath = ".//*[@id='sfEmpresaBase']/tbody/tr/td[1]/input")
	private WebElement empresaBase;
	
	@FindBy(xpath = ".//*[@id='sfEmpresaBase']/tbody/tr/td[3]/input")
	private WebElement searchEmpresaBase;
	
	@FindBy(css = "#txtDtInicio")
	private WebElement dtInicio;
	
	@FindBy(css = "#txtDtFim")
	private WebElement dtFim;

	public SequenciaEmpenho(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public SequenciaEmpenho setBase(String base) {
		new Select(this.comboBase).selectByVisibleText(base);
		return this;
	}
	
	public SequenciaEmpenho setSequencia(String sequencia) {
		this.sequencia.clear();
		this.sequencia.sendKeys(sequencia);
		return this;
	}
	
	public SequenciaEmpenho setLojaEmpenho(String lojaEmpenho) {
		this.lojaEmpenho.clear();
		this.lojaEmpenho.sendKeys(lojaEmpenho);
		this.searchLojaEmpenho.click();
		return this;
	}
	
	public SequenciaEmpenho setEmpresaBase(String empresaBase) {
		this.empresaBase.clear();
		this.empresaBase.sendKeys(empresaBase);
		this.searchEmpresaBase.click();
		return this;
	}
	
	public SequenciaEmpenho setDtInicio(String dtInicio) {
		this.dtInicio.clear();
		this.dtInicio.sendKeys(dtInicio);
		return this;
	}
	
	public SequenciaEmpenho setDtFim(String dtFim) {
		this.dtFim.clear();
		this.dtFim.sendKeys(dtFim);
		return this;
	}
}