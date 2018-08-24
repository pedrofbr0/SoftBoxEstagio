package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ControleRecolhimentoProdutoPage;
import br.com.mv.pages.RelatorioDevolucaoTrocaPage;
import net.thucydides.core.annotations.Steps;

public class RelatorioDevolucaoTrocaSteps {

	@Steps
	RelatorioDevolucaoTrocaPage relatorioDevolucaoTroca;
	
	@When("No relatorio de devolucao e troca eu pesquiso a forma de pagamento $formaPagto")
	public void noRelatorioDevTrocaPesquisoFormaPagto(String formaPagto) throws InterruptedException {
		relatorioDevolucaoTroca.pesquisarFormaPagto(formaPagto);
	}
	
	@When("No relatorio de devolucao e troca eu informo a loja $codNroLoja")
	public void noRelatorioDevTrocaInformoLoja(String codNroLoja) throws InterruptedException {
		relatorioDevolucaoTroca.informarLoja(codNroLoja);
	}
	
	@When("No relatorio de devolucao e troca eu informo a data inicio $dataInicio")
	public void noRelatorioDevTrocaInformoDataInicio(String dataInicio) throws InterruptedException {
		relatorioDevolucaoTroca.informarDataInicio(dataInicio);
	}
	
	@When("No relatorio de devolucao e troca eu informo a data fim $dataFim")
	public void noRelatorioDevTrocaInformoDataFim(String dataFim) throws InterruptedException {
		relatorioDevolucaoTroca.informarDataFim(dataFim);
	}
	
	@When("No relatorio de devolucao e troca eu informo o departamento $depto")
	public void noRelatorioDevTrocaInformoDepto(String depto) throws InterruptedException {
		relatorioDevolucaoTroca.pesquisarDepartamento(depto);
	}
	
	@When("No relatorio de devolucao e troca eu clico no botao Imprimir")
	public void noRelatorioDevTrocaClicoBotaoImprimir() throws InterruptedException {
		relatorioDevolucaoTroca.clickBtnImprimir();
	}
	
	@When("No relatorio de devolucao eu valido a forma de pagamento escolhida $textoFormaPagto")
	public void noRelatorioDevTrocaEuValidoFormaPagtoEscolhida(String textoFormaPagto) throws InterruptedException {
		relatorioDevolucaoTroca.validarTextoFormaPagamento(textoFormaPagto);
	}
	
	@When("No relatorio de devolucao eu valido a loja escolhida $textoLoja")
	public void noRelatorioDevTrocaEuValidoLojaEscolhida(String textoLoja) throws InterruptedException {
		relatorioDevolucaoTroca.validarTextoLoja(textoLoja);
	}
	
	@When("No relatorio de devolucao eu valido o departamento escolhido $textoDepto")
	public void noRelatorioDevTrocaEuValidoDeptoEscolhida(String textoDepto) throws InterruptedException {
		relatorioDevolucaoTroca.validarTextoDepartamento(textoDepto);
	}
	
	@Then("No relatorio de devolucao e troca eu valido a impressao")
	public void noRelatorioDevTrocaEuValidoImpressao() throws InterruptedException {
		relatorioDevolucaoTroca.validarRelatorio();
	}
	
	
}