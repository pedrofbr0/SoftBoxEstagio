/*@include de funções utilitárias*/

var require = patchRequire(require);
var x = require('casper').selectXPath;
var mvLojas = require('../utils/UtilsMVLojas.js');
var pdvWeb = require('../../utils/UtilsPDVWeb.js');
var utils = require('../utils/Utils');

var dadosPedido = require('../data/pedido_avista_empenho_outra_loja_normal.json');
var dadosConexao = require('../data/conexao.json');

var selClientePadrao = require('../telas/selecionarClientePadrao.js');

// pedido
var telaProduto = require('../telas/elaborar_pedido/produtos.js');
var telaServicos = require('../telas/elaborar_pedido/servicos.js');
var telaLiberacoes = require('../telas/elaborar_pedido/liberacoes.js');
var telaPlanilhas = require('../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');
var telaRecebimentoPedido = require('../../telas/elaborar_pedido/receber_pedido.js');

var nivelCadastro;

//teste deve rodar apenas em INLOJAD


/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboracao de pedido com pagamento a vista, empenho em outra loja e entrega normal.', 0, function suite(test) {

    //start na suite de teste
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
        telaServicos.servico(dadosPedido[data.banco].servicos);
    });

    casper.then(function () {
        telaLiberacoes.liberacoes(dadosPedido[data.banco].liberacoes);
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