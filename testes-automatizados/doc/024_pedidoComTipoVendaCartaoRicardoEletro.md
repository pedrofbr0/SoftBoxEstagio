# Caso de test 024 Pedido com tipo de venda 'Cartão Ricardo Eletro'.
Produtos usados no pedido:

  - (201791-4-0) KIT COZ ELIANA BR MULTIVISÃO
  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO
  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT
  
Serviços existentes no pedido:

  - Serviço Antivirus Mobile Norton
  - Seg Caminhão da Sorte
  - Solução Certa Informática
  - Frete

Garantias existentes no pedido:

  - Garantia Maior Móveis - 1 ano
  - Montagem Kit Cozinha / Cozinhas
  - Suporte TV
  - Instalação TV
  - Roubo e Furto Tablet
  - Solução Certa Informática
  - Garantia Maior Tablet - 2 anos
  - Serviço Antivirus Mobile Norton

Empenhos realizados no pedido:

  - Depósito - Retirada Outra Loja com Empenho Depósito
  - Depósito - Agendada Cliente
  - Outra Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Cartão Ricardo Eletro", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "Cartão Ricardo Eletro";
5. Pesquisa e seleciona um produto (KIT COZ ELIANA BR MULTIVISÃO), que contém as garantias (GARANTIA MAIOR MOVEIS - 1 ANO, MONTAGEM KIT COZINHA / COZINHAS);
6. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
7. Altera o plano de pagamento para "4x";
8. Valida as garantias do produto;
9. Valida os serviços do pedido;
10. Insere uma forma de pagamento (CARTÃO RICARDO ELETRO);
11. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
12. Abre a tela de consulta do pedido;
13. Pesquisa pelo pedido feito acima;
14. Reabre o pedido;
15. Adiciona um segundo produto (SUP MULTI TV PLUG 14A84 PTO), contendo as seguinte garantias (SUPORTE TV, INSTALAÇÃO DE TV);
16. Empenha o produto (OUTRA LOJA - NORMAL);
17. Adiciona um terceiro produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), contendo as seguintes garantias (ROUBO E FURTO TABLET, GARANTIA MAIOR TABLET - 2 ANOS, SOLUÇÃO CERTA INFORMÁTICA, SERVIÇO ANTIVIRUS MOBILE NORTON);
18. Empenha o produto (DEPOSITO - AGENDADA CLIENTE);
19. Ajusta o plano de pagamento para "10x";
20. Valida as garantias de todos os produtos inseridos
21. Valida os serviços do pedido;
22. Realiza liberação de empenho em Outra Loja;
23. Abre a aba planilha e remove a linha de forma de pagamento;
24. Insere uma forma de pagamento e valida se o valor está Ok;
25. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[024_pedidoComTipoVendaCartaoRicardoEletro.js](/Testes/test/novos_testes/024_pedidoComTipoVendaCartaoRicardoEletro.js)