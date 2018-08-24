# Caso de test 005 Pedido com tipo de venda 'A Vista'.
# Caso de test 005 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (208974-371-0) RACK 091L MOSCOU 29598 CRV TQ EDN

Serviços existentes no pedido:

  - (163594) Seg Caminhão da Sorte
  - (209514) Serviço Antivirus Mobile Norton

Garantias existentes no pedido:

  - (192434) Montagem de Móveis
  - (144765) Garantia de Móveis - 1 ano

Empenhos realizados no pedido:

  - Depósito - Retirada Outra Loja com Empenho Depósito

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (RACK 091L MOSCOU 29598 CRV TQ EDN), que contém as seguintes garantias (MONTAGEM DE MÓVEIS, GARANTIA MAIOR);
6. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
7. Valida as garantias de todos os produtos inseridos no pedido;
8. Valida os serviços do pedido;
9. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Abre a tela de consulta do pedido;
12. Pesquisa pelo pedido feito acima;
13. Reabre o pedido;
14. Valida os serviços do pedido;
15. Salva o pedido, efetua o fechamento total.
```
### Código fonte do teste
[005_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/005_pedidoComTipoVendaAVista.js)
