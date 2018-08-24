package br.com.mv.pages;

import static br.com.mv.model.SessionVariables.NRO_PEDIDO;
import static br.com.mv.model.SessionVariables.COD_NRO_LOJA;
import static br.com.mv.model.SessionVariables.VAL_UNITARIO_PRODUTO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jbehave.core.annotations.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.mv.dao.LojaDAO;
import br.com.mv.dao.PedidoDAO;
import br.com.mv.dao.PreReciboDAO;
import br.com.mv.model.PreRecibo;
import br.com.mv.model.RelatorioAutomacao;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;
import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.Configuration;


public class PedidoPage extends PageObject {

	@FindBy(css = "#cbx_nro_tipo_venda_chosen")
	private WebElement tipoVendaChosen;
	
	@FindBy(css = "#cbx_plano_chosen")
	private WebElement cbxPlano;
	
	@FindBy(css = "#cbx_primeiro_vencimento_chosen")
	private WebElement cbxPrimeiroVencimento;
	
	@FindBy(css = "#cbx_garantias_avulsas_chosen")
	private WebElement garantiasAvulsas;
	
	@FindBy(css = "#btn-insere-produto")
	private WebElement btnInsereProduto;
	
	@FindBy(css = "#cbx_garantias_empresa_chosen")
	private WebElement garantiasEmpresa;
	
	@FindBy(css = "#cbx_local_empenho_chosen")
	private WebElement localEmpenho;
	
	@FindBy(css = "#cbx_opcao_entrega_chosen")
	private WebElement opcaoEntrega;
	
	@FindBy(css = "#chx-ind-entrega")
	private WebElement flagEntrega;
	
	@FindBy(css = "#btn-empenhar-produto")
	private WebElement btnEmpenharProduto;
	
	@FindBy(css = ".modal.in .btn.Ok")
	private WebElement btnOkModal;
	
	@FindBy(css = ".modal.in .btn.Sim")
	private WebElement btnSimModal;
	
	@FindBy(css = "#tab-planilha")
	private WebElement abaPlanilha;
	
	@FindBy(css = "#btn-nova-planilha")
	private WebElement btnInsereFormaPagamento;
	
	@FindBy(css = "#btn-salvar-pedido")
	private WebElement btnSalvarPedido;
	
	@FindBy(css = "#btn-fechamento-total-pedido")
	private WebElement btnFechamentoTotalPedido;
	
	@FindBy(css = "#txt-pedido-venda")
	private WebElement txtPedidoVenda;
	
	@FindBy(css = "#cbx_documento_chosen")
	private WebElement cbxDocumentoChosen;
	
	@FindBy(css = "#btn-recibo-troca-planilha")
	private WebElement btnReciboTrocaPlanilha;
	
	@FindBy(css = "input[data-column='rec_nro_pedido']")
	private WebElement recNroPedido;
	
	@FindBy(css = "input[data-column='rec_cod_nro_loja']")
	private WebElement recCodNroLoja;
	
	@FindBy(css = "#btn-verificar-credito")
	private WebElement btnVerificarCredito;
	
	@FindBy(css = "#btn-salvar-recibo-troca")
	private WebElement btnSalvarReciboTroca;
	
	@FindBy(css = "#btn-credito-sgr")
	private WebElement btnCreditoEnterprise;
	
	@FindBy(css = "#nro_pedido_enterprise_sgr")
	private WebElement nroPedidoEnterprise;
	
	@FindBy(css = "#nro_loja_enterprise_sgr")
	private WebElement nroLojaEnterprise;
	
	@FindBy(css = "#cmb_empresa_enterprise_chosen")
	private WebElement cmbEmpresaEnterpriseChosen;
	
	@FindBy(css = "#btn-verificar-credito-sgr")
	private WebElement btnVerificarCreditoEnterprise;
	
	@FindBy(css = "#btn-salvar-credito-sgr")
	private WebElement btnSalvarCreditoEnterprise;
	
	@FindBy(css = "#btn-cancelar-pedido")
	private WebElement btnCancelarPedido;

	@FindBy(css = "button.Sim")
	private WebElement btnSimRealizarAnaliseCredito;
	
	@FindBy(css = "button.Não")
	private WebElement btnNaoRealizarAnaliseCredito;
	
	@FindBy(css = "#btn-comunicacao")
	private WebElement btnComunicar;
	
	@FindBy(css = "#ckb-identidade")
	private WebElement checkIdentidade;
	
	@FindBy(css = "#ckb-cpf")
	private WebElement checkCPF;
	
	@FindBy(css = "#ckb-renda")
	private WebElement checkRenda;
	
	@FindBy(css = "#txtarea-observacoes-comunicacao")
	private WebElement observacaoComunicacao;
	
	@FindBy(css = "#btn-enviar")
	private WebElement btnEnviar;
	
	@FindBy(css = "button.Ok")
	private WebElement btnOkPropostaProcessamento;
	
	@FindBy(css = "#btn-limite-credito")
	private WebElement btnLimiteCredito;
	
	@FindBy(css = "#btn-consulta-comunicacao")
	private WebElement btnConsultaComunicacao;
	
	@FindBy(css = "div[id^='mdl-'].modal.in .modal-body")
	private WebElement mensagemPropostaAprovada;
	
	@FindBy(css = "#btn-analise-credito")
	private WebElement btnAnaliseCredito;
	
	@FindBy(css = "#btn-credito-preaprovado")
	private WebElement btnConsultarCredito;
	
	@FindBy(css = "#btn-atv-pln-ctrl")
	private WebElement btnAtivarPlanoControle;
	
	Utils utils;
	Configuration configuration;
	AguardaCarregamento agCarregamento;
	ConsultaPreSimplificadaPage consultaPreSimplificada;
	ControleJanela controleJanela;
	
	private String nroPedido;
	private int qtdConsultasPre = 0;
	PedidoDAO pedidoDAO;
    LojaDAO lojaDAO;
    PreReciboDAO preReciboDAO;
    PreRecibo preRecibo;
    
	public PedidoPage() {
		this.utils = new Utils();
		pedidoDAO = new PedidoDAO();
        lojaDAO = new LojaDAO();
        preReciboDAO = new PreReciboDAO();
	}
			
	public void clicarBotaoCancelarPedido() throws InterruptedException {
		Thread.sleep(2000);
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		this.waitFor(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn-cancelar-pedido")));
		btnCancelarPedido.click();
		clicarBtnSimModal();
		clicarBtnOkModal();
	}
	
	public void selecionarTipoVenda(String tipoVenda) throws InterruptedException {
		
		//System.out.println("url: " + configuration.getBaseUrl());
		//System.out.println("baseUrl: " + pages.getConfiguration().getBaseUrl());
		/*if (configuration.getBaseUrl().contains("100")) { // Na 101 não está apresentando o modal (VERIFICAR O MOTIVO)
			new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura();
		}*/
		
		Thread.sleep(3000);
		this.waitFor(ExpectedConditions.elementToBeClickable(tipoVendaChosen));
		utils.preencherCampoChosen(tipoVendaChosen, tipoVenda);
	}
	
	public void selecionarPlano(String plano) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(cbxPlano, plano);
	}
	
