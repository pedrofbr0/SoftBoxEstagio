package br.com.mv.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.Utils;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

import static br.com.mv.model.SessionVariables.NRO_IMPORTACAO_PROMOCAO;
import static br.com.mv.model.SessionVariables.NRO_PEDIDO;

public class ImportacaoPromocaoPage extends PageObject{

	@FindBy(css = "#btn-nova-importacao")
	private WebElement btnNovaImportacao;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.desc_promocao']")
	private WebElement descPromocao;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.tipo_promocao']")
	private WebElement tipoPromocao;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.tipo_promocao'] ~ ul li a")
	private WebElement opcaoTipoPromocao;

	@FindBy(css = "input[ng-model='impPromocao.importacao.dt_inicio']")
	private WebElement dtInicio;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.dt_fim']")
	private WebElement dtFim;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.conceito_cliente']")
	private WebElement conceitoCliente;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.conceito_cliente'] ~ ul li a")
	private WebElement opcaoConceitoCliente;
	
	@FindBy(css = "input[ng-model='impPromocao.arquivo']")
	private WebElement arquivo;
	
	@FindBy(xpath = ".//button[contains(text(), 'Validar Arquivo')]")
	private WebElement btnValidarArquivo;
	
	@FindBy(xpath = ".//label[contains(text(), 'Código importação:')]/parent::fieldset/input")
	private WebElement codImportacao;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisarImportacao;
	
	@FindBy(xpath = ".//button[contains(text(), 'Efetivar Importação')]")
	private WebElement btnEfetivarImportacao;
	
	@FindBy(xpath = ".//a[contains(text(), 'Voltar')]")
	private WebElement btnVoltar;
	
	@FindBy(css = "#combo-processo")
	private WebElement comboProcesso;
	
	Utils utils;
	
	public ImportacaoPromocaoPage() {
		utils = new Utils();
	}
	
	public void informarDescricao(String descPromocao) {
		this.descPromocao.clear();
		this.descPromocao.sendKeys(descPromocao);
	}
	
	public void clicarBtnNovaImportacao() {
		this.btnNovaImportacao.click();
	}
	
	public void informarTipoPromocao(String tipoPromocao) throws InterruptedException {
		this.utils.preencherCampoTypeAHead(tipoPromocao, this.tipoPromocao);
		
		Thread.sleep(1000);
		this.opcaoTipoPromocao.click();
	}
	
	public void selecionarPermiteVariacaoPlano(String permitirVariacaoPlano) {
		
		String valorOpcao = "";
		
		if (permitirVariacaoPlano.equals("Não variar")) {
			valorOpcao = "0";
		} else if (permitirVariacaoPlano.equals("Variar em qualquer plano")) {
			valorOpcao = "1";
		} else if (permitirVariacaoPlano.equals("Variar somente planos acima")) {
			valorOpcao = "2";
		}
		
		this.getDriver().findElement(By.cssSelector("input[ng-model='impPromocao.importacao.ind_permite_variar_plano'][value='" + valorOpcao + "']")).click();
	}
	
	public void selecionarConfiguracaoPrecoPartida(String configPrecoPartida) {
		String valorOpcao = "";
		
		if (configPrecoPartida.equals("Preço partida fixo")) {
			valorOpcao = "F";
		} else if (configPrecoPartida.equals("Preço partida calculado")) {
			valorOpcao = "C";
		}
		
		this.getDriver().findElement(By.cssSelector("input[ng-model='impPromocao.importacao.flag_config_pp'][value='" + valorOpcao + "']")).click();
	}
	
	public void selecionarVisualizarAlerta(String visualizarAlerta) {
		
		String valorOpcao = "";
		
		if (visualizarAlerta.equals("Sim")) {
			valorOpcao = "1";
		} else if (visualizarAlerta.equals("Não")) {
			valorOpcao = "0";
		}
		
		this.getDriver().findElement(By.cssSelector("input[ng-model='impPromocao.importacao.ind_visualiza_alerta'][value='" + valorOpcao + "']")).click();
	}
	
	public void informarDataInicioVigencia() {
		String dataAtual = new Utils().getDataAtual();
		
		this.dtInicio.clear();
		this.dtInicio.sendKeys(dataAtual);
	}
	
	public void informarDataFimVigencia() {
		String dataAtual = new Utils().getDataAtual();
		
		this.dtFim.clear();
		this.dtFim.sendKeys(dataAtual);
	}
	
	public void informarConceitoCliente(String conceitoCliente) throws InterruptedException {
		this.conceitoCliente.clear();
		this.utils.preencherCampoTypeAHead(conceitoCliente, this.conceitoCliente);
		
		Thread.sleep(1000);
		this.opcaoConceitoCliente.click();
	}
	
	public void informarTipoPraca(String tipoPraca) {
		String valorOpcao = "";
		
		if (tipoPraca.equals("Loja")) {
			valorOpcao = "LJ";
		} else if (tipoPraca.equals("CD")) {
			valorOpcao = "CD";
		} else if (tipoPraca.equals("Estado")) {
			valorOpcao = "UF";
		} else if (tipoPraca.equals("Zona")) {
			valorOpcao = "ZN";
		} else if (tipoPraca.equals("Brasil")) {
			valorOpcao = "BR";
		}
		
		this.getDriver().findElement(By.cssSelector("input[ng-model='impPromocao.importacao.flag_tipo_praca'][value='" + valorOpcao + "']")).click();
	}
	
	public void selecionarTipoIdentificacaoItens(String tipoIdentificacaoItens) {
		
		String valorOpcao = "";
		
		if (tipoIdentificacaoItens.equals("Produto")) {
			valorOpcao = "PRO";
		} else if (tipoIdentificacaoItens.equals("Kit")) {
			valorOpcao = "KIT";
		}
		
		this.getDriver().findElement(By.cssSelector("input[ng-model='impPromocao.importacao.flag_tipo_item'][value='" + valorOpcao + "']")).click();
	}
	
