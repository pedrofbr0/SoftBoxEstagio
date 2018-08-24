# Caso de test 001 Pedido com tipo de venda 'A Vista' com Garantia Avulsa.
Produtos usados no pedido:

  - (215275-0-B) TV PANASONIC 32 TC-32A400B LED HD
  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO

Serviços existentes no pedido:

  - (104141) Frete

Garantias existentes no pedido:

  - (149535) Suporte TV

Garantias avulsa existentes no pedido:

  - (144657) GARANTIA MAIOR TV LCD - 1 ANO

Empenhos realizados no pedido:

  - Depósito - Agendada

> O caso de teste descrito abaixo realiza o 1º pedido com tipo de venda "A Vista" de um produto com garantia estendida, e um 2º pedido com a garantia avulsa do 1º pedido, variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A Vista";
5. Pesquisa e seleciona um produto (TV PANASONIC 32 TC-32A400B LED HD), que contém as seguinte garantias (Intalação de TV, Garantia Maior TV LCD);
6. Empenha o produto (DEPÓSITO - AGENDADA);
7. Remove todas as garantias do produto;
8. Valida os serviços do pedido;
9. Insere uma forma de pagamento (A Vista);
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Elabora um novo pedido com um cliente padrão do sistema;
12. Abre a tela de elaboração de pedido;
13. Informa o tipo de venda "A Vista";
14. Pesquisa e seleciona um produto (SUP MULTI TV PLUG 14A84 PTO), que contém as seguinte garantias (Intalação de TV, Suporte TV);
15. Empenha o produto (DEPÓSITO - AGENDADA);
16. Valida as garantias de todos os produtos do pedido;
17. Valida os serviços do pedido;
18. Adiciona a garantia avulsa (GARANTIA MAIOR TV LCD - 1 ANO), do produto (TV PANASONIC 32 TC-32A400B LED HD), do pedido anterior;
19. Insere uma forma de pagamento (A Vista);
20. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido; 
21. Abre a tela de consulta do pedido;
22. Pesquisa pelo pedido feito acima;
23. Reabre o pedido;
24. Cancela o empenho produto (SUP MULTI TV PLUG 14A84 PTO);
25. Reempenha o segundo produto (DEPÓSITO - AGENDADA);
26. Valida as garantias de todos os produtos do pedido;
27. Valida os serviços do pedido;
28. Insere uma forma de pagamento (A Vista);
29. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[001_pedidoComTipoVendaAVista.js](/Testes/test/novos_testes/001_pedidoComTipoVendaAVista.js)