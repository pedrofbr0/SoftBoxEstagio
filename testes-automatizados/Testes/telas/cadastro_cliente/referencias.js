var require = patchRequire(require);

/**
 * Função responsável por prencher os principais dados do cliente na tela de cadastro de cliente
 * 
 * @param dadosCliente, array com os do cliente
 */
exports.cadReferencias = function (refs) {

    var qtdRefs = refs.length;
    var n = 0;

    casper.eachThen(refs, function (response) {
        (function (data) {

            n++;

            casper.then(function () {
                casper.echo("");
                casper.echo(".. Cadastrando Referências " + n + " de " + qtdRefs);
            });

            casper.then(function () {
                mvLojas.click("#aba-referencias #btn-nova-referencia");
            });

            // aguarda modal abrir
            casper.then(function () {
                mvLojas.waitModalOpenEvt("#mdl-referencia-cliente");
            });

            casper.then(function () {
                casper.echo("");
                casper.echo(".. Modal de cadastro de Referência aberta com sucesso");
            });

            casper.then(function () {

                var cpfCnpj = isNull(data.cpf_cnpj);

                // valida se a referência é um cliente, se sim busca as informações na base de dados 
                if (data.ind_cliente == 1) {


                    casper.then(function () {
                        casper.echo(".. abrindo searchfield para buscar referencia na base de dados.");
                        mvLojas.click("#btn-sf-cliente-ref-search");
                    });

                    casper.then(function () {
                        mvLojas.waitModalOpenEvt("#mdl-sf-cliente-ref");
                    });

                    casper.then(function () {
                        utils.takeSS("");
                        if (data.ind_pf == 0) {
                            mvLojas.click("#mdl-sf-cliente-ref input[data-sf-column='tipo_pessoa']");
                        }
                    });

                    casper.then(function () {
                        mvLojas.waitForLoad();
                    });

                    casper.then(function () {
                        casper.echo(".. pesquisando cliente por " + cpfCnpj ? "Nome" : "CPF?CNPJ");
                        casper.echo(cpfCnpj ? (".. Nome: " + data.nome) : (".. CPF: " + data.cpf_cnpj));
                    });

                    casper.then(function () {
                        cpfCnpj ? mvLojas.$val("#mdl-sf-cliente-ref input[data-sf-column='nome_cliente']", data.nome) : mvLojas.$val("#mdl-sf-cliente-ref input[data-sf-column='cpf']", data.cpf_cnpj);
                        mvLojas.$valCombo("#mdl-sf-cliente-ref select[data-sf-column='uf']", data.uf);
                    });

                    casper.then(function () {
                        mvLojas.click("#mdl-sf-cliente-ref .mv-sf-filter-search");
                    });

                    casper.then(function () {
                        mvLojas.waitForLoad();
                    });

                    casper.then(function () {
                        utils.takeSS("referencia_consultaSFClienteReferencia_" + n);
                    });

                    casper.then(function () {

                        var valor;

                        casper.then(function () {
                            valor = cpfCnpj ? data.nome : data.cpf_cnpj;
                        });

                        casper.then(function () {
                            if (valor.length == 11) {
                                valor = valor.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3\-\$4");
                            } else if (valor.lenght == 14) {
                                valor = valor.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, "\$1.\$2.\$3\/\$4\-\$5");
                            }
                        });

                        casper.then(function () {
                            if (mvLojas.gridLength('#grid-sf-cliente-ref') > 0) {
                                utils.takeSS('referencia_clienteEncontrado_' + n);
                                casper.test.pass('Cliente Encontrado');
                            } else {
                                takeSS("referencia_clienteNaoEncontrado_" + n);
                                casper.test.fail('Cliente não encontrado');
                            }
                        });

                        casper.then(function () {

                            if (cpfCnpj) {

                                casper.then(mvLojas.findRowTable('#grid-sf-cliente-ref', {
                                    nome_cliente: valor
                                }, function (selector) {
                                    this.mouse.click(selector);
                                    utils.takeSS("referencia_referenciaSelecionada_" + n);
                                    casper.test.pass("Cliente Selecionado.");
                                }));

                            } else {

                                casper.then(mvLojas.findRowTable('#grid-sf-cliente-ref', {
                                    cpf_cnpj: valor
                                }, function (selector) {
                                    this.mouse.click(selector);
                                    utils.takeSS("referencia_referenciaSelecionada_" + n);
                                    casper.test.pass("Cliente Selecionado.");
                                }));

                            }

                        });

                    });

                } else {

                    casper.then(function () {
                        mvLojas.$val("#txt-sf-cliente-ref-text", data.nome);
                    });

                }

            });

            casper.then(function () {
                mvLojas.$val("input[data-column='fone_referencia_ddd']", data.ddd);
                mvLojas.$val("input[data-column='fone_referencia_nro']", data.fone);
                mvLojas.$val("input[data-column='fone_referencia_ramal']", data.ramal);
                mvLojas.$valCombo("#mdl-referencia-cliente select[data-column='cod_vinculo']", data.cod_vinculo);
                mvLojas.$valCombo("#mdl-referencia-cliente select[data-column='ind_ativo']", data.cod_ativo);
            });

            casper.then(function () {
                utils.takeSS("antesSalvarReferencia");
            });

            casper.then(function () {
                mvLojas.click("#mdl-referencia-cliente #btn-salvar-referencia-cliente");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("referencia_aposInserirReferencia_" + n);
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

        })(response.data);
    });

    casper.then(function () {

        if (mvLojas.gridLength('#grd-referencias') == qtdRefs) {
            casper.test.pass('Referencias inseridas com sucesso.');
        } else {
            utils.takeSS("referencia_erroNaQuantidadeReferencia");
            casper.test.fail('Erro ao inserir referencias');
        }
    });

    casper.then(function () {
        utils.takeSS("referencia_aposInserirTodasAsReferencias");
    });

};

/**
 * Valida se uma variável ou posição de um array é vazia
 * @param element, string valor a ser comparado
 * @returns bool, retorna TRUE caso esteja vazio e FALSE caso contrário
 */
function isNull(element) {
    return element == "" || element == null || element == undefined ? true : false;
}