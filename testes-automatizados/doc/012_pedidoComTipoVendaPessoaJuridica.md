# Caso de test 012 Pedido com tipo de venda 'Pessoa Jurídica'.
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
2. Na tela de consulta de cliente, marca a opção de "PESSOA FÍSICA" (NÃO);
3. Insere um CNPJ válida que ainda não esteja cadastro no sistema;
4. Abre a tela de cadastro cliente clicando em "NOVO CLIENTE";
5. Insere os dados do cliente no formulário principal;
6. Cadastra o representante da empresa na aba "COMPRADOR/REPRESENTANTE";
7. Cadastra o endereço do cliente;
8. Cadastra as referências;
9. Salva o cliente (DEVERÁ ABRIR A TELA DE ELABORAÇÃO DO PEDIDO);
10. Informa o tipo de venda "PESSOA JURÍDICA";
11. Pesquisa e seleciona um produto (CEL MOTO G XT1068 2CHIP 8GB PTO), que contém as seguintes garantias (SERVICO ANTIVIRUS MOBILE NORTON, ROUBO E FURTOS CELULAR SMARTFONE, GARANTIA MAIOR CELULAR);
12. Empenha o produto (DEPÓSITO - AGENDADA);
13. Valida as garantias de todos os produtos inseridos no pedido;
14. Valida os serviços do pedido;
15. Insere a forma de pagamento do financiamento (F - BOLETO BANCÁRIO), contendo o valor total do pedido;
16. Abre a modal de gerar financiamento e informa um Número do Documento;
17. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
18. Abre a tela de consulta do pedido;
19. Pesquisa pelo pedido feito acima;
20. Reabre o pedido;
21. Pesquisa e adiciona um segundo produto (COMODA 5G MULT B255 C/ESP NOC KAPPESBERG), contendo as seguinte garantias (MONTAGEM DE MÓVEIS);
22. Empenha o mesmo (DEPÓSITO - AGENDADA);
23. Insere um valor de entrada;
24. Valida as garantias de todos os produtos no pedido;
25. Valida os serviços do pedido;
26. Abre a aba planilha e remove a linha da planilha;
27. Insere a 1ª forma de pagamento da entrada (E - DINHEIRO), contendo o valor da entrada;
28. Insere a 2ª forma de pagamento do financiamento (F - BOLETO BANCÁRIO), contendo o restando do valor do pedido;
29. Abre a modal de gerar financiamento e informa um Número do Documento;
30. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[012_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/012_pedidoComTipoVendaPessoaJuridica.js)
