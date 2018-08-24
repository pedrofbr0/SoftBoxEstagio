package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.ControleJanela;

public class PopupControleComissao {

	WebDriver driver;
	
	@FindBy(css = "#anoComissaoControle")
	private WebElement anoComissao;
	
	@FindBy(css = "#dtRefInicialComissaoControle")
	private WebElement dtRefInicialComissaoControle;
	
	@FindBy(css = "#dtRefFinalComissaoControle")
	private WebElement dtRefFinalComissaoControle;
	
	private ControleJanela controleJanela;
	
	public PopupControleComissao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		this.controleJanela = new ControleJanela(driver);
	}
	
	public PopupControleComissao setAnoComissao(String anoComissao) {
		this.anoComissao.clear();
		this.anoComissao.sendKeys(anoComissao);
		return this;
	}
	
	public PopupControleComissao selecionarMes() throws InterruptedException {
		controleJanela.fecharJanela(By.xpath("//*[@id='table:tContent']/tbody/tr[1]/td[2]"));
		return this;
	}
	
	public PopupControleComissao setDtRefInicial(String dtRefInicial) {
		this.dtRefInicialComissaoControle.clear();
		this.dtRefInicialComissaoControle.sendKeys(dtRefInicial);
		
		return this;
	}
	
	public PopupControleComissao setDtRefFinal(String dtRefFinal) {
		this.dtRefFinalComissaoControle.clear();
		this.dtRefFinalComissaoControle.sendKeys(dtRefFinal);
		return this;
	}
}