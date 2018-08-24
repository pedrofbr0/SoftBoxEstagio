# Caso de test 020 Pedido com tipo de venda 'Carne Losango'.
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
  - (103532) CDC Assistência 500
  - (103537) CDC Assistência 2000 II

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

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Carne Losango", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "Carne Losango";
5. Pesquisa e seleciona um produto (KIT COZ ELIANA BR MULTIVISAO), que contem as seguintes garantias (MONTAGEM KIT COZINHA / COZINHAS, GARANTIA MAIOR MOVEIS - 1 ANO);
6. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
7. Altera o plano de pagamento para "4x";
8. Valida as garantias do produto;
9. Valida os serviços do pedido;
10. Insere uma forma de pagamento (F - CARNE LOSANGO);
11. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
12. Abre a tela de consulta do pedido;
13. Pesquisa pelo pedido feito acima;
14. Reabre o pedido;
15. Adiciona um segundo produto (SUP MULTI TV PLUG 14A84 PTO), contendo as seguinte garantias (SUPORTE TV, INSTALAÇÃO DE TV);
16. Empenha o mesmo (OUTRA LOJA - NORMAL);
17. Adiciona um terceiro produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), contendo as seguinte garantias (ROUBO E FURTO TABLET, SOLUÇÃO CERTA INFORMÁTICA, GARANTIA MAIOR TABLET, SERVIÇO ANTIVIRUS MOBILE NORTON);
18. Empenha o mesmo (DEPÓSITO - AGENDADA CLIENTE);
19. Altera o plano de pagamento para "21x";
20. Valida as garantias de todos os produtos inseridos no pedido;
21. Valida os serviços do pedido;
22. Realiza liberação de empenho em Outra Loja;
23. Abre a aba planilha e remove a linha de forma de pagamento;
24. Insere a forma de pagamento do financiamento (F - CARNE LOSANGO), será gerada 21 parcelas;
25. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[020_pedidoComTipoVendaCarneLosango.js](Testes/test/novos_testes/020_pedidoComTipoVendaCarneLosango.js)
