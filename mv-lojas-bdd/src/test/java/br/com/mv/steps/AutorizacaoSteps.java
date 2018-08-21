package br.com.mv.steps;

import org.jbehave.core.annotations.When;

import br.com.mv.pages.AutorizacaoPage;
import net.thucydides.core.annotations.Steps;

public class AutorizacaoSteps {

	@Steps
	AutorizacaoPage autorizacao;
	
	@When("Na tela de autorizacao eu clico no botao Novo")
	public void naTelaAutorizacaoEuClicoBotaoNovo() throws InterruptedException {
		autorizacao.clicarBotaoNovo();
	}
	
	@When("Na tela de autorizacao eu seleciono o tipo de autotizacao $tipoAutorizacao")
	public void naTelaAutorizacaoEuSelecionoTipoAutorizacao(String tipoAutorizacao) throws InterruptedException {
		autorizacao.setTipoAutorizacao(tipoAutorizacao);
	}
	
	@When("Na tela de autorizacao eu informo a loja destino $codLoja")
	public void naTelaAutorizacaoEuInformoLojaDestino(String codLoja) throws InterruptedException {
		autorizacao.setLojaDestino(codLoja);
	}
	
	@When("Na tela de autorizacao eu informo a observacao $observacao")
	public void naTelaAutorizacaoEuInformoObservacao(String observacao) throws InterruptedException {
		autorizacao.setObservacao(observacao);
	}
	
	@When("Na tela de autorizacao eu informo o tipo de nota fiscal $tipoNotaFiscal")
	public void naTelaAutorizacaoEuInformoTipoNotaFiscal(String tipoNotaFiscal) throws InterruptedException {
		autorizacao.setTipoNotaFiscal(tipoNotaFiscal);
	}
	
	@When("Na tela de autorizacao eu clico no botao Salvar")
	public void naTelaAutorizacaoEuClicoBotaoSalvar() throws InterruptedException {
		autorizacao.clicarBotaoSalvar();
	}
	
	@When("Na tela de autorizacao eu clico em Ok no alert")
	public void naTelaAutorizacaoEuClicoOkAlert() throws InterruptedException {
		autorizacao.clicarOkAlert();
	}
	
	@When("Na tela de autorizacao eu clico em Sim no alert")
	public void naTelaAutorizacaoEuClicoSimAlert() throws InterruptedException {
		autorizacao.clicarSimAlert();
	}
	
	@When("Na tela de autorizacao eu clico no botao Inserir")
	public void naTelaAutorizacaoEuClicoInserir() throws InterruptedException {
		autorizacao.clicarBotaoInserir();
	}
	
	@When("No popup de itens de autorizacao eu pesquiso o produto $codProduto")
	public void noPopupItensAutorizacaoEuPesquisoProduto(String codProduto) throws InterruptedException {
		autorizacao.pesquisarProduto(codProduto);
	}
	
	@When("No popup de itens de autorizacao eu informo a quantidade solicitada de $qtdSolicitada")
	public void noPopupItensAutorizacaoEuInformoQuantidadeSolicitada(String qtdSolicitada) throws InterruptedException {
		autorizacao.setQtdSolicitada(qtdSolicitada);
	}
	
	@When("No popup de itens de autorizacao eu clico no botao Salvar")
	public void noPopupItensAutorizacaoEuClicoBotaoSalvar() throws InterruptedException {
		autorizacao.clicarBotaoSalvarPopupItens();
	}
	
	@When("No popup de itens de autorizacao eu clico em Ok no alert")
	public void noPopupItensAutorizacaoEuClicoOkAlert() throws InterruptedException {
		autorizacao.clicarSimAlertPopupItens();
	}
	
	@When("No popup de itens de autorizacao eu clico no botao Sair")
	public void noPopupItensAutorizacaoEuClicoBotaoSair() throws InterruptedException {
		autorizacao.clicarBotaoSair();
	}
	
	@When("Na tela de autorizacao eu clico no botao Processar")
	public void naTelaAutorizacaoEuClicoBotaoProcessar() throws InterruptedException {
		autorizacao.clicarBotaoProcessar();
	}
	
	@When("Na tela de autorizacao eu seleciono a autorizacao")
	public void naTelaAutorizacaoEuSelecionoAutorizacao() throws InterruptedException {
		autorizacao.selecionarAutorizacao();
	}
	
	@When("Na tela de autorizacao eu clico no botao Itens")
	public void naTelaAutorizacaoEuClicoBotaoItens() throws InterruptedException {
		autorizacao.clicarBotaoItens();
	}
	
	@When("No popup de itens de autorizacao eu informo a quantidade autorizada de $qtdAutorizada")
	public void noPopupItensAutorizacaoEuInformoQuantidadeAutorizada(String qtdAutorizada) throws InterruptedException {
		autorizacao.setQtdAutorizada(qtdAutorizada);
	}
	
	@When("Na tela de autorizacao eu clico no botao Pesquisar")
	public void naTelaAutorizacaoEuClicoBotaoPesquisar() throws InterruptedException {
		autorizacao.clicarBotaoPesquisar();
	}
	
	@When("Na tela de autorizacao eu informo a autorizacao")
	public void naTelaAutorizacaoEuInformoAutorizacao() throws InterruptedException {
		autorizacao.informarCodigo();
	}
	
	@When("Na tela de autorizacao eu seleciono o status da autorizacao $statusAutorizacao")
	public void naTelaAutorizacaoEuSelecionoStatusAutorizacao(String statusAutorizacao) throws InterruptedException {
		autorizacao.selecionarStatusAutorizacao(statusAutorizacao);
	}
	
	@When("Na tela de autorizacao eu clico no botao Processar para preencher nota avulsa")
	public void naTelaAutorizacaoEuClicoBotaoProcessarPreecherNotaAvulsa() throws InterruptedException {
		autorizacao.clicarBotaoProcessarPreencherNotaAvulsa();
	}
}