var require = patchRequire(require);

//var utils = require("../utils/Utils");
var URL = 'http://10.41.0.32:8080/';
var LOJA = '291';
var USUARIO = '6699';
var SENHA = '1';
var cpf = "377.786.705-50";
var useTakeSS = true;
var screenshot = 0;

var utils = require("../utils/Utils");
var pdvWeb = require("../utils/UtilsPDVWeb.js");
var mvLojas = require('../utils/UtilsMVLojas.js');
var errors = [];

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nTeste V58', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.then(function () {
    	casper.echo("Autenticar...");
    	pdvWeb.autenticarPDV("110", "291", "7380", "1", "RELOH");	
    })
    
    casper.then(function () {
    	pdvWeb.navegarPDV(["Pedido"]);
    });

    casper.then(function () {
    	casper.echo("Informando nro pedido");
    	casper.sendKeys("#filtro-flag-acesso", "4620033");
    	casper.click("#btn-pesquisar");
    	utils.takeSS("telaPesquisaPedido");
    });
    
    casper.then(function (){
    	casper.wait(5000, function() {
    		casper.click("#btn-receber");
        	utils.takeSS("telaRecebimentoPedido");
    	});
    });
    
    casper.then(function () {
    	casper.wait(5000, function() {
	    	var vlrTotalPedido = mvLojas.getElementValue('input[ng-model="pedido.valorTotalPedido"]');
	    	casper.echo("Valor total pedido: " + vlrTotalPedido);
	    	
	    	casper.sendKeys('input[ng-model="planilha.valorRecebidoPedido"]', vlrTotalPedido, {reset: true});
    	});
    	
    	casper.wait(5000, function() {
    		utils.takeSS("telaInformandoRecebimentoPedido");
    	});
    });
    
    casper.on("page.error", function(msg, trace) {
  	  this.echo("Error:    " + msg, "ERROR");
  	  this.echo("file:     " + trace[0].file, "WARNING");
  	  this.echo("line:     " + trace[0].line, "WARNING");
  	  this.echo("function: " + trace[0]["function"], "WARNING");
  	  errors.push(msg);
  	});

  	casper.run(function() {
  	  if (errors.length > 0) {
  	    this.echo(errors.length + ' Javascript errors found', "WARNING");
  	  } else {
  	    this.echo(errors.length + ' Javascript errors found', "INFO");
  	  }
  	  casper.exit();
  	});
   
    //execucao da suite de testes
    casper.run(function () {
        test.done();
    });
});
