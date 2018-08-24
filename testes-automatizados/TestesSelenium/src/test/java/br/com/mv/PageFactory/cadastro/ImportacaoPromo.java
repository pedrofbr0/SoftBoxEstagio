package br.com.mv.PageFactory.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.utils.Utils;

public class ImportacaoPromo {

	WebDriver driver;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.desc_promocao']")
	private WebElement descPromocao;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.tipo_promocao']")
	private WebElement tipoPromocao;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.tipo_promocao'] ~ ul li a")
	private WebElement opcaoTipoPromocao;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.ind_permite_variar_plano'][value='0']")
	private WebElement indPermiteVariarPlano;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.ind_visualiza_alerta'][value='1']")
	private WebElement indVisualizaAlerta;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.dt_inicio']")
	private WebElement dtInicio;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.dt_fim']")
	private WebElement dtFim;
			
	@FindBy(css = "input[ng-model='impPromocao.importacao.flag_tipo_praca'][value='LJ']")
	private WebElement flagTipoPraca;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.flag_tipo_item'][value='PRO']")
	private WebElement flagTipoItem;
	
	@FindBy(css = "input[ng-model='impPromocao.importacao.ind_replica_produto_pai'][value='N']")
	private WebElement indReplicaProdutoPai;
			
	@FindBy(css = "#btn-nova-importacao")
	private WebElement btnNovaImportacao;

	@FindBy(css = "input[ng-model='impPromocao.arquivo']")
	private WebElement arquivo;
	
	@FindBy(xpath = ".//button[contains(text(), 'Validar Arquivo')]")
	private WebElement btnValidarArquivo;
	
	@FindBy(xpath = ".//a[contains(text(), 'Voltar')]")
	private WebElement btnPesquisar;
	
	private Map<String, Integer> statusImportacaoPromocao;
	
	@FindBy(xpath = ".//label[contains(text(), 'Código importação:')]/parent::fieldset/input")
	private WebElement codImportacao;
	
	@FindBy(css = "#btn-pesquisar")
	private WebElement btnPesquisarImportacao;
	
	@FindBy(xpath = ".//button[contains(text(), 'Efetivar Importação')]")
	private WebElement btnEfetivarImportacao;
	
	@FindBy(css = "#combo-processo")
	private WebElement comboProcesso;
	
	Utils utils;
	
	public ImportacaoPromo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		this.utils = new Utils();
		this.statusImportacaoPromocao = new HashMap<String, Integer>();
		
		statusImportacaoPromocao.put("Todos", 0);
		statusImportacaoPromocao.put("Em validação", 1);
		statusImportacaoPromocao.put("Validação concluída", 2);
		statusImportacaoPromocao.put("Erro validação", 3);
		statusImportacaoPromocao.put("Importando", 4);
		statusImportacaoPromocao.put("Importação concluída", 5);
		statusImportacaoPromocao.put("Erro importação", 6);
	}

	public ImportacaoPromo setDescPromocao(String descPromocao) {
		this.descPromocao.clear();
		this.descPromocao.sendKeys(descPromocao);
		return this;
	}
	
	public ImportacaoPromo setTipoPromocao(String tipoPromocao) throws InterruptedException {
		this.utils.preencherCampoTypeAHead(tipoPromocao, this.tipoPromocao);
		
		Thread.sleep(1000);
		
		this.opcaoTipoPromocao.click();
		
		return this;
	}
	
	public ImportacaoPromo clickVisualizaAlerta() {
		this.indVisualizaAlerta.click();
		return this;
	}
	
	public ImportacaoPromo clickPermiteVariarPlano() {
		this.indPermiteVariarPlano.click();
		return this;
	}
	
	public ImportacaoPromo setDtInicio(String dtInicio) {
		this.dtInicio.clear();
		this.dtInicio.sendKeys(dtInicio);
		return this;
	}
	
	public ImportacaoPromo setDtFim(String dtFim) {
		this.dtFim.clear();
		this.dtFim.sendKeys(dtFim);
		return this;
	}
	
	public ImportacaoPromo clickTipoPraca() {
		this.flagTipoPraca.click();
		return this;
	}
	
	public ImportacaoPromo clickTipoItem() {
		this.flagTipoItem.click();
		return this;
	}
	
	public ImportacaoPromo clickReplicaProdutoPai() {
		this.indReplicaProdutoPai.click();
		return this;
	}
	
	public ImportacaoPromo clickBtnNovaImportacao() {
		this.btnNovaImportacao.click();
		return this;
	}
	
	public ImportacaoPromo clickBtnValidarArquivo() {
		this.btnValidarArquivo.click();
		return this;
	}
	
	public ImportacaoPromo setArquivo(String arquivo) {
		this.arquivo.clear();
		this.arquivo.sendKeys(arquivo);
		return this;
	}
	
	public ImportacaoPromo clickPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	
	public ImportacaoPromo setStatusPesquisa(String status) {
		//driver.findElement(By.cssSelector("input[type='radio'][value='"+ this.statusImportacaoPromocao.get(status) +"']")).click();
		new Select(this.comboProcesso).selectByVisibleText(status);
		
		return this;
	}
	
	public ImportacaoPromo clickPesquisarImportacao() {
		this.btnPesquisarImportacao.click();
		return this;
	}
	
	public ImportacaoPromo setCodPromocao(String codPromocao) {
		this.codImportacao.clear();
		this.codImportacao.sendKeys(codPromocao);
		return this;
	}
	
	public ImportacaoPromo clickEfetivarImportacao() {
		this.btnEfetivarImportacao.click();
		return this;
	}
}
