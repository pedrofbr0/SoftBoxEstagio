# Caso de test 026 Pedido com tipo de venda 'Depósito em Conta'.
Produtos usados no pedido:

  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO

Serviços existentes no pedido:

  - Suporte
  - Instalação de TV

Garantias existentes no pedido:

  - Seg Caminhão da Sorte
  - Frete

Empenhos realizados no pedido:

  - Loja - Normal
  - Depósito - Agendada
  - Depósito - Retirada Outra Loja com Empenho Depósito
  - Outra Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Depósito em conta", variando as opções de empenho contidas no pedido, garantias e serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
2. Abre a tela de elaboração de pedido;
3. Pesquia e seleciona um produto (SUP MULTI TV PLUG 14A84 PTO), que contém as garantias (SUPORTE, INSTALAÇÃO DE TV);
4. Empenha o produto (LOJA - NORMAL);
5. Cancela o empenho feito acima;
6. Reempenha o produto (DEPÓSITO - AGENDADA);
7. Valida as garantias do produto;
8. Valida os serviços;
9. Insere uma forma de pagamento (DEPOSITO EM CONTA);
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Abre a tela de consulta do pedido;
12. Pesquisa pelo pedido feito acima;
13. Reabre o pedido;
14. Cancela o empenho do produto;
15. Reempenha o produto (OUTRA LOJA - NORMAL);
16. Valida as garantias do produto;
17. Valida os serviços do produto;
18. Realiza liberação de empenho Outra Loja;
19. Abre a aba planilha e remove a linha de forma de pagamento;
20. Insere uma forma de pagamento e valida se o valor está Ok;
21. Salva o pedido, efetua o fechamento total do mesmo e fecha a tela de elaboração do pedido;
22. Pesquisa mais uma vez o pedido;
23. Reabre o pedido;
24. Cancela o empenho do produto;
25. Reempenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
26. Valida as garantias do produto;
27. Valida os serviços do produto;
28. Abre a aba planilha e remove a linha de forma de pagamento;
29. Insere uma forma de pagamento e valida se o valor está Ok;
30. Salva o pedido e efetua o fechamento total do pedido.
```
### Código fonte do teste
[026_pedidoComTipoVendaDepositoEmConta.js](/Testes/test/novos_testes/026_pedidoComTipoVendaDepositoEmConta.js)