# Caso de teste de Troca e Devolução.
Pedidos usados no caso de teste: 

  - Qualquer pedido que esteja faturado e ainda não tenha sido feito Troca ou Devolução.

> O caso de testes descrito abaixo, realiza a troca ou a devolução de todos os produtos e todos os serviço existente em um pedido. Cria um pré-recibo para a troca/devolução, valida quais serviços requerem aprovação, e aprova todos os produtos e todos os itens, cria as notas fiscais necessárias e efetua a devolução dos produtos.

### Casos de Teste
```sh
1. Loga no sistema;
2. Abre a tela de Pré-Recibos (Faturamento > Troca/Devolução > Pré-Recibos);
3. Abre um novo Pré-Recibo;
4. Pesquisa o pedido informando (Pedido, Empresa, Loja, Processo);
5. Valida os produtos e serviços existentes na nota e clica em Ok;
6. Seleciona e informa um motivo de troca/devolução para todos os produtos do pedido;
7. Seleciona e informa um motivo de troca/devolução para todos os serviços que necessitarem de um motivo (OBS.: alguns serviços já são aprovados para troca/devolução, não tendo a necessidade de informar um motivo para o mesmo);
8. Salva a solicitação;
9. Volta a tela de Pré-Recibos e pesquisa pelo Pré-Recibo criado anteriormente (OBS.: O mesmo estará com status de AG. APROVACAO PRODUTOS);
10. Seleciona e clica em editar o Pré-Recibo;
11. Seleciona um produto e clica em "Fluxo Aprovação" (Abrirá a tela de Aprovação de Produtos);
12. Seleciona a primeira pendência, clica no botão na coluna de observação;
13. Adiciona uma observação e clica em Ok (OBS.: os passos 12 e 13 serão repetidos até que todas as pendências tenham sido aprovadas).
14. Clica no botão Aprovar (OBS.: os passos 11, 12, 13, 14 serão repetidos ate que todos os produtos tenham sido aprovados);
15. Caso exista serviço pendente de aprovação, selecionar o serviço e clicar no botão "Fluxo Aprovação";
16. No popup que surge e informado uma obs e, clica no botão aprovar (OBS.: os passos 15 e 16 serão repetidos para todos os serviços pendentes de aprovação);
17. Clica no botão OK;
18. Pesquisa novamente pelo pré-recibo e valida que o mesmo foi aprovado;
19. Realiza login no minicd
20. Abre a tela de Emissão de NF Avulsa (Faturamento > Emissão NF Avulsa);
21. Informa a operação de nota fiscal;
22. Seleciona o tipo de Nota Fiscal;
23. Informa um grupo de estoque e clica no botão Informar Parâmetros;
24. Na janela de Parâmetros informa os seguintes parâmetros (Cupom Fiscal, Empresa, Cod. Loja, Série NF), e clica em pesquisar;
25. Seleciona o cupom fiscal encontrado na pesquisa e clica em Concluir;
26. Valida as informações da nota fisca e clica no botão Concluir Nota Fiscal;
OBS.: Ao criar um Pré-Recibo para Troca/Devolução, pode ser gerado mais de um cupom fiscal, dependedo da forma de empenho dos produtos do pedido. Se o mesmo ocorrer os passos 20, 21, 22, 23 e 24 serão repetidos até que tenha sido criado notas para todos os cumpons fiscais gerados;
27. Realiza login na loja;
28. Abre a tela de Devolução de Produtos (Faturamento > Controles > Devolução de Produtos);
29. Clica no botão Pesquisar;
30. Clica no botão Pesquisar Nota Fiscal (abrirá o popup de consulta de NF);
31. Informa número da Nota Fiscal que foi gerada na tela de Emissão NF Avulsa, e clica no botão Selecionar;
32. Clica sobre a nota que foi encontrada na consulta (o popup de consulta de NF ira fechar e na tela de Devolução de Produtos irá carregar todos os produtos contidos na NF selecionada);
33. Seleciona o produto e clique no botão Editar;
34. Altera o Status para "Processado";
35. Adiciona uma Observação e clica no botão Salvar (OBS.: Caso exista mais de um produto na NF, repetir os passos 32,33 e 34 até que todos os produtos da NF teham sido processados);
OBS.: (Caso tenha sido gerado mais de uma NF, os passos 28, 29, 30, 31, 32, 33 e 34 serão repetidos, até que todos os produtos de todas as notas tenham sido processados);

```
### Código fonte do teste
[TrocaProdutoEmpenhoDeposito.java](/TestesSelenium/src/test/java/br/com/mv/test/TrocaProdutoEmpenhoDeposito.java)
