Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal 29	NOTA FISCAL ACOMPANHAMENTO NFCE S/ICMS-UF2330 ao final do processo


Scenario: Nota fiscal de venda ao final do processo 29	NOTA FISCAL ACOMPANHAMENTO NFCE S/ICMS-UF2330 - CENÁRIO - CLIENTE CONSUMIDOR
Meta:
@tag component:Smoke testing - MVSH
@context NOTAS_FISCAIS
@base MVSH
@grupoInstancias AdmCD
@issue nf-08
@nf-08

Given Eu acesso a instancia padrao
When Eu faco login na loja 1090 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 29
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Eu informo o numero do cupom de um pedido faturado consumidor NFCE
And Eu informo a empresa do cupom de um pedido faturado consumidor NFCE
And Eu informo o codigo loja do cupom de um pedido faturado consumidor NFCE
And Eu informo o numero da serie do cupom de um pedido faturado consumidor NFCE
And Eu clico no botao Pesquisar nota fiscal tipo 1
And Eu seleciono o cupom fiscal de um pedido faturado consumidor NFCE
And Eu clico no botao Concluir parametros e informo CPF na nota

And No popup de selecao cliente eu informo o cpf 191
And No popup de selecao cliente eu clico no botao Pesquisar
And No popup de selecao cliente eu seleciono o cliente
And No popup de selecao cliente eu clico no botao Selecionar

And Eu clico no botao Concluir emissao nf avulsa tipo 5
Then Eu salvo a nota fiscal do teste @nf-08-a no relatorio


Scenario: Emite Nota Fiscal Avulsa Tipo 29	NOTA FISCAL ACOMPANHAMENTO NFCE S/ICMS-UF2330 - CENÁRIO - CLIENTE COM CPF
Meta:
@tag component:Processos - MVSH
@context NOTAS_FISCAIS
@base MVSH
@grupoInstancias AdmCD
@issue nf-08
@nf-08

Given Eu acesso a instancia padrao
When Eu faco login na loja 1090 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 29
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Eu informo o numero do cupom de um pedido faturado cpf NFCE
And Eu informo a empresa do cupom de um pedido faturado cpf NFCE
And Eu informo o codigo loja do cupom de um pedido faturado cpf NFCE
And Eu informo o numero da serie do cupom de um pedido faturado cpf NFCE
And Eu clico no botao Pesquisar nota fiscal tipo 1
And Eu seleciono o cupom fiscal de um pedido faturado cpf NFCE

And Eu clico no botao Concluir parametros
And Eu clico no botao Concluir emissao nf avulsa tipo 29
Then Eu salvo a nota fiscal do teste @nf-08-b no relatorio