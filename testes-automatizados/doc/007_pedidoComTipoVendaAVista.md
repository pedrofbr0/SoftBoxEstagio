# Caso de test 007 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (219757-14-B) PRANCHA MALLORY ARGAN LISS BIV
  - (219494-4-0) TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT

Serviços existentes no pedido:

  - (104141) Frete
  - (163594) Seg Caminhão da Sorte

Garantias existentes no pedido:

  - (144779) Troca Garantida - 1 ano
  - (196014) Roubo e Furtos Tablet
  - (139524) Solução Certa Informática
  - (144776) Garantia Maior Tablet - 2 anos
  - (209514) Serviço Antivirus Mobile Norton

Empenhos realizados no pedido:

  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (PRANCHA MALLORY ARGAN LISS BIV), que contém as seguintes garantias (TROCA GARANTIDA - 1 ANO);
6. Empenha o produto (LOJA - NORMAL);
7. Valida as garantias de todos os produtos inseridos no pedido;
8. Valida os serviços do pedido;
9. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Abre a tela de consulta do pedido;
12. Pesquisa pelo pedido feito acima;
13. Reabre o pedido;
16. Adiciona um segundo produto (TABLET DL TP104 KIDS 8GB 7CAPA TV:BR:SEM VOLT), com as seguinte garantias (ROUBO E FURTOS TABLET, SOLUÇÃO CERTA INFOMÁTICA, GARANTIA MAIOR TABLET, SERVIÇO ANTIVIRUS MOBILE NORTON);
17. Empenha o produto (LOJA - NORMAL);
18. Valida as garantias de todos os produtos existentes no pedido;
19. Valida os serviços do pedido;
20. Remove a linha de forma de pagamento da aba planilha;
21. Adiciona a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
22. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[007_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/007_pedidoComTipoVendaAVista.js)
