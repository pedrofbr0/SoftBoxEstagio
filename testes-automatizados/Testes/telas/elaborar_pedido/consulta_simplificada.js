var require = patchRequire(require);

/**
 * Função utilitária responsável por fazer consulta de um cliente por um cpf na tela de consulta simplificada 
 * @param data, array com os dados do cliente
 */
exports.consultaSimplificada = function (data) {

    var cpf = data.cpf;
    var dataNascimento = data.data_nascimento;
    var tipoVenda = data.tipo_venda;

    casper.then(function () {
        this.echo(".. Clicando no botão de Consulta Simplificada.");
        mvLojas.click("#btn-credito-preaprovado");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.waitForSelector("#mdl-cps", function () {
            casper.test.pass("Modal de Consulta Simplificada aberta.");
            utils.takeSS("modalConsultaSimplificada");
        });
    });

    casper.then(function () {
        this.echo(".. Preenchendo Informacoes");
        this.echo(".. CPF: " + data.cpf);
        this.echo(".. Data de Nascimento: " + data.data_nascimento);
        this.echo(".. Tipo de Venda: " + data.desc_tipo_venda);
    });

    casper.then(function () {
        data.cpf = null;
        data.data_nascimento = null;
        data.tipo_venda = null;
    });

    casper.then(function () {
        mvLojas.$val("#frm-cps #txt-cpf", data.cpf);
        mvLojas.$val("#frm-cps #txt-data-nascimento", data.data_nascimento);
        mvLojas.$valCombo("#frm-cps #cbx-tipos-venda", data.tipo_venda);
    });

    casper.then(function () {
        this.echo(".. Enviando proposta.");
        mvLojas.click("#tb-cps #btn-enviar-cps");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    //valida o cpf não informado
    casper.then(function () {
        utils.takeSS();
        casper.waitForText("Favor informar o CPF para pesquisa", function () {
            casper.test.pass("Validacao CPF nao informado validada com sucesso! 'Favor informar o CPF para pesquisa'");
            utils.takeSS("cpfNaoInformado");
            mvLojas.click(".modal.alert.in .btn.Ok");
            data.cpf = cpf;
            mvLojas.$val("#frm-cps #txt-cpf", data.cpf);
        });
    });

    casper.then(function () {
        this.echo(".. Enviando proposta.");
        mvLojas.click("#tb-cps #btn-enviar-cps");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    //valida data de nascimento não informada
    casper.then(function () {
        utils.takeSS();
        casper.waitForText("Favor informar o data de nascimento para pesquisa", function () {
            casper.test.pass("Validacao Data Nascimento nao informado validada com sucesso! 'Favor informar o data de nascimento para pesquisa'");
            utils.takeSS("cpfDataNascimentoNaoInformado");
            mvLojas.click(".modal.alert.in .btn.Ok");
            data.data_nascimento = dataNascimento;
            mvLojas.$val("#frm-cps #txt-data-nascimento", data.data_nascimento);
        });
    });

    casper.then(function () {
    	utils.takeSS("cpfDataNascimentoInformado");
    });
    
    casper.then(function () {
        this.echo(".. Enviando proposta.");
        mvLojas.click("#tb-cps #btn-enviar-cps");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    //valida tipo de venda não informado
    casper.then(function () {
        utils.takeSS();
        casper.waitForText("É NECESSÁRIO SELECIONAR O TIPO DE VENDA PARA REALIZAR O ENVIO DA CONSULTA", function () {
            casper.test.pass("Validacao Tipo de Venda nao informado validada com sucesso! 'É NECESSÁRIO SELECIONAR O TIPO DE VENDA PARA REALIZAR O ENVIO DA CONSULTA.'");
            utils.takeSS("cpfTipoVendaNaoInformado");
            mvLojas.click(".modal.alert.in .btn.Ok");
            data.tipo_venda = tipoVenda;
            mvLojas.$valCombo("#frm-cps #cbx-tipos-venda", data.tipo_venda);
        });
    });

    casper.then(function () {
        this.echo(".. Enviando proposta.");
        mvLojas.click("#tb-cps #btn-enviar-cps");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    /*casper.then(function () {
        utils.takeSS();
        casper.then(mvLojas.findRowTable('#grid-cps', {
            tipo_venda: data.desc_tipo_venda,
            mensagem: data.ret_enviar_msg,
        }, function (selector) {
            this.mouse.click(selector);
            utils.takeSS("consultando");
        }));
    });*/

    casper.then(function () {
        this.echo(".. Enviando dados para consulta. O tempo de espera sera de 120 segundos!");
        this.echo(".. Aguardando...");
        this.wait(120000, function () {
            casper.echo(".. Tempo de espera finalizado.");
            utils.takeSS("retornoConsultaSimplificada");
        });
    });

    casper.then(function () {
        mvLojas.click("#mdl-cps #btn-consultar-cps");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS();
        this.waitForText("PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!", function () {
            casper.test.pass("PROCESSO DE CONSULTA DADOS CLIENTE FINALIZADO!");
            utils.takeSS("consultaFinalizada");
            mvLojas.click(".modal.alert.in .btn.Ok");
        });
    });

    casper.then(function () {
        utils.takeSS();
        casper.then(mvLojas.findRowTable('#grid-cps', {
            tipo_venda: data.desc_tipo_venda,
            nro_status: data.ret_concluido_avaliacao
        }, function (selector) {
            this.mouse.click(selector);
            this.test.pass("CPF Elegível");
            utils.takeSS("consultando");
        }));
    });

    casper.then(function () {

        if (data.ind_elaborar_pedido) {

            casper.then(function () {
                mvLojas.click("#tb-cps #btn-elaborar-cps");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS();
                this.waitForSelector("#modal-elabora-pedido", function () {
                    casper.test.pass("Modal de pedido Aberta com sucesso.");
                    utils.takeSS("modalPedido");
                });
            });

        } else {

            casper.then(function () {
                mvLojas.click("#tb-cps #btn-fechar-cps");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

        }
    });
};