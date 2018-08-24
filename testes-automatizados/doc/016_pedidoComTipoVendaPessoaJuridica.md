# Caso de test 016 Pedido com tipo de venda 'Pessoa Jurídica'.
Produtos usados no pedido:

  - (139550-0-0) MODEM USB ZTE MF190 POS PAGO
  - (187574-14-0) SUP MULTI TV PLUG 14A84 PTO

Serviços existentes no pedido:

  - "Nenhum serviço adicionado a esse pedido"

Garantias existentes no pedido:

  - (143191) Banda Larga Modem
  - (140358) Instalação de TV
  - (149535) Suporte TV

Empenhos realizados no pedido:

  - Outra Loja - Normal
  - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Pessoa Jurídica", variando as opções de empenho contidas no pedido, garantias e tipos de pagamentos disponíveis e nenhum serviço adicionado ao pedido. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona um usuário (PESSOA JURÍDICA) padrão, criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "PESSOA JURÍDICA";
5. Pesquisa e seleciona um produto (MODEM USB ZTE MF190 POS PAGO), que contém as seguintes garantias (BANDA LARGA MODEM);
6. Empenha o produto (OUTRA LOJA - NORMAL);
7. Define um valor de entrada;
8. Valida as garantias de todos os produtos inseridos no pedido;
9. Realiza liberação de empenho em Outra Loja;
10. Insere 1ª forma de pagamento da entrada (E - CHEQUE PRÉ-DATADO);
11.. Abre a modal de validação de CMC7, e efetua a validação do mesmo;
12.. Insere 2ª forma de pagamento do financiamento (F - DUPLICATA AON);
13.. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
14.. Abre a tela de consulta do pedido;
15.. Pesquisa pelo pedido feito acima;
16.. Reabre o pedido;
17.. Pesquisa e adiciona um segundo produto (SUP MULTI TV PLUG 14A84 PTO), contendo as seguinte garantias (INSTALAÇÃO DE TV, SUPORTE TV);
18.. Empenha o mesmo (LOJA - NORMAL);
19.. Valida as garantias de todos os produtos no pedido;
20.. Abre a aba planilha e remove todas a linhas da planilha;
21.. Insere 1ª forma de pagamento da entrada (E - CHEQUE PRÉ-DATADO);
22.. Abre a modal de validação de CMC7, e efetua a validação do mesmo;
23.. Insere 2ª forma de pagamento do financiamento (F - DUPLICATA AON);
24. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[016_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/016_pedidoComTipoVendaPessoaJuridica.js)
