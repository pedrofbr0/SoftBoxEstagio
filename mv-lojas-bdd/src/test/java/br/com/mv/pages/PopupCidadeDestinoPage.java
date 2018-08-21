package br.com.mv.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.thucydides.core.pages.PageObject;

public class PopupCidadeDestinoPage extends PageObject {

	@FindBy(id = "txtNome")
	private WebElement nomeCidade;
	
	@FindBy(css = "#toolbar input[id='toolbar:btnSearch']")
	private WebElement btnSearchCidade;
	
	private ControleJanela controleJanela;
	private AguardaCarregamento agCarregamento;
	CargaVeiculoPage cargaVeiculo;
	
	public PopupCidadeDestinoPage() {
		//this.controleJanela = new ControleJanela(this.getDriver());
		//this.agCarregamento = new AguardaCarregamento(this.getDriver());
	}
	
	public PopupCidadeDestinoPage clickBtnSearchCidade() {
		this.btnSearchCidade.click();
		return this;
	}
	
	public PopupCidadeDestinoPage setNomeCidade(String nomeCidade) {
		this.nomeCidade.clear();
		this.nomeCidade.sendKeys(nomeCidade);
		
		return this;
	}
	
	public PopupCidadeDestinoPage selecionarCidadeDestino(String nomeCidade) throws InterruptedException {
		$("//table[@id='table:tContent']/tbody/tr/td[text()='" + nomeCidade + "']/parent::tr/td[2]").click();
		return this;
	}
	
	public void preencherCidade(String nomCidade) throws InterruptedException {
		controleJanela.abrirPopupClick(cargaVeiculo.getBtnSearchCidadeDestino());
		
		this.setNomeCidade(nomCidade).clickBtnSearchCidade();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		
		this.selecionarCidadeDestino(nomCidade);
		controleJanela.voltarJanelaPrincipal();
	}
}
