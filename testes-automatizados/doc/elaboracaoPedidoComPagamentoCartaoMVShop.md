# Caso de test 'Cadastro de Cliente Completo PF(Pessoa Física), e Elaboração de um pedido com tipo de venda Cartão MVShop'.
Produtos usados no pedido:

  - (219757-14-B) PRANCHA MALLORY ARGAN LISS BIV

Serviços existentes no pedido:

  - (104141) Frete

Garantias existentes no pedido:

  - "Não existem validações de garantias nesse caso de teste";

Empenhos realizados no pedido:

  - Depósito - Agendada Cliente

> O caso de teste descrito abaixo realiza o cadastro de um cliente completo "Pessoa Física", e um pedido com tipo de venda "Cartão MVShop", variando as opções de empenho contidas no pedido, serviços e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Informa um CPF válido ainda não cadastrado no sistema (TELA DE CADASTRO DE CLIENTE SERÁ ABERTA);
3. Preenche o formulário principal da tela de cadastro do cliente com as informações do cliente;
4. Insere o endereço principal do cliente;
5. Preenche o formulário de "Identificação/Trabalho";
6. Insere 2 referências;
7. Preenche os formulário "Cartão MVShop";
8. Salva o cliente (DEVERÁ FECHAR A TELA DE CADASTRO DE CLIENTE E ABRIR A DE ELABORAÇÃO DO PEDIDO);
9. Informa o tipo de venda "Cartão MVShop";
10. Pesquia e seleciona um produto (PRANCHA MALLORY ARGAN LISS BIVRACK);
11. Empenha o produto (DEPÓSITO - AGENDADA CLIENTE);
12. Define a tabela de preço do produto (65 - TABELA 65);
13. Altera o plano de pagamento para "3x";
14. Valida os serviços do pedido;
15. Insere a forma de pagamento da entrada (F - MV SHOP 1ª COMPRA);
16. Salva e efetua o fechamento total do pedido;
```
### Código fonte do teste
[elaboracaoPedidoComPagamentoCartaoMVShop.js](Testes/test/novos_testes/elaboracaoPedidoComPagamentoCartaoMVShop.js)
