package br.com.mv.PageFactory.cargaVeiculo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;

public class PopupVeiculo {

	private WebDriver driver;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnSearch']")
	private WebElement btnSearchVeiculo;
	
	@FindBy(id = "cbxEstados")
	private WebElement estados;
	
	@FindBy(id = "cbxAtivo")
	private WebElement ativo;
	
	private ControleJanela controleJanela;
	private AguardaCarregamento agCarregamento;
	
	public PopupVeiculo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.controleJanela = new ControleJanela(driver);
		this.agCarregamento = new AguardaCarregamento(driver);
	}
	
	public PopupVeiculo clickBtnSearchVeiculo() {
		this.btnSearchVeiculo.click();
		return this;
	}
	
	public String getAtivo() {
		return ativo.getAttribute("value");
	}

	public PopupVeiculo setAtivo(String ativo) {
		new Select(this.ativo).selectByVisibleText(ativo);
		return this;
	}

	public String getEstado() {
		return estados.getAttribute("value");
	}

	public PopupVeiculo setEstado(String estado) {
		new Select(this.estados).selectByVisibleText(estado);
		return this;
	}
	
	public PopupVeiculo selecionarVeiculo() throws InterruptedException {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr[6]/td[1]/input")).click();
		return this;
	}
	
	public void preecherVeiculo() throws InterruptedException {
		this.setEstado("MG").setAtivo("Sim").clickBtnSearchVeiculo();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		this.selecionarVeiculo();
		controleJanela.voltarJanelaPrincipal();
		
	}
}
