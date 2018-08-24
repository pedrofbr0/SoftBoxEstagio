/*@include de funções utilitárias*/

var require = patchRequire(require);
var x = require('casper').selectXPath;

var mvLojas = require('../../utils/UtilsMVLojas.js');
var pdvWeb = require('../../utils/UtilsPDVWeb.js');
var utils = require('../../utils/Utils');

var dadosPedido = require('../../data/039/pedido_venda_produto.json');
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
var cpf;

/*
 * CASOS DE TESTE - Documentação test 030
 */

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\n PEDIDO COM TIPO DE VENDA A VISTA EMPENHO LOJA E ENTREGA NORMAL - Valida Regra 4 Troca/Devolução', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.eachThen(dadosConexao, function (response) {
        (function (data) {

        	//casper.echo(data.executar_teste + " - " + data.loja);
        	
            if (data.loja == '4010') {
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
    	var nomArquivoPedido = casper.cli.get("nome_arquivo_pedido"); //032_regra_4
    	mvLojas.echoToFile(nomArquivoPedido, dadosPedido[data.banco].nro_pedido);
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