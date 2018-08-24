package br.com.mv.PageFactory.emissaoNotaFiscalAvulsa;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.mv.PageFactory.Mensagens;
import br.com.mv.dao.notafiscal.NotaFiscal;
import br.com.mv.dao.notafiscal.NotaFiscalDAO;
import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.ControleJanela;

public class EmissaoNotaFiscalAvulsa {
	
	WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id = "cbxOperacaoNotaFiscal")
	private WebElement operacaoNF;
	
	@FindBy(id = "txtCodigoTipoNotaFiscal")
	private WebElement codTipoNF;
	
	@FindBy(id = "txtDescricaoTipoNotaFiscal")
	private WebElement descTipoNF;
	
	@FindBy(id = "btnBuscarTiposNotaFiscal")
	private WebElement btnBuscarTiposNF;
	
	@FindBy(id = "txtNaturezaOperacao")
	private WebElement naturezaOperacao;
	
	@FindBy(id = "cbxGrupoEstoqueOrigem")
	private WebElement grupoEstoqueOrigem;
	
	@FindBy(id = "chxAverbaSeguro")
	private WebElement chkAverbarNoSeguro;
	
	@FindBy(id = "btnIformarParametros")
	private WebElement btnInformarParametros;
	
	//botões de nagegação entre as abas de nota fiscal
	@FindBy(xpath = "//span[contains(text(), \"Cabeçalho\")]")
	private WebElement abaCabecalho;
	
	@FindBy(xpath = "//span[contains(text(), \"Itens\")]")
	private WebElement abaItens;
	
	@FindBy(xpath = "//span[contains(text(), \"Transportador\")]")
	private WebElement abaTransportador;
	
	@FindBy(xpath = "//span[contains(text(), \"Outros\")]")
	private WebElement 	abaOutros;
	
	@FindBy(xpath = "//span[contains(text(), \"Adicionais\")]")
	private WebElement abaDados;
	//.
	
	//Elementos Aba Cabeçalho
	@FindBy(id = "txtDataEmissao")
	private WebElement dataEmissao;
	
	@FindBy(id = "txtDescricaoUsuario")
	private WebElement descUsuario;
	
	@FindBy(id = "txtCodigoUsuario")
	private WebElement codUsuario;
	
	@FindBy(id = "txtNumeroNota")
	private WebElement nroNota;
	
	@FindBy(id = "txtNumeroCupom")
	private WebElement nroCupom;
	
	@FindBy(id = "txtNFReferencia")
	private WebElement nfReferencia;
	
	@FindBy(id = "txtNatureza")
	private WebElement natrueza;
	
	@FindBy(id = "txtCFOP")
	private WebElement cfop;
	
	@FindBy(id = "txtDataSaida")
	private WebElement dataSaida;
	
	@FindBy(id = "txtNomeRazaoSocial")
	private WebElement razaoSocial;
	
	@FindBy(id = "txtCnpjCpf")
	private WebElement cnpjCpf;
	
	@FindBy(id = "txtIE")
	private WebElement inscricaoEstadual;
	
	@FindBy(id = "txtDescricaoTipoLogradouro")
	private WebElement descTipoLogradouro;
	
	@FindBy(id = "txtDescricaoLogradouro")
	private WebElement descLogradouro;
	
	@FindBy(id = "txtNumeroEndereco")
	private WebElement nroEndereco;
	
	@FindBy(id = "txtComplementoEndereco")
	private WebElement complementoEndereco;
	
	@FindBy(id = "txtNomeBairro")
	private WebElement bairro;
	
	@FindBy(id = "txtNomeLocalidade")
	private WebElement localidade;
	
	@FindBy(id = "txtUf")
	private WebElement estado;
	
	@FindBy(id = "txtCep")
	private WebElement cep;
	//.
	
	//Elementos Aba Itens
	@FindBy(id = "btnIncluirProduto")
	private WebElement btnIncluirProduto;
	
	@FindBy(id = "btnRemoverProduto")
	private WebElement btnRemoverProduto;
	
	@FindBy(id = "txtNumeroSerie")
	private WebElement nroSerie;
	
	@FindBy(id = "txtObservacaoItemNf")
	private WebElement obsItemNF;
	
	@FindBy(id = "txtBaseCalculoIcms")
	private WebElement baseCalculoICMS;
	
	@FindBy(id = "txtValorIcms")
	private WebElement valorICMS;
	
	@FindBy(id = "txtValorTotalIPI")
	private WebElement valorTotalIPI;
	
	@FindBy(id = "txtBaseCalculoIcmsSubst")
	private WebElement baseCalculoICMSSubst;
	
	@FindBy(id = "txtValorIcmsSubst")
	private WebElement valorICMSSubst;
	
	@FindBy(id = "txtValorTotalProdutos")
	private WebElement valorTotalProdutos;
	
	@FindBy(id = "txtValorFrete")
	private WebElement valorFrete;
	
	@FindBy(id = "txtValorSeguro")
	private WebElement valorSeguro;
	
	@FindBy(id = "txtValorOutrasDespesas")
	private WebElement valorOutrasDespesas;
	
	@FindBy(id = "txtValorTotalNota")
	private WebElement valorTotalNota;
	//.
	
	//Elementos Aba Transportador
	@FindBy(css = "#scTransportadoraNf input[type='text']")
	private WebElement transportadora;
	
	@FindBy(css = "#scTransportadoraNf input:nth(1)")
	private WebElement btnSearchTransportadora;
	
	@FindBy(css = "#scTransportadoraNf input:nth(2)")
	private WebElement btnLimparTransportadora;
	
	@FindBy(id = "txtPlacaVeiculo")
	private WebElement placaVeiculo;
	
	@FindBy(id = "cbxVeiculoUf")
	private WebElement veiculoEstado;
	
	@FindBy(id = "cbxTipoFrete")
	private WebElement tipoFrete;
	//.
	
	//Elementos Aba Outros
	@FindBy(id = "txtPesoBruto")
	private WebElement pesoBruto;
	
	@FindBy(id = "txtPesoLiquido")
	private WebElement pesoLiquido;
	
	@FindBy(id = "txtDesEspecieProduto")
	private WebElement descEspecieProduto;
	
	@FindBy(id = "txtQtdProdEspecie")
	private WebElement qtdProdutoEspecie;
	//.
	
	//Elementos Aba Dados Adicionais
	@FindBy(id = "txtObsSistema")
	private WebElement obsSistema;
	
	@FindBy(id = "txtObsUsuario")
	private WebElement obsUsuario;
	
	@FindBy(id = "txtNomeCliente")
	private WebElement nomeCliente;
	
	@FindBy(id = "txtNumeroCliente")
	private WebElement nroCliente;
	
	@FindBy(id = "txtNomeVendedor")
	private WebElement nomeVendedor;
	
	@FindBy(id = "txtNumeroVendedor")
	private WebElement nroVendedor;
	
	@FindBy(id = "txtNumeroPedido")
	private WebElement nroPedido;
	
	@FindBy(id = "txtLojaPedido")
	private WebElement lojaPedido;
	
	@FindBy(id= "txtLojaFaturamento")
	private WebElement lojaFaturamento;
	
	@FindBy(id = "txtNumeroPDV")
	private WebElement nroPdv;
	//.
	
	@FindBy(id = "btnAlterarNroNF")
	private WebElement btnAlterarNroNF;
	
	@FindBy(id = "btnEmitirEspelho")
	private WebElement btnEmitirEspelho;
	
	@FindBy(id = "btnConcluir")
	private WebElement btnConcluir;
	
	@FindBy(id = "btnCancelar")
	private WebElement btnCancelar;
	
	private ControleJanela controleJanela;
	private AguardaCarregamento agCarregamento;
	private Mensagens msg;
	
	public EmissaoNotaFiscalAvulsa(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.controleJanela = new ControleJanela(driver);
		this.agCarregamento = new AguardaCarregamento(driver);
		this.msg = new Mensagens(this.driver);
		this.wait = new WebDriverWait(driver, 20);
	}
	

	public String getOperacaoNFValor() {
		return operacaoNF.getAttribute("value");
	}
	
	public WebElement getOperacaoNF() {
		return operacaoNF;
	}

	public EmissaoNotaFiscalAvulsa setOperacaoNF(String operacaoNF) {
		new Select(this.operacaoNF).selectByVisibleText(operacaoNF);
		return this;
	}

	public String getCodTipoNF() {
		return codTipoNF.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setCodTipoNF(String codTipoNF) {
		this.codTipoNF.clear();
		this.codTipoNF.sendKeys(codTipoNF);
		return this;
	}

	public String getDescTipoNF() {
		return descTipoNF.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setDescTipoNF(String descTipoNF) {
		this.descTipoNF.clear();
		this.descTipoNF.sendKeys(descTipoNF);
		return this;
	}

	public WebElement getBtnBuscarTiposNF() {
		return btnBuscarTiposNF;
	}

	public EmissaoNotaFiscalAvulsa clickBtnBuscarTiposNF() {
		this.btnBuscarTiposNF.click();
		return this;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao.clear();
		this.naturezaOperacao.sendKeys(naturezaOperacao);
		return this;
	}

	public String getGrupoEstoqueOrigem() {
		return grupoEstoqueOrigem.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setGrupoEstoqueOrigem(String grupoEstoqueOrigem) {
		new Select(this.grupoEstoqueOrigem).selectByVisibleText(grupoEstoqueOrigem);
		return this;
	}

	public String getChkAverbarNoSeguro() {
		return chkAverbarNoSeguro.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa clickChkAverbarNoSeguro() {
		this.chkAverbarNoSeguro.click();
		return this;
	}

	public WebElement getBtnInformarParametros() {
		return btnInformarParametros;
	}

	public EmissaoNotaFiscalAvulsa clickBtnInformarParametros() {
		this.btnInformarParametros.click();
		return this;
	}

	public WebElement getAbaCabecalho() {
		return abaCabecalho;
	}

	public EmissaoNotaFiscalAvulsa clickAbaCabecalho() {
		this.abaCabecalho.click();
		return this;
	}

	public WebElement getAbaItens() {
		return abaItens;
	}

	public EmissaoNotaFiscalAvulsa clickAbaItens() {
		this.abaItens.click();
		return this;
	}

	public WebElement getAbaTransportador() {
		return abaTransportador;
	}

	public EmissaoNotaFiscalAvulsa clickAbaTransportador() {
		this.abaTransportador.click();
		return this;
	}

	public WebElement getAbaOutros() {
		return abaOutros;
	}

	public EmissaoNotaFiscalAvulsa clickAbaOutros() {
		this.abaOutros.click();
		return this;
	}

	public WebElement getAbaDados() {
		return abaDados;
	}

	public EmissaoNotaFiscalAvulsa clickAbaDados() {
		this.abaDados.click();
		return this;
	}

	public String getDataEmissao() {
		return dataEmissao.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setDataEmissao(String dataEmissao) {
		this.dataEmissao.clear();
		this.dataEmissao.sendKeys(dataEmissao);
		return this;
	}

	public String getDescUsuario() {
		return descUsuario.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setDescUsuario(String descUsuario) {
		this.descUsuario.clear();
		this.descUsuario.sendKeys(descUsuario);
		return this;
	}

	public String getCodUsuario() {
		return codUsuario.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setCodUsuario(String codUsuario) {
		this.codUsuario.clear();
		this.codUsuario.sendKeys(codUsuario);
		return this;
	}

	public String getNroNota() {
		return nroNota.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroNota(String nroNota) {
		this.nroNota.clear();
		this.nroNota.sendKeys(nroNota);
		return this;
	}

	public String getNroCupom() {
		return nroCupom.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroCupom(String nroCupom) {
		this.nroCupom.clear();
		this.nroCupom.sendKeys(nroCupom);
		return this;
	}

	public String getNfReferencia() {
		return nfReferencia.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNfReferencia(String nfReferencia) {
		this.nfReferencia.clear();
		this.nfReferencia.sendKeys(nfReferencia);
		return this;
	}

	public String getNatrueza() {
		return natrueza.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNatrueza(String natureza) {
		this.natrueza.clear();
		this.natrueza.sendKeys(natureza);
		return this;
	}

	public String getCfop() {
		return cfop.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setCfop(String cfop) {
		this.cfop.clear();
		this.cfop.sendKeys(cfop);
		return this;
	}

	public String getDataSaida() {
		return dataSaida.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setDataSaida(String dataSaida) {
		this.dataSaida.clear();
		this.dataSaida.sendKeys(dataSaida);
		return this;
	}

	public String getRazaoSocial() {
		return razaoSocial.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setRazaoSocial(String razaoSocial) {
		this.razaoSocial.clear();
		this.razaoSocial.sendKeys(razaoSocial);
		return this;
	}

	public String getCnpjCpf() {
		return cnpjCpf.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf.clear();
		this.cnpjCpf.sendKeys(cnpjCpf);
		return this;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual.clear();
		this.inscricaoEstadual.sendKeys(inscricaoEstadual);
		return this;
	}

	public String getDescTipoLogradouro() {
		return descTipoLogradouro.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setDescTipoLogradouro(String descTipoLogradouro) {
		this.descTipoLogradouro.clear();
		this.descTipoLogradouro.sendKeys(descTipoLogradouro);
		return this;
	}

	public String getDescLogradouro() {
		return descLogradouro.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setDescLogradouro(String descLogradouro) {
		this.descLogradouro.clear();
		this.descLogradouro.sendKeys(descLogradouro);
		return this;
	}

	public String getNroEndereco() {
		return nroEndereco.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroEndereco(String nroEndereco) {
		this.nroEndereco.clear();
		this.nroEndereco.sendKeys(nroEndereco);
		return this;
	}

	public String getComplementoEndereco() {
		return complementoEndereco.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco.clear();
		this.complementoEndereco.sendKeys(complementoEndereco);
		return this;
	}

	public String getBairro() {
		return bairro.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setBairro(String bairro) {
		this.bairro.clear();
		this.bairro.sendKeys(bairro);
		return this;
	}

	public String getLocalidade() {
		return localidade.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setLocalidade(String localidade) {
		this.localidade.clear();
		this.localidade.sendKeys(localidade);
		return this;
	}

	public String getEstado() {
		return estado.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setEstado(String estado) {
		this.estado.clear();
		this.estado.sendKeys(estado);
		return this;
	}

	public String getCep() {
		return cep.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setCep(String cep) {
		this.cep.clear();
		this.cep.sendKeys(cep);
		return this;
	}

	public WebElement getBtnIncluirProduto() {
		return btnIncluirProduto;
	}

	public EmissaoNotaFiscalAvulsa clickBtnIncluirProduto() {
		this.btnIncluirProduto.click();
		return this;
	}

	public WebElement getBtnRemoverProduto() {
		return btnRemoverProduto;
	}

	public EmissaoNotaFiscalAvulsa clickBtnRemoverProduto() {
		this.btnRemoverProduto.click();
		return this;
	}

	public String getNroSerie() {
		return nroSerie.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroSerie(String nroSerie) {
		this.nroSerie.clear();
		this.nroSerie.sendKeys(nroSerie);
		return this;
	}

	public String getObsIntemNF() {
		return obsItemNF.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setObsItemNF(String obsItemNF) {
		this.obsItemNF.clear();
		this.obsItemNF.sendKeys(obsItemNF);
		return this;
	}

	public String getBaseCalculoICMS() {
		return baseCalculoICMS.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setBaseCalculoICMS(String baseCalculoICMS) {
		this.baseCalculoICMS.clear();
		this.baseCalculoICMS.sendKeys(baseCalculoICMS);
		return this;
	}

	public String getValorICMS() {
		return valorICMS.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorICMS(String valorICMS) {
		this.valorICMS.clear();
		this.valorICMS.sendKeys(valorICMS);
		return this;
	}

	public String getValorTotalIPI() {
		return valorTotalIPI.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorTotalIPI(String valorTotalIPI) {
		this.valorTotalIPI.clear();
		this.valorTotalIPI.sendKeys(valorTotalIPI);
		return this;
	}

	public String getBaseCalculoICMSSubst() {
		return baseCalculoICMSSubst.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setBaseCalculoICMSSubst(String baseCalculoICMSSubst) {
		this.baseCalculoICMSSubst.clear();
		this.baseCalculoICMSSubst.sendKeys(baseCalculoICMSSubst);
		return this;
	}

	public String getValorICMSSubst() {
		return valorICMSSubst.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorICMSSubst(String valorICMSSubst) {
		this.valorICMSSubst.clear();
		this.valorICMSSubst.sendKeys(valorICMSSubst);
		return this;
	}

	public String getValorTotalProdutos() {
		return valorTotalProdutos.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorTotalProdutos(String valorTotalProdutos) {
		this.valorTotalProdutos.clear();
		this.valorTotalProdutos.sendKeys(valorTotalProdutos);
		return this;
	}

	public String getValorFrete() {
		return valorFrete.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorFrete(String valorFrete) {
		this.valorFrete.clear();
		this.valorFrete.sendKeys(valorFrete);
		return this;
	}

	public String getValorSeguro() {
		return valorSeguro.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorSeguro(String valorSeguro) {
		this.valorSeguro.clear();
		this.valorSeguro.sendKeys(valorSeguro);
		return this;
	}

	public String getValorOutrasDespesas() {
		return valorOutrasDespesas.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorOutrasDespesas(String valorOutrasDespesas) {
		this.valorOutrasDespesas.clear();
		this.valorOutrasDespesas.sendKeys(valorOutrasDespesas);
		return this;
	}

	public String getValorTotalNota() {
		return valorTotalNota.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setValorTotalNota(String valorTotalNota) {
		this.valorTotalNota.clear();
		this.valorTotalNota.sendKeys(valorTotalNota);
		return this;
	}

	public String getTransportadora() {
		return transportadora.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setTransportadora(String transportadora) {
		this.transportadora.clear();
		this.transportadora.sendKeys(transportadora);
		return this;
	}

	public WebElement getBtnSearchTransportadora() {
		return btnSearchTransportadora;
	}

	public EmissaoNotaFiscalAvulsa clickBtnSearchTransportadora() {
		this.btnSearchTransportadora.click();
		return this;
	}

	public WebElement getBtnLimparTransportadora() {
		return btnLimparTransportadora;
	}

	public EmissaoNotaFiscalAvulsa clickBtnLimparTransportadora() {
		this.btnLimparTransportadora.click();
		return this;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo.clear();
		this.placaVeiculo.sendKeys(placaVeiculo);
		return this;
	}

	public String getVeiculoEstado() {
		return veiculoEstado.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setVeiculoEstado(String veiculoEstado) {
		new Select(this.veiculoEstado).selectByVisibleText(veiculoEstado);
		return this;
	}

	public String getTipoFrete() {
		return tipoFrete.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setTipoFrete(String tipoFrete) {
		new Select(this.tipoFrete).selectByVisibleText(tipoFrete);
		return this;
	}

	public String getPesoBruto() {
		return pesoBruto.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setPesoBruto(String pesoBruto) {
		this.pesoBruto.clear();
		this.pesoBruto.sendKeys(pesoBruto);
		return this;
	}

	public String getPesoLiquido() {
		return pesoLiquido.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setPesoLiquido(String pesoLiquido) {
		this.pesoLiquido.clear();
		this.pesoLiquido.sendKeys(pesoLiquido);
		return this;
	}

	public String getDescEspecieProduto() {
		return descEspecieProduto.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setDescEspecieProduto(String descEspecieProduto) {
		this.descEspecieProduto.sendKeys(descEspecieProduto);
		return this;
	}

	public String getQtdProdutoEspecie() {
		return qtdProdutoEspecie.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setQtdProdutoEspecie(String qtdProdutoEspecie) {
		this.qtdProdutoEspecie.clear();
		this.qtdProdutoEspecie.sendKeys(qtdProdutoEspecie);
		return this;
	}

	public String getObsSistema() {
		return obsSistema.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setObsSistema(String obsSistema) {
		this.obsSistema.clear();
		this.obsSistema.sendKeys(obsSistema);
		return this;
	}

	public String getObsUsuario() {
		return obsUsuario.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setObsUsuario(String obsUsuario) {
		this.obsUsuario.clear();
		this.obsUsuario.sendKeys(obsUsuario);
		return this;
	}

	public String getNomeCliente() {
		return nomeCliente.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNomeCliente(String nomeCliente) {
		this.nomeCliente.clear();
		this.nomeCliente.sendKeys(nomeCliente);
		return this;
	}

	public String getNroCliente() {
		return nroCliente.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroCliente(String nroCliente) {
		this.nroCliente.clear();
		this.nroCliente.sendKeys(nroCliente);
		return this;
	}

	public String getNomeVendedor() {
		return nomeVendedor.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor.clear();
		this.nomeVendedor.sendKeys(nomeVendedor);
		return this;
	}

	public String getNroVendedor() {
		return nroVendedor.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroVendedor(String nroVendedor) {
		this.nroVendedor.clear();
		this.nroVendedor.sendKeys(nroVendedor);
		return this;
	}

	public String getNroPedido() {
		return nroPedido.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroPedido(String nroPedido) {
		this.nroPedido.clear();
		this.nroPedido.sendKeys(nroPedido);
		return this;
	}

	public String getLojaPedido() {
		return lojaPedido.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setLojaPedido(String lojaPedido) {
		this.lojaPedido.clear();
		this.lojaPedido.sendKeys(lojaPedido);
		return this;
	}

	public String getLojaFaturamento() {
		return lojaFaturamento.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setLojaFaturamento(String lojaFaturamento) {
		this.lojaFaturamento.clear();
		this.lojaFaturamento.sendKeys(lojaFaturamento);
		return this;
	}

	public String getNroPdv() {
		return nroPdv.getAttribute("value");
	}

	public EmissaoNotaFiscalAvulsa setNroPdv(String nroPdv) {
		this.nroPdv.clear();
		this.nroPdv.sendKeys(nroPdv);
		return this;
	}

	public WebElement getBtnAlterarNroNF() {
		return btnAlterarNroNF;
	}

	public EmissaoNotaFiscalAvulsa clickBtnAlterarNroNF() {
		this.btnAlterarNroNF.click();
		return this;
	}

	public WebElement getBtnEmitirEspelho() {
		return btnEmitirEspelho;
	}

	public EmissaoNotaFiscalAvulsa clickBtnEmitirEspelho() {
		this.btnEmitirEspelho.click();
		return this;
	}

	public WebElement getBtnConcluir() {
		return btnConcluir;
	}

	public EmissaoNotaFiscalAvulsa clickBtnConcluir() {
		this.btnConcluir.click();
		return this;
	}

	public WebElement getBtnCancelar() {
		return btnCancelar;
	}

	public EmissaoNotaFiscalAvulsa clickBtnCancelar() {
		this.btnCancelar.click();
		return this;
	}
	
	/**
	 * Gera Nota Fiscal Avulsa de Troca ou Devolução de produtos ou serviços
	 * @param cuponsFiscais, Lista do Tipo NotaFiscal, que contem todos os cupons fiscais gerados no pré-recibo
	 * @throws InterruptedException
	 */
	public void gerarNfAvulsa(List<NotaFiscal> cuponsFiscais, String descEmpresa, int nroPedido, int tipoNfDevolucao) throws InterruptedException {
		
		NotaFiscalDAO nfDAO = new NotaFiscalDAO();
		
		for (NotaFiscal cf : cuponsFiscais) {
			PopupTiposNotaFiscalAvulsa tiposNfAvulsa = new PopupTiposNotaFiscalAvulsa(driver);
			PopupParametros parametros = new PopupParametros(driver);
			
			controleJanela.abrirPopupSelect(getOperacaoNF(), "Entrada");
			tiposNfAvulsa.selecionarTipoNotaFiscal(tipoNfDevolucao);
			setGrupoEstoqueOrigem("1 - Loja");
			controleJanela.abrirPopupClick(getBtnInformarParametros());
			
			if (cf.getNroCupom() != 0 && cf.getNroNf() == 0) {
				parametros.setNroCumpomFiscal(Integer.toString(cf.getNroCupom())).setEmpresaCumpomFiscal(descEmpresa)
																.setCodNroLojaCumpomFiscal(Integer.toString(cf.getCodNroLoja()))
																.setNroSerieCF(cf.getSerieNf()).clickBtnSearchBuscarCupomFiscal();
			} else if (cf.getNroCupom() == 0 && cf.getNroNf() != 0) {
				parametros.setNroNotaFiscal(Integer.toString(cf.getNroNf())).setEmpresaNotaFiscal(descEmpresa)
																.setCodNroLojaNotaFiscal(Integer.toString(cf.getCodNroLoja()))
																.setNroSerieNF(cf.getSerieNf()).clickBtnSearchBuscarNotaFiscal();
				
			}
			
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			parametros.selecionarNFCF(nroPedido);
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			
			controleJanela.fecharJanela(parametros.getBtnConcluir());
			
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			
			if (cf.getNroCupom() == 0 && cf.getNroNf() != 0) {
				clickAbaTransportador().setTipoFrete("Emitente");
			}
			
			clickBtnConcluir();
			
			//controleJanela.abrirPopupAlert(true);
			
			msg.clickBtnFechar();
			controleJanela.voltarJanelaPrincipal(false);
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			
			nfDAO.aprovarNota(cf.getNroLoja(), nroPedido, tipoNfDevolucao);
			
		}
		
	}
	
	/**
	 * Gera Nota Fiscal Avulsa de Troca ou Devolução de produtos ou serviços
	 * @param cuponsFiscais, Lista do Tipo NotaFiscal, que contem todos os cupons fiscais gerados no pré-recibo
	 * @throws InterruptedException
	 */
	public void gerarNfAvulsaSaida(List<NotaFiscal> cuponsFiscais, String descEmpresa, int nroPedido, int tipoNfDevolucao) throws InterruptedException {
		
		NotaFiscalDAO nfDAO = new NotaFiscalDAO();
		
		for (NotaFiscal cf : cuponsFiscais) {
			PopupTiposNotaFiscalAvulsa tiposNfAvulsa = new PopupTiposNotaFiscalAvulsa(driver);
			PopupParametros parametros = new PopupParametros(driver);
			
			controleJanela.abrirPopupSelect(getOperacaoNF(), "Saída");
			tiposNfAvulsa.selecionarTipoNotaFiscal(tipoNfDevolucao);
			setGrupoEstoqueOrigem("1 - Loja");
			controleJanela.abrirPopupClick(getBtnInformarParametros());
			
			if (cf.getNroCupom() != 0 && cf.getNroNf() == 0) {
				parametros.setNroCumpomFiscal(Integer.toString(cf.getNroCupom())).setEmpresaCumpomFiscal(descEmpresa)
																.setCodNroLojaCumpomFiscal(Integer.toString(cf.getCodNroLoja()))
																.setNroSerieCF(cf.getSerieNf()).clickBtnSearchBuscarCupomFiscal();
			} else if (cf.getNroCupom() == 0 && cf.getNroNf() != 0) {
				/*parametros.setNroNotaFiscal(Integer.toString(cf.getNroNf())).setEmpresaNotaFiscal(descEmpresa)
																.setCodNroLojaNotaFiscal(Integer.toString(cf.getCodNroLoja()))
																.setNroSerieNF(cf.getSerieNf()).clickBtnSearchBuscarNotaFiscal();*/
				
				parametros.setNroCumpomFiscal(Integer.toString(cf.getNroNf())).setEmpresaCumpomFiscal(descEmpresa)
					.setCodNroLojaCumpomFiscal(Integer.toString(cf.getCodNroLoja()))
					.setNroSerieCF(cf.getSerieNf()).clickBtnSearchBuscarCupomFiscal();
			}
			
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			parametros.selecionarNFCF(nroPedido);
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			
			controleJanela.fecharJanela(parametros.getBtnConcluir());
			
			agCarregamento.aguardarCarregamentoArquiteturaAntiga();
			
			if (cf.getNroCupom() == 0 && cf.getNroNf() != 0) {
				clickAbaTransportador().setTipoFrete("Emitente");
			}
			
			clickBtnConcluir();
			
			// Confirma a emissão da nota?
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			
			// Bloquear janela de confirmacao
			wait.until(ExpectedConditions.alertIsPresent());
			//driver.switchTo().alert().dismiss();
			controleJanela.abrirPopupAlert(false);
			
			if (tipoNfDevolucao == 29) { // NOTA FISCAL DE ACOMPANHAMENTO
				// Valida mensagem de trava/bloqueio
				System.out.println("Mensagem: " + msg.getMsg());
				
				Assert.assertTrue("O bloqueio de nota de acompanhamento não foi acionado!", msg.getMsg().contains("Nao e permitido a emissao de uma Nota Fiscal do tipo '29 - NOTA FISCAL ACOMPANHAMENTO NFCE S/ICMS-UF2330' para o estado 'BA' utilizando uma Nota Fiscal do tipo '5 - VENDA - NFCE' como referencia"));
				
			} else {
			
				msg.clickBtnFechar();
				controleJanela.voltarJanelaPrincipal(false);
				agCarregamento.aguardarCarregamentoArquiteturaAntiga();
				
				nfDAO.aprovarNota(cf.getNroLoja(), nroPedido, tipoNfDevolucao);
			}
		}
	}
}