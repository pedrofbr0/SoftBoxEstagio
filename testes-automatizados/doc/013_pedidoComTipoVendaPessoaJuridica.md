# Caso de test 013 Pedido com tipo de venda 'Pessoa Jurídica'.
Produtos usados no pedido:

  - (219254-14-0) CEL MOTO G XT1068 2CHIP 8GB PTO
  - (189614-320-0) COMODA 5G MULT B255 C/ESP NOC KAPPESBERG

Serviços existentes no pedido:

  - (104141) Frete

Garantias existentes no pedido:

  - (209514) Serviço Antivirus Mobile Norton
  - (196015) Roubo e Furtos Celular Smartfone
  - (144736) Garantia Maior Celular - 2 anos
  - (144735) Garantia Maior Celular - 1 ano
  - (192434) Montagem de Móveis

Empenhos realizados no pedido:

  - Depósito - Agendada

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Pessoa Jurídica", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona um cliente (PESSOA JURÍDICA) padrão, criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "PESSOA JURÍDICA";
5. Pesquisa e seleciona um produto (CEL MOTO G XT1068 2CHIP 8GB PTO), que contém as seguintes garantias (SERVICO ANTIVIRUS MOBILE NORTON, ROUBO E FURTOS CELULAR SMARTFONE, GARANTIA MAIOR CELULAR);
6. Empenha o produto (DEPÓSITO - AGENDADA);
7. Valida as garantias de todos os produtos inseridos no pedido;
8. Valida os serviços do pedido;
9. Insere a forma de pagamento do financiamento (F - BOLETO BANCÁRIO), contendo o valor total do pedido;
10. Abre a modal de gerar financiamento e informa um Número do Documento;
11. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
12. Abre a tela de consulta do pedido;
13. Pesquisa pelo pedido feito acima;
14. Reabre o pedido;
15. Pesquisa e adiciona um segundo produto (COMODA 5G MULT B255 C/ESP NOC KAPPESBERG), contendo as seguinte garantias (MONTAGEM DE MÓVEIS);
16. Empenha o mesmo (DEPÓSITO - AGENDADA);
17. Insere um valor de entrada;
18. Valida as garantias de todos os produtos no pedido;
19. Valida os serviços do pedido;
20. Abre a aba planilha e remove a linha da planilha;
21. Insere a 1ª forma de pagamento da entrada (E - DINHEIRO), contendo o valor da entrada;
22. Insere a 2ª forma de pagamento do financiamento (F - BOLETO BANCÁRIO), contendo o restando do valor do pedido;
23. Abre a modal de gerar financiamento e informa um Número do Documento;
24. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[013_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/013_pedidoComTipoVendaPessoaJuridica.js)
