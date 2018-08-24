package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Rota {

	WebDriver driver;
	
	@FindBy(css = "#comboBase")
	private WebElement comboBase;
	
	@FindBy(css = "#rota")
	private WebElement rota;
	
	@FindBy(xpath = ".//*[@id='searchLoja']/tbody/tr/td[1]/input")
	private WebElement lojaRefaturamento;
	
	@FindBy(xpath = ".//*[@id='searchLoja']/tbody/tr/td[3]/input")
	private WebElement searchLojaRefaturamento;
	
	@FindBy(xpath = ".//*[@id='searchLoja1']/tbody/tr/td[1]/input")
	private WebElement lojaAbastecimento;
	
	@FindBy(xpath = ".//*[@id='searchLoja1']/tbody/tr/td[3]/input")
	private WebElement searchLojaAbastecimento;
	
	@FindBy(css = "#comboEmpresaBase")
	private WebElement comboEmpresaBase;
	
	public Rota(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Rota setBase(String base) {
		new Select(this.comboBase).selectByVisibleText(base);
		return this;
	}
	
	public Rota setRota(String rota) {
		this.rota.clear();
		this.rota.sendKeys(rota);
		return this;
	}
	
	public Rota setLojaRefaturamento(String lojaRefaturamento) {
		this.lojaRefaturamento.clear();
		this.lojaRefaturamento.sendKeys(lojaRefaturamento);
		this.searchLojaRefaturamento.click();
		return this;
	}
	
	public Rota setLojaAbastecimento(String lojaAbastecimento) {
		this.lojaAbastecimento.clear();
		this.lojaAbastecimento.sendKeys(lojaAbastecimento);
		this.searchLojaAbastecimento.click();
		return this;
	}
	
	public Rota setEmpresaBase(String empresaBase) {
		new Select(this.comboEmpresaBase).selectByVisibleText(empresaBase);
		return this;
	}
}