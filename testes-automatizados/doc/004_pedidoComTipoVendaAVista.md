# Caso de test 004 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (139550-0-0) MODEM USB ZTE MF190 POS PAGO
  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO

Serviços existentes no pedido:

  - (104141) Frete

Garantias existentes no pedido:

  - (143191) Banda Larga Modem
  - (149535) Suporte TV

Empenhos realizados no pedido:

  - Loja - Normal
  - Loja - Retirada com Retenção
  - Depósito - Agendada

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (MODEM USB ZTE MF190 POS PAGO), que contém as seguintes garantias (BANDA LARGA MODEM);
6. Empenha o produto (LOJA - NORMAL);
7. Valida as garantias de todos os produtos inseridos no pedido;
8. Valida os serviços do pedido;
9. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Abre a tela de consulta do pedido;
12. Pesquisa pelo pedido feito acima;
13. Reabre o pedido;
14. Cancela o empenho do produto (MODEM USB ZTE MF190 POS PAGO);
15. Reempenha o produto (MODEM USB ZTE MF190 POS PAGO) (LOJA - RETIRADA LOJA COM RETENÇÃO);
16. Adiciona um segundo produto (SUP MULTI TV PLUG 14A84 PTO), com as seguinte garantias (SUPORTE TV, INSTALAÇÃO DE TV);
17. Empenha o produto (LOJA - RETIRADA LOJA COM RETENÇÃO);
18. Valida as garantias de todos os produtos existentes no pedido;
19. Valida os serviços do pedido;
20. Remove a linha de forma de pagamento da aba planilha;
21. Adiciona a forma de pagamento (V - DINHEIRO), contendo o valor do pedido;
22. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[004_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/004_pedidoComTipoVendaAVista.js)
