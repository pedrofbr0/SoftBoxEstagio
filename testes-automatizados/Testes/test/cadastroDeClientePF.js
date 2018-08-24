/*@include de funções utilitárias*/

var require = patchRequire(require);
var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils');

var selClientePadrao = require('../telas/selecionarClientePadrao.js');

var dadosCliente = require('../data/cadastro_cliente_mvshop.json');
var dadosPedido = require('../data/pedido_cartao_mvshop.json');
var dadosConexao = require('../data/conexao.json');

// telas de cadastro de cliente
var cadastroCliente = require('../telas/cadastro_cliente.js');
var telaFormPrincipal = require('../telas/cadastro_cliente/form_principal.js');
var telaCadEndereco = require('../telas/cadastro_cliente/enderecos.js');
var telaIdentificacaoTrabalho = require('../telas/cadastro_cliente/identificacao_trabalho.js');
var telaReferencias = require('../telas/cadastro_cliente/referencias.js');
var telaCartaoMVShop = require('../telas/cadastro_cliente/cartao_mv_shop.js');
var telaSalvarCliente = require('../telas/cadastro_cliente/salvar_cliente.js');

// telas de elaboração de pedido
var telaProdutos = require('../telas/elaborar_pedido/produtos.js');
var telaPlanilhas = require('../telas/elaborar_pedido/planilha.js');
var telaServicos = require('../telas/elaborar_pedido/servicos.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');

var cpf;
var nivelCadastro;

var execTestEmpresas = {"MVSH": true, "RELODEV": true, "RELOWMS": true};

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\n CADASTRO DE CLIENTE PF', 0, function suite(test) {

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

    // execucao da suite de testes
    casper.run(function () {
        test.done();
    });

});


function execTest(data) {

    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });
    
    //chama função que gera um cpf
    casper.then(function () {
        cpf = mvLojas.gerarCPF();
        dadosCliente[data.banco].cpf = cpf;
    });

    // tentan cadastrar cliente sem informar CPF
    casper.then(function () {
        casper.echo(".. Clicando no botao de cadastro de cliente sem informar CPF.");
        mvLojas.click("#btn-novo-cliente");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("clicarEmCadastrarClienteSemInformarCPF");
    });

    casper.then(function () {
        casper.waitForText("Informe o CNPJ ou CPF!", function success() {
            casper.test.pass("cadastro sem informar CPF validado 'Informe o CNPJ ou CPF!'");
            casper.click('div.modal.alert.in, btn');
        }, function fail() {
            utils.takeSS('falhaValidarCPFAoAbrirCadastroDeCliente');
            casper.test.fail("Foi permitido abrir tela de cadastro de cliente sem informar CPF.");
        });
    });

    // informando cpf
    casper.wait(2000, function () {
        casper.echo(".. informando CPF: " + cpf);
        mvLojas.$val('#cbx-cpf', cpf);
    });

    casper.then(function () {
        mvLojas.click("#btn-novo-cliente");
    });


    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        cadastroCliente.cadatroCompletoCliente(dadosCliente[data.banco]);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

}