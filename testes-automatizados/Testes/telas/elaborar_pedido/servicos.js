var require = patchRequire(require);

/**
 * Função responsável por adicionar ou remover serviços a um pedido 
 * @param, array com os dados do serviço.
 */
exports.servico = function (servicos) {

    var qtdServicos = servicos.length;

    casper.then(function () {
        casper.echo("");
        casper.echo(".. Servicos.");
        mvLojas.click("a[href='#aba-servico']");
    });

    casper.then(function () {

        if (qtdServicos > 0) {

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("servico_abaServicos");
            });

            // envia um serviço de cada vez para ser tratado.
            casper.then(function () {

                for (var i = 0; i < qtdServicos; i++) {
                    ajustarServicos(servicos[i]);
                }

            });
        } else {
            casper.echo(".. Nao existe servicos a serem tratados");
        }

    });

};

/**
 * Função responsável por selecionar uma garantia avulsa, de um pedido ja fechado, a aba venda interna
 * @param garantias, array com as garantias a serem inseridas.
 */
exports.garantiaAvulsaVendaInterna = function (garantias) {

    casper.eachThen(garantias, function (response) {
        (function (data) {

            casper.then(function () {
                this.echo(".. Inserindo Garantia Avulsa (venda interna)");
                mvLojas.click("#btn-insere-garantia-avulsa");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                this.waitForText("Seleção de garantias avulsas", function () {
                    utils.takeSS("modalGarantiaAvulsa");
                });
            });

            casper.then(function () {
                mvLojas.click("#body-consulta-garantia-avulsa a[href='#aba-venda-interna']");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {

                if (data.ind_nota_fiscal) {
                    casper.echo(".. ENTREI EM BUSCAR GARANTIA AVULSA POR NOTA FISCAL. FUNCAO AINDA NAO FOI CONSTRUIDA");
                    casper.die();
                } else if (data.ind_pedido) {
                    buscarPorPedido(data);
                } else if (data.ind_cpf) {
                    buscarGarantiaPorCPF(data);
                }

            });

            casper.then(function () {

                if (data.ind_valid_faixa_preco) {
                    utils.takeSS("avisoFaixaPrecoGarantias");
                    mvLojas.click(".modal.alert.in .btn.Ok");
                }

            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                this.echo(".. Informando garantia: " + data.desc_garantia);
                mvLojas.$valCombo("#cbx-garantias-avulsas", data.nro_servico);
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("garantiaInformada");
            });

            casper.then(function () {
                this.echo(".. Inserindo Garantia");
                mvLojas.click("#btn-ins-garantia");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                casper.then(mvLojas.findRowTable('#grid-garantias-avulsas', {
                    nro_produto: data.nro_produto
                }, function (selector) {
                    casper.test.pass("Garantia Avulsa selecionada");
                    this.mouse.click(selector);
                    utils.takeSS("antesDeConfirmarGarantia");
                }));
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                mvLojas.click(".modal.in .btn.Ok");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                casper.then(mvLojas.findRowTable('#grd-servicos', {
                    nro_servico: data.nro_servico
                }, function (selector) {
                    casper.test.pass("Servico Encontrado na grid");
//	    				this.mouse.click(selector);
                }));
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("garantiaAvulsaAdicionada");
            });

        })(response.data);
    });

};

/**
 * Função responsável por selecionar uma garantia avulsa da aba venda externa
 * @param garantias, array com os dados da garantia avulsa
 */
exports.garantiaAvulsaVendaExterna = function (garantias) {

    casper.eachThen(garantias, function (response) {
        (function (data) {

            casper.then(function () {
                this.echo(".. Inserindo garantia avulsa (venda externa)");
                mvLojas.click("#btn-insere-garantia-avulsa");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                this.waitForText("Selecao de garantias avulsas", function () {
                    utils.takeSS("modalGarantiaAvulsaVendaExterna");
                });
            });

            casper.then(function () {
                mvLojas.click("#body-consulta-garantia-avulsa a[href='#aba-venda-externa']");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                this.echo("");
                this.echo(".. Inserindo valores.");
                this.echo(".. Nota Fiscal" + garantias.nota_fiscal);
                this.echo(".. Cupom Fiscal" + garantias.cupom_fiscal);
                this.echo(".. Data Emissao NF: " + garantias.data_emissao_nf);
                this.echo(".. Empresa: " + garantias.desc_empresa);
            });

            casper.then(function () {
                mvLojas.$val("#body-consulta-garantia-avulsa #tx-nota-fiscal-externa", garantias.nota_fiscal);
                mvLojas.$val("#body-consulta-garantia-avulsa #tx-cupom-fiscal-externa", garantias.cupom_fiscal);
                mvLojas.$val("#body-consulta-garantia-avulsa #tx-data-emissao-nf-externa", garantias.data_emissao_nf);
                mvLojas.$valCombo("#body-consulta-garantia-avulsa #cbx-garantias-empresa", garantias.empresa);
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("dadosPreenchidos");
                utils.waitForValue("#tx-valor-produto", garantias.valor_produto);
            });

            casper.then(function () {
                if (garantias.ind_pesquisa_prod_search_field) {

                    casper.then(function () {
                        mvLojas.click("#btn-sf-produto-search");
                    });

                    casper.then(function () {
                        mvLojas.waitForLoad();
                    });

                    casper.then(function () {
                        this.waitForSelector("#mdl-sf-produto", function () {
                            utils.takeSS("searchFieldProduto");
                        });
                    });

                    casper.then(function () {
                        this.echo("");
                        this.echo(".. Inserindo informacoes para pesquisar produto");
                        garantias.produto.cod_pai ? casper.echo("Cod Pai: " + garantias.produto.cod_pai) : "";
                        garantias.produto.cod_cor_produto ? casper.echo("Cod Cor Produto: " + garantias.produto.cod_cor_produto) : "";
                        garantias.produto.cod_tipo_voltagem ? casper.echo("Cod Tipo Voltagem: " + garantias.produto.cod_tipo_voltagem) : "";
                        garantias.produto.descricao ? casper.echo("Descricao Produto: " + garantias.produto.descricao) : "";
                        garantias.produto.nro_produto ? casper.echo("Nro Produto: " + garantias.produto.nro_produto) : "";
                        garantias.produto.situacao ? casper.echo("Situacao: " + garantias.produto.situacao) : "";
                        garantias.produto.desc_situacao ? casper.echo("Situacao: " + garantias.produto.desc_situacao) : "";
                        garantias.produto.cod_fiscal ? casper.echo("Cod Fiscal: " + garantias.produto.cod_fiscal) : "";
                        garantias.produto.desc_fiscal ? casper.echo("Desc Fiscal: " + garantias.produto.desc_fiscal) : "";
                    });

                    casper.then(function () {
                        garantias.produto.cod_pai ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='cod_nro_produto']", garantias.produto.cod_pai) : "";
                        garantias.produto.cod_cor_produto ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='cod_cor_produto']", garantias.produto.cod_cor_produto) : "";
                        garantias.produto.cod_tipo_voltagem ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='cod_tipo_voltagem']", garantias.produto.cod_tipo_voltagem) : "";
                        garantias.produto.descricao ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='desc_produto']", garantias.produto.descricao) : "";
                        garantias.produto.nro_produto ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='nro_produto']", garantias.produto.nro_produto) : "";
                        garantias.produto.situacao ? mvLojas.$valCombo("#mdl-sf-produto select[data-sf-column='cod_situacao_produto']", garantias.produto.situacao) : "";
                        garantias.produto.cod_fiscal ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='codigo_fiscal']" + garantias.produto.cod_fiscal) : "";
                        garantias.produto.desc_fiscal ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='desc_fiscal']" + garantias.produto.desc_fiscal) : "";
                    });

                    casper.then(function () {
                        utils.takeSS("formSearchProdPreenchido");
                        mvLojas.waitForLoad();
                    });

                    casper.then(function () {
                        this.echo(".. Clicando no botao Pesquisar");
                        mvLojas.click("#mdl-sf-produto .btn-success");
                    });

                    casper.then(function () {
                        mvLojas.waitForLoad();
                    });

                    casper.then(function () {
                        casper.then(mvLojas.findRowTable('#grid-sf-produto', {
                            nro_produto: garantias.produto.nro_produto
                        }, function (selector) {
                            this.test.pass("Produto encontrado.");
//	    					mvLojas.sellHideLineGrid("#grid-sf-produto", mvLojas.idLineGrid(selector));
                            this.mouse.click(selector);
                            utils.takeSS("produtoSelecionado");
                        }));
                    });

                    casper.then(function () {
                        mvLojas.waitForLoad();
                    });
                } else {

                    casper.then(function () {
                        this.echo("");
                        this.echo(".. Inserindo informacoes para pesquisar produto");
                        garantias.produto.cod_pai ? casper.echo("Cod Pai: " + garantias.produto.cod_pai) : "";
                        garantias.produto.cod_cor_produto ? casper.echo("Cod Cor Produto: " + garantias.produto.cod_cor_produto) : "";
                        garantias.produto.cod_tipo_voltagem ? casper.echo("Cod Tipo Voltagem: " + garantias.produto.cod_tipo_voltagem) : "";
                    });

                    casper.then(function () {
                        garantias.produto.cod_pai ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='cod_nro_produto']", garantias.produto.cod_pai) : "";
                        garantias.produto.cod_cor_produto ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='cod_cor_produto']", garantias.produto.cod_cor_produto) : "";
                        garantias.produto.cod_tipo_voltagem ? mvLojas.$val("#mdl-sf-produto input[data-sf-column='cod_tipo_voltagem']", garantias.produto.cod_tipo_voltagem) : "";
                    });

                    casper.then(function () {
                        mvLojas.waitForLoad();
                    });

                    casper.then(function () {
                        utils.takeSS("produtoEncontrado");
                        utils.waitForValue("#txt-sf-produto-text", garantias.produto.descricao);
                    });

                }
            });

            casper.then(function () {
                this.echo(".. Inserindo Garantia: " + garantias.desc_garantia);
                mvLojas.$valCombo("#cbx-garantias-avulsas", garantias.garantia);
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                mvLojas.click("#btn-ins-garantia");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {

                utils.takeSS("produtoInserido");

                casper.then(mvLojas.findRowTable('#grid-garantias-avulsas', {
                    nro_produto: garantias.produto.nro_produto
                }, function (selector) {
                    this.test.pass("Produto encontrado.");
//					mvLojas.sellHideLineGrid("#grid-sf-produto", mvLojas.idLineGrid(selector));
                    this.mouse.click(selector);
                    utils.takeSS("produtoSelecionado");
                }));
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                mvLojas.click(".modal.in .btn.Ok");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                casper.then(mvLojas.findRowTable('#grd-servicos', {
                    nro_servico: data.nro_servico
                }, function (selector) {
                    casper.test.pass("Servico Encontrado na grid");
//	    				this.mouse.click(selector);
                }));
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("garantiaAvulsaAdicionada");
            });

        });
    });
};