	public void selecionarPrimeiroVencimento() throws InterruptedException {
		//String dtaPrimeiroVencimento = $(".//*[@id='cbx-primeiro-vencimento']/option[10]").getText();
		
		//utils.preencherCampoChosen(cbxPrimeiroVencimento, dtaPrimeiroVencimento);
		
		this.cbxPrimeiroVencimento.click();
		this.cbxPrimeiroVencimento.findElement(By.cssSelector(".chosen-drop .chosen-search input")).clear();
		this.cbxPrimeiroVencimento.findElement(By.cssSelector(".chosen-drop .chosen-search input")).sendKeys("2");
		
		Thread.sleep(2000);
		
		$(".//*[@id='cbx_primeiro_vencimento_chosen']/div/ul/li[1]").click();
	}
	
	public void informarValorPrestacao(String vlrPrestacao) throws InterruptedException {
		Thread.sleep(1000);
		$("#txt-vr-prestacao").clear();
		Thread.sleep(1000);
		$("#txt-vr-prestacao").sendKeys(vlrPrestacao);
		Thread.sleep(1000);
	}
	
	public void clicarInsereProduto() throws InterruptedException {
		Thread.sleep(1000);
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(1000);
		//new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModalDlg();
		//Thread.sleep(2000);
		btnInsereProduto.click();
		Thread.sleep(1000);
	}
	
	public void clicarIconeDetalhesProduto(String codNroProduto) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		$(".//*[@id='grd-produtos']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/parent::tr/td[1]/button[@title='Detalhes']").click();
	}
	
	public void clicarIconeDetalhesProdutoNaoEmpenhado(String codNroProduto) throws InterruptedException {	
		List<WebElement> produtos = this.getDriver().findElements(By.xpath(".//*[@id='grd-produtos']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/parent::tr/td[1]/button[@title='Detalhes']")); 		
		
		for (WebElement produto : produtos) {
			produto.click();
			
			Thread.sleep(2000);
			new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
			Thread.sleep(2000);
			
			String labelBotaoReservar = $(".//*[@id='frm-detalhes-produto']/fieldset[2]/div/div[1]/div[3]/div/button[not(contains(@class, 'hide'))]").getText();
			
			if (labelBotaoReservar.contains("Reservar")) {
				break;
			}
			
			$(".//*[@id='btn-fechar-detalhes']").click();
			Thread.sleep(2000);
		}
	}
	
	public void informarLocalEmpenho(String descLocalEmpenho) throws InterruptedException {
		Thread.sleep(2000);
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(2000);
		utils.preencherCampoChosen(this.localEmpenho, descLocalEmpenho);
		Thread.sleep(1000);

	}
	
	
	public void informarOpcaoEntrega(String descOpcaoEntrega) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		
		// Utilizado quando se pesquisa por AGENDADA pois ao digitar efetuar a pesquisa o resultado aparecem dois registros 
		if (descOpcaoEntrega.equals("AGENDADA")) {
			this.opcaoEntrega.click();
			this.opcaoEntrega.findElement(By.cssSelector(".chosen-drop .chosen-search input")).clear();
			this.opcaoEntrega.findElement(By.cssSelector(".chosen-drop .chosen-search input")).sendKeys(descOpcaoEntrega);
			
			Thread.sleep(8000);
			
			$(".//*[@id='cbx_opcao_entrega_chosen']/div/ul/li[2]").click();
		} else {
			utils.preencherCampoChosen(this.opcaoEntrega, descOpcaoEntrega);
		}
	}
	
	public void informarFlagEntrega(String flagEntrega) throws InterruptedException {
		//new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		/*Thread.sleep(2000);
		String statusflagEntrega = this.flagEntrega.getAttribute("checked");
		
		System.out.println("Status Flag Entrega: " + statusflagEntrega);
		*/
		
		Thread.sleep(1000);
		String indFlagEntrega = "false";
		
		if (flagEntrega.equals("SIM")) {
			indFlagEntrega = "true";
		}
		
		System.out.println("Flag entrega: "+ indFlagEntrega);
		
		this.evaluateJavascript("$('#chx-ind-entrega').prop('checked', " + indFlagEntrega + ");$('#chx-ind-entrega').change().trigger('chosen:updated');$('#chx-ind-entrega').val();");
		
	}
	
	public void clicarBtnEmpenharProduto() throws InterruptedException {
		Thread.sleep(1000);
		btnEmpenharProduto.click();
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(3000);
	}
	
	public void clicarBtnOkModal() throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
//		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura();
		Thread.sleep(5000);
		
