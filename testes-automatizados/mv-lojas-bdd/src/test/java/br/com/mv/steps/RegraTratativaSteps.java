package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.RegraTratativaPage;
import net.thucydides.core.annotations.Steps;

public class RegraTratativaSteps {

	@Steps
	RegraTratativaPage regraTratativa;
	
	@When("Eu informo o filtro descricao trativa $descricao")
	public void euInformoFiltroDescricaoTratativa(String descricao) throws InterruptedException {
		regraTratativa.informarDescricao(descricao);
	}
	
	@When("Eu clico no botao Pesquisar regra de tratativa")
	public void euClicoBotaoPesquisar() throws InterruptedException {
		regraTratativa.clicarBtnPesquisar();
	}
	
	@When("Eu informo descricao $descricao regra de tratativa")
	public void euInformoDescricaoTratativa(String descricao) throws InterruptedException {
		regraTratativa.informarDescricao(descricao);
	}
	
	@When("Eu informo dias $dias regra de tratativa")
	public void euInformoDiasRegraTratativa(String dias) throws InterruptedException {
		regraTratativa.informarPeriodoDias(dias);
	}
	
	@When("Eu informo horas $horas regra de tratativa")
	public void euInformoHorasRegraTratativa(String horas) throws InterruptedException {
		regraTratativa.informarPeriodoHoras(horas);
	}
	
	@When("Eu informo minutos $minutos regra de tratativa")
	public void euInformoMinutosRegraTratativa(String minutos) throws InterruptedException {
		regraTratativa.informarPeriodoMinutos(minutos);
	}
	
	@When("Eu clico no botao Salvar regra de tratativa")
	public void euClicoBotaoSalvarRegraTratativa() throws InterruptedException {
		regraTratativa.clicarBtnSalvar();
	}
	
	@Then("Eu valido a mensagem de sucesso de regra de tratativa")
	public void euValidoMensagemSucesso() throws InterruptedException {
		regraTratativa.validarMensagemSucesso();
	}
	
	@When("Eu clico no icone Editar regra de tratativa")
	public void euClicoIconeEditarRegraTratativa() throws InterruptedException {
		regraTratativa.clicarIconeEditar();
	}
	
}