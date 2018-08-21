package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ClientePage;
import br.com.mv.pages.HomePage;
import br.com.mv.pages.VendedorLinhaEspecificaPage;
import net.thucydides.core.annotations.Steps;

public class VendedorLinhaEspecificaSteps {
	
	@Steps
//	HomePage homePage;
	VendedorLinhaEspecificaPage vlePage;
	
	@When("Eu informo e seleciono um novo departamento $dep")
	public void euInformoESelecionoUmNovoDep(String dep) {
		vlePage.informarDepartamento(dep);
	}
	
	@When("Eu valido a mensagem de '$msg' do tipo '$tipo'")
	public void euValidoMensagemDeXDoTipoY (String msg, String tipo) throws InterruptedException {
		vlePage.validarMensagemDeXDoTipoY(msg, tipo);
	}
	
	@When("Num sei")
	public void euNumSei () {
		vlePage.numSei();		
	}
	
}
