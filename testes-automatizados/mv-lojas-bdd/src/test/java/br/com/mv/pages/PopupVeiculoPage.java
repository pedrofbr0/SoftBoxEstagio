package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class PopupVeiculoPage extends PageObject {

	@FindBy(css = "#toolbar input[id='toolbar:btnSearch']")
	private WebElement btnSearchVeiculo;
	
	@FindBy(id = "cbxEstados")
	private WebElement estados;
	
	@FindBy(id = "cbxAtivo")
	private WebElement ativo;
	
	private ControleJanela controleJanela;
	private AguardaCarregamento agCarregamento;
	
	public PopupVeiculoPage() {
	}
	
	public PopupVeiculoPage clickBtnSearchVeiculo() {
		this.btnSearchVeiculo.click();
		return this;
	}
	
	public String getAtivo() {
		return ativo.getAttribute("value");
	}

	public PopupVeiculoPage setAtivo(String ativo) {
		new Select(this.ativo).selectByVisibleText(ativo);
		return this;
	}

	public String getEstado() {
		return estados.getAttribute("value");
	}

	public PopupVeiculoPage setEstado(String estado) {
		new Select(this.estados).selectByVisibleText(estado);
		return this;
	}
	
	public PopupVeiculoPage selecionarVeiculo() throws InterruptedException {
		$("//table[@id='table:tContent']/tbody/tr[6]/td[1]/input").click();
		return this;
	}
	
	public void preecherVeiculo() throws InterruptedException {
		this.setEstado("MG").setAtivo("Sim").clickBtnSearchVeiculo();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		this.selecionarVeiculo();
		controleJanela.voltarJanelaPrincipal();
	}
}