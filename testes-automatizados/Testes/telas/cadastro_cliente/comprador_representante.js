var require = patchRequire(require);
var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils');

/**
 * Função responsável por prencher os principais dados do cliente na tela de cadastro de cliente
 * 
 * @param dadosCliente, array com os do cliente
 * @param infPf, bollean com indicador se o cliente é pessoa física ou pessoa juridica (pf = true, pj = false)
 */
exports.cadCompradorRepresentante = function (params) {

    var cpf;

    casper.then(function () {
        this.echo(".. Abrindo a aba para cadastro do Comprador/Representante");
        mvLojas.click("#tab-cadastro-cliente #lnk-representante");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("abaCompradorRepresentante");
    });


    casper.eachThen(params, function (response) {
        (function (data) {

            casper.then(function () {
                cpf = mvLojas.gerarCPF();
            });

            casper.then(function () {
                data.cpf = cpf;
            });

            casper.then(function () {
                this.echo(".. Clicando no botão para adicionar um Comprador/Representante");
                mvLojas.click("#aba-representante #btn-novo-representante");
            });

            casper.then(function () {
                this.waitForSelector('#mdl-representante', function () {
                    utils.takeSS("modalAberta");
                });
            });

            casper.then(function () {
                this.echo('.. Preenchendo formulário.');
                this.echo(".. Nome: " + data.nome);
                this.echo(".. CPF: " + data.cpf);
                this.echo(".. Fone Residencial: (" + data.ddd_fone1 + ")" + data.nro_fone1 + (data.ramal_fone1 ? (" - ramal - " + data.ramal_fone1) : ""));
                this.echo(".. Celular: (" + data.ddd_fone2 + ")" + data.nro_fone2 + (data.ramal_fone2 ? (" - ramal - " + data.ramal_fone2) : ""));
                this.echo(".. Cargo: " + data.cargo);
                this.echo(".. Data Admissão: " + data.data_admissao);
                this.echo("");
                this.echo(".. Dados RG");
                this.echo(".. Estado: " + data.rg.uf);
                this.echo(".. Orgao: " + data.rg.orgao);
                this.echo(".. Numero: " + data.rg.nro);
                this.echo(".. Data Emissao : " + data.rg.data_emissao);
            });

            casper.evaluate(function (s) {
                $(s).css("margin-left", "-20px");
            }, "#frm-representante input[data-column='identidade_nro']");
            
            casper.then(function () {
                mvLojas.$val("#frm-representante input[data-column='cpf']", data.cpf);
                mvLojas.$val("#frm-representante input[data-column='nome']", data.nome);
                //fone 1
                mvLojas.$val("#frm-representante input[data-column='fone_cliente_ddd_1']", data.ddd_fone1);
                mvLojas.$val("#frm-representante input[data-column='fone_cliente_nro_1']", data.nro_fone1);
                mvLojas.$val("#frm-representante input[data-column='fone_cliente_ramal_1']", data.ramal_fone1);
                //fone 2
                mvLojas.$val("#frm-representante input[data-column='fone_cliente_ddd_2']", data.ddd_fone2);
                mvLojas.$val("#frm-representante input[data-column='fone_cliente_nro_2']", data.nro_fone2);
                mvLojas.$val("#frm-representante input[data-column='fone_cliente_ramal_2']", data.ramal_fone2);

                mvLojas.$val("#frm-representante input[data-column='cargo']", data.cargo);
                mvLojas.$val("#frm-representante #dt_admissao", data.data_admissao);
                //rg
                //mvLojas.$valCombo("#frm-representante #cbx-uf-referente", data.rg.uf);
                
                //mvLojas.$val("#frm-representante input[data-column='identidade_orgao_emissor']", data.rg.orgao);
                
                casper.echo("Orgao: " + data.rg.orgao);
                /*
                casper.evaluate(function () {
                	document.getElementById("cbx-orgao-emissor").value = "1";
	                
	                $("#frm-representante #cbx-orgao-emissor").trigger('chosen:updated');
	                $("#frm-representante #cbx-orgao-emissor").trigger('liszt:updated');
                });
                */
                mvLojas.$valCombo("#frm-representante #cbx-orgao-emissor", data.rg.cod_orgao);
                
                /*casper.evaluate(function (texto) {
                	 $("#frm-representante #cbx_orgao_emissor").click();
             		 //$("#frm-representante #cbx_orgao_emissor .chosen-drop .chosen-search input").clear();
             		 $("#frm-representante #cbx_orgao_emissor .chosen-drop .chosen-search input").sendKeys(texto);
             		 $("#frm-representante #cbx_orgao_emissor .chosen-results .active-result").click();
                }, "CARTO - CART DO REG CIVIL");
               */
                
                mvLojas.$valCombo("#frm-representante #cbx-uf-comprador", data.rg.uf);
                
                mvLojas.$val("#frm-representante input[data-column='identidade_nro']", data.rg.nro);
                mvLojas.$val("#frm-representante #identidade_dt_emissao", data.rg.data_emissao);

            });

            casper.then(function () {
                utils.takeSS("formCompradorPreenchido");
            });

            casper.then(function () {
                mvLojas.click("#mdl-representante #btn-salvar-representante");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("compradorPreenchidoInserido");
            });

        })(response.data);
    });


};