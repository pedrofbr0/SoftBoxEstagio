package br.com.mv.PageFactory.cadastro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Loja {

	WebDriver driver;
	
	// CAMPOS LOJA
	
	@FindBy(css = "#txtCodigo")
	private WebElement txtCodigo;
	
	@FindBy(css = "#txtCodigoLoja")
	private WebElement txtCodigoLoja;
	
	@FindBy(css = "#comboGrupoRegional")
	private WebElement comboGrupoRegional;
	
	@FindBy(css = "#txtDescricao")
	private WebElement txtDescricao;
	
	@FindBy(css = "#comboClassificacaoTipoLoja")
	private WebElement comboClassificacaoTipoLoja;
	
	@FindBy(css = "#comboTipoConfirmacao")
	private WebElement comboTipoConfirmacao;
	
	@FindBy(css = "#txtAvisoSenha")
	private WebElement txtAvisoSenha;
	
	@FindBy(css = "#txtIpServidor")
	private WebElement txtIpServidor;
	
	@FindBy(css = "#comboCentroCusto")
	private WebElement comboCentroCusto;
	
	@FindBy(css = "#comboTransferencia")
	private WebElement comboTransferencia;

	@FindBy(css = "#txtPraça")
	private WebElement txtPraca;
	
	@FindBy(css = "#comboBaseCalculoMVA")
	private WebElement comboBaseCalculoMVA;
	
	@FindBy(xpath = ".//*[@id='searchCliente']/tbody/tr/td[2]/input")
	private WebElement pesquisarCliente;
	
	@FindBy(css = "#comboClassificacao")
	private WebElement comboClassificacao;
	
	@FindBy(css = "#comboTipoLoja")
	private WebElement comboTipoLoja;
	
	@FindBy(css = "#txtEmail")
	private WebElement txtEmail;
	
	@FindBy(css = "#txtValidadeSenha")
	private WebElement txtValidadeSenha;
	
	@FindBy(css = "#txtQtdNotas")
	private WebElement txtQtdNotas;
	
	@FindBy(css = "#txtRenovacaoNF")
	private WebElement txtRenovacaoNF;
	
	@FindBy(css = "#txtServidor")
	private WebElement txtServidor;
	
	@FindBy(css = "#comboBandeira")
	private WebElement comboBandeira;
	
	@FindBy(css = "#txtCodigoSerieNfLoja")
	private WebElement txtCodigoSerieNfLoja;
	
	@FindBy(css = "#comboLojaEmpenho")
	private WebElement comboLojaEmpenho;
	
	@FindBy(xpath = ".//*[@id='txtNumLojaAbastecimento']/parent::td/parent::tr/td[2]/input[@class='buttonSearchField']")
	private WebElement pesquisarLojaAbastecimento;
	
	// CAMPOS ENDEREÇO
	
	@FindBy(xpath = ".//*[@id='searchCidade']/tbody/tr/td[2]/input")
	private WebElement pesquisarCidade;
	
	@FindBy(xpath = ".//*[@id='searchLogradouro']/tbody/tr/td[2]/input")
	private WebElement pesquisarLogradouro;
	
	@FindBy(css = "#txtComplemento")
	private WebElement complemento;

	@FindBy(css = "#txtDDD")
	private WebElement ddd;
	
	@FindBy(css = "#txtNumeroFone")
	private WebElement numeroFone;
	
	@FindBy(css = "#txtUF")
	private WebElement uf;
	
	@FindBy(css = "#txtNumero")
	private WebElement numero;
	
	@FindBy(css = "#txtBairro")
	private WebElement bairro;
	
	// CAMPOS GRUPOS
	
	@FindBy(css = "#comboGrupoPreco")
	private WebElement comboGrupoPreco;
	
	@FindBy(css = "#comboGrupoVenda")
	private WebElement comboGrupoVenda;
	
	@FindBy(css = "#comboGrupoCredito")
	private WebElement comboGrupoCredito;
	
	@FindBy(css = "#comboGrupoParametro")
	private WebElement comboGrupoParametro;
	
	@FindBy(css = "#comboGrupoPromocao")
	private WebElement comboGrupoPromocao;
	
	@FindBy(css = "#comboGrupoMensagem")
	private WebElement comboGrupoMensagem;
	
	@FindBy(css = "#txtCNPJ")
	private WebElement txtCNPJ;
	
	@FindBy(css = "#txtRS")
	private WebElement txtRS;
	
	@FindBy(css = "#txtFantasia")
	private WebElement txtFantasia;
	
	@FindBy(css = "#txtIE")
	private WebElement txtIE;
	
	@FindBy(css = "#txtIM")
	private WebElement txtIM;
	
	// ATIVO
	
	@FindBy(css = "#rdAtivo")
	private WebElement rdAtivo;
	
	@FindBy(css = "#rdVariosEnd")
	private WebElement rdVariosEnd;
	
	@FindBy(css = "#rdImprimeDataSaidaDanfe")
	private WebElement rdImprimeDataSaidaDanfe;
	
	@FindBy(css = "#rdVendaManual")
	private WebElement rdVendaManual;
	
	@FindBy(css = "#rdImprimeRecibo")
	private WebElement rdImprimeRecibo;
	
	@FindBy(css = "#rdLojaRE")
	private WebElement rdLojaRE;
	
	@FindBy(css = "#rdPermiteImp")
	private WebElement rdPermiteImp;
	
	// DADOS ADICIONAIS
	
	@FindBy(css = "#codigoEnterprise")
	private WebElement codigoEnterprise;
	
	@FindBy(css = "#identificadorTEF")
	private WebElement identificadorTEF;
	
	@FindBy(css = "#comboProcessoTribu")
	private WebElement comboProcessoTribu;
	
	@FindBy(css = "#qtdDiaValidadeCarga")
	private WebElement qtdDiaValidadeCarga;
	
	@FindBy(css = "#codMunicipioIBGE")
	private WebElement codMunicipioIBGE;
	
	@FindBy(css = "#shipCustomerRef")
	private WebElement shipCustomerRef;
	
	@FindBy(css = "#billCustomerRef")
	private WebElement billCustomerRef;
	
	@FindBy(css = "#codigoLojaMastersaf")
	private WebElement codigoLojaMastersaf;
	
	@FindBy(css = "#codigoLojaOrms")
	private WebElement codigoLojaOrms;
	
	@FindBy(css = "#comboSistemaRetaguarda")
	private WebElement comboSistemaRetaguarda;
	
	@FindBy(css = "#codigoGetNet")
	private WebElement codigoGetNet;
	
	@FindBy(css = "#comboNfe")
	private WebElement comboNfe;
	
	@FindBy(css = "#comboRecebeEmailNFE")
	private WebElement comboRecebeEmailNFE;
	
	@FindBy(css = "#comboTipoFechamentoCaixa")
	private WebElement comboTipoFechamentoCaixa;
	
	@FindBy(css = "#shipAddressRef")
	private WebElement shipAddressRef;
	
	@FindBy(css = "#billAddressRef")
	private WebElement billAddressRef;
	
	public Loja(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Loja setCodigo(String codigo) {
		this.txtCodigo.clear();
		this.txtCodigo.sendKeys(codigo);
		return this;
	}
	
	public Loja setCodigoLoja(String codigoLoja) {
		this.txtCodigoLoja.clear();
		this.txtCodigoLoja.sendKeys(codigoLoja);
		return this;
	}
	
	public Loja setGrupoRegional(String grupoRegional) {
		new Select(this.comboGrupoRegional).selectByVisibleText(grupoRegional);
		return this;
	}
	
	public Loja setDescricao(String descricao) {
		this.txtDescricao.clear();
		this.txtDescricao.sendKeys(descricao);
		return this;
	}
	
	public Loja setClassificacaoTipoLoja(String classificacaoTipoLoja) {
		new Select(this.comboClassificacaoTipoLoja).selectByVisibleText(classificacaoTipoLoja);
		return this;
	}
	
	public Loja setTipoConfirmacao(String tipoConfirmacao) {
		new Select(this.comboTipoConfirmacao).selectByVisibleText(tipoConfirmacao);
		return this;
	}
	
	public Loja setAvisoSenha(String avisoSenha) {
		this.txtAvisoSenha.clear();
		this.txtAvisoSenha.sendKeys(avisoSenha);
		return this;
	}
	
	public Loja setIpServidor(String ipServidor) {
		this.txtIpServidor.clear();
		this.txtIpServidor.sendKeys(ipServidor);
		return this;
	}
	
	public Loja setCentroCusto(String centroCusto) {
		new Select(this.comboCentroCusto).selectByVisibleText(centroCusto);
		return this;
	}
	
	public Loja setTransferencia(String transferencia) {
		new Select(this.comboTransferencia).selectByVisibleText(transferencia);
		return this;
	}
	
	public Loja setPraca(String praca) {
		this.txtPraca.clear();
		this.txtPraca.sendKeys(praca);
		return this;
	}
	
	public Loja setBaseCalculoMVA(String baseCalculo) {
		new Select(this.comboBaseCalculoMVA).selectByVisibleText(baseCalculo);
		return this;
	}
	
	public Loja setClassificacao(String classificacao) {
		new Select(this.comboClassificacao).selectByVisibleText(classificacao);
		return this;
	}
	
	public Loja setTipoLoja(String tipoLoja) {
		new Select(this.comboTipoLoja).selectByVisibleText(tipoLoja);
		return this;
	}
	
	public Loja setEmail(String email) {
		this.txtEmail.clear();
		this.txtEmail.sendKeys(email);
		return this;
	}
	
	public Loja setValidadeSenha(String validadeSenha) {
		this.txtValidadeSenha.clear();
		this.txtValidadeSenha.sendKeys(validadeSenha);
		return this;
	}
	
	public Loja setQtdNotas(String qtdNotas) {
		this.txtQtdNotas.clear();
		this.txtQtdNotas.sendKeys(qtdNotas);
		return this;
	}
	
	public Loja setRenovacaoNF(String renovacaoNF) {
		this.txtRenovacaoNF.clear();
		this.txtRenovacaoNF.sendKeys(renovacaoNF);
		return this;
	}
	
	public Loja setServidor(String servidor) {
		this.txtServidor.clear();
		this.txtServidor.sendKeys(servidor);
		return this;
	}
	
	public Loja setBandeira(String bandeira) {
		new Select(this.comboBandeira).selectByVisibleText(bandeira);
		return this;
	}
	
	public Loja setCodigoSerieNFLoja(String codigoSerieNFLoja) {
		this.txtCodigoSerieNfLoja.clear();
		this.txtCodigoSerieNfLoja.sendKeys(codigoSerieNFLoja);
		return this;
	}
	
	public Loja setLojaEmpenho(String lojaEmpenho) {
		new Select(this.comboLojaEmpenho).selectByVisibleText(lojaEmpenho);
		return this;
	}
	
	public WebElement getBtnLojaAbastecimento() {
		return this.pesquisarLojaAbastecimento;
	}
	
	public void tabCadastroLoja(String nomeTab) {
		driver.findElement(By.xpath(".//table[@class='tabbedPane']/tbody/tr/td/table/tbody/tr/td/span[contains(text(), '" + nomeTab + "')]")).click();
	}
	
	public WebElement getBtnPesquisarCidade() {
		return this.pesquisarCidade;
	}
	
	public WebElement getBtnPesquisarLogradouro() {
		return this.pesquisarLogradouro;
	}
	
	public Loja setComplemento(String complemento) {
		this.complemento.clear();
		this.complemento.sendKeys(complemento);
		return this;
	}
	
	public Loja setDDD(String ddd) {
		this.ddd.clear();
		this.ddd.sendKeys(ddd);
		return this;
	}
	
	public Loja setNumeroFone(String numeroFone) {
		this.numeroFone.clear();
		this.numeroFone.sendKeys(numeroFone);
		return this;
	}
	
	public Loja setNumero(String numero) {
		this.numero.clear();
		this.numero.sendKeys(numero);
		return this;
	}
	
	public Loja setGrupoPreco(String grupoPreco) {
		new Select(this.comboGrupoPreco).selectByVisibleText(grupoPreco);
		return this;
	}
	
	public Loja setGrupoVenda(String grupoVenda) {
		new Select(this.comboGrupoVenda).selectByVisibleText(grupoVenda);
		return this;
	}
	
	public Loja setGrupoCredito(String grupoCredito) {
		new Select(this.comboGrupoCredito).selectByVisibleText(grupoCredito);
		return this;
	}
	
	public Loja setGrupoParametro(String grupoParametro) {
		new Select(this.comboGrupoParametro).selectByVisibleText(grupoParametro);
		return this;
	}
	
	public Loja setGrupoPromocao(String grupoPromocao) {
		new Select(this.comboGrupoPromocao).selectByVisibleText(grupoPromocao);
		return this;
	}
	
	public Loja setGrupoMensagem(String grupoMensagem) {
		new Select(this.comboGrupoMensagem).selectByVisibleText(grupoMensagem);
		return this;
	}
	
	public Loja setCnpj(String cnpj) {
		this.txtCNPJ.clear();
		this.txtCNPJ.sendKeys(cnpj);
		return this;
	}
	
	public Loja setRazaoSocial(String razaoSocial) {
		this.txtRS.clear();
		this.txtRS.sendKeys(razaoSocial);
		return this;
	}
	
	public Loja setFantasia(String fantasia) {
		this.txtFantasia.clear();
		this.txtFantasia.sendKeys(fantasia);
		return this;
	}
	
	public Loja setInscricaoEstadual(String inscricaoEstadual) {
		this.txtIE.clear();
		this.txtIE.sendKeys(inscricaoEstadual);
		return this;
	}
	
	public Loja setInscricaoMunicipal(String inscricaoMunicipal) {
		this.txtIM.clear();
		this.txtIM.sendKeys(inscricaoMunicipal);
		return this;
	}
	
	public Loja setAtivo() {
		this.rdAtivo.click();
		return this;
	}
	
	public Loja setVariosEnd() {
		this.rdVariosEnd.click();
		return this;
	}
	
	public Loja setImprimeDataSaidaDanfe() {
		this.rdImprimeDataSaidaDanfe.click();
		return this;
	}
	
	public Loja setVendaManual() {
		this.rdVendaManual.click();
		return this;
	}
	
	public Loja setImprimeRecibo() {
		this.rdImprimeRecibo.click();
		return this;
	}
	
	public Loja setLojaRE() {
		this.rdLojaRE.click();
		return this;
	}
	
	public Loja setPermiteImpressaoContrato() {
		this.rdPermiteImp.click();
		return this;
	}
	
	public Loja setCodigoEnterprise(String codigoEnterprise) {
		this.codigoEnterprise.clear();
		this.codigoEnterprise.sendKeys(codigoEnterprise);
		return this;
	}
	
	public Loja setIdentificadorTEF(String identificadorTEF) {
		this.identificadorTEF.clear();
		this.identificadorTEF.sendKeys(identificadorTEF);
		return this;
	}
	
	public Loja setProcessoTributario(String processoTribu) {
		new Select(this.comboProcessoTribu).selectByVisibleText(processoTribu);
		return this;
	}
	
	public Loja setQtdDiasValidadeCarga(String qtdDiaValidadeCarga) {
		this.qtdDiaValidadeCarga.clear();
		this.qtdDiaValidadeCarga.sendKeys(qtdDiaValidadeCarga);
		return this;
	}
	
	public Loja setCodMunicipioIBGE(String codMunicipioIBGE) {
		this.codMunicipioIBGE.clear();
		this.codMunicipioIBGE.sendKeys(codMunicipioIBGE);
		return this;
	}
	
	public Loja setShipCustomerRef(String shipCustomerRef) {
		this.shipCustomerRef.clear();
		this.shipCustomerRef.sendKeys(shipCustomerRef);
		return this;
	}
	
	public Loja setBillCustomerRef(String billCustomerRef) {
		this.billCustomerRef.clear();
		this.billCustomerRef.sendKeys(billCustomerRef);
		return this;
	}
	
	public Loja setCodigoLojaMastersaf(String codigoLojaMastersaf) {
		this.codigoLojaMastersaf.clear();
		this.codigoLojaMastersaf.sendKeys(codigoLojaMastersaf);
		return this;
	}
	
	public Loja setCodigoLojaOrms(String codigoLojaOrms) {
		this.codigoLojaOrms.clear();
		this.codigoLojaOrms.sendKeys(codigoLojaOrms);
		return this;
	}
	
	public Loja setSistemaRetaguarda(String sistemaRetaguarda) {
		new Select(this.comboSistemaRetaguarda).selectByVisibleText(sistemaRetaguarda);
		return this;
	}
	
	public Loja setCodigoGetNet(String codigoGetNet) {
		this.codigoGetNet.clear();
		this.codigoGetNet.sendKeys(codigoGetNet);
		return this;
	}
	
	public Loja setEmiteNfe(String nfe) {
		new Select(this.comboNfe).selectByVisibleText(nfe);
		return this;
	}
	
	public Loja setRecebeEmailNFE(String recebeEmailNFE) {
		new Select(this.comboRecebeEmailNFE).selectByVisibleText(recebeEmailNFE);
		return this;
	}
	
	public Loja setTipoFechamentoCaixa(String tipoFechamentoCaixa) {
		new Select(this.comboTipoFechamentoCaixa).selectByVisibleText(tipoFechamentoCaixa);
		return this;
	}
	
	public Loja setShipAddressRef(String shipAddressRef) {
		this.shipAddressRef.clear();
		this.shipAddressRef.sendKeys(shipAddressRef);
		return this;
	}
	
	public Loja setBillAddressRef(String billAddressRef) {
		this.billAddressRef.clear();
		this.billAddressRef.sendKeys(billAddressRef);
		return this;
	}
}