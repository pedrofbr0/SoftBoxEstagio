/*@include de funções utilitárias*/

var require = patchRequire(require);
var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils.js');

var dadosPedido = require('../data/pedido_pessoa_juridica.json');
var dadosConexao = require('../data/conexao.json');
var dadosCliente = require('../data/cadastro_cliente_pj.json');

var cadastroCliente = require('../telas/cadastro_cliente.js');
var elaborarPedido = require('../telas/elaborar_pedido/elaboracao_pedido.js');

// cad cliente
var telaSelecionarCliente = require('../telas/selecionarClientePadrao.js');
var telaCadClienteFormPrincipal = require('../telas/cadastro_cliente/form_principal.js');
var telaCompradorRepresentante = require('../telas/cadastro_cliente/comprador_representante.js');
var telaCadEndereco = require('../telas/cadastro_cliente/enderecos.js');
var telaCadReferencias = require('../telas/cadastro_cliente/referencias.js');
var telaSalvarCliente = require('../telas/cadastro_cliente/salvar_cliente.js');
// pedido
var telaProduto = require('../telas/elaborar_pedido/produtos.js');
var telaServicos = require('../telas/elaborar_pedido/servicos.js');
var telaLiberacoes = require('../telas/elaborar_pedido/liberacoes.js');
var telaPlanilhas = require('../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');
var telaAnaliseCredito = require('../telas/elaborar_pedido/analise_credito.js');

var cpf;
var nivelCadastro;


var bd = "RELODEV"; //"MVSH", "INLOJAD", "RELODEV"
/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboração de pedido pessoa jurídica e pagamento com boleto bancário.', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.eachThen(dadosConexao, function (response) {
        (function (data) {

            if (data.executar_teste) {
                casper.echo("");
                casper.echo('INICIANDO EXECUACAO TESTE DA EMPRESA: ' + data.desc_empresa, 'INFO');

                execTest(data);
            }

        })(response.data);
    });

    //execucao da suite de testes
    casper.run(function () {
        test.done();
    });

});


/**
 * função responsável por rodar os testes sequencialmente
 * @param data, array com informações de onde o teste será executado
 */
function execTest(data) {

    var cnpj;
    var cpf;

    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });

    /*casper.then(function () {
        cadastroCliente.cadatroClientePessoaJuridica(dadosCliente[data.banco]);
    });*/
    
    casper.then(function () {
    	telaSelecionarCliente.selecionarClientePj(data.banco);
	});

    //TODO CÓDIGO PARA RODAR TESTE SEM A NECESSIDADE DE CADASTRAR UM CLIENTE

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    });

    casper.then(function () {
        utils.takeSS("modaPedidoAberta");
    });

    casper.then(function () {
        this.echo(".. Validando tipo de venda.");
        utils.waitForText("#cbx_nro_tipo_venda_chosen a span", dadosPedido[data.banco].desc_tipo_venda);
    });

    casper.then(function () {
        this.echo(".. Inserindo Produto.");
    });

    casper.then(function () {
        telaProduto.produtos(dadosPedido[data.banco].produtos, true);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.echo(".. Clicando na aba de planilhas");
        mvLojas.click("#tab-itens-pedido #tab-planilha");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido[data.banco].planilhas);
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

    casper.then(function () {
        casper.echo(".. Teste cadastro de cliente Pessoa Juridica e elabaração de pedido com Pessoal Juridica, finalizado com sucesso.");
    });

}