/**
 * Função responsável por validar se o é necessário remover ou inserir  serviço
 * @param servicos, array com os dados do serviço
 */
function ajustarServicos(servico) {

    casper.then(function () {

        if (servico.ind_ativo && !verificarServicoNaGrid(servico)) {

            adicionarServico(servico);

        } else if (!servico.ind_ativo && verificarServicoNaGrid(servico)) {

            removerServico(servico);

        } else if (servico.ind_ativo && verificarServicoNaGrid(servico)) {

            casper.then(function () {
                casper.test.pass("Servico '" + servico.cod_servico + " - " + servico.descricao + "', ja esta adicionado.");
            });

            casper.then(function () {
                if (servico.quantidade) {
                    alterarQuantidade(servico);
                }

                if (servico.valor) {
                    alterarValor(servico);
                }
            });
        }

    });

    casper.then(function () {
        utils.takeSS("aposValidarServicoExistente");
        mvLojas.waitForLoad();
    });

}
;

/**
 * adiciona um array que existente no json de pedido que ainda não existe na grid
 * @param servico, array com os dados do serviço
 */
function adicionarServico(servico) {

    casper.then(function () {
        utils.takeSS("servico_adicionarServico");
        casper.echo(".. Adicionando servico");
    });

    casper.then(function () {
        mvLojas.click("#mdl-elabora-pedido #btn-insere-servico");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.waitForSelector("#body-consulta-servico", function () {
            utils.takeSS("modalConsultaServicos");
        });
    });

    casper.then(mvLojas.findRowTable('#grid-consulta-servico', {
        nro_servico: servico.cod_servico
    }, function (selector) {
        this.test.pass("Servico encontrado.");
        mvLojas.sellHideLineGrid("#grid-consulta-servico", mvLojas.idLineGrid(selector));
    }));

    casper.then(function () {
        mvLojas.click(".modal.in .btn.Selecionar");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("servico_adicionarServico");
    });

    casper.then(function () {
        if (servico.quantidade) {
            alterarQuantidade(servico);
        }

        if (servico.valor) {
            alterarValor(servico);
        }
    });

    casper.then(function () {
        if (verificarServicoNaGrid(servico)) {
            casper.test.pass("Servico '" + servico.cod_servico + " - " + servico.descricao + "', adicionado com sucesso.");
        } else {
            casper.test.fail("Servico '" + servico.cod_servico + " - " + servico.descricao + "', nao foi adicionado.");
        }
    });

    casper.then(function () {
        utils.takeSS("servico_adicionarServico3");
    });

}

