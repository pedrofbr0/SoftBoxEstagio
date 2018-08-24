var require = patchRequire(require);

/**
 * Funções utilitárias
 */
exports.liberacoes = function (dadosLiberacoes) {

    var qtdLiberacoes = dadosLiberacoes.length;
    var n = 0;

    casper.then(function () {
        mvLojas.click("#tab-itens-pedido #tab-liberacoes");
    });

    casper.then(function () {

        utils.takeSS("liberacoes");

        this.echo(".. Quantidade liberacoes no JSON: " + qtdLiberacoes);
        this.echo(".. Quantidade liberacoes na Grid: " + mvLojas.gridLength('#grd-liberacoes'));

        if (mvLojas.gridLength('#grd-liberacoes') == qtdLiberacoes) {
           // utils.takeSS('qtdLiberacoes');
            casper.test.pass('Liberacoes encontradas na grid');
        } else {
            utils.takeSS("qtdLiberacoesIncorretas");
            casper.test.fail('Quantidade de liberacoes incorreta');
        }
    });

    casper.then(function () {

        casper.eachThen(dadosLiberacoes, function (response) {
            (function (data) {

                n++;

                casper.then(function () {
                	selecionarPendencia(data, false);
                });
                
                casper.then(function () {
                    utils.takeSS("aposSelecionarLiberacao_" + n);
                });

                casper.then(function () {
                    liberarPendencias(data, n);
                });

            })(response.data);
        });

    });
};

function selecionarPendencia(data, aposSalvamentoAutomatico) {
	var idLinha = capturarIdLinha(data.inf_produto, data.tipo_liberacao);

	if (aposSalvamentoAutomatico) {
		casper.then(function () {
			casper.echo(".. Selecionando pendencia apos salvamento automatico.");
		});
	}
	
	casper.echo("Id Linha: " + idLinha);
	
    casper.then(function () {
        casper.then(mvLojas.findRowTable('#grd-liberacoes', {
            desc_tipo_liberacao: data.tipo_liberacao,
            ind_liberado: data.ind_liberacao
        }, function (selector) {

            if (selector) {
                mvLojas.$valCheck("#grd-liberacoes tr[id='" + idLinha + "'] td .chk-liberacao", data.ind_liberar);
                casper.test.pass("Liberacao encontrada: " + data.tipo_liberacao);
            } else {
                casper.test.fail("Liberacao nao encontrada: " + data.tipo_liberacao);
            }
        }));
    });
}

/**
 * Função responsável por liberar as pendencias geradas na aba liberação
 * @param data - array, com dados 
 */
function liberarPendencias(data, n) {

    casper.then(function () {
        casper.echo(".. Liberando as pendências");
        mvLojas.click("#btn-modal-liberar");
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	// Mensagem: "O pedido será salvo automaticamente. Após término realize o procedimento de liberação."
    	utils.takeSS("clickBtnOkProcedimentoLiberacao" + n);
        mvLojas.click(".modal-dialog .btn.Ok");
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	// Pedido salvo
    	utils.takeSS("clickBtnOkPedidoSalvo" + n);
        mvLojas.click(".modal-dialog .btn.Ok");
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.click("#tab-itens-pedido #tab-liberacoes");
    });
    
    // Selecionar novamente a liberacao
    casper.then(function () {
    	selecionarPendencia(data, true);
    });
    
    casper.then(function () {
    	 mvLojas.click("#btn-modal-liberar");
    });
    
//    casper.then(function () {
//		  mvLojas.waitForLoad();
//	  });
//    
//    casper.then(function () {
//		  mvLojas.waitForLoad();
//	  });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#mdl-liberacao");
    });

    casper.then(function () {
        casper.echo(".. Adicionando Observação: " + data.obs);
        mvLojas.$val("#mdl-liberacao #txt-obs-liberacao", data.obs);
    });

    casper.then(function () {
        utils.takeSS("modalObsLiberacao_" + n);
    });

    casper.then(function () {
        casper.echo(".. Clicando no botão Liberar");
        mvLojas.click("#mdl-liberacao #btn-liberar-liberacao");
    });

    casper.then(function () {
        utils.takeSS("modalObsLiberacao_" + n);
    });

    if (!data.usuarioLogadoPossuiPrivilegio) {

        casper.then(function () {
            mvLojas.waitModalOpenEvt("#modal-validacao-usuario");
        });

        casper.then(function () {
            casper.echo(".. Adicionando usuário e senha com privilegios para libracao");
            mvLojas.$val("#modal-validacao-usuario #fm-validacao-pedido #usuario", data.user);
            mvLojas.$val("#modal-validacao-usuario #fm-validacao-pedido #senha", data.senha);
        });

        casper.then(function () {
            utils.takeSS("modalUserSenhaLIberacao_" + n);
        });

        casper.then(function () {
            mvLojas.click("#modal-validacao-usuario .modal-validar-privilegio-ok");
        });

    }

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("msgLiberacao_" + n);
    });

    casper.then(function () {
        casper.waitForText(data.msg_sucesso_apos_liberacao, function success() {
            casper.test.pass(data.msg_sucesso_apos_liberacao);
            mvLojas.click(".modal.alert.in .btn.Ok");
        }, function fail() {
            utils.takeSS('erroAoLiberarLiberacoes_' + n);
            casper.test.fail("Erro ao liberar pendencias na aba de liberacao");
        });
    });

    casper.then(function () {
        utils.takeSS("aposOkLiberacao_" + n);
    });

}

/**
 * função responsável por capturar o id da linha do grid pro um determinado title
 * @param title, string com o title da linha
 * @returns int, com o id da linha
 */
function capturarIdLinha(prod, title) {

    console.log(prod, " - ", title);

    casper.echo("#grd-liberacoes tr td[title='" + prod + "'] + td[title='" + title + "']");
    
    return casper.evaluate(function (prod, title) {
        return $("#grd-liberacoes tr td[title='" + prod + "'] + td[title='" + title + "']").parent().attr("id");
    }, prod, title);
}