package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.DetalhesAtendimentoPage;
import net.thucydides.core.annotations.Steps;

public class DetalhesAtendimentoSteps {

	@Steps
	DetalhesAtendimentoPage detalhesAtendimento;
	
	@When("No detalhes do atendimento eu clico no botao Proximo")
	public void noDetalhesAtendimentoClicoBotaoProximo() throws InterruptedException {
		detalhesAtendimento.clicarBtnProximo();
	}
	
	
}