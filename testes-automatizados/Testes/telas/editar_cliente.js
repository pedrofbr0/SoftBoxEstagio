var require = patchRequire(require);

/**
 * Edição de cliente completo
 * 
 * @param dadosCliente, array com os dados do cliente
 */
exports.editarCliente = function (dadosCliente) {
	casper.then(function () {
        casper.echo(".. Editando o cliente");
    });
	
	casper.then(function () {
        mvLojas.waitForLoad();
    });
	
    casper.then(function () {
        casper.click("#btn-sf-cliente-pedido-edit");
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        utils.takeSS("aoAbrirTelaCliente");
    });
    
    casper.then(function () {
        casper.waitForText("salvo com sucesso", function success() {
            casper.test.pass("Edicao de cliente aberta!");
            mvLojas.click(".modal.alert.in .btn.Ok");
        }, function fail() {
            utils.takeSS('modal_salvamento_nao_encontrada');
            casper.test.fail("Nao foi encontrado a modal de confirmacao de salvamento");
        });
    });
    
    // chama a função que preenche os pricipais dados do cadastro de cliente
    casper.then(function () {
        preencherCamposPrincipais(dadosCliente);
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	editarInformacoesDeRenda(dadosCliente.identificacao_trabalho);
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    //chama a função responsável por cadastrar o endereço do cliente
    casper.then(function () {
    	editarEndereco(dadosCliente.endereco);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("aposCadastrarEnderecos");
    });
    
    casper.then(function () {
        salvarCliente();
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
        mvLojas.$val('#txtNroTelefoneCelular', dadosCliente.celular);
 
        casper.echo(".. Nome cliente: " + dadosCliente.nome);
    });

    casper.then(function () {
        utils.takeSS("camposPrincipaisEdicaoClientePreenchidos");
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
function editarEndereco(endereco) {

    casper.then(function () {
    	casper.then(mvLojas.findRowTable('#grd-endereco', {
    		desc_tipo_endereco: endereco[0].desc_tipo_endereco,
        }, function (selector) {
        	casper.echo("... Selecionando o endereco do cliente.");
        	casper.echo("end: " + endereco[0].desc_tipo_endereco+ " selector: " + selector);
        	
        	mvLojas.click(selector + "[aria-describedby='grd-endereco_editar'] button");       	
        }));
    });
    
    casper.then(function () {
    	casper.waitForText("Não é permitido editar este endereço", function success() {
    		utils.takeSS('modal_regra_pedidos_ag_faturamento');
    		casper.test.pass("Modal de regra de pedidos em Ag. Faturamento fechado com sucesso!");
            //mvLojas.click(".modal.alert.in .btn.Ok");
    		casper.click('div.modal.in button.Ok');
        }, function fail() {
            utils.takeSS('modal_regra_pedidos_ag_faturamento_nao_encontrada');
            casper.test.fail("Nao foi encontrado a modal de regra de pedidos em Ag. Faturamento");
        });
    });
}

function editarInformacoesDeRenda(dados) {
	// abrir modal nova referência
    casper.then(function () {
        casper.echo(".. Editando Informacoes da Renda");
        mvLojas.click("#lnk-modal-trabalho");
    });

    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#mdl-trabalho");
    });
    
	// Calcula o mês ano da renda principal, pegando 40 dias anteriores a partir da data atual
    dados.renda_principal.mes_ano = mvLojas.setDataRendaPrincipal(-40);
    
    // printando renda principal
    casper.then(function () {
    	casper.echo("");
        casper.echo(".. Renda Principal.");
        casper.echo(".. Mês/Ano: " + dados.renda_principal.mes_ano);
        casper.echo(".. Profissao: " + dados.trabalho.cod_profissao);
    });

    // inserindo informações Ocupação
    casper.then(function () {
         mvLojas.$val("#frm-trabalho-cliente #dp-comprovante-renda", dados.renda_principal.mes_ano);
         //mvLojas.$valCombo("#frm-trabalho-cliente #cbx-profissao", dados.trabalho.cod_profissao);
         mvLojas.$valCombo("#frm-trabalho-cliente #cbx-profissao", dados.trabalho.cod_profissao);
         utils.takeSS("camposPreenchidosAntesSalvar_");
    });
    
    casper.then(function () {
        mvLojas.click("#btn-salvar-trabalho");
    });

    casper.then(function () {
        casper.echo(".. salvando informações Identificação/Trabalho.");
    });

    casper.then(function () {
        mvLojas.waitModalCloseEvt("#mdl-trabalho");
        utils.takeSS("aposSalvarInfoTrabalho_");
    });
}

/**
 * Função responsável por cadastrar os dados (detalhes pessoais) e dados profissionais
 * 
 * @param dados, array com informações pessoais e profissionais
 */
function editarIdentificacaoTrabalho(dados) {

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // printando dados pessoais
    casper.then(function () {
        casper.echo("");
        casper.echo(".. Preenchendo dados pessoais.");
    });

    // inserindo informações de RG na tela
    casper.then(function () {
        mvLojas.$val("#frm-identificacao-cliente input[data-column='nome_pai']", dados.dados_pessoais.nome_pai);
        
        // RETIRAR
        mvLojas.$valCombo("#frm-identificacao-cliente #cbx-estado-civil", "SOLTEIRO(A)");
        
        mvLojas.$valCombo("#frm-identificacao-cliente #cbx-estado-civil", dados.dados_pessoais.cod_estado_civil);
        mvLojas.$valCombo("#frm-identificacao-cliente #cbx-sexo-conjuge", dados.dados_pessoais.sexo_conjuge);
        mvLojas.$val("#frm-identificacao-cliente #nome_conjuge", dados.dados_pessoais.nome_conjuge);
    });
        
    casper.then(function () {
        casper.echo(".. clicando no botão OK para confirmar informações inseridas.");
        utils.takeSS("informacoesPreenchidas");
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
function editarReferencias(referencia) {

    // abrir modal nova referência
    casper.then(function () {
        casper.echo(".. Editando referência");
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

    casper.then(mvLojas.findRowTable('#grd-referencias', {
    	desc_vinculo: referencia.vinculo, // Alterar no json de AMIGO P/ VIZINHO
    }, function (selector) {
    	mvLojas.click(selector + ".editButtonReferencia div");       	
    }));
    
    casper.then(function () {
    	mvLojas.$val("#txt-sf-cliente-ref-text", referencia.nome);
    	
    	// Retirar
    	mvLojas.$val("input[data-column='fone_referencia_ddd']", referencia.ddd);
        mvLojas.$val("input[data-column='fone_referencia_nro']", referencia.fone);
        mvLojas.$val("input[data-column='fone_referencia_ramal']", referencia.ramal);
        mvLojas.$valCombo("#mdl-referencia-cliente select[data-column='cod_vinculo']", referencia.cod_vinculo);
    });
    
    casper.then(function () {
        utils.takeSS("printAposFecharDetalhesCadastro");
    });

    casper.then(function () {
        mvLojas.click("#mdl-referencia-cliente #btn-salvar-referencia-cliente");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("aposInserirReferencia_");
    });
}

/**
 * Função responsável por salvar o cliente 
 */
function salvarCliente() {

    casper.then(function () {
    	casper.echo("... Salvando o cliente.");
        //mvLojas.click("#btn-salvar-cliente");
    	casper.click("#btn-salvar-cliente");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    /*casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });*/

    casper.then(function () {
        utils.takeSS("antesConfirmarClienteSalvoSucesso");
    });

    // valida se existe a mensagem informando que o cliente foi salvo.
    casper.then(function () {
        casper.waitForText("Cliente salvo com sucesso!", function success() {
            casper.test.pass("Cliente salvo com sucesso!.");
            casper.click('div.modal.in button.Ok');
        }, function fail() {
            utils.takeSS('erroAoSalvarCliente');
            casper.test.fail("Erro ao salvar cliente.");
        });
    });

    /*casper.then(function () {
        mvLojas.click('div.modal.in, btn');
    });*/

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
function editarCartaoMVShop(data) {

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