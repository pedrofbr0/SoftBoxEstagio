package br.com.mv.PageFactory.devolucaoProdutos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PopupCadastroUsuario {
	
	WebDriver driver;
	
	@FindBy(css = "input[node='usuario/dataAlteracao']")
	private WebElement dataAlteracao;
	
	@FindBy(css = "input[node='usuario/codigoUsuarioAlteracao']")
	private WebElement usuarioAlteracao;
	
	@FindBy(id = "codigo")
	private WebElement codigo;
	
	@FindBy(id = "nome")
	private WebElement nome;
	
	@FindBy(id = "nomeAbreviado")
	private WebElement abreviaturaNome;
	
	@FindBy(id = "comboAtivo")
	private WebElement ativo;
	
	@FindBy(id = "senha")
	private WebElement senha;
	
	@FindBy(id = "dataExpiracaoSenha")
	private WebElement dataExpiracaoSenha;
	
	@FindBy(css = "#searchLoja > tbody > tr > td:nth-child(1) > input")
	private WebElement nroLojaAdmissao;
	
	@FindBy(css = "#searchLoja > tbody > tr > td:nth-child(2) > input")
	private WebElement descLojaAdmissao;
	
	@FindBy(css = "#searchLoja > tbody > tr > td:nth-child(4) > input[type='button']")
	private WebElement btnLimparLojaAdmissao;
	
	@FindBy(id = "cpf")
	private WebElement cpf;
	
	@FindBy(id = "comboLiberaMigVendas")
	private WebElement liberaFrenteLoja;
	
	@FindBy(id = "chapaRM")
	private WebElement matricula;
	
	@FindBy(id = "comboIndTerceiro")
	private WebElement terceiro;
	
	@FindBy(id = "comboPerfilUsuario")
	private WebElement perfil;
	
	@FindBy(id = "comboComissaoGrupoDepto")
	private WebElement grupoDepto;
	
	public PopupCadastroUsuario(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getDataAlteracao() {
		return dataAlteracao.getAttribute("value");
	}

	public String getUsuarioAlteracao() {
		return usuarioAlteracao.getAttribute("value");
	}

	public String getCodigo() {
		return codigo.getAttribute("value");
	}

	public PopupCadastroUsuario setCodigo(String codigo) {
		this.codigo.clear();
		this.codigo.sendKeys(codigo);
		return this;
	}

	public String getNome() {
		return nome.getAttribute("value");
	}

	public PopupCadastroUsuario setNome(String nome) {
		this.nome.clear();
		this.nome.sendKeys(nome);
		return this;
	}

	public String getAbreviaturaNome() {
		return abreviaturaNome.getAttribute("value");
	}

	public PopupCadastroUsuario setAbreviaturaNome(String abreviaturaNome) {
		this.abreviaturaNome.clear();
		this.abreviaturaNome.sendKeys(abreviaturaNome);
		return this;
	}

	public String getAtivo() {
		return ativo.getAttribute("value");
	}

	public PopupCadastroUsuario setAtivo(String ativo) {
		new Select(this.ativo).selectByVisibleText(ativo);
		return this;
	}

	public String getSenha() {
		return senha.getAttribute("value");
	}

	public PopupCadastroUsuario setSenha(String senha) {
		this.senha.clear();
		this.senha.sendKeys(senha);
		return this;
	}

	public String getDataExpiracaoSenha() {
		return dataExpiracaoSenha.getAttribute("value");
	}

	public PopupCadastroUsuario setDataExpiracaoSenha(String dataExpiracaoSenha) {
		this.dataExpiracaoSenha.clear();
		this.dataExpiracaoSenha.sendKeys(dataExpiracaoSenha);
		return this;
	}

	public String getNroLojaAdmissao() {
		return nroLojaAdmissao.getAttribute("value");
	}

	public PopupCadastroUsuario setNroLojaAdmissao(String nroLojaAdmissao) {
		this.nroLojaAdmissao.clear();
		this.nroLojaAdmissao.sendKeys(nroLojaAdmissao);
		return this;
	}

	public String getDescLojaAdmissao() {
		return descLojaAdmissao.getAttribute("value");
	}

	public WebElement getBtnLimparLojaAdmissao() {
		return btnLimparLojaAdmissao;
	}

	public PopupCadastroUsuario clickBtnLimparLojaAdmissao() {
		this.btnLimparLojaAdmissao.click();
		return this;
	}

	public String getCpf() {
		return cpf.getAttribute("value");
	}

	public PopupCadastroUsuario setCpf(String cpf) {
		this.cpf.clear();
		this.cpf.sendKeys(cpf);
		return this;
	}

	public String getLiberaFrenteLoja() {
		return liberaFrenteLoja.getAttribute("value");
	}

	public PopupCadastroUsuario setLiberaFrenteLoja(String liberaFrenteLoja) {
		new Select(this.liberaFrenteLoja).selectByVisibleText(liberaFrenteLoja);
		return this;
	}

	public String getMatricula() {
		return matricula.getAttribute("value");
	}

	public PopupCadastroUsuario setMatricula(String matricula) {
		this.matricula.clear();
		this.matricula.sendKeys(matricula);
		return this;
	}

	public String getTerceiro() {
		return terceiro.getAttribute("value");
	}

	public PopupCadastroUsuario setTerceiro(String terceiro) {
		new Select(this.terceiro).selectByVisibleText(terceiro);
		return this;
	}

	public String getPerfil() {
		return perfil.getAttribute("value");
	}

	public PopupCadastroUsuario setPerfil(String perfil) {
		new Select(this.perfil).selectByVisibleText(perfil);
		return this;
	}

	public String getGrupoDepto() {
		return grupoDepto.getAttribute("value");
	}

	public PopupCadastroUsuario setGrupoDepto(String grupoDepto) {
		new Select(this.grupoDepto).selectByVisibleText(grupoDepto);
		return this;
	}
	
	public PopupCadastroUsuario selecionarUsuario(int nroUsuario) {
		driver.findElement(By.xpath("//table[@id='table:tContent']/tbody/tr/td[text()='" + nroUsuario + "']/parent::tr/td[2]")).click();
		return this;
	}

}
