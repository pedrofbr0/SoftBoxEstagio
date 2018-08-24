var require = patchRequire(require);
var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils');

/**
 * Função responsável por prencher os principais dados do cliente na tela de cadastro de cliente
 * 
 * @param dadosCliente, array com os do cliente
 */
exports.identificaoTrabalho = function (data) {

    var agora = new Date();
    var mes = (agora.getMonth() + 1) > 0 && (agora.getMonth() + 1) < 10 ? "0" + (agora.getMonth() + 1) : (agora.getMonth() + 1);
    var ano = agora.getFullYear();

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // printando informações de RG a serem inseridas na tela
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo dados da Identidade.");
        casper.echo(".. Orgão: " + data.identidade.orgao);
        casper.echo(".. UF: " + data.identidade.estado);
        casper.echo(".. Nº Identidade: " + data.identidade.rg);
        casper.echo(".. Nº Identidade: " + data.identidade.data_emissao);
    });

    // inserindo informações de RG na tela
    casper.then(function () {

        mvLojas.$valCombo("#frm-identificacao-cliente #cbx-orgao-emissor", data.identidade.cod_orgao);
        mvLojas.$valCombo("#frm-identificacao-cliente #cbx-uf", data.identidade.estado);
        mvLojas.$val("#frm-identificacao-cliente input[data-column='identidade_nro']", data.identidade.rg);
        mvLojas.$val("#frm-identificacao-cliente #identidade_dt_emissao", data.identidade.data_emissao);

    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("ident_trabalho_informacoesIdentidade");
    });

    // printando dados pessoais
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo dados pessoais.");
        casper.echo(".. Nome Mãe: " + data.dados_pessoais.nome_mae);
        casper.echo(".. Nome Pai: " + data.dados_pessoais.nome_pai);
        casper.echo(".. Data Nascimento: " + data.dados_pessoais.data_nascimento);
        casper.echo(".. Sexo: " + data.dados_pessoais.sexo);
        casper.echo(".. Nacionalidade: " + data.dados_pessoais.nacionalidade);
        casper.echo(".. Naturalidade: " + data.dados_pessoais.naturalidade_cidade + " | " + data.dados_pessoais.naturalidade_mg);
        casper.echo(".. Estado Civil: " + data.dados_pessoais.estado_civil);
        casper.echo(".. Sexo Conjuge: " + data.dados_pessoais.sexo_conjuge);
        casper.echo(".. Nome Conjuge: " + data.dados_pessoais.nome_conjuge);
    });

    // inserindo informações de RG na tela
    casper.then(function () {

        casper.then(function () {
            mvLojas.$val("#frm-identificacao-cliente input[data-column='nome_pai']", data.dados_pessoais.nome_pai);
            mvLojas.$val("#frm-identificacao-cliente #nome_mae", data.dados_pessoais.nome_mae);
            mvLojas.$val("#frm-identificacao-cliente #identidade_ddd", data.dados_pessoais.data_nascimento);
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-sexo", data.dados_pessoais.sexo);
            mvLojas.$valCombo("#frm-identificacao-cliente #flag_nacionalidade", data.dados_pessoais.nacionalidade);
        });

        casper.then(function () {
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-uf-naturalidade", data.dados_pessoais.naturalidade_mg);
        });

        casper.then(function () {
            mvLojas.waitForLoad();
        });

        casper.then(function () {
        	casper.echo("Naturalidade Cod cidade: " + data.dados_pessoais.naturalidade_cod_cidade);
        	
        	casper.wait(3000, function() {
        		mvLojas.$valCombo("#frm-identificacao-cliente #cod-cgl-naturalidade", data.dados_pessoais.naturalidade_cod_cidade); //naturalidade_cidade
        	});
            
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-estado-civil", data.dados_pessoais.cod_estado_civil);
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-sexo-conjuge", data.dados_pessoais.sexo_conjuge);
            mvLojas.$val("#frm-identificacao-cliente #nome_conjuge", data.dados_pessoais.nome_conjuge);
        });

    });

    // printando ocupapção
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo Ocupação.");
        casper.echo(".. Ocupação: " + data.trabalho.ocupacao);
        casper.echo(".. Cargo: " + data.trabalho.cargo);
        
    });

    // inserindo informações Ocupação
    casper.then(function () {
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-ocupacao", data.trabalho.cod_ocupacao);
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-profissao", data.trabalho.cod_profissao);
        //mvLojas.$val("#frm-trabalho-cliente input[data-column='desc_cargo']", data.trabalho.cargo);
        
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // printando renda principal
    casper.then(function () {

        casper.echo("");
        casper.echo(".. Renda Principal.");
        casper.echo(".. Tipo Comprovante: " + data.renda_principal.tipo_comprovante);
        casper.echo(".. Mês/Ano: " + mes + "/" + ano);
        casper.echo(".. Renda Líquida: " + data.renda_principal.renda_liquida);
        casper.echo(".. Número Benefício: " + data.renda_principal.nro_beneficio);
        casper.echo(".. Orgão Pagador: " + data.renda_principal.orgao_pagador);
    });

    // inserindo informações Ocupação
    casper.then(function () {
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-tipo-comprovante-renda", data.renda_principal.cod_tipo_comprovante);
        mvLojas.$val("#frm-trabalho-cliente #dp-comprovante-renda", (mes + "/" + ano));
        mvLojas.$val("#frm-trabalho-cliente #txt-vr-salario-cliente", data.renda_principal.renda_liquida);
        data.renda_principal.nro_beneficio != "" ? mvLojas.$val("#frm-trabalho-cliente #txt-nro-beneficio", data.renda_principal.nro_beneficio) : "";
        data.renda_principal.orgao_pagador != "" ? mvLojas.$val("#frm-trabalho-cliente #slt_beneficio_chosen", data.renda_principal.orgao_pagador) : "";
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("ident_trabalho_dadosPreencidosAteRendaPrincipal");
    });

    // inserindo informações Outra Renda
    casper.then(function () {

        if (data.outra_renda.origem != "") {
            casper.echo("");
            casper.echo(".. Outra Renda.");
            casper.echo(".. Origem: " + data.outra_renda.origem);
            casper.echo(".. Valor: " + data.outra_renda.valor);


            mvLojas.$valCombo("#frm-trabalho-cliente #cbx-origem-outra-renda", data.outra_renda.cod_origem);
            mvLojas.$val("#frm-trabalho-cliente #txt-vr-outras-rendas-cliente", data.renda_principal.valor);
        }

    });

    casper.then(function () {

        var total;
        var rendaLiquida;
        var valorOutraRenda;
        var txtTotal;

        casper.then(function () {

            rendaLiquida = mvLojas.getElementValue("#txt-vr-salario-cliente");
            valorOutraRenda = mvLojas.getElementValue("#txt-vr-outras-rendas-cliente") != "" ? mvLojas.getElementValue("#txt-vr-outras-rendas-cliente") : "0";
            txtTotal = mvLojas.getElementValue("#frm-trabalho-cliente #txt-renda-total");
            total = (parseFloat(rendaLiquida) + parseFloat(valorOutraRenda)).toFixed(2);

        });

        casper.then(function () {
            parseFloat(txtTotal) == total ? casper.test.pass("Valor total ok: " + txtTotal) : casper.test.fail("Valor total incorreto.");
        });

    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // printando renda principal
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Local Trabalho/dados financeira.");
        casper.echo(".. Local: " + data.local_trabalho.local);
        casper.echo(".. CEP: " + data.local_trabalho.cep);
        casper.echo(".. Endereço: " + data.local_trabalho.endereco + ", n" + data.local_trabalho.numero);
        casper.echo(".. Bairro: " + data.local_trabalho.bairro);
        casper.echo(".. Cidade: " + data.local_trabalho.cidade);
        casper.echo(".. Estado: " + data.local_trabalho.estado);
        casper.echo(".. Fone: (" + data.local_trabalho.ddd + ") " + data.local_trabalho.fone + " " + data.local_trabalho.ramal);
        casper.echo(".. Trabalha desde: " + data.local_trabalho.trabalha_desde);
    });

    casper.then(function () {
        mvLojas.$val("#frm-trabalho-cliente input[data-column='local_trabalho_cliente']", data.local_trabalho.local);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='cep_comercial']", data.local_trabalho.cep);
        mvLojas.$val("#frm-trabalho-cliente #txt-endereco-trabalho-cliente", data.local_trabalho.endereco);
        mvLojas.$val("#frm-trabalho-cliente #txt-numero-trabalho-cliente", data.local_trabalho.numero);
        mvLojas.$val("#frm-trabalho-cliente #txt-complemento-trabalho-cliente", data.local_trabalho.complemento);
        mvLojas.$val("#frm-trabalho-cliente #txt-bairro-trabalho-cliente", data.local_trabalho.bairro);
        mvLojas.$val("#frm-trabalho-cliente #txt-cidade-trabalho-cliente", data.local_trabalho.cidade);
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-uf-local-trabalho", data.local_trabalho.estado);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='fone_trabalho_ddd']", data.local_trabalho.ddd);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='fone_trabalho_nro']", data.local_trabalho.fone);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='fone_trabalho_ramal']", data.local_trabalho.ramal);
        mvLojas.$val("#frm-trabalho-cliente #dp-trabalha-desde", data.local_trabalho.trabalha_desde);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("ident_trabalho_telaPreencida");
    });

    casper.then(function () {
        casper.echo(".. clicando no botão OK para confirmar informações inseridas.");
    });

    casper.then(function () {
        mvLojas.click("#btn-salvar-trabalho");
    });

    casper.then(function () {
        casper.echo(".. salvando informações Identificação/Trabalho.");
    });

    casper.then(function () {
        mvLojas.waitModalCloseEvt("#mdl-trabalho");
    });

};