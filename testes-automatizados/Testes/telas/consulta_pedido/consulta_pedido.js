/**
 * Consulta Pedido e abre a tela de pedido
 * @param data, array com os dados do pedido
 */
exports.consultarPedido = function (data) {

	casper.then(function() {
		if (data.cod_empresa) {
			this.echo(".. Informando empresa do pedido: " + data.nro_pedido);
			mvLojas.$valCombo("#cbx-empresa-cons-ped", data.cod_empresa);
		}
	});
	
	casper.then(function() {
		if (data.loja_pedido) {
			mvLojas.$val("#sf-loja-cons-ped input:first", data.loja_pedido);
			casper.wait(3000);
		}
	});
	
    casper.then(function () {
        this.echo(".. Consultando pedido: " + data.nro_pedido);
        mvLojas.$val("#fm-consulta-pedido #txt-pedido", data.nro_pedido);
    });
	

    casper.then(function () {
        utils.takeSS("pedidoInformado");
        mvLojas.click("#btn-consultar-pedido");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {

        utils.takeSS("consultaPedido");

        if (mvLojas.gridLength('#grid-pedido') > 0) {
            this.test.pass("Pedido encontrado");
            utils.takeSS("pedidoEncontrado");
        } else {
            this.test.pass("Pedido nao selecionado");
            utils.takeSS("pedidoNaoEncontrado");
        }
    });

    casper.then(function () {
        this.clickLabel(data.nro_pedido, 'button');
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.waitForSelector("#modal-elabora-pedido", function () {
            this.test.pass("Tela de pedido foi aberta com sucesso");
            utils.takeSS("telaPedido");
        });
    });

};

/**
 * reabre pedido, depois que o pedido ja esta aberto na modal de pedido.
 * @param data, array com os dados do pedido  
 */
exports.reabrirPedido = function (data) {

    casper.then(function () {
        this.echo("");
        this.echo("===================================================> REABERTURA DO PEDIDO.");
        this.echo(".. Reabrindo pedido: " + data.nro_pedido);
        mvLojas.click("#btn-reabertura-pedido");
    });

    casper.then(function () {
        this.waitForText("Realizar processo de reabertura do pedido!", function () {
            casper.test.pass("Realizar processo de reabertura do pedido! Deseja Prosseguir?");
            utils.takeSS("msgReaberturaPedido");
        });
    });

    casper.then(function () {
        this.echo(".. Confirmando reabertura do pedido");
        mvLojas.click(".modal.alert.in .btn.Sim");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
    	utils.takeSS("reaberturaConfirmada");
        this.waitForText("Pedido " + data.nro_pedido, function () {
            casper.test.pass("Pedido " + data.nro_pedido + "! <br> Status: 0 - Em Elaboracao");
            utils.takeSS("msgPedidoAberto");
            mvLojas.click(".modal.in:not(#modal-elabora-pedido) .btn.Ok");
        });
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("pedidoReaberto");
    });

};

/**
 * Função responsável por buscar um cliente existente na base de dados
 * @param data, array com os dados do cliente a ser informado
 */
function selecionarCliente(data) {

    casper.then(function () {
        this.echo(".. Clicando no botão para abrir SearchField de cliente");
        mvLojas.click("#sf-cliente .mv-sf-btn-search");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.waitForText("Pesquisa de Clientes", function () {
            casper.test.pass("SearchField de cliente, aberta com sucesso.");
            utils.takeSS("searchFieldCliente");
        });
    });

    casper.then(function () {
        this.echo(".. Informando dados de um cliente " + (data.ind_cpf ? "PESSOA FISICA" : "PESSOA JURIDICA"));

        mvLojas.$valCheck("input[data-sf-column='tipo_pessoa']", data.ind_pf);
        mvLojas.$val("input[data-sf-column='nome_cliente']", data.nome);
        mvLojas.$val("input[data-sf-column='cpf']", data.cpf_cnpj);
        mvLojas.$valCombo("select[data-sf-column='UF']", data.uf);
        mvLojas.$valCombo("select[data-sf-column='cod_cgl']", data.cidade);
    });

    casper.then(function () {
        mvLojas.click(" id ou classe do botão !!!!!!!!!! ");
    });

}