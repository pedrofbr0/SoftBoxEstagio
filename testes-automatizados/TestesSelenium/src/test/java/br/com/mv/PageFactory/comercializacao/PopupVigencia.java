package br.com.mv.PageFactory.comercializacao;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.Utils;

public class PopupVigencia {
	
	WebDriver driver;
	
	@FindBy(css = "#data-inicio")
	private WebElement dataInicio;
	
	@FindBy(css = "#data-fim")
	private WebElement dataFim;
	
	@FindBy(css = ".modal-content input[ng-model='lojaSelecionada']")
	private WebElement loja;
	
	@FindBy(css = ".modal-content input[ng-model='lojaSelecionada'] ~ ul li a")
	private WebElement opcaoLoja;
	
	@FindBy(css = ".modal-content input[ng-model='regionalSelecionada']")
	private WebElement regional;
	
	@FindBy(css = ".modal-content input[ng-model='regionalSelecionada'] ~ ul li a")
	private WebElement opcaoRegional;
	
	@FindBy(css = ".modal-content input[ng-model='ufSelecionada']")
	private WebElement uf;
	
	@FindBy(css = ".modal-content input[ng-model='ufSelecionada'] ~ ul li a")
	private WebElement opcaoUf;
	
	@FindBy(css = ".modal-content input[ng-model='bandeiraSelecionada']")
	private WebElement bandeira;
	
	@FindBy(css = ".modal-content input[ng-model='bandeiraSelecionada'] ~ ul li a")
	private WebElement opcaoBandeira;
	
	@FindBy(css = ".modal-content .btn-primary")
	private WebElement btnOk;
	
	@FindBy(css = ".modal-content .btn-default")
	private WebElement btnCancelar;
	
	private Utils utils;
	
	public PopupVigencia(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public PopupVigencia setDataInicio(String dataInicio) {
		this.dataInicio.clear();
		this.dataInicio.sendKeys(dataInicio);
		return this;
	}
	
	public PopupVigencia setDataFim(String dataFim) {
		this.dataFim.clear();
		this.dataFim.sendKeys(dataFim);
		return this;
	}
	
	public PopupVigencia setLoja(List<String> lojas) {
		this.utils.preencherCampoTypeAHead(lojas, this.loja, this.opcaoLoja);
		return this;
	}

	public PopupVigencia setRegional(List<String> regionais) {
		this.utils.preencherCampoTypeAHead(regionais, this.regional, this.opcaoRegional);
		return this;
	}
	
	public PopupVigencia setUf(List<String> ufs) {
		this.utils.preencherCampoTypeAHead(ufs, this.uf, this.opcaoUf);
		return this;
	}
	
	public PopupVigencia setBandeira(List<String> bandeiras) {
		this.utils.preencherCampoTypeAHead(bandeiras, this.bandeira, this.opcaoBandeira);
		return this;
	}
	
	public PopupVigencia clickOk() {
		this.btnOk.click();
		return this;
	}
}
