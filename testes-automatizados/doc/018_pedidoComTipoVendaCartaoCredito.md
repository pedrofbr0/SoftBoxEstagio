# Caso de test 018 Pedido com tipo de venda 'Cartão de Crédito'.
Produtos usados no pedido:

  - (214954-14-B) TABLET CCE TR92 8GB WF9 PTO
  - (139550-0-0) MODEM USB ZTE MF190 POS PAGO
  - (219757-14-B) PRANCHA MALLORY ARGAN LISS BIV

Serviços existentes no pedido:

  - (209514) Serviço Antivirus Mobile Norton
  - (163594) Seg Caminhão da Sorte
  - (104141) Frete

Garantias existentes no pedido:

  - (196014) Roubo e Furtos Tablet
  - (139524) Solução Certa Informática
  - (144776) Garantia Maior Tablet - 2 anos
  - (209514) Serviço Antivirus Mobile Norton
  - (143191) Modem Banda Larga
  - (144779) Troca Garantida - 1 ano

Empenhos realizados no pedido:

  - Depósito - Retirada Outra Loja com Empenho Depósito
  - Depósito - Agendada Cliente
  - Loja - Normal
  - Outra Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Cartão de Crédito", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "Cartão de Crédito";
5. Pesquisa e seleciona um produto (TABLET CCE TR92 8GB WF9 PTO), que contém as seguintes garantias (ROUBO E FURTOS TABLET, SOLUCAO CERTA INFORMATICA, GARANTIA MAIOR TABLET, SERVICO ANTIVIRUS MOBILE NORTON);
6. Define a tabela de preço do produto (65 - TABELA 65);
7. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
8. Pesquisa e seleciona um segundo produto (MODEM USB ZTE MF190 POS PAGO), que contém as seguintes garantias (MODEM BANDA LARGA);
9. Define a tabela de preço do produto (65 - TABELA 65);
10. Empenha o produto (LOJA - NORMAL);
11. Altera o plano de pagamento para "12x";
12. Valida as garantias de todos os produtos inseridos no pedido;
13. Valida os serviços do pedido;
14. Altera o valor da prestação, inserindo um valor menos que o calculado pelo sistema (SERÁ GERADO UMA LIBERAÇÃO);
15. Validar a liberação (LIBERAÇÃO DE PRECO) e liberar a mesma com um usuário com permissão para tal.
16. Insere uma forma de pagamento (F - CARTÃO MASTERCARD);
17. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
18. Abre a tela de consulta do pedido;
19. Pesquisa pelo pedido feito acima;
20. Reabre o pedido;
21. Pesquisa e adiciona um terceiro produto (PRANCHA MALLORY ARGAN LISS BIV), contendo as seguinte garantias (TROCA GARANTIDA - 1 ANO);
22. Define a tabela de preço do produto (65 - TABELA 65);
23. Empenha o mesmo (DEPÓSITO - AGENDADA CLIENTE);
24. Cancela o empenho do produto (MODEM USB ZTE MF190 POS PAGO);
25. Reempenha o produto (MODEM USB ZTE MF190 POS PAGO) (OUTRA LOJA - NORMAL);
26. Insere um valor de entrada e define o plano de pagamento em "3x";
27. Valida as garantias de todos os produtos no pedido;
28. Valida os serviços do pedido;
29. Abre a aba planilha e remove a linha de forma de pagamento;
30. Insere 1ª forma de pagamento da entrada (E - DINHEIRO);
31. Insere 2ª forma de pgamento do financiamento (F - CARTÃO VISA);
32. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[018_pedidoComTipoVendaCartaoCredito.js](Testes/test/novos_testes/018_pedidoComTipoVendaCartaoCredito.js)