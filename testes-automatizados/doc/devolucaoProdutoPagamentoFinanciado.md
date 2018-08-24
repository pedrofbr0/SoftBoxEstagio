# Caso de teste de devolução parcial de um pedido.
Pedidos usados no caso de teste: 

  - Qualquer pedido que esteja faturado e ainda não tenha sido feito Troca ou Devolução, e que contenha no mínio 1 produto e um serviço com.

> O caso de testes descrito abaixo, realiza a devolução de um ou mais produtos do pedido e de nenhum serviço, fazendo com que a troca seja parcial. Cria um pré-recibo para a troca/devolução, aprova o(s) produto(s), aprova a planilha com a devolução parcial, cria a nota fiscal de devolução do produto e efetua a devolução do produto.

### Casos de Teste
```sh
1. Loga no sistema;
2. Abre a tela de Pré-Recibos (Faturamento > Troca/Devolução > Pré-Recibos);
3. Abre um novo Pré-Recibo;
4. Pesquisa o pedido informando (Pedido, Empresa, Loja, Processo);
5. Remove todos os serviços do pedido;
6. Deixa todos os produtos do pedido(assim será uma devolução parcial);
7. Confirma o pré-recibo;
8. Seleciona o(s) produto(s) e informa o motivo da devolução do mesmo;
9. Salva a solicitação;
10. Volta a tela de Pré-Recibos e pesquisa pelo Pré-Recibo criado anteriormente (OBS.: O mesmo estará com status de AG. APROVAÇÃO PRODUTOS);
11. Seleciona e clica em editar o Pré-Recibo;
12. Seleciona um produto e clica em "Fluxo Aprovação" (Abrirá a tela de Aprovação de Produtos);
13. Seleciona a primeira pendência, clica no botão na coluna de observação;
14. Adiciona uma observação e clica em Ok (caso exista mais de uma etapa a ser aprovada, os passos 13 e 14 se repetirão);
15. Clica no botão Aprovar (caso exista mais de um produto n pedido, os passos 12, 13, 14, 15 se petirão);
16. Clica no botão OK;
17. Pesquisa novamente pelo pré-recibo (OBS.: O mesmo estará com status de AG. APROVAÇÃO PLANILHAS);
18. Seleciona o pré-recibo encontrado e clica em Editar;
19. No popup de Pré-Recibo Troca/Devolução, clica sobre o botão plilha;
20. Abrirá uma nova popupa com as opções de devolução do pagamento;
21. É informado o típo de devolução e o valor da devolução, e botão Aprovar;
22. Na tela de pré-recibo clica no botão Ok;
23. Pesquisa novamente pelo pré-recibo e valida que o mesmo foi aprovado;
24. Abre a tela de Emissão de NF Avulsa (Faturamento > Emissão NF Avulsa);
25. Informa a operação de nota fiscal;
26. Seleciona o tipo de Nota Fiscal;
27. Informa um grupo de estoque e clica no botão Informar Parâmetros;
28. Na janela de Parâmetros informa os seguintes parâmetros (Cupom Fiscal, Empresa, Cod. Loja, Série NF), e clica em pesquisar;
29. Seleciona o cupom fiscal encontrado na pesquisa e clica em Concluir;
30. Valida as informações da nota fisca e clica no botão Concluir Nota Fiscal;
31. Abre a tela de Devolução de Produtos (Faturamento > Controles > Devolução de Produtos);
32. Clica no botão Pesquisar;
33. Clica no botão Pesquisar Nota Fiscal (abrirá o popup de consulta de NF);
34. Informa número da Nota Fiscal que foi gerada na tela de Emissão NF Avulsa, e clica no botão Selecionar;
35. Clica sobre a nota que foi encontrada na consulta (o popup de consulta de NF ira fechar e na tela de Devolução de Produtos irá carregar o produto cujo o serviço vinculado será devolvido);
36. Seleciona o produto e clique no botão Editar;
37. Altera o Status para "Processado";
38. Adiciona uma Observação e clica no botão Salvar;
```
### Código fonte do teste
[TesteTrocaDevolucao.java](/TestesSelenium/src/test/java/br/com/mv/test/DevolucaoProdutoPagamentoFinanciado.java)
