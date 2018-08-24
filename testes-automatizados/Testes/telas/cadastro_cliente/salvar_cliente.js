var require = patchRequire(require);

/**
 * Função responsável por prencher os principais dados do cliente na tela de cadastro de cliente
 * 
 * @param dadosCliente, array com os do cliente
 */
exports.salvar = function () {

    casper.then(function () {
        mvLojas.click("#btn-salvar-cliente");
    });

    casper.then(function () {
        casper.echo(".. Salvando Cliente");
    });

    //casper.then(function () {
     //   this.wait(3000);
   // });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("salvar_cliente_antesConfirmarClienteSalvoSucesso");
    });

    // valida se existe a mensagem informando que o cliente foi salvo.
    casper.then(function () {
        casper.waitForText("Cliente salvo com sucesso!", function success() {
            casper.test.pass("Cliente salvo com sucesso!.");
            casper.click('div.modal.in, btn');
        }, function fail() {
            utils.takeSS('salvar_cliente_erroAoSalvarCliente');
            casper.test.fail("Erro ao salvar cliente.");
        });
    });

    casper.then(function () {
        mvLojas.click('div.modal.in, btn');
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("salvar_cliente_aposSalvarCliente");
    });
};