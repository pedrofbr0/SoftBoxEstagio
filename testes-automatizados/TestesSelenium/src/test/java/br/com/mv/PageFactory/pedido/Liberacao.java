package br.com.mv.PageFactory.pedido;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Liberacao {

	WebDriver driver;
	
	@FindBy(css = "#tab-liberacoes")
	private WebElement abaLiberacoes;
	
	@FindBy(css = "#btn-modal-liberar")
	private WebElement btnLiberar;
	
	@FindBy(css = "#txt-obs-liberacao")
	private WebElement obsLiberacao;
	
	@FindBy(css = "#btn-liberar-liberacao")
	private WebElement btnLiberarLiberacao;
	
	public Liberacao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Liberacao clickAbaLiberacao() {
		this.abaLiberacoes.click();
		return this;
	}
	
	public Liberacao clickBtnLiberar() {
		this.btnLiberar.click();
		return this;
	}
	
	public List<WebElement> buscarLiberacao(int nroLinha) {
		List<WebElement> dadosLiberacao = driver.findElements(By.xpath("//*[@id='grd-liberacoes']/tbody/tr[contains(@class, 'ui-widget-content')][" + nroLinha + "]/td"));
		
		return dadosLiberacao;
	}
	
	public Liberacao selecionarLiberacao(int nroLinha) {
		List<WebElement> elementoLiberacao = buscarLiberacao(nroLinha);
		elementoLiberacao.get(0).findElement(By.cssSelector("input[class='chk-liberacao']")).click();
		
		return this;
	}
	
	public String getTipoLiberacao(int nroLinha) {
		List<WebElement> elementoLiberacao = buscarLiberacao(nroLinha);
		String tipoLiberacao = elementoLiberacao.get(2).getText();
		
		return tipoLiberacao;
	}
	
	public String getFlagLiberacao(int nroLinha) {
		List<WebElement> elementoLiberacao = buscarLiberacao(nroLinha);
		String flagLiberacao = elementoLiberacao.get(4).getText();
		
		return flagLiberacao;
	}
	
	public Liberacao setObsLiberacao(String obsLiberacao) {
		this.obsLiberacao.clear();
		this.obsLiberacao.sendKeys(obsLiberacao);
		return this;
	}
	
	public Liberacao clickBtnLiberarLiberacao() {
		this.btnLiberarLiberacao.click();
		return this;
	}
}