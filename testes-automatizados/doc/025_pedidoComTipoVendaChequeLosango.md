# Caso de test 025 Pedido com tipo de venda 'Cheque Losango'.
Produtos usados no pedido:

  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT
  - (139756-4-0) PAN 6P CLASS SL C/VD 70L BR COLORMAQ
  
Serviços existentes no pedido:

  - Seg Caminhão da Sorte
  - Frete
  - CDC Assistência 500
  - CDC Assistência 1000
  - CDC Assistência 2000
  - Serviço Antivirus Mobile Norton

Garantias existentes no pedido:

  - Roubo e Furto Tablets
  - Solução Certa Informática
  - Garantia Maior Tablet - 2 anos
  - Serviço Antivirus Mobile Norton
  - Garantia Maior Móveis - 1 ano
  - Montagem PAN,GAB,BAL,ARM SUP,CANT,PRATEL
  - Montagem de Móveis

Empenhos realizados no pedido:

  - Depósito - Agendada Cliente
  - Depósito - Agendada
  - Outra Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Cheque Losango Scred", variando as opções de empenho contidas no pedido, garantias, serviços e formas de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Pesquisa e seleciona um produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), que contém as garantias (ROUBO E FURTO TABLET, SOLUÇÃO CERTA, GARANTIA MAIOR TABLET - 2 ANOS, SERVIÇO ANTIVIRUS MOBILE NORTON);
5. Empenha o produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT) (DEPÓSITO - AGENDADA CLIENTE);
6. Altera o plano de pagamento para "2x";
7. Valida as garantias do produto;
8. Valida os serviços;
9. Insere uma forma de pagamento (CHEQUE LOSANGO);
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Abre a tela de consulta do pedido;
12. Pesquisa pelo pedido feito acima;
13. Reabre o pedido;
14. Insere um segundo produto  (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), que contém as garantias (ROUBO E FURTO TABLET, SOLUÇÃO CERTA, GARANTIA MAIOR TABLET - 2 ANOS, SERVIÇO ANTIVIRUS MOBILE NORTON);
15. Empenha o mesmo (OUTRA LOJA - NORMAL);
16. Altera o plano de pagamento para "12X";
17. Valida os serviços do produto;
18. Realiza liberação de empenho Outra Loja;
19. Abre a aba planilha e remove a linha de forma de pagamento;
20. Insere uma forma de pagamento e valida se o valor está Ok;
21. Salva o pedido, efetua o fechamento total do mesmo e fecha a tela de elaboração do pedido;
22. Pesquisa mais uma vez o pedido;
22. Reabre o pedido;
24. Insere um terceiro produto (PAN 6P CLASS SL C/VD 70L BR COLORMAQ), que faz parte de um kit, com as seguintes garantias (GARANTIA MAIOR MOVEIS - 1 ANO, MONTAGEM PAN,GAB,BAL,ARM SUP,CANT,PRATEL, MONTAGEM DE MOVEIS);
25. Empenha o produto (DEPÓSITO - AGENDADA);
26. Altera o plano de pedido para "6x", e insere um valor de entrada;
27. Valida as garantias do produto;
28. Valida os serviços do produto;
29. Abre a aba planilha e remove a linha de forma de pagamento;
30. Insere uma forma de pagamento e valida se o valor está Ok;
31. Salva o pedido e efetua o fechamento total do pedido.
```
### Código fonte do teste
[023_pedidoComTipoVendaChequeLosango.js](/Testes/test/novos_testes/025_pedidoComTipoVendaChequeLosango.js)