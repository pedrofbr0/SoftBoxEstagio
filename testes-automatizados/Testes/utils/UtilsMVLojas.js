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
var url = 'http://10.30.237.98:8080/';

// Homologacao
var urlbase = {
    "MVSH": 'http://10.41.0.100:8080/',
    "INLOJAD": 'http://10.30.237.94:8080/',
    "RELODEV": 'http://10.30.237.96:8080/',
    "RELOWMS": 'http://10.30.237.238:8080/',
    "LOCALHOST": 'http://localhost:8080/',
    "RELOH": 'http://10.30.232.18:8080/',
    "RELOD": 'http://10.41.0.71:8080/',
    "RELOH_EXA": 'http://10.41.0.101:8080/',
    "RELOH_BA": 'http://10.41.0.101:8080/'
};

// Para rodar o casper no jenkins
// http://localhost:8080/

/**
 * Realiza Login no sistema 
 * 
 * @params LOJA, int - com o número da loja a logar
 * @params USUARIO, int - com o número do usuário a logar
 * @params SENHA, string - senha do usuário
 * @params URL, string - com informação de qual banco o sistema usará (MVSH/INJOJAD/RELODEV)
 */
exports.autenticar = function autenticar(LOJA, USUARIO, SENHA, URL, empresa) {

    LOJA = LOJA || loja;
    USUARIO = USUARIO || usuarioPadrao;
    SENHA = SENHA || senhaPadrao;

    URL = urlbase[URL] || url;

    casper.echo(".. " + URL);

    casper.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/38.0.5');
    casper.page.viewportSize = {width: 1920, height: 1080};
    
    casper.thenOpen(URL + 'lojas/seguranca?action=login', function () {

    	casper.then(function () {
            casper.waitForSelector('#formLogin', function success() {
                // se achou o login , realizar login

                casper.sendKeys('input#j_loja', LOJA, {reset: true});
                casper.sendKeys('input#j_username_fake', USUARIO);
                casper.sendKeys('input#j_password', SENHA);
                casper.click("input[type=button][value='Entrar']");

                waitForLoad();

                utils.takeSS("telaLogin");

            }, function fail() {
                utils.takeSS("falhaAbrirTelaLogin");
                casper.test.fail('Falha ao abir tela de login');
            });
        });

    	/**
        casper.then(function () {
            mvLojas.waitForLoad();
            
            if(empresa == 5){
            	casper.then(function () {
            		utils.takeSS("selecionarEmpresa");
            		this.evaluate(function () {
            			document.getElementById("selEmpresa").value = 5;
            		});
            		casper.test.pass("Empresa Selecionada");
            	});
            	
            	casper.then(function () {
            		utils.takeSS();
            		//this.mouse.click("#divMultiEmpresa .buttonEntrar");
            	});
            	
            	casper.then(function () {
            		waitForLoad();
            	});
            }
        });
        **/

        casper.then(function () {
            utils.takeSS('telaConsultaCliente');
            casper.waitForSelector('input#txt-cliente', function success() {
                // se achou o login , realizar login
                casper.test.pass('Login realizado com sucesso! Usuario: ' + USUARIO + " - Loja: " + LOJA);
            }, function fail() {
                utils.takeSS('Falha ao autenticar');
                casper.test.fail('Falha ao autenticar no MV Lojas');
            });
        });

        casper.then(function () {

        });

    });

};

/**
 *   abre uma tela e verifica o titulo da tela
 */
exports.abrirTela = function abrirTela(url, titulo, base) {

    casper.echo(urlbase[base] + url);

    casper.thenOpen(urlbase[base] + url, function () {
    	casper.test.pass('Entrou na página com sucesso');
    	
    	this.waitForText(titulo, function success() {
            casper.test.pass('Entrou na página ' + titulo + ' com sucesso');

            waitForLoad();
        }, function fail() {
            utils.takeSS('Falha ao entrar na página ' + titulo);
            casper.test.fail('Falha ao entrar na página ' + titulo);
        });
    });
};

