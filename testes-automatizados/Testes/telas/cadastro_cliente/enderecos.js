var require = patchRequire(require);

/**
 * Função responsável por cadastrar o endereço do cliente
 * 
 * @param dadosCliente array com os dados do cliente
 * @param n inteiro que contem a sequencia do endereço que esta sendo salvo, ex(endereco n(1), endereco n(2), ...)
 */
exports.cadastrarEndereco = function (enderecos) {

    var n = 0;

    casper.then(function () {
        mvLojas.click("#mdl-cadastro-cliente a[href='#aba-endereco']");
    });

    casper.then(function () {
        casper.eachThen(enderecos, function (response) {
            (function (data) {

                n++;

                casper.then(function () {
                    mvLojas.click("#btn-novo-endereco");
                });

                casper.then(function () {
                    mvLojas.waitModalOpenEvt("#mdl-pesquisa-endereco");
                });

                casper.then(function () {
                	casper.echo("... CEP: " + data.cep);
                    if (data.cep == "" || data.cep == undefined || data.cep == null) {
                        mvLojas.$val("#txt-nome-cidade", data.cidade);
                    } else {
                        mvLojas.$val("#txt-cep-pesquisa-endereco", data.cep);
                    }

                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

                casper.then(function () {
                    mvLojas.click("#btn-pesquisar-endereco");
                    casper.echo(".. pesquisando endereço");
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });
                
                casper.then(function () {
                	casper.echo("... Esperando 4 segundos.");
                	casper.wait(4000);
                });
                
                //Verifica se foi encontrado algum endereço
                casper.then(function () {
                    if (mvLojas.gridLength('#grd-pesquisa-endereco') > 0) {
                        utils.takeSS('cad_endereco_CEPencontrado');
                        casper.test.pass('CEP encontrado');
                    } else {
                        utils.takeSS("cad_endereco_cefNaoEncontrado_" + n);
                        casper.test.fail('CEP não encontrado');
                    }
                });

                casper.then(function () {

                    if (data.indCepUnicoCidade == 1) {

                        casper.then(function () {

                            utils.takeSS("cad_endereco_gridComRegistro");
                            casper.then(function () {
                                casper.then(mvLojas.findRowTable('#grd-pesquisa-endereco', {
                                    cidade: data.cidade,
                                    bairro: data.bairro
                                }, function (selector) {
                                    mvLojas.sellHideLineGrid("#grd-pesquisa-endereco", mvLojas.idLineGrid(selector));
                                }));
                            });

                        });

                        casper.then(function () {
                            casper.echo(".. Selecionando endereço");
                            mvLojas.click("#btn-selecionar-endereco");
                        });

                        casper.then(function () {
                            mvLojas.waitForLoad();
                        });

                        // aguarda modal abrir
                        casper.then(function () {
                            mvLojas.waitModalOpenEvt("#mdl-cadastro-logradouro");
                        });

                        casper.then(function () {
                            mvLojas.$valCombo(data.estado, "#frm-cadastro-logradouro #cbx-uf-cad");
                        });

                        casper.then(function () {
                            mvLojas.waitForLoad();
                        });

                        casper.then(function () {
                            casper.echo(".. cod_cidade: " + data.cod_cidade);
                            casper.echo(".. tipo logradouro: " + data.tipo_logradouro);
                            casper.echo(".. logradouro: " + data.logradouro);

                            mvLojas.$valCombo("#frm-cadastro-logradouro #cbx-cod-cgl-cad", data.cod_cidade);
                            mvLojas.$valCombo("#frm-cadastro-logradouro select[data-column='cod_tipo_logradouro']", data.tipo_logradouro);
                            mvLojas.$val("#frm-cadastro-logradouro input[data-column='desc_logradouro']", data.logradouro);
                        });

                        casper.then(function () {
                            utils.takeSS("cad_endereco_modaCadastroLogradouro");
                        });

                        casper.then(function () {
                            mvLojas.waitForLoad();
                        });

                        casper.then(function () {
                            mvLojas.click("#btn-salvar-logradouro");
                        });

                        casper.then(function () {
                            mvLojas.waitForLoad();
                        });

                        casper.then(function () {
                            utils.takeSS("cad_endereco_SalvarLogradouro_" + n);
                        });

                        // valida se existe a mensagem informando que o cliente foi salvo.
                        casper.then(function () {
                            casper.waitForText("Logradouro salvo com sucesso!", function success() {
                                casper.test.pass("Logradouro salvo com sucesso!");
                                mvLojas.click('div.modal.in, btn');
                            }, function fail() {
                                utils.takeSS('cad_endereco_erroAoSalvarLogradouro' + n);
                                casper.test.fail("Erro ao salvar logradouro");
                            });
                        });
                    } else {
                        casper.then(function () {

                            utils.takeSS("cad_endereco_gridComRegistro");

                            casper.then(function () {
                                casper.then(mvLojas.findRowTable('#grd-pesquisa-endereco', {
                                    cep: data.cep,
                                    logradouro: data.logradouro
                                }, function (selector) {
                                    mvLojas.sellHideLineGrid("#grd-pesquisa-endereco", mvLojas.idLineGrid(selector));
                                }));
                            });

                        });

                        casper.then(function () {
                            casper.echo(".. Selecionando endereço");
                        });

                        casper.then(function () {
                            mvLojas.click("#btn-selecionar-endereco");
                            mvLojas.waitForLoad();
                        });

                        casper.then(function () {
                            utils.takeSS("cad_endereco_modalComplementoEndereço_" + n);
                        });

                        casper.then(function () {
                            mvLojas.waitModalOpenEvt("#mdl-endereco-cliente");
                        });
                    }
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

                casper.then(function () {
                    casper.echo(".. validando endereço.");
                    utils.takeSS("cad_endereco_validandoEndereco");
                    utils.waitForValue("#mdl-endereco-cliente input[data-column='cep']", data.cep);
                    utils.waitForValue("#mdl-endereco-cliente input[data-column='nome_localidade']", data.cidade);
                    utils.waitForValue("#mdl-endereco-cliente input[data-column='uf']", data.estado);
                    utils.waitForValue("#mdl-endereco-cliente input[data-column='nome_bairro']", data.bairro);
                    utils.waitForValue("#mdl-endereco-cliente input[data-column='desc_logradouro']", data.desc_logradouro);

                });

                casper.then(function () {

                    casper.then(function () {
                        mvLojas.$valCheck("#cbx-sem-numero", data.ind_numero);
                    });

                    casper.then(function () {
                        mvLojas.$val("#mdl-endereco-cliente input[data-column='nro_endereco_cliente']", data.numero);
                        mvLojas.$valCombo("#cbx-tipo-complemento-endereco", data.cod_complemento_endereco);
                        mvLojas.$val("#mdl-endereco-cliente input[data-column='complemento_endereco_cliente']", data.complemento);

                        mvLojas.$val("#dp-residencia-cliente", data.reside_desde);

                        mvLojas.$valCombo("#mdl-endereco-cliente select[data-column='cod_situacao_residencia']", data.situacao_residencia);
                        mvLojas.$valCombo("#mdl-endereco-cliente select[data-column='nro_tipo_endereco']", data.tipo_endereco);
                    });
                });

                casper.then(function () {
                    utils.takeSS("cad_endereco_CamposPreenchidosAntesSalvar_" + n);
                });

                casper.then(function () {
                    mvLojas.click("#btn-salvar-endereco");
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

                casper.then(function () {
                    utils.takeSS("cad_endereco_camposPreenchidos_" + n);
                });
            })(response.data);
        });
    });
};