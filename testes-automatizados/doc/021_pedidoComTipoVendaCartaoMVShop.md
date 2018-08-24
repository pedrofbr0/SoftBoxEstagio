# Caso de test 021 Pedido com tipo de venda 'Cartão MVShop'.
Produtos usados no pedido:

  - (201791-4-0) KIT COZ ELIANA BR MULTIVISAO
  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO
  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT

Serviços existentes no pedido:

  - (139524) Solução Certa Informática
  - (209514) Serviço Antivirus Mobile Norton
  - (163594) Seg Caminhão da Sorte
  - (104141) Frete
  - (187814) Ajuste de Preço Sugerido

Garantias existentes no pedido:

  - (144765) Garantia Maior Móveis - 1 ano
  - (142383) Montagem Kit Cozinha / Cozinhas
  - (149535) Suporte TV
  - (140358) Instação de TV
  - (196014) Roubo e Furto Tablet
  - (139524) Solução Certa Informática
  - (144776) Garantia Maior Tablet - 2 anos
  - (209514) Serviço Antivirus Mobile Norton

Empenhos realizados no pedido:

  - Depósito - Retirada Outra Loja com Empenho Depósito
  - Depósito - Agendada Cliente
  - Outra Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Cartão Cartão MVShop", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "Cartão MVShop";
5. Pesquisa e seleciona um produto (KIT COZ ELIANA BR MULTIVISAO), que contem as seguintes garantias (MONTAGEM KIT COZINHA / COZINHAS, GARANTIA MAIOR MOVEIS - 1 ANO);
6. Define a tabela do produto (96 - TABELA 96);
7. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
8. Altera o plano de pagamento para "4x";
9. Valida as garantias do produto;
10. Valida os serviços do pedido;
11. Insere uma forma de pagamento (F - MVSHOP REABERTURA);
12. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
13. Abre a tela de consulta do pedido;
14. Pesquisa pelo pedido feito acima;
15. Reabre o pedido;
16. Adiciona um segundo produto (SUP MULTI TV PLUG 14A84 PTO), contendo as seguinte garantias (SUPORTE TV, INSTALAÇÃO DE TV);
17. Empenha o mesmo (OUTRA LOJA - NORMAL);
18. Adiciona um terceiro produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), contendo as seguinte garantias (ROUBO E FURTO TABLET, SOLUÇÃO CERTA INFORMÁTICA, GARANTIA MAIOR TABLET, SERVIÇO ANTIVIRUS MOBILE NORTON);
19. Empenha o mesmo (DEPÓSITO - AGENDADA CLIENTE);
20. Altera o plano de pagamento para "10x";
21. Valida as garantias de todos os produtos inseridos no pedido;
22. Valida os serviços do pedido;
23. Realiza liberação de empenho em Outra Loja;
24. Abre a aba planilha e remove a linha de forma de pagamento;
25. Insere a forma de pagamento do financiamento (F - MVSHOP REABERTURA), correspondente ao valor total do pedido;
26. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[021_pedidoComTipoVendaCartaoMVShop.js](Testes/test/novos_testes/021_pedidoComTipoVendaCartaoMVShop.js)
