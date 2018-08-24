var require = patchRequire(require);

/**
 * Funções utilitárias, que seleciona um cliente padrão usado para testes
 */
exports.selecionarCliente = function (banco) {

    var cpf;

    casper.echo(".. Banco: " + banco);
    
    if (banco == "RELOH" || banco == "RELOH_EXA") {
    	//cpf = "000.000.001-91";
    	cpf = "377.786.705-50";
    	//cpf = "339.179.793-22"; // Usado na validação de motivos
    } else if (banco == "RELOH2") {
        cpf = "317.833.095-04";
    } else if (banco == "RELOH_BA") {
    	cpf = "258.406.575-49"; // CPF da BA para geracao de NFCe
    } else if (banco == "MVSH") {
    	cpf = "695.842.263-55"; // Michelle Cavalheiri
    } else {
        cpf = "000.000.001-91";
    }

    casper.then(function () {
        this.echo('.. Pesquisando Cliente');
        mvLojas.$valCheck("#ckb-tipo-pessoa", true);
    });

    casper.then(function () {
        mvLojas.$val("#form-consulta-cliente #cbx-cpf", cpf);
    });

    casper.then(function () {
        mvLojas.click("#btn-pesquisar");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.then(mvLojas.findRowTable('#gridCliente', {
            cpf_cnpj: cpf
        }, function (selector) {
            this.mouse.click(selector);
            this.test.pass("Cliente encontrado: " + cpf);
        }));
    });

    casper.then(function () {
        utils.takeSS("clienteSelecionado");
        
    });

    casper.then(function () {
    	this.echo(".. Abrindo modal de pedidos");
        mvLojas.click("#btn-elaborar-pedido");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.waitForSelector("#modal-elabora-pedido", function () {
            utils.takeSS("modalElaboraPedidoAberta");
        });
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    });

};

/**
 * Funções utilitárias, que seleciona um cliente padrão (pessoa jurídica) usado para testes
 */
exports.selecionarClientePj = function (banco) {

    var cnpj = 0;

    if (banco == "LOCALHOST" || banco == "RELODEV") {
        cnpj = "14.653.632/0001-69";
    } else if (banco == "RELOH") {
        cnpj = "51.727.323/0001-12";
    }

    casper.then(function () {
        this.echo('.. Pesquisando Cliente');
        mvLojas.$valCheck("#ckb-tipo-pessoa", false);
    });

    casper.then(function () {
        mvLojas.$val("#form-consulta-cliente #cbx-cnpj", cnpj);
    });
    
    casper.then(function () {
        utils.takeSS("cnpj");
        mvLojas.click("#btn-pesquisar");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.then(mvLojas.findRowTable('#gridCliente', {
            cpf_cnpj: cnpj
        }, function (selector) {
            this.mouse.click(selector);
            this.test.pass("Cliente encontrado: " + cnpj);
        }));
    });

    casper.then(function () {
        utils.takeSS("clienteSelecionado");
    });

    casper.then(function () {
        this.echo(".. Abrindo modal de pedidos");
        mvLojas.click("#btn-elaborar-pedido");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.waitForSelector("#modal-elabora-pedido", function () {
            utils.takeSS("modalElaboraPedidoAberta");
        });
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    });

};