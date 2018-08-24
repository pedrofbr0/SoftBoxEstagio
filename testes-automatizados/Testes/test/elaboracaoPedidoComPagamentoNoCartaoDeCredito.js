/*@include de funções utilitárias*/

var require = patchRequire(require);
var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils');

var dadosCliente = require('../data/cliente_existente_compra_cartao_credito.json');
var dadosPedido = require('../data/pedido_cartao_credito.json');
var dadosConexao = require('../data/conexao.json');

var cadastroCliente = require('../telas/cadastro_cliente.js');
var telaProduto = require('../telas/elaborar_pedido/produtos.js');
var telaPlanilha = require('../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');
var telaServicos = require('../telas/elaborar_pedido/servicos.js');

var cpf;
var nivelCadastro;

var bd = "RELODEV"; //"MVSH", "INLOJAD", "RELODEV"

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboração de pedido com pagamento no cartão de créditos', 0, function suite(test) {


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

    casper.run(function () {
        test.done();
    });

});

function execTest(data) {

    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });

    casper.then(function () {
        casper.echo(".. Elaborar pedido com venda no cartão de crédito");
    });

    casper.then(function () {
        mvLojas.click("#btn-elaborar-pedido");
        casper.echo(".. Clicando no botão para elaborar pedido");
        casper.echo(".. Aguardando a modal de elaboração de pedido ser aberta");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // aguarda a modal aparecer na tela
    casper.then(function () {
        utils.takeSS("");
        mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    });

    casper.then(function () {
        casper.echo(".. Preencher formulario principal");
    });

    casper.then(function () {
        casper.echo(".. validando dados.");
        utils.waitForValue("#txt-status-pedido", dadosPedido[data.banco].status_pedido);
    });

    // setando tipo de venda Cartão de Crédito
    casper.then(function () {
        casper.echo(".. Selecionando o tipo de venda: " + dadosPedido[data.banco].desc_tipo_venda);
        mvLojas.$valCombo("#cbx-nro-tipo-venda", dadosPedido[data.banco].tipo_venda);
    });

    casper.then(function () {
        utils.takeSS("aposSelecionarFormaPagamento");
    });

    casper.then(function () {
        telaProduto.produtos(dadosPedido[data.banco].produtos, false, dadosCliente[data.banco]);
    });

    casper.then(function () {
        casper.echo(".. Inserindo valor da entrada: R$ " + dadosPedido[data.banco].valor_entrada);
        mvLojas.$val("#frm-pedido #txt-entrada", dadosPedido[data.banco].valor_entrada);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("aposInserirValor");
    });

    casper.then(function () {
        casper.echo(".. Ajustando serviço do pedido de compra");
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido[data.banco].servicos);
    });

    casper.then(function () {
        mvLojas.click("#tab-itens-pedido,  #tab-planilha");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
        utils.takeSS("abaPlanilhaAberta");
    });

    casper.then(function () {
        casper.echo("");
        casper.echo(".. Definindo forma de pagamento");
        telaPlanilha.formasPagamento(dadosPedido[data.banco].planilha);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

    casper.then(function () {
        casper.echo(".. Teste de compra com pagamento pelo cartão de crédito realizado com sucesso.");
    });

}