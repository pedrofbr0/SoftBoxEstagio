Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 244 - DEVOL.DE COMPRAS P/COMERCIALIZACAO (FORNEC.C/ARMAZ.GERAL) - UF2140
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-14
@nf-14

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 244
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Eu informo o numero da nota via integracao tipo 614
And Eu informo a empresa da nota via integracao tipo 614
And Eu informo o codigo loja da nota via integracao tipo 614
And Eu informo o numero da serie da nota via integracao tipo 614
And Eu clico no botao Pesquisar nota fiscal tipo 614
And Eu seleciono o nota fiscal tipo 614
And Eu clico no botao Concluir parametros


And Na tela de nota avulsa eu clico na aba Transportador da nota fiscal
And Na tela de nota avulsa eu seleciono o tipo de frete Emitente

And Eu clico no botao Concluir emissao nf avulsa tipo 244
Then Eu salvo a nota fiscal do teste @nf-14 no relatorio