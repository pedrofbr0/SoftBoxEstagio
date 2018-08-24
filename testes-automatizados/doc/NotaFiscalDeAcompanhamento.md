# Caso de test Nota Fiscal de Acompanhamento com tipo de venda 'A Vista' com Empenho em Loja e Entrega Normal.
Produtos usados no pedido:

    - (711-0-0) PAN PRESSAO PANELUX 4.5LTS POLIDA

Serviços existentes no pedido:

    - "Não existem validações de serviços nesse caso de teste"

Garantias existentes no pedido:

    - "Não existem validações de garantias nesse caso de teste"

Empenhos realizados no pedido:

    - Loja - Normal

> O caso de teste descrito abaixo realiza um pedido com tipo de venda "A Vista". Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema com os seguintes dados: Instância: http://10.41.0.100:8080/lojas/seguranca?action=login Usuário: 7380 Senha: 1;
2. Seleciona um usuário (PESSOA FÍSICA) padrão, criado por um teste automatizado;
3. Abre a tela de elaboração de pedido;
4. Informa o tipo de venda "A VISTA";
5. Pesquisa e seleciona um produto (PAN PRESSAO PANELUX 4.5LTS POLIDA);
6. Empenha o produto (LOJA - NORMAL);
7. Insere a forma de pagamento ("E - DINHEIRO");
8. Efetua o fechamento total do pedido e fecha a tela de elaboração de pedido;
9. Recebe o pedido criado, no PDV;

10. Abre a tela de Emissão de NF Avulsa (Faturamento > Emissão NF Avulsa);
11. Informa a operação de nota fiscal ("Saída");
12. Seleciona o tipo de Nota Fiscal ("NOTA FISCAL ACOMPANHAMENTO CUPOM FISCAL - UF2330");
13. Informa um grupo de estoque ("1 - Loja") e clica no botão Informar Parâmetros;
14. Na janela de Parâmetros informa os seguintes parâmetros (Cupom Fiscal, Empresa, Cod. Loja, Série NF), e clica em pesquisar;
15. Seleciona o cupom fiscal encontrado na pesquisa e clica em Concluir;
16. Valida as informações da nota fiscal e clica no botão Concluir Nota Fiscal;
```
### Código fonte do teste
(/TestesSelenium/.../test/NotaFiscalDeAcompanhamento.java)