/*@include de funções utilitárias*/

var require = patchRequire(require);
var x = require('casper').selectXPath;
var mvLojas = require('../utils/UtilsMVLojas.js');
var pdvWeb = require('../../utils/UtilsPDVWeb.js');
var utils = require('../utils/Utils');

var dadosPedido = require('../data/pedido_cliente_cosumidor_empenho_loja_normal.json');
var dadosConexao = require('../data/conexao.json');

var telaProduto = require('../telas/elaborar_pedido/produtos.js');
var telaPlanilha = require('../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');
var telaServicos = require('../telas/elaborar_pedido/servicos.js');
var telaRecebimentoPedido = require('../../telas/elaborar_pedido/receber_pedido.js');

var cpf;
var nivelCadastro;

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboracao de pedido com pagamento a vista.', 0, function suite(test) {

    casper.start();

    casper.eachThen(dadosConexao, function (response) {
        (function (data) {

            if (data.executar_teste) {
                casper.echo("");
                casper.echo('INICIANDO EXECUACAO TESTE DA EMPRESA: ' + data.desc_empresa, 'INFO');

                execTest(data);
            }

        })(response.data);
    });

    casper.run(function () {
        test.done();
    });

});


function execTest(data) {

    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });

    casper.then(function () {
        utils.takeSS("telaConsultaCliente");
        this.echo(".. Clicando no botao Consumidor");
        this.mouse.click("#toolbar-pedido #btn-consumidor");
    });

    casper.then(function () {

        mvLojas.waitForLoad();

        this.waitForSelector("#modal-elabora-pedido", function () {
            utils.takeSS("modaPedido");
        });
    });

    casper.then(function () {
        telaProduto.produtos(dadosPedido[data.banco].produtos);
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido[data.banco].servicos);
    });

    casper.then(function () {
        telaPlanilha.formasPagamento(dadosPedido[data.banco].planilhas);
    });

    casper.then(function () {
        dadosPedido[data.banco].nro_pedido = mvLojas.getNumPedido();
    });
    
    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

    casper.then(function () {
        casper.echo(".. Teste de compra com pagamento avista com empenho na loja e tipo de entrega 'nao entrega'.");
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