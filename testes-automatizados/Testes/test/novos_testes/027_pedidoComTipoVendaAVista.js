/*@include de funções utilitárias*/

var require = patchRequire(require);
var mvLojas = require('../../utils/UtilsMVLojas.js');
var utils = require('../../utils/Utils');

var dadosPedido = require('../../data/027/pedido_venda_produto.json');
var dadosConexao = require('../../data/conexao.json');

var selClientePadrao = require('../../telas/selecionarClientePadrao.js');
var cadCliente = require('../../telas/cadastro_cliente.js');

// PEDIDO
var telaServicos = require('../../telas/elaborar_pedido/servicos.js');
var telaLiberacoes = require('../../telas/elaborar_pedido/liberacoes.js');
var telaPlanilhas = require('../../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../../telas/elaborar_pedido/salvar_pedido.js');
var telaProduto = require('../../telas/elaborar_pedido/produtos.js');
var telaGarantias = require('../../telas/elaborar_pedido/garantias.js');
var telaConsultaPedido = require('../../telas/consulta_pedido/consulta_pedido.js');

var nivelCadastro;
var cpf;

/*
 * CASOS DE TESTE - Documentação test 027
 */

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\n PEDIDO COM TIPO DE VENDA A VISTA', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.eachThen(dadosConexao, function (response) {
        (function (data) {

            if (data.executar_teste) {
                casper.echo('');
                casper.echo('INICIANDO EXECUACAO TESTE DA EMPRESA: ' + data.desc_empresa, 'INFO');

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

    // ===================> ELABORAÇÃO DO PEDIDO

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    });
    
    casper.then(function () {
        this.echo(".. Informando tipo de venda: " + dadosPedido[data.banco].desc_tipo_venda);
        mvLojas.$valCombo("#cbx-nro-tipo-venda", dadosPedido[data.banco].tipo_venda);
    });

    casper.then(function () {
        utils.takeSS("tipoVenda");
    });

    casper.then(function () {
        telaProduto.produtos(dadosPedido[data.banco].produtos);
    });

    casper.then(function () {
        telaGarantias.garantias(dadosPedido[data.banco].garantias, data);
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido[data.banco].servicos);
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido[data.banco].planilhas);
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

    casper.then(function () {
        dadosPedido[data.banco].nro_pedido = mvLojas.getNumPedido();
    });

    casper.then(function () {
        this.echo(".. Fechando modal de Elaboracao de Pedido");
        mvLojas.click("#modal-elabora-pedido #btn-fechar");
    });

    // entrando na tela de consulta de pedido e reabrindo pedido
    casper.then(function () {
        mvLojas.abrirTelaConsultaPedido();
    });

    casper.then(function () {
        telaConsultaPedido.consultarPedido(dadosPedido[data.banco]);
    });

    // ======================> REABERTURA DO PEDIDO

    casper.then(function () {
        telaConsultaPedido.reabrirPedido(dadosPedido[data.banco]);
    });
    
    casper.then(function () {
        telaProduto.produtos(dadosPedido[data.banco].reabertura1.produtos);
    });

    casper.then(function () {
        telaProduto.cancelarEmpenho(dadosPedido[data.banco].reabertura1.cancelar_produto);
    });

    casper.then(function () {
        telaProduto.reempenharProduto(dadosPedido[data.banco].reabertura1.reempenho, true);
    });

    casper.then(function () {
        telaGarantias.garantias(dadosPedido[data.banco].reabertura1.garantias, data);
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido[data.banco].reabertura1.servicos);
    });

    casper.then(function () {
        telaPlanilhas.removerLinhas(dadosPedido[data.banco].reabertura1.planilhas_remove);
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido[data.banco].reabertura1.planilhas);
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco].reabertura1);
    });

    casper.then(function () {
        this.echo(".. Fechando modal de Elaboracao de Pedido");
        mvLojas.click("#modal-elabora-pedido #btn-fechar");
    });

    // entrando na tela de consulta de pedido e reabrindo pedido
    casper.then(function () {
        mvLojas.abrirTelaConsultaPedido();
    });

    casper.then(function () {
        telaConsultaPedido.consultarPedido(dadosPedido[data.banco]);
    });

    // ======================> 2ª REABERTURA DO PEDIDO

    casper.then(function () {
        telaConsultaPedido.reabrirPedido(dadosPedido[data.banco]);
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido[data.banco].reabertura2.servicos);
    });

    casper.then(function () {
        telaPlanilhas.removerLinhas(dadosPedido[data.banco].reabertura2.planilhas_remove);
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido[data.banco].reabertura2.planilhas);
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco].reabertura2);
    });

}