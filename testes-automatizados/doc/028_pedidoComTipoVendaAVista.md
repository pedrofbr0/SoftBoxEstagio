# Caso de test 028 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO
  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT
  - (201791-4-0) KIT COZ ELIANA BR MULTIVISAO

Serviços existentes no pedido:

  - (139524) Solução Certa Informática
  - (104141) Frete

Garantias existentes no pedido:

  - (149535) Suporte TV
  - (140358) Instalação de TV
  - (139524) Solução Certa Informática
  - (144776) Garantia Maior Tablet - 2 anos
  - (209514) Serviço Antivirus Mobile Norton
  - (196014) Roubo E Furtos Tablet
  - (144765) Garantia Maior Móveis - 1 ano
  - (142383) Montagem Kit Cozinha / Cozinhas

Empenhos realizados no pedido:

  - Outra Loja - Normal
  - Depósito - Retirada Outra Loja com Empenho Depósito
  - Depósito - Agendada Cliente
  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A Vista";
5. Pesquisa e seleciona um produto (SUP MULTI TV PLUG 14A84 PTO), que contém as seguintes garantias (Suporte TV, Instalação de TV);
6. Empenha o produto (OUTRA LOJA - NORMAL);
7. Pesquisa e seleciona um 2º produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), que contém as seguintes garantias (Solução Certa Informática, Garantia Maior Tablet - 2 anos, Serviço Antivirus Mobile Norton, Roubo e Furtos Tablet);
8. Empenha o mesmo (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
9. Pesquisa e seleciona um 3º produto (KIT COZ ELIANA BR MULTIVISAO), que contém as seguintes garantias (Garantia Maior Móveis, Montagem Kit Cozinha / Cozinhas);
10. Empenha o mesmo (DEPÓSITO - AGENDADA CLIENTE);
11. Valida as garantias de todos os produtos do pedido;
12. Valida os serviços do pedido;
13. Realiza liberação de empenho Outra Loja;
14. Insere a forma de pagamento (Dinheiro), contendo o valor total do pedido;
15. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
16. Abre a tela de consulta do pedido;
17. Pesquisa pelo pedido feito acima;
18. Reabre o pedido;
19. Cancela o empenho do produto (SUP MULTI TV PLUG 14A84 PTO);
20. Cancela o empenho do produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT);
21. Cancela o empenho do produto (KIT COZ ELIANA BR MULTIVISAO);
22. Reempenha todos os produtos com opção de empenho (LOJA), e entrega (NORMAL);
23. Cancela o empenho do produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT);
24. Remove o produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT) do pedido;
25. Valida as garantias de todos os produtos do pedido;
26. Valida os serviços do pedido;
27. Remove a forma de pagamento que contém no pedido;
28. Adiciona a 1ª forma de pagamento (Cartão Visa Electron), contendo parte do valor do pedido;
29. Adiciona a 2ª forma de pagamento (Dinheiro), contendo o valor restante do pedido;
30. Salva o pedido, efetua o fechamento total do pedido.
```
### Código fonte do teste
[028_pedidoComTipoVendaAVista.js](/Testes/test/novos_testes/028_pedidoComTipoVendaAVista.js)
