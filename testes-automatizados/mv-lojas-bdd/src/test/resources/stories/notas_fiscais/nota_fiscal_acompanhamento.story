Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo

Scenario: Emite Nota Fiscal Avulsa Tipo 30	NOTA FISCAL ACOMPANHAMENTO CUPOM FISCAL - UF2330
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-06
@nf-06

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 30
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Eu informo o numero do cupom de um pedido faturado
And Eu informo a empresa do cupom de um pedido faturado
And Eu informo o codigo loja do cupom de um pedido faturado
And Eu informo o numero da serie do cupom de um pedido faturado
And Eu clico no botao Pesquisar nota fiscal tipo 1

And Eu seleciono o cupom fiscal de um pedido faturado
And Eu clico no botao Concluir parametros
And Eu clico no botao Concluir emissao nf avulsa tipo 30
Then Eu salvo a nota fiscal do teste @nf-06 no relatorio