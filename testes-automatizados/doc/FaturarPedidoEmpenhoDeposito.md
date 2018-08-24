> O caso de testes descrito abaixo, realiza o faturamento de um pedido com empenho em depósito. Cria uma carga, fatura essa carga, processa nota fiscal de transferência e efetua o faturamento dessa nota.

> Script executado quando o pedido possui empenho em depósito;

### Casos de Teste
```sh
1. Loga no sistema (Loja depósito - 47);
2. Abre a tela de Carga (Faturamento > Cadastros > Carga X Veículo);
3. Cria uma nova carga;
4. Informe os dados (Cidade, Veículo, Descrição, Motorista e Telefone);
5. Salva a carga;
6. Abre a tela de Faturamento > Controles > Faturamento;
7. Pesquisa a carga informando o (Número da carga criada no passo 5);
8. Informe o tipo de frete "Emitente";
9. Clica no botão "Filtro Controle de Faturamento";
10. Pesquisa o controle de faturamento informando (Número do Pedido);
11. Clica em Consultar;
12. Seleciona o controle de faturamento;
13. Clica em Atribuir;
14. Clica em Faturar;

15. Abre a tela de Faturamento (Faturamento > Controles > Faturamento);
16. Recupera a nota de transferência criada no passo 14;
17. Aprova NFe de transferência;
18. Sai do sistema do depósito;

19. Loga no sistema (Loja mini cd - 221);
20. Abre a tela Notas de Transferências (Faturamento > Notas Transferências);
21. Pesquisa nota fiscal de transferência informando (Número Nota Fiscal, Limpa Loja de Destino);
22. Clica no botão Procurar;
23. Seleciona a Nota Fiscal;
24. Clica no botão Selecionar;
25. Informa Grupo de Estoque (40 - Base) e Quantidade Conferida (1) e Observação ("Teste");
26. Clica no botão Processar;
27. Recupera o número da carga;
28. Abre a tela Faturamento (Faturamento > Controles > Faturamento);
29. Pesquisa a carga informando (Número da carga do passo 27);
30. Clica no botão Pesquisar;
31. Seleciona a carga encontrada;
32. Informe o tipo de frete "Emitente";
33. Clica no botão Faturar;
34. Aprova NFe gerada no passo 33;
```
### Código fonte do teste
[FaturaPedidoEmpenhoDeposito.java](/TestesSelenium/src/test/java/br/com/mv/test/FaturaPedidoEmpenhoDeposito.java)