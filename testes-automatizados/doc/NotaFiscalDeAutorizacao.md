# Caso de test Nota Fiscal de Autorizacao.

> O caso de teste descrito abaixo valida o processo de criação de uma nota fiscal de autorização . Segue abaixo o passo a passo do caso de teste.

### Casos de Teste
```sh
1. Loga no sistema;
2. Acessa a tela de Faturamento > Cadastros > Autorização;
4. Informa o tipo de autorização: "1 - NOTA FISCAL", loja destino: "47", observação: "Teste" e Tipo de Nota Fiscal: "25 - TRANSFERENCIA - UF2120" e salva;
5. Adiciona um produto na autorização: cod_nro_produto: "16397";
6. Informa a quantidade solicitada "1" e salva;
7. Pesquise a autorização criada acima e clique em "Processar";
8. Pesquise e selecione novamente a autorização acima e que esteja com status "Pendente";
9. Informe a quantidade autorizada "1" e salva;
10. Clique em "Processar";

11. Informa um grupo de estoque origem ("1 - Loja") e clica no botão Informar Parâmetros;
12. Na janela de Parâmetros informe os seguintes parâmetros, empresa: "5 - RN COMERCIO VAREJISTA S.A - RELOEXA", loja destino: "601 - CD_047_MG" e grupo estoque destino: "60 - Transito" e clica em "Concluir";
```
### Código fonte do teste
(/TestesSelenium/.../test/NotaFiscalDeAutorizacao.java)