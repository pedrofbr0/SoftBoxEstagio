package br.com.mv.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.mv.utils.AguardaCarregamento;
import br.com.mv.utils.Utils;
import net.serenitybdd.core.pages.PageObject;

public class ClientePage extends PageObject {
	
	/*
	 * Tela Consulta Cliente
	 */
	//Pessoa Física
	@FindBy(id = "cbx-cpf")
	private WebElement cbxCPF;
	
	@FindBy(xpath = "/html/body/div[2]/div/div[4]/div/button")
	private WebElement botaoSalvar;
	
	//Pessoa Física
	@FindBy(css = "#btn-novo-cliente")
	private WebElement btnNovoCliente;
	
	@FindBy(id = "btn-pesquisar")
	private WebElement btnPesquisarCliente;
	
	@FindBy(xpath = "//*[@id=\"1\"]/td[3]/button")
	private WebElement radioPesquisaCliente;
	
	//Pessoa Jurídica
//	@FindBy(id = "ckb-tipo-pessoa")
//	@FindBy(xpath = "//*[@id=\"ckb-tipo-pessoa\"]")
	@FindBy(css = "#ckb-tipo-pessoa")
	private WebElement cbkTipoPessoa;
	
	//Pessoa Jurídica
	@FindBy(id="cbx-cnpj")
	private WebElement cbxCnpj;
	
	/*
	 * Tela Cadastro Cliente
	 */
	@FindBy(id = "txtNome")
	private WebElement txtNome;
	
	@FindBy(id = "cbx_nro_nivel_cadastro_chosen")
	private WebElement cbxGrupo;
	
	@FindBy(id = "txtDddResidencial")
	private WebElement txtDddResidencial;
	
	@FindBy(id = "txtNroTelefoneResidencial")
	private WebElement txtNroTelefoneResidencial;
	
	@FindBy(id = "txtRamalResidencial")
	private WebElement txtRamalResidencial;
	
	@FindBy(id = "txtDddCelular")
	private WebElement txtDddCelular;
	
	@FindBy(id = "txtNroTelefoneCelular")
	private WebElement txtNroTelefoneCelular;
	
	@FindBy(id = "txtRamalCelular")
	private WebElement txtRamalCelular;
	
	@FindBy(id = "btn-novo-endereco")
	private WebElement btnNovoEndereco;
	
	@FindBy(id = "btn-salvar-cliente")
	private WebElement btnSalvarCliente;
	
	@FindBy(id = "lnk-modal-trabalho")
	private WebElement lnkModalTrabalho;
	
	@FindBy(xpath = "//*[@id=\"tab-cadastro-cliente\"]/li[3]/a")
	private WebElement abaReferencias;
	
//	@FindBy(xpath = "//*[@id=\"1\"]/td[2]/div/button")
//	private WebElement btnEditarEndereço;

	//Pessoa Jurídica
	@FindBy(xpath="//*[@id=\"frm-cadastro-cliente\"]/div[3]/div[6]/input")
	private WebElement nomeFantasia;
	
	//Pessoa Jurídica
	@FindBy(xpath="//*[@id=\"frm-cadastro-cliente\"]/div[4]/div[2]/input")
	private WebElement inscricaoEstadual;
	
	//Pessoa Jurídica
	@FindBy(xpath="//*[@id=\"cbx_nro_tipo_empresa_chosen\"]")
	private WebElement cbxTipoEmpresa;
	
	//Pessoa Jurídica
	@FindBy(id="lnk-representante")
	private WebElement lnkRepresentante;
	
	//Pessoa Jurídica
	@FindBy(id = "lnk-modal-cred-prop-pj")
	private WebElement lnkModalCredPropPj;
	
	//Edição
	@FindBy(xpath = "//*[@id=\"1\"]/td[2]/div/button")
	private WebElement btnEditar;
	
	/*
	 * Tela Pesquisa Endereço	
	 */
	@FindBy(id = "txt-cep-pesquisa-endereco")
	private WebElement txtCepPesquisaEndereco;
	
	@FindBy(id = "btn-pesquisar-endereco")
	private WebElement btnPesquisarEndereco;
	