//		this.getDriver().findElement(By.xpath("//*[@id='mdl-4198']/div/div/div[3]/button")).click();
		
	
		btnOkModal.click();
	}
	
	public void clicarBtnSimModal() throws InterruptedException {
		//new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura();
		Thread.sleep(2000);
		btnSimModal.click();
	}
	
	public void clicarNaAba(String descAba) throws InterruptedException {
		Thread.sleep(1000);
		//new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura();
		Thread.sleep(2000);
		$(".//*[@id='tab-itens-pedido']/*/a[contains(text(), '" + descAba + "')]").click();
	}
	
	public void clicarBtnInsereFormaPagamento() throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModal();
		this.btnInsereFormaPagamento.click();
	}
	
	public void informarTipoPagamento(String idLinha, String tipoFormaPagto) throws InterruptedException {
		$("#grd-planilha-pagto tr[id='" + idLinha + "'] select[data-column='flag_tipo_pagto_pedido']").selectByVisibleText(tipoFormaPagto);
	}
	
	public void informarFormaPagamento(String idLinha, String formaPagamento) throws InterruptedException {
		$("#grd-planilha-pagto tr[id='" + idLinha + "'] select[data-column='nro_forma_pagto']").selectByVisibleText(formaPagamento);
	}
	
	public void informarValorPagto(String indiceFormaPagto, String valorFormaPagto) throws InterruptedException {
		$("#grd-planilha-pagto tr[id='" + indiceFormaPagto + "'] select[data-column='vr_planilha']").sendKeys(valorFormaPagto);
	}
	
	public String getNumeroPedido() {
		String descNroPedido = this.txtPedidoVenda.getAttribute("value");
		//System.out.println("Número pedido1: " + nroPedido);
		String[] desc = descNroPedido.split("/", 2);
		//System.out.println("Número pedido2: " + nroPedido);
		
		return desc[0];
	}
	
	public void clicarBtnSalvarPedido() {
		// Salvar nro do pedido
		String nroPedido = this.getNumeroPedido();
		
		//System.out.println("Número pedido3: " + nroPedido);
		
		Serenity.setSessionVariable(NRO_PEDIDO).to(nroPedido.trim());
		//System.out.println("Número pedido: " + Serenity.sessionVariableCalled(NRO_PEDIDO));
		
		btnSalvarPedido.click();
	}
	
	public void clicarBtnFechamentoTotalPedido() throws InterruptedException {
		Thread.sleep(1000);
		
		System.out.println("Numero pedido: " + Serenity.sessionVariableCalled(NRO_PEDIDO));
		
		btnFechamentoTotalPedido.click();
		Thread.sleep(1000);
	}
	
	public void validarStatusPedido(String statusPedido) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertTrue("O status do pedido está incorreto.", $("div[id^='mdl'].modal.in .modal-body").containsText(statusPedido));
	}
	
	public void validarMensagemObrigatoriedadePeloMenos1ServicoETipoServico() throws InterruptedException {
		Thread.sleep(3000);
		/*String txtMensagem = "Não foi encontrado um dos serviços:\r\n" + 
				"\r\n" + 
				"Serviço 153414 - PLANO CONTROLE.\r\n" + 
				"\r\n" + 
				"Informa��es, produtos 219254, 256403 do n�mero do kit 2305(1) relacionada a promo��o 40920.";*/
		
		
		String txtMensagem1 = "Não foi encontrado um dos serviços";
		String txtMensagem2 = "Serviço 153414 - PLANO CONTROLE";
		String txtMensagem3 = "Informações, produtos 219254 - CEL MOTO G XT1068 2CHIP 8GB PTO, 256403 - CHIP CLARO PRE TRIPLE FLEX BR do número do kit 2305(1) relacionada a promoção 40920 - CENARIO SIV - KIT.";
		
		System.out.println($("div[id^='mdl'].modal.in .modal-body").getText());
		Assert.assertTrue("Mensagem de validacao incorreta.", $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem1) && $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem2) && $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem3));
	}
	
	public void validarMensagemRegraISIV() throws InterruptedException {
		Thread.sleep(3000);
		/*String txtMensagem = "Também pode-se incluir um serviço dos tipos:\r\n" + 
				"\r\n" + 
				"Serviço 31 - PLANO CONTROLE.\r\n" + 
				"\r\n" + 
				"Informações do item, produto 256403 relacionada a promoção 40580.";*/
		
		/*Não foi encontrado um dos serviços.

		Serviços:
		Serviço 153414 - PLANO CONTROLE.

		Pode-se incluir um serviço dos tipos:
		Serviço 31 - PLANO CONTROLE.

		Informações do item, produto 256403 - CHIP CLARO PRE TRIPLE FLEX BR relacionada a promoção 40580 - VENDA CASADA 1.*/
		
		String txtMensagem1 = "Serviços:";
		String txtMensagem2 = "Serviço 153414 - PLANO CONTROLE.";
		String txtMensagem3 = "Informações do item, produto 256403 - CHIP CLARO PRE TRIPLE FLEX BR relacionada a promoção 41481 - TESTE AUTOMATIZADO.";
		
		System.out.println($("div[id^='mdl'].modal.in .modal-body").getText());
		
		
		Assert.assertTrue("Mensagem de validacao incorreta.", $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem1) && $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem2) && $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem3));
	}
	
	public void validarMensagemRegraIISIV() throws InterruptedException {
		Thread.sleep(3000);
		/*String txtMensagem = "Não foi encontrado um dos serviços:\r\n" + 
				"\r\n" + 
				"Serviço 153414 - PLANO CONTROLE.\r\n" + 
				"\r\n" + 
				"Também pode-se incluir um serviço dos tipos:\r\n" + 
				"\r\n" + 
				"Serviço 31 - PLANO CONTROLE.\r\n" + 
				"\r\n" + 
				"Informações do item, produto 256403 relacionada a promoção 40580.";*/
		
		String txtMensagem1 = "Serviços:";
		String txtMensagem2 = "Serviço 31 - PLANO CONTROLE.";
		String txtMensagem3 = "Informações do item, produto 256403 - CHIP CLARO PRE TRIPLE FLEX BR relacionada a promoção 40580 - VENDA CASADA 1.";
		
		System.out.println($("div[id^='mdl'].modal.in .modal-body").getText());
		Assert.assertTrue("Mensagem de validacao incorreta.", $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem1) && $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem2) && $("div[id^='mdl'].modal.in .modal-body").containsText(txtMensagem3));
	}
	
	public void removerTodosServicos() {
		// Remover todos os servicos
	}
	
	public void removerOServico(String nroServico) throws InterruptedException {
		Thread.sleep(1500);
		$("button[id^=btn-excluir-servico-" + nroServico).click();
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModal();
	}
	
	public void adicionarOServico(String nroServico) {
		$(".//*[@id='grid-consulta-servico']/*/tr/td[contains(text(), '" + nroServico + "')]/parent::tr/td[1]/input").click();
		$("div[id^=mdl-].modal.in .Selecionar").click();
	}
	
	public void clicarEmAdicionarServico() throws InterruptedException {
		Thread.sleep(2000);
		$("#btn-insere-servico").click();
		Thread.sleep(1000);
	}
	
	public void clicarNoBotaoAgendada() throws InterruptedException {
		Thread.sleep(3000);
		$("#btn-buscar-data-entrega-agendada").click();
	}
	
	public void selecionarDataDisponivel() throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModal();
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();

		//table[@id='grd-data-entrega']//tr[@id='1']
		
//		$("#grd-data-entrega tr[id='1']").click();

		Thread.sleep(3500);
//		$("#grd-data-entrega tr[id='1']").click();
		
//		this.getDriver().findElement(By.xpath("/html[1]/body[1]/div[2]/div[19]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[3]/div[1]/table[1]")).click();
		
