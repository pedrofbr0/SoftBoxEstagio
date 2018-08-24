var require = patchRequire(require);

var qtdEnderecos = 0;
var cpf;

/**
 * Cadastro Simples de cliente, feito para compras no dinheiro ou cartão de crédito
 * 
 * @paramns dadosCliente, dados do cliente a cadastrar
 */
exports.cadatroSimplesCliente = function (dadosCliente) {

    casper.then(function () {
        casper.echo(".. Cadastrando cliente");
    });

    casper.then(function () {
        cpf = mvLojas.getElementValue("#txt-cpf-cadastro-cliente");
    });

    casper.then(function () {

        if (cpf == dadosCliente.cpf) {
            casper.echo(".. Validando cpf");
            casper.test.pass('CPF OK.');
        } else {
            casper.test.pass('CPF não confere');
        }
    });

    // chama a função que preenche os pricipais dados do cadastro de cliente
    casper.then(function () {
        preencherCamposPrincipais(dadosCliente);
    });

    //chama a função responsável por cadastrar o endereço do cliente
    casper.then(function () {

        qtdEnderecos = dadosCliente.endereco.length;

        for (var i = 0; i < qtdEnderecos; i++) {
            cadastrarEndereco(dadosCliente.endereco[i], i + 1);
        }

    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("antesSalvarCliente");
    });

    casper.then(function () {
        salvarCliente();
    });

};

/**
 * Cadastro de cliente completo, para compra no carne losango
 * 
 * @param dadosCliente, array com os dados do cliente
 */
