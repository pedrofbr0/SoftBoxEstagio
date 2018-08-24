var require = patchRequire(require);

/**
 * Consulta Pedido e abre a tela de pedido
 * @param tipoVenda, int com o código do tipo de venda
 */
exports.validarCampos = function (tipoVenda) {

    var camposValidar = require('../data/validacao_tipo_venda.json');

    casper.then(function () {
        this.echo(".. Validando campos.");
        mvLojas.waitForLoad();
    });

    // valida campo Plano
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].plano, "Plano", "#cbx-plano", "disabled");
    });

    // valida campo Vr Prestação
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].vr_prestacao, "Valor Prestacao", "#txt-vr-prestacao", "disabled");
    });

    // valida campo Entrada
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].entrada, "Entrada", "#txt-entrada", "disabled");
    });

    // valida campo TAC
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].tac, "TAC", "#txt-tac", "disabled");
    });

    // valida campo 1º Vencimento
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].primeiro_vencimento, "Primeiro Vencimento", "#cbx-primeiro-vencimento", "disabled");
    });

    // valida campo Financiado Produto
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].financiado_produto, "Financiado Produto", "#txt-vr-total-produto", "readonly");
    });

    // valida campo Financiado Serviço
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].financiado_servico, "Financiado Servico", "#txt-vr-total-servico", "readonly");
    });

    // valida campo Total Pedido
    casper.then(function () {
        execultarValidacao(camposValidar[tipoVenda].total_pedido, "Total Pedido", "#txt-vr-total-pedido", "readonly");
    });

};

/**
 * Execulta validações para saber se o campo esta bloqueado ou desbloqueado indevidamente.
 * @param campo, String contendo a informação do campo, para validar se ele deve estar bloqueado ou desbloqueado
 * @param descCampo, String com a descrição do campo
 * @param selector, String com o selector do campo
 * @param attribute, String com o atibuto a ser buscado no selector (disabled, readonly e ect...)
 */
function execultarValidacao(campo, descCampo, selector, attribute) {

    var hasSelector = mvLojas.hasAttr(selector, attribute);

    casper.then(function () {
        if (campo && !hasSelector) {
            casper.test.pass("Campo '" + descCampo + "'=> DESBLOQUEADO para o tipo de venda selecionado");
        } else if (!campo && hasSelector) {
            casper.test.pass("Campo '" + descCampo + "'=> BLOQUEADO para o tipo de venda selecionado");
        } else if (campo && hasSelector) {
            casper.test.fail("Campo '" + descCampo + "'=> BLOQUEADO indevidamente");
        } else if (!campo && !hasSelector) {
            casper.test.fail("Campo '" + descCampo + "'=> DESBLOQUEADO indevidamente");
        }
    });

}