package br.com.mv.PageFactory.preRecibo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupAgrupamentoLoja {
	
	WebDriver driver;
	
	@FindBy(id = "txtCodigo")
	private WebElement codigo;
	
	@FindBy(id = "txtDescricao")
	private WebElement descricao;
	
	@FindBy(css = "img[title='Pesquisar']")
	private WebElement btnPesquisar;
	
	@FindBy(css = "#fakeToolbar input[type='button'][value='+']")
	private WebElement btnAdicionar;
	
	@FindBy(css = "#fakeToolbar input[type='button'][value='-']")
	private WebElement btnRemover;
	
	@FindBy(css = "#fakeToolbar input[type='button'][value='Confirmar']")
	private WebElement btnConfirmar;
	
	@FindBy(id = "btnFechar")
	private WebElement btnFechar;
	
	public PopupAgrupamentoLoja(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PopupAgrupamentoLoja setCodigo(String codigo) {
		this.codigo.clear();
		this.codigo.sendKeys(codigo);
		return this;
	}
	
	public PopupAgrupamentoLoja setDescricao(String descricao) {
		this.descricao.clear();
		this.descricao.sendKeys(descricao);
		return this;
	}
	
	public PopupAgrupamentoLoja clickPesquisar() {
		btnPesquisar.click();
//		WebElement n = driver.findElement(By.id("sadfalsdfj")).click();
		return this;
	}
	
	public PopupAgrupamentoLoja clickAdicionar() {
		btnAdicionar.click();
		return this;
	}
	
	public PopupAgrupamentoLoja clickRemover() {
		btnRemover.click();
		return this;
	}
	
	public PopupAgrupamentoLoja clickConfirmar() {
		btnConfirmar.click();
		return this;
	}
	
	public PopupAgrupamentoLoja clickFechar() {
		btnFechar.click();
		return this;
	}
	
	public PopupAgrupamentoLoja selecionarLojas(int[] lojas) {
		for (int i=0; i<lojas.length; i++) {
			driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + lojas[i] + "']/parent::tr/td[1]")).click();
		}
		return this;
	}
	
	public PopupAgrupamentoLoja excluirLojas(int[] lojas) {
		for (int i=0; i<lojas.length; i++) {
			driver.findElement(By.xpath("//table[@id='tableSelected:tContent']/tbody/tr/td[text()='" + lojas[i] + "']/parent::tr/td[1]")).click();
		}
		return this;
	}
	
	public PopupAgrupamentoLoja aguardar() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	};
	

}
