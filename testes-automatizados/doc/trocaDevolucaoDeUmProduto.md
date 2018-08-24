# Caso de teste de troca e devolução de apenas um produto.
Pedidos usados no caso de teste: 

  - Qualquer pedido que esteja faturado e ainda não tenha sido feito Troca ou Devolução, e que contenha no mínio 1 produto.

> O caso de testes descrito abaixo, realiza a troca ou a devolução de um único produto e de nenhum serviçode um pedido. Cria um pré-recibo para a troca/devolução, aprova o produto, cria a nota fiscal de devolução do produto e efetua a devolução do produto.

### Casos de Teste
```sh
1. Loga no sistema;
2. Abre a tela de Pré-Recibos (Faturamento > Troca/Devolução > Pré-Recibos);
3. Abre um novo Pré-Recibo;
4. Pesquisa o pedido informando (Pedido, Empresa, Loja, Processo);
5. Deixa apenas um produto no pedido, caso exista mais de um;
6. Remove todos os serviços do pedido;
7. Confirma o pré-recibo;
8. Seleciona o produto e informa o motivo de troca/devolução para o mesmo;
9. Salva a solicitação;
10. Volta a tela de Pré-Recibos e pesquisa pelo Pré-Recibo criado anteriormente (OBS.: O mesmo estará com status de AG. APROVACAO PRODUTOS);
11. Seleciona e clica em editar o Pré-Recibo;
12. Seleciona um produto e clica em "Fluxo Aprovação" (Abrirá a tela de Aprovação de Produtos);
13. Seleciona a primeira pendência, clica no botão na coluna de observação;
14. Adiciona uma observação e clica em Ok;
15. Clica no botão Aprovar;
16. Clica no botão OK;
17. Pesquisa novamente pelo pré-recibo e valida que o mesmo foi aprovado;
18. Abre a tela de Emissão de NF Avulsa (Faturamento > Emissão NF Avulsa);
19. Informa a operação de nota fiscal;
20. Seleciona o tipo de Nota Fiscal;
21. Informa um grupo de estoque e clica no botão Informar Parâmetros;
22. Na janela de Parâmetros informa os seguintes parâmetros (Cupom Fiscal, Empresa, Cod. Loja, Série NF), e clica em pesquisar;
23. Seleciona o cupom fiscal encontrado na pesquisa e clica em Concluir;
24. Valida as informações da nota fisca e clica no botão Concluir Nota Fiscal;
25. Abre a tela de Devolução de Produtos (Faturamento > Controles > Devolução de Produtos);
26. Clica no botão Pesquisar;
27. Clica no botão Pesquisar Nota Fiscal (abrirá o popup de consulta de NF);
28. Informa número da Nota Fiscal que foi gerada na tela de Emissão NF Avulsa, e clica no botão Selecionar;
29. Clica sobre a nota que foi encontrada na consulta (o popup de consulta de NF ira fechar e na tela de Devolução de Produtos irá carregar o produto a ser devolvido);
30. Seleciona o produto e clique no botão Editar;
31. Altera o Status para "Processado";
32. Adiciona uma Observação e clica no botão Salvar;
```
### Código fonte do teste
[TesteTrocaDevolucao.java](/TestesSelenium/src/test/java/br/com/mv/test/TrocaUmProduto.java)
