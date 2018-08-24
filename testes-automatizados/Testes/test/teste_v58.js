//var require = patchRequire(require);
//var utils = require("../utils/Utils");
var URL = 'http://10.41.0.32:8080/';
var LOJA = '291';
var USUARIO = '6699';
var SENHA = '1';
var cpf = "377.786.705-50";
var useTakeSS = true;
var screenshot = 0;

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nTeste V58', 0, function suite(test) {

    //start na suite de teste
    casper.start();

    casper.echo(".. " + URL);

    casper.echo("");
    casper.echo('INICIANDO EXECUACAO TESTE DA EMPRESA:46 ', 'INFO');
    
    casper.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/46.0.0');
    casper.page.viewportSize = {width: 1920, height: 1080};
    
    casper.then(function(){
    	casper.echo("Escrevendo no arquivo.");
    	echoToFile();
    });
    
    /*casper.thenOpen(URL + 'lojas/seguranca?action=login', function () {
    	casper.then(function () {
            casper.waitForSelector('#formLogin', function success() {
                // se achou o login , realizar login
                casper.sendKeys('input#j_loja', LOJA, {reset: true});
                casper.sendKeys('input#j_username_fake', USUARIO);
                casper.sendKeys('input#j_password', SENHA);
                casper.click("input[type=button][value='Entrar']");

                //waitForLoad();

                takeSS("telaLogin");

            }, function fail() {
                takeSS("falhaAbrirTelaLogin");
                casper.test.fail('Falha ao abir tela de login');
            });
        });
    });
    
    casper.then(function () {
        this.echo('.. Pesquisando Cliente');
        //mvLojas.$valCheck("#ckb-tipo-pessoa", true);
    });

    casper.then(function () {
        //mvLojas.$val("#form-consulta-cliente #cbx-cpf", cpf);
    	casper.sendKeys('#form-consulta-cliente #cbx-cpf', cpf);
    	takeSS("telaPesquisa");
    });
    
    casper.then(function () {
        //mvLojas.$valCheck("#ckb-tipo-pessoa", true);
        casper.click("#btn-pesquisar");
        casper.wait(3000);
        takeSS("telaPesquisar");
    });
    
    casper.then(function () {
    	casper.click("#btn-elaborar-pedido");
    	casper.wait(5000);
    	takeSS("telaPedido");
    });
    
    casper.then(function () {
    	casper.wait(10000);
    	//casper.click("#btn-salvar-pedido");
    	takeSS("telaPedidoSalvar");
    });
    */
    
    //execucao da suite de testes
    casper.run(function () {
        test.done();
    });

});

function echoToFile() {
	
	"use strict";
	var fs = require('fs'),
	    system = require('system');

	var content = '123456',
        f = null,
        i;
	
    try {
        fs.write("C:\\Temp\\002", content, 'w');
        fs.write("C:\\Temp\\002", "asdfsa", 'w+');
    } catch(e) {
        console.log(e);
    }

    phantom.exit();
};