// seta um valor em um selector
exports.$val = function $val(selector, value, keepFocus) {
    keepFocus = (keepFocus != undefined || keepFocus != '' || keepFocus != null) ? false : true;
    return casper.evaluate(function (selector, value, keepFocus) {
        $(selector).val(value).trigger('change');
        if (!keepFocus) {
            $(selector).blur();
        }
        return $(selector).val();
    }, selector, value, keepFocus);
};

// paga valor de seta valor em um select (comobo box)
exports.$valCombo = function $val(selector, value, keepFocus) {
    keepFocus = (keepFocus != undefined || keepFocus != '' || keepFocus != null) ? false : true;

    casper.echo("selector" + selector + " value: " + value);
    
    return casper.evaluate(function (selector, value, keepFocus) {
    	
        MvLojas.utils.setValue(value, selector);

        $(selector).change().trigger("chosen:updated");

        if (!selector) {
            $(selector).blur();
        }
        return $(selector).val();
    }, selector, value, keepFocus);
};

// define valor de um checkbox (marcado/desmarcado)
exports.$valCheck = function $valCheck(selector, value) {

    return casper.evaluate(function (selector, value) {

        $(selector).prop("checked", value);
        $(selector).change().trigger("chosen:updated");
        return $(selector).val();

    }, selector, value);
};

/*
 * recupera o valor de um elemento
 */
function getElementValue(selector) {
    return casper.evaluate(function (selector) {
        return $(selector).val();
    }, selector);
};
exports.getElementValue = getElementValue;

/*
 *   recupera o texto de um elemento
 */
function getElementText(selector) {
    return casper.evaluate(function (selector) {
        return $(selector).text();
    }, selector);
};
exports.getElementText = getElementText;

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


/*
 *   aguarda pop-up abrir
 */
exports.waitModalOpenEvt = function (selectorModal) {
    casper.waitUntilVisible(selectorModal);
    casper.wait(1000);

};

/*
 *   aguarda pop-up fechar
 */
exports.waitModalCloseEvt = function (selectorModal) {
    casper.wait(500);
    casper.waitWhileVisible(selectorModal);
};

/*
 *   click em um elemento
 */
function click(selector) {

    return casper.evaluate(function (selector) {
        return $(selector).click();
    }, selector);

};
exports.click = click;

/*
 * Selecionar um item no grid com o valor passado em sampleObj como json pela propriedade title.
 * 
 * Ex: findRowTable (#tbl-crud, 
 * 	{ 	id_ctt_definitivo: '1',
 * 		documento_terceiros: '56128820000118'
 * }, function (selector) )
 * 
 *  #tbl-crud = id da tabela do grid
 *  id_ctt_definitivo = um title de uma das colunas do grid
 *  documento_terceiros = um title de uma das colunas do grid
 *  
 *  function (selector) = ira retornar uma lista de funcoes que podem ser utilizadas na linha do grid achado
 *  
 */
exports.findRowTable = function findRowTable(tblSelector, sampleObj, fncCallback) {
    return function () {

        var idx = casper.evaluate(function (tblSelector, sampleObj) {

            var gridElems = $(tblSelector).getRowData();

            //console.log(gridElems);
            
            var i = 0;

            for (; i < gridElems.length; i++) { // Linhas
                var equals = false;

                for (var prop in sampleObj) { // Percorre o objeto informado

                	//__utils__.echo(sampleObj[prop] + ' - '+ gridElems[i][prop]);
                	
                    if ((sampleObj[prop] + '') != (gridElems[i][prop] + '')) { // Compara se o valor da propriedade eh diferente do valor grid
                        equals = false;
                        break;
                    }

                    equals = true;
                }

                if (equals) {
                    return i;
                }
                
            }

            return -1;
        }, tblSelector, sampleObj);

        
        var hasFilter = casper.evaluate(function (tblSelector) {
            return !$(tblSelector + ' > tbody > tr:first').hasClass('jqgrow');
        }, tblSelector);

        //console.log('idx: ' + idx + ' hasFilter: ' + hasFilter);
        
        //console.log('table' + tblSelector + ' > tbody > tr:nth-child(' + (hasFilter ? idx + 2 : idx + 1) + ') > td');
        
        var ret = (idx < 0) ? false : 'table' + tblSelector + ' > tbody > tr:nth-child(' + (hasFilter ? idx + 2 : idx + 1) + ') > td';

        return fncCallback.call(this, ret, idx + 1);

    };
};

