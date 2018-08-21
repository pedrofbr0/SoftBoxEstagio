package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ConfiguracaoPromocaoPage;
import net.thucydides.core.annotations.Steps;

public class ConfiguracaoPromocaoSteps {

	@Steps
	ConfiguracaoPromocaoPage configuracaoPromocao;
	
	@When("Eu informo o codigo da promocao em configuracao")
	public void euInformoCodigoPromocaoPesquisa() throws InterruptedException {
		
	}
	
	@When("Eu clico no botao Acoes da primeira promocao encontrada")
	public void euClicoBotaoAcoesPrimeiraPromocaoEncontrada() throws InterruptedException {
		configuracaoPromocao.clicarEmAcoesPrimeiraPromocaoEncontrada();
	}
	
	@When("Eu clico no botao Pesquisar promocao em configuracao")
	public void euClicoBotaoPesquisaPromocaoParaConfigurar() throws InterruptedException {
		configuracaoPromocao.clicarBtnPesquisarPromocao();
	}
	
	@When("Eu seleciono o status da promocao $statusPromocao em configuracao")
	public void euSelecionoStatusPromocao(String statusPromocao) throws InterruptedException {
		configuracaoPromocao.selecionarStatusPromocao(statusPromocao);
	}
	
	@When("Eu seleciono o condicional $condicional o tipo servico/servico a promocao")
	public void euSelecionoCondicional(String condicional) throws InterruptedException {
		configuracaoPromocao.selecionarCondicional(condicional);
	}
	
	@When("Eu seleciono o tipo de servico $tipoServico para a promocao")
	public void euSelecionoTipoServico(String tipoServico) throws InterruptedException {
		configuracaoPromocao.selecionarTipoServico(tipoServico);
	}
	
	@When("Eu seleciono o servico $servico para a promocao")
	public void euSelecionoServico(String servico) throws InterruptedException {
		configuracaoPromocao.selecionarServico(servico);
	}
	
	@When("Eu clico botao Salvar associacao promocao")
	public void euClicoBotaoSalvarAssociacaoPromocao() throws InterruptedException {
		configuracaoPromocao.clicarBtnSalvar();
	}
	
	@Then("Eu valido mensagem de sucesso na associacao")
	public void euValidoMensagemSucessoAssociacao() throws InterruptedException {
		configuracaoPromocao.validarMensagemSucesso();
	}
}