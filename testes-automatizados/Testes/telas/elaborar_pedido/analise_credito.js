var require = patchRequire(require);

/**
 * Funções utilitárias
 */
exports.analiseCredito = function (data) {

    casper.then(function () {
        this.echo(".. Abrindo a aba Financeira");
        mvLojas.click("#modal-analise-credito #tab-analise-credito a[href='#aba-financeira']");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        abaFinanceira(data.financeira);
    });

};

function abaFinanceira(data) {

    casper.then(function () {
        this.echo(".. Clicando no botao Comunicar");
        mvLojas.click("#modal-analise-credito #btn-comunicacao");
    });

    casper.then(function () {

        this.waitForSelector("#modal-comunicacao", function () {
            utils.takeSS("modalComunicacao");
        });

    });

    casper.then(function () {
        this.echo(".. Documentos Apresentados.");
        this.echo(".. RG: " + (data.ind_identidade ? "Sim" : "Nao"));
        this.echo(".. CPF: " + (data.ind_cpf ? "Sim" : "Nao"));
        this.echo(".. Comprovante de Renda: " + (data.ind_comprovante_renda ? "Sim" : "Nao"));
    });

    casper.then(function () {
        mvLojas.$valCheck("#ckb-identidade", data.ind_identidade);
        mvLojas.$valCheck("#ckb-cpf", data.ind_cpf);
        mvLojas.$valCheck("#ckb-renda", data.ind_comprovante_renda);
    });

    casper.then(function () {
        mvLojas.$val("#txtarea-observacoes-comunicacao", data.observacao);
    });

    casper.then(function () {
        utils.takeSS("dadosComunicaoPreencidos");
    });

    casper.thne(function () {
        this.echo(".. Clicando no botao Enviar.");
        mvLojas.click("#modal-comunicacao #btn-enviar");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
        casper.wait(5000);
    });

    casper.then(function () {
        utils.takeSS("fim");
    });

}