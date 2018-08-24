/*@include de funcões utilitárias*/


var require             		= patchRequire(require);

var mvLojas             		= require('../../utils/UtilsMVLojas.js');
var utils               		= require('../../utils/Utils.js');

var dadosCliente				= require('../../data/cadastro_cliente_mvshop.json');
var dadosPedido					= require('../../data/pedido_cartao_mvshop.json');
var dadosConexao				= require('../../data/conexao.json');
var dadosValidacao				= require('../../data/data_validacao.json');

var telaLiberacao				= require('../../telas/elaborar_pedido/liberacoes.js');


var cpf;
var nivelCadastro;
var bd = "MVSH"; //"MVSH", "INLOJAD", "RELODEV"




/*@estrutura da suite de teste*/
casper.test.begin('\n@TestCase:\nText de Cadastro de Cliente Simples e Elaboracao de Pedido, testando as validacoes do sistema', 0, function suite(test) {

	//start na suite de teste
    casper.start();
    
    casper.eachThen(dadosConexao, function (response) {
	    (function (data) {
	    	
	    	if (data.executar_teste) {
	    		
		    	casper.echo("");
		    	casper.echo('INICIANDO EXECUACAO TESTE DA EMPRESA: ' + data.desc_empresa, 'INFO');
		    	
		    	execTest(data, dadosValidacao[data.banco]);
	    	}
	    	
	    })(response.data);
	});
    
    
    
    // execucao da suite de testes
    casper.run(function () {
        test.done();
    });

});


function validarErrosLogin(dadosLogin, bd) {
	
	casper.eachThen(dadosLogin, function (response) {
	    (function (data) {
	    	
	    	casper.userAgent ('Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/38.0.5');
	    	casper.page.viewportSize = { width: 1920, height: 1080 };
	    	
	    	var urlbase = { 
	    			"MVSH" 		: 'http://10.30.237.98:8080/',
	    			"INLOJAD" 	: 'http://10.30.237.94:8080/',
	    			"RELODEV" 	: 'http://10.30.237.96:8080/',
	    			"FIDELIS"	: 'http://10.30.125.10:8080/',
	    			"LOCALHOST"	: 'http://localhost:8080/'
	    	};
	    	
	    	casper.echo("");
	        casper.echo(data.msg_test);
	    	
	        casper.thenOpen(urlbase[bd] + 'lojas/seguranca?action=login', function () {
	        	
	        	casper.then(function () {
	    	    	casper.waitForSelector('#headerJSP', function success() {

	    	    		casper.sendKeys('input#j_loja', data.loja);
	    	            casper.sendKeys('input#j_username_fake', data.user);
	    	            casper.sendKeys('input#j_password', data.senha);
	    	            casper.click("input[type=button][value='Entrar']");
	    	            
	    	            mvLojas.waitForLoad();
	    	            
	    	            utils.takeSS("telaLogin");
	    	            
	    	        }, function fail() {
	    	            utils.takeSS("falhaAbrirTelaLogin");
	    	            casper.test.fail('Falha ao abir tela de login');
	    	        });
	        	});
	        	
	            
	        	casper.then(function() {
	        		
	        		utils.takeSS('validacaoLogin');

	        		casper.waitForText(data.msg_erro, function success(){
	    				casper.test.pass("Msg de erro Validada com sucesso: " + data.msg_erro);
	    			},function fail(){
	    				utils.takeSS('login_erroNaoValidado');
	    				casper.test.fail("Erro de login nao validado");
	    			});
	        		
	        	});
	        	
	        });
	    	
	    })(response.data);
	});
	
}

/**
 * funcao responsável por fazer as validacões de cpf ao cadastrar ou pesquisar um cliente
 * @param data, array as condicões a serem validadas
 */
function validarCPF(dadosCPF) {
	
	casper.eachThen(dadosCPF, function (response) {
	    (function (data) {
	    	
	    	casper.then(function(){
		    	casper.echo(data.msg_test);
		        mvLojas.$val('#cbx-cpf', data.cpf);
		    });
		    
		    // tentan cadastrar cliente sem informar CPF
		    casper.then(function () {
		    	casper.echo(data.msg_info);
		    	mvLojas.click("#btn-novo-cliente");
		    });
		    
		    casper.then(function () {
		    	mvLojas.waitForLoad();
		    });
		    
		    casper.then(function () {
		    	utils.takeSS("msgValidacaoCPF");
		    });
		    
		    casper.then(function (){
		        casper.waitForText(data.msg_erro, function success(){
		            casper.test.pass("Validacao realizada com sucesso: " + data.msg_erro);
		            casper.click('div.modal.alert.in, btn');
		        },function fail(){
		            utils.takeSS('falhaValidacaoCPF');
		            casper.test.fail(data.msg_falha_validacao);
		        });
		    });
		    
		    casper.then(function() {
		    	casper.echo("");
		    });
	    	
	    })(response.data);
	});
	
}

