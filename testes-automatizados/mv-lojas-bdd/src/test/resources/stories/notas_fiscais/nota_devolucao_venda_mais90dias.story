Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 23	DEVOL.DE VENDAS (COM MAIS DE 90 DIAS) - UF1275
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-05
@nf-05

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Entrada da nota fiscal
And Eu seleciono o tipo de nota fiscal 23
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Eu informo o numero da nota com mais de 90 dias
And Eu informo a empresa da nota com mais de 90 dias
And Eu informo o codigo loja da nota com mais de 90 dias
And Eu informo o numero da serie da nota com mais de 90 dias
And Eu clico no botao Pesquisar nota fiscal tipo 1

And Eu seleciono o cupom fiscal com mais de 90 dias
And Eu clico no botao Concluir parametros
And Eu clico no botao Concluir emissao nf avulsa tipo 23
Then Eu salvo a nota fiscal do teste @nf-05 no relatorio