/*@include de funções utilitárias*/



var require             = patchRequire(require);
 
casper.options.logLevel = 'debug';
casper.options.verbose 	= true;

var mvLojas             = require('../utils/UtilsMVLojas.js');
var utils               = require('../utils/Utils');

var dadosCliente		= require('../data/cadastro_cliente.json');
var dadosPedido			= require('../data/pedido_a_vista.json');
var dadosConexao		= require('../data/conexao.json');

var cadastroCliente		= require('../telas/cadastro_cliente.js');
var elaborarPedido		= require('../telas/elaborar_pedido/elaboracao_pedido.js');

var cpf;
var nivelCadastro;

var x;	
var y;

var bd = "RELODEV"; //"MVSH", "INLOJAD", "RELODEV"


/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nFluxo principal de elaboração de pedido com pagamento a vista', 0, function suite(test) {
	
	
	
	casper.on('remote.message', function(msg) {
		this.echo('------------------------> console.log(.js): ' + msg);
	});
	

//	casper.on('remote.message', function(message) {
//	    this.echo(message);
//	});
	//start na suite de teste
    casper.start();
    
//    casper.evaluate(function sendLog(log) {
//        // you can access the log from page DOM
//        console.log('from the browser, I can tell you there are ' + log.length + ' entries in the log');
//        require('utils').dump(log);
//    }, casper.result.log);
    
    casper.then(function () {
    	mvLojas.autenticar(dadosConexao[2].loja, dadosConexao[0].usuario, dadosConexao[0].senha, "LOCALHOST"); //OPÇÕES DE BANCO 'MVSH | INLOJAD | RELODEV'
    });
   
    casper.then(function () {
    	this.clickLabel('Pré-Recibos', 'a');
    });
    
    casper.then(function () {
    	casper.wait(3000);
    });
    
    
    
    casper.then(function () {
    	utils.takeSS("_");
    });
    
    
    
    casper.then(function () {
    	this.echo("... VOU CLICAR NO BOTÃO");
    });
    
    
    
    
    
    
    
    
    
    
    
    casper.then(function () {
    	this.mouse.click("#toolbar input[id='toolbar:btnNew']");
    });
    
    casper.then(function () {
    	utils.takeSS("alksdfjalskjdfalkdjs");
    	this.echo("... PASSEI...");
    });
        
    //execucao da suite de testes
    casper.run(function () {
        test.done();
    });

});