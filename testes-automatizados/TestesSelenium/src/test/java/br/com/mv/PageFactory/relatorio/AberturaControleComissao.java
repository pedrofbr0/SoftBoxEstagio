package br.com.mv.PageFactory.relatorio;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AberturaControleComissao {

	WebDriver driver;

	@FindBy(css = ".mv-sf-filter-search")
	private WebElement btnPesquisar;
	
	@FindBy(css = "input[data-sf-column='mes_comissao,ano_comissao']")
	private WebElement mesAnoComissao;
	
	public AberturaControleComissao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public AberturaControleComissao setMesAnoComissao(String mesAnoComissao) {
		this.mesAnoComissao.clear();
		this.mesAnoComissao.sendKeys(mesAnoComissao + Keys.TAB);
		return this;
	}
	
	public AberturaControleComissao clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public AberturaControleComissao selecionarAnoMes(String ano, String mes) {
		driver.findElement(By.xpath("//table/tbody[*]/tr/td[text()='" + ano + "']/parent::tr/td[text()='" + mes + "']/parent::tr/td[1]/input")).click();
		return this;
	}
}