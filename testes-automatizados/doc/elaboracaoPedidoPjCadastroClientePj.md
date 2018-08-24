# Caso de test 'Cadastro de Cliente e Elaboração de Pedido PJ (Pessoa Jurídica), com Empenho Automático'.
Produtos usados no pedido:

  - (214954-14-B) TABLET CCE TR92 8GB WF9 PTO

Serviços existentes no pedido:

  - "Não existem validações de serviços no teste"

Garantias existentes no pedido:

  - "Não existem validações de garantias no teste"

Empenhos realizados no pedido:

  - Empenho Automático

> O caso de teste descrito abaixo realiza o cadastro de um cliente PJ (Pessoa Jurídica) e uma venda com tipo de venda (Pessoa Jurídica) com empenho automático. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Na tela de consulta de cliente, marca a opção de "PESSOA FÍSICA" (NÃO);
3. Insere um CNPJ válida que ainda não esteja cadastro no sistema;
4. Abre a tela de cadastro cliente clicando em "NOVO CLIENTE";
5. Insere os dados do cliente no formulário principal;
6. Cadastra o representante da empresa na aba "COMPRADOR/REPRESENTANTE";
7. Cadastra o endereço do cliente;
8. Cadastra as referências;
9. Salva o cliente (DEVERÁ ABRIR A TELA DE ELABORAÇÃO DO PEDIDO);
10. Informa o tipo de venda "PESSOA JURÍDICA";
11. Pesquia e seleciona um produto (CEL MOTO G XT1068 2CHIP 8GB PTO);
12. Não efetua nenhum empenho, porque o mesmo será automático ao execultar o fechamento total do pedido;
13. Insere a forma de pagamento do financiamento (F - BOLETO BANCÁRIO), contendo o valor total do pedido;
14. Abre a modal de gerar financiamento e informa um Número do Documento;
15. Salva e efetua o fechamento total do pedido;
```
### Código fonte do teste
[elaboracaoPedidoPjCadastroClientePj.js](Testes/test/novos_testes/elaboracaoPedidoPjCadastroClientePj.js)