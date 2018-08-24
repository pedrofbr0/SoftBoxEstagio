package br.com.mv.PageFactory.preRecibo;

import java.text.DecimalFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PlanilhaAprovacao {
	
	WebDriver driver;

	@FindBy(xpath = "html/body/div[2]/div/form/div[9]/div[2]/fieldset/table/tbody/tr/td[4]/select")
	private WebElement formaPagtoDevolucao;
	
	@FindBy(xpath = "html/body/div[2]/div/form/div[9]/div[2]/fieldset/table/tbody/tr/td[5]/input")
	private WebElement valorDevolucao;
	
	@FindBy(xpath = "html/body/div[2]/div/form/div[9]/div[2]/fieldset/table/tbody/tr/td[6]/textarea")
	private WebElement observacao;
	
	private WebElement valorOriginal;
	
	private WebElement valorSaldo;
	
	private WebElement valorRestante;
	
	@FindBy(id = "btn-aprovar")
	private WebElement btnAprovar;
	
	@FindBy(id = "btn-voltar")
	private WebElement btnRetornar;
	

	// Pesquisa de pré-recibo na tela de aprovação de planilha
	
	@FindBy(id = "filtro-nro-pre-recibo")
	private WebElement filtroNroPreRecibo;
	
	@FindBy(id = "radio-pedido-legado")
	private WebElement radioPedidoLegado;
	
	@FindBy(id = "btn-pesquisar")
	private WebElement btnPesquisar;
	
	@FindBy(xpath = ".//*[@id='forma-pgto']")
	private WebElement formaPagto;
	
	@FindBy(css = ".linhas_pagamento input[ng-model='valor_pgto']")
	private WebElement valorPagto;
	
	@FindBy(css = ".linhas_pagamento textarea[ng-model='obs_pgto']")
	private WebElement observacaoPagto;
	
	@FindBy(xpath = ".//*[@class='linhas_pagamento']/div/div[4]/button")
	private WebElement btnAdicionar;
	
	@FindBy(id = "btn-salvar")
	private WebElement btnSalvar;
	
	@FindBy(id = "btn-limpar-resultado") // Btn Voltar
	private WebElement btnLimparResultado;
	
	private static DecimalFormat df2 = new DecimalFormat(".00");
	
	public PlanilhaAprovacao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public PlanilhaAprovacao setFiltroNroPreRecibo(String nroPreRecibo) {
		this.filtroNroPreRecibo.clear();
		this.filtroNroPreRecibo.sendKeys(nroPreRecibo);
		return this;
	}
	
	public PlanilhaAprovacao clickRadioPedidoLegado() {
		this.radioPedidoLegado.click();
		return this;
	}
	
	public PlanilhaAprovacao clickBtnPesquisar() {
		this.btnPesquisar.click();
		return this;
	}
	 
	public PlanilhaAprovacao clickBtnAdicionar() {
		this.btnAdicionar.click();
		return this;
	}
	
	public PlanilhaAprovacao clickBtnSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public PlanilhaAprovacao clickEditarAprovacaoPlanilha(int nroPreRecibo) {
		driver.findElement(By.cssSelector("btn-editar-" + nroPreRecibo)).click();
		return this;
	}
	
	public PlanilhaAprovacao configuracaoDevolucao() {
		
		int qtdFormasPagamento = this.getQtdFormasPagamento();
		Float vlrPorFormaPagamento = this.getValorDisponivel() / qtdFormasPagamento;
		
		for (int i = 1; i< qtdFormasPagamento; i++) {
			new Select(this.formaPagto).selectByIndex(i);
			
			this.valorPagto.sendKeys(df2.format(vlrPorFormaPagamento));
			this.observacaoPagto.sendKeys("Teste automatizado!!!");
			this.clickBtnAdicionar();
		}

		this.clickBtnSalvar();
		
		return this;
	}
	
	public Float getValorDisponivel() {
		String vlrDisponivel = driver.findElement(By.cssSelector("#valor-disponivel")).getText();
		
		vlrDisponivel = vlrDisponivel.replace("R$ ", "").replace(".", "").replace(",", ".");
		
		return Float.parseFloat(vlrDisponivel);
	}
	
	public Float getValorLancado() {
		String vlrLancado = driver.findElement(By.cssSelector("#valor-lancado")).getText();
		
		vlrLancado = vlrLancado.replace("R$ ", "").replace(".", "").replace(",", ".");
		
		return Float.parseFloat(vlrLancado);
	}
	
	public int getQtdFormasPagamento() {
		int qtdFormasPagamento = driver.findElements(By.xpath(".//*[@id='forma-pgto']/option")).size();
		return qtdFormasPagamento;
	}
	
	public int getQtdLinhasPlanilha() {
		int qtdLinhasPlanilha = driver.findElements(By.xpath("html/body/div[2]/div/div[9]/table/tbody")).size();
		return qtdLinhasPlanilha;
	}
	
	public PlanilhaAprovacao aprovarPlanilha() {
		int qtdLinhasPlanilha = this.getQtdLinhasPlanilha();
		
		for (int i = 1; i <= qtdLinhasPlanilha; i++) {
			float vlrOriginal = this.getValorOriginal(i);
			float vlrRestante = this.getValorRestante(i);
			
			if (vlrRestante > vlrOriginal) {
				this.preencherLinhaPlanilha(vlrOriginal, i);
			} else {
				this.preencherLinhaPlanilha(vlrRestante, i);
			}
		}
		
		this.clickAprovar();
		
		return this;
	}
	
	public void preencherLinhaPlanilha(float valor, int i) {
		this.setFormaPagtoDevolucao(i, "DEVOLUCAO ENTERPRISE").setValorDevolucao(i, "R$ " + df2.format(valor)).setObservacao(i);
	}
	
	public Float getValorRestante(int nroLinha) {
		String vlrRestante = driver.findElement(By.xpath("html/body/div[2]/div/div[9]/fieldset[3]/label[contains(text(), \"Total restante:\")]/parent::fieldset/div")).getText();
		
		vlrRestante = vlrRestante.replace("R$ ", "").replace(".", "").replace(",", ".");
		
		return Float.parseFloat(vlrRestante);
	}
	
	public Float getValorOriginal(int nroLinha) {
		String vlrOriginal = driver.findElement(By.xpath("html/body/div[2]/div/div[9]/table/tbody[" + nroLinha + "]/tr/td[3]")).getText();
		
		vlrOriginal = vlrOriginal.replace("R$ ", "").replace(".", "").replace(",", ".");
		
		return Float.parseFloat(vlrOriginal);
	}
	
	public Float getValorAutorizadoDevolucao() {
		this.valorSaldo = driver.findElement(By.xpath("html/body/div[2]/div/div[9]/fieldset[1]/div"));
		
		String vlrSaldo =  this.valorSaldo.getText();
		vlrSaldo = vlrSaldo.replace("R$ ", "");
		
		return Float.parseFloat(vlrSaldo);
	}
	
	public String getFormaPagtoDevolucao(int nroLinha) {
		this.formaPagtoDevolucao = driver.findElement(By.xpath("html/body/div[2]/div/div[9]/table/tbody[" + nroLinha + "]/tr/td[4]/select"));
		return formaPagtoDevolucao.getAttribute("value");
	}
	
	public PlanilhaAprovacao setFormaPagtoDevolucao(int nroLinha, String descFormaPagto) {
		this.formaPagtoDevolucao = driver.findElement(By.xpath("html/body/div[2]/div/div[9]/table/tbody[" + nroLinha + "]/tr/td[4]/select"));
		
		new Select(this.formaPagtoDevolucao).selectByIndex(1); //selectByVisibleText(descFormaPagto);
		
		return this;
	}
	
	public String getValorDevolucao(int nroLinha) {
		this.valorDevolucao = driver.findElement(By.xpath("html/body/div[2]/div/div[9]/table/tbody[" + nroLinha + "]/tr/td[5]/input"));
		return this.valorDevolucao.getAttribute("value");
	}
	
	public PlanilhaAprovacao setValorDevolucao(int nroLinha, String nroValor) {
		this.valorDevolucao = driver.findElement(By.xpath("html/body/div[2]/div/div[9]/table/tbody[" + nroLinha + "]/tr/td[5]/input"));
		this.valorDevolucao.clear();
		this.valorDevolucao.sendKeys(nroValor);
		return this;
	}
	
	public PlanilhaAprovacao setObservacao(int nroLinha) {
		driver.findElement(By.xpath("html/body/div[2]/div/div[9]/table/tbody[" + nroLinha + "]/tr/td[6]/textarea")).sendKeys("Teste automatizado!");
		return this;
	}

	public PlanilhaAprovacao clickAprovar() {
		this.btnAprovar.click();
		return this;
	}
	
	public PlanilhaAprovacao clickRetornar() {
		this.btnRetornar.click();
		return this;
	}
}
