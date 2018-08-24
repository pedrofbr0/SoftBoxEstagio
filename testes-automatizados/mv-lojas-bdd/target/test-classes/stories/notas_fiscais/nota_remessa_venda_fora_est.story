Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 51 - REM.PARA VENDA FORA DO ESTAB. S/EST S/ICMS - UF2260
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-10
@nf-10

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 51
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros


And Na tela de parametros da nota avulsa eu clico no radio Cliente
And Na tela de parametros da nota avulsa eu clico botao Pesquisar cliente

And No popup de selecao cliente eu informo o cpf 191
And No popup de selecao cliente eu clico no botao Pesquisar
And No popup de selecao cliente eu seleciono o cliente
And No popup de selecao cliente eu clico no botao Selecionar


And Na tela de parametros da nota avulsa eu seleciono o endereco do cliente
And Eu clico no botao Concluir parametros

And Na tela de nota avulsa eu clico na aba Itens da nota fiscal
And Na tela de nota avulsa eu clico no botao Incluir Produto
And No popup de itens de autorizacao eu pesquiso o produto 711
And Na tela de nota avulsa eu informo a quantidade 1 do item da nota fiscal

And Eu clico no botao Concluir emissao nf avulsa tipo 51
Then Eu salvo a nota fiscal do teste @nf-10-a no relatorio


Scenario: Emite Nota Fiscal Avulsa Tipo 151 - RET REMSSA VENDA FORA DO ESTABEL. - S/ICMS S/EST - UF1395
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-10
@nf-10

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Entrada da nota fiscal
And Eu seleciono o tipo de nota fiscal 151
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros
And Eu informo o numero da nota tipo 51
And Eu informo a empresa da nota tipo 51
And Eu informo o codigo loja da nota tipo 51
And Eu informo o numero da serie da nota tipo 51
And Eu clico no botao Pesquisar nota fiscal tipo 51
And Eu seleciono o nota fiscal tipo 51
And Eu clico no botao Concluir parametros

And Na tela de nota avulsa eu clico na aba Outros da nota fiscal
And Na tela de nota avulsa eu informo a descricao especie AUTOMACAO
And Na tela de nota avulsa eu informo a quantidade especie 1

And Eu clico no botao Concluir emissao nf avulsa tipo 151
Then Eu salvo a nota fiscal do teste @nf-10-b no relatorio