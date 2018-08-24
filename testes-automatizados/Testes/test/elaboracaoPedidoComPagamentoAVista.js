/*@include de funções utilitárias*/

var require = patchRequire(require);
var x = require('casper').selectXPath;
var mvLojas = require('../utils/UtilsMVLojas.js');
var pdvWeb = require('../../utils/UtilsPDVWeb.js');
var utils = require('../utils/Utils');

var selClientePadrao = require('../telas/selecionarClientePadrao.js');
var dadosCliente = require('../data/cadastro_cliente.json');
var dadosPedido = require('../data/pedido_a_vista.json');
var dadosConexao = require('../data/conexao.json');

var cadastroCliente = require('../telas/cadastro_cliente.js');
var elaborarPedido = require('../telas/elaborar_pedido/elaboracao_pedido.js');

//var selClientePadrao = require('../telas/selecionarClientePadrao.js');
var telaProduto = require('../telas/elaborar_pedido/produtos.js');
var telaServicos = require('../telas/elaborar_pedido/servicos.js');
var telaLiberacoes = require('../telas/elaborar_pedido/liberacoes.js');
var telaPlanilhas = require('../telas/elaborar_pedido/planilha.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');
var telaRecebimentoPedido = require('../../telas/elaborar_pedido/receber_pedido.js');

var cpf;
var nivelCadastro;


var bd = "RELODEV"; //"MVSH", "INLOJAD", "RELODEV"
/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboracao de pedido com pagamento a vista', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    var numTimes = 1, count = 1;

    casper.repeat(numTimes, function() {
       
        casper.echo(count);
       
       casper.eachThen(dadosConexao, function (response) {
            (function (data) {

                if (data.executar_teste) {
                    casper.echo("");
                    casper.echo('INICIANDO EXECUACAO TESTE DA EMPRESA: ' + data.desc_empresa, 'INFO');

                    execTest(data);
                }

            })(response.data);
        });
        
        casper.then(function () {
           count++; 
        });
    
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
        mvLojas.waitForLoad();
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
        this.echo(".. Adicionando Produto(s)");
        telaProduto.produtos(dadosPedido[data.banco].produtos, true);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        this.echo(".. Ajustando Servicos");
        telaServicos.servico(dadosPedido[data.banco].servicos);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.echo("");
        mvLojas.click("#tab-itens-pedido #tab-liberacoes");
        casper.echo(".. Validando se existem liberações pendentes.");
    });

    casper.then(function () {
        this.echo('.. Abrindo aba Planilha');
        mvLojas.click("#tab-itens-pedido #tab-planilha");
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido[data.banco].planilhas);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        dadosPedido[data.banco].nro_pedido = mvLojas.getNumPedido();
    });
    
    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

    casper.then(function () {
        casper.echo(".. Teste de compra com pagamento a vista realizado com sucesso.");
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