/**
 * remove um serviço que esta na grid e esta no json do pedido com o indicador ativo = false 
 * @param servico, array com as informações seviço
 */
function removerServico(servico) {

    casper.then(function () {
        casper.echo(".. Removendo servico");
    });

    casper.then(function () {
        mvLojas.click("#btn-excluir-servico-" + servico.cod_servico);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("servico_removerServico");
    });

    casper.then(function () {
        if (!verificarServicoNaGrid(servico)) {
            casper.test.pass("Servico '" + servico.cod_servico + " - " + servico.descricao + "', removido com sucesso.");
        } else {
            casper.test.fail("Servico '" + servico.cod_servico + " - " + servico.descricao + "', nao foi removido.");
        }
    });

}

/**
 * Verifica se um determinado registro existe no grid
 * 
 * @param array com os dados do serviço
 * @returns {Boolean} TRUE - caso exista o serviço na grid, FALSE - caso não exista o serviço na grid
 */
function verificarServicoNaGrid(servico) {

    var dadosGrid;

    // salva todos os dados da grid em uma variável
    dadosGrid = casper.evaluate(function () {
        return grid = $("#grd-servicos").getGridParam("data");
    });

    // percorre o array e compara o serviço passado com cada interação do grid.
    for (var i = 0; i < dadosGrid.length; i++) {
        if (dadosGrid[i].nro_servico == servico.cod_servico) {
            return true;
        }
    }

    return false;

}

