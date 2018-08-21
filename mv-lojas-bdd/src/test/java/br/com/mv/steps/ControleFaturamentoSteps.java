package br.com.mv.steps;

import static br.com.mv.model.SessionVariables.NRO_CARGA_VEICULO;

import java.util.Date;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.mv.pages.CargaVeiculoPage;
import br.com.mv.pages.ControleFaturamentoPage;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class ControleFaturamentoSteps {

	@Steps
	ControleFaturamentoPage controleFaturamento;
	ControleJanela controleJanela;
	CargaVeiculoPage cargaVeiculo;
	AguardaCarregamento agCarregamento;
	
	@When("No controle de faturamento eu clico no botao Controle de Devolucoes")
	public void euClicoBotaoControleDevolucoes() throws InterruptedException {
		controleJanela.abrirPopupClick(controleFaturamento.getBtnControleDevolucoes());
	}
	
	@When("No controle de devolucao eu informo o pedido")
	public void euInformoPedido() throws InterruptedException {
		controleFaturamento.setPedido();
	}
	
	@When("No controle de devolucao eu informo a opcao recolhimento $opcaoRecolhimento")
	public void euInformoOpcao(String opcaoRecolhimento) throws InterruptedException {
		controleFaturamento.setOpcaoRecolhimento(opcaoRecolhimento);
	}
	
	@When("No controle de devolucao eu clico no botao Consultar")
	public void euClicoBotaoConsultar() throws InterruptedException {
		controleFaturamento.clickConsultar();
	}
	
	@When("No controle de devolucao eu selecono o controle de faturamento")
	public void euSelecionoControleFaturamento() throws InterruptedException {
		controleFaturamento.selecionarControleFaturamento();
	}
	
	@When("No controle de devolucao eu clico no botao Faturar")
	public void euClicoBotaoFaturar() throws InterruptedException {
		controleJanela.abrirPopupClick(controleFaturamento.getBtnFaturar());
	}
	
	@When("No controle de faturamento eu pesquiso a carga")
	public void noControleFaturamentoPesquisoCarga() throws InterruptedException {
		controleJanela.abrirPopupClick(controleFaturamento.getBtnSearchCarga());
		
		String nroCargaVeiculo = (String) Serenity.sessionVariableCalled(NRO_CARGA_VEICULO);
		cargaVeiculo.setNumCarga(nroCargaVeiculo).clickPesquisar();
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
	}
	
	@When("No controle de faturamento eu seleciono a carga")
	public void noControleFaturamentoSelecionoCarga() throws InterruptedException {
		String nroCargaVeiculo = (String) Serenity.sessionVariableCalled(NRO_CARGA_VEICULO);
		cargaVeiculo.selecionarCarga(nroCargaVeiculo);
		
		/*for (int i = 0; i < 50; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
		}*/
		
		//controleJanela.voltarJanelaPrincipal();
	}
	
	@When("No controle de faturamento eu seleciono o tipo de frete $tipoFrete")
	public void noControleFaturamentoSelecionoTipoFrete(String tipoFrete) throws InterruptedException {
		controleFaturamento.setTipoFrete(tipoFrete);
	}
	
	@When("No controle de faturamento eu seleciono o controle")
	public void noControleFaturamentoSelecionoControle() throws InterruptedException {
		controleFaturamento.selecionarControleFaturamento();
	}
	
	@When("No controle de faturamento eu clico no botao Faturar")
	public void noControleFaturamentoClicoBotaoFaturar() throws InterruptedException {
		controleFaturamento.clickFaturar();
	}
	
	@When("No controle de faturamento eu clico no botao Ok para iniciar o processo de faturamento")
	public void noControleFaturamentoClicoBotaoOkIniciarProcessoFaturamento() throws InterruptedException {
		///controleFaturamento.clickFaturar();
		controleFaturamento.clicarBotaoOkIniciarProcessoFaturamento();
	}
	
	@When("No controle de faturamento eu clico no botao Cancelar para informar uma observacao para as Nota Fiscais")
	public void noControleFaturamentoClicoBotaoCancelarInformarObs() throws InterruptedException {
		controleFaturamento.clicarBotaoCancelarInformarObsNota();
	}
	
	@When("Eu aprovo a nota da loja $loja do controle de faturamento da loja $lojaPedido do tipo controle $tipoControleFaturamento")
	public void aprovoNotaPorTipoControleFaturamento(String loja, String lojaPedido, String tipoControleFaturamento) throws InterruptedException {
		controleFaturamento.aprovarNotaFiscalPorTipoControleFaturamento(loja, lojaPedido, tipoControleFaturamento);
	}
	
	
}