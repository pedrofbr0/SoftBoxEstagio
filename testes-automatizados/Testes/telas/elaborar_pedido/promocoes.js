var require = patchRequire(require);


exports.promocoes = function (data){
	
	casper.then(function () {
		this.waitForSelector(".mdl-consulta-promocao", function () {
	        utils.takeSS("modalConsultaPromocao");
	        this.test.pass("Modal de promocao aberta!");
		});
	});
			
    if (!data.ind_sel_promo) {
    	casper.then(function () {
            casper.echo(".. Fechando janela de promocao sem informar promocao");
            mvLojas.click(".mdl-consulta-promocao .btn.Fechar");
    	});
        return false;
    }else{
    	informarPromocao(data);
    }
};

function informarPromocao(data){
	casper.then(mvLojas.findRowTable('#grid-consulta-promocao', {
		desc_tipo_venda: data.tipo_venda,
		vr_financiado: data.valor_promocao
    }, function (selector) {
    	if (selector == false) {
    		this.test.fail("Promocao nao encontrada na grid de promocoes.");
    		utils.takeSS("promocaoNaoEncontrada");
    	} else {
    		this.test.pass("Promocao '" + data.valor_promocao + "' existe na grid de promocoes.");
    		utils.takeSS("promocaoEncontrada");
    		
    		casper.then(function () {
    			this.echo(".. Selecionando a promocao: " + data.valor_promocao);
    	        mvLojas.sellLineGridPromocao(data.tipo_venda, data.valor_promocao);
    	    });
    		
    		casper.then(function () {
    	        mvLojas.waitForLoad();
    	    });
    		
    		casper.then(function () {
    			utils.takeSS("promocaoSelecionada");
    			this.echo(".. Clicando no botao Ok para sair da tela de promocao");
    	        this.mouse.click(".mdl-consulta-promocao.in .btn.Ok");
    	    });
    		
    		casper.then(function () {
    	        mvLojas.waitForLoad();
    	    });
    		
    	}
    	
    }));
};