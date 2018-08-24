var require = patchRequire(require);

var telaProduto = require('../telas/elaborar_pedido/produtos.js');
var telaServico = require('../telas/elaborar_pedido/servicos.js');
var telaLiberacoes = require('../telas/elaborar_pedido/liberacoes.js');
var telaFormaPagamento = require('../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');

var cpf;
var pedidoVenda;

/**
 * Funções utilitárias
 */

exports.vendaAVista = function (elaboracaoPedido) {

    casper.then(function () {
        casper.echo(".. Elaborando pedido com venda a vista");
        utils.takeSS("elaborar pedido");
    });

    casper.then(function () {
        casper.then(function () {
            casper.echo(".. validando dados.");
//			utils.waitForValue("#txt-sf-cliente-pedido-text", elaboracaoPedido.nome_cliente);
            utils.waitForValue("#txt-status-pedido", elaboracaoPedido.status_pedido);
        });

        casper.then(function () {
            cpf = mvLojas.getElementValue("#txt-cpf");
        });
    });

    // setando tipo de venda como a vista
    casper.then(function () {
        casper.echo(".. Selecionando venda a vista.");
        mvLojas.$valCombo("#cbx-nro-tipo-venda", elaboracaoPedido.tipo_venda);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
        casper.echo(".. Buscando Produto.");
    });

    casper.then(function () {
        utils.takeSS("validação de dados na tela do pedido");
    });

    // chama o mapeamento de tela feito para tratar produtos
    casper.then(function () {
        telaProduto.produtos(elaboracaoPedido.produtos, true, null);
    });

    // chama a tela de mapeamento de serviços
    casper.then(function () {
        telaServico.servico(elaboracaoPedido.servicos);
    });

    casper.then(function () {
        casper.echo("");
        casper.echo(".. Definindo forma de pagamento.");
    });

    casper.then(function () {
        mvLojas.click("#tab-itens-pedido,  #tab-planilha");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        telaFormaPagamento.formasPagamento(elaboracaoPedido.planilha);
    });

    casper.then(function () {
        telaSalvarPedido.salvar(elaboracaoPedido);
    });
};

