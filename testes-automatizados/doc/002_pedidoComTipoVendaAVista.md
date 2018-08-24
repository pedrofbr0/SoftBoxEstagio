# Caso de test 002 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (187374-0-B) TV LG 32 LN540B LED DTV
  - (16376-0-0) KIT FEM BE SECRET - MORANGO

Serviços existentes no pedido:

  - (104141) Frete
  - (163594) Seg Caminhão da Sorte

Garantias existentes no pedido:

  - (140358) Instalação de TV
  - (144658) Garantia Maior TV LCD - 2 ano
  - (144657) Garantia Maior TV LCD - 1 ano
  - (136711) Perfumaria

Empenhos realizados no pedido:

  - Depósito - Agendada
  - Depósito - Agendada Cliente

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (TV LG 32 LN540B LED DTV), que contém as seguintes garantias (INSTALAÇÃO DE TV, GARANTIA MAIOR);
6. Empenha o produto (DEPÓSITO - AGENDADA CLIENTE);
7. Pesquisa e seleciona um segundo produto (KIT FEM BE SECRET - MORANGO), que contém as seguintes garantias (PERFUMARIA);
8. Empenha o produto (DEPÓSITO - AGENDADA CLIENTE);
9. Valida as garantias de todos os produtos inseridos no pedido;
10. Valida os serviços do pedido;
11. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
12. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
13. Abre a tela de consulta do pedido;
14. Pesquisa pelo pedido feito acima;
15. Reabre o pedido;
16. Cancela o empenho do produto (TV LG 32 LN540B LED DTV);
17. Reempenha o produto (TV LG 32 LN540B LED DTV) (DEPÓSITO - AGENDADA);
18. Valida as garantias de todos os produtos no pedido;
19. Valida os serviços do pedido;
20. Abre a aba planilha e remove a linha da planilha;
21. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
22. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[002_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/002_pedidoComTipoVendaAVista.js)
