package br.com.mv.steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import br.com.mv.pages.ClientePage;
import net.thucydides.core.annotations.Steps;

public class ClienteSteps {
	
	@Steps
	ClientePage cliente;
	
	/*
	 * Tela Consulta Cliente
	 */
	@When("Eu informo um novo CPF")
	public void euInformoUmNovoCPF() throws InterruptedException {
		cliente.informarNovoCPF();
	}
	
	@When("Eu informo CPF $cpf")
	public void euInformoCPF(String cpf) throws InterruptedException {
		cliente.informarCPF(cpf);
	}
	
	@When("Eu clico no botao Novo Cliente")
	public void euClicoNoBotaoNovoCliente() throws InterruptedException {
		cliente.clicarBtnNovoCliente();
	}
	
	//Pessoa Jurídica
	@When("Eu clico na checkbox de Tipo de Pessoa")
	public void euClicoCheckBoxTipoPessoa() throws InterruptedException {
		cliente.clicarCheckBoxTipoPessoa();
	}
	
	//Pessoa Jurídica
	@When("Eu informo um novo CNPJ")
	public void euInformoUmNovoCNPJ() throws InterruptedException {
		cliente.informarNovoCNPJ();
	}
	
	@When("Eu informo CNPJ $cnpj")
	public void euInformoCNPJ(String cnpj) throws InterruptedException {
		cliente.informarCNPJ(cnpj);
	}
	
	/*
	 * Tela Cadastro Cliente
	 */
	@When("Eu informo o nome do cliente $nome")
	public void euInformoONomeDoCliente(String nome) throws InterruptedException {
		cliente.informarNomeDoCliente(nome);
	}

	@When("Eu seleciono o grupo $grupo")
	public void euSelecionoGrupo(String grupo) throws InterruptedException {
		cliente.selecionarGrupo(grupo);
	}
	
	@When("Eu informo o numero de telefone residencial $ddd $numero $ramal")
	public void euInformoNumeroDeTelefoneResidencial(String ddd, String numero, String ramal) {
		cliente.informarTelefoneResidencial(ddd, numero, ramal);
	}
	
	@When("Eu informo o numero de telefone celular $ddd $numero $ramal")
	public void euInformoNumeroDeTelefoneCelular(String ddd, String numero, String ramal) {
		cliente.informarTelefoneCelular(ddd, numero, ramal);
	}
	
	@When("Eu clico no botao Inserir Novo Endereco")
	public void euClicoNoBotaoInformarNovoEndereco() {
		cliente.clicarBtnNovoEndereco();
	}
	
	//Pessoa Jurídica
	@When("Eu informo o nome fantasia $nome")
	public void euInformoNomeFantasia(String nome) throws InterruptedException {
		cliente.informarNomeFantasia(nome);
	}
	
	//Pessoa Jurídica
	@When("Eu informo a inscricao estadual $ie")
	public void euInformoInscricaoEstadual(String ie) {
		cliente.informarInscricaoEstadual(ie);
	}
	
	//Pessoa Jurídica
	@When("Eu seleciono o tipo de empresa $tipo")
	public void euSelecionoTipoEmpresa(String tipo) {
		cliente.selecionarTipoEmpresa(tipo);
	}	
	
	/*
	 * Tela Pesquisa Endereço
	 */
	@When("Eu informo o CEP $cep")
	public void euInformoOCep(String cep) {
		cliente.informarOCep(cep);
	}
	
	@When("Eu clico no botao Pesquisar Endereco")
	public void euClicoNoBotaoPesquisarEndereco() {
		cliente.clicarBtnPesquisarEndereco();
	}
	
	@When("Eu clico no endereco no campo radio")
	public void euClicoNoEnderecoNoCampoRadio() throws InterruptedException {
		cliente.clicarRadio();
	}
	
	@When("Eu clico no botao Selecionar")
	public void euClicoNoBotaoSelecionar() {
		cliente.clicarBtnSelecionar();
	}
	
	/*
	 * Tela Endereço
	 */
	@When("Eu informo o numero da residencia $numero")
	public void euInformoNumeroDaResidencia(String numero) throws InterruptedException {
		cliente.informarNumero(numero);		
	}
	
