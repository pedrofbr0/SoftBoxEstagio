package br.com.mv.PageFactory.cadastro;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.ControleJanela;

public class PopupConsultaProduto {

	WebDriver driver;
	private ControleJanela controleJanela;
	
	@FindBy(css = "#cbxDepartamento")
	private WebElement cbxDepartamento;
	
	@FindBy(css = "#cbxSituacaoProduto")
	private WebElement cbxSituacaoProduto;
	
	//@FindBy(xpath = ".//*[@id='table:tContent']/tbody/tr[1]/td[1]/input")
	//private WebElement 
	
	@FindBy(css = "#txtCodNroProduto")
	private WebElement txtCodNroProduto;
	
	@FindBy(css = "#txtCodProduto")
	private WebElement txtCodProduto;
	
	@FindBy(css = "#btnPesquisarProduto")
	private WebElement btnPesquisarProduto;
	
	public PopupConsultaProduto(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.controleJanela = new ControleJanela(driver);
	}
	
	public PopupConsultaProduto setDepartamento(String departamento) {
		new Select(this.cbxDepartamento).selectByVisibleText(departamento);
		return this;
	}
	
	public PopupConsultaProduto setSituacaoProduto(String situacaoProduto) {
		new Select(this.cbxSituacaoProduto).selectByVisibleText(situacaoProduto);
		return this;
	}
	
	public PopupConsultaProduto setCodNroProduto(String codNroProduto) {
		this.txtCodNroProduto.sendKeys(codNroProduto);
		return this;
	}
	
	public PopupConsultaProduto setCodProduto(String codProduto) {
		this.txtCodProduto.sendKeys(codProduto);
		return this;
	}
	
	public PopupConsultaProduto clickBtnPesquisar() {
		this.btnPesquisarProduto.click();
		return this;
	}
	
	public PopupConsultaProduto selecionaUmProduto() throws InterruptedException {
		//driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]")).click();
		
		controleJanela.fecharJanela(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]"));
		return this;
	}
	
	public PopupConsultaProduto buscaUmProduto(HashMap<String, String> pesquisaProduto) throws InterruptedException {
		
		if (pesquisaProduto.containsKey("departamento")) {
			this.setDepartamento(pesquisaProduto.get("departamento"));
		}
		
		if (pesquisaProduto.containsKey("situacao")) {
			this.setSituacaoProduto(pesquisaProduto.get("situacao"));
		}
		
		if (pesquisaProduto.containsKey("codNroProduto")) {
			this.setCodNroProduto(pesquisaProduto.get("codNroProduto"));
		}
		
		this.clickBtnPesquisar();
		
		this.selecionaUmProduto();
		
		return this;
	}
}