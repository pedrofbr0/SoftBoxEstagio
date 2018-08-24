package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.AtendimentoClientePage;
import net.thucydides.core.annotations.Steps;

public class AtendimentoClienteSteps {

	@Steps
	AtendimentoClientePage atendimentoCliente;
	
	@When("No atendimento ao cliente eu informo o filtro pedido")
	public void noAtendimentoInformoFiltroPedido() throws InterruptedException {
		atendimentoCliente.informarPedido();
	}
	
	@When("No atendimento ao cliente eu informo o filtro loja $loja")
	public void noAtendimentoInformoFiltroLoja(String loja) throws InterruptedException {
		atendimentoCliente.selecionarLoja(loja);
	}
	
	@When("No atendimento ao cliente eu clico no botao Buscar pedidos")
	public void noAtendimentoClicoBotaoBuscarPedidos() throws InterruptedException {
		atendimentoCliente.clicarBtnPesquisar();
	}
	
	@When("No atendimento ao cliente eu seleciono o item $codigoItem")
	public void noAtendimentoClicoSelecionoItem(String codigoItem) throws InterruptedException {
		atendimentoCliente.selecionarItemAtendimento(codigoItem);
	}
	
	@When("No atendimento ao cliente eu clico no botao Proximo")
	public void noAtendimentoClicoBotaoProximo() throws InterruptedException {
		atendimentoCliente.clicarBtnProximo();
	}
	
	@When("No atendimento ao cliente eu seleciono a tratativa $tratativa")
	public void noAtendimentoSelecionoTratativa(String tratativa) throws InterruptedException {
		atendimentoCliente.selecionarTratativa(tratativa);
	}
	
	@When("No atendimento ao cliente eu informo a observacao $observacao")
	public void noAtendimentoInformoObservacao(String observacao) throws InterruptedException {
		atendimentoCliente.informarObservacaoTratativa(observacao);
	}
	
	@When("No atendimento ao cliente eu seleciono a acao Troca")
	public void noAtendimentoSelecionoAcaoTroca() throws InterruptedException {
		atendimentoCliente.selecionarAcaoTroca();
	}
	
	@When("No atendimento ao cliente eu clico no botao Concluir")
	public void noAtendimentoClicoBotaoConcluir() throws InterruptedException {
		atendimentoCliente.clicarBtnConcluir();
	}
	
	@When("No atendimento ao cliente eu seleciono o tipo de troca $tipoTroca")
	public void noAtendimentoClienteSelecionoTipoTroca(String tipoTroca) throws InterruptedException {
		atendimentoCliente.selecionarTipoTroca(tipoTroca);
	}
	
	@When("Eu clico botao Sim no modal de atendimento")
	public void euClicoBotaoSimModalAtendimento() throws InterruptedException {
		atendimentoCliente.clicarBtnSim();
	}	
}