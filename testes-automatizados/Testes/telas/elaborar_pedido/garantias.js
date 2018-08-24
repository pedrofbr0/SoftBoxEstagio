var require = patchRequire(require);

/**
 * Funções utilitárias
 */
exports.garantias = function (garantias) {

    casper.then(function () {
        //produtos
        casper.eachThen(garantias, function (response) {
            (function (data) {          
                
            	casper.then(function () {
            		selecionarProduto(data);
            	});

                casper.then(function () {
                    ajustarGarantias(data.garantias, data);
                });

            })(response.data);
        });
    });
};

function selecionarProduto(data){
	
	casper.then(function () {
        mvLojas.click("#tab-itens-pedido #tab-produtos");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.echo(".. Selecionando o produto: " + data.desc_produto);
        mvLojas.sellLineGridProd(data.cod_produto, data.cor, data.voltagem);
    });

    casper.then(function () {
        utils.takeSS("produtoSelecionado");
    });

    casper.then(function () {
        this.echo(".. Clicando na aba 'Garantia'");
        this.mouse.click("#tab-itens-pedido #tab-garantia");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
};

function ajustarGarantias(garantias, produto) {

    casper.then(function () {
        utils.takeSS("garantias");
    });

    casper.then(function () {
        casper.eachThen(garantias, function (response) {
            (function (data) {

                var idLinhaProduto = getIdLineGrid(data.produto, data.cod_servico);

                casper.then(function () {
                    /*if (data.ind_add) {
                        inserirGarantia(data, produto);
                    } else if (data.ind_existe) {
                        validarGarantia(data);
                    } else {
                        removerGarantia(data, idLinhaProduto);
                    }*/
                    
                	if (data.ind_add) {
                        inserirGarantia(data, produto);
                    }
                	
                	if (data.ind_existe) {
                        validarGarantia(data);
                    }
                	
                    //if ((data.ind_existe && !data.ind_add)) {
                	if (data.ind_remove) {
                    	removerGarantia(data, idLinhaProduto);
                    }
                    
                });

            })(response.data);
        });
    });

}
;

/**
 * função responsável por inserir uma garantia para um determinado produto
 * @param data, array com os dados da garantia a ser inserida
 */
function inserirGarantia(data, produto) {
	
	//data = garantia.garantias

    casper.then(function () {
        utils.takeSS("antesInserirGarantia");
        this.echo(".. Inserindo garantia: " + data.nome_servico);
    });
    
    casper.then(function () {
    	selecionarProduto(produto);
    });


    casper.then(function () {
        mvLojas.click("#btn-insere-garantia");
    });

    casper.then(function () {
        this.waitForSelector("#mdl-inserir-garantia", function () {
            casper.test.pass("Modal de Garantias aberta!");
            utils.takeSS("modalGarantia");
        });
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.then(mvLojas.findRowTable('#grd-inserir-garantia', {
            nro_servico: data.cod_servico
        }, function (selector) {
            this.test.pass("Garantia '" + data.nome_servico + "' selecionada");
            mvLojas.sellHideLineGrid("#grd-inserir-garantia", mvLojas.idLineGrid(selector));
            utils.takeSS("garantiaSelecionada");
        }));
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.click("#mdl-inserir-garantia #btn-adicionar-garantia");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.then(mvLojas.findRowTable('#grd-garantias', {
            nro_item_pedido_produto: data.produto,
            nro_servico: data.cod_servico
        }, function (selector) {
        	this.test.pass("Garantia '" + data.nome_servico + "' inserido!");
            utils.takeSS("garantiaInserida");
        }));
    });
}
;

/**
 * Verifica se a garantia ja esta inserida na grid de garantias
 * @param data, array com os dados da garantia a ser validada
 */
function validarGarantia(data) {

    casper.then(function () {
        casper.then(mvLojas.findRowTable('#grd-garantias', {
            nro_item_pedido_produto: data.produto,
            nro_servico: data.cod_servico
        }, function (selector) {
        	if (selector == false) {
        		this.test.fail("Garantia '" + data.nome_servico + "' nao existe na grid de garantias.");
        		utils.takeSS("garantiaNaoEncontrada");
        	} else {
        		this.test.pass("Garantia '" + data.nome_servico + "' existe na grid de garantias.");
        		utils.takeSS("garantiaValidada");
        	}
        	
        }));
    });

}

/**
 * função responsável por remover uma garantia do grid de garantia
 * @param data, array com os dados da garantia
 * @param id, int com o id da linha da garantia a ser reomvida
 */
function removerGarantia(data, id) {

    casper.then(function () {
        this.echo(".. Removendo garantia: " + data.nome_servico);
    });

    casper.then(function () {
        mvLojas.click("#btn-excluir-garantia-" + id);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        if (data.ind_validacao) {
            validacaoUsuario(data);
        }
    });

    casper.then(function () {
        utils.takeSS("aposRemoverGarantia");
    });

};

/**
 * Função responsável por inserir usuário e senha para liberar a remoção de uma garantia
 * @param data, array com o usuário e a senha para liberação.
 */
function validacaoUsuario(data) {

    //utils.takeSS("modalValidacaoUsuario");

    casper.then(function () {
        this.waitForSelector("#modal-validacao-usuario", function () {
            casper.test.pass("Modal de validação de usuario aberta.");
            utils.takeSS("modalValidacaoUsuario");
        });
    });

    casper.then(function () {
        mvLojas.$val("#fm-validacao-pedido #usuario", data.usuario);
        mvLojas.$val("#fm-validacao-pedido #senha", data.senha);
    });

    casper.then(function () {
        utils.takeSS("aposInserirDados");
        mvLojas.click(".modal-validar-privilegio-ok");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

}

/**
 * 
 * @param prod, string informações para identificar o produto (cod_produto + "." + cor + "." + voltagem") ex.: 192835.0.0
 * @param serv, int com o código do serviço.
 * @returns int com id da linha
 */
function getIdLineGrid(prod, serv) {
    return casper.evaluate(function (prod, serv) {
        return $("tr:contains('" + prod + "'):contains('" + serv + "')").attr("id");
    }, prod, serv);
};