	@When("Eu seleciono o tipo de complemento $tipo")
	public void euSelecionoOTipoDeComplemento(String tipo) throws InterruptedException {
		cliente.selecionarTipoDoComplemento(tipo);		
	}
	
	@When("Eu informo a data de residencia $data")
	public void euInformoDataDeResidencia(String data) {
		cliente.informarDataDeResidencia(data);		
	}
	
	@When("Eu informo o situacao da residencia $situacao")
	public void euInformoSituacaoDaResidencia(String situacao) throws InterruptedException {
		cliente.selecionarSituacaoResidencia(situacao);		
	}
	
	@When("Eu informo o tipo de endereco $tipo")
	public void euInformoTipoDeEndereco(String tipo) throws InterruptedException {
		cliente.selecionarTipoEndereco(tipo);		
	}
	
	@When("Eu clico no botao OK")
	public void euClicoNoBotaoOK() {
		cliente.clicarBtnOk();		
	}
	
	//Tela Cadastro Cliente
	@When("Eu clico no botao Salvar cliente")
	public void euClicoNoBotaoSalvar() throws InterruptedException {
		cliente.clicarBtnSalvar();
	}
	
	//Tela Cadastro Cliente-Validação
	@Then("Eu valido a mensagem de sucesso do cadastro do cliente")
	public void EuValidoMensagemSucesso() throws InterruptedException {
		cliente.validarMensagemSucesso();
	}
	
	//Tela Cadastro Cliente
	@When("Eu clico aba de Identificação/Trabalho")
	public void EuClicoAbaIdentificacao() throws InterruptedException {
		cliente.clicarAbaIdentificacao();
	}
	
	/*
	 * Tela Identificação/Trabalho
	 */
	@When("Eu seleciono o orgao emissor $orgao")
	public void EuSelecionoOrgaoEmissor(String orgao) throws InterruptedException {
		cliente.selecionarOrgaoEmissor(orgao);
	}
	
	@When("Eu seleciono a UF $uf")
	public void EuSelecionoUf(String uf) throws InterruptedException {
		cliente.selecionarUf(uf);
	}
	
	@When("Eu informo o RG $numero")
	public void EuInformoNroRg(String numero) throws InterruptedException {
		cliente.informarNroRg(numero);
	}
	
	@When("Eu seleciono data de emissao $data")
	public void EuSelecionoDataDeEmissao(String data) throws InterruptedException {
		cliente.selecionarDataDeEmissao(data);
	}
	
	@When("Eu informo o nome do pai $nome")
	public void EuInformoNomeDoPai(String nome) throws InterruptedException {
		cliente.informarNomeDoPai(nome);
	}
	
	@When("Eu informo o nome da mae $nome")
	public void EuInformonomeDaMae(String nome) throws InterruptedException {
		cliente.informarNomeDoMae(nome);
	}
	
	@When("Eu seleciono data de nascimento $data")
	public void EuInformoDataDeNascimento(String data) throws InterruptedException {
		cliente.selecionarDataDeNascimento(data);
	}
	
	@When("Eu seleciono sexo $sexo")
	public void EuSelecionoSexo(String sexo) throws InterruptedException {
		cliente.selecionarSexo(sexo);
	}
	
	@When("Eu seleciono nacionalidade $nacionalidade")
	public void EuSelecionoNacionalidade(String nacionalidade) throws InterruptedException {
		cliente.selecionarNacionalidade(nacionalidade);
	}
	
	@When("Eu seleciono naturalidade $uf $cidade")
	public void EuSelecionoNaturalidade(String uf, String cidade) throws InterruptedException {
		cliente.selecionarNaturalidade(uf, cidade);
	}
	
	@When("Eu seleciono Estado Civil $estado")
	public void EuSelecionoEstadoCivil(String estado) throws InterruptedException {
		cliente.selecionarEstadoCivil(estado);
	}
	
	@When("Eu seleciono ocupacao $ocupacao")
	public void euSelecionoOcupacao(String ocupacao) {
		cliente.selecionarOcupacao(ocupacao);
	}
	
	@When("Eu seleciono profissao $profissao")
	public void euSelecionoProfissao(String profissao) {
		cliente.selecionarProfissao(profissao);
	}
	
	@When("Eu seleciono tipo de comprovante de renda $tipo")
	public void euSelecionoTipoComprovanteRenda(String tipo) {
		cliente.selecionarTipoComprovanteRenda(tipo);
	}
	
