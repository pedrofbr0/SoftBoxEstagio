# Caso de test 011 Pedido com tipo de venda 'Pessoa Jurídica'.
Produtos usados no pedido:

  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT
  - (189137-315-0) RACK 097L TUANI 28988 EBA/PTO EDN

Serviços existentes no pedido:

  - (209514) Serviço Antivirus Mobile Norton

Garantias existentes no pedido:

  - (192434) Montagem de Móveis

Empenhos realizados no pedido:

  - Depósito - Retirada Outra Loja com Empenho Depósito

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Pessoa Jurídica", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente (PESSOA JURÍDICA) padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "PESSOA JURÍDICA";
5. Pesquisa e seleciona um produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), que contém as seguintes garantias (SOLUÇÃO CERTA INFORMÁTICA, SERVIÇO ANTIVIRUS MOBILE NORTON);
6. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
7. Define valor de entrada;
8. Valida as garantias de todos os produtos inseridos no pedido;
9. Valida os serviços do pedido;
10. Insere a 1ª forma de pagamento (E - CARTÃO VISA ELECTRON), contendo o valor da entrada do pedido;
11. Insere a 2ª forma de pagamento (F - BOLETO BANCÁRIO), contendo o valor restante do pedido;
12. Gera o número do documento do financiamento;
13. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
14. Abre a tela de consulta do pedido;
15. Pesquisa pelo pedido feito acima;
16. Reabre o pedido;
17. Adiciona um segundo produto (RACK 097L TUANI 28988 EBA/PTO EDN), com as seguinte garantias (MONTAGEM DE MÓVEIS);
18. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
19. Valida as garantias de todos os produtos existentes no pedido;
20. Valida os serviços do pedido;
21. Remove a linha de forma de pagamento da aba planilha;
22. Insere a 1ª forma de pagamento (E - CARTÃO VISA ELECTRON), contendo o valor da entrada do pedido;
23. Insere a 2ª forma de pagamento (F - BOLETO BANCÁRIO), contendo o valor restante do pedido;
24. Gera o número do documento do financiamento;
25. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[011_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/011_pedidoComTipoVendaPessoaJuridica.js)