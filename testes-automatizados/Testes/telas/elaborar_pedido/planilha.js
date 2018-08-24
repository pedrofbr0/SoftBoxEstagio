var require = patchRequire(require);

/**
 * Funções utilitárias
 */
exports.formasPagamento = function (formasPagamento) {

    var qtdPlanilha = formasPagamento.length;
    var n = 0;

    casper.then(function () {
        this.echo(".. Clicando na aba Planilha");
        this.mouse.click("#tab-itens-pedido #tab-planilha");
    });

    casper.then(function () {

        casper.eachThen(formasPagamento, function (response) {
            (function (data) {

                n++;

                casper.then(function () {
                    mvLojas.click("#btn-nova-planilha");
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

                casper.then(function () {
                    utils.takeSS("planilha_definindoFormaPagamento_" + n);
                    casper.echo("");
                });

                // validase existe uma linha no grid de forma de pagamento
                casper.then(function () {
                    if (mvLojas.gridLength('#grd-planilha-pagto') == n) {
                        casper.test.pass('Criada linha no grid de forma de pagamento. ' + n + " de " + qtdPlanilha);
                    } else {
                        utils.takeSS('planilha_linhaNoGridFormaPagamentoNaoCriada');
                        casper.test.fail('Linha no grid de forma de pagamento não foi criada.');
                    }
                });

                casper.then(function () {

                    casper.then(function () {
                        casper.echo(".. Adicionando forma de pagamento " + n + " de " + qtdPlanilha);
                        casper.echo(".. Tipo de pagamento: " + data.tipo);
                        casper.echo(".. Forma de pagamento: " + data.forma_pagamento);
                    });

                    casper.then(function () {
                    	//casper.echo("#grd-planilha-pagto tr[id='" + n + "'] select[data-column='flag_tipo_pagto_pedido']");
                        mvLojas.$valCombo("#grd-planilha-pagto tr[id='" + n + "'] select[data-column='flag_tipo_pagto_pedido']", data.tipo);
                    });

                    casper.then(function () {
                    	//casper.echo("#grd-planilha-pagto");
                        mvLojas.sellHideLineGrid("#grd-planilha-pagto", n);
                    });

                    casper.then(function () {
                    	//casper.echo("#grd-planilha-pagto tr[id='" + n + "'] select[data-column='nro_forma_pagto']");
                        mvLojas.$valCombo("#grd-planilha-pagto tr[id='" + n + "'] select[data-column='nro_forma_pagto']", data.cod_forma_pagamento);
                    });

                    casper.then(function () {
                        utils.takeSS("planilha_antesDeInserirValor_" + n);
                    });

                    casper.then(function () {
                        if (data.ind_inserir_valor) {
                            casper.echo(".. Inserindo valor");
                            casper.sendKeys("#grd-planilha-pagto tr[id='" + n + "'] input[data-column='vr_planilha']", data.valor, {reset: true});
                        } else {
                            casper.echo(".. Validando valor");
                            // Retirado pois esse valor é alterado sempre q há divergencia, então não faz sentido validar
                            //utils.waitForValue("#grd-planilha-pagto tr[id='" + n + "'] input[data-column='vr_planilha']", data.valor);
                        }
                    });
                });

                casper.then(function () {
                	if (data.cod_forma_pagamento == 10) {
                		casper.echo(".. Abrindo modal Recibo de Troca");
                		preecherReciboTroca(data.recibo_troca);
                	}
                });
                
                casper.then(function () {
                    mvLojas.waitForLoad();
                });
                
                casper.then(function () {
                    if (data.cod_forma_pagamento == 29) {
                    	//data.cheque.dt_vencto_docto_pagto = mvLojas.somaMes(new Date());
                    	//casper.echo(".. Data vencimento somada um mês de hj: " + data.cheque.dt_vencto_docto_pagto);
                    	
                    	data.cheque.dt_vencto_docto_pagto = mvLojas.getElementValue("#cbx-primeiro-vencimento");
                    	casper.echo(".. Data vencimento da tela de pedido: " + data.cheque.dt_vencto_docto_pagto);
                        validarCMC7(data.cheque, n);
                    }
                });

                casper.then(function () {
                    utils.takeSS("planilha_formaPagamento_" + n);
                });

                casper.then(function () {
                    casper.then(mvLojas.findRowTable('#grd-planilha-pagto', {
                        parcela_docto_pagto: data.valor
                    }, function (selector) {
                        casper.test.pass("1 - Pagamento adicionando: (R$ " + data.valor + " - " + data.forma_pagamento + ")");
                    }));
                });

                casper.then(function () {
                    if (data.cod_forma_pagamento == 3 || data.cod_forma_pagamento == 2) {

                        casper.then(function () {
                            mvLojas.click("#btn-cmc7-planilha");
                        });
                        data.cheque.dt_vencto_docto_pagto = mvLojas.dataAdd(0);
                        validarCMC7(data.cheque, n);
                    }
                });

                casper.then(function () {
                    if (data.ind_gerar_financiamento) {
                        gerarFinanciamento(data, n);
                    }
                });

                casper.then(function () {
                    utils.takeSS("planilha_formaPagamentoPeenchida_" + n);
                });

            })(response.data);
        });
    });
};

/**
 * função respnosável por remover linhas do grid de planilhas
 * @params, array com dados da linha de pagamento a ser removido
 */
exports.removerLinhas = function (dadosPlanilha) {

    casper.then(function () {
        this.echo(".. Clicando na aba Planilha");
        this.mouse.click("#tab-itens-pedido #tab-planilha");
    });

    casper.then(function () {
        utils.takeSS("abaPlanilha");
    });

    casper.eachThen(dadosPlanilha, function (response) {
        (function (data) {

            var idLine = data.id_linha ? data.id_linha : getIdLineGrid(data.forma_pagamento, data.tipo);

            casper.then(function () {
                this.echo("ID LINHA: " + idLine);
                this.echo(".. Removendo linha da grid de planilha: 'Tipo' -> " + data.tipo + " | 'Forma de Pagamento' -> " + data.forma_pagamento);
            });

            casper.then(function () {
                mvLojas.click(".btn-excluir-planilha[data-id='" + idLine + "']");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("aposRemoverLinhaGrid");
            });

        })(response.data);
    });

};

/**
 * função responsável por gerar financiamento.
 * @param data, array com informações da forma de pagamento da linha em específica selecionada
 * @param linha, inteiro com o número da linha do grid
 */
function gerarFinanciamento(data, linha) {

    casper.then(function () {
        mvLojas.sellHideLineGrid("#grd-planilha-pagto", linha);
    });

    casper.then(function () {
        this.echo(".. Clicanco no botão Gerar Financiamento.");
        mvLojas.click("#btn-gera-financiamento-planilha");
    });

    casper.then(function () {
        this.waitForSelector("#mdl-gerar-financiamento", function () {
            utils.takeSS("modalGerarFinanciemanto");
        });
    });

    casper.then(function () {
        this.echo(".. Inserindo N. Documento.");
        mvLojas.$val("#frm-gerar-financiamento input[data-column='nro_docto_pagto']", data.documento);
    });

    casper.then(function () {
        utils.takeSS("numeroDocumento");
    });

    casper.then(function () {
        this.echo(".. Gerando financimento.");
        mvLojas.click("#btn-salvar-gerar-financiamento");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitModalCloseEvt("#mdl-gerar-financiamento");
    });

}

/**
 * Captura o id da linha desejada, informando qual o tipo de pagamento desejado
 * @param data, array com os dados da linha
 */
function getIdLineGrid(forma_pagamento, tipo) {
    return casper.evaluate(function (forma_pagamento, tipo) {
        return $("tr:contains('" + forma_pagamento + "'):contains('" + tipo + "')").attr("id");
    }, forma_pagamento, tipo);
}
;

/**
 * Função responsável por validar modal CMC7
 * @param data, array com dados necessários para preencher o form da modal
 */
function validarCMC7(data, n) {

    casper.then(function () {
        mvLojas.sellHideLineGrid("#grd-planilha-pagto", n);
    });

    casper.then(function () {
        mvLojas.click("#btn-cmc7-planilha");
    });

    casper.then(function () {
        utils.takeSS("modalcheque");
        this.waitForSelector("#mdl-cheque", function () {
            utils.takeSS("modalcheque");
            casper.test.pass("Modal de validacao do cheque aberta.");
        });
    });

    casper.then(function () {
        mvLojas.$val("#frm-cheque #txt-cmc7", data.cmc7);
    });

    casper.then(function () {
        mvLojas.click("#btn-validar-cmc7");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("cmc7Validado");
        this.waitForText("CMC7 válido!", function () {
            casper.test.pass("CMC7 valido!");
        });
    });

    casper.then(function () {
        utils.waitForValue("#frm-cheque input[data-column='cod_banco']", data.nro_banco);
        utils.waitForText("#frm-cheque select[data-text='desc_banco'] + div", data.desc_banco);
        utils.waitForValue("#frm-cheque input[data-column='conta_pagto_pedido_agencia']", data.conta_pagto_pedido_agencia);
        utils.waitForValue("#frm-cheque input[data-column='conta_pagto_pedido_nro']", data.conta_pagto_pedido_nro);
        utils.waitForValue("#frm-cheque input[data-column='nro_docto_pagto']", data.nro_docto_pagto);
        utils.waitForValue("#frm-cheque input[data-column='dt_vencto_docto_pagto']", data.dt_vencto_docto_pagto);
    });

    casper.then(function () {
        mvLojas.$val("#frm-cheque input[data-column='dt_conta']", data.dt_abertura);
    });

    casper.then(function () {
        utils.takeSS("antesConfirmarCheque");
        mvLojas.click("#mdl-cheque #btn-salvar-cheque");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

}

/**
 * Função responsável por preencher o recibo de troca
 * @param reciboTroca
 */
function preecherReciboTroca(reciboTroca) {
	casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.waitForText("Recibo de Troca", function success() {
            casper.test.pass("Modal de Recibo de Troca aberta com sucesso.");
        }, function fail() {
            utils.takeSS('planilha_erroAoAbrirModalReciboTroca');
            casper.test.fail("Modal de Recibo de Troca não foi aberta.");
        });
    });

    casper.then(function () {
        casper.echo(".. Preechendo recibo de troca: " + reciboTroca.nro_recibo_troca);
        casper.echo(".. Pedido: " + reciboTroca.nro_pedido);
        casper.echo(".. Loja: " + reciboTroca.loja_pedido);
        casper.echo(".. Empresa: " + reciboTroca.nro_empresa);
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.$val("input[data-column='rec_nro_pedido']", reciboTroca.nro_pedido);
        mvLojas.$val("input[data-column='rec_cod_nro_loja']", reciboTroca.loja_pedido);
        mvLojas.$valCombo("#cbx-empresa-rec", reciboTroca.nro_empresa);
    });

    casper.then(function() {
    	utils.takeSS('planilha_DadosReciboPreenchidos');
    	mvLojas.click("#btn-verificar-credito");
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function() {
    	mvLojas.click(".modal.alert.in .btn.Ok");
    });
    
    casper.then(function() {
    	mvLojas.click("#btn-salvar-recibo-troca");
    });
    
}