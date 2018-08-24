/*@include de funções utilitárias*/

var require = patchRequire(require);
var x = require('casper').selectXPath;
var mvLojas = require('../../utils/UtilsMVLojas.js');
var pdvWeb = require('../../utils/UtilsPDVWeb.js');
var utils = require('../../utils/Utils');

// cadastro de cliente
var telaCadClienteFormPrincipal = require('../../telas/cadastro_cliente/form_principal.js');
var telaCompradorRepresentante = require('../../telas/cadastro_cliente/comprador_representante.js');
var telaCadEndereco = require('../../telas/cadastro_cliente/enderecos.js');
var telaCadReferencias = require('../../telas/cadastro_cliente/referencias.js');
var telaSalvarCliente = require('../../telas/cadastro_cliente/salvar_cliente.js');

var dadosPedido = require('../../data/015/pedido_venda_produto.json');
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
var telaRecebimentoPedido = require('../../telas/elaborar_pedido/receber_pedido.js');

var nivelCadastro;

/*
 * CASOS DE TESTE - Documentação test 015
 */

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\n PEDIDO COM TIPO DE VENDA PESSOA JURIDICA, GARANTIA SOLUCAO CERTA INFORMATICA E SERVICOS DE MONTAGEM DE MOVEIS, SERVICOS FRETE E SOLUCAO CERTA INFORMATICA, EMPENHO DEPOSITO ENTREGA AGENDADA', 0, function suite(test) {

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
        selClientePadrao.selecionarClientePj(data.banco);
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
        dadosPedido[data.banco].nro_pedido = mvLojas.getNumPedido();
    });
    
    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });

    // entrando na tela de consulta de pedido e reabrindo pedido
    casper.then(function () {
        mvLojas.abrirTelaConsultaPedido();
    });

    casper.then(function () {
        telaConsultaPedido.consultarPedido(dadosPedido[data.banco]);
    });

    casper.then(function () {
        telaConsultaPedido.reabrirPedido(dadosPedido[data.banco]);
    });

    // ======================>

    casper.then(function () {
        telaProduto.produtos(dadosPedido[data.banco].reabertura1.produtos);
    });

    casper.then(function () {
        telaGarantias.garantias(dadosPedido[data.banco].reabertura1.garantias);
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
        dadosPedido[data.banco].statusAposFechamentoTotalPedido = 'Ag. Recebimento';
        dadosPedido[data.banco].msg_success_ao_finalizar = 'Status: 30 - Ag. Recebimento';
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

    if (data.receber_pedido) {
    	// --------------------------
        // Recebimento no PDV
        // --------------------------
        casper.then(function () {
        	pdvWeb.autenticarPDV(data.pdv, data.loja, data.usuario, data.senha, data.banco);
        });
        
        casper.then(function () {
        	telaRecebimentoPedido.receberPedido(dadosPedido[data.banco]);
        });
    }
}