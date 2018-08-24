# [PASSO A PASSO PARA VALIDAR CADA REGRA DO FLUXO DE APROVAÇÃO]

> Realizar a criação dos pedidos com o CPF: 339.179.793-22 USUÁRIO TESTE AUTOMATIZADO, para liberar a opção Depósito/Normal


### 4-ALERTAR_AUTOMATICAMENTE_PDVS_REC_DEV_PENDENTE:
```sh 
	Passos: - Pré-requisito:
            -- exec. 032_pedidoComTipoVendaAVistaLojaNormal.js
			-- recebe o pedido; (PDV - Opções > Recebimento Pedido (F3))
            -- exec. TrocaDevolucaoPagamentoAVistaDinheiro.java; (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=404, TIPO_FLUXO_APROVACAO=1, TIPO_FLUXO_APROVACAO_DESC=Troca)
            - Pós:
            -- o teste verifica no final(via query) se falta emitir recibo para o pedido;
```

### 1-PERMITE_GERAR_CONTROLE_FATURAMENTO_DEV_APOS_APROVACAO_FLUXO:
```sh
    Passos: - Pré-requisito:
            -- exec. 030_pedidoComTipoVendaAVista_empenhoDepositoNormal.js;
            -- recebe o pedido (PDV);
            -- exec. FaturarPedidoEmpenhoDeposito.java; (Informar NRO_PEDIDO)
            -- exec. TrocaProdutoEmpenhoDeposito.java; (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=405, NRO_STATUS_PRE_RECIBO_APROV=1)
            -- emite comprovante - recibo de troca; (PDV - Opções > Recibos > Troca/Devolução)
			-- gera novo pedido utilizando o recibo de troca exec. 031_pedidoComTipoVendaReciboTroca.js; (Informar nro_recibo_troca e nro_pedido em data/031/pedido_venda_produto.json)
            -- recebe o pedido; (PDV)
            - Pós:
            - Se a troca for realizada a regra está validada;
```        
		
### 5-EXIGE_APROVACAO_FINANCEIRO:
```sh
	Passos: - Pré-requisito:
            -- exec. 033_pedidoComTipoVendaCartaoCredito.js
            -- recebe o pedido (PDV);
            -- DevolucaoProdutoPagamentoFinanciado.java (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=406)
            - Pós:
            -- Se a devolução for realizada, então o usuário possui o privilégio(LIBERATRODAPRODDEFEITO) para executar essa regra
```

### 6-APROVA_REVERSAO_AUTOMATICO:
```sh    
	Passos: - Pré-requisito:
            -- exec. 033_pedidoComTipoVendaCartaoCredito.js
            -- recebe o pedido (PDV);
            -- DevolucaoProdutoPagamentoFinanciado.java (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=407)
            - Pós:
            -- Se a devolução for realizada, então o usuário possui o privilégio(LIBERATRODAPRODDEFEITO) para executar essa regra(6)
```
            
### 3-MARCAR_CONTROLE_BAIXA_PRIORITARIO:
```sh    
    Passos: - Pré-requisito:
            -- exec. 033_pedidoComTipoVendaCartaoCredito.js
            -- recebe o pedido (PDV);
            -- DevolucaoProdutoPagamentoFinanciado.java (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=408)
            -- Emitir recibo; (PDV)
            - Pós:
            -- exec. ValidaRegraBaixaPrioritaria.java (Informar NRO_PEDIDO)
```
			
### 2-LIBERAR_EMISSAO_RECIBO_DEVOLUCAO_SEM_NF_DEV_CONFIRMADA:
```sh    
    Passos: - Pré-requisito:
            -- exec. 032_pedidoComTipoVendaAVistaLojaNormal.js
            -- recebe o pedido (PDV);
            -- exec. TrocaDevolucaoPagamentoAVistaDinheiro.java; (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=409)
            - Pós:
            -- tentar emitir recibo de devolução sem confirmar NF de devolução; (PDV)
```
			
### 7-TROCA_SOMENTE_FATURADO: 
```sh
 	Passos: - Pré-requisito:
			-- Criar pedido com tipo de venda 53 - Cartão de Crédito e empenho em Depósito/Normal
            -- recebe o pedido (PDV);
			-- exec. TrocaProdutoEmpenhoDeposito.java; (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=410)
			- Pós:
			-- pedido não pode ser trocado;
```
			
### 8-NAO_PERMITE_DEVOLUCAO:
```sh
    Passos: - Pré-requisito:
			-- exec. 032_pedidoComTipoVendaAVistaLojaNormal.js
			-- recebe o pedido (PDV);
			-- exec. TrocaDevolucaoPagamentoAVistaDinheiro.java; (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=411, TIPO_FLUXO_APROVACAO=2, TIPO_FLUXO_APROVACAO_DESC=Devolucao)
			- Pós:
			-- pedido não pode ser devolvido;
```
			
### 9-APROVA_ATD_PRODUTO_NAO_FATURADO:
```sh
    Passos: - Pré-requisito:
            -- exec. 030_pedidoComTipoVendaAVista_empenhoDepositoNormal.js
            -- recebe o pedido (PDV);
            -- exec. TrocaProdutoEmpenhoDeposito.java; (Informar NRO_PEDIDO, NRO_MOTIVO_TROCA_DEVOL=412, NRO_STATUS_PRE_RECIBO_APROV=2)
            - Pós:
            -- Tentar emitir o recibo de troca, se conseguir então a regra foi validada; (PDV)
```			