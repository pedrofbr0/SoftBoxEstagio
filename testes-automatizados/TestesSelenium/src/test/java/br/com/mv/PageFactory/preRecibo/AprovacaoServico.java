package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AprovacaoServico {
	
	WebDriver driver;
	
	@FindBy(id = "btn-voltar")
	private WebElement btnVoltar;
	
	@FindBy(xpath = "html/body/div[2]/div/div[10]/form/div[1]/fieldset/textarea")
	private WebElement observacao;
	
	@FindBy(id = "btn-aprovar")
	private WebElement btnAprovar;
	
	public AprovacaoServico(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AprovacaoServico clickVoltar() {
		this.btnVoltar.click();
		return this;
	}
	
	public AprovacaoServico setObservacao(String observacao) {
		this.observacao.clear();
		this.observacao.sendKeys(observacao);
		return this;
	}
	
	public AprovacaoServico clickAprovar() {
		this.btnAprovar.click();
		return this;
	}
	
	public AprovacaoServico selecionarAprovacaoPendente(String descAprovacao) throws InterruptedException {
		driver.findElement(By.xpath("//table/tbody[*]/tr/td[text()='" + descAprovacao + "']/parent::tr/td[6]/div/a[1]")).click();
		return this;
	}
}
