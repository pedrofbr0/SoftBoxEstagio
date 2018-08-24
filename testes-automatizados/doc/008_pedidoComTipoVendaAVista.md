# Caso de test 008 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (188774-14-0) SUP MULTI TV PLUG LED 14A84 PTO
  - (16376-0-0) KIT FEM BE SECRET - MORANGO

Serviços existentes no pedido:

  - (103519) Cartão Claro 50

Garantias existentes no pedido: 

  - (140358) Instalação de TV
  - (149535) Suporte TV
  - (136711) Perfumaria

Empenhos realizados no pedido:

  - Outra Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (SUP MULTI TV PLUG LED 14A84 PTO), que contém as seguintes garantias (INSTALAÇÃO DE TV, SUPORTE TV);
6. Empenha o produto (OUTRA LOJA - NORMAL);
7. Valida as garantias de todos os produtos inseridos no pedido;
8. Valida os serviços do pedido;
9. Realiza liberação de empenho em Outra Loja;
10 Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
11. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
12. Abre a tela de consulta do pedido;
13. Pesquisa pelo pedido feito acima;
16. Reabre o pedido;
17. Adiciona um segundo produto (KIT FEM BE SECRET - MORANGO), com as seguinte garantias (PERFUMARIA);
18. Empenha o produto (OUTRA LOJA - NORMAL);
19. Valida as garantias de todos os produtos existentes no pedido;
20. Valida os serviços do pedido;
21. Realiza liberação de empenho em Outra Loja;
22. Remove a linha de forma de pagamento da aba planilha;
23. Adiciona a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
24. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[008_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/008_pedidoComTipoVendaAVista.js)
