# Caso de test 'Cadastro de Cliente Completo PF(Pessoa Física), e Elaboração de um pedido com tipo de venda Carne Losango SCRED'.
Produtos usados no pedido:

  - (219254-14-0) CEL MOTO G XT1068 2CHIP 8GB PTO

Serviços existentes no pedido:

  - "Não existem validações de serviços nesse caso de teste"

Garantias existentes no pedido:

  - "Não existem validações de garantias nesse caso de teste"

Empenhos realizados no pedido:

  - Loja - Normal

> O caso de teste descrito abaixo realiza o cadastro de um cliente completo "Pessoa Física", e um pedido com tipo de venda "Carne Losango SCRED", variando as opções de empenho contidas no pedido, tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Informa um CPF válido ainda não cadastrado no sistema (TELA DE CADASTRO DE CLIENTE SERÁ ABERTA);
3. Preenche o formulário principal da tela de cadastro do cliente com as informações do cliente;
4. Insere o endereço principal do cliente;
5. Preenche o formulário de "Identificação/Trabalho";
6. Insere 2 referências;
7. Preenche o formulário "Cartão MVShop";
8. Salva o cliente (DEVERÁ FECHAR A TELA DE CADASTRO DE CLIENTE E ABRIR A DE ELABORAÇÃO DO PEDIDO);
9. Informa o tipo de venda "Carne Losango SCRED";
10. Pesquia e seleciona um produto (CEL MOTO G XT1068 2CHIP 8GB PTO);
11. Empenha o produto (LOJA - NORMAL);
12. Define a tabela de preço do produto (89 - TABELA 89);
13. Altera o plano de pagamento para "10x";
14. Valida os serviços do pedido;
15. Insere a forma de pagamento da entrada (F - CARNE LOSANGO);
16. Salva e efetua o fechamento total do pedido;
```
### Código fonte do teste
[elaboracaoPedidoComPagamentoCarneLosango.js](Testes/test/novos_testes/elaboracaoPedidoComPagamentoCarneLosango.js)
