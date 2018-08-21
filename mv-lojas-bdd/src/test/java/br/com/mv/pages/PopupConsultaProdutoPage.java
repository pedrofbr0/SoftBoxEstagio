package br.com.mv.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.pages.PageObject;

public class PopupConsultaProdutoPage extends PageObject {

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
	
	ControleJanela controleJanela;
	
	public PopupConsultaProdutoPage() {
	}
	
	public PopupConsultaProdutoPage setDepartamento(String departamento) {
		new Select(this.cbxDepartamento).selectByVisibleText(departamento);
		return this;
	}
	
	public PopupConsultaProdutoPage setSituacaoProduto(String situacaoProduto) {
		new Select(this.cbxSituacaoProduto).selectByVisibleText(situacaoProduto);
		return this;
	}
	
	public PopupConsultaProdutoPage setCodNroProduto(String codNroProduto) {
		this.txtCodNroProduto.clear();
		this.txtCodNroProduto.sendKeys(codNroProduto);
		return this;
	}
	
	public PopupConsultaProdutoPage setCodProduto(String codProduto) {
		this.txtCodProduto.sendKeys(codProduto);
		return this;
	}
	
	public PopupConsultaProdutoPage clickBtnPesquisar() {
		this.btnPesquisarProduto.click();
		return this;
	}
	
	public PopupConsultaProdutoPage selecionaUmProduto() throws InterruptedException {
		//driver.findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]")).click();
		WebElement selectProd =  this.getDriver().findElement(By.xpath(".//*[@id='table:tContent']/tbody/tr[1]/td[1]"));//.//input[contains(@id, 'txtQtdSolicitada')]
		
		controleJanela.fecharJanelaVoltarUltimoModal(selectProd); //fecharJanela(By.xpath(""));
		return this;
	}
	
	public PopupConsultaProdutoPage buscaUmProduto(HashMap<String, String> pesquisaProduto) throws InterruptedException {
		
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
