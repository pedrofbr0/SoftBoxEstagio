var require = patchRequire(require);

/**
 * Funções utilitárias
 */
exports.analiseComercial = function (produtos) {
	var n = 0;
	
	casper.then(function () {

		casper.echo("... Analise Comercial:");
		
		mvLojas.click("#tab-analise-comercial");
		
		casper.then(function () {
            utils.takeSS("precosVendaAntes");
        });
		
        casper.eachThen(produtos, function (response) {
            (function (data) {
            	n++;
            	
            	casper.then(function () {
            		
            		var descProd = data.cod_nro_produto + "." + data.cod_cor_produto + "." + data.cod_tipo_voltagem + "   " + data.descricao;
            		
            		casper.then(mvLojas.findRowTable('#grd-analise-comercial', {
                        "codigo_produto": descProd
                    }, function (selector) {
                    	if (selector == false) {
                    		this.test.fail("Produto: " + descProd + " nao encontrado na grid de análise comercial.");
                    		utils.takeSS("ProdutoNaoEncontrado");
                    	} else {
                    		
                    		//casper.echo("Seletor " + selector);
                    		casper.echo("Produto: " + descProd);
                    		
                    		if (data.preco_venda) {
                        		casper.echo("... Produto: " + descProd + ", preco de venda: "+ data.preco_venda);
                        		
                        		var idLinha = casper.evaluate(function (descProd) {
                        	        return $("tr:contains('" + descProd + "')").attr("id");
                        	    }, descProd);
                        		
                        		mvLojas.$val("#grd-analise-comercial tr[id='" + idLinha + "'] input[name='vr_unitario_produto_an']", data.preco_venda);
                        	}
                    	}
                    }));
                });
            })(response.data);
        });
        
        casper.then(function () {
            utils.takeSS("precosVendaDepois");
        });
	});
};