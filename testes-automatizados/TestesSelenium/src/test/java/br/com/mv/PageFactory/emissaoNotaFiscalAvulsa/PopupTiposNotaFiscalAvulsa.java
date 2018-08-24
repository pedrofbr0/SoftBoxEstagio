package br.com.mv.PageFactory.emissaoNotaFiscalAvulsa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.ControleJanela;

public class PopupTiposNotaFiscalAvulsa {
	
	WebDriver driver;
	
	@FindBy(id = "btnFechar")
	private WebElement btnFechar;
	
	public PopupTiposNotaFiscalAvulsa(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getBtnFechar() {
		return this.btnFechar;
	}
	
	public PopupTiposNotaFiscalAvulsa clickBtnFechar() {
		this.btnFechar.click();
		return this;
	}
	
	public PopupTiposNotaFiscalAvulsa selecionarTipoNotaFiscal(int nroTipoNF) throws InterruptedException {
		ControleJanela controleJanela = new ControleJanela(this.driver);
		WebElement linha = driver.findElement(By.xpath("//table[@id='tableTiposNotaFiscal:tContent']/tbody/tr/td[text()='" + nroTipoNF + "']/parent::tr/td[2]"));
		
		controleJanela.fecharJanela(linha);
		
		return this;
	}

}
