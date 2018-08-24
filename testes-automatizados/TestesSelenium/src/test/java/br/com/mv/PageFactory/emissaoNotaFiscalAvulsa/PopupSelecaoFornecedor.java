package br.com.mv.PageFactory.emissaoNotaFiscalAvulsa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupSelecaoFornecedor {
	
	WebDriver driver;
	
	@FindBy(id = "txtCodigo")
	private WebElement codigo;
	
	@FindBy(id = "txtNome")
	private WebElement nome;
	
	@FindBy(id = "cbxAtivo")
	private WebElement ativo;
	
	@FindBy(id = "cbxGrupoFornecedor")
	private WebElement grupo;
	
	@FindBy(id = "cbxEmpresa")
	private WebElement empresa;
	
	@FindBy(id= "txtDataInclusao")
	private WebElement dataInclusao;
	
	@FindBy(id = "txtDataAlteracao")
	private WebElement dataAlteracao;
	
	public PopupSelecaoFornecedor(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getCodigo() {
		return codigo.getAttribute("value");
	}

	public PopupSelecaoFornecedor setCodigo(String codigo) {
		this.codigo.clear();
		this.codigo.sendKeys(codigo);
		return this;
	}

	public String getNome() {
		return nome.getAttribute("value");
	}

	public PopupSelecaoFornecedor setNome(String nome) {
		this.nome.clear();
		this.nome.sendKeys(nome);
		return this;
	}

	public String getAtivo() {
		return ativo.getAttribute("value");
	}

	public PopupSelecaoFornecedor setAtivo(String ativo) {
		new Select(this.ativo).selectByVisibleText(ativo);
		return this;
	}

	public String getGrupo() {
		return grupo.getAttribute("value");
	}

	public PopupSelecaoFornecedor setGrupo(String grupo) {
		new Select(this.grupo).selectByVisibleText(grupo);
		return this;
	}

	public String getEmpresa() {
		return empresa.getAttribute("value");
	}

	public PopupSelecaoFornecedor setEmpresa(String empresa) {
		this.empresa.clear();
		this.empresa.sendKeys(empresa);
		return this;
	}

	public String getDataInclusa() {
		return dataInclusao.getAttribute("value");
	}

	public PopupSelecaoFornecedor setDataInclusa(String dataInclusao) {
		this.dataInclusao.clear();
		this.dataInclusao.sendKeys(dataInclusao);
		return this;
	}

	public String getDataAlteracao() {
		return dataAlteracao.getAttribute("value");
	}

	public PopupSelecaoFornecedor setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao.clear();
		this.dataAlteracao.sendKeys(dataAlteracao);
		return this;
	}
	
	public PopupSelecaoFornecedor selecionarFornecedor(int nroFornecedor) {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + nroFornecedor + "']/parent::tr/td[2]")).click();
		return this;
	}

}
