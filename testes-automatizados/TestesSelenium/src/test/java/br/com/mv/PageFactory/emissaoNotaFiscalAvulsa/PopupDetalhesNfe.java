package br.com.mv.PageFactory.emissaoNotaFiscalAvulsa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupDetalhesNfe {
	
	WebDriver driver;
	
	@FindBy(id = "txtChaveAcesso")
	private WebElement chaveAcesso;
	
	@FindBy(id = "verAppNfe")
	private WebElement versaoAplicacaoNFE;
	
	@FindBy(id = "nroUsuarioGeracao")
	private WebElement usuarioGeracao;
	
	@FindBy(id = "ufGeracao")
	private WebElement ufGeracao;
	
	@FindBy(id = "tempoMedio")
	private WebElement tempoMedio;
	
	@FindBy(id = "indDpceEnviado")
	private WebElement dpceEnviado;
	
	@FindBy(id = "indDanfeImpresso")
	private WebElement danfeImpresso;
	
	@FindBy(id = "idLote")
	private WebElement idLote;
	
	@FindBy(id = "statusRe")
	private WebElement statusRE;
	
	@FindBy(id = "statusNfe")
	private WebElement statusNfe;
	
	@FindBy(id = "dtEnvio")
	private WebElement dataEnvio;
	
	@FindBy(id = "dtConsulta")
	private WebElement dataConsulta;
	
	@FindBy(id = "txtMotivo")
	private WebElement motivo;
	
	@FindBy(css = "input[type='button'].buttonSearchField:eq(0)")
	private WebElement btnSearchMotivo;
	
	@FindBy(id = "sequencia")
	private WebElement nroSequenciaNf;
	
	@FindBy(id = "nroReciboNfe")
	private WebElement nroReciboNfe;
	
	@FindBy(id = "loja")
	private WebElement loja;
	
	@FindBy(id = "nroProtocoloNfe")
	private WebElement protocoloNfe;
	
	@FindBy(id = "cargaVeiculo")
	private WebElement cargaVeiculo;
	
	@FindBy(id = "modoOperacao")
	private WebElement modoOperacao;
	
	@FindBy(id = "tipoContingencia")
	private WebElement tipoContingencia;
	
	@FindBy(id = "dtRecebimento")
	private WebElement dataRecebimento;
	
	@FindBy(id = "dtAutorizacao")
	private WebElement dataAutorizacao;
	
	@FindBy(id = "dtEnvioDpce")
	private WebElement dataEnvioDpce;
	
	@FindBy(id = "txtMotivoDpce")
	private WebElement motivoDpce;
	
	@FindBy(css = "input[type='button'].buttonSearchField:eq(1)")
	private WebElement btnSearchMotivoDpce;
	
//	@FindBy(id = )
	
	public PopupDetalhesNfe(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getChaveAcesso() {
		return chaveAcesso.getAttribute("value");
	}

	public PopupDetalhesNfe setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso.clear();
		this.chaveAcesso.sendKeys(chaveAcesso);
		return this;
	}

	public String getVersaoAplicacaoNFE() {
		return versaoAplicacaoNFE.getAttribute("value");
	}

	public PopupDetalhesNfe setVersaoAplicacaoNFE(String versaoAplicacaoNFE) {
		this.versaoAplicacaoNFE.clear();
		this.versaoAplicacaoNFE.sendKeys(versaoAplicacaoNFE);
		return this;
	}

	public String getUsuarioGeracao() {
		return usuarioGeracao.getAttribute("value");
	}

	public PopupDetalhesNfe setUsuarioGeracao(String usuarioGeracao) {
		this.usuarioGeracao.clear();
		this.usuarioGeracao.sendKeys(usuarioGeracao);
		return this;
	}

	public String getUfGeracao() {
		return ufGeracao.getAttribute("value");
	}

	public PopupDetalhesNfe setUfGeracao(String ufGeracao) {
		this.ufGeracao.clear();
		this.ufGeracao.sendKeys(ufGeracao);
		return this;
	}

	public String getTempoMedio() {
		return tempoMedio.getAttribute("value");
	}

	public PopupDetalhesNfe setTempoMedio(String tempoMedio) {
		this.tempoMedio.clear();
		this.tempoMedio.sendKeys(tempoMedio);
		return this;
	}

	public String getDpceEnviado() {
		return dpceEnviado.getAttribute("value");
	}

	public PopupDetalhesNfe setDpceEnviado(String dpceEnviado) {
		this.dpceEnviado.clear();
		this.dpceEnviado.sendKeys(dpceEnviado);
		return this;
	}

	public String getDanfeImpresso() {
		return danfeImpresso.getAttribute("value");
	}

	public PopupDetalhesNfe setDanfeImpresso(String danfeImpresso) {
		this.danfeImpresso.clear();
		this.danfeImpresso.sendKeys(danfeImpresso);
		return this;
	}

	public String getIdLote() {
		return idLote.getAttribute("value");
	}

	public PopupDetalhesNfe setIdLote(String idLote) {
		this.idLote.clear();
		this.idLote.sendKeys(idLote);
		return this;
	}

	public String getStatusRE() {
		return statusRE.getAttribute("value");
	}

	public PopupDetalhesNfe setStatusRE(String statusRe) {
		this.statusRE.clear();
		this.statusRE.sendKeys(statusRe);
		return this;
	}

	public String getStatusNfe() {
		return statusNfe.getAttribute("value");
	}

	public PopupDetalhesNfe setStatusNfe(String statusNfe) {
		this.statusNfe.clear();
		this.statusNfe.sendKeys(statusNfe);
		return this;
	}

	public String getDataEnvio() {
		return dataEnvio.getAttribute("value");
	}

	public PopupDetalhesNfe setDataEnvio(String dataEnvio) {
		this.dataEnvio.clear();
		this.dataEnvio.sendKeys(dataEnvio);
		return this;
	}

	public String getDataConsulta() {
		return dataConsulta.getAttribute("value");
	}

	public PopupDetalhesNfe setDataConsulta(String dataConsulta) {
		this.dataConsulta.clear();
		this.dataConsulta.sendKeys(dataConsulta);
		return this;
	}

	public String getMotivo() {
		return motivo.getAttribute("value");
	}

	public PopupDetalhesNfe setMotivo(String motivo) {
		this.motivo.clear();
		this.motivo.sendKeys(motivo);
		return this;
	}

	public String getNroSequenciaNf() {
		return nroSequenciaNf.getAttribute("value");
	}

	public PopupDetalhesNfe setNroSequenciaNf(String nroSequenciaNf) {
		this.nroSequenciaNf.clear();
		this.nroSequenciaNf.sendKeys(nroSequenciaNf);
		return this;
	}

	public String getNroReciboNfe() {
		return nroReciboNfe.getAttribute("value");
	}

	public PopupDetalhesNfe setNroReciboNfe(String nroReciboNfe) {
		this.nroReciboNfe.clear();
		this.nroReciboNfe.sendKeys(nroReciboNfe);
		return this;
	}

	public String getLoja() {
		return loja.getAttribute("value");
	}

	public PopupDetalhesNfe setLoja(String loja) {
		this.loja.clear();
		this.loja.sendKeys(loja);
		return this;
	}

	public String getProtocoloNfe() {
		return protocoloNfe.getAttribute("value");
	}

	public PopupDetalhesNfe setProtocoloNfe(String protocoloNfe) {
		this.protocoloNfe.clear();
		this.protocoloNfe.sendKeys(protocoloNfe);
		return this;
	}

	public String getCargaVeiculo() {
		return cargaVeiculo.getAttribute("value");
	}

	public PopupDetalhesNfe setCargaVeiculo(String cargaVeiculo) {
		this.cargaVeiculo.clear();
		this.cargaVeiculo.sendKeys(cargaVeiculo);
		return this;
	}

	public String getModoOperacao() {
		return modoOperacao.getAttribute("value");
	}

	public PopupDetalhesNfe setModoOperacao(String modoOperacao) {
		this.modoOperacao.clear();
		this.modoOperacao.sendKeys(modoOperacao);
		return this;
	}

	public String getTipoContingencia() {
		return tipoContingencia.getAttribute("value");
	}

	public PopupDetalhesNfe setTipoContingencia(String tipoContingencia) {
		this.tipoContingencia.clear();
		this.tipoContingencia.sendKeys(tipoContingencia);
		return this;
	}

	public String getDataRecebimento() {
		return dataRecebimento.getAttribute("value");
	}

	public PopupDetalhesNfe setDataRecebimento(String dataRecebimento) {
		this.dataRecebimento.clear();
		this.dataRecebimento.sendKeys(dataRecebimento);
		return this;
	}

	public String getDataAutorizacao() {
		return dataAutorizacao.getAttribute("value");
	}

	public PopupDetalhesNfe setDataAutorizacao(String dataAutorizacao) {
		this.dataAutorizacao.clear();
		this.dataAutorizacao.sendKeys(dataAutorizacao);
		return this;
	}

	public String getDataEnvioDpce() {
		return dataEnvioDpce.getAttribute("value");
	}

	public PopupDetalhesNfe setDataEnvioDpce(String dataEnvioDpce) {
		this.dataEnvioDpce.clear();
		this.dataEnvioDpce.sendKeys(dataEnvioDpce);
		return this;
	}

	public String getMotivoDpce() {
		return motivoDpce.getAttribute("value");
	}

	public PopupDetalhesNfe setMotivoDpce(String motivoDpce) {
		this.motivoDpce.clear();
		this.motivoDpce.sendKeys(motivoDpce);
		return this;
	}
	
	public WebElement getBtnSearchMotivo() {
		return btnSearchMotivo;
	}
	
	public PopupDetalhesNfe clickBtnSearchMotivo() {
		this.btnSearchMotivo.click();
		return this;
	}
	
	public WebElement getBtnSearchMotivoDpce() {
		return btnSearchMotivoDpce;
	}
	
	public PopupDetalhesNfe clickBtnSearchMotivoDpce() {
		this.btnSearchMotivoDpce.click();
		return this;
	}
	
	

}
