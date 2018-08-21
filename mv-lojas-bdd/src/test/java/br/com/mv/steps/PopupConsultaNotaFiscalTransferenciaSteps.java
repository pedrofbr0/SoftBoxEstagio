package br.com.mv.steps;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

import org.jbehave.core.annotations.When;

import br.com.mv.dao.NotaFiscalDAO;
import br.com.mv.pages.PopupConsultaNotaFiscalTransferenciaPage;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class PopupConsultaNotaFiscalTransferenciaSteps {

	@Steps
	PopupConsultaNotaFiscalTransferenciaPage poupConsultaNotaFiscalTrasf;
	NotaFiscalDAO nfDAO;
	AguardaCarregamento agCarregamento;
	ControleJanela controleJanela;
	
	@When("Na consulta de Notas de transferencias eu informo o numero da Nota do deposito $deposito da loja $loja do pedido")
	public void naConsultaNFTransInformoNumeroNota(String deposito, String loja) throws InterruptedException {
		
		String nroPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO);
		System.out.println("nroPedido" + nroPedido + " loja:" + loja +" deposito: "+deposito);
		
		int nroNFe = new NotaFiscalDAO().getNotaFiscalTransferencia(Integer.parseInt(deposito), Integer.parseInt(loja), Integer.parseInt(nroPedido)).getNroNf();
		
		System.out.println("nroNFe: " + nroNFe);
		
		new NotaFiscalDAO().aprovarNFe(Integer.parseInt(deposito), nroNFe);
		
		poupConsultaNotaFiscalTrasf.setNumeroNf(Integer.toString(nroNFe));
	}
	
	@When("Na consulta de Notas de transferencias eu limpo o campo loja destino")
	public void naConsultaNFTransLimpaLojaDestino() throws InterruptedException {
		poupConsultaNotaFiscalTrasf.limparLojaDestino();
	}
	
	@When("Na consulta de Notas de transferencias eu clico no botao Pesquisar Nota")
	public void naConsultaNFTransClicoBotaoPesquisarNota() throws InterruptedException {
		poupConsultaNotaFiscalTrasf.clickBtnSearchNota();
	}
	
	@When("Na consulta de Notas de transferencias eu seleciono a Nota")
	public void naConsultaNFTransSelecionoNota() throws InterruptedException {
		agCarregamento.aguardarCarregamentoArquiteturaAntiga();
		poupConsultaNotaFiscalTrasf.selecionarNFe().clickBtnSelecionar();
		controleJanela.voltarJanelaPrincipal();
	}
	
	
	
	
}