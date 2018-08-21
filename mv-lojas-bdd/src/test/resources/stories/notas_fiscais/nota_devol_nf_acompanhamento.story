Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 32 DEVOL.DE NF ACOMPANHAMENTO CUPOM FISCAL - UF1550
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-15
@nf-15

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Entrada da nota fiscal
And Eu seleciono o tipo de nota fiscal 32
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros
And No popup de parametros eu informo o numero da nota tipo 30 da loja 291
And No popup de parametros eu informo a empresa da nota tipo 30 da loja 291
And No popup de parametros eu informo o codigo loja da nota tipo 30 da loja 291
And No popup de parametros eu informo o numero da serie da nota tipo 30 da loja 291
And Eu clico no botao Pesquisar nota fiscal tipo 30
And Eu seleciono o nota fiscal tipo 30
And Eu clico no botao Concluir parametros

And Na tela de nota avulsa eu clico na aba Outros da nota fiscal
And Na tela de nota avulsa eu informo a descricao especie AUTOMACAO
And Na tela de nota avulsa eu informo a quantidade especie 1

And Eu clico no botao Concluir emissao nf avulsa tipo 32
Then Eu salvo a nota fiscal do teste @nf-15 no relatorio