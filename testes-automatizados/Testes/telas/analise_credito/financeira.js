var require = patchRequire(require);
var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils');

/**
 * Funções utilitárias
 */
exports.financeira = function (data) {

    casper.then(function () {
        casper.echo(".. Clicando no botão Comunicar");
        mvLojas.click("#modal-analise-credito #geral #btn-comunicacao");
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#modal-comunicacao");
    });

    casper.then(function () {
        mvLojas.$valCheck("#form-comunicacao #ckb-identidade", data.comunicacao.ind_rg);
        mvLojas.$valCheck("#form-comunicacao #ckb-cpf", data.comunicacao.ind_cpf);
        mvLojas.$valCheck("#form-comunicacao #ckb-renda", data.comunicacao.ind_comprovate_renda);
    });

    casper.then(function () {
        mvLojas.$val("#txtarea-observacoes-comunicacao", data.comunicacao.observacao);
    });

    casper.then(function () {
        casper.echo(".. Clicando no botão Enviar");
        mvLojas.click("#modal-comunicacao #btn-enviar");
        utils.takeSS("comunicacaoEnviar");
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
	    this.wait(50000, function () {
	        casper.echo(".. Tempo de espera finalizado.");
	        utils.takeSS("esperaComunicacao");
	    });
    });
    
    casper.then(function () {
        casper.echo(".. Clicando no botão Comunicar");
        mvLojas.click("#modal-analise-credito #geral #btn-comunicacao");
    });
    
    casper.then(function () {
        mvLojas.waitModalOpenEvt("#modal-comunicacao");
    });
    
    casper.then(function () {
        casper.echo(".. Clicando no botão Consultar");
        mvLojas.click("#modal-comunicacao #btn-consulta-comunicacao");
        utils.takeSS("comunicacaoEnviar");
    });
    
};