	@FindBy(xpath = "//*[@id='grd-pesquisa-endereco']/tbody/tr[2]/td[3]")
	private WebElement formFieldRadioEnd;
	
	@FindBy(id = "btn-selecionar-endereco")
	private WebElement btnSelecionarEndereco;
	
	/*
	 * Tela Endereço
	 */
	@FindBy(css = "input[data-column='nro_endereco_cliente']")
	private WebElement nroResidencia;
	
	@FindBy(xpath = "//*[@id=\"cbx_tipo_complemento_endereco_chosen\"]")
	private WebElement cbxComplementoEndereco;
	
	@FindBy(id = "dp-residencia-cliente")
	private WebElement dpResidenciaCliente;
	
	@FindBy(xpath = "//*[@id=\"infoEndereco\"]/div[4]/div")
	private WebElement cbxSituacaoResidencia;
	
	@FindBy(xpath = "//*[@id=\"infoEndereco\"]/div[6]/div")
	private WebElement cbxTipoEndereco;
	
	@FindBy(id = "btn-salvar-endereco")
	private WebElement btnSalvarEndereco;
	
	/*
	 * Tela Identificação/Trabalho
	 */
	@FindBy(xpath = "//*[@id=\"cbx_orgao_emissor_chosen\"]")
	private WebElement cbxOrgaoEmissorChosen;
	
	@FindBy(xpath = "//*[@id=\"cbx_uf_chosen\"]")
	private WebElement cbxUfChosen;
	
	@FindBy(id = "nroRg")
	private WebElement nroRg;
	
	@FindBy(id = "identidade_dt_emissao")
	private WebElement identidadeDtEmissao;
	
	@FindBy(id = "nome_pai")
	private WebElement nomePai;
	
	@FindBy(id = "nome_mae")
	private WebElement nomeMae;
	
	@FindBy(id = "identidade_ddd")
	private WebElement DataDeNascimento_identidadeDDD;
	
	@FindBy(id = "cbx_sexo_chosen")
	private WebElement cbxSexoChosen;
	
	@FindBy(id = "flag_nacionalidade_chosen")
	private WebElement flagNacionalidadeChosen;
	
	@FindBy(id = "cbx_uf_naturalidade_chosen")
	private WebElement cbxUfNaturalidadeChosen;
	
	@FindBy(id = "cod_cgl_naturalidade_chosen")
	private WebElement cbxCglNaturalidadeChosen;
	
	@FindBy(id = "cbx_estado_civil_chosen")
	private WebElement cbxEstadoCivilChosen;
	
	@FindBy(id = "cbx_ocupacao_chosen")
	private WebElement cbxOcupacaoChosen;
	
	@FindBy(id = "cbx_profissao_chosen")
	private WebElement cbxProfissaoChosen;
	
	@FindBy(id = "cbx_tipo_comprovante_renda_chosen")
	private WebElement cbxTipoComprovanteRendaChosen;
	
	@FindBy(id = "dp-comprovante-renda")
	private WebElement dpComprovanteRenda;
	
	@FindBy(id = "txt-vr-salario-cliente")
	private WebElement txtVrSalarioCliente;
	
	@FindBy(id = "local_trabalho")
	private WebElement localTrabalho;
	
	@FindBy(id = "txtCepTrabalho")
	private WebElement txtCepTrabalho;
	
	@FindBy(id = "dp-trabalha-desde")
	private WebElement dpTrabalhaDesde;
	
	@FindBy(id = "txt-numero-trabalho-cliente")
	private WebElement txtNumeroTrabalhoCliente;
	
	@FindBy(id = "txtDddTrabalho")
	private WebElement txtDddTrabalho;
	
	@FindBy(id = "txtNroTelefoneTrabalho")
	private WebElement txtNroTelefoneTrabalho;
	
	@FindBy(id = "txtRamalTrabalho")
	private WebElement txtRamalTrabalho;
	
	@FindBy(id = "btn-salvar-trabalho")
	private WebElement btnSalvarTrabalho;
	
	/*
	 * Tela Referências
	 */
	@FindBy(id = "btn-nova-referencia")
	private WebElement btnNovaReferencia;
	
