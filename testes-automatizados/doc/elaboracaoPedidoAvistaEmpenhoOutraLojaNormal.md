# Caso de test 'Elaboração de um Pedido A Vista, com Empenho Outra Loja e Entrega Normal'.
Produtos usados no pedido:

  - (139756-4-0) PAN 6P CLASS SL C/VD 70L BR COLORMAQ
  - (139754-4-0) ARM 3P CLASS SL AP3P S/VD BR COLORMAQ
  - (139763-4-0) ARM 1P BASC CLASS SL 70L BR COLORMAQ

Serviços existentes no pedido:

  - (104141) Frete

Empenhos realizados no pedido:

  - Outra Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista" com empenho "Outra Loja - Normal". Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquia e seleciona um produto (PAN 6P CLASS SL C/VD 70L BR COLORMAQ);
6. Empenha o produto (OUTRA LOJA - NORMAL);
7. Valida os serviços do pedido;
8. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
9. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
```
### Código fonte do teste
[elaboracaoPedidoAvistaEmpenhoOutraLojaNormal.js](Testes/test/elaboracaoPedidoAvistaEmpenhoOutraLojaNormal.js)