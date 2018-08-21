package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.PesquisaAtendimentoPage;
import net.thucydides.core.annotations.Steps;

public class PesquisaAtendimentoSteps {

	@Steps
	PesquisaAtendimentoPage pesquisaAtendimento;
	
	@When("Eu informo o filtro protoco atendimento $protocolo consulta atendimento")
	public void euInformoProtocolo(String protocolo) throws InterruptedException {
		pesquisaAtendimento.informarProtocoloAtendimento(protocolo);
	}
	
	@When("Eu informo o filtro data inicio $dtaInicio consulta atendimento")
	public void euInformoDataInicio(String dataInicio) throws InterruptedException {
		pesquisaAtendimento.informarDataInicio(dataInicio);
	}
	
	@When("Eu informo o filtro data fim $dtaFim consulta atendimento")
	public void euInformoDataFim(String dataFim) throws InterruptedException {
		pesquisaAtendimento.informarDataFim(dataFim);
	}
	
	@When("Eu informo o filtro cpf/cnpj $cpfCnpj consulta atendimento")
	public void euInformoCpfCnpj(String cpfCnpj) throws InterruptedException {
		pesquisaAtendimento.informarCpfCnpj(cpfCnpj);
	}
	
	@When("Eu informo o filtro loja $loja consulta atendimento")
	public void euInformoLoja(String loja) throws InterruptedException {
		pesquisaAtendimento.selecionarLoja(loja);
	}
	
	@When("Eu informo o filtro pedido $pedido consulta atendimento")
	public void euInformoPedido(String pedido) throws InterruptedException {
		pesquisaAtendimento.informarPedido(pedido);
	}
	
	@When("Eu informo o filtro status $status consulta atendimento")
	public void euInformoStatus(String status) throws InterruptedException {
		pesquisaAtendimento.informarStatus(status);
	}
	
	@When("Eu informo o filtro tratativa $tratativa consulta atendimento")
	public void euInformoTratativa(String tratativa) throws InterruptedException {
		pesquisaAtendimento.selecionarTratativa(tratativa);
	}
	
	@When("Eu informo o filtro acao $acao consulta atendimento")
	public void euInformoAcao(String acao) throws InterruptedException {
		pesquisaAtendimento.selecionarAcao(acao);
	}
	
	@When("Eu informo o filtro uf $uf consulta atendimento")
	public void euInformoUf(String uf) throws InterruptedException {
		pesquisaAtendimento.selecionarUf(uf);
	}
	
	@When("Eu clico no botao Pesquisar atendimento")
	public void euClicoBotaoPesquisarAtendimento() throws InterruptedException {
		pesquisaAtendimento.clicarBtnPesquisar();
	}
	
	@When("Eu clico no icone Editar atendimento")
	public void euClicoIconeEditarAtendimento() throws InterruptedException {
		pesquisaAtendimento.clicarBtnEditar();
	}
	
	@Then("Eu valido a informacao protocolo $protocolo em detalhes do atendimento")
	public void euClicoIconeEditarAtendimento(String protocolo) throws InterruptedException {
		pesquisaAtendimento.validarDetalhesProtocoloAtendimento(protocolo);
	}
	
	@When("Na consulta atendimento eu informo o filtro pedido")
	public void naConsultaAtendimentoInformoFiltroPedido() throws InterruptedException {
		pesquisaAtendimento.informarPedidoSessao();
	}
}