/*
 *   Retorna o numero de registros de uma grid
 */
function gridLength(selector) {
    return casper.evaluate(function (selector) {
        var grid = $(selector).getRowData();
        return grid.length;
    }, selector);
};
exports.gridLength = gridLength;

/*
 * 	retorno o id da linha do grid (jqGrid)
 */
function idLineGrid(selector) {
    return casper.evaluate(function (selector) {
        return $(selector).parent().attr("id");
    }, selector);
};
exports.idLineGrid = idLineGrid;

/*
 * selecinad linha escondida no grid
 */
function sellHideLineGrid(grid, line) {

    return casper.evaluate(function (grid, line) {
        return $(grid).jqGrid('setSelection', line);
    }, grid, line);

}
exports.sellHideLineGrid = sellHideLineGrid;

/**
 * retorna a parametriação da tabela MV_NIVEL_CADASTRO, para saber quais campos serão validados
 */
function mvNivelCadastro(loja) {

    return nivelCadastro[loja];

//	casper.then(function () {
//		ret = casper.evaluate(function (BD) {
//	        
//			var n = MvLojas.context.lookupEjb("CadastroNivelCadastroBO");
//			
//			return n;
//			
//	    }, n);
//	});
//	
//
//
//	
//	casper.then(function () {
//		casper.wait(5000);
//	});
//	
//	casper.then(function () {
//		require('utils').dump(ret);
////		setTimeout(function () {
////			console.log("entrei no setTimeOut");
//////			casper.echo("RETORNO: " + ret);
//////			require('utils').dump(ret);
////			return true;
////		}, 10000);
//	});
//	
//	casper.then(function () {
//		return ret;
//	});
}
;
exports.mvNivelCadastro = mvNivelCadastro;

/*
 *   retornar o valor do atributo de um elemento
 */
exports.verifyAttr = function (selector, attribute) {
    return casper.evaluate(function (selector, attribute) {
        return $(selector).attr(attribute);
    }, selector, attribute);
};

/*
 * verifica se existe um elemento em um selector html
 */
exports.hasAttr = function (selector, attribute) {
    if (this.verifyAttr(selector, attribute)) {
        return true;
    }
    return false;
};

exports.clearForm = function (selector) {
    return casper.evaluate(function (selector) {
        return MvLojas.utils.setValue("", selector);
    }, selector);
};

/**
 * Função responsável por gerar um CPF válido
 * @returns int - cpf
 */
function randomiza(n) {
    return Math.round(Math.random() * n);
}

function mod(dividendo, divisor) {
    return Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
}

function gerarCPF() {

    var n = 9;
    var n1 = randomiza(n);
    var n2 = randomiza(n);
    var n3 = randomiza(n);
    var n4 = randomiza(n);
    var n5 = randomiza(n);
    var n6 = randomiza(n);
    var n7 = randomiza(n);
    var n8 = randomiza(n);
    var n9 = randomiza(n);
    var d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
    d1 = 11 - (mod(d1, 11));
    if (d1 >= 10)
        d1 = 0;
    var d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
    d2 = 11 - (mod(d2, 11));
    if (d2 >= 10)
        d2 = 0;
    return '' + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;

}
exports.gerarCPF = gerarCPF;

function gerarCnpj() {

    var n = 9;
    var n1 = randomiza(n);
    var n2 = randomiza(n);
    var n3 = randomiza(n);
    var n4 = randomiza(n);
    var n5 = randomiza(n);
    var n6 = randomiza(n);
    var n7 = randomiza(n);
    var n8 = randomiza(n);
    var n9 = 0;// gera_random(n);
    var n10 = 0;// gera_random(n);
    var n11 = 0;// gera_random(n);
    var n12 = 1;// gera_random(n);
    var d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8
            + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;
    d1 = 11 - (mod(d1, 11));
    if (d1 >= 10)
        d1 = 0;
    var d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8
            + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;
    d2 = 11 - (mod(d2, 11));
    if (d2 >= 10)
        d2 = 0;
    return '' + n1 + n2 + '.' + n3 + n4 + n5 + '.' + n6 + n7 + n8 + '/' + n9 + n10 + n11 + n12 + '-' + d1 + d2;
}
exports.gerarCnpj = gerarCnpj;

