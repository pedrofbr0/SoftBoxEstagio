var require = patchRequire(require);
var qtdValidacoesFechamentoTotal = 0;

exports.salvar = function (pedido) {

    casper.then(function () {
        casper.echo(".. Salvando pedido");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.click("#modal-elabora-pedido #btn-salvar-pedido");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.waitForText("salvo com sucesso.", function success() {
            casper.test.pass("Pedido salvo com sucesso.");
            utils.takeSS("pedidoSalvoComSucesso");
            mvLojas.click(".modal.alert.in .btn.Ok");
        }, function fail() {
            utils.takeSS('salvar_erroAoSalvarPedido');
            casper.test.fail("Erro ao salvar pedido.");
        });
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.waitForValue("#modal-elabora-pedido #txt-status-pedido", pedido.statusAtualizadoAposSalvarPedido);
    });

    // alterado para redirecionamento login
    casper.then(function () {
        pedidoVenda = mvLojas.getElementValue("#mdl-elabora-pedido #txt-pedido-venda");
    });
    
    casper.then(function () {
        mvLojas.click("#modal-elabora-pedido #btn-fechamento-total-pedido");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function (){
    	if(pedido.ajustar_datas_divergentes)
    		ajustarDatasDivergentes();
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });

    /*casper.then(function () {
        utils.takeSS("salvar_aposSalvarPedido");
    });*/

    casper.wait(3000, function () {
    	casper.echo("Esperando a tela carregar!!");
    	
    	casper.echo(pedido.msg_success_ao_finalizar);
    	
    	casper.waitForText(pedido.msg_success_ao_finalizar, function success() {
            casper.test.pass(pedido.msg_success_ao_finalizar);
            
            utils.takeSS("salvar_aposSalvarPedido");

            if (pedido.ind_analise_credito) {
                var btn = pedido.btn_confirme_analise ? ".modal.in .btn.Sim" : ".modal.in .btn.Não";
                casper.echo(".. " + btn);
                mvLojas.click(btn);
            } else {
                mvLojas.click(".modal.in .btn.Ok");
                casper.echo("Clicar em OK");
            }
        }, function fail() {
            utils.takeSS('salvar_erroEfetuarFechamentoTotalPedido');
            casper.test.fail("Erro ao efetuar fechamento total do pedido.");
        });
    	
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("salvar_aposConfirmarFechamentoTotalPedido");
    });

    // alterado para redirecionamento login
    /*casper.then(function () {
        utils.waitForValue("#modal-elabora-pedido #txt-status-pedido", pedido.statusAposFechamentoTotalPedido);
    });*/

    casper.then(function () {
        utils.takeSS("salvar_aposFechamentoTotalPedido");
    });

    casper.then(function () {
        casper.echo(".. Pedido Venda: " + pedidoVenda);
    });
    
};

function ajustarDatasDivergentes() {
	
	caper.then(function () {
		casper.echo("Selecionando endereco com datas agendadas pendentes.");
		mvLojas.click("#modal-ajusta-datas-divergentes #ckb-seleciona-endereco-1");
	});
	
	casper.then(function () {
        utils.takeSS("endereco_de_datas_agendadas_selecionado");
    });
	
	casper.then(function () {
		casper.echo("Manter entregas em dias diferentes.");
		mvLojas.click("#modal-ajusta-datas-divergentes #ckb-manter-datas-dias-diferentes");
	});
	
	casper.then(function () {
        utils.takeSS("manter_entrega_dias_diferentes");
    });
	
	casper.then(function () {
		casper.echo("Salvar ajuste.")
		mvLojas.click("#modal-ajusta-datas-divergentes #btn-salvar-data-escolhida")
	});
}