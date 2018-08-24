package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.MotivoAtendimentoPage;
import net.thucydides.core.annotations.Steps;

public class MotivoAtendimentoSteps {

	@Steps
	MotivoAtendimentoPage motivoAtendimento;
	
	@When("Eu clico no botao Cadastrar motivo atendimento")
	public void euClicoBotaoCadastrarMotivo() throws InterruptedException {
		motivoAtendimento.clicarBtnCriar();
	}
	
	@When("Eu informo a descricao $descricao motivo atendimento")
	public void euClicoBotaoCadastrarMotivo(String descricao) throws InterruptedException {
		motivoAtendimento.informarDescricao(descricao);
	}
	
	@When("Eu clico no botao Salvar motivo atendimento")
	public void euClicoBotaoSalvarMotivo() throws InterruptedException {
		motivoAtendimento.clicarBtnSalvar();
	}
	
	@Then("Eu valido a mensagem de sucesso de motivo de atendimento")
	public void euValidoMensagemSucesso() throws InterruptedException {
		motivoAtendimento.validarMensagemSucesso();
	}
	
	@Then("Eu valido a mensagem de sucesso exclusao de motivo de atendimento")
	public void euValidoMensagemExclusao() throws InterruptedException {
		motivoAtendimento.validarMensagemSucessoExclusao();
	}
	
	@Then("Eu valido a mensagem de motivo de atendimento ja existente")
	public void euValidoMensagemMotivoExistente() throws InterruptedException {
		motivoAtendimento.validarMotivoJaExistente();
	}
	
	@When("Eu clico no botao Pesquisar motivo atendimento")
	public void euClicoBotaoPesquisar() throws InterruptedException {
		motivoAtendimento.clicarBtnPesquisar();
	}
	
	@When("Eu clico no botao Excluir motivo atendimento")
	public void euClicoBotaoExcluir() throws InterruptedException {
		motivoAtendimento.clicarIconeExcluir();
	}
	
	@When("Eu clico no icone Editar motivo atendimento")
	public void euClicoIconeEditar() throws InterruptedException {
		motivoAtendimento.clicarIconeEditar();
	}
	
	@When("Eu clico no botao Sim modal motivo atendimento")
	public void euClicoBotaoSimRegraLinhaProduto() throws InterruptedException {
		motivoAtendimento.clicarBtnSim();
	}
}