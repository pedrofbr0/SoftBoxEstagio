# Caso de test Nota Fiscal de Regularização de Estoque.

> O caso de teste descrito abaixo valida o processo de regularização de estoque de produtos. Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Acessa a tela de Faturamento > Estoque > Regularização de Estoque;
> Criando "Regularização de Saída";
3. Pesquisa um produto no filtro de saída: cod_nro_produto: "711";
4. Informa o GE Saída: "1 - Loja" e Quantidade: "1" e clica em "Inserir";
5. Informa a Observação: "Teste", Duplicata: "123456", Matrícula: "nro_usuario" e Senha Matrícula: "senha_usuario" e clica no botão "Concluir";
> Criando "Regularização de Entrada";
6. Pesquisa um produto no filtro de entrada: cod_nro_produto: "711";
7. Informa o GE Entrada: "1 - Loja" e Quantidade: "1" e clica em "Inserir";
8. Informa a Observação: "Teste", Duplicata: "123456", Matrícula: "nro_usuario" e Senha Matrícula: "senha_usuario" e clica no botão "Concluir";
```
### Código fonte do teste
(/TestesSelenium/.../test/NotaFiscalRegularizacaoEstoque.java)