	@FindBy(id = "txt-sf-cliente-ref-text")
	private WebElement txtSfClienteRefText;
	
	@FindBy(css = "input[data-column='fone_referencia_ddd']")
	private WebElement foneReferenciaDDD;
	
	@FindBy(xpath = "//*[@id=\"frm-referencia-cliente\"]/fieldset/div[1]/div[4]/input[2]")
	private WebElement foneReferenciaNro;
	
	@FindBy(xpath = "//*[@id=\"frm-referencia-cliente\"]/fieldset/div[1]/div[4]/input[3]")
	private WebElement foneReferenciaRamal;
	
	@FindBy(xpath = "//*[@id=\"frm-referencia-cliente\"]/fieldset/div[2]/div[2]/div")
	private WebElement vinculoReferencia;
	
	@FindBy(id = "btn-salvar-referencia-cliente")
	private WebElement btnSalvarReferencia;
	
	/*
	 * Tela Crediário Próprio
	 */
	@FindBy(id = "lnk-modal-dados-financeiros")
	private WebElement lnkModalDadosFinanceiros;
	
	@FindBy(id = "cbx_grau_escolaridade_chosen")
	private WebElement cbxGrauEscolaridadeChosen;
	
	@FindBy(xpath = "//*[@id=\"frm-dados-financeiros\"]/fieldset/div[1]/div[4]/input")
	private WebElement nroCarteiraTrabalho;
	
	@FindBy(id = "cbx_tipo_tel_residencial_chosen")
	private WebElement cbxTipoTelResidencialChosen;
	
	@FindBy(xpath = "//*[@id=\"frm-dados-financeiros\"]/fieldset/div[2]/div[4]/input")
	private WebElement serieCarteiraTrabalho;
	
	@FindBy(id = "obs_endereco_cliente")
	private WebElement obsEnderecoCliente;
	
	@FindBy(xpath = "//*[@id=\"frm-dados-cred-prop-pj\"]/fieldset/div[2]/div[2]/div/input")
	private WebElement obsEnderecoClientePj;
	
	@FindBy(id = "btn-salvar-dados-financeiros")
	private WebElement btnSalvarDadosFinanceiros;
	
	//Pessoa Jurídica
	@FindBy(id = "btn-salvar-dados-cred-prop-pj")
	private WebElement btnSalvarDadosCredPropPj;
	
	//Pessoa Jurídica
	@FindBy(xpath = "//*[@id=\"frm-dados-cred-prop-pj\"]/fieldset/div[2]/div[2]/div/input")
	private WebElement pontoReferenciaPj;
	
	/*
	 * Tela Comprador/Representante
	 */
	@FindBy(id="btn-novo-representante")
	private WebElement btnNovoRepresentante;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[1]/input[1]")
	private WebElement cpfRepresentante;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[1]/input[2]")
	private WebElement nomeRepresentante;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[2]/div[2]/input[1]")
	private WebElement dddRepresentantePrimario;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[2]/div[2]/input[2]")
	private WebElement telefoneRepresentantePrimario;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[2]/div[2]/input[3]")
	private WebElement ramalRepresentantePrimario;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[2]/div[4]/input[1]")
	private WebElement dddRepresentanteSecundario;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[2]/div[4]/input[2]")
	private WebElement telefoneRepresentanteSecundario;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[2]/div[4]/input[3]")
	private WebElement ramalRepresentanteSecundario;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[3]/input")
	private WebElement cargoRepresentante;
	
	@FindBy(id = "cbx_uf_comprador_chosen")
	private WebElement cbxUfCompradorChosen;
	
	@FindBy(xpath = "//*[@id=\"cbx_orgao_emissor_chosen\"]")
	private WebElement cbxOrgaoEmissorChosenRepresentante;
	
	@FindBy(xpath = "//*[@id=\"frm-representante\"]/fieldset/div[4]/div[2]/div[3]/input")
	private WebElement nroRgRepresentante;
	
	@FindBy(id = "identidade_dt_emissao")
	private WebElement identidadeDtEmissaoRepresentante;
	
	@FindBy(id = "btn-salvar-representante")
	private WebElement btnSalvarRepresentante;
	
	
	//Crediário Próprio (Pessoa Jurídica)

