/*@include de funções utilitárias*/

var require             			= patchRequire(require);
var mvLojas            			 	= require('../utils/UtilsMVLojas.js');
var utils              			 	= require('../utils/Utils');


var dadosConexao					= require('../data/conexao.json');

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nTeste de estesse do sistema', 0, function suite(test) {
	
	//start na suite de teste
	casper.start();
	
	casper.eachThen(dadosConexao, function (response) {
	    (function (data) {
	    	
	    	if (data.executar_teste) {
		    	casper.echo("");
		    	''
		    	
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
	
	
	var dadosLojas = require('../data/lojas.json');
	
	casper.eachThen(dadosLojas[data.banco], function (response) {
	    (function (dataLoja) {
	    	
	    	casper.then(function () {
	    		this.echo(".. Logando na loja: " + dataLoja.cod_nro_loja + " - " + dataLoja.desc_loja, "INFO_BAR");
	    		mvLojas.autenticar(dataLoja.cod_nro_loja, data.usuario, data.senha, data.banco);
	    	});
	    	
	    	casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	this.echo('.. Inserindo CPF: 000.000.001-91');
	        	mvLojas.$val("#form-consulta-cliente #cbx-cpf", "00000000191");
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("cpfInformadoParaPesquisa");
	        	mvLojas.click("#btn-pesquisar");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        	utils.takeSS("aposPesquisar");
	        });
	        
	        casper.then(function () {
	        	casper.then(mvLojas.findRowTable('#gridCliente', {
	        		cpf_cnpj : "000.000.001-91"
	    		}, function (selector) {
	    			this.mouse.click(selector);
	    			utils.takeSS("cliente_encontrado");
	    			casper.test.pass("Cliente Selecionado.");
	    		}));
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	mvLojas.click("#btn-elaborar-pedido");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("modalPedido");
	        });
	        
	        //aba garantias
	        casper.then(function () {
	        	this.echo(".. Aba Garantias");
	        	mvLojas.click("#tab-itens-pedido #tab-garantia");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("abaGarantias");
	        });
	        
	        //aba Servicos
	        casper.then(function () {
	        	this.echo(".. Aba Servicos");
	        	mvLojas.click("#tab-itens-pedido #tab-servicos");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("abaAnaliseComercial");
	        });
	        
	        //aba Analise Comercial
	        casper.then(function () {
	        	this.echo(".. Aba Servicos");
	        	mvLojas.click("#tab-itens-pedido #tab-analise-comercial");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("abaAnaliseComercial");
	        });
	        
	        //aba Liberacoes
	        casper.then(function () {
	        	this.echo(".. Aba Liberacoes");
	        	mvLojas.click("#tab-itens-pedido #tab-liberacoes");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("abaLiberacoes");
	        });
	        
	        //aba Liberacoes
	        casper.then(function () {
	        	this.echo(".. Aba Planilhas");
	        	mvLojas.click("#tab-itens-pedido #tab-planilha");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("abaPlanilhas");
	        });
	        
	        casper.then(function () {
	        	mvLojas.click("#btnSair");
	        });
	        
	        casper.then(function () {
	        	mvLojas.waitForLoad();
	        });
	        
	        casper.then(function () {
	        	utils.takeSS("logout");
	        });
	    	
	    })(response.data);
	});
	
    
    
	
}