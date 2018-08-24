/*@include de funções utilitárias*/

debugger;
	

var require             = patchRequire(require);
var fs = require("fs");
var mvLojas             = require('../utils/UtilsMVLojas.js');
var utils               = require('../utils/Utils');

var dadosCliente		= require('../data/cadastro_cliente.json');
var dadosConexao		= require('../data/conexao.json');
var dadosPedido			= require('../data/pedido_carne_losango.json');

var cadastroCliente		= require('../telas/cadastro_cliente.js');
var telaProdutos		= require('../telas/elaborar_pedido/produtos.js');
var telaFormaPagto		= require('../telas/elaborar_pedido/planilha.js');
var telaLiberacoes		= require('../telas/elaborar_pedido/liberacoes.js');
var telaSalvarPedido	= require('../telas/elaborar_pedido/salvar_pedido.js');
var telaConsultaPedido	= require('../telas/consulta_pedido/consulta_pedido.js');
var telaValidaFormPrincipal	=require('../telas/elaborar_pedido/formPrincipal.js');
var telaConsultaSimplificada	= require('../telas/elaborar_pedido/consulta_simplificada.js');

var cpf;

var data = {
		  "cpf": "00000000192",
		  "data_nascimento": "23/01/1986",
		  "tipo_venda": "66",
		  "desc_tipo_venda": "66 - Carne Losango SCRED",
		  "ret_enviar_msg": '<b>CONSULTA SIMPLIFICADA EM ANDAMENTO, AGUARDE ALGUNS <br>INSTANTES E ACIONE CONSULTAR <br><b></b></b>',
		  "ret_concluido_avaliacao": '<span class="alert-success"><i class="icon-smile"></i> Elegível</span>',
		  "ind_elaborar_pedido": true
		};

(function() {
	
/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboração de pedido com pagamento no carne losango', 0, function suite(test) {

    //start na suite de teste
    casper.start();
    
//    casper.then(function () {
//    	mvLojas.autenticar("102", "7380", "1", "RELODEV");
//    });
//    
//    casper.then(function () {
//    	utils.takeSS("telaInicial");
//    });
//    
//    casper.then(function () {
//    	telaConsultaSimplificada.consultaSimplificada(data);
//    });
    
    casper.thenOpen("http://www.netshoes.com.br/", function () {
    	utils.takeSS("tela");
    });
    
    casper.then(function () {
    	casper.clickLabel('Login', 'a');
    });
    
    casper.then(function () {
    	casper.wait(3000);
    });
    
    casper.then(function () {
    	utils.takeSS("asfd");
    });
    
    casper.run(function () {
    	test.done();
    });


});

})();