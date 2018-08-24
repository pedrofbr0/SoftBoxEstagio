package br.com.mv.PageFactory.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.utils.Utils;

public class PercentualPlus {

	WebDriver driver;
	
	@FindBy(css = "#qtd_prod")
	private WebElement qtdProd;
	
	@FindBy(css = "#qtd_serv")
	private WebElement qtdServ;
	
	@FindBy(css = "#data-inicio")
	private WebElement dataInicio;
	
	@FindBy(css = "#data-fim")
	private WebElement dataFim;
	
	@FindBy(css = "input[ng-model='percentualplus.lojaSelecionada']")
	private WebElement loja;
	
	@FindBy(css = "input[ng-model='percentualplus.lojaSelecionada'] ~ ul li a")
	private WebElement opcaoLoja;
	
	@FindBy(css = "input[ng-model='pesquisaPercentualPlusService.filtros.loja']")
	private WebElement pesquisaLoja;
	
	@FindBy(css = "input[ng-model='pesquisaPercentualPlusService.filtros.loja'] ~ ul li a")
	private WebElement pesquisaOpcaoLoja;
	
	@FindBy(xpath = ".//button[contains(text(), 'Gerar Tabela')]")
	private WebElement btnGerarTabela;
	
	@FindBy(xpath = ".//button[contains(text(), 'Salvar')]")
	private WebElement btnSalvar;
	
	@FindBy(css = "#btn-criar")
	private WebElement btnCriar;
	
	@FindBy(css = "a[title='Excluir']")
	private WebElement btnExcluirTabela;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisar;
	
	private Utils utils;
	
	public PercentualPlus(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.utils = new Utils();
	}
	
	public PercentualPlus setDataInicio(String dataInicio) {
		this.dataInicio.clear();
		this.dataInicio.sendKeys(dataInicio);
		return this;
	}
	
	public PercentualPlus setDataFim(String dataFim) {
		this.dataFim.clear();
		this.dataFim.sendKeys(dataFim);
		return this;
	}
	
	public PercentualPlus setQtdProd(String qtdProd) {
		this.qtdProd.clear();
		this.qtdProd.sendKeys(qtdProd);
		return this;
	}
	
	public PercentualPlus setQtdServ(String qtdServ) {
		this.qtdServ.clear();
		this.qtdServ.sendKeys(qtdServ);
		return this;
	}
	
	public PercentualPlus setLoja(List<String> lojas) {
		this.utils.preencherCampoTypeAHead(lojas, this.loja, this.opcaoLoja);
		return this;
	}
	
	public PercentualPlus setPesquisaLoja(List<String> lojas) {
		this.utils.preencherCampoTypeAHead(lojas, this.pesquisaLoja, this.pesquisaOpcaoLoja);
		return this;
	}
	
	public PercentualPlus clickPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public PercentualPlus clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public PercentualPlus clickExcluirTabela() {
		this.btnExcluirTabela.click();
		return this;
	}
	
	public PercentualPlus clickGerarTabela() {
		this.btnGerarTabela.click();
		return this;
	}
	
	public PercentualPlus clickCriarMatrizPlus() {
		this.btnCriar.click();
		return this;
	}
	
	public PercentualPlus setTabelaPercentualPlus(double[][] tabelaPercentualProdutos, double[][] tabelaPercentualServicos, double[] tabelaPercentualPlus, int qtdProdutos, int qtdServicos) {
		
		// Percentual produtos
		int x = 3;
		for (int i = 0; i < tabelaPercentualProdutos.length; i++) {
			for (int j = 0; j < qtdProdutos; j++) {
				String val = Double.toString(tabelaPercentualProdutos[i][j]);
				
				WebElement campo = driver.findElement(By.xpath("//*[@id='form-percentualplus']/div/div[6]/table/tbody/tr[" + x + "]/td[1]/input["+ (j + 1) +"]"));
				if (campo.isEnabled()) {
					campo.sendKeys(val);
				}
			}
			
			x++;
		}
		
		// Percentual Servicos
		for (int i = 0; i < tabelaPercentualServicos.length; i++) {
			for (int j = 0; j < qtdServicos; j++) {
				String val = Double.toString(tabelaPercentualServicos[i][j]);
				
				WebElement campo = driver.findElement(By.xpath(".//*[@id='form-percentualplus']/div/div[6]/table/tbody/tr[2]/td["+ (i+1) +"]/input[" + (j+1) + "]"));
				if (campo.isEnabled()) {
					campo.sendKeys(val);
				}
			}
		}
		
		// Percentual plus
		x = 0;
		int qtdPercentuais = qtdProdutos * qtdServicos;
		for (int i = 3; i <= qtdPercentuais; i++) {
			for (int j = 2; j < qtdPercentuais; j++) {
				String val = Double.toString(tabelaPercentualPlus[x]);
				driver.findElement(By.xpath("//*[@id='form-percentualplus']/div/div[6]/table/tbody/tr[" + i + "]/td[" + j + "]/input")).sendKeys(val);
				x++;
			}
		}
		
		return this;
	}
}