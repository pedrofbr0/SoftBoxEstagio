/*@include de funções utilitárias*/

var require = patchRequire(require);
var infra = require('../utils/UtilsInfra.js');
var utils = require('../utils/Utils');
var dadosConexao = require('../data/conexaoInfra.json');

/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\n LOGIN NO SISTEMA', 0, function suite(test) {

    casper.start();
    
    casper.eachThen(dadosConexao, function (response) {
        (function (data) {

            if (data.executar_teste) {
                casper.echo("");
                casper.echo('INICIANDO EXECUACAO TESTE');

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
        infra.autenticar(data.usuario, data.senha);
    });
}
