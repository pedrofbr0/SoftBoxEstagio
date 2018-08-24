# Caso de teste de devolu��o parcial de um pedido.
Pedidos usados no caso de teste: 

  - Qualquer pedido que esteja faturado e ainda n�o tenha sido feito Troca ou Devolu��o, e que contenha no m�nio 1 produto e um servi�o com.

> O caso de testes descrito abaixo, realiza a devolu��o de um ou mais produtos do pedido e de nenhum servi�o, fazendo com que a troca seja parcial. Cria um pr�-recibo para a troca/devolu��o, aprova o(s) produto(s), aprova a planilha com a devolu��o parcial, cria a nota fiscal de devolu��o do produto e efetua a devolu��o do produto.

### Casos de Teste
```sh
1. Loga no sistema;
2. Abre a tela de Pr�-Recibos (Faturamento > Troca/Devolu��o > Pr�-Recibos);
3. Abre um novo Pr�-Recibo;
4. Pesquisa o pedido informando (Pedido, Empresa, Loja, Processo);
5. Remove todos os servi�os do pedido;
6. Deixa todos os produtos do pedido(assim ser� uma devolu��o parcial);
7. Confirma o pr�-recibo;
8. Seleciona o(s) produto(s) e informa o motivo da devolu��o do mesmo;
9. Salva a solicita��o;
10. Volta a tela de Pr�-Recibos e pesquisa pelo Pr�-Recibo criado anteriormente (OBS.: O mesmo estar� com status de AG. APROVA��O PRODUTOS);
11. Seleciona e clica em editar o Pr�-Recibo;
12. Seleciona um produto e clica em "Fluxo Aprova��o" (Abrir� a tela de Aprova��o de Produtos);
13. Seleciona a primeira pend�ncia, clica no bot�o na coluna de observa��o;
14. Adiciona uma observa��o e clica em Ok (caso exista mais de uma etapa a ser aprovada, os passos 13 e 14 se repetir�o);
15. Clica no bot�o Aprovar (caso exista mais de um produto n pedido, os passos 12, 13, 14, 15 se petir�o);
16. Clica no bot�o OK;
17. Pesquisa novamente pelo pr�-recibo (OBS.: O mesmo estar� com status de AG. APROVA��O PLANILHAS);
18. Seleciona o pr�-recibo encontrado e clica em Editar;
19. No popup de Pr�-Recibo Troca/Devolu��o, clica sobre o bot�o plilha;
20. Abrir� uma nova popupa com as op��es de devolu��o do pagamento;
21. � informado o t�po de devolu��o e o valor da devolu��o, e bot�o Aprovar;
22. Na tela de pr�-recibo clica no bot�o Ok;
23. Pesquisa novamente pelo pr�-recibo e valida que o mesmo foi aprovado;
24. Abre a tela de Emiss�o de NF Avulsa (Faturamento > Emiss�o NF Avulsa);
25. Informa a opera��o de nota fiscal;
26. Seleciona o tipo de Nota Fiscal;
27. Informa um grupo de estoque e clica no bot�o Informar Par�metros;
28. Na janela de Par�metros informa os seguintes par�metros (Cupom Fiscal, Empresa, Cod. Loja, S�rie NF), e clica em pesquisar;
29. Seleciona o cupom fiscal encontrado na pesquisa e clica em Concluir;
30. Valida as informa��es da nota fisca e clica no bot�o Concluir Nota Fiscal;
31. Abre a tela de Devolu��o de Produtos (Faturamento > Controles > Devolu��o de Produtos);
32. Clica no bot�o Pesquisar;
33. Clica no bot�o Pesquisar Nota Fiscal (abrir� o popup de consulta de NF);
34. Informa n�mero da Nota Fiscal que foi gerada na tela de Emiss�o NF Avulsa, e clica no bot�o Selecionar;
35. Clica sobre a nota que foi encontrada na consulta (o popup de consulta de NF ira fechar e na tela de Devolu��o de Produtos ir� carregar o produto cujo o servi�o vinculado ser� devolvido);
36. Seleciona o produto e clique no bot�o Editar;
37. Altera o Status para "Processado";
38. Adiciona uma Observa��o e clica no bot�o Salvar;
```
### C�digo fonte do teste
[TesteTrocaDevolucao.java](/TestesSelenium/src/test/java/br/com/mv/test/DevolucaoProdutoPagamentoFinanciado.java)
