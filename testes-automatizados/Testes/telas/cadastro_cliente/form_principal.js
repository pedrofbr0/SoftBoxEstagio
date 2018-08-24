var require = patchRequire(require);

/**
 * Função responsável por prencher os principais dados do cliente na tela de cadastro de cliente
 * 
 * @param dadosCliente, array com os do cliente
 * @param infPf, bollean com indicador se o cliente é pessoa física ou pessoa juridica (pj = true, pf = false)
 */
exports.cadFormPrincipal = function (data, indPj) {

    var doc = indPj ? "cnpj" : "cpf";

    casper.then(function () {
        utils.waitForValue("#frm-cadastro-cliente input[data-column='" + doc + "']", data.cpf_cnpj);
    });

    casper.then(function () {
        mvLojas.$val('#txtNome', data.nome);
        mvLojas.$val("#txtEmail", data.email);
        mvLojas.$val("#txtDddResidencial", data.ddd_residencial);
        mvLojas.$val("#txtNroTelefoneResidencial", data.fone_residencial);
        mvLojas.$val("#txtRamalResidencial", data.ramal_residencial);
        mvLojas.$val("#txtDddCelular", data.ddd_celular);
        mvLojas.$val("#txtNroTelefoneCelular", data.celular);
        mvLojas.$val("#txtRamalCelular", data.ramal_celular);
        mvLojas.$valCombo("#frm-cadastro-cliente #cbx-nro-nivel-cadastro", data.grupo);

        casper.echo(".. Nome cliente: " + data.nome);
        casper.echo(".. E-mail: " + data.email);
        casper.echo(".. Fone Residencial: (" + data.ddd_residencial + ")" + data.fone_residencial + (data.ramal_residencial ? (" - ramal - " + data.ramal_residencial) : ""));
        casper.echo(".. Celular: (" + data.ddd_celular + ")" + data.celular + (data.ramal_celular ? (" - ramal - " + data.ramal_celular) : ""));

        if (indPj) {
            mvLojas.$valCombo("#frm-cadastro-cliente #cbx-nro-tipo-empresa", data.tipo_empresa);
            mvLojas.$val("#frm-cadastro-cliente input[data-column='nome_fantasia']", data.nome_fantasia);
            mvLojas.$val("#frm-cadastro-cliente input[data-column='ie']", data.inscricao_estadual);

            casper.echo(".. Tipo empresa: " + data.desc_tipo_empresa);
            casper.echo(".. Nome fantasia: " + data.nome_fantasia);
            casper.echo(".. Inscricao Estadual: " + data.inscricao_estadual);
        } else {
            mvLojas.$valCombo("#frm-cadastro-cliente #cbx-tipo-correspondencia", data.correspondencia);
        }
    });

    casper.then(function () {
        utils.takeSS("formPreenchido");
    });

    casper.then(function () {

        if (data.conceito_cliente && data.desc_conceito_cliente != mvLojas.getElementText("#btn-conceito-cliente")) {
            casper.then(function () {
                mvLojas.click("#btn-conceito-cliente");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("conceitoCliente");
            });

            casper.then(function () {
                this.echo(".. Selecionando conceito: " + data.conceito_cliente + " - " + data.desc_conceito_cliente);
                this.mouse.click("#mdl-conceito-cliente input[type='radio'][value='" + data.conceito_cliente + "']");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.waitForText("#btn-conceito-cliente", data.desc_conceito_cliente);
            });
        }
    });

    casper.then(function () {
        utils.takeSS("camposPrincipaisCadastroClientePreenchidos");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
};