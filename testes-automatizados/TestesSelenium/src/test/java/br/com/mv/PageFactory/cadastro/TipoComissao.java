package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TipoComissao {

	WebDriver driver;
	
	@FindBy(css = "#comboStatusTipoComissao")
	private WebElement comboStatusTipoComissao;
	
	@FindBy(css = "#comboFlagTipoComissao")
	private WebElement comboFlagTipoComissao;
	
	@FindBy(css = "#descTipoComissao")
	private WebElement descTipoComissao;
	
	@FindBy(css = "#descAbreviadoTipoComissao")
	private WebElement descAbreviadoTipoComissao;
	
	public TipoComissao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public TipoComissao setStatusTipoComissao(String statusTipoComissao) {
		new Select(this.comboStatusTipoComissao).selectByVisibleText(statusTipoComissao);
		return this;
	}
	
	public TipoComissao setFlagTipoComissao(String flagTipoComissao) {
		new Select(this.comboFlagTipoComissao).selectByVisibleText(flagTipoComissao);
		return this;
	}
	
	public TipoComissao setDescTipoComissao(String descTipoComissao) {
		this.descTipoComissao.clear();
		this.descTipoComissao.sendKeys(descTipoComissao);
		return this;
	}
	
	public TipoComissao setDescAbreviadoTipoComissao(String descAbreviadoTipoComissao) {
		this.descAbreviadoTipoComissao.clear();
		this.descAbreviadoTipoComissao.sendKeys(descAbreviadoTipoComissao);
		return this;
	}
}