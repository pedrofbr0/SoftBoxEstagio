package br.com.mv.steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.PDVWebPage;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class PDVWebSteps {

	@Steps
	PDVWebPage pdvWeb;
	
	/**
	 * Pesquisar pedido
	 * @throws InterruptedException 
	 * 
	 */
	@When("No pdv eu pesquiso o pedido")
    public void euPesquisoPedido() throws InterruptedException {
		Boolean receberPedido = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.receber.pedido"));
		
		if (receberPedido) {
			pdvWeb.pesquisarPedido();
		}
    }
	
	/**
	 * Clica no botão receber pedido
	 * @throws InterruptedException 
	 * 
	 */
	@When("No pdv eu clico no botao receber pedido")
    public void euClicoBotaoReceberPedido() throws InterruptedException {
		Boolean receberPedido = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.receber.pedido"));
		
		if (receberPedido) {
			pdvWeb.receberPedido();
		}
    }
	
	/**
	 * Clica no botão receber pedido
	 * @throws InterruptedException 
	 * 
	 */
	@When("No pdv eu preencho os valores")
    public void euPreenchoValores() throws InterruptedException {
		Boolean receberPedido = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.receber.pedido"));
		
		if (receberPedido) {
			pdvWeb.preencherValores();
		}
    }
	
	/**
	 * Clica no botão receber pedido
	 * @throws InterruptedException 
	 * 
	 */
	@When("No pdv eu clico no botao Emitir Cupom")
    public void euClicoBotaoEmitirCupom() throws InterruptedException {
		Boolean receberPedido = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.receber.pedido"));
		
		if (receberPedido) {
			pdvWeb.clickBotaoEmitirCupom();
		}
    }
	
	/**
	 * Clica no botão receber pedido
	 * @throws InterruptedException 
	 * 
	 */
	@Then("No pdv eu valido se o pedido foi recebido")
    public void euValidoORecebimentoPedido() throws InterruptedException {
		Boolean receberPedido = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.receber.pedido"));
		
		if (receberPedido) {
			pdvWeb.validarMsgRecebimentoPedido();
		}
    }
	
	@When("No pdv eu clico na forma de pagamento para liberar pos")
    public void clicarFormaPagtoAbrirFormularioLiberarPos() throws InterruptedException {
		Boolean receberPedido = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.receber.pedido"));
		
		Thread.sleep(5000);
		if (receberPedido) {
			pdvWeb.clicarFormaPagamentoTipoFinalizacaoTEF2AbrirLiberacaoPOS();
		}
    }
	
	@When("No pdv eu informo o numero do documento $nroDocto e lote $nroLote do form liberar pos")
    public void informarNumeroDoctoELoteLiberarPos(String nroDocto, String nroLote) throws InterruptedException {
		Boolean receberPedido = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.receber.pedido"));
		
		Thread.sleep(5000);
		if (receberPedido) {
			pdvWeb.informarNumeroDocELoteLiberarPos(nroDocto, nroLote);
		}
    }
}