var require = patchRequire(require);

exports.receberPedido = function (dadosPedido) {
	casper.then(function () {
    	pdvWeb.navegarPDV(["Pedido"]);
    });

    casper.then(function () {
    	casper.echo(".. Informando nro pedido: " + dadosPedido.nro_pedido);
    	mvLojas.$val("#filtro-flag-acesso", dadosPedido.nro_pedido);
    	
    	casper.click("#btn-pesquisar");
    	utils.takeSS("telaPesquisaPedido");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.then(function (){
	    casper.click("#btn-receber");
		utils.takeSS("telaRecebimentoPedido");
    });
		
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
   
    casper.then(function () {
    	casper.wait(5000, function(){
		    var qtdFormasPagto = formasPagto().length;
	    	
		    casper.echo(".. Qtd formas de pagto: " + qtdFormasPagto);
	    			
	    	for (i = 1; i <= qtdFormasPagto; i++) {
	    		casper.wait(5000);
	    		var formaPagto = casper.getElementInfo(x('//label[contains(text(), "Formas de Pagamento")]/parent::div/table/tbody/tr[' + i + ']/td[1]')).text;
	    		var vlrReceber = casper.getElementInfo(x('//label[contains(text(), "Formas de Pagamento")]/parent::div/table/tbody/tr[' + i + ']/td[2]')).text;
	    		
	    		casper.echo(".. " + formaPagto + ": " + vlrReceber);
	    		
	    		var vlrReceberFormaPagto = utils.getValorMonetario(vlrReceber);
		    	
		    	mvLojas.$val('input[ng-model="planilha.valorRecebidoPedido"]', vlrReceberFormaPagto+'');
	    	}
    	});
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    	utils.takeSS("telaInformandoRecebimentoPedido");
    });
    
    casper.then(function() {
    	casper.wait(5000);
    	mvLojas.click("#btn-emitir-cupom");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    	casper.wait(5000);
    	utils.takeSS("telaAposRecebimentoPedido");
    });
    
    casper.then(function () {
    	mvLojas.waitForLoad();
    });
    
    casper.wait(6000, function () {
        /*casper.waitForText("recebido com sucesso!", function success() {
            casper.test.pass("Pedido recebido com sucesso.");
            utils.takeSS("pedidoRecebidoComSucesso");
        }, function fail() {
            utils.takeSS('erroAoReceberPedido');
            casper.test.fail("Erro ao receber pedido.");
        });*/
    });
};


function formasPagto() {
	return casper.evaluate(function() {
	    return [].map.call(__utils__.findAll('table[ng-if="pedido.planilhas.length"] tbody tr'), function(node) {
	        return node.getAttribute('class');
	    });
	});
}