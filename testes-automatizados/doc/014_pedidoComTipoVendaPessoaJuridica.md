# Caso de test 014 Pedido com tipo de venda 'Pessoa Jurídica'.
Produtos usados no pedido:

  - (215275-0-B) TV PANASONIC 32 TC-32A400B LED HD
  - (16374-0-0) KIT FEM BE SECRET - PERA

Serviços existentes no pedido:

  - "Não existem serviços neste pedido"

Garantias existentes no pedido:

  - (140358) Instalação de TV
  - (136711) Perfumaria

Empenhos realizados no pedido:

  - Loja - Retirada Loja com Retenção

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "Pessoa Jurídica", variando as opções de empenho contidas no pedido, serviços, garantias e tipos de pagamentos disponíveis. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Seleciona um cliente (PESSOA JURÍDICA) padrão, criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "PESSOA JURÍDICA";
5. Pesquisa e seleciona um produto (TV PANASONIC 32 TC-32A400B LED HD), que contém as seguintes garantias (INSTALAÇÃO DE TV);
6. Empenha o produto (LOJA - RETIRADA LOJA COM RETENÇÃO);
7. Insere um valor de entrada no pedido;
8. Valida as garantias de todos os produtos inseridos no pedido;
9. Valida os serviços do pedido;
10. Insere a 1ª forma de pagamento da entrada (E - CARTÃO VISA ELECTRON), contendo o valor da entrada;
11. Insere a 2ª forma de pagamento do financiamento (F - BOLETO BANCÁRIO), contendo o restando do valor do pedido;
12. Abre a modal de gerar financiamento e informa um Número do Documento;
13. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
14. Abre a tela de consulta do pedido;
15. Pesquisa pelo pedido feito acima;
16. Reabre o pedido;
17. Pesquisa e adiciona um segundo produto (KIT FEM BE SECRET - PERA), contendo as seguinte garantias (PERFUMARIA);
18. Empenha o mesmo (LOJA - RETIRADA LOJA COM RETENÇÃO);
19. Cancela o empenho do produto (TV PANASONIC 32 TC-32A400B LED HD);
20. Reempenha o produto (TV PANASONIC 32 TC-32A400B LED HD) (LOJA - RETIRADA LOJA COM RETENÇÃO);
21. Valida as garantias de todos os produtos no pedido;
22. Valida os serviços do pedido;
23. Abre a aba planilha e remove as linhas da planilha;
24. Insere a 1ª forma de pagamento da entrada (E - DINHEIRO), contendo o valor da entrada;
25. Insere a 2ª forma de pagamento do financiamento (F - BOLETO BANCÁRIO), contendo o restando do valor do pedido;
26. Abre a modal de gerar financiamento e informa um Número do Documento;
27. Salva o pedido, efetua o fechamento total do mesmo.;
```
### Código fonte do teste
[014_pedidoComTipoVendaPessoaJuridica.js](Testes/test/novos_testes/014_pedidoComTipoVendaPessoaJuridica.js)