/**
 * buasca todos os pedido pelo cpf do cliente
 * @param data, array com os dados necessários para busca
 */
function buscarGarantiaPorCPF(data) {

    casper.then(function () {
        this.echo(".. Buscando garantia por CPF");
        mvLojas.click("#dados-cliente input[type='radio'][name='servico-garantia-avulso']");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.$val("#aba-venda-interna #tx-cpf", data.cpf);
    });

    casper.then(function () {
        utils.takeSS("GarantiaCPFInformado");
    });

    casper.then(function () {
        this.echo(".. Clicando no botao para pesquisar notas");
        mvLojas.click("#dados-cliente .search-btn-nota-cpf");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {

        this.waitForText("Consulta notas", function () {
            utils.takeSS("modalConsultaNotas");
        });

    });

    casper.then(function () {
        this.echo(".. Selecionando pedido: " + data.consulta_nota.pedido);

        casper.then(mvLojas.findRowTable('#grid-notas', {
            nro_loja: data.consulta_nota.loja,
            nro_pedido: data.consulta_nota.pedido
        }, function (selector) {
            casper.test.pass("Nota encontrada");

            mvLojas.sellHideLineGrid("#grid-notas", mvLojas.idLineGrid(selector));

            this.mouse.click(selector);
        }));
    });

    casper.then(function () {
        utils.takeSS("notaSelecionada");
    });

    casper.then(function () {
        mvLojas.click(".modal.in .btn.Selecionar");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.waitForValue("#aba-venda-interna #tx-nro-produto", data.nro_produto);
        utils.waitForValue("#aba-venda-interna #tx-desc-produto", data.desc_produto);
    });

}

/**
 * Altera a quantidade de ums serviço
 * @param servico, Array com os dados do serviço
 */
function alterarQuantidade(servico) {

    casper.then(function () {
        mvLojas.$val("#grd-servicos tr[id='" + servico.cod_servico + "'] input[name='qtde_servico']", servico.quantidade);
        casper.echo(".. Alterando quantidade da garantia: " + servico.quantidade);
    });

    casper.then(function () {
        utils.takeSS("qtdAlterada");
    });

}

/**
 * Altera o valor do serviço
 * @param servico, Array com os dados do serviço
 */
function alterarValor(servico) {

    casper.then(function () {
        mvLojas.$val("#grd-servicos tr[id='" + servico.cod_servico + "'] input[name='vr_unitario_servico']", servico.valor);
        casper.echo(".. Alterando valor da garantia. " + servico.cod_servico + " - " + servico.descricao + " - R$ " + servico.valor);
    });

    casper.then(function () {
        utils.takeSS("valorAlterado");
    });

}

/**
 * busca um produto por um determinado pedido
 * @param data
 */
function buscarPorPedido(data) {

    casper.then(function () {
        this.echo(".. Selecionando a opcao Pedido");
        mvLojas.click("#dados-pedido input[type='radio'][name='servico-garantia-avulso']");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.$val("#aba-venda-interna #tx-nro-pedido", data.consulta_nota.pedido);
    });

    casper.then(function () {
        utils.takeSS("pedidoInformado");
    });

    casper.then(function () {
        this.echo(".. Inserindo Loja");
        mvLojas.$val("#aba-venda-interna #txt-ssf-loja-garantias-cod-nro-loja-key", data.consulta_nota.loja);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("lojaInformada");
    });

    casper.then(function () {
        mvLojas.click(".search-btn-produto.mv-consulta-produtos-cliente");
    });
    
    casper.then(function () {
        this.waitForText("Consulta notas", function () {
            utils.takeSS("consultaNotas");
        });
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.echo("..  Consulta notas: " + data.consulta_nota.pedido);

        casper.then(mvLojas.findRowTable('#grid-notas', {
            nro_pedido: data.consulta_nota.pedido,
            nro_produto: data.nro_produto
        }, function (selector) {
            mvLojas.sellHideLineGrid("#grid-notas", mvLojas.idLineGrid(selector));
            casper.test.pass("Nota encontrada");
            utils.takeSS("notaSelecionada");
        }));
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.click(".modal.in .btn.Selecionar");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

}