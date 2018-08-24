package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AprovacaoProduto {

	WebDriver driver;
	
	@FindBy(id = "btn-voltar")
	private WebElement btnVoltar;
	
	@FindBy(css = "textarea[ng-model='data.etapa.observacao_aprovador']")
	private WebElement observacao;
	
	@FindBy(css = ".modal-footer .btn-primary")
	private WebElement btnAprovar;
	
	public AprovacaoProduto(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AprovacaoProduto clickVoltar() {
		this.btnVoltar.click();
		return this;
	}
	
	public AprovacaoProduto setObservacao(String observacao) {
		this.observacao.clear();
		this.observacao.sendKeys(observacao);
		return this;
	}
	
	public AprovacaoProduto clickAprovar() {
		this.btnAprovar.click();
		return this;
	}
	
	public AprovacaoProduto selecionarAprovacaoPendente(String descAprovacao) throws InterruptedException {
		driver.findElement(By.xpath("//table/tbody[*]/tr/td[text()='" + descAprovacao + "']/parent::tr/td[6]/div/a[1]")).click();
		return this;
	}
}