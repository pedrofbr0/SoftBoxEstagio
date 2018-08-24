# Caso de test 'Elaboração de Pedido com Tipo de Venda (Cartão de Crédito)'.
Produtos usados no pedido:

  - (214954-14-B) TABLET CCE TR92 8GB WF9 PTO

Serviços existentes no pedido:

  - (104141) Frete

Garantias existentes no pedido:

  - "Não existem validações de garantias no teste"

Empenhos realizados no pedido:

  - Loja - Normal

> O caso de teste descrito abaixo realiza a elaboração de um pedido com tipo de venda "Cartão de Crédito", com empenho "Loja - Normal" e duas formas de pagamento. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona um cliente padrão gerado por um teste automatizado;
3. Abre a tela de Elaboração de pedido;
4. Informa o tipo de venda "Cartão de Crédito";
5. Pesquia e seleciona um produto (TABLET CCE TR92 8GB WF9 PTO);
6. Efetua o empenho do mesmo (LOJA - NORMAL);
7. Insere um valor de entrada no pedido;
8. Valida os serviços do pedido;
9. Insere a 1ª forma de pagamento (E - CARTÃO VISA ELECTRON), contendo o valor da entrada do pedido;
10. Insere a 2ª forma de pagamento (F - BANESCARD CRÉDITO), contendo o valor restante do pedido;
11. Salva e efetua o fechamento total do pedido;
```
### Código fonte do teste
[elaboracaoPedidoComPagamentoNoCartaoDeCredito.js](Testes/test/novos_testes/elaboracaoPedidoComPagamentoNoCartaoDeCredito.js)