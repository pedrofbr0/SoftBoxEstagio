package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.PopupVeiculoPage;
import br.com.mv.pages.TransportePage;
import br.com.mv.utils.ControleJanela;
import net.thucydides.core.annotations.Steps;

public class TransporteSteps {

	@Steps
	TransportePage transporte;
	PopupVeiculoPage veiculo;
	ControleJanela controleJanela;
	
	@When("No faturamento transporte eu informo o motorista $nomeMotorista")
	public void euInformoMotorista(String nomeMotorista) throws InterruptedException {
		transporte.setNomeMotorista(nomeMotorista);
	}
	
	@When("No faturamento transporte eu informo o telefone motorista $telefoneMotorista")
	public void euInformoTelefoneMotorista(String telefoneMotorista) throws InterruptedException {
		transporte.setTelefoneMotorista(telefoneMotorista);
	}
	
	@When("No faturamento transporte eu informo um veiculo ativo")
	public void euInformoVeiculoAtivo() throws InterruptedException {
		controleJanela.abrirPopupClick(transporte.getBtnPesquisarVeiculo());
		veiculo.preecherVeiculo();
	}
	
	@When("No faturamento transporte eu clico no botao Ok")
	public void euClicoBotaoOk() throws InterruptedException {
		controleJanela.voltarJanelaPrincipal();
		transporte.clickOk();
	}
	
	
}