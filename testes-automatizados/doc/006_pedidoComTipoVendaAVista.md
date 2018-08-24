# Caso de test 006 Pedido com tipo de venda 'A Vista'.
Produtos usados no pedido:

  - (214954-14-B) TABLET CCE TR92 8GB WF9 PTO

Serviços existentes no pedido:

  - (209514) Serviço Antivirus Mobile Norton

Garantias existentes no pedido:

  - (196014) Roubo e Furtos Tablets
  - (139524) Solução Certa Informática
  - (144775) Garantia Maior Tablet - 1ano
  - (144776) Garantia Maior Tablet - 2 anos
  - (209514) Serviço Antivirus Mobile Norton

Empenhos realizados no pedido:

  - Loja - Retirada Loja com Retenção

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (TABLET CCE TR92 8GB WF9 PTO), que contém as seguintes garantias (ROUBO E FURTOS TABLET, SOLUÇÃO CERTA INFORMÁTICA, SERVIÇO ANTIVIRUS MOBILE NORTON, GARANTIA MAIOR TABLET);
6. Empenha o produto (LOJA - RETIRADA LOJA COM RETENÇÃO);
7. Valida as garantias de todos os produtos inseridos no pedido;
8. Valida os serviços do pedido;
9. Insere a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
10. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
11. Abre a tela de consulta do pedido;
12. Pesquisa pelo pedido feito acima;
13. Reabre o pedido;
14. Valida os serviços do pedido;
15. Valida as garantias do serviço;
16. Excluir a linha da planilha de forma de pagamento;
17. Adiciona a forma de pagamento (V - DINHEIRO), contendo o valor total do pedido;
18. Salva o pedido, efetua o fechamento total.
```
### Código fonte do teste
[006_pedidoComTipoVendaAVista.js](Testes/test/novos_testes/006_pedidoComTipoVendaAVista.js)