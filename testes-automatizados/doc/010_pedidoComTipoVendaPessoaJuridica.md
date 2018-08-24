# Caso de test 010 Pedido com tipo de venda 'Pessoa Jurídica'.
Produtos usados no pedido:

  - (139550-0-0) MODEM USB ZTE MF190 POS PAGO
  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO

Serviços existentes no pedido:

  - (104141) Frete
  - (209514) Serviço Antivirus Mobile Norton

Garantias existentes no pedido:

  - (149535) Suporte TV
  - (143191) Banda Larga Modem

Empenhos realizados no pedido:

  - Depósito - Cliente
  - Loja - Normal
  - Loja - Retirada com Retenção

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Pessoa Jurídica", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Pesquisa e seleciona um cliente (PESSOA JURÍDICA) padrão criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "PESSOA JURÍDICA";
5. Pesquisa e seleciona um produto (MODEM USB ZTE MF190 PÓS PAGO), que contém as seguintes garantias (BANDA LARGA MODEM);
6. Empenha o produto (LOJA - NORMAL);
7. Define valor de entrada "0,00";
8. Define o plano de pagamento para "1x";
9. Valida as garantias de todos os produtos inseridos no pedido;
10. Valida os serviços do pedido;
11. Insere a forma de pagamento (F - DUPLICATA AON), contendo o valor total do pedido;
12. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
13. Abre a tela de consulta do pedido;
14. Pesquisa pelo pedido feito acima;
15. Reabre o pedido;
16. Adiciona um segundo produto (SUP MULTI TV PLUG 14A84 PTO), com as seguinte garantias (INSTALAÇÃO DE TV, SUPORTE TV);
17. Empenha o produto (DEPÓSITO - AGENDADA CLIENTE);
18. Cancela o empenho do produto (MODEM USB ZTE MF190 PÓS PAGO);
19. Reempenha o produto (MODEM USB ZTE MF190 PÓS PAGO) - (LOJA - RETIRADA COM RETENÇÃO);
20. Valida as garantias de todos os produtos existentes no pedido;
21. Valida os serviços do pedido;
22. Remove a linha de forma de pagamento da aba planilha;
23. Insere a forma de pagamento (F - DUPLICATA AON), contendo o valor total do pedido;
24. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[010_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/010_pedidoComTipoVendaPessoaJuridica.js)