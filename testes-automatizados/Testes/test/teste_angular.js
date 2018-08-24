var require = patchRequire(require);

//var utils = require("../utils/Utils");
var URL = 'http://10.41.0.32:8080/';
var LOJA = '291';
var USUARIO = '6699';
var SENHA = '1';
var cpf = "377.786.705-50";
var useTakeSS = true;
var screenshot = 0;

var pdvWeb = require("../utils/UtilsPDVWeb.js");
//var mvLojas = require('../utils/UtilsMVLojas.js');
var errors = [];

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nTeste V58', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.then(function () {
    	casper.echo("Autenticar...");
    	
    	casper.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/38.0.5');
        casper.page.viewportSize = {width: 1920, height: 1080};
        
        casper.thenOpen("http://tableless.github.io/exemplos/angularjs/lista-compras/", function () {
        	casper.echo("Tela aberta...");
        	takeSS("telaPrincipal");
        });
    })
    
    casper.then(function () {
    	//mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.echo("Tela produto...");
    	
    	casper.sendKeys('input[ng-model="item.produto"]', "Testes");
        casper.sendKeys('input[ng-model="item.quantidade"]', "2");
        casper.click(".btn-primary");
        
    	//pdvWeb.navegarPDV(["Pedido"]);
        takeSS("telaProduto");
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
   
    
/*
    casper.then(function () {
    	casper.sendKeys("#filtro-flag-acesso", "4619824");
    	casper.click("#btn-pesquisar");
    	takeSS("telaPesquisa");
    });*/
    
    //execucao da suite de testes
    casper.run(function () {
        test.done();
    });
});

function takeSS(name) {
	
	if (useTakeSS) {
		var fs = require('fs');
		
		var pathWin = 'C://Screenshots//' + ((typeof casper.cli.get("folder") != 'undefined' ) ? casper.cli.get("folder") + '//' : '');
		var pathLinux = '/opt/Screenshots/' + ((typeof casper.cli.get("folder") != 'undefined' ) ? casper.cli.get("folder") + '/' : '');
		var path = '';
		
		if (fs.isDirectory("C:")) {
			path = pathWin;
		
			if (!fs.isDirectory(path)) {
				fs.makeDirectory(path);
			}
			
		} else if (fs.isDirectory("/opt")) {
			path = pathLinux;
			
			if (!fs.isDirectory(path)) {
				fs.makeDirectory(path);
			}
		}
		
		casper.capture(path + screenshot + name + '.png', undefined, {
	        format: 'png',
	        quality: 75
	    });
		
		//phantom.exit();
		
		screenshot++;
	}
};