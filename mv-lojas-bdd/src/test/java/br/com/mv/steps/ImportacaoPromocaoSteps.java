package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.AutorizacaoPage;
import br.com.mv.pages.ImportacaoPromocaoPage;
import net.thucydides.core.annotations.Steps;

public class ImportacaoPromocaoSteps {

	@Steps
	ImportacaoPromocaoPage importacaoPromocao;
	
	@When("Eu clico no botao Nova Importacao de promocao")
	public void euClicoBotaoNovaImportacaoPromocao() throws InterruptedException {
		importacaoPromocao.clicarBtnNovaImportacao();
	}
	
	@When("Eu informo a descricao $descricao da importacao de promocao")
	public void euInformoDescricaoImportacaoPromocao(String descPromocao) throws InterruptedException {
		importacaoPromocao.informarDescricao(descPromocao);
	}
	
	@When("Eu informo $tipoPromocao no tipo da promocao")
	public void euInformoTipoPromocao(String tipoPromocao) throws InterruptedException {
		importacaoPromocao.informarTipoPromocao(tipoPromocao);
	}
	
	@When("Eu seleciono $permitirVariacaoPlano em permitir variacao de plano")
	public void euSelecionoPermitirVariacaoPlano(String permitirVariacaoPlano) throws InterruptedException {
		importacaoPromocao.selecionarPermiteVariacaoPlano(permitirVariacaoPlano);		
	}
	
	@When("Eu seleciono $precoPartida em configurar preco de partida")
	public void euSelecionoPrecoPartida(String configPrecoPartida) throws InterruptedException {
		importacaoPromocao.selecionarConfiguracaoPrecoPartida(configPrecoPartida);
	}
	
	@When("Eu seleciono $visualizarAlerta em visualizar alerta")
	public void euSelecionoVisualizarAlerta(String visualizarAlerta) throws InterruptedException {
		importacaoPromocao.selecionarVisualizarAlerta(visualizarAlerta);
	}
	
	@When("Eu informo a data de comeco de vigencia da promocao")
	public void euInformoDataComecoVigencia() throws InterruptedException {
		importacaoPromocao.informarDataInicioVigencia();
	}
	
	@When("Eu informo a data de termino de vigencia da promocao")
	public void euInformoDataTerminoVigencia() throws InterruptedException {
		importacaoPromocao.informarDataFimVigencia();
	}
	
	@When("Eu informo $conceitoCliente em conceito cliente")
	public void euInformoConceitoCliente(String conceitoCliente) throws InterruptedException {
		importacaoPromocao.informarConceitoCliente(conceitoCliente);
	}
	
	@When("Eu registro o numero da importacao na sessao")
	public void euRegistroNumeroImportacaoSessao() throws InterruptedException {
		importacaoPromocao.registrarNroImportacaoPromocaoSessao();
	}
	
	@When("Eu seleciono $tipoPraca em tipo da praca")
	public void euInformoTipoPraca(String tipoPraca) throws InterruptedException {
		importacaoPromocao.informarTipoPraca(tipoPraca);
	}
	
	@When("Eu seleciono $tipoIdentificacaoItens em tipo de identificacao de itens")
	public void euInformoTipoIdentificacaoItens(String tipoIdentificacaoItens) throws InterruptedException {
		importacaoPromocao.selecionarTipoIdentificacaoItens(tipoIdentificacaoItens);
	}
	
	@When("Eu seleciono $copiarPromocao em copiar promocao para demais produtos")
	public void euSelecionoCopiarPromocao(String copiarPromocao) throws InterruptedException {
		importacaoPromocao.selecionarCopiarPromocaoProdutos(copiarPromocao);
	}
	
	@When("Eu informo o arquivo $nomeArquivo de importacao de promocao")
	public void euInformoArquivoImportacaoPromocao(String nomeArquivo) throws InterruptedException {
		importacaoPromocao.informarArquivoImportacao(nomeArquivo);
	}
	
	@When("Eu clico no botao Validar Arquivo importacao de promocao")
	public void euClicoBotaoValidarArquivo() throws InterruptedException {
		importacaoPromocao.clicarBtnValidarArquivo();
	}
	
	@When("Eu valido mensagem de inconsistencia de dados executando em segundo plano")
	public void euValidoMensagemInconsistenciaDadosSegundoPlano() throws InterruptedException {
		importacaoPromocao.validaMensagemValidacaoSegundoPlano();
	}
	
	@When("Eu valido mensagem de erro inconsistencia")
	public void euValidoMensagemErroInconsistencia() throws InterruptedException {
		importacaoPromocao.validaMensagemErroInconsistencia();
	}
	
	@When("Eu valido mensagem de alerta inconsistencia")
	public void euValidoMensagemAlertaInconsistencia() throws InterruptedException {
		importacaoPromocao.validaMensagemAlertaInconsistencia();
	}
	
	@When("Eu valido mensagem de promocao sendo efetivada em segundo plano")
	public void euValidoPromocaoSendoEfetivadaSegundoPlano() throws InterruptedException {
		importacaoPromocao.validaPromocaoSendoEfetivadaSegundoPlano();
	}
	
	@When("Eu clico no botao Pesquisar importacao de promocao")
	public void euClicoBotaoPesquisarImportacaoPromocao() throws InterruptedException {
		importacaoPromocao.clicarBtnPesquisarImportacao();
	}
	
	@When("Eu clico no botao Voltar para pesquisa de importacoes de promocoes")
	public void euClicoBotaoVoltarPesquisaImportacaoPromocao() throws InterruptedException {
		importacaoPromocao.clicarBtnVoltar();
	}
	
	@When("Eu informo o nro da promocao na pesquisa")
	public void euInformoNroPromocaoPesquisa() throws InterruptedException {
		importacaoPromocao.informarCodPromocao();
	}
	
	@When("Eu seleciono o status $status da importacao na pesquisa")
	public void euSelecionoStatusImportacaoPesquisa(String status) throws InterruptedException {
		importacaoPromocao.selecionarStatusImportacao(status);
	}
	
	@When("Eu clico no link para editar importacao de promocao")
	public void euClicoLinkEdicaoImportacao() throws InterruptedException {
		importacaoPromocao.clicarLinkEdicaoImportacao();
	}
	
	@When("Eu clico no botao efetivar importacao de promocao")
	public void euClicoBtnEfetivarImportacao() throws InterruptedException {
		importacaoPromocao.clicarBtnEfetivarImportacao();
	}
	
	@When("Eu aguardo a validacao estar concluida")
	public void euAguardoAValidacaoEstarConcluida() throws InterruptedException {
		importacaoPromocao.aguardarConclusaoValidacao();
	}
	
	@When("Eu aguardo a conclusao da importacao de promocao")
	public void euAguardoAConclusaoImportacaoPromocao() throws InterruptedException {
		importacaoPromocao.aguardarConclusaoImportacaoPromocao();
	}
	
	@Then("Eu valido mensagem de sucesso da importacao de promocao")
	public void euValidoMensagemSucessoImportacaoPromocao() throws InterruptedException {
		importacaoPromocao.validaPromocaoConcluidaComSucesso();
	}
	
	@Then("Eu clico no link para baixar o template de importacao")
	public void euClicoLinkBaixarTemplateImportacao() throws InterruptedException {
		importacaoPromocao.clicarLinkBaixarTemplateImportacao();
	}
	
	@Then("Eu clico no link para baixar o o log de validacao")
	public void euClicoLinkBaixarLogValidacao() throws InterruptedException {
		importacaoPromocao.clicarLinkBaixarLogValidacao();
	}	
}