	private Utils utils;
	
	public ClientePage() {
		this.utils = new Utils();
	}
	
	//Tela Consulta Cliente
	public void informarNovoCPF() throws InterruptedException {
		
		Thread.sleep(3000);
		Thread.sleep(3000); // devido ao alerta de promocoes que apresentado na tela 
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		
		this.cbxCPF.clear();
		this.cbxCPF.sendKeys(utils.gerarCpf());
	}
	
	//Tela Consulta Cliente
	public void informarCPF(String cpf) throws InterruptedException {
		
		Thread.sleep(3000);
		Thread.sleep(3000); // devido ao alerta de promocoes que apresentado na tela 
		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		
		this.cbxCPF.clear();
		this.cbxCPF.sendKeys(cpf);
	}
	
	//Tela Consulta Cliente
	public void clicarBtnNovoCliente() throws InterruptedException {
		Thread.sleep(2000);
		this.btnNovoCliente.click();
	}
	
	/*
	 * Tela Cadastro Cliente
	 */
	public void informarNomeDoCliente(String nome) throws InterruptedException {
		Thread.sleep(2000);
		this.txtNome.clear();
		this.txtNome.sendKeys(nome);
	}
	
	public void selecionarGrupo(String grupo) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(cbxGrupo, grupo);
	}
	
	public void informarTelefoneResidencial(String ddd, String numero, String ramal) {
		this.txtDddResidencial.clear();
		this.txtDddResidencial.sendKeys(ddd);
		this.txtNroTelefoneResidencial.clear();
		this.txtNroTelefoneResidencial.sendKeys(numero);
		this.txtRamalResidencial.clear();
		this.txtRamalResidencial.sendKeys(ramal);
	}
	
	public void informarTelefoneCelular(String ddd, String numero, String ramal) {
		this.txtDddCelular.clear();
		this.txtDddCelular.sendKeys(ddd);
		this.txtNroTelefoneCelular.clear();
		this.txtNroTelefoneCelular.sendKeys(numero);
		this.txtRamalCelular.clear();
		this.txtRamalCelular.sendKeys(ramal);
	}
	
	public void clicarBtnNovoEndereco() {
		this.btnNovoEndereco.click();
	}
	
	/*
	 * Tela Pesquisa Endereço
	 */
	public void informarOCep(String cep) {
		this.txtCepPesquisaEndereco.clear();
		this.txtCepPesquisaEndereco.sendKeys(cep);
	}
	
	public void clicarBtnPesquisarEndereco() {
		this.btnPesquisarEndereco.click();
	}
	
	public void clicarRadio() throws InterruptedException {
		this.formFieldRadioEnd.click();
	}
	
	public void clicarBtnSelecionar() {
		this.btnSelecionarEndereco.click();
	}
	
	/*
	 * Tela Endereço
	 */
	public void informarNumero(String numero) throws InterruptedException {
		Thread.sleep(2000);
		this.nroResidencia.clear();
		this.nroResidencia.sendKeys(numero);
	}
	
	public void selecionarTipoDoComplemento(String tipo) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(cbxComplementoEndereco,tipo);
	}
	
	public void informarDataDeResidencia(String data) {
		this.dpResidenciaCliente.clear();
		this.dpResidenciaCliente.sendKeys(data);
	}
	
	public void selecionarSituacaoResidencia(String situacao) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(cbxSituacaoResidencia,situacao);
	}
	
	public void selecionarTipoEndereco(String tipo) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(cbxTipoEndereco,tipo);
	}
	
	public void clicarBtnOk() {
		this.btnSalvarEndereco.click();
	}
	
	//Tela Cadastro Cliente
	public void clicarBtnSalvar() throws InterruptedException {
		Thread.sleep(2000);
		this.btnSalvarCliente.click();
	}
	
	//Tela Cadastro Cliente-Validação
	public void validarMensagemSucesso() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> modais = this.getDriver().findElements(By.cssSelector("div[id^='mdl'].modal.in .modal-body"));
		System.out.println( modais.get(1).getText().trim());
		Assert.assertTrue("Erro ao salvar cliente", modais.get(1).getText().trim().equals("Cliente salvo com sucesso!"));
	}
	
	//Tela Cadastro Cliente
	public void clicarAbaIdentificacao() throws InterruptedException {
		Thread.sleep(2000);
		this.lnkModalTrabalho.click();
	}
	
	/*
	 * Tela Identificação/Trabalho
	 */
	public void selecionarOrgaoEmissor(String orgao) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(cbxOrgaoEmissorChosen,orgao);
	}
	
	public void selecionarUf(String uf) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosen(cbxUfChosen,uf);
	}
	
	public void informarNroRg(String numero) {
		this.nroRg.clear();
		this.nroRg.sendKeys(numero);
	}
	
	public void selecionarDataDeEmissao(String data) {
		this.identidadeDtEmissao.clear();
		this.identidadeDtEmissao.sendKeys(data);
	}
	
	public void informarNomeDoPai(String nome) {
		this.nomePai.clear();
		this.nomePai.sendKeys(nome);
	}
	
	public void informarNomeDoMae(String nome) {
		this.nomeMae.clear();
		this.nomeMae.sendKeys(nome);
	}
	
	public void selecionarDataDeNascimento(String data) {
		this.DataDeNascimento_identidadeDDD.clear();
		this.DataDeNascimento_identidadeDDD.sendKeys(data);
	}
	
	public void selecionarSexo(String sexo) {
		utils.preencherCampoChosen(cbxSexoChosen,sexo);
	}
	
	public void selecionarNacionalidade(String nacionalidade) {
		utils.preencherCampoChosen(flagNacionalidadeChosen,nacionalidade);
	}
	
	public void selecionarNaturalidade(String uf, String cidade) throws InterruptedException {
		utils.preencherCampoChosen(cbxUfNaturalidadeChosen,uf);
		Thread.sleep(4000);
		utils.preencherCampoChosen(cbxCglNaturalidadeChosen,cidade);
	}
	
	public void selecionarEstadoCivil(String estado) {
		utils.preencherCampoChosen(cbxEstadoCivilChosen,estado);
	}
	
	public void selecionarOcupacao(String ocupacao) {
		utils.preencherCampoChosen(cbxOcupacaoChosen,ocupacao);
	}
	
	public void selecionarProfissao(String profissao) {
		utils.preencherCampoChosen(cbxProfissaoChosen,profissao);
	}
	
	public void selecionarTipoComprovanteRenda(String tipo) {
		utils.preencherCampoChosen(cbxTipoComprovanteRendaChosen,tipo);
	}
	
	public void selecionarMesAnoComprovanteRenda(String data) {
		this.dpComprovanteRenda.clear();
		this.dpComprovanteRenda.sendKeys(data);
	}
	
	public void informarRendaLiquida(String renda) {
		this.txtVrSalarioCliente.clear();
		this.txtVrSalarioCliente.sendKeys(renda);
	}
	
	public void informarLocalDeTrabalho(String nome) {
		this.localTrabalho.clear();
		this.localTrabalho.sendKeys(nome);
	}
	
	public void informarCepTrabalho(String cep) {
		this.txtCepTrabalho.clear();
		this.txtCepTrabalho.sendKeys(cep);
	}
	
	public void selecionarTrabalhaDesde(String data) {
		this.dpTrabalhaDesde.clear();
		this.dpTrabalhaDesde.sendKeys(data);
	}
	
	public void informarNumeroLocalDeTrabalho(String numero) {
		this.txtNumeroTrabalhoCliente.clear();
		this.txtNumeroTrabalhoCliente.sendKeys(numero);
	}
	
	public void informarTelefoneTrabalho(String ddd, String numero, String ramal) {
		this.txtDddTrabalho.clear();
		this.txtDddTrabalho.sendKeys(ddd);
		this.txtNroTelefoneTrabalho.clear();
		this.txtNroTelefoneTrabalho.sendKeys(numero);
		this.txtRamalTrabalho.clear();
		this.txtRamalTrabalho.sendKeys(ramal);
	}
	
	public void clicarBtnOkSalvarTrabalho() {
		this.btnSalvarTrabalho.click();
	}
	
	//Tela Cadastro Cliente
	public void clicarAbaReferencias() {
		this.abaReferencias.click();
	}
	
	/*
	 * Tela Referências
	 */
	public void clicarBtnNovaReferencia() throws InterruptedException {
		Thread.sleep(1000);
		this.btnNovaReferencia.click();
	}
	
	public void informarNomeReferencia(String nome) {
		this.txtSfClienteRefText.clear();
		this.txtSfClienteRefText.sendKeys(nome);
	}
	
	public void informarTelefoneReferencia(String ddd, String numero, String ramal) {
		this.foneReferenciaDDD.clear();
		this.foneReferenciaDDD.sendKeys(ddd);
		this.foneReferenciaNro.clear();
		this.foneReferenciaNro.sendKeys(numero);
		this.foneReferenciaRamal.clear();
		this.foneReferenciaRamal.sendKeys(ramal);
	}
	
	public void selecionarVinculoReferencia(String vinculo) {
		utils.preencherCampoChosen(vinculoReferencia,vinculo);
	}
	
	public void clicarBtnOkSalvarReferencia() {
		this.btnSalvarReferencia.click();
	}
	
	//Tela Cadastro Cliente
	
	//Pessoa Jurídica
	public void clicarAbaCrediarioproprio() {
		this.lnkModalDadosFinanceiros.click();
	}
	
	//Pessoa Jurídica
	public void clicarAbaCrediarioproprioPJ() {
		this.lnkModalCredPropPj.click();
	}
	
	/*
	 * Tela Crediário Próprio
	 */
	public void selecionarGrauEscolaridade(String escolaridade) {
		utils.preencherCampoChosen(cbxGrauEscolaridadeChosen,escolaridade);
	}
	
	public void informarNumeroCarteiraTrabalho(String numero) {
		this.nroCarteiraTrabalho.clear();
		this.nroCarteiraTrabalho.sendKeys(numero);
	}
	
	public void selecionarTipoTelResidencial(String tipo) {
		utils.preencherCampoChosen(cbxTipoTelResidencialChosen, tipo);
	}
	
	public void informarSerieCarteiraTrabalho(String serie) {
		this.serieCarteiraTrabalho.clear();
		this.serieCarteiraTrabalho.sendKeys(serie);
	}
	
	public void informarPontoReferencia(String referencia) {
		this.obsEnderecoCliente.clear();
		this.obsEnderecoCliente.sendKeys(referencia);
	}
	
	public void informarPontoReferenciaPj(String referencia) {
		this.obsEnderecoClientePj.clear();
		this.obsEnderecoClientePj.sendKeys(referencia);
	}
	
	public void clicarBtnOkSalvarDadosFinanceiros() {
		this.btnSalvarDadosFinanceiros.click();
	}
	
	//Pessoa Jurídica
	public void clicarBtnOkSalvarDadosCredPropPj() {
		this.btnSalvarDadosCredPropPj.click();
	}
	
	/*
	 * Tela Cadastro Cliente PJ
	 */
	public void clicarCheckBoxTipoPessoa() throws InterruptedException {
		Thread.sleep(30000); // devido ao alerta de promocoes que apresentado na tela 
//		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
//		Thread.sleep(6000);
		this.getDriver().findElement(By.cssSelector("#ckb-tipo-pessoa")).click();
	}
	
	public void informarNovoCNPJ() throws InterruptedException {
//		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(10000);
		this.cbxCnpj.clear();
		this.cbxCnpj.sendKeys(utils.geraCNPJ());
	}
	
	public void informarCNPJ(String cnpj) throws InterruptedException {
//		new AguardaCarregamento(this.getDriver()).aguardarCarregamentoNovaArquitetura2();
		Thread.sleep(10000);
		this.cbxCnpj.clear();
		this.cbxCnpj.sendKeys(cnpj);
	}
	
	public void informarNomeFantasia(String nome) throws InterruptedException {
		this.nomeFantasia.clear();
		this.nomeFantasia.sendKeys(nome);
	}
	
	public void informarInscricaoEstadual(String ie) {
		this.inscricaoEstadual.clear();
		this.inscricaoEstadual.sendKeys(ie);
	}
	
	public void selecionarTipoEmpresa(String tipo) {
		utils.preencherCampoChosen(cbxTipoEmpresa, tipo);
	}
	
	
	/*
	 * Tela Comprador/Representante
	 */
	
	public void clicarAbaCompradorRepresentante() {
		this.lnkRepresentante.click();
	}
	
	public void clicarBtnNovoRepresentante() {
		this.btnNovoRepresentante.click();
	}
	
	public void informarUmNovoCpfRepresentante() {
		this.cpfRepresentante.clear();
		this.cpfRepresentante.sendKeys(utils.gerarCpf());
	}
	
	public void informarNomeRepresentante(String nome) {
		this.nomeRepresentante.clear();
		this.nomeRepresentante.sendKeys(nome);
	}
	
	public void informarTelefonePrimarioRepresentante(String ddd, String numero, String ramal) {
		this.dddRepresentantePrimario.clear();
		this.dddRepresentantePrimario.sendKeys(ddd);
		this.telefoneRepresentantePrimario.clear();
		this.telefoneRepresentantePrimario.sendKeys(numero);
		this.ramalRepresentantePrimario.clear();
		this.ramalRepresentantePrimario.sendKeys(ramal);
	}
	
	public void informarTelefoneSecundarioRepresentante(String ddd, String numero, String ramal) {
		this.dddRepresentanteSecundario.clear();
		this.dddRepresentanteSecundario.sendKeys(ddd);
		this.telefoneRepresentanteSecundario.clear();
		this.telefoneRepresentanteSecundario.sendKeys(numero);
		this.ramalRepresentanteSecundario.clear();
		this.ramalRepresentanteSecundario.sendKeys(ramal);
	}
	
	public void informarCargoRepresentante(String cargo) {
		this.cargoRepresentante.clear();
		this.cargoRepresentante.sendKeys(cargo);
	}
	
	public void selecionarUfRepresentante(String uf) throws InterruptedException {
		Thread.sleep(2000);
		utils.preencherCampoChosenWebDriver("#cbx_uf_comprador_chosen", uf, this.getDriver());
	}
	
	public void selecionarOrgaoEmissorRepresentante(String orgao) throws InterruptedException {
		Thread.sleep(2000);
		By by = By.xpath(".//*[@id='frm-representante']/fieldset/div[4]/div[2]/div[2]/div[1]");
		utils.preencherCampoChosenWebDriverBy(by, orgao, this.getDriver());
	}
	
	public void informarNroRgRepresentante(String rg) {
		this.nroRgRepresentante.clear();
		this.nroRgRepresentante.sendKeys(rg);
	}
	
	public void selecionarDataDeEmissaoRgRepresentante(String data) {
		this.getDriver().findElement(By.xpath(".//*[@id='frm-representante']/fieldset/div[4]/div[2]/div[4]/div/input")).clear();
		this.getDriver().findElement(By.xpath(".//*[@id='frm-representante']/fieldset/div[4]/div[2]/div[4]/div/input")).sendKeys(data);
	}
	
	public void clicarBtnOkSalvarRpresentante() {
		this.btnSalvarRepresentante.click();
	}
	
	/*
	 * EDIÇÃO DE CLIENTE
	 */
	public void clicarBtnPesquisarCliente() {
		this.btnPesquisarCliente.click();
	}
	
	public void clicarRadioPesquisaCliente() {
		this.radioPesquisaCliente.click();
	}
	
	public void clicarBtnEditar() {
		this.btnEditar.click();
	}
	
	public void clicarBtnEditarReferencia() {
		this.getDriver().findElement(By.xpath("//*[@id=\"1\"]/td[2]/div/span")).click();
	}
	
	public void clicarBtnEditarRepresentante() {
		this.getDriver().findElement(By.xpath("//div[@class='editButtonRepresentante ui-pg-div ui-inline-edit']")).click();
		
	}
	
	/*
	 * OUTROS
	 */
	public void esperar(int tempo) throws InterruptedException {
		Thread.sleep(tempo);
	}
	
	public void clicarBotaoSalvar() {
		this.botaoSalvar.click();
	}

}
