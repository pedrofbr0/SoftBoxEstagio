package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;

public class PDVWebPage extends PageObject {

	public void pesquisarPedido() throws InterruptedException {
		
		//Serenity.setSessionVariable(NRO_PEDIDO).to("4931664");
		
		Thread.sleep(5000);
		$("#filtro-flag-acesso").clear();
		$("#filtro-flag-acesso").sendKeys((String) Serenity.sessionVariableCalled(NRO_PEDIDO));
		
		$("#btn-pesquisar").click();
	}
	
	public void receberPedido() throws InterruptedException {
		Thread.sleep(5000);
		$("#btn-receber").click();
	}
	
	public void preencherValores() throws InterruptedException {
		Thread.sleep(5000);
		
		System.out.println("qtdFormas: " + buscarQtdFormasPagto());
		
		for (int i = 1; i <= buscarQtdFormasPagto(); i++) {
			
			System.out.println("i: " + i);
			String formaPagto = $("//label[contains(text(), 'Formas de Pagamento')]/parent::div/table/tbody[" + i + "]/tr/td[1]").getText();
			String vlrReceber = $("//label[contains(text(), 'Formas de Pagamento')]/parent::div/table/tbody[" + i + "]/tr/td[2]").getText();
			
			System.out.println("vlrReceber: " + vlrReceber);
			
			System.out.println("$(\"//label[contains(text(), 'Formas de Pagamento')]/parent::div/table/tbody['\"" + i + " \"']/tr/td[1]\")");
			
			String vlrReceberFormaPagto = (String) this.evaluateJavascript("var vlrReceber = '" + vlrReceber + "'; return vlrReceber.replace(/[\\D]+/g,'');");
	    	
			System.out.println(".. " + formaPagto + ": " + vlrReceberFormaPagto);
			
			Thread.sleep(5000);
			
			//$(".table input[ng-model='planilha.valorRecebidoPedido']").clear();
	    	//$(".table input[ng-model='planilha.valorRecebidoPedido']").sendKeys(vlrReceberFormaPagto);
			
			$("//label[contains(text(), 'Formas de Pagamento')]/parent::div/table/tbody[ " + i + "]/tr/td[3]/input").clear();
			$("//label[contains(text(), 'Formas de Pagamento')]/parent::div/table/tbody[ " + i + "]/tr/td[3]/input").sendKeys(vlrReceberFormaPagto);
	    }
	}
	
	public void validarMsgRecebimentoPedido() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue("O pedido NÃO foi recebido.", $("//html/body/div[2]/div/div[2]/div/div/strong").containsText("recebido com sucesso!"));
	}
	
	public void clickBotaoEmitirCupom() throws InterruptedException {
		Thread.sleep(2000);
		$("#btn-emitir-cupom").click();
	}
	
	public Long buscarQtdFormasPagto() {
		Long qtdformasPagto = (long) this.evaluateJavascript("var scope = $('table').scope(); return scope.pedido.planilhas.length;");
		
		return qtdformasPagto;
	}
	
	public void clicarFormaPagamentoTipoFinalizacaoTEF2AbrirLiberacaoPOS() throws InterruptedException { // tipo finalizacao CAR
		Thread.sleep(5000);
		System.out.println("clicou liberar pos");
		$("//label[contains(text(), 'Formas de Pagamento')]/parent::div/table/tbody[1]/tr/td[1]").click();
		
		/*Thread.sleep(10000);
		System.out.println("clicou liberar pos 2");
		$("//label[contains(text(), 'Formas de Pagamento')]/parent::div/table/tbody[1]/tr/td[1]").click();*/
	}
	
	public void informarNumeroDocELoteLiberarPos(String nroDocto, String nroLote) throws InterruptedException {
		Thread.sleep(5000);
		$("#btn-liberar-pos").click();
		
		$("#nro-docto").sendKeys(nroDocto);
		$("#lote").sendKeys(nroLote);
		
		$("#btn-confirmar-liberacao-pos").click();
		
		this.waitFor(ExpectedConditions.alertIsPresent());
		this.getAlert().accept();
		
		Assert.assertTrue("Liberação POS não efetuada!.", $(".alert").containsText("Liberação POS efetuada com sucesso!"));
	}
}