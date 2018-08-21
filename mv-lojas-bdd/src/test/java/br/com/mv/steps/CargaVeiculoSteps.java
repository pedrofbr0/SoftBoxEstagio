package br.com.mv.steps;

import static br.com.mv.model.SessionVariables.NRO_CARGA_VEICULO;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.mv.pages.CargaVeiculoPage;
import br.com.mv.pages.PopupCidadeDestinoPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class CargaVeiculoSteps {

	@Steps
	CargaVeiculoPage cargaVeiculo;
	PopupCidadeDestinoPage cidadeDestino;
	
	@When("No cadastro de carga eu clico no botao Novo")
	public void euClicoNoBotaoNovo() throws InterruptedException {
		cargaVeiculo.clickBtnNovo();
	}
	
	@When("No cadastro de carga eu informo a descricao $descricao")
	public void euInformoDescricao(String descricao) throws InterruptedException {
		cargaVeiculo.setDescCarga(descricao);
	}
	
	@When("No cadastro de carga eu clico no botao Salvar")
	public void euClicoBotaoSalvar() throws InterruptedException {
		cargaVeiculo.clickSalvar();
		
		cargaVeiculo.clicarOkAlertCadastroSucesso();
		
		String nroCarga = cargaVeiculo.getNumCarga();
		System.out.println("nroCargaVeiculo: " + nroCarga);
		
		Serenity.setSessionVariable(NRO_CARGA_VEICULO).to(nroCarga);
	}
	
	@When("No cadastro de carga eu informo a cidade de destino $cidade")
	public void euInformoCidadeDestino(String cidade) throws InterruptedException {
		cidadeDestino.preencherCidade(cidade);
	}
	
	@When("Eu associo a carga do deposito $deposito ao controle de faturamento da loja $loja")
	public void euAssocioCargaControleFaturamento(String deposito, String loja) throws InterruptedException {
		cargaVeiculo.associarCargaControleFaturamento(deposito, loja);
	}
}