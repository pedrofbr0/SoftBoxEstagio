# Caso de test 'Elaboração de um Pedido Cliente Consumidor A Vista, com Empenho Loja e Entrega Normal'.
Produtos usados no pedido:

  - (219757-14-B) PRANCHA MALLORY ARGAN LISS BIV

Serviços existentes no pedido:

  - "Não existem serviços no pedido

Empenhos realizados no pedido:

  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido tipo "Cliente Consumidor", tipo de venda "A Vista", e com empenho "Outra Loja - Normal". Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona a opção "Cliente Consumidor";
3. Informa o tipo de venda "A VISTA";
4. Pesquia e seleciona um produto (PRANCHA MALLORY ARGAN LISS BIV);
5. Empenha o produto (LOJA - NORMAL);
6. Valida os serviços do pedido;
7. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
8. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
```
### Código fonte do teste
[elaboracaoPedidoClienteConsumidorEmpenhoLojaNormal.js](Testes/test/elaboracaoPedidoClienteConsumidorEmpenhoLojaNormal.js)