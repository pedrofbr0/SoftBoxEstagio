var require = patchRequire(require);
var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils');

/**
 * Função responsável por prencher os principais dados do cliente na tela de cadastro de cliente
 * 
 * @param dadosCliente, array com os do cliente
 */
exports.cadCartao = function (data) {

    casper.then(function () {
        casper.echo(".. Abrindo aba para solicitar Cartão MV Shop");
    });

    casper.then(function () {
        mvLojas.click("#tab-cadastro-cliente #lnk-modal-cartao-mvshop");
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#mdl-cartao-mvshop");
    });

    casper.then(function () {
        utils.takeSS("mv_shop_modalMVShop");
    });

    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo informações do cartão");
    });

    casper.then(function () {
        mvLojas.$valCombo("#frm-cartao-mvshop select[data-column='dia_vencto']", data.dia_vencimento);
    });

    casper.then(function () {
        mvLojas.$valCombo("#frm-cartao-mvshop select[data-column='tipo_endereco_fatura']", data.cod_endereco_fatura);
    });

    casper.then(function () {
        mvLojas.$val("#frm-cartao-mvshop input[data-column='nome_impresso_cartao']", data.nome_impressao_cartao);
    });

    casper.then(function () {
        if (data.dependentes.lenght > 0) {
            casper.echo("dependentes");
        }
    });

    casper.then(function () {
        utils.takeSS("mv_shop_modalMVShopFormPreenchido");
    });

    casper.then(function () {
        mvLojas.click("#mdl-cartao-mvshop #btn-salvar-cartao");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

};