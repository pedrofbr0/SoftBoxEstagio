/*@include de funções utilitárias*/

var require = patchRequire(require);
var mvLojas = require('../../utils/UtilsMVLojas.js');
var utils = require('../../utils/Utils');

var dadosPedido = require('../../data/005/pedido_venda_produto.json');
var dadosEdicaoCliente = require('../../data/005/edicao_cliente.json');
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

var telaEdicaoCliente = require('../../telas/editar_cliente.js');

var nivelCadastro;

// indica em quais empresas esse teste deve rodar
var execTestEmpresas = {"MVSH": false, "RELODEV": true, "INLOJAD": false, "RELOWMS": true};

/*
 * CASOS DE TESTE - Documentação test 005
 */

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\n EDICAO DE DADOS DO CLIENTE', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.eachThen(dadosConexao, function (response) {
        (function (data) {

            if (data.executar_teste) {
            	
                casper.echo('');
                casper.echo('INICIANDO EXECUACAO TESTE DA EMPRESA: ' + data.desc_empresa + " Banco: " + data.banco, 'INFO');

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

    casper.then(function() {
    	telaEdicaoCliente.editarCliente(dadosEdicaoCliente[data.banco]);
    });
}