	@When("Eu seleciono mes/ano do comprovante de renda $data")
	public void euSelecionoMesAnoComprovanteRenda(String data) {
		cliente.selecionarMesAnoComprovanteRenda(data);
	}
	
	@When("Eu informo renda liquida $renda")
	public void euInformoRendaLiquida(String renda) {
		cliente.informarRendaLiquida(renda);
	}
	
	@When("Eu informo local de trabalho $nome")
	public void euInformoLocalDeTrabalho(String nome) {
		cliente.informarLocalDeTrabalho(nome);
	}
	
	@When("Na tela de Identificao/Trabalho, eu informo o CEP $cep")
	public void euInformoCepTrabalho(String cep) {
		cliente.informarCepTrabalho(cep);
	}
	
	@When("Eu seleciono trabalha desde $data")
	public void euSelecionoTrabalhaDesde(String data) {
		cliente.selecionarTrabalhaDesde(data);
	}
	
	@When("Eu informo numero do local de trabalho $numero")
	public void euInformoNumeroLocalDeTrabalho(String numero) {
		cliente.informarNumeroLocalDeTrabalho(numero);
	}
	
	@When("Eu informo numero de telefone do trabalho $ddd $numero $ramal")
	public void euInformoTelefoneTrabalho(String ddd, String numero, String ramal) {
		cliente.informarTelefoneTrabalho(ddd, numero, ramal);
	}
	
	@When("Eu clico no botao ok da tela Identificacao/Trabalho")
	public void euClicoNoBotaoOkSalvarTrabalho() {
		cliente.clicarBtnOkSalvarTrabalho();
	}
	
	
	//Tela Cadastro Cliente
	@When("Eu clico aba de Referencias")
	public void euClicoAbaReferencias() {
		cliente.clicarAbaReferencias();
	}
	
	/*
	 * Tela Referências
	 */
	@When("Eu clico botao nova referencia")
	public void euClicoBtnNovaReferencia() throws InterruptedException {
		cliente.clicarBtnNovaReferencia();
	}
	
	@When("Eu informo o nome da referencia $nome")
	public void euInformoNomeReferencia(String nome) {
		cliente.informarNomeReferencia(nome);
	}
	
	@When("Eu informo o telefone da referencia $ddd $numero $ramal")
	public void euInformoTelefoneReferencia(String ddd, String numero, String ramal) {
		cliente.informarTelefoneReferencia(ddd, numero, ramal);
	}
	
	@When("Eu seleciono o vinculo da referencia $vinculo")
	public void euSelecionoVinculoReferencia(String vinculo) {
		cliente.selecionarVinculoReferencia(vinculo);
	}
	
	@When("Eu clico no botao ok da tela Referencias")
	public void euInformoNomeReferencia() {
		cliente.clicarBtnOkSalvarReferencia();
	}
	
	/*
	 * Tela Crediário Próprio
	 */
	@When("Eu clico aba Crediario Proprio")
	public void euClicoAbaCrediarioproprio() {
		cliente.clicarAbaCrediarioproprio();
	}
	
	//Pessoa Jurídica
	@When("Eu clico aba Crediario Proprio PJ")
	public void euClicoAbaCrediarioproprioPj() {
		cliente.clicarAbaCrediarioproprioPJ();
	}
	
	@When("Eu seleciono grau de escolaridade $escolaridade")
	public void euSelecionoGrauEscolaridade(String escolaridade) {
		cliente.selecionarGrauEscolaridade(escolaridade);
	}
	
	@When("Eu informo numero da carteira de trabalho $numero")
	public void euInformoNumeroCarteiraTrabalho(String numero) {
		cliente.informarNumeroCarteiraTrabalho(numero);
	}
	
	@When("Eu seleciono tipo de telefone residencial $tipo")
	public void euSelecionoTipoDeTelefoneResidencial(String tipo) {
		cliente.selecionarTipoTelResidencial(tipo);
	}
	
	@When("Eu informo serie da carteira de trabalho $serie")
	public void euInformoSerieCarteiraTrabalho(String serie) {
		cliente.informarSerieCarteiraTrabalho(serie);
	}
	
	@When("Eu informo ponto de referencia $referencia")
	public void euInformoPontoReferencia(String referencia) {
		cliente.informarPontoReferencia(referencia);
	}
	
