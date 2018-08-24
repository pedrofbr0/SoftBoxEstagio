# Caso de test 'Elaboração Pedido A Vista de um Serviço'.
Produtos usados no pedido:

  - "Não existem produtos no pedido"

Serviços existentes no pedido:

  - (103519) Claro Cartão 50

Garantias existentes no pedido:

  - "Não existem garantias no pedido"

Empenhos realizados no pedido:

  - "Não existem empenhos no pedido"

> O caso de teste descrito abaixo realiza um pedido de um serviço com forma de pagamento "A Vista". Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Seleciona um serviço (Claro Cartão 50);
6. Insere a a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
7. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[elaboracaoPedidoAvistaDeUmServico.js](Testes/test/elaboracaoPedidoAvistaDeUmServico.js)