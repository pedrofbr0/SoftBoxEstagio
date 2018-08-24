# Caso de test 022 Pedido com tipo de venda 'Cartão MVShop'.
Produtos usados no pedido:

  - (139756-4-0) PAN 6P CLASS SL C/VD 70L BR COLORMAQ
  - (139754-4-0) ARM 3P CLASS SL AP3P S/VD BR COLORMAQ
  - (139763-4-0) ARM 1P BASC CLASS SL 70L BR COLORMAQ
  - (219757-14-B) PRANCHA MALLORY ARGAN LISS BIV

Serviços existentes no pedido:

  - (209514) Serviço Antivirus Mobile Norton
  - (163594) Seg Caminhão da Sorte
  - (187814) Ajuste Preço Sugerido
  - (104141) Frete

Garantias existentes no pedido:

  - (142384) Montagem PAN,GAB,BAL,ARM SUP,CANT,PRATEL
  - (144765) Garantia Maior Móveis - 1 ano
  - (192434) Montagem de Móveis
  - (144779) Troca Garantida - 1 ano

Empenhos realizados no pedido:

  - Depósito - Agendada
  - Loja - Retirada com Retenção
  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Cartão Cartão MVShop", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "Cartão MVShop";
5. Pesquisa e seleciona um produto (PAN 6P CLASS SL C/VD 70L BR COLORMAQ), que perterce a um kit e contem as seguintes garantias ("MONTAGEM PAN,GAB,BAL,ARM SUP,CANT,PRATEL", GARANTIA MAIOR MOVEIS - 1 ANO, MONTAGEM DE MOVEIS);
7. Empenha o produto (DEPÓSITO - AGENDADA);
8. Altera o plano de pagamento para "10x";
9. Valida as garantias do produto;
10. Valida os serviços do pedido;
11. Insere uma forma de pagamento (F - MVSHOP REABERTURA);
12. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
13. Abre a tela de consulta do pedido;
14. Pesquisa pelo pedido feito acima;
15. Reabre o pedido;
16. Adiciona um segundo produto (PRANCHA MALLORY ARGAN LISS BIV), contendo as seguinte garantias (TROCA GARANTIDA - 1 ANO);
17. Empenha o mesmo (LOJA - RETIRADA LOJA COM RETENÇÃO);
18. Valida as garantias de todos os produtos adicionados no pedido;
19. Cancela o empenho do segundo produto (LOJA - RETIRADA LOJA COM RETENÇÃO);
20. Reempenha o segundo produto (LOJA - RETIRADA LOJA COM RETENÇÃO) - (LOJA - NORMAL);
21. Insere um valor de entrada no pedido;
22. Valida os serviços do pedido;
23. Abre a aba planilha e remove a linha de forma de pagamento;
24. Insere 1ª forma de pagamento da entrada (E - CARTÃO VISA ELECTRON), correspondente a 50% do valor da entrada;
25. Insere 2ª forma de pagamento da entrada (E - DINHEIRO), correspondente a 50% do valor da entrada;
26. Insere 3ª forma de pgamento do financiamento (F - MVSHOP REABERTURA), correspondente ao valor restante do pedido;
27. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[022_pedidoComTipoVendaCartaoMVShop.js](Testes/test/novos_testes/022_pedidoComTipoVendaCartaoMVShop.js)