	@When("Eu informo o ponto de referencia PJ $referencia")
	public void euInformoPontoReferenciaPj(String referencia) {
		cliente.informarPontoReferenciaPj(referencia);
	}
	
	@When("Eu clico botao ok da tela Crediario Proprio")
	public void euClicoBtnOkSalvarDadosFinanceiros() {
		cliente.clicarBtnOkSalvarDadosFinanceiros();
	}
	
	@When("Eu clico botao ok da tela Crediario Proprio PJ")
	public void euClicoBtnOkSalvarDadosCredPropPj() {
		cliente.clicarBtnOkSalvarDadosCredPropPj();
	}
	
	/*
	 * Tela Comprador/representante
	 */
	@When("Eu clico aba Comprador/Representante")
	public void euClicoAbaCompradorRepresentante() {
		cliente.clicarAbaCompradorRepresentante();
	}
	
	@When("Eu clico botao novo representante")
	public void euClicoBtnNovoCompradorRepresentante() {
		cliente.clicarBtnNovoRepresentante();
	}
	
	@When("Eu informo um novo CPF do representante")
	public void euInformoUmNovoCpfRepresentante() {
		cliente.informarUmNovoCpfRepresentante();
	}
	
	@When("Eu informo nome do representante $nome")
	public void euInformoNomeRepresentante(String nome) {
		cliente.informarNomeRepresentante(nome);
	}
	
	@When("Eu informo o telefone 1 do representante $ddd $numero $ramal")
	public void euInformoTelefonePrimarioRepresentante(String ddd, String numero, String ramal) {
		cliente.informarTelefonePrimarioRepresentante(ddd, numero, ramal);
	}
	
	@When("Eu informo o telefone 2 do representante $ddd $numero $ramal")
	public void euInformoTelefoneSecundarioRepresentante(String ddd, String numero, String ramal) {
		cliente.informarTelefoneSecundarioRepresentante(ddd, numero, ramal);
	}
	
	@When("Eu informo cargo do representante $cargo")
	public void euInformoCargoRepresentante(String cargo) {
		cliente.informarCargoRepresentante(cargo);
	}
	
	@When("Eu seleciono orgao emissor do representante $orgao")
	public void EuSelecionoOrgaoEmissorRepresentante(String orgao) throws InterruptedException {
		cliente.selecionarOrgaoEmissorRepresentante(orgao);
	}
	
	@When("Eu seleciono UF do representante $uf")
	public void EuSelecionoUfdoRepresentante(String uf) throws InterruptedException {
		cliente.selecionarUfRepresentante(uf);
	}
	
	@When("Eu informo RG do representante $rg")
	public void EuInformoNroRgRepresentante(String rg) throws InterruptedException {
		cliente.informarNroRgRepresentante(rg);
	}
	
	@When("Eu seleciono a data de emissao de rg do representante $data")
	public void EuSelecionoDataDeEmissaoRgRepresentante(String data) throws InterruptedException {
		cliente.selecionarDataDeEmissaoRgRepresentante(data);
	}
	
	@When("Eu clico no botao Ok salvar representante")
	public void euClicoBtnOkSalvarRpresentante() {
		cliente.clicarBtnOkSalvarRpresentante();
	}
	
	@When("Eu clico no botao pesquisar cliente")
	public void euClicoBtnPesquisarCliente() {
		cliente.clicarBtnPesquisarCliente();
	}
	
	@When("Eu clico no radio pesquisa cliente")
	public void euClicoRadioPesquisaCliente() {
		cliente.clicarRadioPesquisaCliente();
	}
	
	/*
	 * EDIÇÃO
	 */
	@When("Eu clico no botao editar")
	public void euClicoBtnEditar() {
		cliente.clicarBtnEditar();
	}
	
	@When("Eu clico no botao editar referencia")
	public void euClicoBtnEditarReferencia() {
		cliente.clicarBtnEditarReferencia();
	}
	
	@When("Eu clico no botao editar representante")
	public void euClicoBtnEditarRepresentante() {
		cliente.clicarBtnEditarRepresentante();
	}
	
	/*
	 * OUTROS
	 */
	@When("Eu espero $tempo milisegundos")
	public void euEspero(int tempo) throws InterruptedException {
		cliente.esperar(tempo);
	}
	
}
