package br.com.mv.PageFactory.cargaVeiculo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;

public class PopupCidadeDestino {

	private WebDriver driver;
	
	@FindBy(id = "txtNome")
	private WebElement nomeCidade;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnSearch']")
	private WebElement btnSearchCidade;
	
	private ControleJanela controleJanela;
	private AguardaCarregamento agCarregamento;
	private Mensagens msg;
	
	public PopupCidadeDestino(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.controleJanela = new ControleJanela(driver);
		this.agCarregamento = new AguardaCarregamento(driver);
		this.msg = new Mensagens(this.driver);
	}
	
	public PopupCidadeDestino clickBtnSearchCidade() {
		this.btnSearchCidade.click();
		return this;
	}
	
	public PopupCidadeDestino setNomeCidade(String nomeCidade) {
		this.nomeCidade.clear();
		this.nomeCidade.sendKeys(nomeCidade);
		
		return this;
	}
	
	public PopupCidadeDestino selecionarCidadeDestino(String nomeCidade) throws InterruptedException {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + nomeCidade + "']/parent::tr/td[2]")).click();
		return this;
	}
	
	public void preencherCidade(String nomCidade) throws InterruptedException {
		this.setNomeCidade(nomCidade).clickBtnSearchCidade();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		this.selecionarCidadeDestino(nomCidade);
		controleJanela.voltarJanelaPrincipal();
	}
	
}
