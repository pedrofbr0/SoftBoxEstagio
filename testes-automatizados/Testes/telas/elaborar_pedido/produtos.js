var require = patchRequire(require);
var telaPromocoes = require('../../telas/elaborar_pedido/promocoes.js');

/**
 * função mapeada para a inserção de produtos
 * 
 * @param produtos, array com os produtos a serem inseridos no pedido de compra
 * @param cliente_informado (Bollean), informando se o cliente ja foi informando no pedido (true ? cliente informado : cliente não informado)
 * @param dadosCliente, array com os dados do cliente, só deverá ser passado, caso o cliente ainda não tenha sido informado no pedido
 */
exports.produtos = function (produtos, cliente_informado, dadosCliente, idLinha) {

    var n = 0;

    cliente_informado == undefined ? cliente_informado = true : false;

    casper.then(function () {

        casper.eachThen(produtos, function (response) {
            (function (data) {

                n++;

                casper.then(function () {
                	casper.echo("Esperando para clicar no botão de Produto");
                    mvLojas.waitForLoad();
                });
                
                casper.then(function () {
                    mvLojas.click("#btn-insere-produto");
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

                casper.then(function () {
                    casper.waitForText("Consulta Produtos", function success() {
                        casper.test.pass("Modal de produtos aberta com sucesso.");
                    }, function fail() {
                        utils.takeSS('produto_erroAoAbrirModalProdutos');
                        casper.test.fail("Modal de produtos não foi aberta.");
                    });
                });

                casper.then(function () {
                    casper.echo(".. Selecionando produto: " + data.cod_nro_produto + " - " + data.descricao);
                });

                casper.then(function () {
                    mvLojas.$val("input[data-column='cod_nro_produto']", data.cod_nro_produto);
                    mvLojas.$val("input[data-column='cod_cor_produto']", data.cod_cor_produto);
                    mvLojas.$val("input[data-column='cod_tipo_voltagem']", data.cod_tipo_voltagem);
                });

                casper.then(function () {
                    utils.takeSS("produto_dadosBuscarProduto");
                    mvLojas.click("#btn-prod-pesq-prod");
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

                //Verifica se foi encontrado algum produto
                casper.then(function () {
                    if (mvLojas.gridLength('#grid-consulta-prod') > 0) {
                        utils.takeSS('produto_produtoEncontrado');
                        casper.test.pass('Produto encontrado');
                    } else {
                        utils.takeSS("produto_produtoNaoEncontrado");
                        casper.test.fail('Produto não encontrado');
                    }
                });

                // seleciona o produto na grid
                casper.then(function () {
                    casper.then(mvLojas.findRowTable('#grid-consulta-prod', {
                        cod_pai: data.cod_nro_produto + "." + data.cod_cor_produto + "." + data.cod_tipo_voltagem
                    }, function (selector) {
                    	this.mouse.click(selector);
                        utils.takeSS("produto_produtoSelecionado");
                    }));
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });
                
                // VALIDACAO KIT
                casper.then(function (){
                	if (data.promocao.ind_kit) {
                		casper.echo("");
                		casper.echo(".. Validacao de KIT");
                		
                		casper.then(function () {
                	        mvLojas.waitForLoad();
                	    });
                		
                		casper.then(function() {
                			utils.takeSS("consultaPromocao");
	                		mvLojas.click(".mdl-consulta-promocao.in .Fechar");
                		});
                		
                		casper.then(function() {
                			utils.takeSS("pesquisaKit");
                			mvLojas.click("#btn-prod-pesq-kit");
                		});
                		
                		casper.then(function() {
                			casper.echo("... Nro KIT: " + data.promocao.nro_kit);
                			casper.echo("... Descricao KIT: " + data.promocao.desc_kit);
                		});
                		
                		casper.then(function () {
                	        mvLojas.waitForLoad();
                	    });
                		
                		// 1935 - KIT - TESTES AUTOMATIZADO
                		casper.then(mvLojas.findRowTable('#grid-consulta-kit', {
                            "nro_kit": data.promocao.nro_kit,
                            "descricao": data.promocao.desc_kit
                        }, function (selector) {
                        	if (selector == false) {
                        		this.test.fail("KIT nao encontrado na grid de kits.");
                        		utils.takeSS("kitNaoEncontrado");
                        	} else {
                        		//casper.echo("Seletor " + selector);
	                        	//this.mouse.click(selector);
	                        	//utils.takeSS("selecionarKit");
	                        	
	                        	this.test.pass("Kit '" + data.promocao.nro_kit + " - " + data.promocao.desc_kit + "' existe na grid de kits.");
	                    		utils.takeSS("kitEncontrado");
	                    		
	                    		casper.then(function () {
	                    			this.echo(".. Selecionando o kit: " + data.promocao.desc_kit);
	                    	        mvLojas.sellLineGridKit(data.promocao.nro_kit, data.promocao.desc_kit);
	                    	    });
	                    		
	                    		casper.then(function () {
	                    	        mvLojas.waitForLoad();
	                    	    });
                        	}
                        }));
                		
                		casper.then(function() {
                			mvLojas.click(".btn.Selecionar:not(.mdl-consulta_produtos .btn.Selecionar)");
                			utils.takeSS("kitSelecionado");
                		});
                	}
                });
                
                // VALIDA PROMOÇÃO, CASO TENHA UMA PROMOÇÃO CADASTRADA PARA O PRODUTO SELECIONADO
                casper.then(function () {
                    if (data.promocao.ind_promocao_ativa) {
                    	casper.echo(".. Promocao Ativa");
                    	telaPromocoes.promocoes(data.promocao);
                    	//validarPromocao(data.promocao);
                    }
                });
                
                
                // VALIDACAO VENDA CASADA
                casper.then(function (){
                	if (data.promocao.ind_venda_casada) {
                    	casper.echo(".. Promocao - Venda Casada");
                    }
                });
                
                // VALIDACAO VENDA AVULSA
                casper.then(function (){
                	if (data.promocao.ind_venda_avulsa) {
                		
                		casper.echo("");
                		casper.echo(".. Validacao de VENDA AVULSA");
                		
                		/*casper.then(function() {
                			utils.takeSS("consultaPromocao");
	                		mvLojas.click(".mdl-consulta-promocao.in .Fechar");
                		});*/
                		
                		casper.then(function () {
                	        mvLojas.waitForLoad();
                	    });
                		
                		casper.then(function() {
                			utils.takeSS("pesquisaKit");
                			mvLojas.click("#btn-prod-pesq-kit");
                		});
            		
                		casper.then(function() {
                			casper.echo("... Nro KIT: " + data.promocao_venda_avulsa.nro_kit);
                			casper.echo("... Descricao KIT: " + data.promocao_venda_avulsa.desc_kit);
                		});
                		
                		casper.then(function () {
                	        mvLojas.waitForLoad();
                	    });
                		
                		// 1935 - KIT - TESTES AUTOMATIZADO
                		casper.then(mvLojas.findRowTable('#grid-consulta-kit', {
                            "descricao": data.promocao_venda_avulsa.desc_kit
                        }, function (selector) {
                        	if (selector == false) {
                        		this.test.fail("KIT (" + data.promocao_venda_avulsa.desc_kit + ") nao encontrado na grid de kits.");
                        		utils.takeSS("kitNaoEncontrado");
                        	} else {
                        		//casper.echo("Seletor " + selector);
	                        	//this.mouse.click(selector);
	                        	//utils.takeSS("selecionarKit");
	                        	
	                        	this.test.pass("Kit '" + data.promocao_venda_avulsa.nro_kit + " - " + data.promocao_venda_avulsa.desc_kit + "' existe na grid de kits.");
	                    		utils.takeSS("kitEncontrado");
	                    		
	                    		casper.then(function () {
	                    			this.echo(".. Selecionando o kit: " + data.promocao_venda_avulsa.desc_kit);
	                    	        mvLojas.sellLineGridKit(data.promocao_venda_avulsa.nro_kit, data.promocao_venda_avulsa.desc_kit);
	                    	    });
	                    		
	                    		casper.then(function () {
	                    	        mvLojas.waitForLoad();
	                    	    });
                        	}
                        }));
            		
                		casper.then(function() {
                			mvLojas.click(".btn.Selecionar:not(.mdl-consulta_produtos .btn.Selecionar)");
                			utils.takeSS("kitSelecionado");
                		});
	                		
                		// VALIDA PROMOÇÃO VENDA AVULSA, CASO TENHA UMA PROMOÇÃO CADASTRADA PARA O PRODUTO SELECIONADO
                        casper.then(function () {
                            if (data.promocao_venda_avulsa.ind_promocao_ativa) {
                            	casper.echo(".. Promocao VENDA AVULSA Ativa");
                            	telaPromocoes.promocoes(data.promocao_venda_avulsa);
                            	//validarPromocao(data.promocao);
                            }
                        });
                	}
                });
                
                casper.then(function () {
                    mvLojas.waitForLoad();
                    casper.echo(".. Clicando no botao selecionar");
                });

                casper.then(function () {
                    mvLojas.click(".mdl-consulta_produtos .btn.Selecionar");
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

                casper.then(function () {

                    utils.takeSS("aposSelecionarProduto");

                    casper.then(mvLojas.findRowTable('#grd-produtos', {
                        "produto.cod_nro_produto": data.cod_nro_produto,
                        ind_entrega_produto: data.ind_entrega_produto
                    }, function (selector) {
                        //					this.mouse.click(selector);
                    }));
                });

                // TODO inserir detalhes do produto aqui....
                casper.then(function () {
                    if (!utils.isEmpty(data.empenho)) {
                        casper.echo(".. Empenhando produto.");
                        empenharProduto(data, cliente_informado, dadosCliente, idLinha);
                    }
                });                

                casper.then(function () {

                    casper.echo(".. Validando se o status da entrega esta OK.");

                    casper.then(function () {
                        mvLojas.waitForLoad();
                    });
                    
                    casper.echo("Produto: " + data.cod_nro_produto + " Entrega: " + data.ind_entrega_atualizado);
                    
                    casper.then(mvLojas.findRowTable('#grd-produtos', {
                        "produto.cod_nro_produto": data.cod_nro_produto,
                        "ind_entrega_produto": data.ind_entrega_atualizado
                    }, function (selector) {
                        if (selector != false) {
                            casper.test.pass('Produto empenhado.');
                            casper.test.pass('Indicador da entrega: ' + data.ind_entrega_atualizado);
                        } else {
                            utils.takeSS("produto_erroAoSetarEmpenharProduto");
                            casper.test.fail('Indicador de entrega, esta incorreto');
                        }
                    }));
                });
                
                

                casper.then(function () {
                    editarLinhaGrid(data, n);
                });

                casper.then(function () {
                    mvLojas.waitForLoad();
                });

            })(response.data);
        });
    });

};

/**
 * Remove um produto da grid de produtos e do pedido
 * @param {produtos} array com dados de todos os produtos a serem removidos
 */
exports.removerProduto = function(produtos) {
    
    casper.then(function () {
       this.echo(".. Removendo produtos."); 
    });
    
    casper.eachThen(produtos, function(response) {
        (function (data) {
            
            var idLinha;
            var qtdProdutos = mvLojas.gridLength("#grd-produtos");
            // valida se exite um id passado para a função, caso não exista o id é pego pela informações do produto
            if (data.id) {
                idLinha = data.id;
            } else {
                idLinha = mvLojas.getIdLineGrid(data.cod_nro_produto, data.cod_cor_produto, data.cod_tipo_voltagem);
            }
            
            casper.then(function () {
               mvLojas.click("#tab-produtos"); 
            });
            
            casper.then(function () {
                this.echo("");
                this.echo(".. Produtos existes na grid: " + qtdProdutos);
                utils.takeSS("qtdProdutos");
            });
            
            casper.then(function () {
                this.echo(".. Removendo produto: " + data.descricao);
                mvLojas.click("#btn-excluir-produto-" + idLinha);
                qtdProdutos--;
            });
            
            casper.then(function () {
               mvLojas.waitForLoad(); 
            });
            
            casper.then(function () {
                utils.takeSS("aposRemoverProduto");
                
                if (mvLojas.gridLength("#grd-produtos") == qtdProdutos) {
                    casper.test.pass("Produto Removido com sucesso.");
                } else {
                    casper.test.fail("Produto não foi removido");
                }
            });
            
        })(response.data);
    });
    
}

/**
 * Cancela o empenho de um determinado produto, se o empenho for loja - normal, o sistema nao emite mensagem de sucesso
 * 
 * @param {array} com os dados do produto a ter o empenho cancelado
 */
exports.cancelarEmpenho = function (produtos) {

    casper.then(function () {
        this.echo(".. Cancelando empenho Produto.");
    });

    casper.eachThen(produtos, function (response) {
        (function (data) {

            var idLinha = mvLojas.getIdLineGrid(data.cod_nro_produto, data.cod_cor_produto, data.cod_tipo_voltagem);

            casper.then(function () {
                utils.takeSS("antesAbrirDetalhesProduto");
                this.echo(".. Clicando no botao de detalhes do produto: " + data.descricao);
                mvLojas.click("#btn-detalhes-item-" + idLinha);
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                this.waitForSelector("#modal-detalhes-produto", function () {
                    casper.test.pass("Modal detalhe de produtos aberta com sucesso");
                    utils.takeSS("detalhesCancelarEmpenho");
                });
            });

            casper.then(function () {
                utils.waitForText("#desc-local-empenho", data.desc_local_empenho);
                utils.waitForText("#desc-opcao-entrega", data.desc_opcao_entrega);
            });

            casper.then(function () {
                this.echo(".. Clicando no botao 'Cancelar Empenho'");
                mvLojas.click("#btn-cancelar-empenho-produto");
            });

//            casper.then(function () {
//                mvLojas.waitForLoad();
//            });
            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("aposCancelarEmpenho");
            });
            
          //verifica se o sistema emite mensagem de sucesso
      	  casper.then(function () {
      	        if (data.cancela_empenho_sem_emitir_mensagem) {
      	            return;
      	        }else{
      	        	casper.echo("Aguardando 10seg para abrir modal de confirmação!!");
      	        	casper.wait(10000, function () {
      	      	      casper.waitForText("salvo com sucesso.", function success() {
      	      	    	casper.test.pass("Empenho cancelado com sucesso.");
            	          utils.takeSS("empenho_cancelado");
            	          mvLojas.click(".modal.alert.in .btn.Ok");
            	      }, function fail() {
            	          utils.takeSS('erroAoCancelarEmpenho');
            	          casper.test.fail("Cancelamento de empenho nao realizado.");
      	      	      });
      	      	  });
      	        }
      	  });

        })(response.data);
    });

};

/**
 * Função responsável por inserir um cliente no pedido de venda
 * @param data, array com as informações do cliente ja cadastrado no sistema
 */
function informarCliente(data) {

    var cpfCnpj = mvLojas.isNull(data.cpf_cnpj) ? false : true;

    casper.then(function () {
        casper.echo(".. abrindo searchfield de busca de cliente.");
        mvLojas.click("#btn-sf-cliente-pedido-search");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#mdl-sf-cliente-pedido");
    });

    casper.then(function () {
        if (data.ind_pf == 0) {
            mvLojas.$valCheck("input[data-sf-column='tipo_pessoa']", data.ind_pf);
        }
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.echo(cpfCnpj ? (".. pesquisando cliente por CPF") : "pesquisando cliente por Nome");
        casper.echo(cpfCnpj ? (".. Nome: " + data.nome) : (".. CPF: " + data.cpf_cnpj));
    });

    casper.then(function () {
        cpfCnpj ? mvLojas.$val("#mdl-sf-cliente-pedido input[data-sf-column='cpf']", data.cpf_cnpj) : mvLojas.$val("#mdl-sf-cliente-pedido input[data-sf-column='nome_cliente']", data.nome);
        mvLojas.$valCombo("#mdl-sf-cliente-pedido select[data-sf-column='uf']", data.uf);
        mvLojas.$valCombo("#mdl-sf-cliente-pedido select[data-sf-column='cod_cgl']", data.uf);
    });

    casper.then(function () {
        utils.takeSS("produto_antesConsultaSFCliente");
        casper.echo(".. Clicando no botao pesquisar");
        mvLojas.click("#mdl-sf-cliente-pedido .mv-sf-filter-search");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {

        var valor;
        utils.takeSS("produto_consultaSFCliente");

        casper.then(function () {
            valor = cpfCnpj ? data.cpf_cnpj : referencia.nome;
        });

        casper.then(function () {
            if (valor.length == 11) {
                valor = valor.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3\-\$4");
            } else if (valor.lenght == 11) {
                valor = valor.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, "\$1.\$2.\$3\/\$4\-\$5");
            }
        });

        casper.then(function () {
            if (mvLojas.gridLength('#grid-sf-cliente-pedido') > 0) {
                utils.takeSS('produto_clienteEncontrado');
                casper.test.pass('Cliente Encontrado');
            } else {
                casper.test.fail('Cliente nao encontrado');
                takeSS("produto_clienteNaoEncontrado");
            }
        });

        casper.then(function () {

            utils.takeSS("produto_clienteEncontrado");

            if (cpfCnpj) {

                casper.then(mvLojas.findRowTable('#grid-sf-cliente-pedido', {
                    cpf_cnpj: valor
                }, function (selector) {
                    this.mouse.click(selector);
                    utils.takeSS("produto_clienteSelecionado");
                    casper.test.pass("Cliente Selecionado.");
                }));

            } else {

                casper.then(mvLojas.findRowTable('#grid-sf-cliente-pedido', {
                    nome_cliente: valor
                }, function (selector) {
                    this.mouse.click(selector);
                    utils.takeSS("produto_clienteSelecionado");
                    casper.test.pass("Cliente Selecionado.");
                }));

            }

        });

        casper.then(function () {
            utils.takeSS("produto_aposSelecionarCliente");
        });

    });
}

exports.reempenharProduto = function (produtos) {

    casper.eachThen(produtos, function (response) {
        (function (data) {

            empenharProduto(data, true);

        })(response.data);
    });

};

/**
 * Função responsável por clicar no botão e empenho de um produto e empenhar o mesmo de acordo com os dados passados
 * @param array - data, array com os dados para empenhar o produto.
 * @param int - n, inteiro responsável por identificar qual linha esta selecionada para clicar nos botões corretos no grid
 * @param array - dadosCliente, array com os dados do cliente, caso o mesmo ainda não tenha sido inserido no pedido de compra
 * @param boolean - cliente_informado, indicador que informa se o cliente ja foi informado no pedido de venda 
 */
function empenharProduto(data, cliente_informado, dadosCliente, idLinha) {

    // n = id da linha do produto
    var n = idLinha ? idLinha : mvLojas.getIdLineGrid(data.cod_nro_produto, data.cod_cor_produto, data.cod_tipo_voltagem);

    casper.echo("Aguardando 10s para abrir detalhes do produto.");
    casper.wait(10000, function () {
        casper.echo("");
        casper.echo(".. Abrindo detalhes do produto.");
        mvLojas.waitForLoad();
    });

    // se existe cliente e não foi informado, então validar msg de erro solcitando que seja informado o cliente
    casper.then(function () {

        //utils.takeSS("produto_detalheProduto0");

        if (!cliente_informado) {

            cliente_informado = true;

            casper.then(function () {
                mvLojas.click("#btn-detalhes-item-" + n);
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                casper.waitForText("Favor informar um cliente!", function success() {
                    casper.test.pass("Favor informar um cliente!");
                    mvLojas.click(".modal.alert.in .btn.Ok");
                }, function fail() {
                    utils.takeSS('produto_validacaoNaoFoifeita');
                    casper.test.fail("Não foi feita validação. Detalhes do produto aberto sem informar o cliente.");
                });
            });

            casper.then(function () {
                informarCliente(dadosCliente);
            });

        }
    });

    casper.then(function () {
        mvLojas.click("#btn-detalhes-item-" + n);
    });
    
    casper.then(function() {
       this.wait(3000); 
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // aguarda aparecer na tela
    casper.wait(3000, function () {
        this.waitForSelector("#modal-detalhes-produto", function () {
            utils.takeSS("produto_detalheProduto");
        });
    });

    casper.then(function () {

        if (data.empenho.ind_valid_regra_cad_endereco) {

            casper.waitForText("Não existe regras cadastradas para este endereco.", function success() {
                casper.test.pass("Validacao de inexistencia de regra pra  o endereço OK.");
                utils.takeSS("produto_validacaoMsgRegraenderecoNaoCad");
                mvLojas.click(".modal.in .btn.Ok");
            }, function fail() {
                utils.takeSS('produto_erroValidacaoCadRegraEnderecor');
                casper.test.fail("Não foi feita a validação cadastro de regra para o endereco.");
            });

        }

    });

    casper.then(function () {
        casper.echo(".. Empenhando produto.");
    });

    // caso seja necessário "Informações de vendas"
    casper.then(function () {
        if (data.empenho.info_venda) {
            mvLojas.$valCombo("#1_nro_grupo_estoque", data.empenho.info_venda.ge);
            mvLojas.$val("#1_desc_info_adicional", data.empenho.info_venda.nro_serie);
        }
    });

    casper.then(function () {
        casper.echo(".. Local de empenho: " + data.empenho.local_empenho + " - " + data.empenho.desc_local_empenho);
        casper.echo(".. Opcao de entrega: " + data.empenho.opcao_entrega + " - " + data.empenho.desc_opcao_entrega);
        casper.echo("");
    });

    casper.then(function () {
    	mvLojas.$valCombo("#cbx-local-empenho", data.empenho.local_empenho);
        mvLojas.$valCombo("#cbx-opcao-entrega", data.empenho.opcao_entrega);
    });

    // TODO regras de empenho início de desenvolvimento
    casper.then(function () {

        utils.takeSS("aposInformarEmpenho");

        //empenho 'LOJA', local empenho 'NORMAL'
        if (data.empenho.local_empenho == 0 && data.empenho.opcao_entrega == 0) {
            empenhoLojaRetiradaNormal(data.empenho);
        }
        //empenho 'LOJA', local empenho 'RETIRADA LOJA COM RETENÇÃO'
        else if (data.empenho.local_empenho == 0 && data.empenho.opcao_entrega == 2) {
            empenhoLojaRetiradaLojaRetencao(data.empenho);
        }
        //empenho 'LOJA', local empenho 'NÃO ENTREGA' ... essa condição acontecera apenas em INLOJAD
        else if (data.empenho.local_empenho == 0 && data.empenho.opcao_entrega == 7) {
            empenhoLojaNaoEntrega(data.empenho);
        }
        //empenho 'DEPOSITO', local empenho 'AGENDADA'
        else if (data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 6) {
            empenhoDepositoAgendada(data.empenho);
        }
        //empenho 'DEPOSITO', local empenho 'NORMAL'
        else if (data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 0) {
            empenhoDepositoNormal(data.empenho);
        }
        //empenho 'DEPOSITO', local empenho 'RETIRADA DEPOSITO'
        else if (data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 1) {
            empenhoDepositoRetiradaDeposito(data.empenho);
        }
        //empenho 'DEPOSITO', local empenho 'RETIRADA OUTRA LOJA COM EMPENHO DEPOSITO'
        else if (data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 3) {
            empenhoDepositoRetOutraLojaEmpeDepesito(data.empenho);
        }
        //empenho 'DEPOSITO', local empenho 'AGENDADA CLIENTE'
        else if (data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 4) {
            empenhoDepositoAgendadaCliente(data.empenho);
        }
        //empenho 'DEPOSITO', local empenho 'NÃO ENTREGA'
        else if (data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 7) {
            empenhoDepositoNaoEntrega(data.empenho);
        }
        //empenho 'OUTRA LOJA', local empenho 'NORMAL'
        else if (data.empenho.local_empenho == 2 && data.empenho.opcao_entrega == 0) {
            empenhoOutraLojaNormal(data.empenho);
        }

    });
    // TODO regras de empenho termina aqui

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
    	//casper.echo(".. chx-ind-entrega setado para " + data.empenho.entrega);
        mvLojas.$valCheck("#chx-ind-entrega", data.empenho.entrega);
    });

//    casper.then(function () {
//    	
//    	if (((data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 0) || (data.empenho.local_empenho == 0 && data.empenho.opcao_entrega == 2)) && data.empenho.data.empenho_retencao) {
//    		this.echo(".. Informando a data.empenho Retencao");
//    		mvLojas.$val("#txt-dt-retencao", data.empenho.data.empenho_retencao);
//    	}
//    	
//    });

    casper.then(function () {
        utils.takeSS("produto_aposDefinirOpcaoEntrega");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

//	casper.then(function() {
//		utils.waitForValue("#txt-data.empenho-entrega", data.empenho.data.empenho_entrega_cliente);
//		utils.waitForValue("#txt-desc-turno-entrega", data.empenho.desc_turno);
//	});

    casper.then(function () {
        utils.takeSS("produto_telaEmpenhoProduto");
    });

    casper.then(function () {
        casper.echo(".. Clicando no botao empenhar produto.");
        
        //setTimeout(function(){ casper.test.pass(".. Aguardando 3 segundos para empenhar produto."); return true; }, 3000);
        
        mvLojas.click("#btn-empenhar-produto");
    });

    casper.then(function () {
        casper.echo(".. Aguardando modal de detalhes de produto ser fechada.");
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        if ((data.empenho.local_empenho == 1 && data.empenho.opcao_entrega == 3) || (data.empenho.local_empenho == 2 && data.empenho.opcao_entrega == 0)) {
            selecionarLojaParaEmpenho(data.empenho);
        }
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

//    casper.then(function () {
//        mvLojas.waitForLoad();
//    });

    casper.then(function () {
        this.waitForSelector("#modal-detalhes-produto", function () {
            utils.takeSS("produto_modalDetalhesProduto");
        });
    });
    
	  casper.then(function () {
		  mvLojas.waitForLoad();
	  });
	  
	  //setTimeout(function(){ casper.test.pass(".. Aguardando 3 segundos após empenhar produto."); return true; }, 3000);
	  
	  //verifica se o sistema nao emite mensagem de sucesso apos empenho
	  casper.then(function () {
	        if (data.empenho.empenha_sem_emitir_mensagem) {
	            return;
	        }else{
	        	casper.echo("Aguardando 10seg para abrir modal de confirmação!!");
	        	casper.wait(10000, function () {
	      	      casper.waitForText("salvo com sucesso.", function success() {
	      	          casper.test.pass("Empenho salvo com sucesso.");
	      	          utils.takeSS("empenho_salvo");
	      	          mvLojas.click(".modal.alert.in .btn.Ok");
	      	      }, function fail() {
	      	          utils.takeSS('erroAoEmepenhar');
	      	          casper.test.fail("Empenho nao realizado.");
	      	      });
	      	  });
	        }
	  });

}

/**
 * função resnposável por selecionar uma loja, no caso de Empenho depósito com retirada em outra loja com empenho depósito
 * @param data, dados para empenhar produto
 */
function selecionarLojaParaEmpenho(data) {

    casper.then(function () {
        this.echo(".. Entrei na funcao de selecionar a loja para empenho");
        this.waitForSelector("#modal-lojas-empenho", function () {
            this.test.pass("Modal de Lojas para Empenho Aberta com sucesso");
            utils.takeSS("modalLojasEmpenho");
        });

    });

    casper.then(function () {
        casper.then(mvLojas.findRowTable('#grd-lojas-empenho', {
            cod_nro_loja: data.nro_loja_empenho
        }, function (selector) {            
            mvLojas.sellHideLineGrid("#grd-lojas-empenho", mvLojas.idLineGrid(selector));
            utils.takeSS("lojaEmpenhoSelecioanda");
        }));
    });

    casper.then(function () {
        //utils.takeSS("modalLojasEmpenhoSelecionada");
        this.echo(".. Clicando em selecionar loja de empenho");
        mvLojas.click("#btn-escolher-loja-empenho");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

}

/**
 * Função responsável por editar os dados de um produto na grid principal
 * @param data - array, array com dados a serem editados
 * @param nLinha - int, inteiro com o número da linha a ser editado
 */
function editarLinhaGrid(data, nLinha) {

    casper.then(function () {
        casper.echo("");
        data.qtd ? casper.echo(".. Quantidade: " + data.qtd) : "";
        data.tabela ? casper.echo(".. Tabela: " + data.tabela) : "";
        data.valor_unitario ? casper.echo(".. Valor unitário: R$ " + data.valor_unitario) : "";
    });

    casper.then(function () {
        if (data.qtd) {
            mvLojas.$val("#grd-produtos tr[id='" + nLinha + "'] input[name='qtde_pedida_produto']", data.qtd);
        }
    });

    casper.then(function () {
        if (data.tabela) {
            mvLojas.$valCombo("#grd-produtos tr[id='" + nLinha + "'] select[data-column='cod_tipo_tabela']", data.tabela);
        }
    });

    casper.then(function () {
        if (data.valor_unitario) {
            mvLojas.$val("#grd-produtos tr[id='" + nLinha + "'] input[name='vr_unitario_produto']", data.valor_unitario);
        }
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("produto_linhaGridProdutoAlterada_" + nLinha);
    });

}

//FUNÇÕES PARA TRATAR AS FORMAS DE EMPENHO

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'LOJA', Opção de entrega 'NORNAL'
 * 
 * @param data, array informações de empenho
 */
function empenhoLojaRetiradaNormal(data) {}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'LOJA', Opção de entrega 'RETIRADA LOJA RETENÇÃO'
 * 
 * @param data, array informações de empenho
 */
function empenhoLojaRetiradaLojaRetencao(data) {

    casper.echo(".. Informando a data de retencao");
    data.data_retencao = mvLojas.dataAdd(10);
    mvLojas.$val("#txt-dt-retencao", data.data_retencao);
    utils.takeSS("dataRetencao");

}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'LOJA', Opção de entrega 'NÃO ENTREGA'
 * 
 * @param data, array informações de empenho
 */
function empenhoLojaNaoEntrega(data) {}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'DEPOSITO', Opção de entrega 'AGENDADA'
 * 
 * @param data, array informações de empenho
 */
function empenhoDepositoAgendada(data) {

	var dataEntregaCliente = mvLojas.setDataAgendada();
	
	casper.then(function () {
		data.data_entrega_cliente = dataEntregaCliente;
		data.data_entrega_cliente_erro = dataEntregaCliente;
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.echo(".. Clicando no botao 'Agendada' para selecionar a data e turno de entrega");
        mvLojas.click("#btn-buscar-data-entrega-agendada");
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });

    //chama a função para selecionar uma data disponível no sistema
    casper.then(function () {
        selecionarDataParaEntregaAgendada(data);
    });

    casper.then(function () {
        utils.takeSS("dataSelecionada");
        utils.waitForValue("#frm-detalhes-produto #txt-data-entrega", data.data_entrega_cliente);
        utils.waitForValue("#frm-detalhes-produto #txt-desc-turno-entrega", data.desc_turno);
    });
}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'DEPOSITO', Opção de entrega 'NORMAL'
 * 
 * @param data, array informações de empenho
 */
function empenhoDepositoNormal(data) {

    casper.echo(".. Informando a data.empenho Retencao");
    mvLojas.$val("#txt-dt-retencao", data.data_retencao);
    utils.takeSS("dataRetencao");

}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'DEPOSITO', Opção de entrega 'RETIRADA DEPÓSITO'
 * 
 * @param data, array informações de empenho
 */
function empenhoDepositoRetiradaDeposito(data) {

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.echo(".. Validando informacoes com local de epemnho 'Depósito' com opcao de entrega 'RETIRADA DEPOSITO'");
        !mvLojas.$valCheck("#chx-ind-entrega") ? this.test.pass("Opção Entrega: 'NAO'") : this.test.pass("Opção Entrega: 'SIM'");
    });

    casper.then(function () {

        this.waitForText("Cliente deverá retirar o produto no deposito. Empenho so permitido em deposito", function () {
            casper.test.pass("Cliente deverá retirar o produto no deposito. Empenho so permitido em deposito");
            utils.takeSS("msgInfoCliente");
        });

    });

}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'DEPOSITO', Opção de entrega 'RETIRADA OUTRA LOJA COM EMPENHO DEPOSITO'
 * 
 * @param data, array informações de empenho
 */
function empenhoDepositoRetOutraLojaEmpeDepesito(data) {}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'DEPOSITO', Opção de entrega 'AGENDADA CLIENTE'
 * 
 * @param data, array informações de empenho
 */
function empenhoDepositoAgendadaCliente(data) {

	var dataEntregaCliente = mvLojas.setDataAgendadaCliente();
	
	data.data_entrega_cliente = dataEntregaCliente;
	data.data_entrega_cliente_erro = dataEntregaCliente;
	
	if (data.ind_validacao_data_agendamento) {
        
    	casper.then(function () {
            this.echo(".. Informando a data escolhida pelo cliente: " + data.data_entrega_cliente);
            mvLojas.$val("#div-entrega-agendada-cliente #txt-data-entrega-cliente", data.data_entrega_cliente);
        });

        casper.then(function () {
            this.echo(".. Informando o periodo escolhido pelo cliente: " + data.desc_turno);
            mvLojas.$valCombo("#div-entrega-agendada-cliente #cbx-turno-entrega", data.turno);
        });

        casper.then(function () {
            this.echo(".. Validando se a data escolhida pelo cliente esta OK.");
            utils.takeSS("dataInformada");
            mvLojas.click("#btn-validar-data");
        });

        casper.then(function () {
            mvLojas.waitForLoad();
        });
        
    } else {
        casper.then(function () {
            this.echo(".. Informando a data escolhida pelo cliente nao valida: " + data.data_entrega_cliente_erro);
            mvLojas.$val("#div-entrega-agendada-cliente #txt-data-entrega-cliente", data.data_entrega_cliente_erro);
        });

        casper.then(function () {
            this.echo(".. Informando o periodo escolhido pelo cliente nao valido: " + data.desc_turno_erro);
            mvLojas.$valCombo("#div-entrega-agendada-cliente #cbx-turno-entrega", data.turno_erro);
        });

        casper.then(function () {
            this.echo(".. Validando se a data escolhida pelo cliente esta OK.");
            utils.takeSS("dataInformada");
            mvLojas.click("#btn-validar-data");
        });

        casper.then(function () {
            mvLojas.waitForLoad();
        });

        casper.then(function () {
            //chama a função para selecionar uma data disponível no sistema
            selecionarDataParaEntregaAgendada(data);
        });
    }
    


//	casper.then(function () {
//		utils.takeSS("dataSelecionada");
//		utils.waitForValue("#div-entrega-agendada-cliente #txt-data-entrega-cliente", data.data_entrega_cliente);
//		utils.waitForValue("#div-entrega-agendada-cliente #cbx-turno-entrega", data.turno);
//	});
}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'DEPOSITO', Opção de entrega 'NÃO ENTREGA'
 * 
 * @param data, array informações de empenho
 */
function empenhoDepositoNaoEntrega(data) {

    casper.then(function () {
        this.echo(".. Validando informacoes com local de empenho 'Depósito' com opcao de entrega 'NAO ENTREGA'");
        !mvLojas.$valCheck("#chx-ind-entrega") ? this.test.pass("Opção Entrega: 'NAO'") : this.test.pass("Opção Entrega: 'SIM'");
    });

}

/**
 * função para fazer validações e preenchar informações para a seguinte situação
 * Local de empenho 'OUTRA LOJA', Opção de entrega 'NORMAL'
 * 
 * @param data, array informações de empenho
 */
function empenhoOutraLojaNormal(data) {

}
//FIM FUNÇÕES PARA TRATAR AS FORMAS DE EMPENHO

/**
 * função responsável por selecionar uma promoção ou fechar a tela da mesma
 * @param data, array com os dados da promoção
 */
function validarPromocao(data) {
   
    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.waitForSelector(".mdl-consulta-promocao", function () {
            utils.takeSS("modalConsultaPromocao");
            this.test.pass("Modal de promocao aberta!");
        });
    });

    /**
     * Verifica se a promocao ja esta inserida na grid de promocoes
     * @param data, array com os dados da promocao a ser validada
     */
    casper.then(function () {
        if (!data.ind_sel_promo) {
            casper.echo(".. Fechando janela de promocao sem informar promocao");
            mvLojas.click(".mdl-consulta-promocao .btn.Fechar");
            return false;
        }else{
        	casper.then(mvLojas.findRowTable('#grid-consulta-promocao', {
        		desc_tipo_venda: data.tipo_venda,
        		vr_financiado: data.valor_promocao
            }, function (selector) {
            	if (selector == false) {
            		this.test.fail("Promocao nao encontrada na grid de promocoes.");
            		utils.takeSS("promocaoNaoEncontrada");
            	} else {
            		this.test.pass("Promocao '" + data.valor_promocao + "' existe na grid de promocoes.");
            		utils.takeSS("promocaoEncontrada");
            		
            		casper.then(function () {
            			this.echo(".. Selecionando a promocao: " + data.valor_promocao);
            			//casper.echo(selector);
            			//this.mouse.click(selector);
            	        mvLojas.sellLineGridPromocao(data.tipo_venda, data.valor_promocao);
            	    });
            		
            		casper.then(function () {
            	        mvLojas.waitForLoad();
            	    });
            		
            		casper.then(function () {
            			utils.takeSS("promocaoSelecionada");
            			this.echo(".. Clicando no botao Ok para sair da tela de promocao");
            	        this.mouse.click(".mdl-consulta-promocao.in .btn.Ok");
            	    });
            		
            		casper.then(function () {
            	        mvLojas.waitForLoad();
            	    });
            		
            	}
            	
            }));
        }
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });

}

/**
 * Seleciona uma data na modal de Datas de Entrega Disponiveis, para agendar a entrega ao cliente
 */
function selecionarDataParaEntregaAgendada(data) {

	casper.echo("Aguardando para abrir o modal de data de entrega!!");
	casper.wait(1000, function () {
        this.waitForSelector("#modal-data-entrega", function () {
            utils.takeSS("modalDataEntrega");
        });
    });

    casper.then(function () {
        casper.echo(".. Data e periodo valida a ser selecionada: " + data.data_entrega_cliente + " - " + data.desc_turno);
    });

    casper.then(function () {

    	casper.then(mvLojas.findRowTable('#grd-data-entrega', {
            data: data.data_entrega_cliente,
            desc_turno_entrega: data.desc_turno
        }, function (selector) {
            this.test.pass("Data de entrega selecionada");
            mvLojas.sellHideLineGrid("#grd-data-entrega", mvLojas.idLineGrid(selector));
        }));
    });

    casper.then(function () {
        mvLojas.click("#modal-data-entrega #btn-escolher-data");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

}