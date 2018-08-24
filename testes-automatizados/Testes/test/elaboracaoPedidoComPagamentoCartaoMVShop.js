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
casper.test.begin('\n@TestCase:\nFluxo principal de elaboracao de pedido com pagamento no cartao MV Shop', 0, function suite(test) {

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
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        selClientePadrao.selecionarCliente(data.banco);
    });
    
    casper.then(function () {
        utils.takeSS("modalElaboraPedidoAberta");
    });

    casper.then(function () {
        casper.echo(".. Realizando pedido de compra com cartão MV Shop");
    });

    casper.then(function () {
        casper.echo(".. Preencher formulario principal e validando dados");
    });

    casper.then(function () {
        casper.echo(".. validando dados.");
        utils.waitForValue("#txt-status-pedido", dadosPedido[data.banco].status_pedido);
//		utils.waitForValue("#txt-sf-cliente-pedido-text", dadosCliente.cadastro_completo.nome);
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
        telaProdutos.produtos(dadosPedido[data.banco].produtos, true, null);
    });

    casper.then(function () {
        this.echo(".. Informando quantidade de parcelas: " + dadosPedido[data.banco].plano);
        mvLojas.$valCombo("#frm-pedido #cbx-plano", dadosPedido[data.banco].plano);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("planoAlterado");
        casper.echo(".. Ajustando serviços");
    });

    casper.then(function () {
        telaServicos.servico(dadosPedido[data.banco].servicos);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        casper.echo(".. Adicionando forma de pagamento na aba planilha");
        mvLojas.click("#tab-itens-pedido #tab-planilha");
    });

    casper.then(function () {
        telaPlanilhas.formasPagamento(dadosPedido[data.banco].planilhas);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });

}