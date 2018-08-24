/**
 * Funções utilitárias do sistema ARC
 */
//desenvolvimento
var require = patchRequire(require);
var x = require('casper').selectXPath;
var n;

var loja = 1;
var usuarioPadrao = '7380';
var senhaPadrao = '1';
var utils = require("../utils/Utils");
var url = 'https://hlgefetividade.softbox.com.br/';

// Homologacao
var urlbase = {
    "hlgefetividade": 'https://hlgefetividade.softbox.com.br/',
    "INLOJAD": 'http://10.30.237.94:8080/',
    "RELODEV": 'http://10.30.237.96:8080/',
    "RELOWMS": 'http://10.30.237.238:8080/',
    "LOCALHOST": 'http://localhost:8080/',
    "RELOH": 'http://10.41.0.31:8080/',
    "RELOD": 'http://10.41.0.71:8080/',
    "RELOH_EXA": 'http://10.41.0.82:8080/',
    "RELOH_BA": 'http://10.41.0.31:8080/'
};

// Para rodar o casper no jenkins
// http://localhost:8080/

/**
 * Realiza Login no sistema 
 * 
 * @params USUARIO, int - com o número do usuário a logar
 * @params SENHA, string - senha do usuário
 * @params URL, string - com informação de qual banco o sistema usará (MVSH/INJOJAD/RELODEV)
 */
exports.autenticar = function autenticar(USUARIO, SENHA, URL) {

    USUARIO = USUARIO || usuarioPadrao;
    SENHA = SENHA || senhaPadrao;

    URL = urlbase[URL] || url;

    casper.echo(".. " + URL);

    casper.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/38.0.5');
    casper.page.viewportSize = {width: 1920, height: 1080};
    
    casper.thenOpen(URL + 'site/login', function () {

    	casper.then(function () {
            casper.waitForSelector('#login-form', function success() {
                // se achou o login , realizar login

                //casper.sendKeys('input#j_loja', LOJA, {reset: true});
                casper.sendKeys('input#LoginForm_username', USUARIO);
                casper.sendKeys('input#LoginForm_password', SENHA);
                casper.click("input[type=submit][value='Login']");
				
				utils.takeSS("telaLogin");
				
                waitForLoad();

            }, function fail() {
                utils.takeSS("falhaAbrirTelaLogin");
                casper.test.fail('Falha ao abir tela de login');
            });
        });

        casper.then(function () {
            utils.takeSS('telaInicial');
            casper.waitForSelector('#page', function success() {
                // se achou o login , realizar login
                casper.test.pass('Login realizado com sucesso! Usuario: ' + USUARIO);
            }, function fail() {
                utils.takeSS('Falha ao autenticar');
                casper.test.fail('Falha ao autenticar no Sistema');
            });
        });
		
		casper.then(function () {
			var js = this.evaluate(function() {
				return document; 
			});
			this.echo(js.all[0].outerHTML);
		});
};

//aguarda ate que o ajax seja retornado
function waitForLoad() {

    var empty = function () {};

    casper.wait(500);

    casper.waitFor(function () {
        return casper.evaluate(function () {
            return $.blockUI.defaults.ajaxCount <= 0 && $(".blockUI").size() == 0;
        });
    }, empty, empty, 1000);
};
exports.waitForLoad = waitForLoad;