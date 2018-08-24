package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.GrupoAcessoTratativaPage;
import net.thucydides.core.annotations.Steps;

public class GrupoAcessoTratativaSteps {
	
	@Steps
	GrupoAcessoTratativaPage grupoAcessoTratativa;
	
	@When("Eu clico no botao Cadastrar grupo acesso tratativa")
	public void euClicoBotaoCadastrarGrupoAcessoTratativa() throws InterruptedException {
		grupoAcessoTratativa.clicarBtnCriar();
	}
	
	@When("Eu clico no botao Pesquisar grupo acesso tratativa")
	public void euClicoBotaoPesquisarGrupoAcessoTratativa() throws InterruptedException {
		grupoAcessoTratativa.clicarBtnPesquisar();
	}
	
	@When("Eu clico no icone Editar grupo acesso tratativa")
	public void euClicoIconeEditarGrupoAcessoTratativa() throws InterruptedException {
		grupoAcessoTratativa.clicarIconeEditar();
	}
	
	@When("Eu informo o grupo acesso $grupoAcesso grupo acesso tratativa")
	public void euInformoGrupoAcessoTratativa(String grupoAcesso) throws InterruptedException {
		grupoAcessoTratativa.informarGrupoAcesso(grupoAcesso);
	}
	
	@When("Eu informo filtro grupo acesso $grupoAcesso grupo acesso tratativa")
	public void euInformoFiltroGrupoAcessoTratativa(String grupoAcesso) throws InterruptedException {
		grupoAcessoTratativa.informarFiltroGrupoAcesso(grupoAcesso);
	}
	
	@When("Eu clico icone Excluir grupo acesso tratativa")
	public void euClicoIconeExcluirGrupoAcessoTratativa() throws InterruptedException {
		grupoAcessoTratativa.clicarIconeExcluirGrupoAcessoTratativa();
	}
	
	@When("Eu clico botao Sim modal grupo acesso tratativa")
	public void euClicoBotaoSimModalGrupoAcessoTratativa() throws InterruptedException {
		grupoAcessoTratativa.clicarBtnSim();
	}
	
	@Then("Eu valido mensagem sucesso exclusao grupo acesso tratativa")
	public void euValidoMensagemSucessoExclusao() throws InterruptedException {
		grupoAcessoTratativa.validarMensagemSucessoExclusao();
	}
	
	@When("Eu informo a tratativa $tratativa grupo acesso tratativa")
	public void euInformoTratativa(String tratativa) throws InterruptedException {
		grupoAcessoTratativa.informarTratativa(tratativa);
	}
	
	@When("Eu informo a data base $dataBase grupo acesso tratativa")
	public void euInformoDataBase(String dataBase) throws InterruptedException {
		grupoAcessoTratativa.informarDataBase(dataBase);
	}
	
	@When("Eu informo a linha de produto $linhaProduto grupo acesso tratativa")
	public void euInformoLinhaProduto(String linhaProduto) throws InterruptedException {
		grupoAcessoTratativa.informarLinhaProduto(linhaProduto);
	}
	
	@When("Eu informo o local de retirada $localRetirada grupo acesso tratativa")
	public void euInformoLocalRetirada(String localRetirada) throws InterruptedException {
		grupoAcessoTratativa.informarRetirada(localRetirada);
	}
	
	@When("Eu informo o ticket medio $ticketMedido grupo acesso tratativa")
	public void euInformoTicketMedio(String ticketMedido) throws InterruptedException {
		grupoAcessoTratativa.informarTicketMedio(ticketMedido);
	}
	
	@When("Eu clico Adicionar regra grupo acesso tratativa")
	public void euClicoAdicionarRegraGrupo() throws InterruptedException {
		grupoAcessoTratativa.clicarBtnAdicionar();
	}
	
	@When("Eu clico no botao Salvar grupo acesso tratativa")
	public void euClicoBotaoSalvar() throws InterruptedException {
		grupoAcessoTratativa.clicarBtnSalvar();
	}
	
	@When("Eu marco a opcao Troca regra grupo acesso tratativa")
	public void euMarcoOpcaoTrocaRegra() throws InterruptedException {
		grupoAcessoTratativa.marcarIndTroca();
	}
	
	@When("Eu marco a opcao Devolução regra grupo acesso tratativa")
	public void euMarcoOpcaoDevolucaoRegra() throws InterruptedException {
		grupoAcessoTratativa.marcarIndCancelamento();
	}
	
	@When("Eu marco a opcao Abertura regra grupo acesso tratativa")
	public void euMarcoOpcaoAberturaRegra() throws InterruptedException {
		grupoAcessoTratativa.marcarIndAbertura();
	}
	
	@When("Eu marco a opcao Aprovação regra grupo acesso tratativa")
	public void euMarcoOpcaoAprovacaoRegra() throws InterruptedException {
		grupoAcessoTratativa.marcarIndAprovacao();
	}
	
	@When("Eu marco a opcao Permite alterar forma de reembolso regra grupo acesso tratativa")
	public void euMarcoOpcaoFormaReembolsoRegra() throws InterruptedException {
		grupoAcessoTratativa.marcarIndReembolso();
	}
	
	@Then("Eu valido a mensagem de sucesso de grupo acesso tratativa")
	public void euValidoMensagemSucessoGrupoAcessoTratativa() throws InterruptedException {
		grupoAcessoTratativa.validarMensagemSucesso();
	}
}