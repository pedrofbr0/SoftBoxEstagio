# Caso de test 003 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (193957-0-B) MULTIFUNCIONAL HP DESKJET 2546
  - (196096-4-B) TABLET STILLE-TEC NB106 4 WF 7 BR
  - (156363-260-0) RACK DJ AQUILA NOG/PTO

Serviços existentes no pedido:

  - (103518) Claro Cartão 30

Garantias existentes no pedido:

  - (139524) Solução Certa Informática
  - (192434) Montagem de Móveis

Empenhos realizados no pedido:

  - Depósito - Retirada Outra Loja com Empenho Depósito

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (MULTIFUNCIONAL HP DESKJET 2546), que contém as seguintes garantias (SOLUÇÃO CERTA INFORMÁTICA, GARANTIA MAIOR IMPRESSORA);
6. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
7. Pesquisa e seleciona um segundo produto (TABLET STILLE-TEC NB106 4 WF 7 BR), que contém as seguintes garantias (ROUBO E FURTOS TABLET, SOLUÇÃO CERTA INFORMÁTICA, GARANTIA MAIOR TABLET, SERVICO ANTIVIRUS MOBILE NORTON);
8. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
9. Valida as garantias de todos os produtos inseridos no pedido;
10. Valida os serviços do pedido;
11. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
12. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
13. Abre a tela de consulta do pedido;
14. Pesquisa pelo pedido feito acima;
15. Reabre o pedido;
16. Pesquisa e seleciona um terceiro produto (RACK DJ AQUILA NOG/PTO), que contém as seguintes garantias (MONTAGEM DE MÓVEIS);
17. Empenha o produto (DEPÓSITO - RETIRADA OUTRA LOJA COM EMPENHO DEPÓSITO);
18. Valida as garantias de todos os produtos no pedido;
19. Valida os serviços do pedido;
20. Abre a aba planilha e remove a linha da planilha;
21. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
22. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[003_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/003_pedidoComTipoVendaAVista.js)
