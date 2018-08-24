package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.EdicaoNotaFiscalPage;
import br.com.mv.pages.EmissaoNotaFiscalAvulsaPage;
import br.com.mv.pages.PopupParametrosPage;
import net.thucydides.core.annotations.Steps;

public class EdicaoNotaFiscalSteps {

	@Steps
	EdicaoNotaFiscalPage edicaoNotaFiscal;
	
	@When("Na edicao de nota fiscal eu informo numero da nf")
	public void euInformoNumeroNF() throws InterruptedException {
		edicaoNotaFiscal.informarNumeroNF();
	}
	
	@When("Na edicao de nota fiscal eu informo a serie $serieNF da nf")
	public void euInformoSerieNF(String serieNF) throws InterruptedException {
		edicaoNotaFiscal.informarSerieNF(serieNF);
	}
	
	@When("Na edicao de nota fiscal eu informo a loja $codLoja da nf")
	public void euInformoLojaNF(String codLoja) throws InterruptedException {
		edicaoNotaFiscal.informarLojaNF(codLoja);
	}
	
	@When("Na edicao de nota fiscal eu clico no botao Pesquisar")
	public void euClicoBotaoPesquisar() throws InterruptedException {
		edicaoNotaFiscal.clicarBtnPesquisar();
	}
	
	@When("Na edicao de nota fiscal eu seleciono a nota encontrada")
	public void euSelecionoNotaEncontrada() throws InterruptedException {
		edicaoNotaFiscal.selecionarNotaEncontrada();
	}
	
	@When("Na edicao de nota fiscal eu clico no botao Editar nf")
	public void euClicoBotaoEditarNF() throws InterruptedException {
		edicaoNotaFiscal.clicarBtnAcao();
	}
	
	@When("Na edicao de nota fiscal eu informo o valor base ICMS $valorBaseICMS")
	public void euInformoValorBaseICMS(String valorBaseICMS) throws InterruptedException {
		edicaoNotaFiscal.informarValorBaseICMS(valorBaseICMS);
	}
	
	@When("Na edicao de nota fiscal eu clico no botao Salvar")
	public void euClicoBotaoSalvar() throws InterruptedException {
		edicaoNotaFiscal.clicarBtnSalvar();
	}
	
	@Then("Eu valido a mensagem de sucesso da edicao de nota")
	public void euValidoMensagemSucessoEdicaoNota() throws InterruptedException {
		edicaoNotaFiscal.validarMensagemSucesso();
	}
}