//		this.getDriver().findElement(By.xpath("//table[@id='grd-data-entrega']//tr[@id='1']")).click();
		
		this.getDriver().findElement(By.xpath("//table[@id='grd-data-entrega']//tr[@id='1']/td[1]")).click();
		
		$("#btn-escolher-data").click();
	}
	
	public void selecionarLojaDisponivel(String codNroLoja) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModal();
		
		$(".//*[@id='grd-lojas-empenho']/*/tr/td[contains(text(), '" + codNroLoja + "')]/parent::tr/td[1]/input").click();
		$("#btn-escolher-loja-empenho").click();
		
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(2000);
	}
	
	public void selecionarALiberacao(String tipoLiberacao, String flagLiberacao, String codNroProduto) {
		$(".//*[@id='grd-liberacoes']/tbody/tr/td[contains(text(), '" + tipoLiberacao + "') or text() = '" + flagLiberacao + "']/parent::tr/td[contains(@title, '" + codNroProduto + "')]/parent::tr/td[1]/input").click();
	}
	
	public void clicarBotaoLiberar() {
		$("#btn-modal-liberar").click();
	}
	
	public void informarObservacao(String observacao) {
		$("#txt-obs-liberacao").sendKeys(observacao);
		$("#btn-liberar-liberacao").click();
	}
	
	public void informarUsuarioSenhaValidacaoLiberacao(String usuario, String senha) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(2000);
		System.out.println("usuario: " + usuario);
		$("#modal-validacao-usuario #usuario").clear();
		$("#modal-validacao-usuario #usuario").sendKeys(usuario);
		//$(".//*[@id='fm-validacao-pedido']/div[1]/input[@id='usuario']").sendKeys("6699");
		$("#modal-validacao-usuario #senha").sendKeys(senha);
		$("#modal-validacao-usuario .modal-validar-privilegio-ok").click();

		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModalDlg();
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
//		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura();
		Thread.sleep(1000);
	}
	
	public void informarDataRetencaoDisponivel() throws InterruptedException {
		System.out.println("Data retencao: " + this.utils.getDataAdd(10));
		Thread.sleep(3000);
		$("#txt-dt-retencao").sendKeys(this.utils.getDataAdd(10));
	}
	
	public void informarNumeroSerie(String nroSerie) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(2000);
		System.out.println("nro serie: " + nroSerie);
		$(".//*[@id='1_desc_info_adicional']").sendKeys(nroSerie);
	}
	
	public void removerGarantiaProduto(String nroGarantia, String codNroProduto) {
		Actions actions = new Actions(this.getDriver());
		
		WebElement btnExcluirGarantia = this.getDriver().findElement(By.xpath(".//*[@id='grd-garantias']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/following-sibling::td[contains(text(), '" + nroGarantia + "')]/parent::tr/td[1]/button"));
		actions.moveToElement(btnExcluirGarantia).build().perform();
		
		btnExcluirGarantia.click();
		
		//$(".//*[@id='grd-garantias']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/following-sibling::td[contains(text(), '" + nroGarantia + "')]/parent::tr/td[1]/button").click();
	}
	
	public void clicarBotaoAdicionarGarantia() {
		$("#btn-insere-garantia").click();
	}
	
	public void clicarNoProduto(String codNroProduto) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModal();
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(1000);
		$(".//*[@id='grd-produtos']/*/tr/td[contains(text(), '" + codNroProduto + "')]").click();
	}
	
	public void selecionarGarantia(String nroGarantia) {
		$(".//*[@id='grd-inserir-garantia']/*/tr/td[contains(text(), '" + nroGarantia + "')]/parent::tr/td[1]/input").click();
		$("#btn-adicionar-garantia").click();
	}
	
	public void selecionarTabelaPorProduto(String descTabela, String codNroProduto) {
		$(".//*[@id='grd-produtos']/*/tr/td[contains(text(), '" + codNroProduto + "')]/following-sibling::td[6]/*/select").selectByVisibleText(descTabela);
	}
	
	public void informarDataTurnoDisponivel() throws InterruptedException {
		System.out.println("Data/turno disponivel: " + this.utils.getDataAdd(32));
		
		$("#txt-data-entrega-cliente").clear();
		$("#txt-data-entrega-cliente").sendKeys(this.utils.getDataAdd(32));
		$("#btn-validar-data").click();
		
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModal();
		Thread.sleep(1000);
		
		this.getDriver().findElement(By.xpath("//table[@id='grd-data-entrega']//tr[@id='1']/td[1]")).click();
		$("#btn-escolher-data").click();
		
//		$(".//*[@id='grd-data-entrega']/*/tr[@id='1']").click();
//		$("#btn-escolher-data").click();
	}
	
	public void validarServicoAdicionado(String nroServico) {
		Assert.assertTrue("O serviço: " + nroServico + " não foi adicionado.", $(".//*[@id='grd-servicos']/*/tr[@id='" + nroServico + "']").isPresent());
	}
	
	public void informarValorFormaPagamento(String idLinha, String valorFormaPagamento) {
		$("#grd-planilha-pagto tr[id='" + idLinha + "'] input[data-column='vr_planilha']").clear();
		$("#grd-planilha-pagto tr[id='" + idLinha + "'] input[data-column='vr_planilha']").sendKeys(valorFormaPagamento);
	}
	
	public void infomarValorEntrada(String vlrEntrada) {
		$("#txt-entrada").clear();
		$("#txt-entrada").sendKeys(vlrEntrada);
	}
	
	public void clicarBotaoGerarFinanciamento() {
		$("#btn-gera-financiamento-planilha").click();
	}
	
	public void informarNumeroDocumento(String nroDocumento) {
		$("#mdl-gerar-financiamento input[data-column='nro_docto_pagto").sendKeys(nroDocumento);
		$("#btn-salvar-gerar-financiamento").click();
	}
	
	public void validoGarantiaPorProduto(String nroGarantia, String codNroProduto) {
		Assert.assertTrue("A garantia: " + nroGarantia + " não foi adicionada.", $(".//*[@id='grd-garantias']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/following-sibling::td[contains(text(), '" + nroGarantia + "')]").isPresent());
	}
	
	public void clicarBotaoAdicionarGarantiaAvulsa() {
		$("#btn-insere-garantia-avulsa").click();
	}
	
	public void informarNumeroPedidoAnterior() throws InterruptedException {
		// Pegar o numero do pedido salvo e informar aqui
		System.out.println("Número pedido anterior: " + Serenity.sessionVariableCalled(NRO_PEDIDO));
		
		String numPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoModalDlg();
		$(".//div[@id='dados-pedido-garantia']/div/*/input[@name='servico-garantia-avulso']").click(); // v2
		$("#aba-venda-interna #tx-nro-pedido").clear();
		//$(".//div[@id='dados-pedido']/div/*/input[@name='servico-garantia-avulso']").click(); // v1
		$("#aba-venda-interna #tx-nro-pedido").sendKeys(numPedido);
	}
	
	public void informarNumeroLoja(String codNroLoja) throws InterruptedException {
		$("#aba-venda-interna #txt-ssf-loja-garantias-cod-nro-loja-key").clear();
		$("#aba-venda-interna #txt-ssf-loja-garantias-cod-nro-loja-key").sendKeys(codNroLoja);
		$("#aba-venda-interna #txt-ssf-loja-garantias-cod-nro-loja-key").click();
		Thread.sleep(3000);
	}
	
	public void informarNumeroProduto(String codNroProduto) {
		$("#aba-venda-interna #tx-nro-produto").clear();
		$("#aba-venda-interna #tx-nro-produto").sendKeys(codNroProduto);
//		$(".//*[@id='aba-venda-interna']/div[7]/div/div/button[1]").click();
		
	}
	
	public void selecionarNotaRelacionadaProduto(String codNroProduto) throws InterruptedException {
		Thread.sleep(3000);		
		this.getDriver().findElement(By.xpath("//*[@id=\"aba-venda-interna\"]/div[7]/div/div/button[1]")).click();
		Thread.sleep(5000);
		this.getDriver().findElement(By.xpath(".//*[@id='grid-notas']/*/tr/td[contains(text(), '" + codNroProduto + "')]")).click();
//		$(".//*[@id='grid-notas']/*/tr/td[contains(text(), '" + codNroProduto + "')]").click();
		$("div[id^='mdl-'].in .Selecionar").click();
		
	}
	
	public void selecionarUmaGarantiaAvulsa(String descGarantia) throws InterruptedException {
		Thread.sleep(1000);
		utils.preencherCampoChosen(garantiasAvulsas, descGarantia);
		Thread.sleep(1000);
	}
	
	public void informarCpfConsultaNotas(String cpf) throws InterruptedException {
		Thread.sleep(1000);
		$("#dados-cliente-garantia input[type=radio]").click();
		Thread.sleep(1000);
		
		$("#tx-cpf").clear();
		$("#tx-cpf").sendKeys(cpf);
		Thread.sleep(1000);
		
		$(".//*[@id='tx-cpf']/parent::div/*/button").click();
		Thread.sleep(1000);
		
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		// Ordenacao crescente
		$("#jqgh_grid-notas_nro_pedido").click();
		Thread.sleep(2000);
		
		// Ordenacao decrescente
		$("#jqgh_grid-notas_nro_pedido").click();
		Thread.sleep(1000);
	}
	
	public void selecionarNotaRelacionadaPedido() {
		String numPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		$(".//*[@id='grid-notas']/tbody/tr/td[@aria-describedby='grid-notas_nro_pedido' and contains(text(), '" + numPedido + "')]").click();
		$("div[id^='mdl-'].in .Selecionar").click();
	}
	
	public void clicarNoBotaoInserirGarantia() throws InterruptedException {
		Thread.sleep(1000);
		$("#btn-ins-garantia").click();
		Thread.sleep(1000);
	}
	
	public void informarNotaFiscalGarantiaExterna() throws InterruptedException{
		$("#aba-venda-externa #tx-nota-fiscal-externa").sendKeys("1111");
	}
	
	public void informarDataEmissaoNotaExterna() throws InterruptedException{
		$("#aba-venda-externa #tx-data-emissao-nf-externa").sendKeys(this.utils.getDataAdd(-1));
	}
	
	public void informarEmpresaNotaExterna(String descGarantia) throws InterruptedException{
		Thread.sleep(1000);
		utils.preencherCampoChosen(garantiasEmpresa, descGarantia);
		Thread.sleep(1000);
	}
	
	public void clicarNoIconePesquisarProduto() throws InterruptedException{
		$("#btn-sf-produto-garantia-avulsa-search").click();
		//$("#btn-sf-produto-search").click();
		Thread.sleep(1000);
	}
	
	public void informarCodigoPaiDoProduto(String codigoPai) throws InterruptedException{
		$("#mdl-sf-produto-garantia-avulsa input[data-sf-column='cod_nro_produto']").sendKeys(codigoPai);
		//$("#mdl-sf-produto input[data-sf-column='cod_nro_produto']").sendKeys(codigoPai);
	}
	
	public void clicarNoBotaoPesquisar() throws InterruptedException{
		$("//div[@id='gview_grid-sf-produto-garantia-avulsa']/div/span/button[2]").click();
		//$(".//*[@id='gview_grid-sf-produto']/div[1]/span/button[2]").click();
	}
	
	public void selecionarProdutoGarantiaExterna(String codNroProduto) throws InterruptedException{
		$("#mdl-sf-produto-garantia-avulsa input[data-row-id='1']").click();
		//$("#mdl-sf-produto input[data-row-id='1']").click();
		Thread.sleep(1000);
	}
	
	public void clicarNaGarantiaAvulsaNaGridVendaExterna() throws InterruptedException{
		$("#grid-garantias-avulsas td[title='TV PANASONIC 32 TC-32A400B LED HD']").click();
	}
	
	public void clicarNoBotaoParaRemoverGarantiaAvulsa()throws InterruptedException{
		$("#btn-rem-garantia").click();
	}
	
	public void consultarPedido() throws InterruptedException {
		Thread.sleep(2000);
		//String numPedido = "4896519";
		//Serenity.setSessionVariable(NRO_PEDIDO).to("4934332");
		
		String numPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO);
		
		System.out.println("numPedido: " + numPedido);
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		$("#txt-pedido").sendKeys(numPedido);
		Thread.sleep(2000);
		$("#btn-consultar-pedido").click();
		
		Thread.sleep(2000);
		$("td[title='" + numPedido + "'] button").click();
		Thread.sleep(2000);
	}
	
	public void reabrirPedido() throws InterruptedException {
		Thread.sleep(2000);
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		this.waitFor(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn-reabertura-pedido")));
		$("#btn-reabertura-pedido").click();
		Thread.sleep(2000);
		this.clicarBtnSimModal();
		Thread.sleep(2000);
		this.clicarBtnOkModal();
		Thread.sleep(2000);
	}
	
	public void cancelarReserva() throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(1000);
		$("#btn-cancelar-empenho-produto").click();
	}
	
	public void removerTodasFormasPagamento() {
		//
	}
	
	public void removerFormaPagamento() {
		
		$(".btn-excluir-planilha").click();
	}
	
	public void alterarQuantidadeServico(String codNroServico, String quantidade) {
		$(".//*[@id='" + codNroServico + "_qtde_servico']").clear();
		$(".//*[@id='" + codNroServico + "_qtde_servico']").sendKeys(quantidade);
	}
	
	public void alterarQuantidadeProduto(String codNroProduto, String quantidade) {
//		this.getDriver().findElement(By.xpath(".//*[@id='grd-produtos']/tbody/tr/td[contains(text(),'" + codNroProduto + "')]/parent::tr/td[7]/input")).clear();
//		this.getDriver().findElement(By.xpath(".//*[@id='grd-produtos']/tbody/tr/td[contains(text(),'" + codNroProduto + "')]/parent::tr/td[7]/input")).sendKeys(quantidade);
		$(".//*[@id='grd-produtos']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/parent::tr/td[7]/input").clear();
		$(".//*[@id='grd-produtos']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/parent::tr/td[7]/input").sendKeys(quantidade);
	}
	
	public void alterarOValorUnitarioServico(String codNroServico, String vlrUnitario) throws InterruptedException {
		Thread.sleep(4000);
		this.getDriver().findElement(By.xpath(".//*[@id='" + codNroServico + "_vr_unitario_servico']/input")).clear();
		Thread.sleep(4000);
		this.getDriver().findElement(By.xpath(".//*[@id='" + codNroServico + "_vr_unitario_servico']/input")).sendKeys(vlrUnitario);
//		$(".//*[@id='" + codNroServico + "_vr_unitario_servico']").clear();
//		$(".//*[@id='" + codNroServico + "_vr_unitario_servico']").sendKeys(vlrUnitario);
	}
	
	public void removerPlanilhas() throws InterruptedException {
		while ($(".btn-excluir-planilha").isPresent()) {
			$(".btn-excluir-planilha").click();
		}
	}
	
	public void clicarBotaoCMC7() {
		$("#btn-cmc7-planilha").click();
	}
	
	public void informarCMC7(String cmc7) {
		$("#txt-cmc7").clear();
		$("#txt-cmc7").sendKeys(cmc7);
	}
	
	public void clicarBotaoValidarCMC7() {
		$("#btn-validar-cmc7").click();
	}
	
	public void informarDtAberturaContaCMC7(String dtAberturaConta) {
		$("#frm-cheque input[data-column='dt_conta']").sendKeys(dtAberturaConta);
	}
	
	public void clicarBotaoSalvarCheque() {
		$("#btn-salvar-cheque").click();
	}
	
	public void removerProduto(String codNroProduto) throws InterruptedException {
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		$(".//*[@id='grd-produtos']/tbody/tr/td[contains(text(), '" + codNroProduto + "')]/parent::tr/td[1]/button[@title='Excluir']").click();
	}
	
	public void executarScriptReset(String nomScript) throws IOException {
		 new PedidoDAO().executarScriptReset(nomScript);
	}
	
	public void clicarBotaoImprimirDocumentos() throws InterruptedException {
		Thread.sleep(2000);
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		$("#btn-imprimir-documentos").click();
	}
	
	public void selecionarDocumento(String nomDocumento) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(this.cbxDocumentoChosen, nomDocumento);
	}
	
	public void clicarBotaoGerarRelatorio() {
		$("#btn-modal-imprimir").click();
	}
	
	public void validarTermoAdesaoAntivirusHero() {
		
	}
	
	public void validarTermoAdesaoPassaporte() {
		
	}
	
	public void salvarPedidoRelatorio(String codTeste) throws IOException {
		
		Boolean gravarPedidoRelatorio = Boolean.parseBoolean(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("mvlojas.gravar.pedido.relatorio"));
		
		if (gravarPedidoRelatorio) {
			RelatorioAutomacao.salvarPedidoRelatorio(codTeste, Serenity.sessionVariableCalled(NRO_PEDIDO));
		}
	}
	
	public void validarValoresCalculoReversoPlanilha() throws IOException {
		
		new RelatorioAutomacao().validarValoresCalculoReverso();
	}
	
	public void clicarBtnReciboTrocaPlanilha() {
		btnReciboTrocaPlanilha.click();
	}
	
	public void informarPedidoComCredito() {
		recNroPedido.clear();
		recNroPedido.sendKeys((String) Serenity.sessionVariableCalled(NRO_PEDIDO));
	}
	
	public void informarLojaPedidoComCredito() {
		recCodNroLoja.clear();
		recCodNroLoja.sendKeys((String) Serenity.sessionVariableCalled(COD_NRO_LOJA));
	}
	
	public void informarUmPedidoComCredito() {
		preRecibo = preReciboDAO.getPreReciboAprovado();
		
		recNroPedido.clear();
		recNroPedido.sendKeys(Integer.toString(preRecibo.getNroPedido()));
	}
	
	public void informarUmaLojaPedidoComCredito() {
		recCodNroLoja.clear();
		recCodNroLoja.sendKeys(Integer.toString(preRecibo.getCodNroLoja()));
	}
	
	public void clicarBtnVerificarCredito() {
		btnVerificarCredito.click();
	}
	
	public void clicarBtnSalvarReciboTroca() {
		btnSalvarReciboTroca.click();
	}
	
	public void clicarBtnCreditoEnterprise() {
		btnCreditoEnterprise.click();
	}
	
	public void informarPedidoComCreditoEnterprise(String nroPedido) {
		nroPedidoEnterprise.clear();
		nroPedidoEnterprise.sendKeys(nroPedido);
	}
	
	public void informarLojaPedidoComCreditoEnterprise(String nroLojaPedido) {
		nroLojaEnterprise.clear();
		nroLojaEnterprise.sendKeys(nroLojaPedido);
	}
	
	public void selecionarEmpresaCreditoEnterprise(String nomEmpresa) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(this.cmbEmpresaEnterpriseChosen, nomEmpresa);
	}
	
	public void clicarBtnVerificarCreditoEnterprise() {
		btnVerificarCreditoEnterprise.click();
	}
	
	public void clicarBtnUtilizarCreditoEnterprise() {
		btnSalvarCreditoEnterprise.click();
	}
	
	public void clicarBtnOkModalCreditoEnterprise() throws InterruptedException {
		Thread.sleep(3000);
		btnOkModal.click();
	}
	
	/* COMUNICACAO FINANCIADORA */
	
	public void clicarBtnSimRealizarAnaliseCredito() throws InterruptedException {
		btnSimRealizarAnaliseCredito.click();
	}
	
	public void clicarBtnNaoRealizarAnaliseCredito() throws InterruptedException {
		btnNaoRealizarAnaliseCredito.click();
	}

	public void clicarBtnComunicar() throws InterruptedException {
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		btnComunicar.click();
	}
	
	public void selecionarIdentidade() throws InterruptedException {
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		//Thread.sleep(2000);
		checkIdentidade.click();
	}
	
	public void selecionarCPF() throws InterruptedException {
		checkCPF.click();
	}

	public void selecionarComprovanteRenda() throws InterruptedException {
		checkRenda.click();
	}
	
	public void informarObservacaoComunicacao(String observacao) throws InterruptedException {
		Thread.sleep(1000);
		observacaoComunicacao.sendKeys(observacao);
	}
	
	public void clicarBotaoEnviar() throws InterruptedException {
		Thread.sleep(500);
		btnEnviar.click();
	}
	
	public void clicarBotaoOkPropostaProcessamento() throws InterruptedException {
		agCarregamento.aguardarCarregamentoNovaArquitetura2();
		btnOkPropostaProcessamento.click();
	}
	
	public void acessarLinkAprovacaoPropostas() throws IOException, InterruptedException {
		Thread.sleep(5000);
		
		EnvironmentVariables variaveisAmbiente = SystemEnvironmentVariables.createEnvironmentVariables();
		String urlAprovacaoPropostas = variaveisAmbiente.getProperty("mvlojas.urlAprovacaoPropostas");
		
		URL url = new URL(urlAprovacaoPropostas);
		new InputStreamReader(url.openStream());
	}
	
	public void clicarBotaoConsultaComunicacao() {
		btnConsultaComunicacao.click();
	}
	
	public void consultarRetornoProposta() throws InterruptedException {
		String mensagemAnalise = "";
		
		// Aguarda o tempo de consulta da avaliação
		for (int i = 0; i <= 60; i++) {
			System.out.println(new Date());
			Thread.sleep(1000);
			
			if (i == 60) {
				agCarregamento.aguardarCarregamentoNovaArquitetura2();
				this.clicarBtnComunicar();
				
				agCarregamento.aguardarCarregamentoNovaArquitetura2();
				clicarBotaoConsultaComunicacao();
			
				mensagemAnalise = $("div[id^=mdl].modal.in .modal-body").getText();
				
				if ((mensagemAnalise.contains("Tempo mínimo para consulta") || mensagemAnalise.contains("Proposta em processamento, favor aguardar para realizar uma nova consulta."))) {
					$(".modal.in .btn.Ok").click();
					consultarRetornoProposta();
				}
			}
		}
		
		System.out.println(mensagemAnalise);
		
		Assert.assertTrue("A proposta foi negada.", mensagemAnalise.contains("aprovada"));
	}
	
	public void validarRetornoComunicacao() {
		String msgRetornoComunicacao = mensagemPropostaAprovada.getText();
		
		Assert.assertTrue("A proposta não foi aprovada.", msgRetornoComunicacao.contains("Proposta aprovada, status do pedido alterado para Ag. Recebimento"));
		
		
	}
	
	public void clicarBtnLimiteCredito() {
		this.btnLimiteCredito.click();
	}
	
	public void verificarCapturaLimite() throws InterruptedException {
		int qtdTempo = 90; // 3 minutos
		String msgCaptura = "";
		
		for (int i = 0; i <= qtdTempo; i++) {
			Thread.sleep(1000);
			
			if (i == 1 || i % 60 == 0) { // Minuto a minuto
				clicarBtnLimiteCredito();
				
				msgCaptura = $("div[id^=mdl].in .modal-dialog .modal-body").getText();
				
				//"Sem informações de captura! Será iniciada uma nova captura! "
				//"Já existe uma captura de limite sendo processada!"
				
				this.clicarBtnOkModal();
				
				System.out.println(msgCaptura +" no minuto: " + i / 60);
				
				//"Consulta concluída! Número máximo de parcelas: -1Percentual de entrada: -100%Carência: Não Categoria: NZ Valor aprovado de parcela: R$50Valor aprovado de crédito: R$450Valor disponível de parcela: R$-9.67Valor disponível total: R$-146.7"
				if (msgCaptura.contains("Consulta concluída")) {
					break;
				}
			}
		}
	}
	
	public void clicarBtnAnaliseCredito() throws InterruptedException {
		Thread.sleep(2000);
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		this.waitFor(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn-analise-credito")));
		btnAnaliseCredito.click();
	}
	
	public void salvarValorUnitarioProdutoSessao(String codNroProduto) {
		String valUnitarioProduto = $(".//*[@id='grd-produtos']/*/tr/td[contains(text(), '" + codNroProduto + "')]/parent::tr/td[9]/input").getValue();
		Serenity.setSessionVariable(VAL_UNITARIO_PRODUTO).to(valUnitarioProduto.trim());
	}
	
	public void validarValorUnitarioProdutoCalculoReverso(String codNroProduto) {
		String valUnitarioProdutoSessao = Serenity.sessionVariableCalled(VAL_UNITARIO_PRODUTO);
		String valUnitarioProdutoGrid = $(".//*[@id='grd-produtos']/*/tr/td[contains(text(), '" + codNroProduto + "')]/parent::tr/td[9]").getTextValue();
		
		System.out.println("sessao: " + valUnitarioProdutoSessao + " apos fechamento total: " + valUnitarioProdutoGrid);
		
		//BigDecimal bg = new BigDecimal(converte(valUnitarioProdutoGrid)).setScale(2, RoundingMode.HALF_EVEN);
		//System.out.println("sessao: " + valUnitarioProdutoSessao + " apos fechamento total: " + bg);
		
		// O valUnitarioProdutoSessao(valor antes de fazer o fechamento do pedido) deve ser menor q o valor apos o fechamento
		Assert.assertTrue("Calculo reverso estah incorreto.", Float.parseFloat(valUnitarioProdutoGrid.replace(",", ".")) > Float.parseFloat(valUnitarioProdutoSessao.replace(",", ".")));
	}
	
	public void clicarBtnConsultarCredito() {
		this.btnConsultarCredito.click();
	}
	
	public void consultaPreSimplificada() throws InterruptedException {
		
		consultaPreSimplificada.setTipoVenda("66 - CARNE LOSANGO SCRED").clickEnviar();
		
		Thread.sleep(1000);
		
		String msgConsulta = $(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '66 - Carne Losango SCRED')]/parent::tr/td[4]").getText();
		String msgAvaliacao = $(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '66 - Carne Losango SCRED')]/parent::tr/td[3]").getText();
		
		System.out.println("Mensagem de consulta: " + msgConsulta);
		System.out.println("Mensagem de avaliação: " + msgAvaliacao);
		
		//agCarregamento.aguardarCarregamentoNovaArquitetura();
		//agCarregamento.aguardarCarregamentoNovaArquitetura2();
		
		/*if (msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok"))); // às vezes nao aparece
			driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
		}*/
		
		// !msgConsulta.equals("CONSULTA SIMPLIFICADA PROCESSADA, ACIONAR O BOTÃO [CONSULTAR]") && 
		// !msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")
		
		/*Thread.sleep(5000);
		
		ExpectedCondition<Boolean> modalAguardeConsultar = ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok"));
		ExpectedCondition<WebElement> elementoModalAguardeConsultar = ExpectedConditions. visibilityOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok"));
		
		System.out.println(modalAguardeConsultar);
		System.out.println(elementoModalAguardeConsultar);
		
		if (!Boolean.TRUE.equals(modalAguardeConsultar) == false) {
		
			System.out.println("Fechando modal!!");
			driver.findElement(By.cssSelector("div.modal.alert.in .btn.Ok")).click();
		}*/
		
		if (!msgAvaliacao.equals("Elegível") && 
				(msgConsulta.equals("CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR")
					|| msgConsulta.equals("CONSULTA SIMPLIFICADA PROCESSADA, ACIONAR O BOTÃO [CONSULTAR]"))){
			
			/*if (qtdConsultasPre == 0) {
				this.waitFor(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.modal.alert.in .btn.Ok")));
				$("div.modal.alert.in .btn.Ok").click();
			}*/
			
			// Aguarda o tempo de consulta da avaliação
			for (int i = 0; i <= 60; i++) {
				Thread.sleep(1000);
				System.out.println(new Date());
				
				if (i == 60) {
					
					consultaPreSimplificada.setTipoVenda("66 - CARNE LOSANGO SCRED").clickConsultar();
					
					// CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS INSTANTES E ACIONE CONSULTAR ou PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!
					agCarregamento.aguardarCarregamentoModal();
					
					Thread.sleep(2000);
					// PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!
					$("div.modal.alert.in .btn.Ok").click();
					
					Thread.sleep(1000);
					msgAvaliacao = $(".//*[@id='grid-cps']/tbody/tr/td[contains(text(), '66 - Carne Losango SCRED')]/parent::tr/td[3]").getText();
					
					System.out.println("msgAvaliacao: " + msgAvaliacao);
					
					if (!msgAvaliacao.equals("Elegível")){
						qtdConsultasPre++;
						consultaPreSimplificada();
						
					} /*else {
						// PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!
						$("div.modal.alert.in .btn.Ok").click();
					}*/
				}
			}
		}
		
		agCarregamento.aguardarCarregamentoModal();
		consultaPreSimplificada.selecionarTipoVendaElegivel("66 - Carne Losango SCRED").clickElaborarPedido();
	}
	
	public void validarMensagemServicoVinculadoCombo() {
		String msgServicoVinculado = $("div[id^='mdl'].in .modal-body").getText();
		
		Assert.assertTrue("Servico nao estah vinculado no combo.", msgServicoVinculado.contains("O serviço AGENDA FACIL não pode ser excluido, pois está vinculado ao servico HERO ESSENCIAL!"));
	}
	
	public void validarServicoNaoExistenteGrid(String nroServico) {
		Assert.assertFalse("O serviço: " + nroServico + " foi adicionado incorretamente.", $(".//*[@id='grd-servicos']/*/tr[@id='" + nroServico + "']").isPresent());
	}
	
	public void validarServicoNaoExistenteGridConsultaServicos(String nroServico) {
		Assert.assertFalse("O serviço: " + nroServico + " estah sendo apresentado incorretamente.", $(".//*[@id='grid-consulta-servico']/*/tr/td[contains(text(), '" + nroServico + "')]").isPresent());
	}
	
	public void validarMensagemGarantiaVinculadoCombo() {
		String msgGarantiaVinculada = $("div[id^='mdl'].in .modal-body").getText();
		
		Assert.assertTrue("Garantia nao estah vinculada no combo.", msgGarantiaVinculada.contains("A garantia AGENDA FACIL não pode ser excluida, pois está vinculado a garantia HERO ESSENCIAL!"));
	}
	
	public void validarGarantiaNaoExistenteGrid(String nroServico) {
		Assert.assertFalse("A garantia: " + nroServico + " foi adicionada incorretamente.", $(".//*[@id='grd-garantias']/*/tr/td[contains(text(), '" + nroServico + "')]").isPresent());
	}
	
	public void validarValoresCalculoReverso() {
		String numPedido = (String) Serenity.sessionVariableCalled(NRO_PEDIDO);
		new PedidoDAO().validarValoresCalculoReverso(numPedido);
	}
	
	public void clicarBotaoAtivarPlanoControle() throws InterruptedException {
		Thread.sleep(2000);
		$("#btn-atv-pln-ctrl").click();
		//controleJanela.abrirPopupClick(this.btnAtivarPlanoControle);
		// vai para janela de ativacao
	}
	
	public void verificarBotaoLiberarPedidoSemAtivacaoNaoEstaPresente() {
		$("#btn-login-externo").waitUntilVisible();
		Assert.assertFalse("Botão nao deveria aparecer!", $("#liberar-pedido").isVisible());
	}
	
	public void clicarBotaoLiberarPedidoSemAtivacao() {
		$("#liberar-pedido").waitUntilVisible();
		$("#liberar-pedido").click();
	}
	
	public void ativarPlanoControle() throws InterruptedException {
		Thread.sleep(2000);
		$("#modal-continue a").click();
		
		$(".btn-success").click();
		
		// Etapas APROVADA(s) com sucesso!
		this.waitFor(ExpectedConditions.alertIsPresent());
		this.getAlert().accept();
		
		// volta para janela principal
		controleJanela.voltarJanelaPrincipal();
	}
	
	public void botaoVisivelAtivarPlanoControle() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue("O botao Ativar Plano Controle nao estah visivel.", $("#btn-atv-pln-ctrl").isVisible());
	}
	
	public void alterarDataFechamentoPedidoRegraDevolucaoParcial() {
		String codNroLoja = (String) Serenity.sessionVariableCalled(COD_NRO_LOJA);
        
        Map<String, String> metadata = HomePage.getTags();
		
		String base = "RELOH";
		
		if (!metadata.get("base").isEmpty()) {
			base = metadata.get("base");
		}
		
		String nroEmpresa = "1";
		
		if (base.equals("RELOH")) {
			nroEmpresa = "5";
		}
		
		//Serenity.setSessionVariable(NRO_PEDIDO).to("4931664");
		
		int nroLoja = lojaDAO.buscarLoja(Integer.parseInt(codNroLoja), Integer.parseInt(nroEmpresa)).getNroLoja();
		int nroPedido = Integer.parseInt(Serenity.sessionVariableCalled(NRO_PEDIDO));
		
		pedidoDAO.alterarDataFechamentoPedidoRegraDevolucaoParcial(nroLoja, nroPedido);
	}
	
	public void clicarBotaoLiberarAguardandoCentral() {
		$("#btn-liberar-pedido-aguardando-central").click();
	}
	
	public void informarDescricaoLiberacaoPosVenda(String descricao) {
		$("#txt-obs-liberacao-pos-venda").clear();
		$("#txt-obs-liberacao-pos-venda").sendKeys(descricao);
	}
	
	public void clicarBotaoSimModal() {
		$("button.Sim").click();
	}
}