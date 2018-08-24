# Caso de test 027 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (139756-4-0) PAN 6P CLASS SL C/VD 70L BR COLORMAQ
  - (139754-4-0) ARM 3P CLASS SL AP3P S/VD BR COLORMAQ
  - (139763-4-0) ARM 1P BASC CLASS SL 70L BR COLORMAQ
  - (219757-14-B) PRANCHA MALLORY ARGAN LISS BIV
  - (139550-0-0) MODEM USB ZTE MF190 POS PAGO

Serviços existentes no pedido:

  - (209514) Serviço Antivirus Mobile Norton
  - (163594) Seg Caminhão da Sorte
  - (104141) Frete
  - (103514) Claro Cartão 18
  - (103519) Claro Cartão 50

Garantias existentes no pedido:

  - (144765) Garantia Maior Móveis - 1 ano
  - (142384) Montagem PAN,GAB,BAL,ARM SUP,CANT,PRATEL
  - (192434) Montagem de Móveis
  - (144779) Troca Garantida - 1 ano
  - (143191) Banda Larga Modem

Empenhos realizados no pedido:

  - Depósito - Agendada
  - Loja - Retirada Loja com Retenção
  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A Vista";
5. Pesquisa e seleciona um produto (PAN 6P CLASS SL C/VD 70L BR COLORMAQ), que contém as seguinte garantias (Garantia Maior Móveis - 1 ano, Montagem PAN,GAB,BAL,ARM SUP,CANT,PRATEL);
6. Empenha o produto (DEPÓSITO - AGENDADA);
7. Pesquisa e seleciona um 2º produto (PRANCHA MALLORY ARGAN LISS BIV), que contém as seguinte garantias (Troca Garantida - 1 ano);
8. Empenha o mesmo (LOJA - RETIRADA COM LOJA RETENÇÃO);
9. Valida as garantias de todos os produtos do pedido;
10. Valida os serviços do pedido;
11. Insere a 1º forma de pagamento (Cartão Redeshop), contendo parte do valor do pedido;
12. Insere a 2º forma de pagamento (Cartão Visa Electron), contendo parte do valor do pedido;
13. Insere a 3º forma de pagamento (Cheque A Vista), contendo parte do valor do pedido;
14. Insere a 4º forma de pagamento (Dinheiro), contendo o restante do valor do pedido;
15. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
16. Abre a tela de consulta do pedido;
17. Pesquisa pelo pedido feito acima;
18. Reabre o pedido;
19. Pesquisa e seleciona um 3º produto (MODEM USB ZTE MF190 POS PAGO), que contém as seguintes garantias (Banda Larga Modem);
20. Emepnha o produto (LOJA - NORMAL);
21. Cancela o empenho do produto (PRANCHA MALLORY ARGAN LISS BIV);
22. Reempenha o produto (PRANCHA MALLORY ARGAN LISS BIV) (DEPÓSITO - AGENDADA);
23. Valida as garantias de todos os produtos do pedido;
24. Valida os serviços do pedido;
25. Remove todas as formas de pagamento;
26. Adiciona uma forma de pagamento (Dinheiro), contendo o valor total do pedido;
27. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
28. Abre a tela de consulta do pedido;
29. Pesquisa pelo pedido feito acima;
30. Reabre o pedido;
31. Efetua validações dos serviços do pedido;
32. Remove a forma de pagamento;
33. Insere a 1º forma de pagamento (Cartão Visa Electron), contendo parte do valor do pedido;
34. Insere a 2º forma de pagamento (Dinehiro), contendo o restante do valor total do pedido;
35. Salva o pedido, efetua o fechamento total pedido.
```
### Código fonte do teste
[027_pedidoComTipoVendaAVista.js](/Testes/test/novos_testes/027_pedidoComTipoVendaAVista.js)