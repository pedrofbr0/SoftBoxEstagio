var require = patchRequire(require);
var utils = require("../utils/Utils");
var x = require('casper').selectXPath;

var url = {
		"MVSH": "http://10.30.232.19:8080/mv-admin/",
		"RELOH": "http://10.30.232.15:8080/mv-admin/",
		"RELOH_BA": "http://10.30.232.15:8080/mv-admin/"
};

var mvLojas = require('../utils/UtilsMVLojas.js');

exports.autenticarPDV = function autenticarPDV(PDV, LOJA, USUARIO, SENHA, URL) {
	URL = url[URL];

	casper.echo("");
	casper.echo("... Autenticando no PDV Web...");
	casper.echo(".. " + URL);
	
    casper.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/38.0.5');
    casper.page.viewportSize = {width: 1920, height: 1080};
    
    casper.thenOpen(URL, function () {

    	casper.then(function () {
            casper.waitForSelector("form[method='post']", function success() {
                // se achou o login , realizar login

                casper.sendKeys('input#j_pdv', PDV, {reset: true});
                casper.sendKeys('input#j_loja', LOJA, {reset: true});
                casper.sendKeys('input#j_username_fake', USUARIO);
                casper.sendKeys('input#j_password', SENHA);
                casper.click("#btnEntrar");

                //mvLojas.waitForLoad();
                utils.takeSS("telaLogin");
                
                casper.test.pass('Login realizado com sucesso no PDV! Usuario: ' + USUARIO + " - Loja: " + LOJA + " PDV: " + PDV);
                
            }, function fail() {
            	utils.takeSS("falhaAbrirTelaLogin");
                casper.test.fail('Falha ao abir tela de login do PDV Web');
            });
    	});
    });
};

// Argumento link, passar como array. Ex.: ["Pedido"]
exports.navegarPDV = function navegarPDV(link) {
	casper.then(function () {
        casper.waitForSelector('.navbar', function success() {
            // se achou o login , realizar login
            for (var i = 0; i < link.length; i++) {
            	casper.echo(".. Clicando no menu: " + link[i]);
        		casper.click(x('//nav/ul[2]/li/a[contains(text(), "' + link[i] + '")]'));
            	//casper.clickLabel(link[i], 'a');
        	}
            
        }, function fail() {
            utils.takeSS('Falha ao carregar tela!');
            casper.test.fail('Falha ao carregar tela!!');
        });
    });
}