/**
 * Valida se uma variável ou posição de um array é vazia
 * @param elementm, string valor a ser comparado
 * @returns bool, retorna TRUE caso esteja vazio e FALSE caso contrário
 */
function isNull(element) {
    return element == "" || element == null || element == undefined ? true : false;
}
exports.isNull = isNull;

/**
 * Função responsável por remover mascaras de conjunto de números
 */
exports.removerMascara = function (element) {
    return element.replace(/[^0-9]+/g, '');
};

/*
 * abre a tela de consulta de pedido
 */
exports.abrirTelaConsultaPedido = function () {

    casper.then(function () {
        casper.evaluate(function () {
            carregaTela('MVVendas', 'consultas', 'consulta_pedido.html');
        });
    });

    casper.then(function () {
        mvLojas.waitForLoad();
    });

    casper.then(function () {
        utils.takeSS("telaConsultaPedido");

        this.waitForSelector("#fm-consulta-pedido", function () {
            utils.takeSS("telaPedido");
            this.test.pass("Tela de Consulta de Pedido aberta com sucesso!");
        });
    });

};

/*
 * seleciona uma linha da grid de produto, baseado nos seguintes parametros que existe em cada linha (codigo, cor, voltagem).
 */
exports.sellLineGridProd = function (cod, cor, v) {
    return casper.evaluate(function (cod, cor, v) {
        return $("tr:contains('" + cod + "'):contains('" + cor + "'):contains('" + v + "')").click();
    }, cod, cor, v);
};

exports.sellLineGridPromocao = function (tipoVenda, valorPromocao) {
    return casper.evaluate(function (tipoVenda, valorPromocao) {
        return $("tr:contains('" + tipoVenda + "'):contains('" + valorPromocao + "') .radio-seleciona-promocao").click();
    }, tipoVenda, valorPromocao);
};

exports.sellLineGridKit = function (nroKit, descKit) {
    return casper.evaluate(function (nroKit, descKit) {
        return $("tr:contains('" + nroKit + "'):contains('" + descKit + "') input[name='rdb-pd-kit']").click();
    }, nroKit, descKit);
};

/*
 * pega o id de uma linha do grid de produtos
 */
exports.getIdLineGrid = function (cod, cor, v) {
    return casper.evaluate(function (cod, cor, v) {
        return $("tr:contains('" + cod + "'):contains('" + cor + "'):contains('" + v + "')").attr("id");
    }, cod, cor, v);
};

exports.getNumPedido = function () {

    var pedido = getElementValue("#mdl-elabora-pedido #txt-pedido-venda").split("/");

    return pedido[0].trim();

};

exports.contagemRegressiva = function (segundos) {

    casper.repeat(segundos + 1, function () {

        casper.wait(1000, function () {
            this.echo(segundos).clear();
            this.clear();
            segundos--;
        });

    });

};

exports.setDataRendaPrincipal = function(qtdDias) {
	var dataCompleta = mvLojas.dataAdd(-40);
	
	return dataCompleta.substring(3);
}

//seta data opção de entrega "Agendada" para 1 dia após a data atual
exports.setDataAgendada = function() {
	return mvLojas.dataAdd(1);
}

//seta data da oção de entrega "Agendada Cliente" para 31 dias após a data atual
exports.setDataAgendadaCliente = function () {
	return mvLojas.dataAdd(32);
}

//recebe como parametro  um inteiro e soma o mesmo com a data atual e retorna a data formatada no formato dd/mm/AAAA
exports.dataAdd = function(qtdDias) {
	
	qtdDias = null || undefined ? 0 : qtdDias;
	
	var data = new Date();
	data.setDate(data.getDate() + qtdDias);
	return utils.dataHoraAtual(0, data);
}


