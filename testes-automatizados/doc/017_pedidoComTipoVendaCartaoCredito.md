# Caso de test 017 Pedido com tipo de venda 'Cartão de Crédito'.
Produtos usados no pedido:

  - (208974-371-0) RACK 091L MOSCOU 29598 CRV TQ EDN
  - (188774-14-0) SUP MULTI TV PLUG LED 14A84 PTO
  - (16374-0-0) KIT FEM BE SECRET - PERA

Serviços existentes no pedido:

  - (139524) Solução Certa Informática
  - (163594) Seg Caminhão da Sorte
  - (104141) Frete

Garantias existentes no pedido:

  - (192434) Montagem de Móveis
  - (144765) Garantia Maior Móveis - 1 ano
  - (140358) Instalação de TV
  - (149535) Suporte TV
  - (136711) Perfumaria

Empenhos realizados no pedido:

  - Depósito - Agendada
  - Loja - Retirada Loja com Retenção
  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Cartão de Crédito", variando as opções de empenho contidas no pedido, garantias, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Informa um CPF válido ainda não cadastrado no sistema (TELA DE CADASTRO DE CLIENTE SERÁ ABERTA);
3. Preenche o formulário principal da tela de cadastro do cliente com as informações do cliente;
4. Insere o endereço principal do cliente;
5. Preenche o formulário de "Identificação/Trabalho";
6. Insere 2 referências;
7. Preenche os formulário "Cartão MVShop";
8. Salva o cliente (DEVERÁ FECHAR A TELA DE CADASTRO DE CLIENTE E ABRIR A DE ELABORAÇÃO DO PEDIDO);
9. Informa o tipo de venda "Cartão de Crédito";
10. Pesquisa e seleciona um produto (RACK 091L MOSCOU 29598 CRV TQ EDN), que contém as seguintes garantias (MONTAGEM DE MOVEIS, GARANTIA MAIOR MOVEIS - 1 ANO);
11. Define a tabela de preço do produto (65 - TABELA 65);
12. Empenha o produto (DEPÓSITO - AGENDADA);
13. Pesquisa e seleciona um segundo produto (SUP MULTI TV PLUG LED 14A84 PTO), que contém as seguintes garantias (INSTALAÇÃO DE TV, SUPORTE TV);
14. Define a tabela de preço do produto (65 - TABELA 65);
15. Empenha o produto (LOJA - NORMAL);
16. Define um valor de entrada;
17. Altera o plano de pagamento para "3x";
18. Valida as garantias de todos os produtos inseridos no pedido;
19. Valida os serviços do pedido;
20. Insere 1ª forma de pagamento da entrada (E - DINHEIRO);
21. Insere 2ª forma de pagamento do financiamento (F - CARTÃO VISA);
22. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
23. Abre a tela de consulta do pedido;
24. Pesquisa pelo pedido feito acima;
25. Reabre o pedido;
26. Pesquisa e adiciona um terceiro produto (KIT FEM BE SECRET - PERA), contendo as seguinte garantias (PERFUMARIA);
27. Define a tabela de preço do produto (65 - TABELA 65);
28. Empenha o mesmo (LOJA - NORMAL);
29. Valida as garantias de todos os produtos no pedido;
30. Valida os serviços do pedido;
31. Insere um valor de entrada (R$ 0,00);
32. Define o plano de pagamento em "6x";
33. Abre a aba planilha e remove todas a linhas da planilha;
34. Insere a forma de pgamento do financiamento (F - CARTÃO VISA);
35. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[017_pedidoComTipoVendaCartaoCredito.js](Testes/test/novos_testes/017_pedidoComTipoVendaCartaoCredito.js)