/**
 * funcao responsável por rodar os testes sequencialmente
 * @param data, array com informacões de onde o teste será executado
 */
function execTest(data, params) {
	
	casper.then(function () {
    	casper.echo(".. Iniciando validacoes");
    });
    
    // validacões na tela de login 
    casper.then(function () {
    	validarErrosLogin(params.validacoes_login, data.banco);
	});
    
    // loga com usuário senha e loja corretos
    casper.then(function () {
    	casper.echo("");
    	mvLojas.autenticar(data.loja, data.usuario, data.senha, data.banco); //OPcÕES DE BANCO 'MVSH | INLOJAD | RELODEV'
    });
    
    casper.then(function () {
    	utils.takeSS("telaAberta");
    });
    
    casper.then(function () {
    	cpf = mvLojas.gerarCPF(); 
    });
    
    casper.then(function () {
    	casper.echo("");
    	casper.echo(".. Validacoes CPF");
    	validarCPF(params.validacoes_cpf);
    });
    
    casper.then(function(){
    	casper.echo(".. Inserindo CPF válido ainda nao cadastrado no sistema");
        mvLojas.$val('#cbx-cpf', cpf);
    });
    
    // tentan cadastrar cliente sem informar CPF
    casper.then(function () {
    	casper.echo(".. CPF: " + cpf);
    	mvLojas.click("#btn-novo-cliente");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	utils.takeSS("telaCadCliente");
    });
    
    // AQUI INICIA O CADASTRO DE CLIENTE
    casper.then(function () {
    	this.echo(".. Informando Nome: " + params.dados_cliente.nome);
    	this.echo(".. Informando E-mail invalido: " + params.dados_cliente.email_invalido);
    	mvLojas.$val("#frm-cadastro-cliente #txtNome", params.dados_cliente.nome);
    	mvLojas.$val("#frm-cadastro-cliente #txtEmail", params.dados_cliente.email_invalido);
    });
    
    casper.then(function () {
    	utils.takeSS("formPrincipal");
    });
    
    casper.then(function () {
    	this.echo(".. Clicando no botao salvar sem informar fone");
    	mvLojas.click("#mdl-cadastro-cliente #btn-salvar-cliente");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    	utils.takeSS('validacaoFone');
    });
    
    casper.then(function (){
        casper.waitForText("Obrigatório fazer o cadastro de 2 Telefones!", function success(){
            casper.test.pass("Validacao realizada com sucesso: Obrigatório fazer o cadastro de 2 Telefones!");
            casper.click('div.modal.alert.in, btn');
        },function fail(){
            utils.takeSS('erroValidarFone');
            casper.test.fail("Fones em branco nao foram validados");
        });
    });
    
    casper.then(function () {
    	this.echo(".. Informando fones: (" + params.dados_cliente.ddd_residencial + ") " + params.dados_cliente.nro_residencial + " | (" + params.dados_cliente.ddd_celular + ")" + params.dados_cliente.nro_celular);
    	mvLojas.$val("#txtDddResidencial", params.dados_cliente.ddd_residencial);
        mvLojas.$val("#txtNroTelefoneResidencial", params.dados_cliente.nro_residencial);
        mvLojas.$val("#txtDddCelular", params.dados_cliente.ddd_celular);
        mvLojas.$val("#txtNroTelefoneCelular", params.dados_cliente.nro_celular);
    });
    
    casper.then(function () {
    	utils.takeSS("fonesInformados");
    });
    
    casper.then(function () {
    	this.echo(".. Clicando em salvar com E-mail incorreto.");
    	mvLojas.click("#mdl-cadastro-cliente #btn-salvar-cliente");
    });
    
    casper.then(function () {
    	utils.takeSS("validacaoEmail");
    });
    
    casper.then(function (){
        casper.waitForText("O e-mail informado é inválido!", function success(){
            casper.test.pass("Validacao realizada com sucesso: O e-mail informado é inválido!");
            casper.click('div.modal.alert.in, btn');
        },function fail(){
            utils.takeSS('erroValidarEmail');
            casper.test.fail("E-mail incorreto nao foi validado");
        });
    });
    
    casper.then(function () {
    	this.echo(".. Informando e-mail válido: teste@gmail.com.br");
    	mvLojas.$val("#frm-cadastro-cliente #txtEmail", params.dados_cliente.email_valido);
    });
    
    casper.then(function () {
    	this.echo(".. Clicar em salvar sem informar um endereco");
    	mvLojas.click("#mdl-cadastro-cliente #btn-salvar-cliente");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	utils.takeSS("validacaoEndereco");
    });
    
    casper.then(function (){
        casper.waitForText("É obrigatório o preenchimento de um endereco principal. ", function success(){
            casper.test.pass("Validacao realizada com sucesso: É obrigatório o preenchimento de um endereco principal. ");
            mvLojas	.click('div.modal.in, .btn.Ok');
        },function fail(){
            utils.takeSS('erroValidarEndereco');
            casper.test.fail("Cliente salvo sem informar um endereco");
        });
    });
    
    // CADASTRO DE ENDEREcO
    casper.then(function () {
    	mvLojas.click("#aba-endereco #btn-novo-endereco");
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#mdl-pesquisa-endereco");
    });
    
    casper.then(function () {
    	utils.takeSS();
    });
    
    casper.then(function () {
    	mvLojas.clearForm("#frm-pesquisa-endereco-cliente");
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisando endereco sem fornecer nenhuma informacao.");
    	utils.takeSS("modalEndereco");
    });
    
    casper.then(function () {
    	mvLojas.click("#frm-pesquisa-endereco-cliente #btn-pesquisar-endereco");
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoPesquisaSemParametros");
    		
    		casper.waitForText("Para pesquisar um endereco devem ser informados estado e cidade ou um CEP!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Para pesquisar um endereco devem ser informados estado e cidade ou um CEP!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidarPesquisaEnderecoSemParametros');
                casper.test.fail("Pesquisa de endereco feita sem informar nenhum parametro");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisar por um CEP inválido");
    	mvLojas.$val("#txt-cep-pesquisa-endereco", params.dados_cliente.cep_invalido);
    });
    
    casper.then(function () {
    	mvLojas.click("#frm-pesquisa-endereco-cliente #btn-pesquisar-endereco");
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoPesquisaCEPInvalido");
    		
    		casper.waitForText("Nenhum registro foi encontrado!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Nenhum registro foi encontrado!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidarPesquisaCEPInvalido');
                casper.test.fail("Nao foi informado que CEP é inválido");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisando por cidade e por um estado diferente da cidade informada.");
    	this.echo(".. UBERLANDIA - GO");
    	mvLojas.$val("#frm-pesquisa-endereco-cliente #txt-nome-cidade", params.dados_cliente.cidade_invalida);
    	mvLojas.$valCombo("#frm-pesquisa-endereco-cliente #cbx-uf-pesquisa-endereco", params.dados_cliente.estado_invalido);
    });
    
    casper.then(function () {
    	utils.takeSS("cidadeEstadoDiferente");
    });
    
    casper.then(function () {
    	mvLojas.click("#frm-pesquisa-endereco-cliente #btn-pesquisar-endereco");
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoPesquisaCidadeEstado");
    		
    		casper.waitForText("Nenhum registro foi encontrado!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Nenhum registro foi encontrado!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidarPesquisaCidade!=Estado');
                casper.test.fail("Nao foi validado que a Cidade nao pertence ao Estado");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Informando um CEP valido: " + params.dados_cliente.cep_valido);
    	mvLojas.$val("#txt-cep-pesquisa-endereco", params.dados_cliente.cep_valido);
    });
    
    casper.then(function () {
    	mvLojas.click("#frm-pesquisa-endereco-cliente #btn-pesquisar-endereco");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	utils.takeSS("pesquisaCEPValido");
    	
    	casper.then(mvLojas.findRowTable('#grd-pesquisa-endereco', {
			cep : params.dados_cliente.cep_valido,
			cidade: params.dados_cliente.cidade_valida
		}, function (selector) {
			this.mouse.click(selector);
			utils.takeSS("cepEncontrado");
			casper.test.pass("CEP Encontrado.");
		}));
    	
    });
    
    casper.then(function () {
    	utils.takeSS("cepSelecionado");
    	this.echo(".. Selecionando endereco encontrado.");
    	mvLojas.click("#btn-selecionar-endereco");
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#mdl-endereco-cliente");
    });
    
    // COMPLEMENTO DO CADASTRO DE ENDEREcO
    casper.then(function () {
    	this.echo(".. Numero: " + params.dados_cliente.end_numero);
    	this.echo(".. Ind Numero: " + params.dados_cliente.ind_numero ? "Sim" : "Nao");
    	this.echo(".. Complemento: " + params.dados_cliente.desc_complemento);
    	this.echo(".. Reside desde: " + params.dados_cliente.reside_desde);
    	this.echo(".. Situacao Endereco: " + params.dados_cliente.desc_situacao_endereco);
    	this.echo(".. Tipo Endereco: " + params.dados_cliente.desc_tipo_endereco);
    	
    	mvLojas.$val("#frm-endereco-cliente input[data-column='nro_endereco_cliente']", params.dados_cliente.end_numero);
    	mvLojas.$valCheck("#frm-endereco-cliente #cbx-sem-numero", params.dados_cliente.ind_numero);
    	mvLojas.$valCombo("#cbx-tipo-complemento-endereco", params.dados_cliente.complemento);
    	mvLojas.$val("#frm-endereco-cliente #dp-residencia-cliente", params.dados_cliente.reside_desde);
    	mvLojas.$valCombo("#frm-endereco-cliente select[data-column='cod_situacao_residencia']", params.dados_cliente.situacao_endereco);
    	mvLojas.$valCombo("#frm-endereco-cliente select[data-column='nro_tipo_endereco']", params.dados_cliente.tipo_endereco);
    });
    
    casper.then(function () {
    	utils.takeSS("abaEndereco");
    	mvLojas.click("#mdl-endereco-cliente #btn-salvar-endereco");
    });
    
    casper.then(function () {
    	mvLojas.waitModalCloseEvt("#mdl-endereco-cliente");
    });
    
    casper.then(function () {
    	utils.takeSS("enderecoNoGrid");
    });
    
    casper.then(function(){
        if(mvLojas.gridLength('#grd-endereco') > 0){
            utils.takeSS('enderecoAdicionado');
            casper.test.pass('Endereco Adicionado');
        }else{
            utils.takeSS("enderecoNaoAdicionado");
        	casper.test.fail('Endereco nao foi adicionado');
        }
    });
    
    casper.then(function () {
    	this.echo(".. Clicando em salvar sem informar um endereco principal.");
    	mvLojas.click("#mdl-cadastro-cliente #btn-salvar-cliente");
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.in', function() {
    		utils.takeSS("validacaoSalvarSemEnderecoPrincipal");
    		
    		casper.waitForText("É obrigatório o preenchimento de um endereco principal.", function success(){
                casper.test.pass("Validacao realizada com sucesso: É obrigatório o preenchimento de um endereco principal.");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidacaoCadSemEndPrincipal');
                casper.test.fail("Foi permitido salvar cliente, sem informar um endereco principal");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Editar o endereco para principal.");
    });
    
    casper.then(function () {
    	mvLojas.click("#grd-endereco tr[id='1'] .editButtonEndereco button");
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#mdl-endereco-cliente");
    	utils.takeSS("modalEditarEnd");
    });
    
    casper.then(function () {
    	this.echo(".. Altrando endereco.");
    	this.echo(".. Tipo Endereco: " + params.dados_cliente.desc_tipo_endereco_alterado);
    	mvLojas.$valCombo("#frm-endereco-cliente select[data-column='nro_tipo_endereco']", params.dados_cliente.tipo_endereco_alterado);
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	utils.takeSS("aposEditarEndereco");
    	mvLojas.click("#mdl-endereco-cliente #btn-salvar-endereco");
    });
    
    casper.then(function () {
    	mvLojas.waitModalCloseEvt("#mdl-endereco-cliente");
    });
    
    casper.then(function () {
    	this.echo(".. Clicando no botao salvar com todos os dados corretos");
    	mvLojas.click("#mdl-cadastro-cliente #btn-salvar-cliente");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.in', function() {
    		utils.takeSS("msgClienteSalvo");
    		
    		casper.waitForText("Cliente salvo com sucesso!", function success(){
                casper.test.pass("Cliente salvo com sucesso!");
                mvLojas.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroSalvarCliente');
                casper.test.fail("Erro ao salvar cliente");
            });
    		
    	});
    });
    
    casper.then(function () {
    	utils.takeSS("clienteSalvo");
    });
    
    casper.then(function () {
    	casper.wait(5000);
    });
    
    
    casper.then(function () {
    	this.echo(".. Clicando em elaborar pedido sem informar um cliente.");
    	mvLojas.click("#btn-elaborar-pedido");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.wait(5000);
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#modal-elabora-pedido");
    	utils.takeSS("modalElaborarPedido");
    });
    
    casper.then(function () {
    	this.echo(".. Clicando em salvar sem fornecer nenhuma informacao");
    	mvLojas.click("#modal-elabora-pedido #btn-salvar-pedido");
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoSalvarPedidoSemCliente");
    		
    		casper.waitForText("Informe um", function success(){
                casper.test.pass("Validacao realizada com sucesso: Informe um CLIENTE para o pedido");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidacaoPedidoSemCliente');
                casper.test.fail("Foi permitido salvar um pedido sem informar um cliente");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Clicando no botao 'Produto', para adicionar um produto");
    	mvLojas.click("#btn-insere-produto");
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt(".mdl-consulta_produtos");
    	utils.takeSS("modalConsultaProdutos");
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisar por produto sem informar nenhum parametro.");
    	mvLojas.click(".mdl-consulta_produtos #btn-prod-pesq-prod");
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoPesquisaProdutoSemParametros");
    		
    		casper.waitForText("Favor informar pelo menos um filtro para pesquisar!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Favor informar pelo menos um filtro para pesquisar!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('validacaoPesquisaProdutoSemParametros');
                casper.test.fail("Pesquisa por produto foi feita sem nenhum parametro fornecido");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisando produto com descricao invalida.");
    	mvLojas.$val("input[data-column='desc_produto']", params.pedido.produto.desc_invalida);
    });
    
    casper.then(function () {
    	utils.takeSS("descProdutoInexistente");
    	mvLojas.click(".mdl-consulta_produtos #btn-prod-pesq-prod");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoPesquisaProdutoDescInvalido");
    		
    		casper.waitForText("Nenhum registro para visualizar!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Nenhum registro para visualizar!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidacaoPesquisaProdutoDescInvalida');
                casper.test.fail("Pesquisa realizada com descricao de produto inexistente");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisando produto sem estoque.");
    	mvLojas.$val("input[data-column='desc_produto']", params.pedido.produto.desc_sem_estoque);
    	mvLojas.$val("input[data-column='cod_nro_produto']", params.pedido.produto.nro_prod_sem_estoque);
    });
    
    casper.then(function () {
    	mvLojas.click(".mdl-consulta_produtos #btn-prod-pesq-prod");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function(){
    	if(mvLojas.gridLength('#grid-consulta-prod') > 0){
    		utils.takeSS('produtoEncontrado');
    		casper.test.pass('Produto encontrado');
    	}else{
    		utils.takeSS("erroAoBuscarProduto");
    		casper.test.fail('Produto nao encontrado');
    	}
	});
    
    casper.then(function () {
    	
    	casper.then(mvLojas.findRowTable('#grid-consulta-prod', {
    		cod_pai: params.pedido.produto.cod_sem_estoque + "." + params.pedido.produto.cor_sem_estoque + "." + params.pedido.produto.voltagem_sem_estoque
		}, function (selector) {
			this.mouse.click(selector);
			utils.takeSS("produtoSelecionado");
			casper.test.pass("Produto selecionado.");
		}));
    	
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoProdutoSemEstoque");
    		
    		casper.waitForText("Estoque nao encontrado!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Estoque nao encontrado!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidarProdutoSemEstoque');
                casper.test.fail("Nao foi informado que produto esta sem estoque.");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisando pelo ID de um item existente.");
    	mvLojas.$val("input[data-column='cod_nro_produto']", params.pedido.produto.nro_prod_ok);
    });
    
    casper.then(function () {
    	mvLojas.click(".mdl-consulta_produtos #btn-prod-pesq-prod");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function(){
    	if(mvLojas.gridLength('#grid-consulta-prod') > 0){
    		utils.takeSS('produtoEncontrado');
    		casper.test.pass('Produto encontrado');
    	}else{
    		utils.takeSS("erroAoBuscarProduto");
    		casper.test.fail('Produto nao encontrado');
    	}
	});
    
    casper.then(function () {
    	this.echo(".. Clicando no botao Kit, sem selecionar nenhum produto");
    	mvLojas.click(".mdl-consulta_produtos #btn-prod-pesq-kit");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		utils.takeSS("validacaoKitSemProduto");
    		
    		casper.waitForText("Favor selecionar um Produto!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Favor selecionar um Produto!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidacaoKitSemProduto');
                casper.test.fail("Erro na validacao de Kit sem produto selecionado");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Selecionado o produto e clicando na botao promocao.");
    });
    
    casper.then(function () {
    	
    	casper.then(mvLojas.findRowTable('#grid-consulta-prod', {
    		cod_pai: params.pedido.produto.voltagem_prod_ok
		}, function (selector) {
			this.mouse.click(selector);
			utils.takeSS("produtoSelecionado");
			casper.test.pass("Produto selecionado.");
		}));
    	
    });
    
    casper.then(function () {
    	mvLojas.click(".mdl-consulta_produtos #btn-prod-pesq-prom");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	mvLojas.click("#btn-prod-pesq-prom");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		
    		utils.takeSS("validacaoProdutoPromocao");
    		casper.waitForText("Produto sem promocao!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Produto sem promocao!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidacaoKitSemProduto');
                casper.test.fail("Erro na validacao de um produto que nao esta em promocao");
            });
    		
    	});
    });
    
    casper.then(function () {
    	utils.takeSS("antesSelecionarProduto");
    	this.echo(".. Selecionando o produto");
    	mvLojas.click(".mdl-consulta_produtos .modal-footer .Selecionar");
    });
    
    casper.then(function () {
    	mvLojas.waitModalCloseEvt(".mdl-consulta_produtos");
    	utils.takeSS("aposFecharModalConsultaProdutos");
    });
    
    casper.then(function () {
    	this.echo(".. Selecionando Tipo de Venda: " + params.pedido.pagamento.tipo_venda);
    	mvLojas.$valCombo("#cbx-nro-tipo-venda", params.pedido.pagamento.desc_tipo_venda);
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	this.echo(".. Clicar em editar Cliente, antes de informar um cliente para o pedido.");
    	mvLojas.click("#sf-cliente-pedido #btn-sf-cliente-pedido-edit");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		
    		utils.takeSS("validacaoEditCliente");
    		casper.waitForText("Selecione um cliente!", function success(){
                casper.test.pass("Validacao realizada com sucesso: Selecione um cliente!");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidacaoEdicacaoCliente');
                casper.test.fail("Erro na validacao de edicao de um cliente que ainda nao foi informado");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Selecionando cliente");
    	mvLojas.click("#sf-cliente-pedido #btn-sf-cliente-pedido-search");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#mdl-sf-cliente-pedido");
    	utils.takeSS("searchFieldCliente");
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisar cliente sem informar nome ou documento");
    	mvLojas.click("#mdl-sf-cliente-pedido .btn-success");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	casper.waitForSelector('.modal.alert.in', function() {
    		
    		utils.takeSS("validacaoPesquisaClienteSemFiltro");
    		casper.waitForText("Favor, ou informe o documento da pessoa ou informe seu nome.", function success(){
                casper.test.pass("Validacao realizada com sucesso: Favor, ou informe o documento da pessoa ou informe seu nome.");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroValidacaoPesquisaClienteSemParametros');
                casper.test.fail("Pesquisa de cliente nao obrigou a informacao de nome ou documentos");
            });
    		
    	});
    });
    
    casper.then(function () {
    	this.echo(".. Pesquisando por um CPF de um cliente válido com mais de 70 anos de idade.");
    	mvLojas.$val("#mdl-sf-cliente-pedido input[data-sf-column='cpf']", params.pedido.cliente.cpf);
    });
    
    casper.then(function () {
    	mvLojas.click("#mdl-sf-cliente-pedido .btn-success");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	utils.takeSS("pesquisaCliente");
    	
    	casper.then(mvLojas.findRowTable('#grid-sf-cliente-pedido', {
    		cpf_cnpj : params.pedido.cliente.cpf_formatado
		}, function (selector) {
			this.mouse.click(selector);
			utils.takeSS("clienteEncontrado");
			casper.test.pass("Cliente encontrado.");
		}));
    	
    });
    
    casper.then(function () {
    	this.echo(".. Validar cliente adicionado");
    	utils.waitForValue("#txt-sf-cliente-pedido-text", params.pedido.cliente.nome, "Cliente adicionado com sucesso!", "Falha ao adicionar cliente!");
    });
    
    // empenho do produto
    casper.then(function () {
    	this.echo(".. Empenhando produto para loja.");
    	mvLojas.click("#btn-detalhes-item-1");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	mvLojas.waitModalOpenEvt("#modal-detalhes-produto");
    	utils.takeSS("modalEmpenho");
    });
    
    casper.then(function () {
    	this.echo(".. Local Empenho: " + params.pedido.empenho.desc_local);
    	this.echo(".. Opcao Entrega: " + params.pedido.empenho.desc_opcao);
    	
    	mvLojas.$valCombo("#cbx-local-empenho", params.pedido.empenho.local);
    	mvLojas.$valCombo("#cbx-opcao-entrega", params.pedido.empenho.opcao);
    	mvLojas.$valCheck("#chx-ind-entrega", params.pedido.empenho.entrega);
    });
    
    casper.then(function () {
    	utils.takeSS("empenhoPreenchido");
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	mvLojas.click("#btn-empenhar-produto");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad(); 
    });
    
    casper.then(function () {
    	utils.takeSS("aposEmpenho");
    });
    
    //TODO ajuste de servicos
    casper.then(function () {
    	mvLojas.click("#tab-itens-pedido #tab-servicos");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	
    	casper.eachThen(params.pedido.servicos, function (response) {
    	    (function (data) {
    	    	
    	    	if (data.excluir) {
    	    		mvLojas.click("#btn-excluir-servico-" + data.cod);
    	    	}
    	    	
    	    })(response.data);
    	});
    	
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	utils.takeSS("servicos");
    });
    //TODO termina aqui a remocao do frete
    
    casper.then(function () {
    	this.echo(".. Abrindo a aba Planilha");
    	mvLojas.click("#tab-itens-pedido #tab-planilha");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
    	utils.takeSS("abaPlanilha");
    	mvLojas.click("#btn-nova-planilha");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function () {
		mvLojas.$valCombo("#grd-planilha-pagto tr[id='1'] select[data-column='flag_tipo_pagto_pedido']", params.pedido.planilha.tipo);
	});
	
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
	casper.then(function () {
		mvLojas.$valCombo("#grd-planilha-pagto tr[id='1'] select[data-column='nro_forma_pagto']", params.pedido.planilha.nro_forma_pagto);
	});
	
	casper.then(function () {
    	mvLojas.waitForLoad();
    });
	
	casper.then(function () {
		utils.takeSS("planilhaPreenchida");
		casper.echo(".. Inserindo valor diferente do total do pedido");
		casper.evaluate(function () {
			MvLojas.utils.setValue("0", "#grd-planilha-pagto tr[id='1'] input[data-column='vr_planilha']");
		});
		
		this.sendKeys("#grd-planilha-pagto tr[id='1'] input[data-column='vr_planilha']", "0,00");
	});
	
	casper.then(function () {
		mvLojas.waitForLoad();
	});
	
	casper.then(function () {
		utils.takeSS("planilhaPreenchida");
	});
	
	casper.then(function () {
		this.echo(".. Fechamento total do pedido com valor da Planilha diferente do Total do Pedido");
		mvLojas.click("#mdl-elabora-pedido #btn-fechamento-total-pedido");
	});
	
	casper.then(function () {
		mvLojas.waitForLoad();
	});
	
	casper.then(function () {
		
		utils.takeSS("aposClicarEmFechamentoTotalPedido");
		
    	casper.waitForSelector('.modal.alert.in', function() {
    		
    		utils.takeSS("validacaoValorErradoPlanilha");
    		
    		casper.then(function () {
    			casper.waitForText("está diferente do Total planilha", function success(){
    				casper.test.pass("Validacao realizada com sucesso: Total do pedido está diferente do Total planilha.");
    			},function fail(){
    				utils.takeSS('erroValorInvalidoPlanilha');
    				casper.test.fail("Erro na validacao de valores da Planilha");
    			});
    		});
    		
    		casper.then(function () {
    			casper.waitForText("É obrigatório informar um valor maior que zero na planilha para a forma de pagamento", function success(){
    				casper.test.pass("Validacao realizada com sucesso: É obrigatório informar um valor maior que zero na planilha para a forma de pagamento");
    			},function fail(){
    				utils.takeSS('erroValorValidar0');
    				casper.test.fail("Nao foi validado valor zero na planilha");
    			});
    		});
    		
    	});
    });
	
	casper.then(function () {
		mvLojas	.click('div.modal.in, .btn.Ok');
	});
	
	
	casper.then(function () {
		casper.echo(".. Inserindo valor correto: " + params.pedido.planilha.valor);
		casper.evaluate(function () {
			MvLojas.utils.setValue("0", "#grd-planilha-pagto tr[id='1'] input[data-column='vr_planilha']");
		});
		
		this.sendKeys("#grd-planilha-pagto tr[id='1'] input[data-column='vr_planilha']", params.pedido.planilha.valor);
	});
	
	casper.then(function () {
		utils.takeSS("planilhaPreenchidaCorretamente");
	});
	
	casper.then(function () {
		mvLojas.waitForLoad();
	});
	
	casper.then(function () {
		this.echo(".. Abrindo aba de servicos");
		casper.click("#tab-itens-pedido #tab-servicos");
	});
	
	casper.then(function () {
		mvLojas.waitForLoad();
	});
	
	casper.eachThen(params.pedido.servicos, function (response) {
	    (function (data) {
	    	
	    	if (data.adicionar) {
	    		
	    		casper.then(function () {
	    			this.echo(".. Adicionando servico: " + data.desc);
	    			mvLojas.click("#aba-servico #btn-insere-servico");
	    		});
	    		
	    		casper.then(function () {
	    			mvLojas.waitModalOpenEvt("#grid-consulta-servico");
	    			utils.takeSS("modalSelecaoServico");
	    		});
	    		
	    		casper.then(function () {
	    	    	
	    	    	casper.then(mvLojas.findRowTable('#grid-consulta-servico', {
	    	    		nro_servico : data.cod
	    			}, function (selector) {
	    				this.scrollToBottom();
	    				this.echo(selector);
	    				mvLojas.click(selector);
	    				utils.takeSS("serviceSelecionado");
	    				casper.test.pass("Servico Selecionado");
	    			}));
	    	    	
	    	    });
	    		
	    		casper.then(function () {
	    			mvLojas.click(".modal.in .Selecionar");
	    		});
	    		
	    		casper.then(function () {
	    			mvLojas.waitForLoad();
	    		});
	    		
	    		casper.then(function () {
	    			utils.takeSS("validacaoServico");
	    			
	    			casper.waitForText(data.msg_validacao, function success(){
	    				casper.test.pass(data.msg_exibicao);
	    				mvLojas	.click('div.modal.alert.in, .btn.Ok');
	    			},function fail(){
	    				utils.takeSS('erroValidarInsercaoServico');
	    				casper.test.fail(data.msg_erro);
	    			});
	    		});
	    		
	    	}
	    	
	    })(response.data);
	});
	
	
	
	casper.then(function () {
		this.echo(".. Realizando fechamento total do pedido.");
		mvLojas.click("#mdl-elabora-pedido #btn-fechamento-total-pedido");
	});
	
	casper.then(function () {
		
		utils.takeSS("fechamentoTotalPedido");
		
    	casper.waitForSelector('.modal.in', function() {
    		
    		casper.waitForText("Status: 30 - AGUARDANDO RECEBIMENTO", function success(){
                casper.test.pass("Status: 30 - AGUARDANDO RECEBIMENTO");
                mvLojas	.click('div.modal.in, .btn.Ok');
            },function fail(){
                utils.takeSS('erroFechamentoTotalPedido');
                casper.test.fail("Erro ao efetuar fechamento total do pedido");
            });
    		
    	});
    });
	
	casper.then(function () {
		mvLojas.waitForLoad();
		this.echo(".. Cancelando pedido");
	});
	
	casper.then(function () {
		mvLojas.click("#btn-cancelar-pedido");
	});
	
	casper.then(function () {
		mvLojas.waitForLoad();
	});
	
	casper.then(function () {
		
    	casper.waitForSelector('.modal.alert.in', function() {

    		utils.takeSS("cancelamentoPedido");
    		casper.waitForText("Realizar processo de cancelamento do pedido!", function success(){
                casper.test.pass("Realizar processo de cancelamento do pedido! Deseja cancelar?");
                mvLojas.click('div.modal.alert.in .btn.Sim');
            },function fail(){
                utils.takeSS('erroFechamentoTotalPedido');
                casper.test.fail("Erro ao efetuar fechamento total do pedido");
            });
    		
    	});
	});
	
	casper.then(function () {
		mvLojas.waitForLoad();
	});
	
	casper.then(function () {
		mvLojas.waitModalCloseEvt(".modal.alert.in");
	});
	
	casper.then(function () {
		mvLojas.waitModalOpenEvt(".modal.in");
		utils.takeSS("msgCancelado");
	});
	
	casper.then(function () {
		
		casper.waitForText("Pedido cancelado com sucesso!", function success(){
            casper.test.pass("Pedido cancelado com sucesso!");
            mvLojas	.click('div.modal.in, .btn.Ok');
        },function fail(){
            utils.takeSS('erroCancelarPedido');
            casper.test.fail("Erro ao efetuar cancelamento do pedido");
        });
		
	});
	
	casper.then(function () {
		mvLojas.waitForLoad();
	});
	
	casper.then(function () {
		utils.waitForValue("#frm-pedido #txt-status-pedido", "CANCELADO");
	});
	
	casper.then(function () {
		this.echo(".. Teste de cadastro de cliente básico e elaboracao de pedido, testando os fluxos alternativos. Finalizado com sucesso!");
	});
	
	casper.then(function () {
		utils.takeSS("final");
	});
	
}