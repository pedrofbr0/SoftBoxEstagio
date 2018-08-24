# Caso de test 015 Pedido com tipo de venda 'Pessoa Jurídica'.
Produtos usados no pedido:

  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT
  - (189137-315-0) RACK 097L TUANI 28988 EBA/PTO EDN

Serviços existentes no pedido:

  - (104141) Frete

Garantias existentes no pedido:

  - (209514) Serviço Antivirus Mobile Norton
  - (139524) Solução Certa Informática
  - (192434) Montagem de Móveis

Empenhos realizados no pedido:

  - Loja - Normal
  - Depósito - Agendada

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Pessoa Jurídica", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona um usuário (PESSOA JURÍDICA) padrão, criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "PESSOA JURÍDICA";
5. Pesquisa e seleciona um produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), que contém as seguintes garantias (SERVICO ANTIVIRUS MOBILE NORTON, SOLUÇÃO CERTA INFORMÁTICA);
6. Empenha o produto (LOJA - NORMAL);
7. Valida as garantias de todos os produtos inseridos no pedido;
8. Valida os serviços do pedido;
9. Insere a forma de pagamento do financiamento (F - BOLETO BANCÁRIO);
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Abre a tela de consulta do pedido;
12. Pesquisa pelo pedido feito acima;
13. Reabre o pedido;
14. Pesquisa e adiciona um segundo produto (RACK 097L TUANI 28988 EBA/PTO EDN), contendo as seguinte garantias (MONTAGEM DE MÓVEIS);
15. Empenha o mesmo (DEPÓSITO - AGENDADA);
16. Valida as garantias de todos os produtos no pedido;
17. Valida os serviços do pedido;
18. Abre a aba planilha e remove a linha da planilha;
19. Insere a forma de pagamento da entrada (F - DUPLICATA AON);
20. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[015_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/015_pedidoComTipoVendaPessoaJuridica.js)
