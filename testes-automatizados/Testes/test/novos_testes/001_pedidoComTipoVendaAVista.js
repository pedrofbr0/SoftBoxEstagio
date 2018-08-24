/*@include de funções utilitárias*/

var require = patchRequire(require);
var mvLojas = require('../../utils/UtilsMVLojas.js');
var pdvWeb = require('../../utils/UtilsPDVWeb.js');
var utils = require('../../utils/Utils');

var dadosPedido1 = require('../../data/001/pedido_venda_produto_A.json');
var dadosPedido2 = require('../../data/001/pedido_venda_produto_B.json');
var dadosConexao = require('../../data/conexao.json');

var selClientePadrao = require('../../telas/selecionarClientePadrao.js');

// PEDIDO
var telaServicos = require('../../telas/elaborar_pedido/servicos.js');
var telaLiberacoes = require('../../telas/elaborar_pedido/liberacoes.js');
var telaPlanilhas = require('../../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../../telas/elaborar_pedido/salvar_pedido.js');
var telaProduto = require('../../telas/elaborar_pedido/produtos.js');
var telaGarantias = require('../../telas/elaborar_pedido/garantias.js');
var telaConsultaPedido = require('../../telas/consulta_pedido/consulta_pedido.js');
var telaRecebimentoPedido = require('../../telas/elaborar_pedido/receber_pedido.js');

var nivelCadastro;

// indica em quais empresas esse teste deve rodar
var execTestEmpresas = {"MVSH": false, "RELODEV": true, "INLOJAD": false, "RELOWMS": true};

/*
 * CASOS DE TESTE - Documentação test 001
 */

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nPEDIDO COM TIPO DE VENDA A VISTA, GARANTIA MAIOR E TROCA GARANTIDA, SERVICOS GARANTIA MAIOR E SEGURO LOSANGO, EMPENHO DEPOSITO ENTREGA AGENDADA', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.eachThen(dadosConexao, function (response) {
        (function (data) {

            if (data.executar_teste) {
                casper.echo('');
                casper.echo('INICIANDO EXECUCAO DE TESTE DA EMPRESA: ' + data.desc_empresa, 'INFO');

                execTest(data);
            }

        })(response.data);
    });

    //execucao da suite de testes
    casper.run(function () {
        test.done();
    });

});


/**
 * função responsável por rodar os testes sequencialmente
 * @param data, array com informações de onde o teste será executado
 */
function execTest(data) {

    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });

    casper.then(function () {
        selClientePadrao.selecionarCliente(data.banco);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    });
    
    casper.then(function () {
        this.echo(".. Informando tipo de venda: " + dadosPedido1[data.banco].desc_tipo_venda);
        mvLojas.$valCombo("#cbx-nro-tipo-venda", dadosPedido1[data.banco].tipo_venda);
    });

    casper.then(function () {
        utils.takeSS("tipoVenda");
    });

    casper.then(function () {
        telaProduto.produtos(dadosPedido1[data.banco].produtos);
    });

    casper.then(function () {
        telaGarantias.garantias(dadosPedido1[data.banco].garantias);
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido1[data.banco].servicos);
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido1[data.banco].planilhas);
    });

    casper.then(function () {
        dadosPedido2[data.banco].garantia_avulsa_venda_interna[0].consulta_nota.pedido = mvLojas.getNumPedido();
    });
    
    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido1[data.banco]);
    });

    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });
    
    /*casper.then(function () {
        this.echo(".. Fechando modal de Elaboracao de Pedido");
        mvLojas.click("#modal-elabora-pedido #btn-fechar");
    });*/

    //AQUI COMEÇA O 2º PEDIDO
    casper.then(function () {
        selClientePadrao.selecionarCliente(data.banco);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    });
    
    casper.then(function () {
        this.echo(".. Informando tipo de venda: " + dadosPedido1[data.banco].desc_tipo_venda);
        mvLojas.$valCombo("#cbx-nro-tipo-venda", dadosPedido1[data.banco].tipo_venda);
    });

    casper.then(function () {
        utils.takeSS("tipoVenda");
    });

    casper.then(function () {
        telaProduto.produtos(dadosPedido2[data.banco].produtos);
    });

    casper.then(function () {
        telaGarantias.garantias(dadosPedido2[data.banco].garantias);
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido2[data.banco].servicos);
    });

    casper.then(function () {
        telaServicos.garantiaAvulsaVendaInterna(dadosPedido2[data.banco].garantia_avulsa_venda_interna);
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido2[data.banco].planilhas);
    });

    casper.then(function () {
        dadosPedido2[data.banco].nro_pedido = mvLojas.getNumPedido();
    });
    
    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido2[data.banco]);
    });
    
    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });

    /*casper.then(function () {
        this.echo(".. Fechando modal de Elaboracao de Pedido");
        mvLojas.click("#modal-elabora-pedido #btn-fechar");
    });*/

    // entrando na tela de consulta de pedido e reabrindo pedido
    casper.then(function () {
        mvLojas.abrirTelaConsultaPedido();
    });

    casper.then(function () {
        telaConsultaPedido.consultarPedido(dadosPedido2[data.banco]);
    });

    casper.then(function () {
        telaConsultaPedido.reabrirPedido(dadosPedido2[data.banco]);
    });

    casper.then(function () {
        telaProduto.cancelarEmpenho(dadosPedido2[data.banco].reabertura1.cancelar_produto);
    });
    
    casper.then(function () {
        telaProduto.reempenharProduto(dadosPedido2[data.banco].reabertura1.reempenho, true);
    });

    casper.then(function () {
        telaGarantias.garantias(dadosPedido2[data.banco].reabertura1.garantias);
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido2[data.banco].reabertura1.servicos);
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido2[data.banco]);
    });
    
}