exports.somaMes = function(data) {
	
	var mes = data.getMonth()+1;
	var dia = data.getDate(); 
	var ano = data.getFullYear();
	var result;	
	
	if (mes == 1) {
		if (isBissexto(ano) && dia > 29) {
			result = 29 + "/" + (mes+1) + "/" + ano;
		} else {
			result = 28 + "/" + (mes+1) + "/" + ano;
		}
	} else if (mes == 3 || mes == 5 || mes == 8 || mes == 10) {
		if (dia == 31) {
			result = (dia-1) + "/" + (mes+1) + "/" + ano;
		} else {
			result = dia + "/" + (mes+1) + "/" + ano;
		}
	} else if (mes == 12) {
		result = dia + "/01/" + (ano+1);
	} else {
		result = dia + "/" + (mes+1) + "/" + ano;
	}
	
	return formatarSaidaDataSomaMes(result);

}

function isBissexto(ano) {
	
	if (ano % 400 == 0) {
		return true;
	}
	else if ((ano % 4 == 0) && (ano % 100 != 0)) {
		return true;
	}
	else {
		return false;
	}
	
}

function formatarSaidaDataSomaMes(data) {
	
	data = data.split("/");
	var result = '';
	
	for(var i=0; i<data.length; i++) {
		
		var n='';
		
		if (data[i] < 10) {
			n = "0"+data[i];
		} else {
			n = data[i];
		}
		
		result += i == 2 ? n : n + "/"; 
	}
	
	return result;
	
}

exports.echoToFile = function echoToFile(arquivo, conteudo, modo) {
	var fs = require('fs');

	if (typeof modo == "undefined") {
		modo = 'w';
	}
	
	try {
        fs.write("C:\\Temp\\" + arquivo, conteudo, modo);
    } catch(e) {
        console.log(e);
    }

    //phantom.exit();
};

var dadosLog = "";

exports.logTexto = function logTexto(texto) {
	dadosLog += texto;
};

exports.logCabecalhoPedido = function logCabecalhoPedido() {
	
	dadosLog += "\n-------------  CABECALHO DO PEDIDO -------------\n";
	
	dadosLog += "Entrada: " + getElementValue("#frm-pedido #txt-entrada") + "\n";
	dadosLog += "Plano: " + getElementValue("#frm-pedido #cbx-plano") + "\n";
};

exports.logProdutos = function logProdutos() {

	dadosLog += "\n-------------  PRODUTOS -------------\n";
	
	var registros = casper.evaluate(function () {
		return $('#grd-produtos').getRowData();
	});
	
	for (var i = 0; i < registros.length; i++) {
		dadosLog += registros[i]["produto.cod_nro_produto"] + " | " + registros[i]["produto.desc_produto"] + " | " + registros[i]["vr_total_produto"] + "\n";
	}
};

exports.logGarantias = function logGarantias() {
	
	dadosLog += "\n-------------  GARANTIAS -------------\n";
	
	var registros = casper.evaluate(function () {
		return $('#grd-garantias').getRowData();
	});
	
	for (var i = 0; i < registros.length; i++) {
		dadosLog += registros[i]["nro_item_pedido_produto"] + " | " +  registros[i]["nro_servico"] + " | " + registros[i]["desc_servico"] + " | " + registros[i]["valor_total"] + "\n";
	}
};

exports.logServicos = function logServicos() {
	
	dadosLog += "\n-------------  SERVICOS -------------\n";
	
	var registros = casper.evaluate(function () {
		return $('#grd-servicos').getRowData();
	});
	
	for (var i = 0; i < registros.length; i++) {
		dadosLog += registros[i]["nro_servico"] + " | " +  registros[i]["desc_servico"] + " | " + registros[i]["vr_total"] + "\n";
	}
};

exports.gravarLog = function gravarLog(nomArquivo) {
	var dataAtual = new Date();
	var nomArquivoLogData = utils.dataHoraAtual(1, dataAtual).replace(/\/|:|\ /gi, "_");
	
	this.echoToFile(nomArquivo + "_" + nomArquivoLogData, dadosLog.split(new RegExp('undefined', 'i')).join(''), "w+");
}
