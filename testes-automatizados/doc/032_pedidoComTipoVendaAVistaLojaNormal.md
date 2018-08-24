# Caso de test 032 Pedido com tipo de venda 'A vista' com Empenho em Loja e Entrega Normal.
Produtos usados no pedido:

    - (223892-14-B) MINI SYSTEM SAMSUNG MX-J640 200W BIV

Serviços existentes no pedido:

    - "Não existem validações de serviços nesse caso de teste"

Garantias existentes no pedido:

    - "Não existem validações de garantias nesse caso de teste"

Empenhos realizados no pedido:

    - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista". Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona um cliente padrão, criado por um teste automatizado;
3. Abre a tela de elaboração do pedido;
4. Informa o tipo de venda "A Vista";
5. Pesquisa e seleciona um produto (MINI SYSTEM SAMSUNG MX-J640 200W BIV);
6. Empenha o produto (LOJA - NORMAL);
7. Remove todos os serviços existentes no pedido deixando apenas o Frete;
8. Insere a forma de pagamento (V - Dinheiro);
9. Salva e efetua o fechamento total do pedido;
```
### Código fonte do teste
[032_pedidoComTipoVendaAVistaLojaNormal.js](/Testes/test/novos_testes/032_pedidoComTipoVendaAVistaLojaNormal.js)