exports.cadatroCompletoCliente = function (dadosCliente) {
    casper.then(function () {
        casper.echo(".. Cadastrando cliente");
    });

    casper.wait(3000, function () {
        cpf = mvLojas.getElementValue("#txt-cpf-cadastro-cliente");
    });
    
    casper.wait(3000, function () {
    	casper.echo("CPF: " + cpf + " : " + dadosCliente.cpf);
    	
        if (cpf == dadosCliente.cpf) {
            casper.echo(".. Validando cpf");
            casper.test.pass('CPF OK.');
        } else {
            casper.test.fail('CPF não confere');
            takeSS("cpfNaoConfere");
        }
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
    // chama a função que preenche os pricipais dados do cadastro de cliente
    casper.then(function () {
        preencherCamposPrincipais(dadosCliente);
    });

    //chama a função responsável por cadastrar o endereço do cliente
    casper.then(function () {
        telaCadEndereco.cadastrarEndereco(dadosCliente.endereco);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("aposCadastrarEnderecos");
    });

    casper.then(function () {
        mvLojas.click("#modal-cadastro-content #lnk-modal-trabalho");
    });

    // aguarda modal abrir
    casper.then(function () {
        mvLojas.waitModalOpenEvt("#mdl-trabalho");
    });

    casper.then(function () {
        casper.echo(".. modal de identificação/trabalho aberta");
    });

    casper.then(function () {
        cadastrarIdentificacaoTrabalho(dadosCliente.identificacao_trabalho);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("printAposFecharDetalhesCadastro");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        qtdReferencias = dadosCliente.referencias.length;

        casper.then(function () {
            casper.echo(".. Quantidade de referências: " + qtdReferencias);
        });
        
        if (qtdReferencias > 0) {
            casper.then(function () {
                casper.echo("");
                casper.echo(".. Cadastrando Referências.");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });
            
            casper.then(function () {
                utils.takeSS("antesCliquenaAbaReferencias");
            });
            
            casper.then(function () {
                mvLojas.click("#tab-cadastro-cliente li a[href='#aba-referencias']");
            });

            casper.then(function () {
                utils.takeSS("clicandoNaAbaReferencias");
            });
            
            casper.then(function () {
                for (var i = 0; i < qtdReferencias; i++) {
                	cadastrarReferencias(dadosCliente.referencias[i], i + 1, qtdReferencias);
                }
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {

                if (mvLojas.gridLength('#grd-referencias') == qtdReferencias) {
                    casper.test.pass('Referencias inseridas com sucesso.');
                } else {
                    utils.takeSS("erroNaQuantidadeReferencia");
                    casper.test.fail('Erro ao inserir referencias');
                }
            });

            casper.then(function () {
                utils.takeSS("aposInserirTodasAsReferencias");
            });
        }
    });

    casper.then(function () {
        if (!utils.isEmpty(dadosCliente.cartao_mv_shop)) {
            cadastraCartaoMVShop(dadosCliente.cartao_mv_shop);
        }
    });

    casper.then(function () {
        salvarCliente();
    });

};

/**
 * Cadastra um cliente PJ (Pessoa Jurídica
 * @paramm dadosCliente, array com os dados do cliente
 */
exports.cadatroClientePessoaJuridica = function (dadosCliente) {

    casper.then(function () {
        this.echo('.. Gerando CNPJ valido.');
        cnpj = mvLojas.gerarCnpj();
    });

    casper.then(function () {
        dadosCliente.form_principal.cpf_cnpj = cnpj;
    });

    casper.then(function () {
        this.echo('.. Cadastrando um cliente Pessoa Juridica');
        mvLojas.$valCheck("#ckb-tipo-pessoa", false);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.echo('.. Inserindo CNPJ: ' + dadosCliente.form_principal.cpf_cnpj);
        mvLojas.$val("#form-consulta-cliente #cbx-cnpj", dadosCliente.form_principal.cpf_cnpj);
    });

    casper.then(function () {
        utils.takeSS("antesNovoCliente");
    });

    casper.then(function () {
        mvLojas.click("#btn-novo-cliente");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#mdl-cadastro-cliente");
    });

    casper.then(function () {
        utils.takeSS("modalPedidoCompra");
    });

    casper.then(function () {
        telaCadClienteFormPrincipal.cadFormPrincipal(dadosCliente.form_principal, true);
    });

    casper.then(function () {
        telaCompradorRepresentante.cadCompradorRepresentante(dadosCliente.comprador_representante);
    });

    casper.then(function () {
        telaCadEndereco.cadastrarEndereco(dadosCliente.enderecos);
    });

    casper.then(function () {
        mvLojas.click("#mdl-cadastro-cliente a[href='#aba-referencias']");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
        utils.takeSS("abaReferencia");
    });

    casper.then(function () {
        telaCadReferencias.cadReferencias(dadosCliente.referencias);
    });

    casper.then(function () {
        telaSalvarCliente.salvar();
    });

    //TODO CÓDIGO PARA RODAR TESTE SEM A NECESSIDADE DE CADASTRAR UM CLIENTE

    casper.then(function () {
        mvLojas.waitForLoad();
    });

};

/**
 * Função responsável por prencher os principais dados do cliente na tela de cadastro de cliente
 * 
 * @param dadosCliente, array com os do cliente
 */
function preencherCamposPrincipais(dadosCliente) {
    casper.then(function () {
        mvLojas.$val('#txtNome', dadosCliente.nome);
        mvLojas.$val("#txtEmail", dadosCliente.email);
        mvLojas.$val("#txtDddResidencial", dadosCliente.ddd_residencial);
        mvLojas.$val("#txtNroTelefoneResidencial", dadosCliente.fone_residencial);
        mvLojas.$val("#txtDddCelular", dadosCliente.ddd_celular);
        mvLojas.$val("#txtNroTelefoneCelular", dadosCliente.celular);
        mvLojas.$valCombo("#frm-cadastro-cliente #cbx-nro-nivel-cadastro", dadosCliente.grupo);
        mvLojas.$valCombo("#frm-cadastro-cliente #cbx-tipo-correspondencia", dadosCliente.correspondencia);

        casper.echo(".. Nome cliente: " + dadosCliente.nome);
        casper.echo(".. E-mail: " + dadosCliente.email);
        casper.echo(".. Fone Residencial: (" + dadosCliente.ddd_residencial + ")" + dadosCliente.fone_residencial);
        casper.echo(".. Celular: (" + dadosCliente.ddd_celular + ")" + dadosCliente.celular);
    });

    casper.then(function () {
        utils.takeSS("camposPrincipaisCadastroClientePreenchidos");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
}

/**
 * Função responsável por cadastrar o endereço do cliente
 * 
 * @param dadosCliente array com os dados do cliente
 * @param n inteiro que contem a sequencia do endereço que esta sendo salvo, ex(endereco n(1), endereco n(2), ...)
 */
function cadastrarEndereco(endereco, n) {

    casper.then(function () {
        mvLojas.click("#btn-novo-endereco");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {

        if (endereco.cep == "" || endereco.cep == undefined || endereco.cep == null) {
            mvLojas.$val("#txt-nome-cidade", endereco.cidade);
        } else {
            mvLojas.$val("#txt-cep-pesquisa-endereco", endereco.cep);
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

    //Verifica se foi encontrado algum endereço
    casper.wait(3000, function () {
        if (mvLojas.gridLength('#grd-pesquisa-endereco') > 0) {
            utils.takeSS('CEP encontrado');
            casper.test.pass('CEP encontrado');
        } else {
            utils.takeSS("cefNaoEncontrado_" + n);
            casper.test.fail('CEP não encontrado');
        }
    });

    if (endereco.indCepUnicoCidade == 1) {

        casper.then(function () {

            this.echo(".. CEP informado e um cep unico da cidade!");

            utils.takeSS("grid com registro");
            casper.then(function () {
                casper.then(mvLojas.findRowTable('#grd-pesquisa-endereco', {
                    cidade: endereco.cidade
                }, function (selector) {
                    this.mouse.click(selector);
                }));
            });

        });

        casper.then(function () {
            casper.echo(".. Selecionando endereço");
        });

        casper.then(function () {
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
            mvLojas.$valCombo(endereco.estado, "#frm-cadastro-logradouro #cbx-uf-cad");
        });

        casper.then(function () {
            mvLojas.waitForLoad();
        });

        casper.wait(3000, function () {
            casper.echo(".. cod_cidade: " + endereco.cod_cidade);
            casper.echo(".. tipo logradouro: " + endereco.tipo_logradouro);
            casper.echo(".. logradouro: " + endereco.logradouro);

            mvLojas.$valCombo("#frm-cadastro-logradouro #cbx-cod-cgl-cad", endereco.cod_cidade);
            mvLojas.$valCombo("#frm-cadastro-logradouro select[data-column='cod_tipo_logradouro']", endereco.tipo_logradouro);
            mvLojas.$val("#frm-cadastro-logradouro input[data-column='desc_logradouro']", endereco.logradouro);
        });

        casper.then(function () {
            utils.takeSS("ModaCadastroLogradouro");
        });

        casper.then(function () {
            mvLojas.waitForLoad();
        });

        casper.wait(3000, function () {
            mvLojas.click("#btn-salvar-logradouro");
        });

        casper.then(function () {
            mvLojas.waitForLoad();
        });

        casper.then(function () {
            utils.takeSS("salvarLogradouro_" + n);
        });

        // valida se existe a mensagem informando que o cliente foi salvo.
        casper.then(function () {
            casper.waitForText("Logradouro salvo com sucesso!", function success() {
                casper.test.pass("Logradouro salvo com sucesso!");
                mvLojas.click('div.modal.in, btn');
            }, function fail() {
                utils.takeSS('erroAoSalvarLogradouro' + n);
                casper.test.fail("Erro ao salvar logradouro");
            });
        });

    } else {

        casper.then(function () {

            utils.takeSS("grid com registro");
            casper.then(function () {
                casper.then(mvLojas.findRowTable('#grd-pesquisa-endereco', {
                    cep: endereco.cep
                }, function (selector) {
                    this.mouse.click(selector);
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
            utils.takeSS("modalComplementoEndereço_" + n);
        });

        casper.then(function () {
            mvLojas.waitModalOpenEvt("#mdl-endereco-cliente");
        });
    }

    casper.then(function () {
        mvLojas.waitForLoad();
    });



//	casper.then(function () {
//		casper.echo(".. validando endereço.");
//		utils.waitForValue("#mdl-endereco-cliente input[data-column='cep']", endereco.cep);
//		utils.waitForValue("#mdl-endereco-cliente input[data-column='nome_localidade']", endereco.cidade);
//		utils.waitForValue("#mdl-endereco-cliente input[data-column='uf']", endereco.estado);
//		utils.waitForValue("#mdl-endereco-cliente input[data-column='nome_bairro']", endereco.bairro);
//		utils.waitForValue("#mdl-endereco-cliente input[data-column='desc_logradouro']", endereco.desc_logradouro);
//		
//	});

    casper.then(function () {

        casper.then(function () {
            mvLojas.$valCheck("#cbx-sem-numero", endereco.ind_numero);
        });

        casper.then(function () {
            mvLojas.$val("#mdl-endereco-cliente input[data-column='nro_endereco_cliente']", endereco.numero);
            mvLojas.$valCombo("#cbx-tipo-complemento-endereco", endereco.cod_complemento_endereco);
            mvLojas.$val("#mdl-endereco-cliente input[data-column='complemento_endereco_cliente']", endereco.complemento);

            mvLojas.$val("#dp-residencia-cliente", endereco.reside_desde);

            mvLojas.$valCombo("#mdl-endereco-cliente select[data-column='cod_situacao_residencia']", endereco.situacao_residencia);
            mvLojas.$valCombo("#mdl-endereco-cliente select[data-column='nro_tipo_endereco']", endereco.tipo_endereco);
        });

    });

    casper.then(function () {
        utils.takeSS("camposPreenchidosAntesSalvar_" + n);
    });

    casper.then(function () {
        mvLojas.click("#btn-salvar-endereco");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("camposPreenchidos_" + n);
    });
}

/**
 * Função responsável por cadastrar os dados (detalhes pessoais) e dados profissionais
 * 
 * @param dados, array com informações pessoais e profissionais
 */
function cadastrarIdentificacaoTrabalho(dados) {

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // printando informações de RG a serem inseridas na tela
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo dados da Identidade.");
        casper.echo(".. Orgão: " + dados.identidade.orgao);
        casper.echo(".. UF: " + dados.identidade.estado);
        casper.echo(".. Nº Identidade: " + dados.identidade.rg);
        casper.echo(".. Nº Identidade: " + dados.identidade.data_emissao);
    });

    // inserindo informações de RG na tela
    casper.then(function () {

        mvLojas.$valCombo("#frm-identificacao-cliente #cbx-orgao-emissor", dados.identidade.cod_orgao);
        mvLojas.$valCombo("#frm-identificacao-cliente #cbx-uf", dados.identidade.estado);
        mvLojas.$val("#frm-identificacao-cliente input[data-column='identidade_nro']", dados.identidade.rg);
        mvLojas.$val("#frm-identificacao-cliente #identidade_dt_emissao", dados.identidade.data_emissao);

    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("informacoesIdentidade");
    });

    // printando dados pessoais
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo dados pessoais.");
        casper.echo(".. Nome Mãe: " + dados.dados_pessoais.nome_mae);
        casper.echo(".. Nome Pai: " + dados.dados_pessoais.nome_pai);
        casper.echo(".. Data Nascimento: " + dados.dados_pessoais.data_nascimento);
        casper.echo(".. Sexo: " + dados.dados_pessoais.sexo);
        casper.echo(".. Nacionalidade: " + dados.dados_pessoais.nacionalidade);
        casper.echo(".. Escolaridade: " + dados.dados_pessoais.nom_escolaridade);
        casper.echo(".. Naturalidade: "+ dados.dados_pessoais.naturalidade_cod_cidade +" | " + dados.dados_pessoais.naturalidade_cidade + " | " + dados.dados_pessoais.naturalidade_mg);
        casper.echo(".. Estado Civil: " + dados.dados_pessoais.estado_civil);
        casper.echo(".. Sexo Conjuge: " + dados.dados_pessoais.sexo_conjuge);
        casper.echo(".. Nome Conjuge: " + dados.dados_pessoais.nome_conjuge);
    });

    // inserindo informações de RG na tela
    casper.then(function () {

        casper.then(function () {
            mvLojas.$val("#frm-identificacao-cliente input[data-column='nome_pai']", dados.dados_pessoais.nome_pai);
            mvLojas.$val("#frm-identificacao-cliente #nome_mae", dados.dados_pessoais.nome_mae);
            mvLojas.$val("#frm-identificacao-cliente #identidade_ddd", dados.dados_pessoais.data_nascimento);
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-sexo", dados.dados_pessoais.sexo);
            mvLojas.$valCombo("#frm-identificacao-cliente #flag_nacionalidade", dados.dados_pessoais.nacionalidade);
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-grau-escolaridade-pessoais", dados.dados_pessoais.escolaridade);
        });

        casper.then(function () {
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-uf-naturalidade", dados.dados_pessoais.naturalidade_mg);
        });

        casper.then(function () {
            mvLojas.waitForLoad();
        });

        casper.then(function () {
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-estado-civil", dados.dados_pessoais.cod_estado_civil);
            mvLojas.$valCombo("#frm-identificacao-cliente #cbx-sexo-conjuge", dados.dados_pessoais.sexo_conjuge);
            mvLojas.$val("#frm-identificacao-cliente #nome_conjuge", dados.dados_pessoais.nome_conjuge);
        });

    });

    // printando ocupapção
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo Ocupação.");
        casper.echo(".. Ocupação: " + dados.trabalho.ocupacao);
        casper.echo(".. Cargo: " + dados.trabalho.cargo);
        casper.echo(".. Cod. Profissao: " + dados.trabalho.cod_profissao);
    });

    // inserindo informações Ocupação
    casper.then(function () {
    	mvLojas.$valCombo("#frm-identificacao-cliente #cod-cgl-naturalidade", dados.dados_pessoais.naturalidade_cod_cidade);
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-ocupacao", dados.trabalho.cod_ocupacao);
        //mvLojas.$val("#frm-trabalho-cliente input[data-column='desc_cargo']", dados.trabalho.cargo);
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-profissao", dados.trabalho.cod_profissao);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // Calcula o mês ano da renda principal, pegando 40 dias anteriores a partir da data atual
    dados.renda_principal.mes_ano = mvLojas.setDataRendaPrincipal(-40);
    
    // printando renda principal
    casper.then(function () {
    	casper.echo("");
        casper.echo(".. Renda Principal.");
        casper.echo(".. Tipo Comprovante: " + dados.renda_principal.tipo_comprovante);
        casper.echo(".. Mês/Ano: " + dados.renda_principal.mes_ano);
        casper.echo(".. Renda Líquida: " + dados.renda_principal.renda_liquida);
        casper.echo(".. Número Benefício: " + dados.renda_principal.nro_beneficio);
        casper.echo(".. Orgão Pagador: " + dados.renda_principal.orgao_pagador);
    });

    // inserindo informações Ocupação
    casper.then(function () {
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-tipo-comprovante-renda", dados.renda_principal.cod_tipo_comprovante);
        mvLojas.$val("#frm-trabalho-cliente #dp-comprovante-renda", dados.renda_principal.mes_ano);
        mvLojas.$val("#frm-trabalho-cliente #txt-vr-salario-cliente", dados.renda_principal.renda_liquida);
        dados.renda_principal.nro_beneficio != "" ? mvLojas.$val("#frm-trabalho-cliente #txt-nro-beneficio", dados.renda_principal.nro_beneficio) : "";
        dados.renda_principal.orgao_pagador != "" ? mvLojas.$val("#frm-trabalho-cliente #slt_beneficio_chosen", dados.renda_principal.orgao_pagador) : "";
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("dadosPreencidosAteRendaPrincipal");
    });

    // inserindo informações Outra Renda
    casper.then(function () {

        if (dados.outra_renda.origem != "") {
            casper.echo("");
            casper.echo(".. Outra Renda.");
            casper.echo(".. Origem: " + dados.outra_renda.origem);
            casper.echo(".. Valor: " + dados.outra_renda.valor);


            mvLojas.$valCombo("#frm-trabalho-cliente #cbx-origem-outra-renda", dados.outra_renda.cod_origem);
            mvLojas.$val("#frm-trabalho-cliente #txt-vr-outras-rendas-cliente", dados.renda_principal.valor);
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
        casper.echo(".. Local: " + dados.local_trabalho.local);
        casper.echo(".. CNPJ: " + dados.local_trabalho.cnpj);
        casper.echo(".. CEP: " + dados.local_trabalho.cep);
        casper.echo(".. Endereço: " + dados.local_trabalho.endereco + ", n" + dados.local_trabalho.numero);
        casper.echo(".. Bairro: " + dados.local_trabalho.bairro);
        casper.echo(".. Cidade: " + dados.local_trabalho.cidade);
        casper.echo(".. Estado: " + dados.local_trabalho.estado);
        casper.echo(".. Fone: (" + dados.local_trabalho.ddd + ") " + dados.local_trabalho.fone + " " + dados.local_trabalho.ramal);
        casper.echo(".. Trabalha desde: " + dados.local_trabalho.trabalha_desde);
    });

    casper.then(function () {

        mvLojas.$val("#frm-trabalho-cliente input[data-column='local_trabalho_cliente']", dados.local_trabalho.local);
        mvLojas.$val("#frm-trabalho-cliente #txtCnpjOcupacao", dados.local_trabalho.cnpj);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='cep_comercial']", dados.local_trabalho.cep);
        mvLojas.$val("#frm-trabalho-cliente #txt-endereco-trabalho-cliente", dados.local_trabalho.endereco);
        mvLojas.$val("#frm-trabalho-cliente #txt-numero-trabalho-cliente", dados.local_trabalho.numero);
        mvLojas.$val("#frm-trabalho-cliente #txt-complemento-trabalho-cliente", dados.local_trabalho.complemento);
        mvLojas.$val("#frm-trabalho-cliente #txt-bairro-trabalho-cliente", dados.local_trabalho.bairro);
        mvLojas.$val("#frm-trabalho-cliente #txt-cidade-trabalho-cliente", dados.local_trabalho.cidade);
        mvLojas.$valCombo("#frm-trabalho-cliente #cbx-uf-local-trabalho", dados.local_trabalho.estado);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='fone_trabalho_ddd']", dados.local_trabalho.ddd);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='fone_trabalho_nro']", dados.local_trabalho.fone);
        mvLojas.$val("#frm-trabalho-cliente input[data-column='fone_trabalho_ramal']", dados.local_trabalho.ramal);
        mvLojas.$val("#frm-trabalho-cliente #dp-trabalha-desde", dados.local_trabalho.trabalha_desde);

    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("telaPreencida");
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

}

/**
 * Função responsável com cadatrar uma referência pessoal
 * 
 * @param referencia, array - com as informações de uma referência
 * @param n, int -  conta a quantidade de referencia cadastrada ex.:(referencia 1(n), referencia 2(n), referencia ...(n))
 * @param totalReferencia, int - quantidade total de referencia a ser inseridas para esse cliente
 */
function cadastrarReferencias(referencia, n, totalReferencia) {

	// abrir modal nova referência
    casper.then(function () {
        casper.echo(".. Inserindo referência " + n + " de " + totalReferencia);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#btn-nova-referencia");
        mvLojas.click("#aba-referencias #btn-nova-referencia");
    });
    
    // aguarda modal abrir
    casper.then(function () {
        mvLojas.waitModalOpenEvt("#mdl-referencia-cliente");
    });

    casper.then(function () {
        utils.takeSS("aposAbrirModalReferencia");
    });
    
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Modal de cadastro de Referência aberta com sucesso");
    });

    casper.then(function () {

        var cpfCnpj = isNull(referencia.cpf_cnpj);

        // valida se a referência é um cliente, se sim busca as informações na base de dados 
        if (referencia.ind_cliente == 1) {

        	casper.then(function () {
                casper.echo(".. abrindo searchfield para buscar referencia na base de dados.");
                mvLojas.click("#btn-sf-cliente-ref-search");
                utils.takeSS("procurandoCliente");
            });

            casper.then(function () {
                mvLojas.waitModalOpenEvt("#mdl-sf-cliente-ref");
            });

            casper.then(function () {
                if (referencia.ind_pf == 0) {
                    mvLojas.click("#mdl-sf-cliente-ref input[data-sf-column='tipo_pessoa']");
                }
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                casper.echo(".. pesquisando cliente por " + cpfCnpj ? "Nome" : "CPF?CNPJ");
                casper.echo(cpfCnpj ? (".. Nome: " + referencia.nome) : (".. CPF: " + referencia.cpf_cnpj));
            });

            casper.then(function () {
                cpfCnpj ? mvLojas.$val("#mdl-sf-cliente-ref input[data-sf-column='nome_cliente']", referencia.nome) : mvLojas.$val("#mdl-sf-cliente-ref input[data-sf-column='cpf']", referencia.cpf_cnpj);
                mvLojas.$valCombo("#mdl-sf-cliente-ref select[data-sf-column='uf']", referencia.uf);
            });

            casper.then(function () {
                mvLojas.click("#mdl-sf-cliente-ref .mv-sf-filter-search");
            });

            casper.then(function () {
                mvLojas.waitForLoad();
            });

            casper.then(function () {
                utils.takeSS("consultaSFClienteReferencia_" + n);
            });

            casper.then(function () {

                var valor;

                casper.then(function () {
                    valor = cpfCnpj ? referencia.nome : referencia.cpf_cnpj;
                });

                casper.then(function () {
                    if (valor.length == 11) {
                        valor = valor.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "\$1.\$2.\$3\-\$4");
                    } else if (valor.lenght == 11) {
                        valor = valor.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/g, "\$1.\$2.\$3\/\$4\-\$5");
                    }
                });

                casper.then(function () {
                    if (mvLojas.gridLength('#grid-sf-cliente-ref') > 0) {
                        utils.takeSS('clienteEncontrado_' + n);
                        casper.test.pass('Cliente Encontrado');
                    } else {
                        takeSS("clienteNaoEncontrado_" + n);
                        casper.test.fail('Cliente não encontrado');
                    }
                    
                    utils.takeSS("buscandoCliente");
                });

                casper.then(function () {

                    if (cpfCnpj) {

                        casper.then(mvLojas.findRowTable('#grid-sf-cliente-ref', {
                            nome_cliente: valor
                        }, function (selector) {
                            this.mouse.click(selector);
                            utils.takeSS("referenciaSelecionada_" + n);
                            casper.test.pass("Cliente Selecionado.");
                        }));

                    } else {

                        casper.then(mvLojas.findRowTable('#grid-sf-cliente-ref', {
                            cpf_cnpj: valor
                        }, function (selector) {
                            this.mouse.click(selector);
                            utils.takeSS("referenciaSelecionada_" + n);
                            casper.test.pass("Cliente Selecionado.");
                        }));

                    }

                });

            });

        } else {

            casper.then(function () {
                mvLojas.$val("#txt-sf-cliente-ref-text", referencia.nome);
            });

        }

    });

    casper.then(function () {
        mvLojas.$val("input[data-column='fone_referencia_ddd']", referencia.ddd);
        mvLojas.$val("input[data-column='fone_referencia_nro']", referencia.fone);
        mvLojas.$val("input[data-column='fone_referencia_ramal']", referencia.ramal);
        mvLojas.$valCombo("#mdl-referencia-cliente select[data-column='cod_vinculo']", referencia.cod_vinculo);
        mvLojas.$valCombo("#mdl-referencia-cliente select[data-column='ind_ativo']", referencia.cod_ativo);
        
        utils.takeSS("preenchendoInformacoes");
    });

    casper.then(function () {
        mvLojas.click("#mdl-referencia-cliente #btn-salvar-referencia-cliente");
        
        utils.takeSS("salvandoReferencia");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("aposInserirReferencia_" + n);
    });
}

/**
 * Valida se uma variável ou posição de um array é vazia
 * @param elementm, string valor a ser comparado
 * @returns bool, retorna TRUE caso esteja vazio e FALSE caso contrário
 */
function isNull(element) {
    return element == "" || element == null || element == undefined ? true : false;
}

/**
 * Função responsável por salvar o cliente 
 */
function salvarCliente() {

    casper.then(function () {
        mvLojas.click("#btn-salvar-cliente");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("antesConfirmarClienteSalvoSucesso");
    });

    // valida se existe a mensagem informando que o cliente foi salvo.
    casper.then(function () {
        casper.waitForText("Cliente salvo com sucesso!", function success() {
            casper.test.pass("Cliente salvo com sucesso!.");
            casper.click('div.modal.in, btn');
        }, function fail() {
            utils.takeSS('erroAoSalvarCliente');
            casper.test.fail("Erro ao salvar cliente.");
        });
    });

    casper.then(function () {

        mvLojas.click('div.modal.in, btn');

    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("aposSalvarCliente");
    });
}

/**
 * função responsável por cadastrar os dados para solicitção do cartão MV Shop
 * @param data, array com os dados para criação do cartão
 */
function cadastraCartaoMVShop(data) {

    casper.then(function () {
        casper.echo(".. Abrindo aba para solicitar Cartão MV Shop");
    });

    casper.then(function () {
        mvLojas.click("#tab-cadastro-cliente #lnk-modal-cartao-mvshop");
    });

    casper.then(function () {
        mvLojas.waitModalOpenEvt("#mdl-cartao-mvshop");
    });

    casper.then(function () {
        utils.takeSS("modalMVShop");
    });

    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo informações do cartão");
    });

    casper.then(function () {
        mvLojas.$valCombo("#frm-cartao-mvshop select[data-column='dia_vencto']", data.dia_vencimento);
    });

    casper.then(function () {
        mvLojas.$valCombo("#frm-cartao-mvshop select[data-column='tipo_endereco_fatura']", data.cod_endereco_fatura);
    });

    casper.then(function () {
        mvLojas.$val("#frm-cartao-mvshop input[data-column='nome_impresso_cartao']", data.nome_impressao_cartao);
    });

    casper.then(function () {
        if (data.dependentes.lenght > 0) {
            casper.echo("dependentes");
        }
    });

    casper.then(function () {
        utils.takeSS("modalMVShopFormPreenchido");
    });

    casper.then(function () {
        mvLojas.click("#mdl-cartao-mvshop #btn-salvar-cartao");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

}