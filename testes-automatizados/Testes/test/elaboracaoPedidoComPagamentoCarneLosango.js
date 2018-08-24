/*@include de funções utilitárias*/

debugger;

var require = patchRequire(require);

var mvLojas = require('../utils/UtilsMVLojas.js');
var utils = require('../utils/Utils');

var selClientePadrao = require('../telas/selecionarClientePadrao.js');

var dadosCliente = require('../data/cadastro_cliente.json');
var dadosConexao = require('../data/conexao.json');
var dadosPedido = require('../data/pedido_carne_losango.json');

// telas de cadastro de cliente
var cadastroCliente = require('../telas/cadastro_cliente.js');
var telaFormPrincipal = require('../telas/cadastro_cliente/form_principal.js');
var telaCadEndereco = require('../telas/cadastro_cliente/enderecos.js');
var telaIdentificacaoTrabalho = require('../telas/cadastro_cliente/identificacao_trabalho.js');
var telaReferencias = require('../telas/cadastro_cliente/referencias.js');
var telaCartaoMVShop = require('../telas/cadastro_cliente/cartao_mv_shop.js');
var telaSalvarCliente = require('../telas/cadastro_cliente/salvar_cliente.js');

var telaProdutos = require('../telas/elaborar_pedido/produtos.js');
var telaFormaPagto = require('../telas/elaborar_pedido/planilha.js');
var telaLiberacoes = require('../telas/elaborar_pedido/liberacoes.js');
var telaServicos = require('../telas/elaborar_pedido/servicos.js');
var telaSalvarPedido = require('../telas/elaborar_pedido/salvar_pedido.js');

var telaConsultaSimplificada	= require('../telas/elaborar_pedido/consulta_simplificada.js');
var telaFinanceira = require('../telas/analise_credito/financeira.js');

var cpf;

var dataConsultaSimplificada = {
		  "cpf": "37778670550",
		  "data_nascimento": "01/01/1990",
		  "tipo_venda": "66",
		  "desc_tipo_venda": "66 - Carne Losango SCRED",
		  "ret_enviar_msg": '<b>CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS <br>INSTANTES E ACIONE CONSULTAR <br><b></b></b>',
		  "ret_concluido_avaliacao": '<span class="alert-success"><i class="icon-smile"></i> Elegível</span>',
		  "ind_elaborar_pedido": true
		};

var dataFinanceira = {"comunicacao": {
		  "ind_rg": true,
		  "ind_cpf": true,
		  "ind_comprovate_renda": true,
		  "observacao": "Teste"
	}};

var bd = "RELODEV"; //"MVSH", "INLOJAD", "RELODEV" // Integração de aprovação de compra no carne acontece apenas na empresa RELODEV

(function() {

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboracao de pedido com pagamento no carne losango', 0, function suite(test) {

	/**
     *  1) - Cadastro de Cliente
     *     - tenta abrir tela de cadastro de cliente sem informar CPF (valida msg)
     *     - informa cadastro CPF
     */

    //start na suite de teste
    casper.start();

    //utils.videoRecord();
    
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

})();

function execTest(data) {
    
    casper.then(function () {
        mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco);
    });
/*
    //chama função que gera um cpf
    casper.then(function () {
        cpf = mvLojas.gerarCPF();
    });

    // tentan cadastrar cliente sem informar CPF
    casper.then(function () {
        casper.echo(".. Clicando no botão de cadastro de cliente sem informar CPF.");
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
    casper.then(function () {
        casper.echo(".. informando CPF: " + cpf);
        mvLojas.$val('#cbx-cpf', cpf);
       dadosCliente[data.banco].cpf = cpf;
    });

    casper.then(function () {
        mvLojas.click("#btn-novo-cliente");
    });


    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("cadastroClienteCompleto");
    });

    // chama a função responsável por cadastrar o cliente
    casper.then(function () {
        cadastroCliente.cadatroCompletoCliente(dadosCliente[data.banco]);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    // TODO aqui começa o código temporário, para não ser necessário cadastrar um cliente novamente

    casper.then(function () {
        casper.echo("");
        casper.echo(".. Iniciando o pedido de compra");
    });

    casper.then(function () {
        casper.echo(".. Preencher formulario principal e validando dados");
    });

    casper.then(function () {
        casper.echo(".. validando dados.");
        utils.waitForValue("#txt-status-pedido", dadosPedido[data.banco].status_pedido);
        utils.waitForValue("#txt-sf-cliente-pedido-text", dadosCliente[data.banco].nome);
    });
*/

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
    	telaConsultaSimplificada.consultaSimplificada(dataConsultaSimplificada);
    });
	
    // setando tipo de venda Cartão de Crédito
    casper.then(function () {
        casper.echo(".. Selecionando o tipo de venda: " + dadosPedido[data.banco].desc_tipo_venda);
        mvLojas.$valCombo("#cbx-nro-tipo-venda", dadosPedido[data.banco].tipo_venda);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        telaProdutos.produtos(dadosPedido[data.banco].produtos);
    });

    casper.then(function () {
        casper.echo(".. Definindo quantidade de parcela em 10 X");
    });

    casper.then(function () {
        mvLojas.$valCombo("#frm-pedido #cbx-plano", dadosPedido[data.banco].plano);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("aposDefinirQuantidadeParcelas");
    });

    casper.then(function () {
        casper.echo("");
        mvLojas.click("#tab-itens-pedido #tab-planilha");
        casper.echo(".. Definir forma de pagamento, na aba planilha");
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        telaServicos.servico(dadosPedido[data.banco].servicos);
    });

    casper.then(function () {
        utils.takeSS("abaPlanilha");
    });

    casper.then(function () {
        telaFormaPagto.formasPagamento(dadosPedido[data.banco].planilha);
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        telaSalvarPedido.salvar(dadosPedido[data.banco]);
    });
    
    casper.then(function () {
        mvLojas.waitForLoad();
    });
    
    casper.then(function () {
        mvLojas.click("#btn-analise-credito");
        casper.echo(".. Clicando botão análise de crédito");
    });
    
    casper.then(function () {
	    this.wait(30000, function () {
	        casper.echo(".. Tempo de espera de abertura tela financeira.");
	        utils.takeSS("esperaAbrirTelaFinanceira");
	    });
	    
	    mvLojas.waitModalOpenEvt("#aba-financeira");
    });
    
    casper.then(function () {
    	mvLojas.click(".modal.alert.in .btn.Ok");
    	
    	this.wait(30000, function () {
	        casper.echo(".. Tempo de espera de abertura tela financeira.");
	        utils.takeSS("asdinanceira");
	    });
    	
    	telaFinanceira.financeira(dataFinanceira);
    });

}