/**
 * Funções utilitárias em js para usar com o casper
 */

/*  Variáveis Globais */
var screenshot = 1;
var stopTest = false;
var useTakeSS = true;

/**
 *  Renderizar imagens durante os testes para geracao de um mpeg
 *  Pre-requisitos:
 *  Instalar ffmpeg e inserir na variavel de ambiente
 *  Inserir stopTest = false dentro da funcao casper.run
 */
/*exports.videoRecord = function videoRecord() {
  setTimeout(function() {
    // Initial frame
    var frame = 0;
    // Add an interval every 50th second
    setInterval(function() {
      // Render an image with the frame name
      casper.page.render('C:/Video/arc' + (frame++) + '.png', {
        format: "png"
      });
      // Exit after condition
      if (stopTest === true) {
        phantom.exit();
      }
    }, 50);
  }, 999);
};*/

/*
 *  retornar data atual nos formatos disponíveis formato:
 *  0 - DD/MM/YYYY
 *  1 - DD/MM/YYYY HH:MM:SS
 *  2 - DD-MM-YYYY
 *  3 - DD-MM-YYYY HH:MM:SS
 *  4 - YYYY/MM/DD
 *  5 - YYYY/MM/DD HH:MM:SS
 *  6 - YYYY-MM-DD
 *  7 - YYYY-MM-DD HH:MM:SS
 */
exports.dataHoraAtual = function dataHoraAtual(formato, date) {
    date = date || new Date();

    var day = date.getDate();
    var mon = date.getMonth() + 1;
    var hors = date.getHours();
    var min = date.getMinutes();
    var sec = date.getSeconds();

    day = (day <= 9 ? '0' : '') + day;
    mon = (mon <= 9 ? '0' : '') + mon;
    hors = (hors <= 9 ? '0' : '') + hors;
    min = (min <= 9 ? '0' : '') + min;
    sec = (sec <= 9 ? '0' : '') + sec;

    switch (formato) {
        case 0:
            return day + '/' + mon + '/' + date.getFullYear();
            break;
        case 1:
            return day + '/' + mon + '/' + date.getFullYear() + ' ' + hors + ':' + min + ':' + sec;
            break;
        case 2:
            return day + '-' + mon + '-' + date.getFullYear();
            break;
        case 3:
            return day + '-' + mon + '-' + date.getFullYear() + ' ' + hors + ':' + min + ':' + sec;
            break;
        case 4:
            return date.getFullYear() + '/' + mon + '/' + day;
            break;
        case 5:
            return date.getFullYear() + '/' + mon + '/' + day + ' ' + hors + ':' + min + ':' + sec;
            break;
        case 6:
            return date.getFullYear() + '-' + mon + '-' + day;
            break;
        case 7:
            return date.getFullYear() + '-' + mon + '-' + day + ' ' + hors + ':' + min + ':' + sec;
            break;
        default:
            return date;
    }
};

/*
 *   tirar screenshot durante o teste
 */
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

exports.takeSS = takeSS;

/*
 *   validar o texto de um seletor
 */
function checkText(selector, valorAux) {
    var valor = valorAux || "";
    return function () {
        if (valor == "") {
            return this.evaluate(function (s) {
                return ($(s).text() != '') && ($(s).text() != undefined);
            }, selector);
        }
        return this.evaluate(function (s, v) {
            return $(s).text() == v;
        }, selector, valor);
    };
};

/*
 *   aguarda um texto aparecer em um elemento
 */
exports.waitForText = function waitForText(selector, valoresperado, messageSuccess, messageFail) {
    valoresperado = valoresperado || "";

    casper.waitFor(checkText(selector, valoresperado), function success() {
        var valorretornado = casper.evaluate(function (s) {
            return $(s).text();
        }, selector);
        messageSuccess = messageSuccess || selector + " " + "esperado '" + valoresperado + "' encontrado '" + valorretornado + "'";
        casper.test.pass(messageSuccess);

    }, function fail() {
        var valorretornado = casper.evaluate(function (s) {
            return $(s).text();
        }, selector);
        messageFail = messageFail || selector + " " + "esperado '" + valoresperado + "' encontrado '" + valorretornado + "'";
        takeSS();
        casper.test.fail(messageFail);
    });
};

/*
 *   validar o valor de um seletor
 */
function checkValue(selector, valorAux) {
    var valor = valorAux || "";
    return function () {
        if (valor == "") {
            return this.evaluate(function (s) {
                return ($(s).val() != '') && ($(s).val() != undefined);
            }, selector);
        }
        return this.evaluate(function (s, v) {
            return $(s).val() == v;
        }, selector, valor);
    };
}
;

/*
 *   aguarda um valor aparecer em um elemento
 */
exports.waitForValue = function waitForValue(selector, valoresperado, messageSuccess, messageFail) {
    valoresperado = valoresperado || "";

    casper.waitFor(checkValue(selector, valoresperado), function success() {
        var valorretornado = casper.evaluate(function (s) {
            return $(s).val();
        }, selector);
        messageSuccess = messageSuccess || selector + " " + "esperado '" + valoresperado + "' encontrado '" + valorretornado + "'";
        casper.test.pass(messageSuccess);

    }, function fail() {
        var valorretornado = casper.evaluate(function (s) {
            return $(s).val();
        }, selector);
        messageFail = messageFail || selector + " " + "esperado '" + valoresperado + "' encontrado '" + valorretornado + "'";
        takeSS();
        casper.test.fail(messageFail);
    }, 10000);
};

/*
 *   capturar o valor diretamente no document com evaluate
 */
exports.getValue = function getValue(selector) {
    return casper.evaluate(function (s) {
        return $(s).val();
    }, selector);
};

/*
 *   verifica se o objeto e um array
 */
exports.isArray = function isArray(obj) {
    return obj instanceof Array;
};

/*
 * Retorna a data atual com 30 dias de acréscimo
 */
function getDataFinal() {
    var data = new Date();
    var mes = (parseInt(data.getMonth()) + 2 == 13) ? 1 : parseInt(data.getMonth()) + 2;
    var ano = (parseInt(data.getMonth()) + 2 == 13) ? parseInt(data.getFullYear()) + 1 : data.getFullYear();
    return data.getDate() + "/" + mes + "/" + ano;
}
;

exports.getDataFinal = getDataFinal();

/*
 *  adiciona zeros a esquerda
 */
exports.padLeft = function padLeft(word, size) {
    do {
        word = "0" + word;
    } while (word.length < size);

    return word.toString();
};

// valida se um elemente json esta vazio
exports.isEmpty = function isEmpty(element) {

    var empty = true;

    for (var i in element) {
        if (element.hasOwnProperty(i)) {
            empty = false;
            break;
        }
    }
    return empty;
};

exports.getValorMonetario = function getValorMonetario(str) {
        return parseInt( str.replace(/[\D]+/g,'') );
}

exports.formatarMoedaReal = function formatarMoedaReal(int) {
        var tmp = int+'';
        tmp = tmp.replace(/([0-9]{2})$/g, ",$1");
        if( tmp.length > 6 )
                tmp = tmp.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");

        return tmp;
}