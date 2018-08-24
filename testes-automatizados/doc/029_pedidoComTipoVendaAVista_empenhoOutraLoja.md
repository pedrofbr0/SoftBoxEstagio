# Caso de test 029 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT
  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO
  - (201791-4-0) KIT COZ ELIANA BR MULTIVISAO

Serviços existentes no pedido:

  - (104141) Frete
  - (139524) Solução Certa Informática

Garantias existentes no pedido:

  - (143191) Instalação de TV
  - (149535) Suporte TV

Empenhos realizados no pedido:

  - Depósito - Agendada Cliente
  - Depósito - Retirada Outra Loja Com Empenho em Depósito
  - Depósito - Agendada Cliente

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (SUP MULTI TV PLUG 14A84 PTO), que contém as seguintes garantias (SUPORTE TV e INSTALAÇÃO_DE_TV);
6. Empenha o produto (OUTRA LOJA - NORMAL);
5. Pesquisa e seleciona um produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), que contém as seguintes garantias (SOLUCAO CERTA INFORMATICA, GARANTIA MAIOR TABLET - 2 ANOS, SERVICO ANTIVIRUS MOBILE NORTON e ROUBO E FURTOS TABLET);
7. Empenha o produto (DEPOSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPOSITO);
8. Pesquisa e seleciona um produto (KIT COZ ELIANA BR MULTIVISAO), que contém as seguintes garantias (GARANTIA MAIOR MOVEIS - 1 ANO e MONTAGEM KIT COZINHA / COZINHAS);
9. Empenha o produto (DEPOSITO - AGENDADA CLIENTE);
10. Valida as garantias de todos os produtos inseridos no pedido;
11. Valida os serviços do pedido;
12. Realiza liberação (LIBERACAO DE EMPENHO OUTRA LOJA) para o produto (SUP MULTI TV PLUG 14A84 PTO);
13. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
14. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
15. Abre a tela de consulta do pedido;
16. Pesquisa pelo pedido feito acima;
17. Reabre o pedido;
18. Cancela o empenho dos produtos (SUP MULTI TV PLUG 14A84 PTO) , (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT) e (KIT COZ ELIANA BR MULTIVISAO);
19. Reempenha o produto (SUP MULTI TV PLUG 14A84 PTO) (LOJA - NORMAL);
20. Reempenha o produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT) (LOJA - NORMAL);
21. Reempenha o produto (KIT COZ ELIANA BR MULTIVISAO) (LOJA - NORMAL);
22. Cancela o empenho do produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT);
23. Remove o produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT);
24. Valida as garantias de todos os produtos existentes no pedido;
25. Valida os serviços do pedido;
26. Remove a linha de forma de pagamento da aba planilha;
27. Adiciona a forma de pagamento (V - DINHEIRO) e (V-Cartao Visa Electron);
28. Salva o pedido, efetua o fechamento total do mesmo;
```
### Código fonte do teste
[004_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/004_pedidoComTipoVendaAVista.js)
