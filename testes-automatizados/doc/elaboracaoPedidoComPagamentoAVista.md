# Caso de test 'Elaboração de um pedido com tipo de venda A Vista  e empenho Loja - Normal'.
Produtos usados no pedido:

  - (219254-14-0) CEL MOTO G XT1068 2CHIP 8GB PTO

Serviços existentes no pedido:

  - "Não existem validações de serviços nesse caso de teste"

Garantias existentes no pedido:

  - "Não existem validações de garantias nesse caso de teste"

Empenhos realizados no pedido:

  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona um cliente padrão, criado por um teste automatizado;
3. Abre a tela de elaboração do pedido;
4. Informa o tipo de venda "A Vista";
5. Pesquia e seleciona um produto (CEL MOTO G XT1068 2CHIP 8GB PTO);
6. Empenha o produto (LOJA - NORMAL);
7. Remove todos os serviços existentes no pedido;
8. Insere a forma de pagamento (V - DINHEIRO);
9. Salva e efetua o fechamento total do pedido;
```
### Código fonte do teste
[elaboracaoPedidoComPagamentoAVista.js](Testes/test/novos_testes/elaboracaoPedidoComPagamentoAVista.js)
