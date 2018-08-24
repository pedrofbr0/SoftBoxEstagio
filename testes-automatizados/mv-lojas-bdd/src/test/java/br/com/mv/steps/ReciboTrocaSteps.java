package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.ReciboTrocaPage;
import net.thucydides.core.annotations.Steps;

public class ReciboTrocaSteps {

	@Steps
	ReciboTrocaPage reciboTroca;
	
	@When("No recibo de troca eu informo o local do produto $localProduto")
	public void noReciboTrocaInformoLocalProduto(String localProduto) throws InterruptedException {
		reciboTroca.selecionarLocalProduto(localProduto);
	}
	
	@When("No recibo de troca eu seleciono a opcao Sem Reversao")
	public void noReciboTrocaEuSelecionoSemReversao() throws InterruptedException {
		reciboTroca.selecionarOpcaoSemReversao();
	}
	
	@When("No recibo de troca eu seleciono a opcao Com Reversao")
	public void noReciboTrocaEuSelecionoComReversao() throws InterruptedException {
		reciboTroca.selecionarOpcaoComReversao();
	}
			
	@When("No recibo de troca eu informo o motivo do atendimento $motivoAtendimento")
	public void noReciboTrocaInformoMotivoAtendimento(String motivoAtendimento) throws InterruptedException {
		reciboTroca.selecionarMotivoAtendimento(motivoAtendimento);
	}
	
	@When("No recibo de troca eu clico no botao Salvar motivo")
	public void noReciboTrocaClicoBotaoSalvarMotivo() throws InterruptedException {
		reciboTroca.clicarBtnSalvarMotivo();
	}
	
	@When("No recibo de troca eu clico no botao Sim")
	public void euClicoBotaoSim() throws InterruptedException {
		reciboTroca.clicarBtnSim();
	}
	
	@When("No recibo de troca eu clico no icone Aprovar")
	public void euClicoIconeAprovar() throws InterruptedException {
		reciboTroca.clicarIconeAprovar();
	}
	
	@When("Nos dados da avaliacao eu informo a observacao $obs")
	public void euInformoObservacao(String obs) throws InterruptedException {
		reciboTroca.informarObservacaoAprovaReprova(obs);
	}
	
	@When("Nos dados da avaliacao eu clico no botao Ok")
	public void dadosAvaliacaoClicoBotaoOk() throws InterruptedException {
		reciboTroca.clicarBtnOkAvaliacao();
	}
	
	@When("No recibo de troca eu clico no botao Ok")
	public void euClicoBotaoOk() throws InterruptedException {
		reciboTroca.clicarBtnOk();
	}
	
	@When("Eu valido mensagem de sucesso recibo de troca")
	public void euValidoMensagemSucessoReciboTroca() throws InterruptedException {
		reciboTroca.mensagemSucessoReciboTroca();
	}
	
	@When("Eu valido mensagem de sucesso recibo de troca simplificada")
	public void euValidoMensagemSucessoReciboTrocaSimplificada() throws InterruptedException {
		reciboTroca.mensagemSucessoReciboTrocaSimplificada();
	}
	
	@When("No recibo de troca eu clico no botao Imprimir")
	public void euClicoBotaoImprimir() throws InterruptedException {
		reciboTroca.clicarBtnImprimir();
	}
	
	@When("No recibo de troca eu clico no icone Agendamento de Entrega")
	public void euClicoBotaoAgendamentoEntrega() throws InterruptedException {
		reciboTroca.clicarBtnAgendamentoEntrega();
	}
	
	@When("No recibo de troca eu seleciono Data de Entrega")
	public void euClicoBotaoSelecionaDataEntrega() throws InterruptedException {
		reciboTroca.selecionarOpcaoDataEntrega();
	}
	
	@When("No recibo de troca eu clico botao Ok Agendamento de Entrega")
	public void euClicoBotaoOkAgendamentoEntrega() throws InterruptedException {
		reciboTroca.clicarBtnOkDataEntrega();
	}
	
	@When("Eu clico botao Ok no modal de agendamento de entrega")
	public void euClicoBotaoSimOkAgendamentoEntrega() throws InterruptedException {
		reciboTroca.clicarBtnSimOkAgendamentoEntrega();
	}
	
	@When("No recibo de troca eu clico botao Confirmar Endereco Cliente")
	public void euClicoBotaoConfirmarEnderecoCliente() throws InterruptedException {
		reciboTroca.clicarBtnConfirmarEnderecoCliente();
	}
	
	
	
}