	public void selecionarCopiarPromocaoProdutos(String copiarPromocao) {
		
		String valorOpcao = "";
		
		if (copiarPromocao.equals("Sim")) {
			valorOpcao = "S";
		} else if (copiarPromocao.equals("Não")) {
			valorOpcao = "N";
		}
		
		this.getDriver().findElement(By.cssSelector("input[ng-model='impPromocao.importacao.ind_replica_produto_pai'][value='" + valorOpcao + "']")).click();
	}
	
	public void informarArquivoImportacao(String nomeArquivo) {
		String pathArquivoImportacao = System.getProperty("user.dir") + "\\src\\test\\resources\\docs\\";
		
		this.arquivo.clear();
		this.arquivo.sendKeys(pathArquivoImportacao + nomeArquivo);
	}
	
	public void clicarBtnValidarArquivo() {
		this.btnValidarArquivo.click();
	}
	
	public void validaMensagemValidacaoSegundoPlano() {
		String msg = $("#divFormulario .alert").getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("Erro ao validar a inconsistência do arquivo", msg.contains("Validação de inconsistência de dados executando em segundo plano. O processo pode ser acompanhado na tela de pesquisa com código de importação:"));
	}
	
	public void validaMensagemErroInconsistencia() {
		String msg = $(".//*[@id='divFormulario']/form/*/*/fieldset/div[2]/div[1]").getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A mensagem de erro de inconsistência de dados não foi apresentada.", msg.contains("0 erro(s) de inconsistência de dados"));
	}
	
	public void validaMensagemAlertaInconsistencia() {
		String msg = $(".//*[@id='divFormulario']/form/*/*/fieldset/div[2]/div[2]").getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A mensagem de alerta inconsistência de dados não foi apresentada.", msg.contains("0 alerta(s) de inconsistência de dados"));
	}
	
	public void validaPromocaoSendoEfetivadaSegundoPlano() {
		String msg = $("#divFormulario .alert").getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A promoção não foi efetivada.", msg.contains("Promoção sendo efetivada em segundo plano. O processo pode ser acompanhado na tela de pesquisa com código de importação:"));
	}
	
	public void validaPromocaoConcluidaComSucesso() {
		String msg = $("#divFormulario .alert").getText();
		
		System.out.println(msg);
		
		Assert.assertTrue("A importação da promoção não foi realizada.", msg.contains("Importação da promoção concluída com sucesso. A nova promoção gerada possui o código:"));
	}
	
	public void clicarBtnEfetivarImportacao() {
		this.btnEfetivarImportacao.click();
	}
	
	public void clicarBtnPesquisarImportacao() {
		this.btnPesquisarImportacao.click();
	}
	
	public void clicarBtnVoltar() {
		this.btnVoltar.click();
	}
	
	public void registrarNroImportacaoPromocaoSessao() {
		
		this.waitForPresenceOf("#divFormulario .alert");
		
		String msg = $("#divFormulario .alert").getText();
		
		String nroImportacao = msg.split(":")[1].trim();
		System.out.println("nro_importacao: " + nroImportacao);
		Serenity.setSessionVariable(NRO_IMPORTACAO_PROMOCAO).to(nroImportacao);
	}
	
	public void informarCodPromocao() throws InterruptedException {
		System.out.println("nroPromo: " + (String) Serenity.sessionVariableCalled(NRO_IMPORTACAO_PROMOCAO));
		this.codImportacao.clear();
		this.codImportacao.sendKeys((String) Serenity.sessionVariableCalled(NRO_IMPORTACAO_PROMOCAO));
	}
	
	public void selecionarStatusImportacao(String status) {
		new Select(this.comboProcesso).selectByVisibleText(status);
	}
	
	public void clicarLinkEdicaoImportacao() {
		$(".//a[contains(text() , '" + Serenity.sessionVariableCalled(NRO_IMPORTACAO_PROMOCAO) + "')]").click();
	}
	
	public void aguardarConclusaoValidacao() throws InterruptedException {
		this.informarCodPromocao();
		this.selecionarStatusImportacao("Em validação");
		this.clicarBtnPesquisarImportacao();
		
		boolean emValidacao = true;
		
		while (emValidacao) {
			Thread.sleep(5000);
			emValidacao = $(".//a[contains(text() , '" + Serenity.sessionVariableCalled(NRO_IMPORTACAO_PROMOCAO) + "')]").isPresent();
			this.clicarBtnPesquisarImportacao();
		}
	}
	
	public void aguardarConclusaoImportacaoPromocao() throws InterruptedException {
		this.informarCodPromocao();
		this.selecionarStatusImportacao("Importando");
		this.clicarBtnPesquisarImportacao();
		
		boolean importando = true;
		
		while (importando) {
			Thread.sleep(5000);
			importando = $(".//a[contains(text() , '" + Serenity.sessionVariableCalled(NRO_IMPORTACAO_PROMOCAO) + "')]").isPresent();
			this.clicarBtnPesquisarImportacao();
		}
	}
	
	public void clicarLinkBaixarTemplateImportacao() {
		//$(".//button[contains(text() , 'Clique aqui para baixar o template de importação')]").click();
		$("//*[@id=\"divFormulario\"]/form/div[4]/div[2]/fieldset/button").click();
	}
	
	public void clicarLinkBaixarLogValidacao() {
		//$(".//button[contains(text() , 'Clique aqui para baixar LOG de validação')]").click();
		$("//*[@id=\"divFormulario\"]/form/div[6]/div[2]/fieldset/div[2]/button").click();
	}
}