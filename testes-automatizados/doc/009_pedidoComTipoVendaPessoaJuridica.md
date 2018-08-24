# Caso de test 009 Pedido com tipo de venda 'Pessoa Jurídica'.
Produtos usados no pedido:

  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT
  - (201791-4-0) KIT COZ ELIANA BR MULTIVISAO

Serviços existentes no pedido:

  - (104141) Frete
  - (139524) Solução Certa Informática

Garantias existentes no pedido:

  - (139524) Solução Certa Informática
  - (142383) Montagem Kit Cozinha / Cozinhas

Empenhos realizados no pedido:

  - Depósito - Agendada

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Pessoa Jurídica", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente pessoa jurídica por um CNPJ que ainda não esta cadastrado no sistema;
3. Abrirá a tela de cadastro de cliente;
4. Preenche o formulário principal da tela de cadastro do cliente com as informações do cliente;
5. Informa o representante da empresa que esta sendo cadastrada;
6. Insere o endereço do cliente;
7. Insere 2 referências;
8. Salva o cliente (DEVERÁ FECHAR A TELA DE CADASTRO DE CLIENTE E ABRIR A DE ELABORAÇÃO DO PEDIDO);
9. Abre a tela de elaboração de pedido;
10. Informa o tipo de venda "PESSOA JURÍDICA";
11. Pesquisa e seleciona um produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), que contém as seguintes garantias (SOLUÇÃO CERTA INFORMÁTICA, SERVICO ANTIVIRUS MOBILE NORTON);
12. Empenha o produto (DEPÓSITO - AGENDADA);
13. Insere um valor de entrada;
14. Altera o plano de pagamento para "2x";
15. Valida as garantias de todos os produtos inseridos no pedido;
16. Valida os serviços do pedido;
17. Insere 1ª forma de pagamento (E - DINHEIRO), contendo o valor da entrada do pedido;
18. Insere a 2ª forma de pagamento (F - BOLETO BANCÁRIO) , contendo o restante do valor do pedido;
19. Gera o documento de financiamento;
20. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
21. Abre a tela de consulta do pedido;
22. Pesquisa pelo pedido feito acima;
23. Reabre o pedido;
24. Adiciona um segundo produto (KIT COZ ELIANA BR MULTIVISAO), com as seguinte garantias (MONTAGEM KIT COZINHA / COZINHAS, GARANTIA MAIOR MOVEIS - 1 ANO);
25. Empenha o produto (DEPÓSITO - AGENDADA);
26. Valida as garantias de todos os produtos existentes no pedido;
27. Valida os serviços do pedido;
28. Remove a linha de forma de pagamento da aba planilha;
29. Insere 1ª forma de pagamento (E - DINHEIRO), contendo o valor da entrada do pedido;
30. Insere a 2ª forma de pagamento (F - BOLETO BANCÁRIO) , contendo o restante do valor do pedido e valida se os valores foram reajustados;
31. Gera o documento do financiamento;
32. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[009_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/009_pedidoComTipoVendaPessoaJuridica.js)