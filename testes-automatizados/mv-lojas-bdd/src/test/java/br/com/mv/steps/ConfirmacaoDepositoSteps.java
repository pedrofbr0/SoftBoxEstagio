package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ConfirmacaoDepositoPage;
import net.thucydides.core.annotations.Steps;

public class ConfirmacaoDepositoSteps {

	@Steps
	ConfirmacaoDepositoPage confirmacaoDeposito;
	
	@When("Eu seleciono o bordero para confirmacao")
	public void euSelecionoBorderoConfirmacao() throws InterruptedException {
		confirmacaoDeposito.selecionarBordero();
	}
	
	@When("Eu clico no botao Confirmar deposito")
	public void euClicoNoBotaoConfirmarDeposito() throws InterruptedException {
		confirmacaoDeposito.clicarBotaoConfirmar();
	}
	
	@When("Eu informo a data do deposito")
	public void euInformoDataDeposito() throws InterruptedException {
		confirmacaoDeposito.informarDataDeposito();
	}
	
	@When("Eu informo a hora do deposito $hora")
	public void euInformoHoraDeposito(String horaDep) throws InterruptedException {
		confirmacaoDeposito.informarHoraDeposito(horaDep);
	}
	
	@When("Eu informo o valor de deposito em dinheiro")
	public void euInformoValorDepositoDinheiro() throws InterruptedException {
		confirmacaoDeposito.informarValorDeposito();
	}
	
	@When("Eu seleciono checkbox banco fora do padrao")
	public void euSelecionoBancoForaPadrao() throws InterruptedException {
		confirmacaoDeposito.selecionarCheckboxBancoForaPadrao();
	}
	
	@When("Eu seleciono o banco $banco")
	public void euSelecionoBancoForaPadrao(String banco) throws InterruptedException {
		confirmacaoDeposito.selecionarComboBanco(banco);
	}
	
	@When("Eu informo o numero do comprovante $nroCompDinheiro deposito em dinheiro")
	public void euInformoNumeroCompravanteDepositoDinheiro(String nroCompDinheiro) throws InterruptedException {
		confirmacaoDeposito.informarNumeroComprovanteDinheiro(nroCompDinheiro);
	}
	
	@When("Eu informo a observacao da confirmacao deposito $obs")
	public void euInformoObservacaoConfirmacao(String obs) throws InterruptedException {
		confirmacaoDeposito.informarObservacao(obs);
	}
	
	@When("Eu clico no botao Ok confirmar deposito")
	public void euClicoNoBotaoOkConfirmarDeposito() throws InterruptedException {
		confirmacaoDeposito.clicarBtnOkConfirmacao();
	}
	
	@Then("Eu valido a mensagem de sucesso da confirmacao")
	public void euValidoMensgaemSucessoConfirmacao() throws InterruptedException {
		confirmacaoDeposito.validarMensagemConfirmacao();
	}
	
	@Then("Eu valido o banco fora do padrao $codBanco")
	public void euValidoMensagemSucessoConfirmacao(String codBanco) throws InterruptedException {
		confirmacaoDeposito.validarBancoForaPadrao(codBanco);
	}
	
	@When("Eu informo o numero do bordero")
	public void euInformoNumeroBordero() throws InterruptedException {
		confirmacaoDeposito.informarNumeroBordero();
	}
	
	@When("Eu clico no botao pesquisar depositos confirmados")
	public void euClicoBotaoPesquisarDepositosConfirmados() throws InterruptedException {
		confirmacaoDeposito.clicarBtnPesquisarDepositos();
	}
	
	@Then("Eu valido o bordero confirmado")
	public void euValidoBorderoConfirmado() throws InterruptedException {
		confirmacaoDeposito.validarBorderoEncontrado();
	}
	
	@When("Eu informo a dta inicio do bordero $dtaInicioBordero")
	public void euInformoDtaInicioBordero(String dtaInicioBordero) throws InterruptedException {
		confirmacaoDeposito.informarDtaInicioBordero(dtaInicioBordero);
	}
	
	@When("Eu informo a dta fim do bordero $dtaFimBordero")
	public void euInformoDtaFimBordero(String dtaFimBordero) throws InterruptedException {
		confirmacaoDeposito.informarDtaFimBordero(dtaFimBordero);
	}
	
	@When("Eu informo o cod loja do bordero")
	public void euInformoCodLojaBordero() throws InterruptedException {
		confirmacaoDeposito.informarCodLojaBordero();
	}
	
	@When("Eu clico no botao Imprimir")
	public void euClicoBotaoImprimir() throws InterruptedException {
		confirmacaoDeposito.clicarBtnImprimirRelatorio();
	}
	
	@Then("Eu valido a impressao do relatorio de deposito do bordero")
	public void euValidoImpressaoRelatorioDepositoBordero() throws InterruptedException {
		confirmacaoDeposito.validarImpressaoRelatorio();
	}
}