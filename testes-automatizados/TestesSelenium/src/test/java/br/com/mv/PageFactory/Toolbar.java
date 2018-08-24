package br.com.mv.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Toolbar
 * 
 * @author antoniojunior <antoniojunior@softbox.com.br>
 *
 */
public class Toolbar {
	
	WebDriver driver;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnBack']")
	private WebElement btnVoltar;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnNew']")
	private WebElement btnNovo;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnEdit']")
	private WebElement btnEditar;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnSearch']")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnRemove']")
	private WebElement btnRemover;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnSave']")
	private WebElement btnSalvar;
	
	public Toolbar(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Toolbar clickVoltar() {
		this.btnVoltar.click();
		return this;
	}
	
	public WebElement getBtnVoltar() {
		return this.btnVoltar;
	}
	
	public Toolbar clickNovo() {
		this.btnNovo.click();
		return this;
	}
	
	public WebElement getBtnNovo() {
		return this.btnNovo;
	}
	
	public Toolbar clickEditar() {
		this.btnEditar.click();
		return this;
	}
	
	public WebElement getBtnEditar() {
		return this.btnEditar;
	}
	
	public Toolbar clickPesquisar() {
		btnPesquisar.click();
		return this;
	}
	
	public WebElement getBtnPesquisar() {
		return this.btnPesquisar;
	}
	
	public Toolbar clickRemover() {
		btnRemover.click();
		return this;
	}
	
	public WebElement getBtnRemover() {
		return this.getBtnRemover();
	}
	
	public Toolbar clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public WebElement getBtnSalvar() {
		return this.btnSalvar;
	}

}
